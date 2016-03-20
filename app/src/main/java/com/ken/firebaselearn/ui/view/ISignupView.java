package com.ken.firebaselearn.ui.view;

/**
 * Created by ken on 20/03/2016.
 */
public interface ISignupView {

    public void showProgress ();

    public void hideProgress ();

    public void setEmailError ();

    public void setEmailIsAlready ();

    public void setPasswordError ();

    public void navigateToChatScreen ();
}
