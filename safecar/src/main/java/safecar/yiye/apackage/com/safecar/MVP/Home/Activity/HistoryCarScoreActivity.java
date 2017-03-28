package safecar.yiye.apackage.com.safecar.MVP.Home.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseActivity;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HistoryCarScoreBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.Presenter.HistoryCarScorePresenter;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HistoryCarScoreActivityView;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;
import safecar.yiye.apackage.com.safecar.R;
import safecar.yiye.apackage.com.safecar.Widget.Dialog.ColorDialog;
import safecar.yiye.apackage.com.safecar.Widget.MyMarkerView;

public class HistoryCarScoreActivity extends BaseActivity implements OnChartGestureListener, OnChartValueSelectedListener,
        View.OnClickListener, HistoryCarScoreActivityView {

    @BindView(R.id.activity_chart_week)
    LineChart mChartWeek;
    @BindView(R.id.activity_chart_mounth)
    LineChart mChartMounth;
    @BindView(R.id.back)
    ImageView back;
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    @BindView(R.id.activity_history_score_springview)
    SpringView activityHistoryScoreSpringview;
    @BindView(R.id.activity_history_score_progress)
    ProgressActivity activityHistoryScoreProgress;
    private HistoryCarScorePresenter mHistoryCarScorePresenter;
    private HistoryCarScoreBean mData;
    private SimpleDateFormat mDateFormat_query;
    private String mMQuery_data;
    private String BaseToken;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_history_car_score);

        SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        BaseToken = preferences.getString("token", "");
        tel = preferences.getString("phone", "");
        //当token值等于下面这个，就是表示游客登录

        if (BaseToken.equals("432F45B44C432414D2F97DF0E5743818")) {
            tel = "12345678910";
        }

        mDateFormat_query = new SimpleDateFormat("yyyy/MM/dd", /*Locale.getDefault()*/Locale.ENGLISH);
        mMQuery_data = mDateFormat_query.format(new Date()).toString();
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        mHistoryCarScorePresenter = new HistoryCarScorePresenter(this);
        mHistoryCarScorePresenter.LoadData("30", tel, BaseToken, mMQuery_data);
    }

    @Override
    protected Context getActivityContext() {
        return MyApplication.getContext();
    }

    private void initListener() {
        mTfRegular = Typeface.createFromAsset(MyApplication.getContext().getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(MyApplication.getContext().getAssets(), "OpenSans-Light.ttf");

        mChartWeek.setOnChartGestureListener(this);
        mChartWeek.setOnChartValueSelectedListener(this);
        mChartWeek.setDrawGridBackground(false);
        back.setOnClickListener(this);

        // no description text
        mChartWeek.getDescription().setEnabled(false);
        mChartMounth.getDescription().setEnabled(false);

        mChartWeek.setDragEnabled(true);
        mChartWeek.setScaleEnabled(true);
        mChartMounth.setDragEnabled(true);
        mChartMounth.setScaleEnabled(true);
        mChartWeek.setPinchZoom(true);
        mChartMounth.setPinchZoom(true);
        // to use for it  设置点击后出现的的mark标记
        MyMarkerView mv = new MyMarkerView(MyApplication.getContext(), R.layout.chart_marker_view);
        mv.setChartView(mChartMounth); // For bounds control
        mChartMounth.setMarker(mv); // Set the marker to the chart


        Typeface tf = Typeface.createFromAsset(MyApplication.getContext().getAssets(), "OpenSans-Regular.ttf");
        XAxis xAxis = mChartWeek.getXAxis();

        //设置X坐标字的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(tf);
        //设置画格子
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.enableGridDashedLine(10f, 10f, 0);

        XAxis xAxisMounth = mChartMounth.getXAxis();

        //设置X坐标字的位置
        xAxisMounth.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisMounth.setTypeface(tf);
        //设置画格子
        xAxisMounth.setDrawGridLines(false);
        xAxisMounth.setDrawAxisLine(true);
        xAxisMounth.enableGridDashedLine(10f, 10f, 0);


        YAxis leftAxis = mChartWeek.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawZeroLine(false);

        leftAxis.setDrawLimitLinesBehindData(true);

        YAxis leftAxisMounth = mChartMounth.getAxisLeft();
        leftAxisMounth.removeAllLimitLines();
        leftAxisMounth.setAxisMaximum(100f);
        leftAxisMounth.setAxisMinimum(0f);
        //leftAxis.setYOffset(20f);
        leftAxisMounth.enableGridDashedLine(10f, 10f, 0);
        leftAxisMounth.setDrawZeroLine(false);

        leftAxisMounth.setDrawLimitLinesBehindData(true);

        mChartWeek.getAxisRight().setEnabled(false);
        mChartMounth.getAxisRight().setEnabled(false);

        // add data
        setDataWeek(7, 20);
        setDataMounth(30, 30);

        mChartWeek.animateX(0);
        mChartMounth.animateX(0);
        Legend l = mChartWeek.getLegend();
        Legend lMounth = mChartMounth.getLegend();

        l.setForm(Legend.LegendForm.LINE);
        lMounth.setForm(Legend.LegendForm.LINE);


        //清楚顶点数字
        List<ILineDataSet> sets = mChartMounth.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {
            LineDataSet set = (LineDataSet) iSet;
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
        mChartMounth.invalidate();
        mChartWeek.animateXY(3000, 3000);
        mChartMounth.animateXY(3000, 3000);
    }

    private void setDataWeek(int count, int range) {

        //模拟一个x轴的数据  12/1 12/2 ... 12/7
        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            xValues.add(mData.getRes_score().get(i).getDate_str());
        }

//模拟一组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
//            int val = (int) ((Math.random() * range) + 80);
            double score = mData.getRes_score().get(i).getScore();
            yValues.add(new Entry(i, (float) (score)));
        }

        LineDataSet set1Week;

        if (mChartWeek.getData() != null &&
                mChartWeek.getData().getDataSetCount() > 0) {
            set1Week = (LineDataSet) mChartWeek.getData().getDataSetByIndex(0);
            set1Week.setValues(yValues);
            mChartWeek.getData().notifyDataChanged();
            mChartWeek.notifyDataSetChanged();
        } else {
            set1Week = new LineDataSet(yValues, "近7天历史评分");

            set1Week.enableDashedLine(10f, 5f, 0f);
            set1Week.enableDashedHighlightLine(10f, 5f, 0f);
            set1Week.setColor(Color.BLACK);
            set1Week.setCircleColor(Color.BLACK);
            set1Week.setLineWidth(1f);
            set1Week.setCircleRadius(5f);
            set1Week.setDrawCircleHole(false);
            set1Week.setValueTextSize(10f);
            set1Week.setDrawFilled(true);
            set1Week.setFormLineWidth(1f);
            set1Week.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1Week.setFormSize(15f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(MyApplication.getContext(), R.drawable.fade_red);
                set1Week.setFillDrawable(drawable);
            } else {
                set1Week.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSetsWeek = new ArrayList<ILineDataSet>();
            dataSetsWeek.add(set1Week);
            LineData dataWeek = new LineData(dataSetsWeek);

            final String[] quarters = new String[7];
//             the labels that should be drawn on the XAxis
            for (int i = 0; i < 7; i++) {
                quarters[i] = mData.getRes_score().get(i).getDate_str();
            }


            IAxisValueFormatter formatter = new IAxisValueFormatter() {

                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return quarters[(int) value];
                }

                // we don't draw numbers, so no decimal digits needed
                @Override
                public int getDecimalDigits() {  return 0; }
            };

            XAxis xAxis = mChartWeek.getXAxis();
            xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
            xAxis.setValueFormatter(formatter);



//            // the labels that should be drawn on the XAxis
//            final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4" };
//
//            IAxisValueFormatter formatter = new IAxisValueFormatter() {
//
//                @Override
//                public String getFormattedValue(float value, AxisBase axis) {
//                    return quarters[(int) value];
//                }
//
//                // we don't draw numbers, so no decimal digits needed
//                @Override
//                public int getDecimalDigits() {  return 0; }
//            };
//
//            XAxis xAxis = mLineChart.getXAxis();
//            xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
//            xAxis.setValueFormatter(formatter);

            // set data
            mChartWeek.setData(dataWeek);
        }
    }


    private void setDataMounth(int count, int range) {

        //模拟一个x轴的数据  12/1 12/2 ... 12/7
        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i < mData.getRes_score().size(); i++) {
            xValues.add(mData.getRes_score().get(i).getDate_str());
        }

        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < mData.getRes_score().size(); i++) {
//            int val = (int) ((Math.random() * range) + 70);

            values.add(new Entry(i, (float) mData.getRes_score().get(i).getScore()));
        }

        LineDataSet set1;
        if (mChartMounth.getData() != null &&
                mChartMounth.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChartMounth.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChartMounth.getData().notifyDataChanged();
            mChartMounth.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "近30天历史评分");
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(5f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(10f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(MyApplication.getContext(), R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            final String[] quarters = new String[30];
//             the labels that should be drawn on the XAxis
            for (int i = 0; i < mData.getRes_score().size(); i++) {
                quarters[i] = mData.getRes_score().get(i).getDate_str();
            }


            IAxisValueFormatter formatter = new IAxisValueFormatter() {

                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return quarters[(int) value];
                }

                // we don't draw numbers, so no decimal digits needed
                @Override
                public int getDecimalDigits() {  return 0; }
            };

            XAxis xAxis = mChartMounth.getXAxis();
            xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
            xAxis.setValueFormatter(formatter);


            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            LineData data = new LineData(dataSets);
            mChartMounth.setData(data);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void showProgress() {
        activityHistoryScoreProgress.showLoading();
    }

    @Override
    public void hideProgress() {
        activityHistoryScoreProgress.showContent();
    }

    //token失效   重新登录
    private void showPopupWindowLogin() {

        ColorDialog dialog = new ColorDialog(HistoryCarScoreActivity.this);
        dialog.setColor("#000");
        dialog.setAnimationEnable(true);
        dialog.setTitle("提示");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentText(Constant.TOKEN_OUT_OF_TIME);

        dialog.setPositiveListener("确 认", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.cancel();
                SharedPreferences preferences=HistoryCarScoreActivity.this.getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(HistoryCarScoreActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("token_out_of_time", "token_out_of_time");
                startActivity(intent);
//                MyApplication.getInstance().exit();
            }
        }).show();
    }
    @Override
    public void newDatas(HistoryCarScoreBean data) {

        //重新登录
        if (data.getRes_code().equals("-2")) {
            showPopupWindowLogin();
//            showToast("身份验证失败，请重新登录！");
//            Intent intent = new Intent(HistoryCarScoreActivity.this, LoginActivity.class);
//            intent.putExtra("token_out_of_time", "token_out_of_time");
//            startActivity(intent);
//
//            MyApplication.getInstance().exit();

        } else {
            mData = data;
            initListener();
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }

    public void toEmpty() {
        activityHistoryScoreProgress.showEmpty(getResources().getDrawable(R.drawable.load_no_data),
                Constant.EMPTY_TITLE_HISTORY_SCORE_TODAY, Constant.EMPTY_CONTEXT_HISTORY_SCORE_TODAY);
    }

    public void toError() {
        activityHistoryScoreProgress.showError(getResources().getDrawable(R.drawable.load_error),
                Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHistoryScoreProgress.showLoading();
                        //重试
                        mHistoryCarScorePresenter.LoadData("30", tel, BaseToken, mMQuery_data);
                    }
                });
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChartWeek.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
        mChartMounth.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
    }

    @Override
    public void onNothingSelected() {
    }
}
