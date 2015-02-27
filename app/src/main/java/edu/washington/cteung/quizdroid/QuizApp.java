package edu.washington.cteung.quizdroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

        String filename = "quizdata.json";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            InputStream is = getAssets().open("quizdata.json.txt");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            try {
                JSONArray ja = new JSONArray(json);
                QuizApp.getInstance().setTr(new TopicRepository(ja));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

    public static void setTr(TopicRepository t) {
        tr = t;
    }

}
