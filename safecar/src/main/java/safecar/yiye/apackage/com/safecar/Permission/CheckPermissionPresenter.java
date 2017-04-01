package safecar.yiye.apackage.com.safecar.Permission;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import org.yapp.core.presenter.BaseActivityPresenter;
import org.yapp.utils.permission.PermissionUtil;
import org.yapp.core.ui.activity.BaseAppCompatActivity;

import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;

/**
 * Created by Administrator on 2017/3/31/031.
 */

public class CheckPermissionPresenter extends BaseActivityPresenter<BaseAppCompatActivity,MyApplication> implements CheckPermissionView {

    @Override
    public void onBuild(Context context) {
        super.onBuild(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onInit() {
        PermissionUtil
                .with(getContext())
                .permissions(
                        PermissionUtil.PERMISSIONS_GROUP_LOCATION ,//定位授权
                        PermissionUtil.PERMISSIONS_GROUP_PHONE, //获取电话授权
                        PermissionUtil.PERMISSIONS_GROUP_STORAGE //存储授权
                ).request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionUtil.onRequestPermissionsResult(getContext(), requestCode, permissions);
    }

    @Override
    public void onRequestPermissionSuccess() {
        final String[] PHONE_PROJECTION = new String[]{"display_name", "data1"};
        ContentResolver resolver = getContext().getContentResolver();
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONE_PROJECTION, null, null, null);
        if (phoneCursor != null && phoneCursor.moveToFirst()) {
            do {
                String name = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            } while (phoneCursor.moveToNext());
            phoneCursor.close();
        }
        showMsg("已授权");
    }

    @Override
    public void onRequestPermissionError() {
        showMsg("无法定位或信息无法加载,请在权限管理中给应用授权");
    }
}
