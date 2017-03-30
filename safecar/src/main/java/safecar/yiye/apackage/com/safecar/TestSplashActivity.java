package safecar.yiye.apackage.com.safecar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import safecar.yiye.apackage.com.safecar.MVP.Home.Activity.TestActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;

public class TestSplashActivity extends AppCompatActivity {

//    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_splash);
//        imageView = (ImageView) findViewById(R.id.testSplash);

        SharedPreferences preferences=getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        String token=preferences.getString("token", "");

        if (!TextUtils.isEmpty(token)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(TestSplashActivity.this, HomeTabActivity.class));
//                    MyApplication.getInstance().exit();
                    finish();
                }
            }, 2000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(TestSplashActivity.this, LoginActivity.class));
//                    MyApplication.getInstance().exit();
                    finish();
                }
            }, 2000);
        }
    }
}
