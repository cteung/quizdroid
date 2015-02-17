package edu.washington.cteung.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class OverviewFragment extends Fragment {
    Topic topic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_overview, container, false);

        TextView des = (TextView) rootView.findViewById(R.id.description);
        des.setText(topic.getDescription());

        TextView qA = (TextView) rootView.findViewById(R.id.qAmount);
        qA.setText("There are " + Integer.toString(topic.qSize()) + " questions in this quiz");

        Button begin = (Button) rootView.findViewById(R.id.btn_begin);

        // On button click Open Question fragment
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get fragment manager
                FragmentManager fm = getFragmentManager();

                // replace
                QuestionFragment qf = new QuestionFragment();
                qf.topic = topic;
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentView, qf);
                ft.addToBackStack(null);
                ft.commit();
        }
        });

        return rootView;
    }

}