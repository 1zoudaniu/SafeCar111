package safecar.yiye.apackage.com.safecar.MVP.Home.Model;

/**
 * Name: OnLoadDataListListener
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:24
 */
public  interface OnLoadDataListListener<T> {
    void onSuccess(T data);
    void onFailure(Throwable e);
}
