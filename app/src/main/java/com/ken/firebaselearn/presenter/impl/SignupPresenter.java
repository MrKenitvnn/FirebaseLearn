package com.ken.firebaselearn.presenter.impl;

import com.ken.firebaselearn.listener.OnSignupListener;
import com.ken.firebaselearn.model.ISignupModel;
import com.ken.firebaselearn.model.impl.SignupModel;
import com.ken.firebaselearn.presenter.ISignupPresenter;
import com.ken.firebaselearn.ui.view.ISignupView;

/**
 * Created by ken on 20/03/2016.
 */
public class SignupPresenter implements ISignupPresenter, OnSignupListener {

    private ISignupModel signupModel;
    private ISignupView signupView;

    public SignupPresenter (ISignupView iSignupView) {
        signupView = iSignupView;
        this.signupModel = new SignupModel();
    }

    @Override
    public void signupEvent(String user_email, String user_password) {
        signupView.showProgress();
        signupModel.signup(user_email, user_password, this);
    }

    @Override
    public void onEmailError() {
        signupView.setEmailError();
        signupView.hideProgress();
    }

    @Override
    public void onEmailIsAlready() {
        signupView.setEmailIsAlready();
        signupView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        signupView.setPasswordError();
        signupView.hideProgress();
    }

    @Override
    public void onSuccess() {
        signupView.navigateToChatScreen();
    }
}
