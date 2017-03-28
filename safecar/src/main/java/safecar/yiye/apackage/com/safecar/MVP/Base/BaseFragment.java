package safecar.yiye.apackage.com.safecar.MVP.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;

/**
 * Name: BaseFragment
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 14:46
 */
public abstract class BaseFragment extends Fragment {
    private View mRootView;
    private Context mContent;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContent = MyApplication.getContext();
        mRootView = initView(inflater,container);
        ButterKnife.bind(this, mRootView);//绑定到butterknife
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSaveState(savedInstanceState);
        initListener();
        initData();
    }

    protected abstract void initSaveState(Bundle savedInstanceState);

    protected abstract View initView(LayoutInflater inflater,ViewGroup container);
    protected abstract void initListener();
    protected abstract void initData();
}
