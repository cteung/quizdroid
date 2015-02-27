package edu.washington.cteung.quizdroid;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    public static Activity activity;
    private static final int SETTINGS_RESULT = 1;
    private int min;
    private PendingIntent pendingIntent;
    private Intent alarmIntent;
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);


        displayUserSettings();

        final Button b0 = (Button) findViewById(R.id.btn0);

        // On button click Open MainPage activity for Math Topic
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, MainPage.class);

                TopicRepository tr = QuizApp.getInstance().getTr();

                // add data to be passed to next activity
                nextActivity.putExtra("topic", tr.getTopicAtIndex(0));
                b0.setText(tr.getTopicAtIndex(0).getTitle());

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }

            }
        });

        final Button b1 = (Button) findViewById(R.id.btn1);

        // On button click Open MainPage activity for Physics Topic
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, MainPage.class);

                TopicRepository tr = QuizApp.getInstance().getTr();

                // add data to be passed to next activity
                nextActivity.putExtra("topic", tr.getTopicAtIndex(1));
                b1.setText(tr.getTopicAtIndex(1).getTitle());

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }

            }
        });

        final Button b2 = (Button) findViewById(R.id.btn2);

        // On button click MainPage activity for Marvel Topic
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, MainPage.class);

                TopicRepository tr = QuizApp.getInstance().getTr();

                // add data to be passed to next activity
                nextActivity.putExtra("topic", tr.getTopicAtIndex(2));
                b2.setText(tr.getTopicAtIndex(2).getTitle());

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }

            }
        });

        Button btnSettings=(Button)findViewById(R.id.btnSettings);
        // start the UserSettingActivity when user clicks on Button
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), UserSetting.class);
                startActivityForResult(i, SETTINGS_RESULT);
            }
        });

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            Toast.makeText(getApplicationContext(), "No active network", Toast.LENGTH_SHORT).show();
        }else if (ni.isConnected()){
            Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        }

        checkAirplaneMode();
    }

    private void checkAirplaneMode() {
        int mode;
            mode = Settings.System.getInt(this.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0);
        if(mode == 1 /* airplane mode is on */) {
            new AlertDialog.Builder(this)
                    .setTitle("Sorry!")
                    .setMessage("This app doesn't work with Airplane mode.\n"
                            + "Please disable Airplane mode.\n")
                    .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
                        }
                    })
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SETTINGS_RESULT)
        {
            displayUserSettings();
        }

    }

    @Override
    protected void onDestroy() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void displayUserSettings()
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        url = sharedPrefs.getString("prefURL", "NOURL");

        String  settings = "";

        settings=settings+"URL: " + sharedPrefs.getString("prefURL", "NOURL");

        min = Integer.parseInt(sharedPrefs.getString("prefFreq", "0"));
        if (min > 0){
            alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            alarmIntent.putExtra("URL", sharedPrefs.getString("prefURL", "NOURL"));
            start();
        }
        settings=settings+"\nUpdate Frequency(Minutes): "+ sharedPrefs.getString("prefFreq", "0");

        TextView textViewSetting = (TextView) findViewById(R.id.textViewSettings);

        textViewSetting.setText(settings);
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = min * 60000;

        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();

    }

    public static Context getAppContext() {
        return activity;
    }

}
