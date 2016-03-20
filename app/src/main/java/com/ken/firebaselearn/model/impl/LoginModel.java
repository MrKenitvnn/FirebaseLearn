package com.ken.firebaselearn.model.impl;

import android.text.TextUtils;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.ken.firebaselearn.application.MyApp;
import com.ken.firebaselearn.listener.OnLoginListener;
import com.ken.firebaselearn.model.ILoginModel;
import com.ken.firebaselearn.utils.PrefUtil;

/**
 * Created by ken on 19/03/2016.
 */
public class LoginModel implements ILoginModel {

    PrefUtil prefUtil = MyApp.getPrefInstance();

    @Override
    public void login(final String user_email, String user_password, final OnLoginListener listener) {

        final boolean[] error = {false};
        if (TextUtils.isEmpty(user_email)) {
            listener.onEmailError();
            error[0] = true;
        }
        if (TextUtils.isEmpty(user_password)) {
            listener.onPasswordError();
            error[0] = true;
        }
        if (!error[0]) {

        }

        MyApp.mFirebaseRoot.authWithPassword(user_email, user_password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listener.onSuccess();
                prefUtil.setPrefUserEmail(user_email);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listener.onPasswordError();
                error[0] = true;
            }
        });
    }

}