package edu.washington.cteung.quizdroid;

import java.util.ArrayList;

/**
 * Created by chris_000 on 2/3/2015.
 */
public class TopicMarvel extends Topic{
    public TopicMarvel() {
        String q;
        String[] a;
        int ans;
        ArrayList<Question> qu = new ArrayList<Question>();

        setDescription("Marvel Super Heroes....");

        q = "q1";
        a = new String[]{"w", "c", "w", "w"};
        ans = 1;
        qu.add(new Question(q, a, ans));

        q = "q2";
        a = new String[]{"w", "w", "w", "c"};
        ans = 3;
        qu.add(new Question(q, a, ans));

        q = "q3";
        a = new String[]{"w", "w", "c", "w"};
        ans = 2;
        qu.add(new Question(q, a, ans));

        q = "q4";
        a = new String[]{"w", "c", "w", "w"};
        ans = 1;
        qu.add(new Question(q, a, ans));

        q = "q5";
        a = new String[]{"c", "w", "w", "w"};
        ans = 0;
        qu.add(new Question(q, a, ans));


        setQuestions(qu);
    }
}
