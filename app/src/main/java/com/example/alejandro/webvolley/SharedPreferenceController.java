package com.example.alejandro.webvolley;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alejandro on 18/04/18.
 */

public class SharedPreferenceController {

    //Constants
    private static final String SETTING_IP_KEY = "SETTING_IP";

    //SharedPreferences
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public SharedPreferenceController(Context context) {
        sharedPref = context.getSharedPreferences(SETTING_IP_KEY,context.MODE_PRIVATE);

        editor = sharedPref.edit();
    }

    public void setSettingIp(String ip) {
        editor.putString(SETTING_IP_KEY, ip);
        editor.apply();
    }

    public String getSettingIp() {
        return sharedPref.getString(SETTING_IP_KEY, "198.168.1.29");
    }

}
