package safecar.yiye.apackage.com.safecar.MVP.Home.View;

import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;

/**
 * Name: HomeMoreSingleCarActivityView
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-25 10:41
 */
public interface HomeMoreSingleCarActivityView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //加载新数据
    void newDatas(HomeSingleCarTodayBean data);
    //显示加载失败
    void showLoadFailMsg();
    //显示无数据
    void showNoData();
}
