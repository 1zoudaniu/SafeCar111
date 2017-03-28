package safecar.yiye.apackage.com.safecar.Boss.Util;

/**
 * Name: CommonUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2017-01-04 14:22
 */
public class CommonUtils {
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 5000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
