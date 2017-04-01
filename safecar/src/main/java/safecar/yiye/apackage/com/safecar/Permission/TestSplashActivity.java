package safecar.yiye.apackage.com.safecar.Permission;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import org.yapp.core.ui.activity.BaseAppCompatActivity;
import org.yapp.core.ui.inject.annotation.ContentInject;
import org.yapp.utils.permission.PermissionError;
import org.yapp.utils.permission.PermissionSuccess;

import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;
import safecar.yiye.apackage.com.safecar.MVP.SplashActivity;
import safecar.yiye.apackage.com.safecar.R;


@ContentInject(value = R.layout.activity_test_splash,
        presenter = CheckPermissionPresenter.class)
public class TestSplashActivity extends BaseAppCompatActivity<CheckPermissionPresenter>

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_splash);
//        //取消状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//
//
//                    startActivity(new Intent(TestSplashActivity.this, LoginActivity.class));
//                    finish();
//
//    }

    {
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

        @PermissionSuccess
        public void onRequestPermissionSuccess(){
        mPresenter.onRequestPermissionSuccess();
            startActivity(new Intent(TestSplashActivity.this, SplashActivity.class));
                    finish();
    }

        @PermissionError
        public void onRequestPermissionError(){
        mPresenter.onRequestPermissionError();
        showMissingPermissionDialog();


    }
        /**
         * 显示提示信息
         *
         * @since 2.5.0
         *
         */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限。");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                        Toast.makeText(TestSplashActivity.this,"授权后请重启应用！",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);

    }
}
