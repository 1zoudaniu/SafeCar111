package safecar.yiye.apackage.com.safecar.Boss.Util;

import android.util.Log;


import java.io.File;

import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;

/**
 * 文件工具类
 *
 */
public class FileUtil {
	/**
	 * @return  创建缓存目录
	 */
	public static File getcacheDirectory()
	{
		File file = new File(MyApplication.getContext().getExternalCacheDir(), "MyCache_ichuche");
		if(!file.exists())
		{
			boolean b = file.mkdirs();
			Log.e("file", "文件不存在  创建文件    "+b);
		}else{
			Log.e("file", "文件存在");
		}
		return file;
	}
}
