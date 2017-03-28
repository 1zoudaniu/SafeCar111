package safecar.yiye.apackage.com.safecar.MVP.Home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.Boss.Dialog.MyDialogHint;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.Fragment.FragmentController;
import safecar.yiye.apackage.com.safecar.R;

public class HomeTabActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.hometab_context)
    FrameLayout mHometabContext;
    @BindView(R.id.rb_home1)
    RadioButton mRbHome1;
    @BindView(R.id.rb_home2)
    RadioButton mRbHome2;
    @BindView(R.id.rb_home3)
    RadioButton mRbHome3;
    @BindView(R.id.hometab_radio)
    RadioGroup mHometabRadio;
    private FragmentController controller;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);
        controller = FragmentController.getInstance(this, R.id.hometab_context);
        controller.showFragment(0);
    }

    @Override
    protected void findViewById() {
        mHometabRadio = (RadioGroup) findViewById(R.id.hometab_radio);
    }

    @Override
    protected void setListener() {
        mHometabRadio.setOnCheckedChangeListener(this);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
//        //4.反注册广播接收器
//        DownloadApk.unregisterBroadcast(this);
        super.onDestroy();
        FragmentController.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home1:
                controller.showFragment(0);
                break;
            case R.id.rb_home2:
                controller.showFragment(1);
                break;
            case R.id.rb_home3:
                controller.showFragment(2);
                break;

        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (((keyCode == KeyEvent.KEYCODE_BACK) ||
                (keyCode == KeyEvent.KEYCODE_HOME))
                && event.getRepeatCount() == 0) {
            new MyDialogHint(HomeTabActivity.this, R.style.MyDialog1).show();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

//        //1.注册下载广播接收器
//        DownloadApk.registerBroadcast(this);
//        //2.删除已存在的Apk
//        DownloadApk.removeFile(this);
//
//        //3.如果手机已经启动下载程序，执行downloadApk。否则跳转到设置界面
//        if (DownLoadUtils.getInstance(getApplicationContext()).canDownload()) {
//            DownloadApk.downloadApk(getApplicationContext(), "http://www.huiqu.co/public/download/apk/huiqu.apk", "SafeCar更新", "SafeCar");
//        } else {
//            DownLoadUtils.getInstance(getApplicationContext()).skipToDownloadManager();
//        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }
}
