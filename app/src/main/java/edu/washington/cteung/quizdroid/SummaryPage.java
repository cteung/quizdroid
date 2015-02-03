package edu.washington.cteung.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SummaryPage extends ActionBarActivity {

    private Topic m;
    boolean gotCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        m = (Topic)launchedMe.getSerializableExtra("topic");  // get data that was passed from first activity

        final String selectedAnswer = (String)launchedMe.getSerializableExtra("selectedAnswer");
        Button summary = (Button) findViewById(R.id.btn_summary);

        if (m == null) {
            finish();
        }else{
            int correct = m.getCorrect();
            int progress = m.getProgress();

            if (progress == m.qSize()) {
                summary.setText("Finish");
                summary.setEnabled(false);
            } else {
                summary.setText("Next");
            }

            // add the extra data into the text view of the 2nd activity (this layout)
            TextView selectedAns = (TextView) findViewById(R.id.selectedAns);
            selectedAns.setText("You entered: " + selectedAnswer);

            TextView correctAns = (TextView) findViewById(R.id.correctAns);
            correctAns.setText("Correct answer: " + m.currentQuestion().answer());

            TextView progressText = (TextView) findViewById(R.id.progress);
            progressText.setText("You have " + Integer.toString(correct) + " out of " + Integer.toString(progress) + " correct");

        }

        // On button click Open QuestionPage activity
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(SummaryPage.this, QuestionPage.class); // cannot use just this cuz this refers to the listener, not the outer this

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
    public void onBackPressed() {
        // super.onBackPressed();

        m.progressBack();
        Intent intent = new Intent();
        intent.putExtra("topic", m);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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
