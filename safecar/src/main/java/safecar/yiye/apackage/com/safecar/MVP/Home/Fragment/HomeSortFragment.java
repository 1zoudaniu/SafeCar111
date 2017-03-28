package safecar.yiye.apackage.com.safecar.MVP.Home.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Adapter.SortFragmentPageAdapter;
import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSortFragment extends Fragment {

    private ViewPager viewPager;

    private TabLayout tabLayout;
    private View view;
    private SortNowFragment mSortNowFragment;
    private SortNextFragment mSortNextFragment;
    private HomeTabActivity mHomeTabActivity;


    public HomeSortFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }

        view = inflater.inflate(R.layout.fragment_home_sort, container, false);

        mSortNowFragment = new SortNowFragment();
        mSortNextFragment = new SortNextFragment();


        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        mHomeTabActivity=(HomeTabActivity) getActivity();

        initViewPager();
        return view;
    }

    private void initViewPager() {

        List<String> titles = new ArrayList<>();
        titles.add("当月");
        titles.add("上月");
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mSortNowFragment);
        fragments.add(mSortNextFragment);

        SortFragmentPageAdapter sortFragmentPageAdapter = new SortFragmentPageAdapter(mHomeTabActivity.getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(sortFragmentPageAdapter);
        viewPager.setCurrentItem(0);
        //将TabLayout和ViewPager关联起来。
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setTabsFromPagerAdapter(sortFragmentPageAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
