package safecar.yiye.apackage.com.safecar.MVP.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/2/002.
 */

public class HistoryCarScoreBean {

    /**
     * res_score : [{"datestr":"2017/03/02","score":71,"date_str":"03/02"},{"datestr":"2017/03/01","score":58.6,"date_str":"03/01"},{"datestr":"2017/02/28","score":63.2,"date_str":"02/28"},{"datestr":"2017/02/27","score":66.8,"date_str":"02/27"},{"datestr":"2017/02/26","score":66.2,"date_str":"02/26"},{"datestr":"2017/02/25","score":68.6,"date_str":"02/25"},{"datestr":"2017/02/24","score":63.6,"date_str":"02/24"}]
     * res_code : 1
     */

    private String res_code;
    private List<ResScoreBean> res_score;

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public List<ResScoreBean> getRes_score() {
        return res_score;
    }

    public void setRes_score(List<ResScoreBean> res_score) {
        this.res_score = res_score;
    }

    public static class ResScoreBean {
        /**
         * datestr : 2017/03/02
         * score : 71
         * date_str : 03/02
         */

        private String datestr;
        private double score;
        private String date_str;

        public String getDatestr() {
            return datestr;
        }

        public void setDatestr(String datestr) {
            this.datestr = datestr;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getDate_str() {
            return date_str;
        }

        public void setDate_str(String date_str) {
            this.date_str = date_str;
        }
    }
}
