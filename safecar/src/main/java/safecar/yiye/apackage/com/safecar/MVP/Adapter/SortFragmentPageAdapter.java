package safecar.yiye.apackage.com.safecar.MVP.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Home.Fragment.SortNextFragment;
import safecar.yiye.apackage.com.safecar.MVP.Home.Fragment.SortNowFragment;

/**
 * Name: SortFragmentPageAdapter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 19:08
 */
public class SortFragmentPageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public SortFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            if(mFragments.get(position) == null){
                return new SortNowFragment();
            }
            return mFragments.get(position);
        }else if(position ==1){
            if(mFragments.get(position) == null){
                return new SortNextFragment();
            }
            return mFragments.get(position);
        }
        return null;
    }
    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
