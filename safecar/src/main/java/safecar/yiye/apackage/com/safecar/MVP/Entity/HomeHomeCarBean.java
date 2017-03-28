package safecar.yiye.apackage.com.safecar.MVP.Entity;

import java.util.List;

/**
 * Name: HomeHomeCarBean
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:31
 */
public class HomeHomeCarBean {
    /**
     * res_msg : 收消息成功
     * res_data : [{"sum_score":"56.5","currMap":{"lastTime":"2017-02-24 23:42:00","lng":"121.43334169402355","hxjiao":"-189279.73","poi":"上海市宝山区杨行镇","lat":"31.389738756073136","speed":"0.00","vehicleStatus":"3"},"datestr":"2017/03/02","device_id":"08100016","sum_mile":"233844.01847222212","name":"测试公司","car_code":"沪DB4592","id":"08100016","data_date":1488451872000},{"sum_score":"55.5","currMap":{"lastTime":"2017-02-18 15:30:37","lng":"120.29037041878385","hxjiao":"320.48","poi":"江苏省无锡市惠山区堰桥街道S48沪宜高速","lat":"31.65992976146143","speed":"38.02","vehicleStatus":"3"},"datestr":"2017/03/02","device_id":"08100013","sum_mile":"274434.4568611112","name":"测试公司","car_code":"沪DF2472","id":"08100013","data_date":1488451873000},{"sum_score":"55.0","currMap":{"lastTime":"2017-03-01 00:49:00","lng":"121.73436887775159","hxjiao":"177.77","poi":"上海市浦东新区合庆镇合庆服务区","lat":"31.223919991775915","speed":"0.00","vehicleStatus":"3"},"datestr":"2017/03/02","device_id":"08100012","sum_mile":"361569.99244444433","name":"测试公司","car_code":"沪DB4616","id":"08100012","data_date":1488451874000},{"sum_score":"95.0","currMap":{"lastTime":"2017-03-01 00:47:00","lng":"121.58136551244674","hxjiao":"0.00","poi":"上海市浦东新区周浦镇康沈路2598号东丰林居","lat":"31.096312382531906","speed":"0.00","vehicleStatus":"3"},"datestr":"2017/03/02","device_id":"08100015","sum_mile":"0.0","name":"测试公司","car_code":"测A15432","id":"08100015","data_date":1488451875000},{"currMap":{"lastTime":"2016-12-04 10:22:39","lng":"121.61165346321017","hxjiao":"80.96","poi":"上海市浦东新区张江高科技园区张江路","lat":"31.2100843561326","speed":"0.00","vehicleStatus":"3"},"name":"测试公司","car_code":"沪DD0961","id":"08100018"}]
     * res_code : 1
     */

    private String res_msg;
    private String res_code;
    private List<ResDataBean> res_data;

    public String getRes_msg() {
        return res_msg;
    }

    public void setRes_msg(String res_msg) {
        this.res_msg = res_msg;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public List<ResDataBean> getRes_data() {
        return res_data;
    }

    public void setRes_data(List<ResDataBean> res_data) {
        this.res_data = res_data;
    }

    public static class ResDataBean {
        /**
         * sum_score : 56.5
         * currMap : {"lastTime":"2017-02-24 23:42:00","lng":"121.43334169402355","hxjiao":"-189279.73","poi":"上海市宝山区杨行镇","lat":"31.389738756073136","speed":"0.00","vehicleStatus":"3"}
         * datestr : 2017/03/02
         * device_id : 08100016
         * sum_mile : 233844.01847222212
         * name : 测试公司
         * car_code : 沪DB4592
         * id : 08100016
         * data_date : 1488451872000
         */

        private String sum_score;
        private CurrMapBean currMap;
        private String datestr;
        private String device_id;
        private String sum_mile;
        private String name;
        private String car_code;
        private String id;
        private long data_date;

        public String getSum_score() {
            return sum_score;
        }

        public void setSum_score(String sum_score) {
            this.sum_score = sum_score;
        }

        public CurrMapBean getCurrMap() {
            return currMap;
        }

        public void setCurrMap(CurrMapBean currMap) {
            this.currMap = currMap;
        }

        public String getDatestr() {
            return datestr;
        }

        public void setDatestr(String datestr) {
            this.datestr = datestr;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getSum_mile() {
            return sum_mile;
        }

        public void setSum_mile(String sum_mile) {
            this.sum_mile = sum_mile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCar_code() {
            return car_code;
        }

        public void setCar_code(String car_code) {
            this.car_code = car_code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getData_date() {
            return data_date;
        }

        public void setData_date(long data_date) {
            this.data_date = data_date;
        }

        public static class CurrMapBean {
            /**
             * lastTime : 2017-02-24 23:42:00
             * lng : 121.43334169402355
             * hxjiao : -189279.73
             * poi : 上海市宝山区杨行镇
             * lat : 31.389738756073136
             * speed : 0.00
             * vehicleStatus : 3
             */

            private String lastTime;
            private String lng;
            private String hxjiao;
            private String poi;
            private String lat;
            private String speed;
            private String vehicleStatus;

            public String getLastTime() {
                return lastTime;
            }

            public void setLastTime(String lastTime) {
                this.lastTime = lastTime;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getHxjiao() {
                return hxjiao;
            }

            public void setHxjiao(String hxjiao) {
                this.hxjiao = hxjiao;
            }

            public String getPoi() {
                return poi;
            }

            public void setPoi(String poi) {
                this.poi = poi;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getSpeed() {
                return speed;
            }

            public void setSpeed(String speed) {
                this.speed = speed;
            }

            public String getVehicleStatus() {
                return vehicleStatus;
            }

            public void setVehicleStatus(String vehicleStatus) {
                this.vehicleStatus = vehicleStatus;
            }
        }
    }
//    /**
//     * sum_score : 48.5
//     * currMap : {"lastTime":"2016-12-14 06:28:46","lng":"121.86429220966414","hxjiao":"309.75","poi":"上海市浦东新区泥城镇S2沪芦高速","lat":"30.898028742347996","speed":"62.47","vehicleStatus":"3"}
//     * datestr : 2016/12/21
//     * device_id : 08100018
//     * sum_mile : 305057.5603055555
//     * name : 测试公司
//     * car_code : 沪DD0961
//     * id : 08100018
//     * data_date : 1482317456000
//     */
//
//    private String sum_score;
//    private CurrMapBean currMap;
//    private String datestr;
//    private String device_id;
//    private String sum_mile;
//    private String name;
//    private String car_code;
//    private String id;
//    private long data_date;
//
//    public void setSum_score(String sum_score) {
//        this.sum_score = sum_score;
//    }
//
//    public void setCurrMap(CurrMapBean currMap) {
//        this.currMap = currMap;
//    }
//
//    public void setDatestr(String datestr) {
//        this.datestr = datestr;
//    }
//
//    public void setDevice_id(String device_id) {
//        this.device_id = device_id;
//    }
//
//    public void setSum_mile(String sum_mile) {
//        this.sum_mile = sum_mile;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setCar_code(String car_code) {
//        this.car_code = car_code;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setData_date(long data_date) {
//        this.data_date = data_date;
//    }
//
//    public String getSum_score() {
//        return sum_score;
//    }
//
//    public CurrMapBean getCurrMap() {
//        return currMap;
//    }
//
//    public String getDatestr() {
//        return datestr;
//    }
//
//    public String getDevice_id() {
//        return device_id;
//    }
//
//    public String getSum_mile() {
//        return sum_mile;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getCar_code() {
//        return car_code;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public long getData_date() {
//        return data_date;
//    }
//
//    @Override
//    public String toString() {
//        return "HomeHomeCarBean{" +
//                "sum_score='" + sum_score + '\'' +
//                ", currMap=" + currMap +
//                ", datestr='" + datestr + '\'' +
//                ", device_id='" + device_id + '\'' +
//                ", sum_mile='" + sum_mile + '\'' +
//                ", name='" + name + '\'' +
//                ", car_code='" + car_code + '\'' +
//                ", id='" + id + '\'' +
//                ", data_date=" + data_date +
//                '}';
//    }
//
//    public static class CurrMapBean {
//        /**
//         * lastTime : 2016-12-14 06:28:46
//         * lng : 121.86429220966414
//         * hxjiao : 309.75
//         * poi : 上海市浦东新区泥城镇S2沪芦高速
//         * lat : 30.898028742347996
//         * speed : 62.47
//         * vehicleStatus : 3
//         */
//
//        private String lastTime;
//        private String lng;
//        private String hxjiao;
//        private String poi;
//        private String lat;
//        private String speed;
//        private String vehicleStatus;
//
//        public void setLastTime(String lastTime) {
//            this.lastTime = lastTime;
//        }
//
//        public void setLng(String lng) {
//            this.lng = lng;
//        }
//
//        public void setHxjiao(String hxjiao) {
//            this.hxjiao = hxjiao;
//        }
//
//        public void setPoi(String poi) {
//            this.poi = poi;
//        }
//
//        public void setLat(String lat) {
//            this.lat = lat;
//        }
//
//        public void setSpeed(String speed) {
//            this.speed = speed;
//        }
//
//        public void setVehicleStatus(String vehicleStatus) {
//            this.vehicleStatus = vehicleStatus;
//        }
//
//        public String getLastTime() {
//            return lastTime;
//        }
//
//        public String getLng() {
//            return lng;
//        }
//
//        public String getHxjiao() {
//            return hxjiao;
//        }
//
//        public String getPoi() {
//            return poi;
//        }
//
//        public String getLat() {
//            return lat;
//        }
//
//        public String getSpeed() {
//            return speed;
//        }
//
//        public String getVehicleStatus() {
//            return vehicleStatus;
//        }
//
//        @Override
//        public String toString() {
//            return "CurrMapBean{" +
//                    "lastTime='" + lastTime + '\'' +
//                    ", lng='" + lng + '\'' +
//                    ", hxjiao='" + hxjiao + '\'' +
//                    ", poi='" + poi + '\'' +
//                    ", lat='" + lat + '\'' +
//                    ", speed='" + speed + '\'' +
//                    ", vehicleStatus='" + vehicleStatus + '\'' +
//                    '}';
//        }
//    }


}
