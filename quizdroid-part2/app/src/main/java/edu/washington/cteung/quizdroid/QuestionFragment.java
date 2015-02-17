package edu.washington.cteung.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

    Topic topic;
    boolean gotCorrect = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_question, container, false);

        final Question q = topic.qInProgress();

        final Button submit = (Button) rootView.findViewById(R.id.btn_submit);
        submit.setEnabled(false);

        // get handle of RadioGroup
        final RadioGroup answers = (RadioGroup) rootView.findViewById(R.id.answers);

        // add the extra data into the text view of the 2nd activity (this layout)
        TextView question = (TextView) rootView.findViewById(R.id.question);
        question.setText(q.getQ());

        TextView a0 = (TextView) rootView.findViewById(R.id.ans0);
        a0.setText(q.AnsAtIndex(0));

        TextView a1 = (TextView) rootView.findViewById(R.id.ans1);
        a1.setText(q.AnsAtIndex(1));

        TextView a2 = (TextView) rootView.findViewById(R.id.ans2);
        a2.setText(q.AnsAtIndex(2));

        TextView a3 = (TextView) rootView.findViewById(R.id.ans3);
        a3.setText(q.AnsAtIndex(3));

        // listener for RadioGroup Java Android example
        answers.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {
                        submit.setEnabled(true);
                    }
                });

        // On button click Open SummaryPage activity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topic.getProgress() != topic.qSize()){
                    topic.progressUp();
                }

                // get selected radio button from radioGroup
                int selectedId = answers.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton selected = (RadioButton) rootView.findViewById(selectedId);
                String selectedAnswer = selected.getText().toString();

                if (selectedAnswer.equals(q.answer())) {
                    topic.correctUp();
                    gotCorrect = true;
                }

                // get fragment manager
                FragmentManager fm = getFragmentManager();

                // replace
                SummaryFragment sf = new SummaryFragment();
                sf.topic = topic;
                sf.selectedAnswer = selectedAnswer;
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentView, sf);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return rootView;
    }
}