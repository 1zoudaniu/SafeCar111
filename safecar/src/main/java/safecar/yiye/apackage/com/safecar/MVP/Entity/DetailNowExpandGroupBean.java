package safecar.yiye.apackage.com.safecar.MVP.Entity;

/**
 * Name: DetailNowExpandGroupBean
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 21:16
 */
public class DetailNowExpandGroupBean {
    private String carid;
    private String carstatus;
    private String carkm;

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarstatus() {
        return carstatus;
    }

    public void setCarstatus(String carstatus) {
        this.carstatus = carstatus;
    }

    public String getCarkm() {
        return carkm;
    }

    public void setCarkm(String carkm) {
        this.carkm = carkm;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    private Float  latitude;
    private Float  longitude;
}
