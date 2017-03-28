package safecar.yiye.apackage.com.safecar.Boss.Data.Retrofit;

import safecar.yiye.apackage.com.safecar.MVP.Entity.HttpResult;

/**
 * Name: ApiException
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:56
 * 异常类
 */
public class ApiException extends RuntimeException  {
    public ApiException(HttpResult httpResult) {
        this(getApiExceptionMessage(httpResult));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     */
    private static String getApiExceptionMessage(HttpResult httpResult){
//        1,成功，-1参数不全，0异常
        String message = "";
        switch (httpResult.getCode()) {
            case 0:
                message = "ERROR:异常";
                break;
            case -1:
                message = "ERROR:参数不全";
                break;
            case 1:
                message = "ERROR:成功";
                break;
            case -2:
                message = "ERROR:token失效";
                break;
            default:
                message = "ERROR:未知错误ApiException";

        }
        return message;
    }
}
