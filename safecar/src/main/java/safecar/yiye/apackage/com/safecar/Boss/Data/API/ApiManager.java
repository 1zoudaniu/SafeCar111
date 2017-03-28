package safecar.yiye.apackage.com.safecar.Boss.Data.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;

/**
 * Name: ApiManager
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-12-06 10:08
 */
public class ApiManager {

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(Constant.URL_IPPORT+"/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final ApiManagerService apiManager = sRetrofit.create(ApiManagerService.class);

}
