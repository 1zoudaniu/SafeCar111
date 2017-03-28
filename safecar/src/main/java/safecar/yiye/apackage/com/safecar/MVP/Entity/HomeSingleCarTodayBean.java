package safecar.yiye.apackage.com.safecar.MVP.Entity;

import java.util.List;

/**
 * Name: HomeSingleCarTodayBean
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-24 15:51
 */
public class HomeSingleCarTodayBean {

    private String res_msg;
    private String res_data;
    private String res_code;
    private String resSum;
    private List<ResFenbuBean> res_fenbu;

    public String getResSum() {
        return resSum;
    }

    public void setResSum(String resSum) {
        this.resSum = resSum;
    }

    private List<ResExpectionBean> res_expection;

    public void setRes_msg(String res_msg) {
        this.res_msg = res_msg;
    }

    public void setRes_data(String res_data) {
        this.res_data = res_data;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public void setRes_fenbu(List<ResFenbuBean> res_fenbu) {
        this.res_fenbu = res_fenbu;
    }

    public void setRes_expection(List<ResExpectionBean> res_expection) {
        this.res_expection = res_expection;
    }

    public String getRes_msg() {
        return res_msg;
    }

    public String getRes_data() {
        return res_data;
    }

    public String getRes_code() {
        return res_code;
    }

    public List<ResFenbuBean> getRes_fenbu() {
        return res_fenbu;
    }

    public List<ResExpectionBean> getRes_expection() {
        return res_expection;
    }

    public static class ResFenbuBean {
        /**
         * dt : 1481505948000
         * flag : 1
         * device_id : 08100012
         * latitude : 31.358877
         * vehicle_code : 沪DB4616
         * speed : 0
         * longitude : 121.542434
         */

        private long dt;
        private int flag;
        private String device_id;
        private double latitude;
        private String vehicle_code;
        private double speed;
        private double longitude;

        public void setDt(long dt) {
            this.dt = dt;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setVehicle_code(String vehicle_code) {
            this.vehicle_code = vehicle_code;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public long getDt() {
            return dt;
        }

        public int getFlag() {
            return flag;
        }

        public String getDevice_id() {
            return device_id;
        }

        public double getLatitude() {
            return latitude;
        }

        public String getVehicle_code() {
            return vehicle_code;
        }

        public double getSpeed() {
            return speed;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    public static class ResExpectionBean {

        /**
         * score : 0.5
         * datestr : 2016/12/12
         * device_id : 08100012
         * latitude : 31.31666136497345
         * pro : 超速
         * data_date : 1481511084000
         * longitude : 121.28441932308175
         */

        private String score;
        private String datestr;
        private String device_id;
        private String latitude;
        private String pro;
        private long data_date;
        private String longitude;

        public void setScore(String score) {
            this.score = score;
        }

        public void setDatestr(String datestr) {
            this.datestr = datestr;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setPro(String pro) {
            this.pro = pro;
        }

        public void setData_date(long data_date) {
            this.data_date = data_date;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getScore() {
            return score;
        }

        public String getDatestr() {
            return datestr;
        }

        public String getDevice_id() {
            return device_id;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getPro() {
            return pro;
        }

        public long getData_date() {
            return data_date;
        }

        public String getLongitude() {
            return longitude;
        }
    }


//    private String res_msg;
//    private String res_data;
//    private String res_code;
//    private List<ResFenbuBean> res_fenbu;
//    private List<ResExpectionBean> res_expection;
//
//    public void setRes_msg(String res_msg) {
//        this.res_msg = res_msg;
//    }
//
//    public void setRes_data(String res_data) {
//        this.res_data = res_data;
//    }
//
//    public void setRes_code(String res_code) {
//        this.res_code = res_code;
//    }
//
//    public void setRes_fenbu(List<ResFenbuBean> res_fenbu) {
//        this.res_fenbu = res_fenbu;
//    }
//
//    public void setRes_expection(List<ResExpectionBean> res_expection) {
//        this.res_expection = res_expection;
//    }
//
//    public String getRes_msg() {
//        return res_msg;
//    }
//
//    public String getRes_data() {
//        return res_data;
//    }
//
//    public String getRes_code() {
//        return res_code;
//    }
//
//    public List<ResFenbuBean> getRes_fenbu() {
//        return res_fenbu;
//    }
//
//    public List<ResExpectionBean> getRes_expection() {
//        return res_expection;
//    }
//
//    public static class ResFenbuBean {
//        /**
//         * dt : 1480959602000
//         * flag : 1
//         * device_id : 08100013
//         * latitude : 121.433909
//         * vehicle_code : 沪DF2472
//         * speed : 0
//         * longitude : 31.388985
//         */
//
//        private long dt;
//        private int flag;
//        private String device_id;
//        private double latitude;
//        private String vehicle_code;
//        private double speed;
//        private double longitude;
//
//        public void setDt(long dt) {
//            this.dt = dt;
//        }
//
//        public void setFlag(int flag) {
//            this.flag = flag;
//        }
//
//        public void setDevice_id(String device_id) {
//            this.device_id = device_id;
//        }
//
//        public void setLatitude(double latitude) {
//            this.latitude = latitude;
//        }
//
//        public void setVehicle_code(String vehicle_code) {
//            this.vehicle_code = vehicle_code;
//        }
//
//        public void setSpeed(double speed) {
//            this.speed = speed;
//        }
//
//        public void setLongitude(double longitude) {
//            this.longitude = longitude;
//        }
//
//        public long getDt() {
//            return dt;
//        }
//
//        public int getFlag() {
//            return flag;
//        }
//
//        public String getDevice_id() {
//            return device_id;
//        }
//
//        public double getLatitude() {
//            return latitude;
//        }
//
//        public String getVehicle_code() {
//            return vehicle_code;
//        }
//
//        public double getSpeed() {
//            return speed;
//        }
//
//        public double getLongitude() {
//            return longitude;
//        }
//    }
//
//    public static class ResExpectionBean {
//
//        /**
//         * dt : 1480965598000
//         * item : 左急转
//         * device_id : 08100013
//         * e_code : 411
//         * X : 31.375823
//         * Y : 121.513506
//         * car_code : 沪DF2472
//         * speed : 25
//         * points : 0.50
//         */
//
//        private long dt;
//        private String item;
//        private String device_id;
//        private String e_code;
//        private double X;
//        private double Y;
//        private String car_code;
//        private int speed;
//        private String points;
//
//        public void setDt(long dt) {
//            this.dt = dt;
//        }
//
//        public void setItem(String item) {
//            this.item = item;
//        }
//
//        public void setDevice_id(String device_id) {
//            this.device_id = device_id;
//        }
//
//        public void setE_code(String e_code) {
//            this.e_code = e_code;
//        }
//
//        public void setX(double X) {
//            this.X = X;
//        }
//
//        public void setY(double Y) {
//            this.Y = Y;
//        }
//
//        public void setCar_code(String car_code) {
//            this.car_code = car_code;
//        }
//
//        public void setSpeed(int speed) {
//            this.speed = speed;
//        }
//
//        public void setPoints(String points) {
//            this.points = points;
//        }
//
//        public long getDt() {
//            return dt;
//        }
//
//        public String getItem() {
//            return item;
//        }
//
//        public String getDevice_id() {
//            return device_id;
//        }
//
//        public String getE_code() {
//            return e_code;
//        }
//
//        public double getX() {
//            return X;
//        }
//
//        public double getY() {
//            return Y;
//        }
//
//        public String getCar_code() {
//            return car_code;
//        }
//
//        public int getSpeed() {
//            return speed;
//        }
//
//        public String getPoints() {
//            return points;
//        }
//    }
}
