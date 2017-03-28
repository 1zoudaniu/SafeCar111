package safecar.yiye.apackage.com.safecar.MVP.Home.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Adapter.DetailFragmentPageAdapter;
import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeDetailFragment extends Fragment {

    private ViewPager viewPager;

    private TabLayout tabLayout;
    private View view;
    private HomeTabActivity mHomeTabActivity;
    private DetailNowFragment mDetailNowFragment;
    private DetailNextFragment mDetailNextFragment;


    public HomeDetailFragment() {
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

        view = inflater.inflate(R.layout.fragment_home_detail, container, false);

        mDetailNowFragment = new DetailNowFragment();
        mDetailNextFragment = new DetailNextFragment();

        viewPager = (ViewPager) view.findViewById(R.id.viewpager_guiji);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_guiji);

        mHomeTabActivity=(HomeTabActivity) getActivity();

        initViewPager();
        return view;
    }

    private void initViewPager() {

        List<String> titles = new ArrayList<>();
        titles.add("现在");
        titles.add("历史");
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mDetailNowFragment);
        fragments.add(mDetailNextFragment);

        DetailFragmentPageAdapter sortFragmentPageAdapter = new DetailFragmentPageAdapter(mHomeTabActivity.getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(sortFragmentPageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(0);
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
