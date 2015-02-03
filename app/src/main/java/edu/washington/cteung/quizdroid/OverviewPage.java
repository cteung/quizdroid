package edu.washington.cteung.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class OverviewPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        final Topic m = (Topic)launchedMe.getSerializableExtra("topic");  // get data that was passed from first activity

        // add the extra data into the text view of the 2nd activity (this layout)
        TextView des = (TextView) findViewById(R.id.description);
        des.setText(m.getDescription());

        TextView qA = (TextView) findViewById(R.id.qAmount);
        qA.setText("There are "+Integer.toString(m.qSize())+" questions in this quiz");

        Button begin = (Button) findViewById(R.id.btn_begin);

        // On button click Open 2nd activity
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(OverviewPage.this, QuestionPage.class); // cannot use just this cuz this refers to the listener, not the outer this

                // add data to be passed to next activity
                nextActivity.putExtra("topic", m);


                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }
                // code still runs asynchronously

                finish(); // kill this instance self (this activity)
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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
