package com.ken.firebaselearn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.EditText;

import com.ken.firebaselearn.R;
import com.ken.firebaselearn.presenter.ILoginPresenter;
import com.ken.firebaselearn.presenter.impl.LoginPresenter;
import com.ken.firebaselearn.ui.view.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseAuthActivity implements ILoginView{

    @Bind(R.id.button_signin) Button buttonSignin;
    @Bind(R.id.button_signup) Button buttonSignup;
    @Bind(R.id.edit_text_email) EditText editTextEmail;
    @Bind(R.id.edit_text_password) EditText editTextPassword;
    @Bind(R.id.input_layout_email) TextInputLayout inputLayoutEmail;
    @Bind(R.id.input_layout_password) TextInputLayout inputLayoutPassword;


    private ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);

    }


    @OnClick(R.id.button_signin) void signinOnClick () {
        presenter.validate(editTextEmail.getText().toString(), editTextPassword.getText().toString());
    }


    @OnClick(R.id.button_signup) void signupOnClick () {
        Intent intent = new Intent (LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }


    @Override
    public void showProgress() {
        mAuthProgressDialog.show();
    }


    @Override
    public void hideProgress() {
        mAuthProgressDialog.hide();
    }


    @Override
    public void setEmailError() {
        inputLayoutEmail.setError(getString(R.string.err_msg_email));
        requestFocus(editTextEmail);
    }


    @Override
    public void setPasswordError() {
        inputLayoutPassword.setError(getString(R.string.err_msg_password));
        requestFocus(editTextPassword);
    }


    @Override
    public void navigateToChatScreen() {
        openChatScreen();
    }

}

