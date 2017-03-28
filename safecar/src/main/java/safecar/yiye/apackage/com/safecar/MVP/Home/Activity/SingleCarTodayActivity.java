package safecar.yiye.apackage.com.safecar.MVP.Home.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.GrowEffect;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;
import safecar.yiye.apackage.com.safecar.MVP.Adapter.HomeSingleCarTodayAdapter;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseActivity;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.Presenter.HomeSingleCarTodayActivityPresenter;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HomeSingleCarTodayActivityView;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;
import safecar.yiye.apackage.com.safecar.R;
import safecar.yiye.apackage.com.safecar.Boss.Util.AMapUtil;
import safecar.yiye.apackage.com.safecar.Boss.Util.ToastUtil;
import safecar.yiye.apackage.com.safecar.Widget.Dialog.ColorDialog;

public class SingleCarTodayActivity extends BaseActivity implements
        HomeSingleCarTodayActivityView,
        LocationSource, AMap.OnMapLoadedListener,
        AMap.OnCameraChangeListener,
        AMap.OnPOIClickListener, AMapLocationListener,
        GeocodeSearch.OnGeocodeSearchListener,
        AdapterView.OnItemClickListener, AMap.OnMapClickListener {

    @BindView(R.id.activity_home_singlecartoday_back)
    ImageView mActivityHomeSinglecartodayBack;
    @BindView(R.id.activity_home_singlecartoday_carid)
    TextView mActivityHomeSinglecartodayCarid;
    @BindView(R.id.activity_home_singlecartoday_distance)
    TextView mActivityHomeSinglecartodayDistance;
    @BindView(R.id.activity_home_singlecartoday_dot)
    TextView mActivityHomeSinglecartodayDot;
    @BindView(R.id.activity_home_singlecartoday_toolbar)
    Toolbar mActivityHomeSinglecartodayToolbar;
    @BindView(R.id.activity_home_singlecartoday_map)
    MapView mActivityHomeSinglecartodayMap;
    @BindView(R.id.activity_home_singlecartoday_listview)
    JazzyListView mActivityHomeSinglecartodayListview;
    @BindView(R.id.activity_home_singlecartoday_springview)
    SpringView mActivityHomeSinglecartodaySpringview;
    @BindView(R.id.activity_home_singlecartoday_progress)
    ProgressActivity mActivityHomeSinglecartodayProgress;
    @BindView(R.id.activity_single_car_today)
    LinearLayout mActivitySingleCarToday;
    private HomeSingleCarTodayActivityPresenter mHomeSingleCarTodayActivityPresenter;
    private AMap aMap;
    private Marker regeoMarker;
    private UiSettings mUiSettings;
    private OnLocationChangedListener mOnLocationChangedListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private Marker markerSingleDot;
    private HomeSingleCarTodayAdapter mHomeSingleCarTodayAdapter;
    private StringBuilder addressName = new StringBuilder();
    private LatLonPoint latLonPoint = new LatLonPoint(31.2396997086, 121.4995909338);
    private HomeSingleCarTodayBean mMData;
    private LatLng latLngww;
    private String mCar_id;
    private String mCar_code;
    private ArrayList<Marker> markerlst;
    private SimpleDateFormat mDateFormat_query;
    private String mMQuery_data;
    private ProgressDialog progDialog = null;

    public static final int REQUEST_ALL_PERMISSION_HOME_SINGLE_A = 103;

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 103) {
                // TODO 相应代码。
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            Toast.makeText(MyApplication.getContext(), "获取权限失败", Toast.LENGTH_SHORT).show();
            finish();
        }
    };
    private String year;
    private String month;
    private String day;
    private int yearint;
    private int monthint;
    private int dayint;
    private Polyline polyline;
    private String BaseToken;
    private MarkerOptions markOptiopnsMiddleUp;
    private Marker markerMiddleUp;
    private MarkerOptions markOptiopnsMiddleDown;
    private Marker markerMiddleDown;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /**
         * 转给AndPermission分析结果。
         *
         * @param object     要接受结果的Activity、Fragment。
         * @param requestCode  请求码。
         * @param permissions  权限数组，一个或者多个。
         * @param grantResults 请求结果。
         */
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionYes(REQUEST_CODE_ALL_SINGLE)
    private void getMultiYes(List<String> grantedPermissions) {

    }

    @PermissionNo(REQUEST_CODE_ALL_SINGLE)
    private void getMultiNo(List<String> deniedPermissions) {
    }

    public static final int REQUEST_CODE_ALL_SINGLE = 502;

    private String tel;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_single_car_today);


        SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        BaseToken = preferences.getString("token", "");
        tel = preferences.getString("phone", "");
        //当token值等于下面这个，就是表示游客登录

        if (BaseToken.equals("432F45B44C432414D2F97DF0E5743818")) {
            tel = "12345678910";
        }

        //申请权限
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_ALL_SINGLE)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(MyApplication.getContext(), rationale).show();
                    }
                })
                .send();
    }

    @Override
    protected void findViewById() {

        /**
         *  intent.putExtra("car_id", mRes_data.get(position - 1).getId());
         intent.putExtra("car_code", mRes_data.get(position - 1).getCar_code());
         */
        //车的id
        mCar_id = getIntent().getStringExtra("car_id");
        //车牌号
        mCar_code = getIntent().getStringExtra("car_code");
        mActivityHomeSinglecartodayCarid.setText(mCar_code);

         /*Locale.getDefault()*/
        mDateFormat_query = new SimpleDateFormat("yyyy/MM/dd", /*Locale.getDefault()*/Locale.ENGLISH);
        mMQuery_data = mDateFormat_query.format(new Date()).toString();

        if (aMap == null) {
            aMap = mActivityHomeSinglecartodayMap.getMap();
//            设置是否显示底图文字标注，默认显示(需要在onMapLoaded之后调用)
            aMap.showMapText(false);

            regeoMarker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).draggable(false));
            mUiSettings = aMap.getUiSettings();
//设置地图指南针
//            mUiSettings.setCompassEnabled(true);

//            设置地图是否可以旋转
            mUiSettings.setRotateGesturesEnabled(false);

            // 自定义系统定位小蓝点
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                    .fromResource(R.drawable.location));// 设置小蓝点的图标
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
            myLocationStyle.strokeWidth(12f);// 设置圆形的边框粗细
            aMap.setMyLocationStyle(myLocationStyle);
            aMap.setMyLocationRotateAngle(180);
            aMap.setLocationSource(this);// 设置定位监听
            mUiSettings.setMyLocationButtonEnabled(false); // 是否显示默认的定位按钮
            mUiSettings.setTiltGesturesEnabled(false);// 设置地图是否可以倾斜
            mUiSettings.setScaleControlsEnabled(true);// 设置地图默认的比例尺是否显示
            mUiSettings.setZoomControlsEnabled(false);
        }

        //日历的年月日
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd", /*Locale.getDefault()*/Locale.ENGLISH);
        String time = dateFormat.format(new Date()).toString();
        String[] split = time.split("_");
        year = split[0];
        month = split[1];
        day = split[2];
        yearint = Integer.parseInt(year);
        monthint = Integer.parseInt(month);
        dayint = Integer.parseInt(day);
    }

    @Override
    protected void setListener() {
        //下拉刷新
//        mActivityHomeSinglecartodaySpringview.setListener(this);
//        mActivityHomeSinglecartodaySpringview.setHeader(new DefaultHeader(mContext));

        //地图的监听
        aMap.setOnMapLoadedListener(this);
        aMap.setOnCameraChangeListener(this);
        aMap.setOnPOIClickListener(this);
        aMap.setOnMapClickListener(this);
        progDialog = new ProgressDialog(this);

        mActivityHomeSinglecartodayBack.setOnClickListener(this);
        mActivityHomeSinglecartodayDot.setOnClickListener(this);
    }

    //与后端交互  初始化数据
    @Override
    protected void processLogic() {
        // TODO: 2017/2/13/013
        Log.d("测试数据", "访问网络开始了  时间是:" + System.currentTimeMillis());
        mHomeSingleCarTodayActivityPresenter = new HomeSingleCarTodayActivityPresenter(this);
        mHomeSingleCarTodayActivityPresenter.LoadData(mCar_id, mMQuery_data, tel, BaseToken);
    }

    @Override
    protected Context getActivityContext() {
        return MyApplication.getContext();
    }


    @Override
    public void onRefresh() {
        mHomeSingleCarTodayActivityPresenter.LoadData(mCar_id, mMQuery_data, tel, BaseToken);
    }

    @Override
    public void onLoadmore() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mActivityHomeSinglecartodayMap.onCreate(savedInstanceState);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mActivityHomeSinglecartodayMap.onSaveInstanceState(outState);
    }

    @Override
    public void showProgress() {
        mActivityHomeSinglecartodayProgress.showLoading();
    }

    @Override
    public void hideProgress() {
        mActivityHomeSinglecartodayProgress.showContent();
    }

    //token失效   重新登录
    private void showPopupWindowLogin() {

        ColorDialog dialog = new ColorDialog(SingleCarTodayActivity.this);
        dialog.setColor("#000");
        dialog.setAnimationEnable(true);
        dialog.setTitle("提示");
        dialog.setContentText(Constant.TOKEN_OUT_OF_TIME);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setPositiveListener("确 认", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.cancel();
                SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(SingleCarTodayActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("token_out_of_time", "token_out_of_time");
                startActivity(intent);
            }
        }).show();
    }

    //加载到了数据
    @Override
    public void newDatas(HomeSingleCarTodayBean data) {

        Log.d("ddd", "dddd");

        if (data.getRes_code().equals("-2")) {
            showPopupWindowLogin();
        } else {

            // TODO: 2017/2/13/013
            Log.d("测试数据", "数据获取后更改数据顺序完成了  时间是:" + System.currentTimeMillis());

            mMData = data;

            //车的今日日程
            String resSum = data.getResSum();
            float parseFloat = Float.parseFloat(resSum);
            float distance1 = parseFloat / 1000f;
            //保留小数点后两位
            DecimalFormat fnum = new DecimalFormat("##0.00");
            String distance = fnum.format(distance1);

            mActivityHomeSinglecartodayDistance.setText("日程：" + distance + "km");

            mHomeSingleCarTodayAdapter = new HomeSingleCarTodayAdapter(mContext, R.layout.activity_single_car_today_listview, data, SingleCarTodayActivity.this);
            mActivityHomeSinglecartodayListview.setAdapter(mHomeSingleCarTodayAdapter);
            mActivityHomeSinglecartodayListview.setTransitionEffect(new GrowEffect());
            mActivityHomeSinglecartodayListview.setOnItemClickListener(this);

            List<LatLng> listLine = new ArrayList<LatLng>();
            int dataLength = data.getRes_fenbu().size();

            if (dataLength > 0) {

                LatLng[] latLngs = new LatLng[dataLength];

                for (int i = 0; i < dataLength; i++) {

                    double longitude = data.getRes_fenbu().get(i).getLongitude();
                    double latitude = data.getRes_fenbu().get(i).getLatitude();

                    //TODO 当经纬度为0的时候处理
                    if (longitude == 0.0) {
                        latitude = 31.367841;
                        longitude = 121.528048;
                    }
                    LatLng allLagng = new LatLng(latitude, longitude);
                    listLine.add(allLagng);
                    latLngs[i] = allLagng;

                }

                //起点终点的Mark标志
                MarkerOptions markOptiopnsStart = new MarkerOptions();

                markOptiopnsStart.position(listLine.get(0))
                        .anchor(0.5f, 1.0f)
                        .setFlat(true);
                markOptiopnsStart.icon(BitmapDescriptorFactory.fromResource(R.drawable.dir_start));
                aMap.addMarker(markOptiopnsStart);

                //起点终点的Mark标志
                MarkerOptions markOptiopnsEnd = new MarkerOptions();
                markOptiopnsEnd.position(listLine.get(dataLength - 1))
                        .anchor(0.5f, 1.0f)
                        .setFlat(true);
                markOptiopnsEnd.icon(BitmapDescriptorFactory.fromResource(R.drawable.dir_end));
                aMap.addMarker(markOptiopnsEnd);

                polyline = aMap.addPolyline((new PolylineOptions())
                        .add(latLngs)
                        .width(8)
                        .setDottedLine(true)//设置虚线
                        .color(Color.argb(127, 0, 0, 255)));

                // 设置所有maker显示在当前可视区域地图中
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (int i = 0; i < listLine.size(); i++) {
                    builder.include(listLine.get(i));
                }
                LatLngBounds build = builder.build();
                aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(build, 10));

            } else {
                Log.d("测试", "没有轨迹点");
            }
            Log.d("测试数据", "数据获取后更改  时间是:" + System.currentTimeMillis());
        }
    }


    @Override
    public void showLoadFailMsg() {
        toError();
    }

    @Override
    public void showNoData() {
        toEmpty();
    }


    public void toEmpty() {
        mActivityHomeSinglecartodayProgress.showEmpty(getResources().getDrawable(R.drawable.load_no_data),
                Constant.EMPTY_TITLE_SINGLE_CAR_TODAY, Constant.EMPTY_CONTEXT_SINGLE_CAR_TODAY);
        mActivityHomeSinglecartodayDistance.setText("日程：" + 0 + "km");
    }

    public void toError() {
        mActivityHomeSinglecartodayProgress.showError(getResources().getDrawable(R.drawable.load_error),
                Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivityHomeSinglecartodayProgress.showLoading();
                        //重试
                        mHomeSingleCarTodayActivityPresenter.LoadData(mCar_id, mMQuery_data, tel, BaseToken);
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
        mActivityHomeSinglecartodayMap.onDestroy();

        if (aMap != null) {
            aMap.clear();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mActivityHomeSinglecartodayMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理

        mActivityHomeSinglecartodayMap.onPause();

    }

    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mOnLocationChangedListener = onLocationChangedListener;
        if (mlocationClient == null) {
//            mlocationClient = new AMapLocationClient(this);
//            mLocationOption = new AMapLocationClientOption();
//            //设置定位监听
//            mlocationClient.setLocationListener(this);
//            //设置为高精度定位模式
//            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//            //设置定位参数
//            mlocationClient.setLocationOption(mLocationOption);
//            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
//            // 在定位结束后，在合适的生命周期调用onDestroy()方法
//            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//            mlocationClient.startLocation();
        }
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mOnLocationChangedListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mOnLocationChangedListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败,请检查网络";
                Log.e("AmapErr", errText);
                ToastUtil.startShort(this, errText);
            }
        }
    }

    @Override
    public void deactivate() {
        mOnLocationChangedListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onMapLoaded() {
//        // 设置所有maker显示在当前可视区域地图中
//        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        for (int i = 0; i < mMData.getRes_fenbu().size(); i++) {
//            LatLng allLagng = new LatLng(mMData.getRes_fenbu().get(i).getLongitude(), mMData.getRes_fenbu().get(i).getLatitude());
//            builder.include(allLagng);
//        }
//        LatLngBounds build = builder.build();
//        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(build, 10));
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
    }


    private void initGeocodeSearch() {
        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 500,
                GeocodeSearch.GPS);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocodeSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_home_singlecartoday_back:
                finish();
                break;
            case R.id.activity_home_singlecartoday_dot:
//                Intent intent = new Intent(mContext, HomeMoreSingleCarActivity.class);
//                intent.putExtra("car_id", mCar_id);
//                intent.putExtra("car_code", mCar_code);
//                startActivity(intent);

                // 对话框下的DatePicker示例 Example in dialog
                final AlertDialog dialog = new AlertDialog.Builder(SingleCarTodayActivity.this).create();
                dialog.show();
                DatePicker picker = new DatePicker(SingleCarTodayActivity.this);
                picker.setDate(yearint, monthint);
                picker.setMode(DPMode.SINGLE);
                picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
                    @Override
                    public void onDatePicked(String date) {


                        String[] split = date.split("-");
                        year = split[0];
                        month = split[1];
                        day = split[2];

                        yearint = Integer.parseInt(year);
                        monthint = Integer.parseInt(month);
                        dayint = Integer.parseInt(day);

                        StringBuilder sb = new StringBuilder();
                        String[] split1 = date.split("-");
                        String y = split1[0];
                        String m = split1[1];
                        String d = split1[2];
                        if (m.length() != 2) {
                            m = "0" + m;
                        }
                        if (d.length() != 2) {
                            d = "0" + d;
                        }
                        sb = sb.append(y).append("/").append(m).append("/").append(d);
                        mMQuery_data = sb.toString();

                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                        try {
                            Date parse1 = format.parse(mMQuery_data);
                            Date parse2 = new Date();

                            int jiangeTime = differentDaysByMillisecond(parse2, parse1);
                            //判断查询日期是30以内的
                            if (jiangeTime > 30) {
                                ToastUtil.startShort(SingleCarTodayActivity.this, "仅支持近30天数据的查询");
                                return;
                            }
                            //判断日期不是今天的
                            if (parse1.getMonth() == parse2.getMonth()) {
                                if (parse1.getDate() > parse2.getDate()) {
                                    ToastUtil.startShort(SingleCarTodayActivity.this, "选择日期不能超过今日！");
                                    return;
                                }
                            }
                            //判断日期不是昨天以及昨天之前的
                            if (jiangeTime < 0) {
                                ToastUtil.startShort(SingleCarTodayActivity.this, "选择日期不能超过今日！");
                                return;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        mHomeSingleCarTodayActivityPresenter.LoadData(mCar_id, mMQuery_data, tel, BaseToken);
                        aMap.clear();
                        //展示进度条
                        mActivityHomeSinglecartodayProgress.showLoading();
                        ToastUtil.startShort(SingleCarTodayActivity.this, date);

                        dialog.dismiss();
                    }
                });
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setContentView(picker, params);
                dialog.getWindow().setGravity(Gravity.CENTER);
                break;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    @Override
    public void onPOIClick(Poi poi) {
        poi.getCoordinate();

    }

    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker, final LatLng latLngww) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        Point startPoint = proj.toScreenLocation(latLngww);
        startPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {

                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * latLngww.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * latLngww.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    /**
     * 逆地理编码回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {

        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {

                if (markerSingleDot != null) {
                    markerSingleDot.remove();
                }
                if (markerMiddleDown != null) {
                    markerMiddleDown.remove();
                }
                if (markerMiddleUp != null) {
                    markerMiddleUp.remove();
                }

//                aMap.clear();
                String formatAddress = result.getRegeocodeAddress().getFormatAddress();

                //设置中心点和缩放比例
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLngww));
                MarkerOptions markOptiopns = new MarkerOptions();
                markOptiopns.position(latLngww)
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))    // 将Marker设置为贴地显示，可以双指下拉看效果
                        .anchor(0.5f, 1.5f)
                        .setFlat(true);
//                TextView textView = new TextView(getApplicationContext());
//                String text = addressName.toString() + formatAddress;
//                if (!text.contains("市")) {
//                    text = addressName.toString() + "Sorry,暂时无法解析此地址！";
//                }
//                textView.setText(text);
                TextView textView = new TextView(getApplicationContext());
                if (!formatAddress.contains("市")) {
                    formatAddress = "Sorry,暂时无法解析此地址！";

                    textView.setText(addressName.toString() + "\n" + formatAddress);

                } else {
                    if (formatAddress.length() > 23) {
                        String substring1 = formatAddress.substring(0, 23);
                        String substring2 = formatAddress.substring(23, formatAddress.length());
                        textView.setText(addressName.toString() + "\n" + substring1 + "\n" + substring2);
                    } else {
                        textView.setText(addressName.toString() + "\n" + formatAddress);
                    }
                }

                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundResource(R.drawable.custom_info_bubble);
                markOptiopns.icon(BitmapDescriptorFactory.fromView(textView));

                markerSingleDot = aMap.addMarker(markOptiopns);

                markOptiopnsMiddleDown = new MarkerOptions();
                markOptiopnsMiddleDown.position(latLngww)
                        .anchor(0.5f, 0.5f)
                        .setFlat(true);
                markOptiopnsMiddleDown.icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin));
                markerMiddleDown = aMap.addMarker(markOptiopnsMiddleDown);

                jumpPoint(markerSingleDot, latLngww);

            } else {
                ToastUtil.startShort(this, "没有明确地址");
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }
        dismissDialog();
    }

    /**
     * 地理编码查询回调
     */
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDialog();
        mHomeSingleCarTodayAdapter.setSelectItemPosition(position);
        mHomeSingleCarTodayAdapter.notifyDataSetInvalidated();
        mHomeSingleCarTodayAdapter.notifyDataSetChanged();

        HomeSingleCarTodayBean.ResExpectionBean resExpectionBean = mMData.getRes_expection().get(position);
        String vehicle_exception_code = resExpectionBean.getPro();
        long vehicle_exception_timestamp = resExpectionBean.getData_date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(vehicle_exception_timestamp);
        String format = sdf.format(date);
        addressName = new StringBuilder(format + "    " + vehicle_exception_code +
                "    扣" + resExpectionBean.getScore() + "分");
//        aMap.clear();

        /**
         * 当点击条目的时候
         * 清空所有的标记点
         * 显现出该项违规的点 标记
         */

        //经纬度是item给的
        String latitude1 = resExpectionBean.getLatitude();
        String longitude1 = resExpectionBean.getLongitude();
        double latitude;
        double longitude;
        if (latitude1 == null) {
            dismissDialog();
            return;
//            HomeSingleCarTodayBean.ResFenbuBean resFenbuBean = mMData.getRes_fenbu().get(0);
//            latitude = resFenbuBean.getLatitude();
//            longitude = resFenbuBean.getLongitude();
        } else {
            latitude = Double.parseDouble(latitude1);
            longitude = Double.parseDouble(longitude1);
        }

        latLonPoint = new LatLonPoint(latitude, longitude);
        latLngww = new LatLng(latitude, longitude);
        initGeocodeSearch();
    }

    /**
     * 显示进度条对话框
     */
    public void showDialog() {
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在获取地址...");
        progDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

        double latitude = latLng.latitude;
        double longitude = latLng.longitude;
        for (int i = 0; i < mMData.getRes_fenbu().size(); i++) {
            HomeSingleCarTodayBean.ResFenbuBean resFenbuBean = mMData.getRes_fenbu().get(i);
            if (resFenbuBean.getLatitude() >= latitude - 0.02f &&
                    resFenbuBean.getLatitude() <= latitude + 0.02f
                    && resFenbuBean.getLongitude() >= longitude - 0.02f
                    && resFenbuBean.getLongitude() <= longitude + 0.02f) {

                if (markerMiddleUp != null) {
                    markerMiddleUp.remove();
                }
                if (markerMiddleDown != null) {
                    markerMiddleDown.remove();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date date = new Date(resFenbuBean.getDt());
                String format = sdf.format(date);
                TextView textView = new TextView(getApplicationContext());
//                ToastUtil.startShort(SingleCarTodayActivity.this, +resFenbuBean.getDt()
//                        + "时间");
                markOptiopnsMiddleUp = new MarkerOptions();
                LatLng latLng1 = new LatLng(resFenbuBean.getLatitude(), resFenbuBean.getLongitude());
                markOptiopnsMiddleUp.position(latLng1)
                        .anchor(0.5f, 1.5f)
                        .setFlat(true);
                textView.setText("时间: "+format+"分");
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundResource(R.drawable.custom_info_bubble);
                markOptiopnsMiddleUp.icon(BitmapDescriptorFactory.fromView(textView));
                markerMiddleUp = aMap.addMarker(markOptiopnsMiddleUp);

                markOptiopnsMiddleDown = new MarkerOptions();
                LatLng latLng3 = new LatLng(resFenbuBean.getLatitude(), resFenbuBean.getLongitude());
                markOptiopnsMiddleDown.position(latLng3)
                        .anchor(0.5f, 0.5f)
                        .setFlat(true);
                markOptiopnsMiddleDown.icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin));
                markerMiddleDown = aMap.addMarker(markOptiopnsMiddleDown);

//                //
//                for (int j = 0; j < mMData.getRes_expection().size(); j++) {
//                    HomeSingleCarTodayBean.ResExpectionBean resExpectionBean = mMData.getRes_expection().get(j);
//                    if ((resFenbuBean.getLatitude()+"").equals(resExpectionBean.getLatitude()+"")
//                            && (resExpectionBean.getLongitude()+"").equals(resFenbuBean.getLongitude()+"")) {
//                        mActivityHomeSinglecartodayListview.setSelection(i);
//                    }
//                }

return;
            }
        }
    }


//    @Override
//    public void onPolylineClick(Polyline polyline) {
//
//                LatLng latLng = polyline.getPoints().get(100);
//                double latitude = latLng.latitude;
//                double longitude = latLng.longitude;
//
//                for (int i = 0; i < mMData.getRes_fenbu().size(); i++) {
//                    HomeSingleCarTodayBean.ResFenbuBean resFenbuBean = mMData.getRes_fenbu().get(i);
//                    if (resFenbuBean.getLatitude() == latitude && resFenbuBean.getLongitude() == longitude) {
//                        ToastUtil.startShort(SingleCarTodayActivity.this, +resFenbuBean.getDt() + "时间");
//                        markOptiopnsMiddleUp = new MarkerOptions();
//                        LatLng latLng1 = new LatLng(resFenbuBean.getLatitude(), resFenbuBean.getLongitude());
//                        LatLng latLng2 = new LatLng(30.832445, 121.929145);
//                        markOptiopnsMiddleUp.position(latLng1)
//                                .anchor(0.5f, 0.5f)
//                                .setFlat(true);
//                        markOptiopnsMiddleUp.icon(BitmapDescriptorFactory.fromResource(R.drawable.attation));
//                        aMap.addMarker(markOptiopnsMiddleUp);
//
//                    }
//                }
//
//    }
}
