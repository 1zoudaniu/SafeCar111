package safecar.yiye.apackage.com.safecar.Widget;


import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;

/**
 * Name: MyListview
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-28 09:56
 */

public class PtrListViewOnScrollListener{}
//if (listView != null) {
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//@Override
//public void onScrollStateChanged(AbsListView view, int scrollState) {
//        switch (scrollState) {
//        case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
//        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
//        mOnSwipeRefreshListener.onLoad();
//        listView.setSelection(listView.getCount());
//
//        }
//        break;
//        }
//        }
//
//@Override
//public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        }
//        });
//        }
//        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
//        在这里做你的加载事件

//        implements AbsListView.OnScrollListener {}
//
//    private ImageLoader imageLoader;
//    //
//    public PtrHTFrameLayout mPtrLayout;
//
//    private final boolean pauseOnScroll;
//
//    private final boolean pauseOnFling;
//
//    private final AbsListView.OnScrollListener externalListener;
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        switch (scrollState) {
//            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
//                imageLoader.resume();
//                break;
//            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
//                if (pauseOnScroll) {
//                    imageLoader.pause();
//                }
//                break;
//            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
//                if (pauseOnFling) {
//                    imageLoader.pause();
//                }
//                break;
//        }
//        if (externalListener != null) {
//            externalListener.onScrollStateChanged(view, scrollState);
//        }
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        //解决listView滑动与下拉刷新冲突问题
//// 当firstVisibleItem是第0位。如果firstView==null说明列表为空，
//// 需要刷新;或者top==0说明已经到达列表顶部, 也需要刷新
//        View firstView = view.getChildAt(firstVisibleItem);
//        if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
//            mPtrLayout.setEnabled(true);
//        } else {
//            mPtrLayout.setEnabled(false);
//        }
//        if (externalListener != null) {
//            externalListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
//        }
//    }
//}



//public class PtrListViewOnScrollListener  implements  AbsListView.OnScrollListener{}

//如果集成了异步加载网络图片框架，需要加入ImageLoader，具体使用好处是：
// ImageLoader#pause() pause ImageLoader's tasks} while list view
// is scrolling (touch scrolling and/or * fling). It prevents redundant loadings.
// 不需要的话直接在构造方法中去掉即可
//
//private ImageLoader imageLoader;
//
//public PtrHTFrameLayout mPtrLayout;
//
//private final booleanpause OnScroll;
//
//private final booleanpause  OnFling;
//
//private final AbsListView.OnScrollListener  externalListener;
//
//public PtrListViewOnScrollListener(PtrHTFrameLayout mPtrLayout,ImageLoader imageLoader,boolean pauseOnScroll,boolean pauseOnFling){
//
//        this(mPtrLayout,imageLoader,pauseOnScroll,pauseOnFling,null);
//
//        }
//
//public PtrListViewOnScrollListener(PtrHTFrameLayout mPtrLayout,ImageLoader imageLoader,boolean pauseOnScroll,boolean pauseOnFling,AbsListView.OnScrollListener customListener){
//
//        this.mPtrLayout=mPtrLayout;
//
//        this.imageLoader=imageLoader;
//
//        this.pauseOnScroll=pauseOnScroll;
//
//        this.pauseOnFling=pauseOnFling;
//
//        externalListener=customListener;
//
//        }
//
//@Override
//
//public void  onScrollStateChanged(AbsListView view,intscrollState){
//
//        switch(scrollState){
//
//        case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
//
//        imageLoader.resume();
//
//        break;
//
//        case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
//
//        if(pauseOnScroll){
//
//        imageLoader.pause();
//
//        }
//
//        break;
//
//        case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
//
//        if(pauseOnFling){
//
//        imageLoader.pause();
//
//        }
//
//        break;
//
//        }
//
//        if(externalListener!=null){
//
//        externalListener.onScrollStateChanged(view,scrollState);
//
//        }
//
//        }
//
//@Override
//
//public void onScroll(AbsListView view,intfirstVisibleItem,intvisibleItemCount,inttotalItemCount){
//
////解决listView滑动与下拉刷新冲突问题
//
//// 当firstVisibleItem是第0位。如果firstView==null说明列表为空，
//
//// 需要刷新;或者top==0说明已经到达列表顶部, 也需要刷新
//
//        View firstView=view.getChildAt(firstVisibleItem);
//
//        if(firstVisibleItem==0&&(firstView==null||firstView.getTop()==0)){
//
//        mPtrLayout.setEnabled(true);
//
//        }else{
//
//        mPtrLayout.setEnabled(false);
//
//        }
//
//        if(externalListener!=null){
//
//        externalListener.onScroll(view,firstVisibleItem,visibleItemCount,totalItemCount);
//
//        }
//
//        }
//
//        }

//        代码中的使用：
//
//private PtrListViewOnScrollListener  ptrListViewOnScrollListener;
//
//        ptrListViewOnScrollListener=new PtrListViewOnScrollListener(mPtrLayout,MyApplication.imageLoader,true,false);
//
//        listView.setOnScrollListener(ptrListViewOnScrollListener);

//结束

//    文／西貝武（简书作者）
//    原文链接：http://www.jianshu.com/p/c507cb2d97aa
//    著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
//        }
