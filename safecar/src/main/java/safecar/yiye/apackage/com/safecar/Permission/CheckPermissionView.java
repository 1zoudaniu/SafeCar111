package safecar.yiye.apackage.com.safecar.Permission;

/**
 * Created by Administrator on 2017/3/31/031.
 */

public interface CheckPermissionView {

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

    void onRequestPermissionSuccess();

    void onRequestPermissionError();
}
