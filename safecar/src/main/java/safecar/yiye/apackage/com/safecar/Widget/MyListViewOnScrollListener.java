package safecar.yiye.apackage.com.safecar.Widget;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;

import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

/**
 * Name: MyListViewOnScrollListener
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-28 10:48
 */
public  class MyListViewOnScrollListener implements AbsListView.OnScrollListener {

    private SpringView mSwipeView;
    private AbsListView.OnScrollListener mOnScrollListener;

    public MyListViewOnScrollListener(SpringView swipeView) {
        mSwipeView = swipeView;
    }

    public MyListViewOnScrollListener(SpringView swipeView,
                                      AbsListView.OnScrollListener onScrollListener) {
        mSwipeView = swipeView;
        mOnScrollListener = onScrollListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        View firstView = absListView.getChildAt(firstVisibleItem);

        // 当firstVisibleItem是第0位。如果firstView==null说明列表为空，需要刷新;或者top==0说明已经到达列表顶部, 也需要刷新
        if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
            mSwipeView.setEnabled(true);
            Log.d("开始打印了","mSwipeView.setEnabled(true);");
        } else {
            mSwipeView.setEnabled(false);
            Log.d("开始打印了","mSwipeView.setEnabled(false);");
        }
        if (null != mOnScrollListener) {
            mOnScrollListener.onScroll(absListView, firstVisibleItem,
                    visibleItemCount, totalItemCount);
        }
    }
}
