package edu.washington.cteung.quizdroid;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chris_000 on 2/2/2015.
 */

//Builds questions for topic
public class TopicMath extends Topic {

    public TopicMath() {
        String q; //question
        String[] a; //array of possible answers
        int ans; //index of correct answer in array a
        ArrayList<Question> qu = new ArrayList<Question>(); //List of each question

        setDescription("This quiz is about Math....");

        q = "2+4";
        a = new String[]{"6", "1", "3", "4"};
        ans = 0;
        qu.add(new Question(q, a, ans));

        q = "2*2";
        a = new String[]{"0", "1", "3", "4"};
        ans = 3;
        qu.add(new Question(q, a, ans));

        q = "2-2";
        a = new String[]{"0", "1", "3", "4"};
        ans = 0;
        qu.add(new Question(q, a, ans));

        q = "1-0";
        a = new String[]{"0", "1", "3", "4"};
        ans = 1;
        qu.add(new Question(q, a, ans));

        q = "3-0";
        a = new String[]{"0", "1", "3", "4"};
        ans = 2;
        qu.add(new Question(q, a, ans));

        setQuestions(qu);
    }

}
