package safecar.yiye.apackage.com.safecar.MVP.Home.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.MVP.Adapter.SortNowLeftAdapter;
import safecar.yiye.apackage.com.safecar.MVP.Adapter.SortNowRightAdapter;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseFragment;
import safecar.yiye.apackage.com.safecar.MVP.Entity.SortBean;
import safecar.yiye.apackage.com.safecar.R;
import safecar.yiye.apackage.com.safecar.Boss.Util.JsonUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortNowFragment extends BaseFragment {


    @BindView(R.id.lv_left_first)
    ListView mLvLeftFirst;
    @BindView(R.id.expendlist_first)
    ExpandableListView mExpendlistFirst;

    private ArrayList<SortBean.DataBean.CategoriesBean> dataRightBeen;
    private ArrayList<String> dataLeftBeen;
    private SortNowLeftAdapter mSortNowLeftAdapter;
    private SortNowRightAdapter mSortNowRightAdapter;
    private SortBean mSortBean;
    private int first=0;

    public SortNowFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_sort_now, container, false);
    }

    @Override
    protected void initListener() {



        /** 点击左边ListView重定位右边psListView中数据**/
        mLvLeftFirst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                pslvRight.setSelection(Integer.parseInt(alLeft.get(position).info));
//                posi = position;
                mSortNowLeftAdapter.setSelectedPosition(position);
                mSortNowLeftAdapter.notifyDataSetInvalidated();

                List<SortBean.DataBean.CategoriesBean> categories = mSortBean.getData().get(position).getCategories();
                initRightData(categories);
                mSortNowRightAdapter.notifyDataSetChanged();
            }
        });
        /**得到左边ListView第一列的位置（显示左边ListView被选中但被隐藏的Item时用）**/
        mLvLeftFirst.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                first=firstVisibleItem;
            }
        });

    }

    @Override
    protected void initData() {
        dataLeftBeen = new ArrayList<>();
        dataRightBeen = new ArrayList<>();
        String json = JsonUtils.getJson(getContext(), "safebox.json");
        Gson gson = new Gson();
        mSortBean = gson.fromJson(json, SortBean.class);
        List<SortBean.DataBean> data = mSortBean.getData();

        dataLeftBeen.clear();
        for (int i = 0; i < data.size(); i++) {
            String cname = data.get(i).getCname();
            dataLeftBeen.add(cname);
        }

        mSortNowLeftAdapter = new SortNowLeftAdapter(getContext(), dataLeftBeen);
        mLvLeftFirst.setAdapter(mSortNowLeftAdapter);


        List<SortBean.DataBean.CategoriesBean> categories = data.get(0).getCategories();
        initRightData(categories);
    }
    private void initRightData(List<SortBean.DataBean.CategoriesBean> dataBean) {
        dataRightBeen.clear();
        dataRightBeen.addAll(dataBean);

        mSortNowRightAdapter = new SortNowRightAdapter(getContext(), dataRightBeen);
        mExpendlistFirst.setAdapter(mSortNowRightAdapter);
        //去掉指示图片
        mExpendlistFirst.setGroupIndicator(null);
        //遍历所有group,将所有项设置成默认展开
        int count = mExpendlistFirst.getCount();
        for (int i=0; i<count; i++)
        {
            mExpendlistFirst.expandGroup(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initSaveState(Bundle savedInstanceState) {

    }
}
