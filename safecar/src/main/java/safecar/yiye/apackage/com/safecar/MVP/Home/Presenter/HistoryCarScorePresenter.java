package safecar.yiye.apackage.com.safecar.MVP.Home.Presenter;

import android.util.Log;

import safecar.yiye.apackage.com.safecar.MVP.Entity.HistoryCarScoreBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.HistoryCarScoreActivityModel;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.HomeSingleCarTodayActivityModel;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.OnLoadDataListListener;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HistoryCarScoreActivityView;

/**
 * Created by Administrator on 2017/3/2/002.
 */

public class HistoryCarScorePresenter implements OnLoadDataListListener<HistoryCarScoreBean> {

    private HistoryCarScoreActivityView mView;
    private HistoryCarScoreActivityModel mModel;

    @Override
    public void onSuccess(HistoryCarScoreBean data) {

        if (data.getRes_code().equals("-2")) {
            mView.newDatas(data);
            mView.hideProgress();
        } else {

            if (data.getRes_score() == null) {
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

    public HistoryCarScorePresenter(HistoryCarScoreActivityView mView) {
        this.mView = mView;
        this.mModel = new HistoryCarScoreActivityModel();
        mView.showProgress();
    }

    public void LoadData(String count, String tel, String token, String datestr) {
        mModel.loadData(count, tel, token, datestr, this);
    }


}
