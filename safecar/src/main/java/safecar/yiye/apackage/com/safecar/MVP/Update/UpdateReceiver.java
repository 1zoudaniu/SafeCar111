package safecar.yiye.apackage.com.safecar.MVP.Update;

/**
 * Created by Administrator on 2016-08-10.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;


import java.io.File;

import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.R;


/**
 * 版本更新升级 广播接受者
 *
 */
public class UpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
//    private AlertDialog.Builder mDialog;
//    public static final String UPDATE_ACTION = "LePoint_auto_update";
//    private boolean isShowDialog;
//    UpdateBean model;
//    int localVersion;
//    String versionName;
//
//    public UpdateReceiver() {
//    }
//
//    public UpdateReceiver(boolean isShowDialog) {
//        super();
//        this.isShowDialog = isShowDialog;
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        SharedPreferences sp = context.getSharedPreferences("update",context.MODE_PRIVATE);
//        String updateInfo = sp.getString("up","");
//        Log.e("ceshi",updateInfo);
//        try {
//            model = JSONObject.parseObject(updateInfo,UpdateBean.class);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        try {
//
//            /**
//             * 获取到当前的本地版本
//             */
//            localVersion = MyApplication
//                    .getInstance()
//                    //包管理独享
//                    .getPackageManager()
//                    //包信息
//                    .getPackageInfo(
//                            MyApplication.getInstance()
//                                    .getPackageName(), 0).versionCode;
//            /**
//             * 获取到当前的版本名字
//             */
//            versionName = MyApplication
//                    .getInstance()
//                    .getPackageManager()
//                    .getPackageInfo(
//                            MyApplication.getInstance()
//                                    .getPackageName(), 0).versionName;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        /*************此处解决升级广播的内存泄漏的bug***************************/
//        context.unregisterReceiver(this);
//        /*************此处解决升级广播的内存泄漏的bug***************************/
//
//        //app名字
////        UpdateInformation.appname = model.getAppName();
//        //服务器版本
////        UpdateInformation.serverVersion = Integer.parseInt(model
////                .getVersionNo());
////        //服务器标志
////        UpdateInformation.serverFlag = model.getVersion();
////        //强制升级
////        UpdateInformation.lastForce = model.isForceUpdate();
////        //升级地址
////        UpdateInformation.setUpdateurl(model.getDownloadUrl());
////        //升级信息
////        UpdateInformation.setUpgradeinfo(model.getNotice());
//
//        //检查版本
//        if(model!=null)
//        checkVersion(context);
//    }
//
//    /**
//     * 检查版本更新
//     *
//     * @param context
//     */
//    public void checkVersion(Context context) {
//        SharedPreferences sp = context.getSharedPreferences("update",context.MODE_PRIVATE);
//        if (localVersion< model.getVersionNo()) {
//            // 需要进行更新
//            sp.edit().putInt(
//                    //有新版本
//                    "version", 1);
//            sp.edit().commit();
//            //更新
//            update(context);
//        } else {
//            sp.edit().putInt(
//                    "version", 0);
//            sp.edit().commit();
//            if (isShowDialog) {
//                //没有最新版本，不用升级
//                noNewVersion(context);
//            }
//            clearUpateFile(context);
//        }
//    }
//
//    /**
//     * 进行升级
//     *
//     * @param context
//     */
//    private void update(Context context) {
//            // 官方推荐升级
//            if (model.isForceUpdate()) {
//                //强制升级
//                forceUpdate(context);
//            } else {
//                //正常升级
//                normalUpdate(context);
//            }
//
//    }
//
//    /**
//     * 没有新版本
//     * @param context
//     */
//    private void noNewVersion(final Context context) {
//        mDialog = new AlertDialog.Builder(context);
//        mDialog.setTitle("版本更新");
//        mDialog.setMessage("当前为最新版本");
//        mDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        }).create().show();
//    }
//
//    /**
//     * 强制升级 ，如果不点击确定升级，直接退出应用
//     *
//     * @param context
//     */
//    private void forceUpdate(final Context context) {
//        mDialog = new AlertDialog.Builder(context);
//        mDialog.setTitle("版本更新");
//        mDialog.setMessage(model.getNotice());
//
//        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent mIntent = new Intent(context, UpdateService.class);
//                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mIntent.putExtra("appname", model.getAppName());
//                mIntent.putExtra("appurl", model.getDownloadUrl());
//                //启动服务
//                context.startService(mIntent);
//            }
//        }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // 直接退出应用
//                //ManagerActivity.getInstance().finishActivity();
//                System.exit(0);
//            }
//        }).setCancelable(false).create().show();
//    }
//
//    /**
//     * 正常升级，用户可以选择是否取消升级
//     *
//     * @param context
//     */
//    private void normalUpdate(final Context context) {
//        mDialog = new AlertDialog.Builder(context);
//        mDialog.setTitle("版本更新");
//        mDialog.setMessage(model.getNotice());
//        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent mIntent = new Intent(context, UpdateService.class);
//                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //传递数据
//                mIntent.putExtra("appname", model.getAppName());
//                mIntent.putExtra("appurl", model.getDownloadUrl());
//                context.startService(mIntent);
//            }
//        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        }).create().show();
//    }
//
//    /**
//     * 清理升级文件
//     *
//     * @param context
//     */
//    private void clearUpateFile(final Context context) {
//        File updateDir;
//        File updateFile;
//        if (Environment.MEDIA_MOUNTED.equals(Environment
//                .getExternalStorageState())) {
//            updateDir = new File(Environment.getExternalStorageDirectory(),
//                    "LePoint");
//        } else {
//            updateDir = context.getFilesDir();
//        }
//        updateFile = new File(updateDir.getPath(), context.getResources()
//                .getString(R.string.app_name) + ".apk");
//        if (updateFile.exists()) {
//            Log.d("update", "升级包存在，删除升级包");
//            updateFile.delete();
//        } else {
//            Log.d("update", "升级包不存在，不用删除升级包");
//        }
//    }
}
