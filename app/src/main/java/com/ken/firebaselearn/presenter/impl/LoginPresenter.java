package com.ken.firebaselearn.presenter.impl;

import com.ken.firebaselearn.listener.OnLoginListener;
import com.ken.firebaselearn.model.ILoginModel;
import com.ken.firebaselearn.model.impl.LoginModel;
import com.ken.firebaselearn.presenter.ILoginPresenter;
import com.ken.firebaselearn.ui.view.ILoginView;

/**
 * Created by ken on 19/03/2016.
 */
public class LoginPresenter implements ILoginPresenter, OnLoginListener {

    private ILoginView loginView;
    private ILoginModel loginModel;

    public LoginPresenter (ILoginView iLoginView) {
        this.loginView = iLoginView;
        this.loginModel = new LoginModel();
    }


    @Override
    public void validate (String user_email, String user_password) {
        loginView.showProgress();
        loginModel.login(user_email, user_password, this);
    }


    @Override
    public void onEmailError () {
        loginView.setEmailError();
        loginView.hideProgress();
    }


    @Override
    public void onPasswordError () {
        loginView.setPasswordError();
        loginView.hideProgress();
    }


    @Override
    public void onSuccess () {
        loginView.hideProgress();
        loginView.navigateToChatScreen();
    }
}