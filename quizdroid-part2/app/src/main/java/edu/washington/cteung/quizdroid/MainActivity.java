package edu.washington.cteung.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b_math = (Button) findViewById(R.id.btn_math);

        // On button click Open MainPage activity for Math Topic
        b_math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, MainPage.class);

                // add data to be passed to next activity
                nextActivity.putExtra("topic", new TopicMath());

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }

            }
        });

        Button b_phys = (Button) findViewById(R.id.btn_phys);

        // On button click Open MainPage activity for Physics Topic
        b_phys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, MainPage.class);

                // add data to be passed to next activity
                nextActivity.putExtra("topic", new TopicPhysics());

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }

            }
        });

        Button b_marvel = (Button) findViewById(R.id.btn_marvel);

        // On button click MainPage activity for Marvel Topic
        b_marvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, MainPage.class);

                // add data to be passed to next activity
                nextActivity.putExtra("topic", new TopicMarvel());

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }

            }
        });
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
}
