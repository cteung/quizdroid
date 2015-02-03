package edu.washington.cteung.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionPage extends ActionBarActivity {

    boolean gotCorrect = false;
    Topic m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        m = (Topic)launchedMe.getSerializableExtra("topic");  // get data that was passed from first activity
        final Question q = m.qInProgress();

        final Button submit = (Button) findViewById(R.id.btn_submit);
        submit.setEnabled(false);

        // get handle of RadioGroup
        final RadioGroup answers = (RadioGroup) findViewById(R.id.answers);

        // On button click Open SummaryPage activity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m.getProgress() != m.qSize()){
                    m.progressUp();
                }
                Intent nextActivity = new Intent(QuestionPage.this, SummaryPage.class); // cannot use just this cuz this refers to the listener, not the outer this
                startActivityForResult(nextActivity, 1);

                // get selected radio button from radioGroup
                int selectedId = answers.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton selected = (RadioButton) findViewById(selectedId);
                String selectedAnswer = selected.getText().toString();

                if (selectedAnswer.equals(q.answer())) {
                    m.correctUp();
                    gotCorrect = true;
                }

                // add data to be passed to next activity
                nextActivity.putExtra("topic", m);
                nextActivity.putExtra("selectedAnswer", selectedAnswer);
                nextActivity.putExtra("gotCorrect", gotCorrect);

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }
                // code still runs asynchronously

                //finish(); // kill this instance self (this activity)
            }
        });

        // add the extra data into the text view of the 2nd activity (this layout)
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(q.getQ());

        TextView a0 = (TextView) findViewById(R.id.ans0);
        a0.setText(q.AnsAtIndex(0));

        TextView a1 = (TextView) findViewById(R.id.ans1);
        a1.setText(q.AnsAtIndex(1));

        TextView a2 = (TextView) findViewById(R.id.ans2);
        a2.setText(q.AnsAtIndex(2));

        TextView a3 = (TextView) findViewById(R.id.ans3);
        a3.setText(q.AnsAtIndex(3));

        // listener for RadioGroup Java Android example
        answers.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {
                        submit.setEnabled(true);
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
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
