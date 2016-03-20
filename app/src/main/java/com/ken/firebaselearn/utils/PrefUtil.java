package com.ken.firebaselearn.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ken on 19/03/2016.
 */
public class PrefUtil {

    private static final String SHARED_NAME		    = "fire_base_shared";
    private static final String PREF_UID		    = "pref_uid";
    private static final String PREF_USER_NAME      = "pref_user_name";
    private static final String PREF_USER_EMAIL     = "pref_user_email";
    private static final String PREF_TOKEN          = "pref_token";
    private static final String PREF_TOKEN_EXPIRES  = "pref_token_expires";

    private static SharedPreferences mPrefs;


    public PrefUtil (Context context) {
        mPrefs = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    public void setPrefUserEmail (String userEmail) {
        setPrefString(PREF_USER_EMAIL, userEmail);
    }

    public String getPrefUserEmail () {
        return getPrefString(PREF_USER_EMAIL, "");
    }



    public String getPrefString (String pref_key, String string_default) {
        return mPrefs.getString(pref_key, string_default);
    }

    public void setPrefString (String pref_key, String pref_value) {
        mPrefs.edit().putString(pref_key, pref_value).commit();
    }

    public boolean getPrefBoolean (String pref_key, boolean your_bool) {
        return mPrefs.getBoolean(pref_key, your_bool);
    }

    public void setPrefBoolean (String pref_key, boolean pref_value) {
        mPrefs.edit().putBoolean(pref_key, pref_value);
    }

}