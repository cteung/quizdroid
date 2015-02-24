package edu.washington.cteung.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by chris_000 on 2/22/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String u = intent.getStringExtra("URL");

        // For our recurring task, we'll just display a message
        Toast.makeText(context, u, Toast.LENGTH_SHORT).show();
    }
}
