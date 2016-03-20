package com.ken.firebaselearn.application;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;
import com.ken.firebaselearn.ui.entity.MessageItem;
import com.ken.firebaselearn.utils.PrefUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 18/03/2016.
 */
public class MyApp extends Application{

    public static Firebase mFirebaseRoot;
    private static PrefUtil mPrefUtil;

    public static List<MessageItem> listMessage = new ArrayList<MessageItem>();


    @Override
    public void onCreate() {
        super.onCreate();
        buildSharedPreferences(this);

        Firebase.setAndroidContext(this);
        mFirebaseRoot = new Firebase("https://kenitvnn-firebase.firebaseio.com/");

    }


    public static PrefUtil getPrefInstance () {
        return mPrefUtil;
    }

    private void buildSharedPreferences (Context context) {
        mPrefUtil = new PrefUtil(context);
    }
}