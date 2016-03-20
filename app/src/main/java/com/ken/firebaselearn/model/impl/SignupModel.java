package com.ken.firebaselearn.model.impl;

import android.text.TextUtils;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.ken.firebaselearn.application.MyApp;
import com.ken.firebaselearn.listener.OnSignupListener;
import com.ken.firebaselearn.model.ISignupModel;
import com.ken.firebaselearn.utils.MZLog;
import com.ken.firebaselearn.utils.PrefUtil;

import java.util.Map;

/**
 * Created by ken on 20/03/2016.
 */
public class SignupModel implements ISignupModel {

    private PrefUtil prefUtil = MyApp.getPrefInstance();

    @Override
    public void signup(final String user_email, String user_password, final OnSignupListener listener) {

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

        MyApp.mFirebaseRoot.createUser(user_email, user_password,
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        MZLog.d(result.get("uid").toString());

                        listener.onSuccess();
                        prefUtil.setPrefUserEmail(user_email);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.EMAIL_TAKEN:
                                listener.onEmailIsAlready();
                                break;

                        }
                        error[0] = true;
                    }
                });
    }
}