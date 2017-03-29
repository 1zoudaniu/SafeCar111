package safecar.yiye.apackage.com.safecar.MVP.Home.Fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.GrowEffect;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseFragment;
import safecar.yiye.apackage.com.safecar.MVP.Entity.DetailNowBean;
import safecar.yiye.apackage.com.safecar.MVP.Holder.DetailNowExpandGroupHolder;
import safecar.yiye.apackage.com.safecar.MVP.Home.Presenter.HomeDetailNowFragmentPresenter;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HomeDetailNowFragmentView;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;
import safecar.yiye.apackage.com.safecar.R;
import safecar.yiye.apackage.com.safecar.Boss.Util.AMapUtil;
import safecar.yiye.apackage.com.safecar.Boss.Util.ToastUtil;
import safecar.yiye.apackage.com.safecar.Widget.Dialog.ColorDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailNowFragment extends BaseFragment implements
        HomeDetailNowFragmentView,
        AdapterView.OnItemClickListener,
        LocationSource, AMapLocationListener,
        GeocodeSearch.OnGeocodeSearchListener {


    @BindView(R.id.list_fragment_map)
    MapView mListFragmentMap;
    @BindView(R.id.expandableListView)
    JazzyListView mExpandableListView;
    @BindView(R.id.activity_detail_now_progress)
    ProgressActivity mActivityDetailNowProgress;
    private DetailNowBean mDetailNowBeen;
    private AMap aMap_frame;
    private JazzyListViewAdapter mJazzyListViewAdapter;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient = new AMapLocationClient(MyApplication.getContext());
    private AMapLocationClientOption mLocationOption;
    private StringBuilder sb;
    private LatLonPoint latLonPoint;
    private LatLng latLng;
    private HomeDetailNowFragmentPresenter mHomeDetailNowFragmentPresenter;
    private ProgressDialog progDialog = null;
    private String BaseToken;
    private String tel;
    private MarkerOptions markOptiopnsMiddleDown;
    private Marker markerMiddleDown;

    public DetailNowFragment() {
    }


    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mListFragmentMap.onSaveInstanceState(outState);
    }


    @Override
    protected void initSaveState(Bundle savedInstanceState) {
        mListFragmentMap.onCreate(savedInstanceState);// 此方法必须重写
    }

    public static final int REQUEST_CODE_ALL_DETAIL_NOW = 501;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        //申请权限
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_ALL_DETAIL_NOW)
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

        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        BaseToken = preferences.getString("token", "");
        tel = preferences.getString("phone", "");
        //当token值等于下面这个，就是表示游客登录
        if (BaseToken.equals("432F45B44C432414D2F97DF0E5743818")) {
            tel = "12345678910";
        }
        return inflater.inflate(R.layout.fragment_detail_now, container, false);
    }


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

    @PermissionYes(REQUEST_CODE_ALL_DETAIL_NOW)
    private void getMultiYes(List<String> grantedPermissions) {

    }

    @PermissionNo(REQUEST_CODE_ALL_DETAIL_NOW)
    private void getMultiNo(List<String> deniedPermissions) {
    }

    @Override
    protected void initListener() {

        if (aMap_frame == null) {
            aMap_frame = mListFragmentMap.getMap();
            UiSettings uiSettings = aMap_frame.getUiSettings();
            // 自定义系统定位小蓝点
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                    .fromResource(R.drawable.location));// 设置小蓝点的图标
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
            myLocationStyle.strokeWidth(12f);// 设置圆形的边框粗细
            aMap_frame.setMyLocationStyle(myLocationStyle);
            aMap_frame.setMyLocationRotateAngle(60);
            aMap_frame.setLocationSource(this);// 设置定位监听
            uiSettings.setMyLocationButtonEnabled(false); // 是否显示默认的定位按钮
            uiSettings.setTiltGesturesEnabled(true);// 设置地图是否可以倾斜
            uiSettings.setScaleControlsEnabled(true);// 设置地图默认的比例尺是否显示
            uiSettings.setZoomControlsEnabled(true);
        }

        progDialog = new ProgressDialog(getContext());
    }

    @Override
    protected void initData() {
        mHomeDetailNowFragmentPresenter = new HomeDetailNowFragmentPresenter(this);
        mHomeDetailNowFragmentPresenter.LoadData(tel, BaseToken);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDialog();
        //TODO  第三条数据一闪而过
        DetailNowBean guijiExpandGroupBean = mDetailNowBeen;

        String vehicleStatus = guijiExpandGroupBean.getRes_data().get(position).getCurrMap().getVehicleStatus();
        switch (vehicleStatus) {
            case "0":
                vehicleStatus = "熄火";
                break;
            case "1":
                vehicleStatus = "行驶中";
                break;
            case "2":
                vehicleStatus = "GPS丢失";
                break;
            case "3":
                vehicleStatus = "信号丢失";
                break;
            default:
                vehicleStatus = "未知状态";
        }
        String lat = guijiExpandGroupBean.getRes_data().get(position).getCurrMap().getLat();
        String lng = guijiExpandGroupBean.getRes_data().get(position).getCurrMap().getLng();
        float latitude = Float.parseFloat(lat);
        float longitude = Float.parseFloat(lng);
        latLonPoint = new LatLonPoint(latitude, longitude);
        latLng = new LatLng(latitude, longitude);


        DecimalFormat df = new DecimalFormat("######0.00");
        //处理为null的情况
        String sum_mile = guijiExpandGroupBean.getRes_data().get(position).getSum_mile();
        if (sum_mile == null) {
            sum_mile = "0";
        }
        float parseFloat = Float.parseFloat(sum_mile);
        float mile_float = parseFloat / 1000f;
        String format = df.format(mile_float);
        float mile = Float.parseFloat(format);
        sb = new StringBuilder(guijiExpandGroupBean.getRes_data().get(position).getCar_code() + "     " +
                vehicleStatus + "     " +
                mile + "km");
        Log.d("ddddd", sb.toString());
/**
 *  Float latitude =
 *  Float longitude =
 */
        mListFragmentMap.setVisibility(View.VISIBLE);
        mJazzyListViewAdapter.setSelectedPosition(position);
        mJazzyListViewAdapter.notifyDataSetInvalidated();

        initGeocodeSearch();
    }

    private void initGeocodeSearch() {
        GeocodeSearch geocodeSearch = new GeocodeSearch(getContext());
        geocodeSearch.setOnGeocodeSearchListener(this);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.GPS);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocodeSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {

        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {

                aMap_frame.clear();

                String formatAddress = result.getRegeocodeAddress().getFormatAddress();

                aMap_frame.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        AMapUtil.convertToLatLng(latLonPoint), 11));


                MarkerOptions markOptiopns = new MarkerOptions();
                markOptiopns.position(latLng).icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))    // 将Marker设置为贴地显示，可以双指下拉看效果
                        .anchor(0.5f, 1.5f)
                        .setFlat(true);
                TextView textView = new TextView(getContext());
                if (formatAddress.length() > 23) {
                    String substring1 = formatAddress.substring(0, 23);
                    String substring2 = formatAddress.substring(23, formatAddress.length());
                    textView.setText(sb.toString() + "\n" + substring1 + "\n" + substring2);
                } else {
                    textView.setText(sb.toString() + "\n" + formatAddress);
                }

                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundResource(R.drawable.map_mark);
                markOptiopns.icon(BitmapDescriptorFactory.fromView(textView));

                Marker marker1 = aMap_frame.addMarker(markOptiopns);

                marker1.setPosition(AMapUtil.convertToLatLng(latLonPoint));
                marker1.setAnchor(0.5f, 1.3f);



                markOptiopnsMiddleDown = new MarkerOptions();
                markOptiopnsMiddleDown.position(latLng)
                        .anchor(0.5f, 1.0f)
                        .setFlat(true);
                markOptiopnsMiddleDown.icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin_blue));
                markerMiddleDown = aMap_frame.addMarker(markOptiopnsMiddleDown);



                jumpPoint(marker1, latLng);


            } else {
                ToastUtil.startShort(getActivity(), "没有明确地址");
            }
        } else {
            ToastUtil.showerror(getActivity(), rCode);
        }
        dismissDialog();
    }

    /**
     * //         * marker点击时跳动一下
     * //
     */
    public void jumpPoint(final Marker marker, final LatLng latLng) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap_frame.getProjection();
        Point startPoint = proj.toScreenLocation(latLng);
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
                double lng = t * latLng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * latLng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    public void showProgress() {
        mActivityDetailNowProgress.showLoading();
    }

    @Override
    public void hideProgress() {
        mActivityDetailNowProgress.showContent();
    }

    //token失效   重新登录
    private void showPopupWindowLogin() {

        ColorDialog dialog = new ColorDialog(getContext());
        dialog.setColor("#000");
        dialog.setAnimationEnable(true);
        dialog.setTitle("提示");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentText(Constant.TOKEN_OUT_OF_TIME);

        dialog.setPositiveListener("确 认", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.cancel();
                SharedPreferences preferences = getActivity().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("token_out_of_time", "token_out_of_time");
                getActivity().startActivity(intent);
//                MyApplication.getInstance().exit();
            }
        }).show();
    }

    @Override
    public void newDatas(DetailNowBean data) {
        if (data.getRes_code().equals("-2")) {

            showPopupWindowLogin();

        } else {


            mDetailNowBeen = data;
            mJazzyListViewAdapter = new JazzyListViewAdapter();
            mExpandableListView.setTransitionEffect(new GrowEffect());
            mExpandableListView.setAdapter(mJazzyListViewAdapter);
            mExpandableListView.setOnItemClickListener(this);

        }
    }

    @Override
    public void showLoadFailMsg() {
        toError();
    }

    @Override
    public void showEmptyMsg() {
        toEmpty();
    }

    public void toEmpty() {
        mActivityDetailNowProgress.showEmpty(getResources().getDrawable(R.drawable.load_no_data),
                Constant.EMPTY_TITLE_DETAIL_NOW, Constant.EMPTY_CONTEXT_DETAIL_NOW);
    }

    public void toError() {
        mActivityDetailNowProgress.showError(getResources().getDrawable(R.drawable.load_error),
                Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivityDetailNowProgress.showLoading();
                        //重试
                        mHomeDetailNowFragmentPresenter.LoadData(tel, BaseToken);
                    }
                });
    }

    private class JazzyListViewAdapter extends BaseAdapter {
        private int selectedPosition = -1;// 选中的位置

        @Override
        public int getCount() {
            return mDetailNowBeen.getRes_data().size();
        }

        @Override
        public Object getItem(int position) {
            return mDetailNowBeen.getRes_data().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DetailNowExpandGroupHolder groupHolder = null;
            DetailNowBean guijiExpandGroupBean = mDetailNowBeen;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.fragment_detail_now_groupitem, null);
                groupHolder = new DetailNowExpandGroupHolder();

                groupHolder.carid = (TextView) convertView.findViewById(R.id.tv_group_carid);
                groupHolder.carstatus = (TextView) convertView.findViewById(R.id.tv_group_status);
                groupHolder.carkm = (TextView) convertView.findViewById(R.id.tv_group_km);

                convertView.setTag(groupHolder);

            } else {
                groupHolder = (DetailNowExpandGroupHolder) convertView.getTag();
            }

            if (selectedPosition == position) {
                groupHolder.carid.setTextColor(Color.WHITE);
                groupHolder.carstatus.setTextColor(Color.WHITE);
                groupHolder.carkm.setTextColor(Color.WHITE);
                convertView.setBackgroundResource(R.color.gray);
//                convertView.setBackgroundColor(Color.LTGRAY);
            } else {
                groupHolder.carid.setTextColor(Color.BLACK);
                groupHolder.carstatus.setTextColor(Color.BLACK);
                groupHolder.carkm.setTextColor(Color.BLACK);
                convertView.setBackgroundColor(Color.TRANSPARENT);

            }
            groupHolder.carid.setText(guijiExpandGroupBean.getRes_data().get(position).getCar_code());
            /**
             * 行程状态:0,熄火，1行驶中，2，GPS丢失，3信号丢失:0,熄火，1行驶中，2，GPS丢失，3信号丢失
             */

            String vehicleStatus = guijiExpandGroupBean.getRes_data().get(position).getCurrMap().getVehicleStatus();
            switch (vehicleStatus) {
                case "0":
                    vehicleStatus = "熄火";
                    break;
                case "1":
                    vehicleStatus = "行驶中";
                    break;
                case "2":
                    vehicleStatus = "GPS丢失";
                    break;
                case "3":
                    vehicleStatus = "信号丢失";
                    break;
                default:
                    vehicleStatus = "未知状态";
            }
            groupHolder.carstatus.setText(vehicleStatus);

            DecimalFormat df = new DecimalFormat("######0.00");
            String sum_mile = guijiExpandGroupBean.getRes_data().get(position).getSum_mile();
            //处理总里程为null
            if (sum_mile == null) {
                groupHolder.carkm.setText(0 + "km");
            } else {
                float parseFloat = Float.parseFloat(sum_mile);
                float mile_float = parseFloat / 1000f;
                String format = df.format(mile_float);
                float mile = Float.parseFloat(format);
                groupHolder.carkm.setText(mile + "km");
            }


            return convertView;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        super.onPause();
        super.onDestroy();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mListFragmentMap.onDestroy();
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
}
