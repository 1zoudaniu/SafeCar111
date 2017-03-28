package safecar.yiye.apackage.com.safecar.Boss.Data.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;

/**
 * Name: RetrofitUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:34
 * 封装一个retrofit集成0kHttp3的抽象基类
 */
public class RetrofitUtils {
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit(){

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径http://180.97.232.57:9090/iChucheBox/a/device/device/interface
                    .baseUrl(Constant.URL_IPPORT)
//                    .baseUrl("http://180.97.232.57:9090/iChucheBox/a/device/device/interface/")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofit;
    }
}