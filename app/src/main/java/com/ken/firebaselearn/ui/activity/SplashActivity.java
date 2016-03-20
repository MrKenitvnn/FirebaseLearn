package com.ken.firebaselearn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.Window;

import com.ken.firebaselearn.R;
import com.ken.firebaselearn.application.MyApp;

/**
 * Created by ken on 19/03/2016.
 */
public class SplashActivity extends BaseActivity {

    private boolean _active = true;
    private int _splashTime = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {

                } finally {

                    String user_email = MyApp.getPrefInstance().getPrefUserEmail();
                    if (TextUtils.equals("", user_email)) {
                        Intent mainIntent	= new Intent(SplashActivity.this, LoginActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                    } else {
                        Intent mainIntent	= new Intent(SplashActivity.this, ChatActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                    }
                    SplashActivity.this.finish();
                }
            }
        };
        splashTread.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}
