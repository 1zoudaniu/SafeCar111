package safecar.yiye.apackage.com.safecar.MVP.Home.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observer;
import safecar.yiye.apackage.com.safecar.Boss.Data.HttpData.HttpData;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;

/**
 * Name: HomeSingleCarTodayActivityModel
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-24 16:03
 * 首页条目点击进去的
 */
public class HomeSingleCarTodayActivityModel {
    public void loadData(String code, String datestr, String tel, String token, final OnLoadDataListListener listener) {

        HttpData.getInstance().getHomeCarTodayInfo(code, datestr, tel, token, new Observer<HomeSingleCarTodayBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(HomeSingleCarTodayBean homeHomeCarBeen) {
                // TODO: 2017/2/13/013
                Log.d("时间测试model", "获取到数据了");
//
//
//                List<HomeSingleCarTodayBean.ResFenbuBean> res_fenbu = homeHomeCarBeen.getRes_fenbu();
//                List<HomeSingleCarTodayBean.ResFenbuBean> res_fenbu1 = new ArrayList<HomeSingleCarTodayBean.ResFenbuBean>();
//                for (int i = 0; i < 10000; i++) {
//                    res_fenbu1.add(res_fenbu.get(i % res_fenbu.size()));
//                }
//                homeHomeCarBeen.setRes_fenbu(res_fenbu1);
//                Log.d("时间测试model", "数据点是："+homeHomeCarBeen.getRes_fenbu().size());
                if (homeHomeCarBeen.getRes_code().equals("-2")) {

                } else {

                    List<HomeSingleCarTodayBean.ResExpectionBean> res_expection = homeHomeCarBeen.getRes_expection();
                    Collections.sort(res_expection, new Comparator<HomeSingleCarTodayBean.ResExpectionBean>() {
                        @Override
                        public int compare(HomeSingleCarTodayBean.ResExpectionBean o1, HomeSingleCarTodayBean.ResExpectionBean o2) {
                            long data_date1 = o1.getData_date();
                            long data_date2 = o2.getData_date();
                            if (data_date1 > data_date2) {
                                return -1;
                            }
                            return 1;
                        }
                    });
                    homeHomeCarBeen.setRes_expection(res_expection);
                }

                listener.onSuccess(homeHomeCarBeen);
                Log.d("时间测试model", "数据更改时间完成");
            }

        });
    }
}
