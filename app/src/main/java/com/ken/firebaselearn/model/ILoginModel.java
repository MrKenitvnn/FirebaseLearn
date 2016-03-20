package com.ken.firebaselearn.model;

import com.ken.firebaselearn.listener.OnLoginListener;

/**
 * Created by ken on 19/03/2016.
 */
public interface ILoginModel {
    public void login(String user_email, String user_password, OnLoginListener listener);
}
