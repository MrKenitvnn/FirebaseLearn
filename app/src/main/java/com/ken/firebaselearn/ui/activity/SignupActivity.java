package com.ken.firebaselearn.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ken.firebaselearn.R;
import com.ken.firebaselearn.application.MyApp;
import com.ken.firebaselearn.presenter.ISignupPresenter;
import com.ken.firebaselearn.presenter.impl.SignupPresenter;
import com.ken.firebaselearn.ui.view.ISignupView;
import com.ken.firebaselearn.utils.PrefUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ken on 20/03/2016.
 */
public class SignupActivity extends BaseAuthActivity implements ISignupView{

    @Bind(R.id.button_signup) Button buttonSingup;
    @Bind(R.id.edit_text_email) EditText editTextEmail;
    @Bind(R.id.edit_text_password) EditText editTextPassword;
    @Bind(R.id.input_layout_email) TextInputLayout inputLayoutEmail;
    @Bind(R.id.input_layout_password) TextInputLayout inputLayoutPassword;


    private PrefUtil prefUtil = MyApp.getPrefInstance();
    private ISignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        presenter = new SignupPresenter(this);
    }


    @OnClick(R.id.button_signup)
    void buttonSignupOnClick() {
        presenter.signupEvent(editTextEmail.getText().toString(), editTextPassword.getText().toString());
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
    public void setEmailIsAlready() {
        Toast.makeText(SignupActivity.this, R.string.err_msg_email_is_already, Toast.LENGTH_SHORT).show();
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
