package com.ken.firebaselearn.ui.view;

/**
 * Created by ken on 19/03/2016.
 */
public interface ILoginView {

    public void showProgress ();

    public void hideProgress ();

    public void setEmailError ();

    public void setPasswordError ();

    public void navigateToChatScreen ();
}