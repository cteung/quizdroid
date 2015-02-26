package edu.washington.cteung.quizdroid;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by chris_000 on 2/16/2015.
 */
public class TopicRepository {

    private ArrayList<Topic> topics;
    final String TAG = "AsyncTaskParseJsonTopicRepo.java";

    public TopicRepository(){

    }

    public TopicRepository(JSONArray ja){
        topics = new ArrayList<Topic>();

        try {
            // loop through all topics
            for (int i = 0; i < ja.length(); i++) {

                //JSONObject c = json.getJSONObject(i);

                // Storing each json item in variable
                String title = ja.getJSONObject(i).getString("title");
                String desc = ja.getJSONObject(i).getString("desc");
                String ques = ja.getJSONObject(i).getString("questions");

                JSONArray q = new JSONArray(ques);

                // show the values in our logcat
                Log.e(TAG, "title: " + title
                        + ", desc: " + desc + ", questions:" + q.getJSONObject(0).getString("text"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        topics.add(new TopicMath());
        topics.add(new TopicPhysics());
        topics.add(new TopicMarvel());
    }

    public Topic getTopicAtIndex(int i){
        return topics.get(i);
    }



}
