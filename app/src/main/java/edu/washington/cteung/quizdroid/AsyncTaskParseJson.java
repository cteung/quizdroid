package edu.washington.cteung.quizdroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by chris_000 on 2/25/2015.
 */


public class AsyncTaskParseJson extends AsyncTask<String, String, String> {


    AlertDialog.Builder alertDialog;
    public boolean dl;


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        // instantiate our json parser
        JsonParser jParser = new JsonParser();
        try{
            // get json string from url
            JSONArray json;
            json = jParser.getJSONFromUrl(MainActivity.url);
            QuizApp.getInstance().setTr(new TopicRepository(json));
            dl = true;
            MainActivity.saveJson(json);
        }catch (Exception e) {
            dl = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {

        if (!dl){
            alertDialog = new AlertDialog.Builder(MainActivity.getAppContext());
            alertDialog.setTitle("Sorry!");
            alertDialog.setMessage("App failed to download questions.");
            alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    new AsyncTaskParseJson().execute();
                }
            });
            alertDialog.setNegativeButton("Quit and try again later", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.activity.finish();
                }
            });
            alertDialog.create().show();
        }
    }
}