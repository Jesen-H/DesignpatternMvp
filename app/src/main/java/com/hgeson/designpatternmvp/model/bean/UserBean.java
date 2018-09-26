package com.hgeson.designpatternmvp.model.bean;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public class UserBean {
    private String username;
    private String usericon;
    private String breastcup;
    private String usersex;

    public UserBean(String username, String usericon, String breastcup, String usersex) {
        this.username = username;
        this.usericon = usericon;
        this.breastcup = breastcup;
        this.usersex = usersex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }

    public String getBreastcup() {
        return breastcup;
    }

    public void setBreastcup(String breastcup) {
        this.breastcup = breastcup;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }
}
