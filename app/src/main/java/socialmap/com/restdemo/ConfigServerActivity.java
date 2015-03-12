package socialmap.com.restdemo;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by yy on 3/10/15.
 */
public class ConfigServerActivity extends PreferenceActivity{

    public static final String KEY_PREF_SERVER_HOST = "pref_server_host";
    public static final String KEY_PREF_SERVER_PORT = "pref_server_port";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
