package safecar.yiye.apackage.com.safecar.MVP.Home.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.Bugly;

import butterknife.BindView;
import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.ZhangPhilListView;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;
import safecar.yiye.apackage.com.safecar.MVP.SplashActivity;
import safecar.yiye.apackage.com.safecar.Permission.TestSplashActivity;
import safecar.yiye.apackage.com.safecar.R;

public class TestActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_splash);

        //7.0以上版本  设置动态权限
        if (Build.VERSION.SDK_INT >= 24) {
            Toast.makeText(TestActivity.this,"1！",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TestActivity.this, SplashActivity.class));
            finish();
        } else {
            Toast.makeText(TestActivity.this,"1！",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TestActivity.this, SplashActivity.class));
            finish();
        }
    }


}

