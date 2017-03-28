package safecar.yiye.apackage.com.safecar.Boss.Data.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import safecar.yiye.apackage.com.safecar.MVP.Entity.DetailNowBean;

/**
 * Name: ApiManagerService
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-12-06 10:10
 */
public interface ApiManagerService {

    /**
     * 获取周公解梦类型
     *
     * @return
     */
    @GET("/iChucheBox/a/device/device/interface/getCarinfoByCode")
    Call<List<DetailNowBean>> getTestCarApi(@Query("code") String code,@Query("datestr") String datestr);


}
