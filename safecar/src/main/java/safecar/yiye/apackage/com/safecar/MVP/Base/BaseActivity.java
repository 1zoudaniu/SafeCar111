package safecar.yiye.apackage.com.safecar.MVP.Base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;

/**
 * Name: BaseActivity
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 12:51
 */
public abstract class BaseActivity  extends AppCompatActivity implements View.OnClickListener, SpringView.OnFreshListener {
    protected Context mContext;
    private ConnectivityManager manager;

    public static final int REQUEST_CODE_SETTING = 101;

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 101) {
                // TODO 相应代码。
                Toast.makeText(MyApplication.getContext(), "获取权限成功", Toast.LENGTH_SHORT).show();

                initdata();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            Toast.makeText(MyApplication.getContext(), "获取权限失败", Toast.LENGTH_SHORT).show();
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(BaseActivity.this, deniedPermissions)) {
                AndPermission.defaultSettingDialog(BaseActivity.this, REQUEST_CODE_SETTING)
                        .setTitle("警告")
                        .setMessage("出于用户考虑，地图展示需要定位权限，否则地图无法展示。由于您多次拒绝，请手动开启权限。")
                        .setPositiveButton("立刻去开启")
                        .setNegativeButton("还是不给", null)
                        .show();

                // 更多自定dialog，请看上面。
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("时间测试BaseActivity", "onCreate");
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏

        mContext = getActivityContext();



        initView();

        ButterKnife.bind(this);

        initdata();

        MyApplication.getInstance().addActivity(this);


        Bugly.init(mContext, "7f96a0be11", false);
    }

    /**
     * 初始activity方法
     */
    private void initView() {
        loadViewLayout();
    }
    private void initdata(){
        findViewById();
        setListener();
        processLogic();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//		StatService.onPause(mContext);

    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
//		StatService.onResume(mContext);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        MyApplication.getInstance().finishActivity(this);
    }
    /**
     * 加载页面layout
     */
    protected abstract void loadViewLayout();

    /**
     * 加载页面元素
     */
    protected abstract void findViewById();

    /**
     * 设置各种事件的监听器
     */
    protected abstract void setListener();

    /**
     * 业务逻辑处理，主要与后端交互
     */
    protected abstract void processLogic();


    /**
     * Activity.this
     */
    protected abstract Context getActivityContext();

    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * 获取屏幕宽度(px)
     *
     * @param
     * @return
     */
    public int getMobileWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }

    /**
     * 获取屏幕高度(px)
     *
     * @param
     * @return
     */
    public int getMobileHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        return height;
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    /*
    返回版本api
     */
    public boolean SdkApi(){
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT){
            return true;
        } else{
            return false;
        }
    }
    public boolean checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }
    public String getVersionName()
    {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
            String version = packInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //TODO 注意返回的版本名称
        return "1.0";
    }
}
