package safecar.yiye.apackage.com.safecar.Boss.Data.HttpData;

import java.io.File;
import java.util.List;

import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import safecar.yiye.apackage.com.safecar.Boss.Data.API.CacheProviders;
import safecar.yiye.apackage.com.safecar.Boss.Data.API.HomeHomeCarService;
import safecar.yiye.apackage.com.safecar.Boss.Data.Retrofit.ApiException;
import safecar.yiye.apackage.com.safecar.Boss.Data.Retrofit.RetrofitUtils;
import safecar.yiye.apackage.com.safecar.MVP.Entity.DetailNowBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HistoryCarScoreBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HttpResult;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HttpResultHome;
import safecar.yiye.apackage.com.safecar.MVP.Entity.LoginBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.TestBean;
import safecar.yiye.apackage.com.safecar.Boss.Util.FileUtil;

/**
 * Name: HttpData
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:40
 * 所有的请求数据的方法集中地
 * 根据MovieService的定义编写合适的方法
 * 其中observable是获取API数据
 * observableCahce获取缓存数据
 * new EvictDynamicKey(false) false使用缓存  true 加载数据不使用缓存
 */
public class HttpData extends RetrofitUtils {
//    http://180.97.232.57:9090/iChucheBox/a /device/device/interface/getRealTimeCarInfoByOri?tel=321
    private static File cacheDirectory = FileUtil.getcacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder()
            .persistence(cacheDirectory)
            .using(CacheProviders.class);
    protected static final HomeHomeCarService service = getRetrofit().create(HomeHomeCarService.class);

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //获取首页车辆信息 Observer<List<HomeHomeCarBean>>
    public void getHomeCarInfo(String tel,String token, Observer<HomeHomeCarBean> observer) {
        Observable observable = service.getCarListService(tel,token);
//        Observable observableCache = providers.getCarListCacheProviders(observable, new DynamicKey("getCarList&" + tel), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<HomeHomeCarBean>>());
//        setSubscribe(observableCache, observer);
        setSubscribe(observable, observer);
    }

    //获取首页点击后进去的 车辆信息   车辆今天的扣分情况
    public void getHomeCarTodayInfo(String code,String datestr,String tel,String token,Observer<HomeSingleCarTodayBean> observer) {
        Observable observable = service.getCarTodayInfoListService(code,datestr,tel,token);
//        Observable observableCache = providers.getCarTodayInfoListCacheProviders(observable, new DynamicKey("getCarList&" + code+datestr), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<HomeSingleCarTodayBean>());
//        setSubscribe(observableCache, observer);
        setSubscribe(observable, observer);
    }


    //获取明细页面第一个的车辆
    public void getHomeDetailNowInfo(String tel,String token,Observer<DetailNowBean> observer) {
        Observable observable = service.getHomeDetailNowInfoService(tel,token);
//        Observable observableCache = providers.getHomeDetailNowInfoCacheProviders(observable, new DynamicKey("getHomeDetailNaoCarList&" + tel), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<DetailNowBean>>());
//        setSubscribe(observableCache, observer);
        setSubscribe(observable, observer);
    }

    //获取首页点击后进去的 车辆信息   车辆今天的扣分情况
    public void getTest(String code,String datestr,Observer<List<TestBean>> observer) {
        Observable observable = service.getTestService(code,datestr).map(new HttpResultFuncHome<List<TestBean>>());
        setSubscribe(observable, observer);
    }

    //登陆
    public void getLogin(String tel,Observer<LoginBean> observer) {
        Observable observable = service.getLoginService(tel);
        setSubscribe(observable, observer);
    }

    //获取七天一个月的历史分数
    public void getHistoryScoreInfo(String count,String tel,String token,String datestr,Observer<HistoryCarScoreBean> observer) {
        Observable observable = service.getHistoryScoreInfoListService(count,tel,token,datestr);
//        Observable observableCache = providers.getCarTodayInfoListCacheProviders(observable, new DynamicKey("getCarList&" + code+datestr), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<HomeSingleCarTodayBean>());
//        setSubscribe(observableCache, observer);
        setSubscribe(observable, observer);
    }

    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 1) {
                throw new ApiException(httpResult);
            }
            return httpResult.getData();
        }
    }

    private  class HttpResultFuncHome<T> implements Func1<HttpResultHome<T>, T> {

        @Override
        public T call(HttpResultHome<T> httpResult) {
            return httpResult.getRes_data();
        }
    }

    /**
     * 用来统一处理RxCacha的结果
     */
    private class HttpResultFuncCcche<T> implements Func1<Reply<T>, T> {

        @Override
        public T call(Reply<T> httpResult) {
            return httpResult.getData();
        }
    }
}
