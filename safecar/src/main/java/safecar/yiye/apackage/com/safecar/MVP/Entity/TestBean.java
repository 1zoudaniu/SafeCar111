package safecar.yiye.apackage.com.safecar.MVP.Entity;

import java.util.List;

/**
 * Name: TestBean
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-12-06 14:18
 */
public class TestBean {

    @Override
    public String toString() {
        return "TestBean{" +
                "res_data='" + res_data + '\'' +
                ", res_fenbu=" + res_fenbu +
                ", res_expection=" + res_expection +
                '}';
    }

    /**
     * res_fenbu : [{"dt":1480954531000,"flag":1,"device_id":"08100015","latitude":117.807636,"vehicle_code":"测A15432","speed":6.8524,"longitude":30.920229},{"dt":1480954810000,"flag":1,"device_id":"08100015","latitude":117.812173,"vehicle_code":"测A15432","speed":63.8754,"longitude":30.932014}]
     * res_expection : [{"dt":1480954812000,"item":"速度波动","device_id":"08100015","car_code":"测A15432","points":"0.50"},{"dt":1480954931000,"item":"超速","device_id":"08100015","car_code":"测A15432","points":"0.50"},{"dt":1480954932000,"item":"速度波动","device_id":"08100015","car_code":"测A15432","points":"0.50"}]
     * res_data : 08100015~#~117.81173424895401~#~30.931892712399346~#~2016-12-06 14:11:00~#~安徽省铜陵市铜官区淮河大道北段555号安徽铜都国际大酒店~#~0~#~0.00~#~239.97
     */

    private String res_data;
    private List<ResFenbuBean> res_fenbu;
    private List<ResExpectionBean> res_expection;

    public void setRes_data(String res_data) {
        this.res_data = res_data;
    }

    public void setRes_fenbu(List<ResFenbuBean> res_fenbu) {
        this.res_fenbu = res_fenbu;
    }

    public void setRes_expection(List<ResExpectionBean> res_expection) {
        this.res_expection = res_expection;
    }

    public String getRes_data() {
        return res_data;
    }

    public List<ResFenbuBean> getRes_fenbu() {
        return res_fenbu;
    }

    public List<ResExpectionBean> getRes_expection() {
        return res_expection;
    }

    public static class ResFenbuBean {
        /**
         * dt : 1480954531000
         * flag : 1
         * device_id : 08100015
         * latitude : 117.807636
         * vehicle_code : 测A15432
         * speed : 6.8524
         * longitude : 30.920229
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

        @Override
        public String toString() {
            return "ResFenbuBean{" +
                    "dt=" + dt +
                    ", flag=" + flag +
                    ", device_id='" + device_id + '\'' +
                    ", latitude=" + latitude +
                    ", vehicle_code='" + vehicle_code + '\'' +
                    ", speed=" + speed +
                    ", longitude=" + longitude +
                    '}';
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
         * dt : 1480954812000
         * item : 速度波动
         * device_id : 08100015
         * car_code : 测A15432
         * points : 0.50
         */

        private long dt;
        private String item;
        private String device_id;
        private String car_code;
        private String points;

        public void setDt(long dt) {
            this.dt = dt;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public void setCar_code(String car_code) {
            this.car_code = car_code;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public long getDt() {
            return dt;
        }

        public String getItem() {
            return item;
        }

        @Override
        public String toString() {
            return "ResExpectionBean{" +
                    "dt=" + dt +
                    ", item='" + item + '\'' +
                    ", device_id='" + device_id + '\'' +
                    ", car_code='" + car_code + '\'' +
                    ", points='" + points + '\'' +
                    '}';
        }

        public String getDevice_id() {
            return device_id;
        }

        public String getCar_code() {
            return car_code;
        }

        public String getPoints() {
            return points;
        }
    }
}
