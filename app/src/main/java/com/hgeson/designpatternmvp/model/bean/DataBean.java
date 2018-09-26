package com.hgeson.designpatternmvp.model.bean;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public class DataBean {

    private String currentTime="";
    private String duration="";
    private String userSex="";
    private String elasticity="";
    private String moisture="";
    private int sort=0;
    private String userName="";
    private String userIcon="";
    private String usagecounter="-";
    private String userId="";
    private String recordline="";
    private String breastcup="";
    private int currentprogress = 0;

    public int getCurrentprogress() {
        return currentprogress;
    }

    public void setCurrentprogress(int currentprogress) {
        this.currentprogress = currentprogress;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getElasticity() {
        return elasticity;
    }

    public void setElasticity(String elasticity) {
        this.elasticity = elasticity;
    }

    public String getMoisture() {
        return moisture;
    }

    public void setMoisture(String moisture) {
        this.moisture = moisture;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUsagecounter() {
        return usagecounter;
    }

    public void setUsagecounter(String usagecounter) {
        this.usagecounter = usagecounter;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecordline() {
        return recordline;
    }

    public void setRecordline(String recordline) {
        this.recordline = recordline;
    }

    public String getBreastcup() {
        return breastcup;
    }

    public void setBreastcup(String breastcup) {
        this.breastcup = breastcup;
    }
}
