package edu.washington.cteung.quizdroid;

import java.util.ArrayList;

/**
 * Created by chris_000 on 2/3/2015.
 */

//Builds questions for topic
public class TopicPhysics extends Topic {

    public TopicPhysics() {
        String q; //question
        String[] a; //array of possible answers
        int ans; //index of correct answer in array a
        ArrayList<Question> qu = new ArrayList<Question>(); //List of each question

        setDescription("This quiz is about Physics....");

        q = "Light year is a unit of";
        a = new String[]{"Time", "Light", "Distance", "Intensity of light"};
        ans = 2;
        qu.add(new Question(q, a, ans));

        q = "Metals are good conductors of electricity because";
        a = new String[]{"they contain free electrons", "the atoms are lightly packed", "they have high melting point", "All of the above"};
        ans = 0;
        qu.add(new Question(q, a, ans));

        q = "Pick out the scalar quantity";
        a = new String[]{"force", "pressure", "velocity", "acceleration"};
        ans = 1;
        qu.add(new Question(q, a, ans));

        q = "Sound waves in air are";
        a = new String[]{"w", "c", "w", "w"};
        ans = 1;
        qu.add(new Question(q, a, ans));

        q = "q5";
        a = new String[]{"transverse", "electromagnetic", "longitudinal", "polarised"};
        ans = 2;
        qu.add(new Question(q, a, ans));

        setQuestions(qu);
    }

}
