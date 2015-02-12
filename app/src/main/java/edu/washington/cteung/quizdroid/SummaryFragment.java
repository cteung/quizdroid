package edu.washington.cteung.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;


public class SummaryFragment extends Fragment {

    String selectedAnswer;
    Topic topic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_summary, container, false);

        Button summary = (Button) rootView.findViewById(R.id.btn_summary);

        if (topic == null) {

        }else{
            int correct = topic.getCorrect();
            int progress = topic.getProgress();

            if (progress == topic.qSize()) {
                summary.setText("Finish");
                summary.setEnabled(false);
            } else {
                summary.setText("Next");
            }

            // add the extra data into the text view of the 2nd activity (this layout)
            TextView selectedAns = (TextView) rootView.findViewById(R.id.selectedAns);
            selectedAns.setText("You entered: " + selectedAnswer);

            TextView correctAns = (TextView) rootView.findViewById(R.id.correctAns);
            correctAns.setText("Correct answer: " + topic.currentQuestion().answer());

            TextView progressText = (TextView) rootView.findViewById(R.id.progress);
            progressText.setText("You have " + Integer.toString(correct) + " out of " + Integer.toString(progress) + " correct");

        }

        // On button click Open QuestionPage activity
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get fragment manager
                FragmentManager fm = getFragmentManager();

                // replace
                QuestionFragment qf = new QuestionFragment();
                qf.topic = topic;
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentView, qf);
                ft.commit();

            }
        });

        return rootView;
    }

}