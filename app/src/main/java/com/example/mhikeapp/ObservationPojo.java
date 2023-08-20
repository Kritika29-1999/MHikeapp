package com.example.mhikeapp;

public class ObservationPojo {

    String hikeid;
    String id;

    String obsname;
    String obsdate, obstime, obscomment;


    public ObservationPojo(String hikeid, String id, String obsname, String obsdate, String obstime, String obscomment) {
        this.hikeid = hikeid;
        this.id = id;
        this.obsname = obsname;
        this.obsdate = obsdate;
        this.obstime = obstime;
        this.obscomment = obscomment;
    }

    public String getHikeid() {
        return hikeid;
    }

    public void setHikeid(String hikeid) {
        this.hikeid = hikeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObsname() {
        return obsname;
    }

    public void setObsname(String obsname) {
        this.obsname = obsname;
    }

    public String getObsdate() {
        return obsdate;
    }

    public void setObsdate(String obsdate) {
        this.obsdate = obsdate;
    }

    public String getObstime() {
        return obstime;
    }

    public void setObstime(String obstime) {
        this.obstime = obstime;
    }

    public String getObscomment() {
        return obscomment;
    }

    public void setObscomment(String obscomment) {
        this.obscomment = obscomment;
    }
}