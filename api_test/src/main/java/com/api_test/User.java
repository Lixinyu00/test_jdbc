package com.api_test;

import cn.bmob.v3.BmobObject;

/**
 * Created by NEDUsoftware on 2017/4/20.
 */

public class User extends BmobObject{
    private String userId;
    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
