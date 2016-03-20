package com.ken.firebaselearn.listener;

/**
 * Created by ken on 19/03/2016.
 */
public interface OnLoginListener {

    public void onEmailError ();

    public void onPasswordError ();

    public void onSuccess ();
}
