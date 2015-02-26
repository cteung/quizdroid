package edu.washington.cteung.quizdroid;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by chris_000 on 2/25/2015.
 */

public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

    public static JSONArray json;

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... arg0) {

        // instantiate our json parser
        JsonParser jParser = new JsonParser();
        try{
            // get json string from url
            json = jParser.getJSONFromUrl(MainActivity.url);
            QuizApp.setTr(new TopicRepository(AsyncTaskParseJson.json));

        }catch (Exception e) {
            MainActivity.dlFail();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {}


}