package edu.washington.cteung.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by chris_000 on 2/16/2015.
 */
public class QuizApp extends Application {

    private static QuizApp instance = null;
    private static TopicRepository tr;

    public static QuizApp getInstance() {
        if (instance == null)
        {
            // Create the instance
            instance = new QuizApp();
        }
        return  instance;
    }

    public TopicRepository getTr(){
        return tr;
    }

    @Override
    public void onCreate()
    {
        Log.d("Singleton", "QuizApp created!");
        super.onCreate();
        tr = new TopicRepository();
    }

}
