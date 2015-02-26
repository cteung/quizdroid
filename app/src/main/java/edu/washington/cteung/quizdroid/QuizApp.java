package edu.washington.cteung.quizdroid;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        MainActivity.url = sharedPrefs.getString("prefURL", "NOURL");

        new AsyncTaskParseJson().execute();

    }

    public static void setTr(TopicRepository t) {
        tr = t;
    }

}
