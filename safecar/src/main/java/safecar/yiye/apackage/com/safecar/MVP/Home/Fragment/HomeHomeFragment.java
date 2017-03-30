package safecar.yiye.apackage.com.safecar.MVP.Home.Fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.SlideInEffect;
import com.xiaochao.lcrapiddeveloplibrary.container.DefaultHeader;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;
import safecar.yiye.apackage.com.safecar.Boss.Util.ToastUtil;
import safecar.yiye.apackage.com.safecar.MVP.Adapter.IndexCarAdapter;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseFragment;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.Activity.HistoryCarScoreActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.Activity.PersonInfoActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.Activity.SingleCarTodayActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.Presenter.HomeCarFragmentPresenter;
import safecar.yiye.apackage.com.safecar.MVP.Home.View.HomeHomeFragmentView;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;
import safecar.yiye.apackage.com.safecar.R;
import safecar.yiye.apackage.com.safecar.Widget.Dialog.ColorDialog;
import safecar.yiye.apackage.com.safecar.Widget.DotCircularRingView;


public class HomeHomeFragment extends BaseFragment implements
        HomeHomeFragmentView,
        SpringView.OnFreshListener,
        AdapterView.OnItemClickListener,
        View.OnClickListener {


    @BindView(R.id.fragment_index_menu)
    ImageView mFragmentIndexMenu;
    @BindView(R.id.fragment_index_message)
    ImageView mFragmentIndexMessage;
    @BindView(R.id.listview)
    JazzyListView mListview;
    @BindView(R.id.home_recommend_springview)
    SpringView mHomeRecommendSpringview;
    @BindView(R.id.home_recommend_progress)
    ProgressActivity mHomeRecommendProgress;
    @BindView(R.id.fragment_index_carname)
    TextView mFragmentIndexCarname;
    @BindView(R.id.fragment_index_carnumb)
    TextView mFragmentIndexCarnumb;
    private RelativeLayout mHeader;
    private RelativeLayout mTttt;
    private TextView mHeaderPicture;
    private DotCircularRingView mCircleView;
    private TextView mHeaderRefreshCar;
    private TextView mHeaderRefreshScore;
    private TextView mHeaderRefreshLevel;
    //    private TextView mHeaderRefresh;
    private View mView_head;
    private HomeCarFragmentPresenter mHomeCarFragmentPresenter;
    private HomeHomeCarBean mRes_data;
    private IndexCarAdapter mIndexCarAdapter;


    private int mActionBarHeight;
    private int mActionBarHeight_null;
    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    private int mMinHeaderTranslation_null;
    private AccelerateDecelerateInterpolator mSmoothInterpolator;
    private RectF mRect1 = new RectF();
    private RectF mRect2 = new RectF();
    public static int proressbar = 50 + new Random().nextInt(50);
    private ObjectAnimator anim;
    private int mAvageScore;
    private RelativeLayout relativeLayout;
    private Button mHeaderRefreshRule;
    private Button mHeaderRefreshHistory;
    private String BaseToken;
    private String token;


    public HomeHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initSaveState(Bundle savedInstanceState) {
    }

    private String tel = null;

    //加载视图
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        BaseToken = preferences.getString("token", "");
        tel = preferences.getString("phone", "");
        //当token值等于下面这个，就是表示游客登录
        if (BaseToken.equals("432F45B44C432414D2F97DF0E5743818")) {
            tel = "12345678910";
        } else {

        }
        return inflater.inflate(R.layout.fragment_home_home, container, false);
    }

    //设置监听
    @Override
    protected void initListener() {
        mHomeRecommendSpringview.setListener(this);
        mHomeRecommendSpringview.setHeader(new DefaultHeader(getContext()));

        //添加头布局  分数
        mView_head = View.inflate(getContext(), R.layout.fragment_home_home_head, null);
        //生成头文件的findviewbyid
        assignViews();
        mListview.addHeaderView(mView_head);
//        mListview.addFooterView(addFooter());


        mHeaderRefreshRule.setOnClickListener(this);
        mHeaderRefreshHistory.setOnClickListener(this);

    }

    private View addFooter() {
        View inflate = View.inflate(getContext(), R.layout.fragment_home_home_footer, null);
        return  inflate;
    }

    private void assignViews() {
        mHeader = (RelativeLayout) mView_head.findViewById(R.id.header);
        mTttt = (RelativeLayout) mView_head.findViewById(R.id.tttt);
        mHeaderPicture = (TextView) mView_head.findViewById(R.id.header_picture);
        mCircleView = (DotCircularRingView) mView_head.findViewById(R.id.circleView);
        mHeaderRefreshCar = (TextView) mView_head.findViewById(R.id.header_refresh_car);
        mHeaderRefreshScore = (TextView) mView_head.findViewById(R.id.header_refresh_score);
        mHeaderRefreshLevel = (TextView) mView_head.findViewById(R.id.header_refresh_level);
//        mHeaderRefresh = (TextView) mView_head.findViewById(R.id.header_refresh);
        relativeLayout = (RelativeLayout) mView_head.findViewById(R.id.header_refresh_rl);
        mHeaderRefreshRule = (Button) mView_head.findViewById(R.id.header_refresh_rule);
        mHeaderRefreshHistory = (Button) mView_head.findViewById(R.id.header_refresh_history);

    }

    //初始化数据
    @Override
    protected void initData() {
        mHomeCarFragmentPresenter = new HomeCarFragmentPresenter(this);

        mHomeCarFragmentPresenter.LoadData(tel, BaseToken);
    }

    @Override
    public void onRefresh() {
        mHomeCarFragmentPresenter.LoadData(tel, BaseToken);
        initRota();
    }

    @Override
    public void onLoadmore() {

    }

    //显示加载页
    @Override
    public void showProgress() {
        mHomeRecommendProgress.showLoading();
    }

    //关闭加载页
    @Override
    public void hideProgress() {
        mHomeRecommendProgress.showContent();
        initRota();
    }

    private static final int MAX_OVERSCROLL_Y = 150;
    private int newMaxOverScrollY;
    //加载新数据
    @Override
    public void newDatas(HomeHomeCarBean data) {

        if (data.getRes_code().equals("-2")) {

            showPopupWindowLogin();

        } else {

            SimpleDateFormat dateFormat_query = new SimpleDateFormat("yyyy.MM.dd", /*Locale.getDefault()*/Locale.ENGLISH);
            String mQuery_data = dateFormat_query.format(new Date()).toString();
            mFragmentIndexCarname.setText(mQuery_data);
            mFragmentIndexCarnumb.setText(data.getRes_data().size() + "辆车");

            mSmoothInterpolator = new AccelerateDecelerateInterpolator();
            mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
            mActionBarHeight = getResources().getDimensionPixelSize(R.dimen.header_actionbar);
            mActionBarHeight_null = getResources().getDimensionPixelSize(R.dimen.header_actionbar_null);

            mMinHeaderTranslation = -mHeaderHeight + mActionBarHeight;
            mMinHeaderTranslation_null = -mHeaderHeight + mActionBarHeight_null;

            mFragmentIndexMenu.setOnClickListener(this);
            mFragmentIndexMessage.setOnClickListener(this);

            mHeader.setClickable(false);

            //设置适配器
            mRes_data = data;
            mIndexCarAdapter = new IndexCarAdapter(getContext(), R.layout.fragment_home_home_head_listview, mRes_data);
            mListview.setAdapter(mIndexCarAdapter);
//        ReverseFlyEffect    SlideInEffect

            mListview.setTransitionEffect(new SlideInEffect());

            mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    View firstView = absListView.getChildAt(firstVisibleItem);

                    // 当firstVisibleItem是第0位。如果firstView==null说明列表为空，需要刷新;或者top==0说明已经到达列表顶部, 也需要刷新
                    if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
                        mHomeRecommendSpringview.setEnabled(true);
                        mHomeRecommendSpringview.setVisibility(View.VISIBLE);
                    } else {
                        mHomeRecommendSpringview.setEnabled(false);
                        mHomeRecommendSpringview.setVisibility(View.GONE);
                    }
                }
            });

            /**
             * 计算总分
             */
            //TODO 这里开始设置分数  6号
            int sum = 0;
            //没有分数的不计入总分内
            int noscore = 0;
            for (int i = 0; i < mRes_data.getRes_data().size(); i++) {
                String score = mRes_data.getRes_data().get(i).getSum_score();
                //处理分数字段为null的情况
                if (score == null) {
                    score = "0";
                    noscore++;
                }
                float parseInt = Float.parseFloat(score);
                sum += parseInt;
            }
//平均分数
            if (sum == 0) {
                mAvageScore = 0;
            } else {
                mAvageScore = sum / (mRes_data.getRes_data().size() - noscore);

            }



            DisplayMetrics metrics = MyApplication.getContext().getResources().getDisplayMetrics();
            float density = metrics.density;
            newMaxOverScrollY = (int) (density * MAX_OVERSCROLL_Y);

            mListview.setOnItemClickListener(this);
            mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    switch (scrollState) {
                        // 当不滚动时
                        case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                            // 判断滚动到底部
                            if (mListview.getLastVisiblePosition() == (mListview.getCount() - 1)) {
                                Log.d("判断到哪里了", "底部");
//                                mListview.isItemChecked(mListview.getCount() - 2);
//                                if (mListview.getCount() <= 5) {
//                                    mListview.setSelection(0);
//
//                                }else {
//                                    mListview.setSelection(mListview.getCount()-3);
//                                }
                            }
                            // 判断滚动到顶部

                            if (mListview.getFirstVisiblePosition() == 0) {
                                Log.d("判断到哪里了", "头部");
                            }

                            break;
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    int scrollY = getScrollY();

                    mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation_null));
//                //header_logo --> actionbar icon
                    float ratio = clamp(mHeader.getTranslationY() / mMinHeaderTranslation, 0.0f, 1.0f);
                    float ratio_null = clamp(mHeader.getTranslationY() / mMinHeaderTranslation_null, 0.0f, 1.0f);
//                //设置进度条图片变化的动画

                    interpolateProgressBar(mCircleView, mSmoothInterpolator.getInterpolation(ratio));
                    interpolateProgressBar_text(mHeaderRefreshCar, mSmoothInterpolator.getInterpolation(ratio));
                    interpolateProgressBar_text_level(mHeaderRefreshLevel, mSmoothInterpolator.getInterpolation(ratio));
                    interpolateProgressBar(mHeaderRefreshScore, mSmoothInterpolator.getInterpolation(ratio));
                    changeHistroyButtonAlpha(relativeLayout, mSmoothInterpolator.getInterpolation(ratio));
                    float clamp = clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F);
                    relativeLayout.setAlpha(1.0f-clamp);
                }
            });


        }
    }

    private void initRota() {
        anim = ObjectAnimator.ofFloat(mCircleView, "rotation", 0f, 360f);
        anim.setDuration(5000);
        anim.setRepeatCount(0);
        anim.setInterpolator(new LinearInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int n = proressbar - 30;
                if (n <= 1) {
                    n = 1;
                }
                int currentBarScore = new Random().nextInt(n);
                mCircleView.setProgress(0 + "%");
                mHeaderRefreshScore.setText(currentBarScore + "");
                mHeaderRefreshLevel.setText("火速计算中...");
            }
        });

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                mHeaderRefresh.setClickable(false);

                mCircleView.setProgress("0%");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                mHeaderRefresh.setClickable(true);

                mHeaderRefreshScore.setText(mAvageScore + "");
                mCircleView.setProgress(mAvageScore + "%");
                if (mAvageScore >= 90) {
                    mHeaderRefreshLevel.setText("优秀");
                } else if (mAvageScore >= 80 && mAvageScore < 90) {
                    mHeaderRefreshLevel.setText("良好");
                } else if (mAvageScore >= 60 && mAvageScore < 80) {
                    mHeaderRefreshLevel.setText("中等");
                } else {
                    mHeaderRefreshLevel.setText("较差");
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }

    public void fixListViewHeight(ListView listView) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        int i = 0;
        for (int index = 0, len = listAdapter.getCount(); i < len; i++) {
            View listViewItem = listAdapter.getView(index, null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight += listViewItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    //显示加载失败
    @Override
    public void showLoadFailMsg() {
        toError();
    }

    public void toError() {
        mHomeRecommendProgress.showError(getResources().getDrawable(R.drawable.load_error),
                Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHomeRecommendProgress.showLoading();
                        //重试
                        mHomeCarFragmentPresenter.LoadData(tel, BaseToken);
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position <= 0) {
//表示点击的是头布局
//        } else if (position == mListview.getCount()-1) {
        }
        else {
            String score = mRes_data.getRes_data().get(position - 1).getSum_score();
            //处理分数字段为null的情况 ,  不进入详情界面，直接弹出对话框
            if (score == null) {
                showPopupWindowNoScore();
                return;
            }
            //如果分数存在，就直接显示车辆详情
            Intent intent = new Intent(getActivity(), SingleCarTodayActivity.class);

            intent.putExtra("car_id", mRes_data.getRes_data().get(position - 1).getId());
            intent.putExtra("car_code", mRes_data.getRes_data().get(position - 1).getCar_code());

            String sum_mile = mRes_data.getRes_data().get(position - 1).getSum_mile();
            if (sum_mile == null) {
                sum_mile = "0.0";
            }
            DecimalFormat df = new DecimalFormat("######0.00");
            float parseFloat = Float.parseFloat(sum_mile);
            float mile_float = parseFloat / 1000f;
            String format = df.format(mile_float);
//            float mile = Float.parseFloat(format);
            intent.putExtra("car_today_distance", format);
            startActivity(intent);
        }
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    //设置进度条文字的动画
    private void interpolateProgressBar_text_level(View view1, float interpolation) {
        getOnScreenRect(mRect1, view1);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        //XY都往小地方走
        float translationX = 0.35F * (interpolation * (mRect2.left - 100 + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.465F * (interpolation * (mRect2.top + 100 + mRect2.bottom - mRect1.top - mRect1.bottom));

        if (scaleX <= 0.3f) {
            scaleX = 0.3f;
        }
        if (scaleY <= 0.3f) {
            scaleY = 0.3f;
        }

        float translationY_pinyi = translationY - mHeader.getTranslationY();

        view1.setTranslationY(translationY_pinyi);
        view1.setTranslationX(translationX);

        view1.setScaleX(scaleX);
        view1.setScaleY(scaleY);

        view1.setAlpha(1);

        if (translationY_pinyi >= 132.0f) {
            view1.setAlpha(0);
        }

    }

    //设置进度条文字的动画
    private void interpolateProgressBar_text(View view1, float interpolation) {
        getOnScreenRect(mRect1, view1);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        //XY都往小地方走
        float translationX = 0.35F * (interpolation * (mRect2.left - 100 + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.465F * (interpolation * (mRect2.top + 100 + mRect2.bottom - mRect1.top - mRect1.bottom));


        if (scaleX <= 0.3f) {
            scaleX = 0.3f;

        }
        if (scaleY <= 0.3f) {
            scaleY = 0.3f;

        }

        float translationY_pinyi = translationY - mHeader.getTranslationY();

        view1.setTranslationY(translationY_pinyi);
        view1.setTranslationX(translationX);

        view1.setScaleX(scaleX);
        view1.setScaleY(scaleY);

        view1.setAlpha(1);

        if (translationY_pinyi >= 319.0f) {
            view1.setAlpha(0);
        }
    }


    //改变历史评分  评分规则的alpha
    private void changeHistroyButtonAlpha(View view1, float interpolation) {
        getOnScreenRect(mRect1, view1);
        float translationY = 0.455F * (interpolation * (mRect2.top + 100 + mRect2.bottom - mRect1.top - mRect1.bottom));
        float translationY_pinyi = translationY - mHeader.getTranslationY();
        view1.setTranslationY(translationY_pinyi);
    }
    //设置进度条的动画
    private void interpolateProgressBar(View view1, float interpolation) {
        getOnScreenRect(mRect1, view1);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        //XY都往小地方走
        float translationX = 0.35F * (interpolation * (mRect2.left - 100 + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.465F * (interpolation * (mRect2.top + 100 + mRect2.bottom - mRect1.top - mRect1.bottom));

        if (scaleX <= 0.3f) {
            scaleX = 0.3f;
        }
        if (scaleY <= 0.3f) {
            scaleY = 0.3f;
        }

        float translationY_pinyi = translationY - mHeader.getTranslationY();

        view1.setTranslationX(translationX);
        view1.setTranslationY(translationY_pinyi);

        view1.setScaleX(scaleX);
        view1.setScaleY(scaleY);
    }

    //设置刷新按钮的动画
    private void interpolateRefresh(View view1, float interpolation) {
        getOnScreenRect(mRect1, view1);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        //XY都往小地方走
        float translationX = 0.1F * (interpolation * (mRect2.left - 100 + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.5F * (interpolation * (mRect2.top + 100 + mRect2.bottom - mRect1.top - mRect1.bottom));


        if (scaleX <= 0.8f) {
            scaleX = 1.0f;
        }
        if (scaleY <= 0.8f) {
            scaleY = 1.0f;
        }

        float translationY_pinyi = translationY - mHeader.getTranslationY();

        view1.setTranslationX(translationX);
        view1.setTranslationY(translationY_pinyi);

        view1.setScaleX(scaleX);
        view1.setScaleY(scaleY);
    }

    ///
    private RectF getOnScreenRect(RectF rect, View view) {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    public int getScrollY() {
        View c = mListview.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = mListview.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 10;//头尾相隔的距离
        if (firstVisiblePosition >= 1) {
            headerHeight = getHeadView().getHeight();
        }
        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    private View getHeadView() {
        //条目展示
        return getLayoutInflater(new Bundle()).inflate(R.layout.fragment_home_home_head, mListview, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_index_menu:
                Intent inten_pt = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(inten_pt);
                break;
            case R.id.fragment_index_message:
                break;
//历史评分
            case R.id.header_refresh_history:
                Intent inten_his = new Intent(getActivity(), HistoryCarScoreActivity.class);
                startActivity(inten_his);
                break;
//规则
            case R.id.header_refresh_rule:
                showPopupWindow(v);
                break;
        }
    }

    //评分规则弹框
    private void showPopupWindow(View v) {
//        // 一个自定义的布局，作为显示的内容
//        View contentView = LayoutInflater.from(getContext()).inflate(
//                R.layout.pop_window_rule, null);
//        final MaterialDialog mMaterialDialog = new MaterialDialog(getContext()).setView(contentView);
//
//        contentView.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
//            //
//            public void onClick(View v) {
//                mMaterialDialog.dismiss();
//            }
//        });
//        mMaterialDialog.setCanceledOnTouchOutside(true);
//        mMaterialDialog.show();


        ColorDialog dialog = new ColorDialog(getContext());
        dialog.setColor("#000");
        dialog.setAnimationEnable(true);
        dialog.setTitle("提示");
        dialog.setContentText("1，车队总分为100分，车辆总分为100分。\n2，车队总分为所有车辆的平均分。\n3，如何提高分数?控制行驶速度，减少三急行为，避免疲劳驾驶。");

        dialog.setPositiveListener("知道了", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.cancel();
            }
        }).show();
    }

    //无信号弹框
    private void showPopupWindowNoScore() {

        ColorDialog dialog = new ColorDialog(getContext());
        dialog.setColor("#000");
        dialog.setAnimationEnable(true);
        dialog.setTitle("提示");
        dialog.setContentText("该车辆当日无信号，请尽快确认车辆情况！如该车辆已经下降，请尽快联系后台。" +
                "\n1861696993");

        dialog.setPositiveListener("知道了", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.cancel();
            }
        }).show();
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
                SharedPreferences preferences=getActivity().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("token_out_of_time", "token_out_of_time");
                getActivity().startActivity(intent);
//                MyApplication.getInstance().exit();
            }
        }).show();
    }
}