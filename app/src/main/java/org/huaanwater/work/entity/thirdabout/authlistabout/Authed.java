package org.huaanwater.work.entity.thirdabout.authlistabout;

import org.huaanwater.work.entity.TokenInfo;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/8.
 * 类描述
 * 版本
 */

public class Authed implements Serializable{

//
//    "user_auth_id": 0,
//            "user_id": 0,
//            "auth_type": "Weixin",
//            "union_id": "string",
//            "parameters": {},
//            "auth_info": "string",
//            "token": {}


    private int user_auth_id;
    private int user_id;
    private String auth_type;
    private String union_id;
    private String auth_info;


    public Authed() {
    }


    public int getUser_auth_id() {
        return user_auth_id;
    }

    public void setUser_auth_id(int user_auth_id) {
        this.user_auth_id = user_auth_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public String getUnion_id() {
        return union_id;
    }

    public void setUnion_id(String union_id) {
        this.union_id = union_id;
    }

    public String getAuth_info() {
        return auth_info;
    }

    public void setAuth_info(String auth_info) {
        this.auth_info = auth_info;
    }

    @Override
    public String toString() {
        return "Authed{" +
                "user_auth_id=" + user_auth_id +
                ", user_id=" + user_id +
                ", auth_type='" + auth_type + '\'' +
                ", union_id='" + union_id + '\'' +
                ", auth_info='" + auth_info + '\'' +
                '}';
    }
}
