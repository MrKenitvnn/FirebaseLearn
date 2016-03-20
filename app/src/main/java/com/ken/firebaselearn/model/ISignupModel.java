package com.ken.firebaselearn.model;

import com.ken.firebaselearn.listener.OnSignupListener;

/**
 * Created by ken on 20/03/2016.
 */
public interface ISignupModel {
    public void signup (String user_email, String user_password, OnSignupListener listener);
}
