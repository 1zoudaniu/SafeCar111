package safecar.yiye.apackage.com.safecar.MVP.Home.View;

import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;

/**
 * Name: HomeHomeFragmentView
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 14:48
 */
public interface HomeHomeFragmentView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //加载新数据
    void newDatas(HomeHomeCarBean data);
    //显示加载失败
    void showLoadFailMsg();
}
