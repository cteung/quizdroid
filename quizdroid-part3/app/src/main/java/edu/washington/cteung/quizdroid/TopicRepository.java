package edu.washington.cteung.quizdroid;

import java.util.ArrayList;

/**
 * Created by chris_000 on 2/16/2015.
 */
public class TopicRepository {

    private ArrayList<Topic> topics;

    public TopicRepository(){
        topics = new ArrayList<Topic>();
        topics.add(new TopicMath());
        topics.add(new TopicPhysics());
        topics.add(new TopicMarvel());
    }

    public Topic getTopicAtIndex(int i){
        return topics.get(i);
    }

}
