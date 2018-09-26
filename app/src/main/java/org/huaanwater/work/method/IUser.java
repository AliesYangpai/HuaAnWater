package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/1/11.
 * 类描述  用户相关的接口方法包括注册，登录
 * 版本
 */

public interface IUser {


    /**
     * 用户注册
     *
     * @param url
     * @param phone
     * @param user_name
     * @param password
     * @param pass_code
     * @param user_points
     */
    void doRegister(String url,
                    String phone,
                    String user_name,
                    String password,
                    String pass_code,
                    String user_points,
                    OnDataBackListener onDataBackListener);




    /**
     * 登录
     * @param url
     * @param user_name
     * @param password
     */
    void doLogon(String url,String user_name,String password, OnDataBackListener onDataBackListener);



    /**
     * 获取用户信息信息
     * @param url
     * @param onDataBackListener
     */
    void doGetUserInfo(String url,OnDataBackListener onDataBackListener);


    /**
     * 获取用户积分
     * @param url
     * @param onDataBackListener
     */
    void doGetUserIntegral(String url,OnDataBackListener onDataBackListener);



    /**
     * 编辑用户信息
     * @param url
     * @param userName
     * @param userType
     * @param address
     * @param regionId
     * @param StudentNo
     * @param onDataBackListener
     */
    void doEditUserInfoCommit(
            String url,
            String userName,
            String userType,
            String address,
            int regionId,
            String StudentNo,
            OnDataBackListener onDataBackListener);



    void doEditPass(String url,String pass,OnDataBackListener onDataBackListener);


    void doEditPhone(String url,
                     String Phone,
                     String PassCode,
                     String Password,
                     OnDataBackListener onDataBackListener);


    /**
     * 修改用户头像地址
     * @param url
     * @param avatar
     * @param onDataBackListener
     */
    void doModifyAvatar(String url,String avatar,OnDataBackListener onDataBackListener);


    /**
     * 用户签到
     * @param url
     * @param onDataBackListener
     */
    void doSignIn(String url,OnDataBackListener onDataBackListener);
}
