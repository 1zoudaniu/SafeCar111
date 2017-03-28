package safecar.yiye.apackage.com.safecar.MVP.Entity;

/**
 * Name: HttpResultHome
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-24 10:09
 */
public class HttpResultHome<T> {
    public String res_msg;
    public String res_code;
    public T res_data;
    public HttpResultHome(String res_msg, T res_data, String res_code) {
        this.res_msg = res_msg;
        this.res_data = res_data;
        this.res_code = res_code;
    }
    public String getRes_msg() {
        return res_msg;
    }
    public void setRes_msg(String res_msg) {
        this.res_msg = res_msg;
    }
    public T getRes_data() {
        return res_data;
    }
    public void setRes_data(T res_data) {
        this.res_data = res_data;
    }
    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }
    public String getRes_code() {
        return res_code;
    }
}
