package safecar.yiye.apackage.com.safecar.Boss.Data.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;

/**
 * Name: MyRetrofit
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-12-06 16:43
 */
public class MyRetrofit {

    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit() {

        if (null == mRetrofit) {
            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径http://180.97.232.57:9090/iChucheBox/a/device/device/interface
                    .baseUrl(Constant.URL_IPPORT+"/")
//                    .baseUrl("http://180.97.232.57:9090/iChucheBox/a/device/device/interface/")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
