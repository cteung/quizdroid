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

        final TopicRepository tr = QuizApp.getInstance().getTr();

        final Button b0 = (Button) findViewById(R.id.btn0);

        // On button click Open MainPage activity for Math Topic
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, MainPage.class);

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

                // add data to be passed to next activity
                nextActivity.putExtra("topic", tr.getTopicAtIndex(2));
                b2.setText(tr.getTopicAtIndex(2).getTitle());

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
