package com.ken.firebaselearn.listener;

/**
 * Created by ken on 20/03/2016.
 */
public interface OnSignupListener {

    public void onEmailError ();

    public void onEmailIsAlready ();

    public void onPasswordError ();

    public void onSuccess ();
}
