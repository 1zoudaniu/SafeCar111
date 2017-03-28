package safecar.yiye.apackage.com.safecar.Boss.Data.API;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import safecar.yiye.apackage.com.safecar.MVP.Entity.DetailNowBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HistoryCarScoreBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HttpResultHome;
import safecar.yiye.apackage.com.safecar.MVP.Entity.LoginBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.TestBean;

/**
 * Name: HomeHomeCarService
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:46
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface HomeHomeCarService {
    //获取首页车辆信息列表
    @GET("/iChucheBox/a/device/device/interface/getRealTimeCarInfoByOri")
    Observable<HomeHomeCarBean> getCarListService(@Query("tel")String tel,@Query("token")String token);

    //获取首页车辆今天的扣分情况  1.2页面
    @GET("/iChucheBox/a/device/device/interface/getCarinfoByCode")
    Observable<HomeSingleCarTodayBean> getCarTodayInfoListService(@Query("code")String code, @Query("datestr")String datestr
    ,@Query("tel")String tel, @Query("token")String token);


    //获取详情页面车辆当前的信息 2。1页面
    @GET("/iChucheBox/a/device/device/interface/getRealTimeCarInfoByOri")
    Observable<DetailNowBean> getHomeDetailNowInfoService(@Query("tel")String tel,@Query("token")String token);


    //测试
    @GET("/iChucheBox/a/device/device/interface/getCarinfoByCode")
    Observable<HttpResultHome<List<TestBean>>> getTestService(@Query("code")String code, @Query("datestr")String datestr);



    //登陆
    @GET("/iChucheBox/a/device/device/interface/applogin")
    Observable<LoginBean> getLoginService(@Query("tel")String tel);

    //获取七天一个月的历史分数
    @GET("/iChucheBox/a/device/device/interface/getSumCodeByDate")
    Observable<HistoryCarScoreBean> getHistoryScoreInfoListService(@Query("count")String count,
    @Query("tel")String tel,@Query("token")String token,@Query("datestr")String datestr);


}
