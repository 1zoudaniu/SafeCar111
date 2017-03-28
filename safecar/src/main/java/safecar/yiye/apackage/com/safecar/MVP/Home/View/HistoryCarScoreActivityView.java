package safecar.yiye.apackage.com.safecar.MVP.Home.View;


import safecar.yiye.apackage.com.safecar.MVP.Entity.HistoryCarScoreBean;

/**
 * Created by Administrator on 2017/3/2/002.
 */

public interface HistoryCarScoreActivityView {
    //显示加载页
    void showProgress();

    //关闭加载页
    void hideProgress();

    //加载新数据
    void newDatas(HistoryCarScoreBean data);

    //显示加载失败
    void showLoadFailMsg();

    //显示无数据
    void showNoData();
}
