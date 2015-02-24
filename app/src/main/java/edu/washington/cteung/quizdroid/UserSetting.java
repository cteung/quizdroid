package edu.washington.cteung.quizdroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by chris_000 on 2/24/2015.
 */
public class UserSetting extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // add the xml resource
        addPreferencesFromResource(R.xml.user_settings);
    }
}
