package edu.washington.cteung.quizdroid;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by chris_000 on 2/2/2015.
 */
public class Question implements Serializable{

    private String question;
    private int answerIndex;
    private String[] answers = new String[4];

    public Question(String q, String[] a, int ansIndex) {
        question = q;
        answers = a;
        answerIndex = ansIndex;
    }

    public String getQ() {
        return question;
    }

    public String AnsAtIndex(int i) {
        return  answers[i];
    }

    public int indexOfAnswer() {
        return answerIndex;
    }

    public String answer() {
        return answers[answerIndex];
    }

    public String[] getAnswers() {
        return answers;
    }
}
