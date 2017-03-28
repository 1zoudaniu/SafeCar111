package safecar.yiye.apackage.com.safecar.MVP.Home.Presenter;

import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Entity.DetailNowBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.HomeDetailNowFragmentModel;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.HomeSingleCarTodayActivityModel;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.OnLoadDataListListener;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HomeDetailNowFragmentView;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HomeSingleCarTodayActivityView;

/**
 * Name: HomeDetailNowFragmentPresenter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-25 14:49
 */
public class HomeDetailNowFragmentPresenter  implements OnLoadDataListListener<DetailNowBean> {
    private HomeDetailNowFragmentView mView;
    private HomeDetailNowFragmentModel mModel;

    public HomeDetailNowFragmentPresenter(HomeDetailNowFragmentView mView) {
        this.mView = mView;
        this.mModel = new HomeDetailNowFragmentModel();
        mView.showProgress();
    }

    public void LoadData(String tel,String token) {
        mModel.loadData(tel,token,this);
    }

    @Override
    public void onSuccess(DetailNowBean data) {
        if(data.getRes_data().size()==0){
            mView.showEmptyMsg();
        }else {
            mView.newDatas(data);
            mView.hideProgress();
        }
    }
    @Override
    public void onFailure(Throwable e) {
        mView.showLoadFailMsg();
    }
}
