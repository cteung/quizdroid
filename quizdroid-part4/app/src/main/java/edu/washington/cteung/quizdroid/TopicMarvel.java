package edu.washington.cteung.quizdroid;

import java.util.ArrayList;

/**
 * Created by chris_000 on 2/3/2015.
 */

//Builds questions for topic
public class TopicMarvel extends Topic{
    public TopicMarvel() {
        setTitle("Marvel Super Heroes");
        String q; //question
        String[] a; //array of possible answers
        int ans; //index of correct answer in array a
        ArrayList<Question> qu = new ArrayList<Question>(); //List of each question

        setDescription("This quiz is about Marvel Super Heroes....");

        q = "The Fantastic Four have the headquarters in what building?";
        a = new String[]{"Fantastic Headquarters", "Stark Tower", "Baxter Building", "Xavier Institute"};
        ans = 2;
        qu.add(new Question(q, a, ans));

        q = "Peter Parker works as a photographer for:";
        a = new String[]{"The Daily Planet", "The New York Times", "The Rolling Stone", "The Daily Bugle"};
        ans = 3;
        qu.add(new Question(q, a, ans));

        q = "S.H.I.E.L.D.'s highest ranking agent is";
        a = new String[]{"Steven Rogers", "Peter Parker", "Nick Fury", "Natalia Romanova"};
        ans = 2;
        qu.add(new Question(q, a, ans));

        q = "Captain America was frozen in which war?";
        a = new String[]{"World War I", "World War II", "Cold War", "American Civil War"};
        ans = 1;
        qu.add(new Question(q, a, ans));

        q = "The vampire hunter Blade is a:";
        a = new String[]{"Mutant", "Human", "Vampire", "Half vampire"};
        ans = 3;
        qu.add(new Question(q, a, ans));

        setQuestions(qu);
    }
}
