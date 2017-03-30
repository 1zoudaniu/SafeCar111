package safecar.yiye.apackage.com.safecar.MVP;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.R;
import safecar.yiye.apackage.com.safecar.Boss.Util.SPUtils;

/*
* 欢迎页面
* 用途进入白屏或者黑屏
* 加载必须配置数据
* */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.rl_splash)
    ImageView mRlSplash;
    private String login_safebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("测试", "oncreate");
        ButterKnife.bind(this);
        // 显示透明度动画
        startAnimation();

    }

    @Override
    protected void loadViewLayout() {
        Log.d("测试", "loadViewLayout");
        setContentView(R.layout.activity_splash);

        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d("测试", "loadViewLayout3");
    }

    /**
     * 显示透明度动画 从完全透明到完全不透明(显示)
     */
    private void startAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(2000);
        mRlSplash.startAnimation(animation);
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        Log.d("测试", "processLogic");
        SharedPreferences preferences=getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        String token=preferences.getString("token", "");

        if (!TextUtils.isEmpty(token)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, HomeTabActivity.class));
//                    MyApplication.getInstance().exit();
                    finish();
                }
            }, 2000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                    MyApplication.getInstance().exit();
                    finish();
                }
            }, 2000);
        }
    }

    @Override
    protected Context getActivityContext() {
        return SplashActivity.this;
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }

}
