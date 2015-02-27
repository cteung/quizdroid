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

    public TopicRepository(JSONArray ja){
        topics = new ArrayList<Topic>();


        try {
            // loop through all topics
            for (int i = 0; i < ja.length(); i++) {

                Topic t = new Topic();
                ArrayList<Question> qu = new ArrayList<Question>();


                // Storing each json item in variable
                String title = ja.getJSONObject(i).getString("title");
                String desc = ja.getJSONObject(i).getString("desc");
                String ques = ja.getJSONObject(i).getString("questions");

                t.setTitle(title);
                t.setDescription(desc);

                JSONArray q = new JSONArray(ques);

                for (int j = 0; j < q.length(); j++){

                    String text = q.getJSONObject(i).getString("text");
                    String answer = q.getJSONObject(i).getString("answer");
                    String a = q.getJSONObject(i).getString("answers");

                    JSONArray answers = new JSONArray(a);

                    String zero = answers.getString(0);
                    String one = answers.getString(1);
                    String two = answers.getString(2);
                    String three = answers.getString(3);

                    String[] as = new String[]{zero, one, two, three};

                    qu.add(new Question(text, as, Integer.parseInt(answer)));

                }

                t.setQuestions(qu);
                topics.add(t);

                // show the values in our logcat
                Log.e(TAG, "title: " + title
                        + ", desc: " + desc + ", questions:" + q.getJSONObject(0).getString("text"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Topic getTopicAtIndex(int i){
        return topics.get(i);
    }



}
