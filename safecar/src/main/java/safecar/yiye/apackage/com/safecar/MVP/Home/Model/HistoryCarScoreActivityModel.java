package safecar.yiye.apackage.com.safecar.MVP.Home.Model;

import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observer;
import safecar.yiye.apackage.com.safecar.Boss.Data.HttpData.HttpData;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HistoryCarScoreBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;

/**
 * Created by Administrator on 2017/3/2/002.
 */

public class HistoryCarScoreActivityModel {
    public void loadData(String count, String tel, String token, String datestr, final OnLoadDataListListener listener) {

        HttpData.getInstance().getHistoryScoreInfo(count,tel,token, datestr, new Observer<HistoryCarScoreBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(HistoryCarScoreBean homeHomeCarBeen) {

                listener.onSuccess(homeHomeCarBeen);

            }

        });
    }
}
