package safecar.yiye.apackage.com.safecar.MVP.Home.View;


import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Entity.DetailNowBean;

/**
 * Name: HomeHomeFragmentView
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 14:48
 */
public interface HomeDetailNowFragmentView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //加载新数据
    void newDatas(DetailNowBean data);
    //显示加载失败
    void showLoadFailMsg();
    //数据为空
    void showEmptyMsg();
}
