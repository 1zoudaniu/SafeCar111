package safecar.yiye.apackage.com.safecar.Boss.MyApplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.xiaochao.lcrapiddeveloplibrary.Exception.core.Recovery;


import org.yapp.core.Application;
import org.yapp.utils.Callback;
import org.yapp.utils.Toast;
import org.yapp.y;

import java.io.File;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import safecar.yiye.apackage.com.safecar.MVP.Home.Activity.TestActivity;
import safecar.yiye.apackage.com.safecar.MVP.SplashActivity;
import safecar.yiye.apackage.com.safecar.MVP.Update.config.SystemParams;

/**
 * Name: MyApplication
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 12:47
 *  用于初始化各种数据以及服务
 */
public class MyApplication extends Application {
    private static Context context;
    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<Activity>();
    //记录需要一次性关闭的页面
    private List<Activity> activitys = new ArrayList<Activity>();


    private static SharedPreferences sharedPrederences = null;
    //在Application初始化
    public static void init(Context context) {
        sharedPrederences = context.getSharedPreferences("safecar", Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        SystemParams.init(this);
        instance = this;
        this.context=getApplicationContext();
//        //异常友好管理初始化
//        Recovery.getInstance()
//                .debug(true)
//                .recoverInBackground(false)
//                .recoverStack(true)
//                .mainPage(SplashActivity.class)
////                .skip(H5PayActivity.class)  如果应用集成支付宝支付 记得加上这句代码  没时间解释了  快上车  老司机发车了
//                .init(this);


//        //文件下载管理初始化
//        FileDownloader.init(getApplicationContext(),
//                new FileDownloadHelper.OkHttpClientCustomMaker() { // is not has to provide.
//                    @Override
//                    public OkHttpClient customMake() {
//                        // just for OkHttpClient customize.
//                        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                        // you can set the connection timeout.
//                        builder.connectTimeout(15_000, TimeUnit.MILLISECONDS);
//                        // you can set the HTTP proxy.
//                        builder.proxy(Proxy.NO_PROXY);
//                        // etc.
//                        return builder.build();
//                    }
//                });
    }
    /**
     * 应用实例
     **/
    private static MyApplication instance;

    /**
     * 获得实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    public static Context getContext(){
        return context;
    }
    /*
    *给临时Activitys
    * 添加activity
    * */
    public void addTemActivity(Activity activity) {
        activitys.add(activity);
    }

    public void finishTemActivity(Activity activity) {
        if (activity != null) {
            this.activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    * 退出指定的Activity
    * */
    public void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                if (activitys.contains(activity)) {
                    Log.d("ddd","dd");
                } else {
                    activity.finish();
                }
            }
        }
//        System.exit(0);
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit(AppCompatActivity a) {
        for (Activity activity : activities) {
            List<Activity> activities = this.activities;
            if (activity != null) {
                if (activity == a) {
                    Log.d("ddd", "ddd");
                } else {
                    activity.finish();
                }

            }
        }
        System.exit(0);
    }


    /**
     * 新加进来的
     */

    /**get**/
    public int getInt(String key){
        return sharedPrederences.getInt(key, 0);
    }

    public int getInt(String key, int defValue){
        return sharedPrederences.getInt(key, defValue);
    }

    public float getFloat(String key){
        return sharedPrederences.getFloat(key, 0);
    }

    public float getFloat(String key, float defValue) {
        return sharedPrederences.getFloat(key, defValue);
    }

    public long getLong(String key){
        return sharedPrederences.getLong(key, 0);
    }

    public long getLong(String key, long defValue) {
        return sharedPrederences.getLong(key, defValue);
    }

    public String getString(String key){
        return sharedPrederences.getString(key, null);
    }

    public String getString(String key, String defValue) {
        return sharedPrederences.getString(key, defValue);
    }

    public boolean getBoolean(String key){
        return sharedPrederences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPrederences.getBoolean(key, defValue);
    }

    /**set**/
    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setSetString(String key, Set<String> values) {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.putStringSet(key, values);
        editor.commit();
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPrederences.edit();
        editor.clear().commit();
    }

    @Override
    protected void init() {
        y.setDebug(true);
        org.yapp.utils.Log.d("App启动");
        // 异常处理初始化
        y.ex().init(this, new Callback.HandlerCallback<Throwable>() {
            @Override
            public void onHandle(Throwable ex) {
                File log = null;
                try {
//                    int stackTraceSize = ex.getStackTrace().length;
//                    String errorMsg = ex.getLocalizedMessage() + "-&&-";
//                    for (int i = 0; i < stackTraceSize; i++) {
//                        StackTraceElement stack = ex.getStackTrace()[i];
//                        errorMsg += stack.toString() + "\n";
//                        if (errorMsg.length() >= 254) {
//                            errorMsg += (stackTraceSize - i)
//                                    + " more...\n";
//                            break;
//                        }
//                    }
//                    log = new File(FileUtil.getCacheDir(AppConsts._LOG),
//                            "error.log");
//                    IOUtil.writeStr(new FileOutputStream(log), errorMsg);
                } catch (Exception e) {
                    org.yapp.utils.Log.e(e.getMessage(), e);
                } finally {
//                    log = null;
                    if (y.ex().getStatus() == 0) {
                        Intent intent = new Intent(MyApplication.this, TestActivity.class);
                        PendingIntent restartIntent = PendingIntent
                                .getActivity(MyApplication.this, 0, intent,
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                        AlarmManager mgr = (AlarmManager) MyApplication.this
                                .getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC,
                                System.currentTimeMillis() + 3000,
                                restartIntent); // 3秒钟后重启应用
                    }
                    // 杀死线程
                    android.os.Process.killProcess(android.os.Process
                            .myPid());
                }
            }

        });
        if (y.ex().status() != 0) {
            Toast.showMessageForCenterLong("无法定位或信息无法加载,请在权限管理中给应用授权。请点击\"设置\"-\"权限\"-打开所需权限");
        }
    }
}
