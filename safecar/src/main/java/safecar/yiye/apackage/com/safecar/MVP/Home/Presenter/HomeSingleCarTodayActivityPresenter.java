package safecar.yiye.apackage.com.safecar.MVP.Home.Presenter;

import android.util.Log;

import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.HomeSingleCarTodayActivityModel;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.OnLoadDataListListener;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HomeSingleCarTodayActivityView;

/**
 * Name: HomeSingleCarTodayActivityPresenter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-24 16:00
 */
public class HomeSingleCarTodayActivityPresenter implements OnLoadDataListListener<HomeSingleCarTodayBean> {
    private HomeSingleCarTodayActivityView mView;
    private HomeSingleCarTodayActivityModel mModel;

    public HomeSingleCarTodayActivityPresenter(HomeSingleCarTodayActivityView mView) {
        this.mView = mView;
        Log.d("时间测试presenter", "网络请求开始");
        this.mModel = new HomeSingleCarTodayActivityModel();
        mView.showProgress();
    }

    public void LoadData(String code, String datestr, String tel, String token) {
        mModel.loadData(code, datestr, tel, token, this);
    }

    @Override
    public void onSuccess(HomeSingleCarTodayBean data) {

        if (data.getRes_code().equals("-2")) {
            mView.newDatas(data);
            mView.hideProgress();
        } else {

            if (data.getRes_fenbu().size() == 0 && data.getRes_expection().size() == 0) {
                mView.showNoData();
            } else {
                mView.newDatas(data);
                mView.hideProgress();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {
        mView.showLoadFailMsg();
    }
}
