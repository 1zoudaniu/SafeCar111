package safecar.yiye.apackage.com.safecar.MVP.Home.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observer;
import safecar.yiye.apackage.com.safecar.Boss.Data.HttpData.HttpData;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;

/**
 * Name: HomeHomeCarModel
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:32
 * 首页的
 */
public class HomeHomeCarModel {
    public void loadData(String tel, String token, final OnLoadDataListListener listener) {
        HttpData.getInstance().getHomeCarInfo(tel, token, new Observer<HomeHomeCarBean>() {
            @Override
            public void onCompleted() {
                Log.d("ddddd", "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
                Log.d("HomeHomeCarModel", "异常是" + e);
            }

            @Override
            public void onNext(HomeHomeCarBean homeHomeCarBeen) {

                List<HomeHomeCarBean.ResDataBean> res_data = homeHomeCarBeen.getRes_data();

                if (res_data == null) {

                } else {

                    Collections.sort(res_data, new Comparator<HomeHomeCarBean.ResDataBean>() {
                        @Override
                        public int compare(HomeHomeCarBean.ResDataBean o1, HomeHomeCarBean.ResDataBean o2) {
                            //按照分数进行升序排列
                            String s1 = o1.getSum_score();
                            String s2 = o2.getSum_score();

                            //将没有分数的条目展示在第一条
                            if (s1 == null) {
                                s1 = "0.0";
                                return -1;
                            }
                            if (s2 == null) {
                                s2 = "0.0";
                                return -1;
                            }
                            Double d1 = Double.parseDouble(s1);
                            Double d2 = Double.parseDouble(s2);
                            if (d1 > d2) {
                                return 1;
                            }
                            return -1;
                        }
                    });

                    homeHomeCarBeen.setRes_data(res_data);
                }
                listener.onSuccess(homeHomeCarBeen);
            }

        });
    }
}
