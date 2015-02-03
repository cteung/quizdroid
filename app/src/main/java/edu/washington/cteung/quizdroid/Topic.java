package edu.washington.cteung.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chris_000 on 2/3/2015.
 */
public abstract class Topic implements Serializable {

    private String description;
    private ArrayList<Question> Questions;
    private int progress = 0;
    private int correct = 0;
    
    public Question getQuestionAtIndex(int i) {
        return Questions.get(i);
    }

    public Question currentQuestion() {
        return Questions.get(progress-1);
    }

    public String getDescription() {
        return description;
    }

    public int qSize() {
        return Questions.size();
    }

    public int getProgress() {
        return progress;
    }

    public void progressUp() {
        progress++;
    }

    public void progressBack() {
        progress--;
    }

    public int getCorrect() {
        return correct;
    }

    public void correctUp() {
        correct++;
    }

    public void correctBack() {
        correct--;
    }

    public Question qInProgress() {
        return Questions.get(progress);
    }

    public void setDescription(String d) {
        description = d;
    }

    public void setQuestions(ArrayList<Question> q) {
        Questions = q;
    }
}
