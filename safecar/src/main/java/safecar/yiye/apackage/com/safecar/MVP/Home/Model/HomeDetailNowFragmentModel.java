package safecar.yiye.apackage.com.safecar.MVP.Home.Model;

import java.util.List;

import rx.Observer;
import safecar.yiye.apackage.com.safecar.Boss.Data.HttpData.HttpData;
import safecar.yiye.apackage.com.safecar.MVP.Entity.DetailNowBean;

/**
 * Name: HomeDetailNowFragmentModel
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-25 14:53
 */
public class HomeDetailNowFragmentModel {
    public void loadData(String tel,String token,final OnLoadDataListListener listener){
        HttpData.getInstance().getHomeDetailNowInfo(tel,token,new Observer<DetailNowBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(DetailNowBean homeDetailNowBeen) {
                listener.onSuccess(homeDetailNowBeen);
            }
        });
    }
}
