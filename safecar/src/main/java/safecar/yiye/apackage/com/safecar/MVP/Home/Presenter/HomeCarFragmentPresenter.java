package safecar.yiye.apackage.com.safecar.MVP.Home.Presenter;

import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.HomeHomeCarModel;
import safecar.yiye.apackage.com.safecar.MVP.Home.Model.OnLoadDataListListener;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HomeHomeFragmentView;

/**
 * Name: HomeCarFragmentPresenter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:25
 */
public class HomeCarFragmentPresenter implements OnLoadDataListListener<HomeHomeCarBean> {
    private HomeHomeFragmentView mView;
    private HomeHomeCarModel mModel;

    public HomeCarFragmentPresenter(HomeHomeFragmentView mView) {
        this.mView = mView;
        this.mModel=new HomeHomeCarModel();
        mView.showProgress();
    }

    public void LoadData(String tel,String token){
        mModel.loadData(tel,token,this);
    }

    @Override
    public void onSuccess(HomeHomeCarBean data) {
        mView.newDatas(data);
        mView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        mView.showLoadFailMsg();
    }
}
