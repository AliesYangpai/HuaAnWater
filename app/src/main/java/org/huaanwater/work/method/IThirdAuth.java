package org.huaanwater.work.method;

import com.google.gson.JsonElement;

import org.huaanwater.work.entity.thirdabout.wx.WxParameters;
import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/2/7.
 * 类描述  第三方授权相关
 * 版本
 */

public interface IThirdAuth {


    /**
     * 获取授权列表
     */
    void doGetAuthoritionList(String url,OnDataBackListener onDataBackListener);


    /**
     * 验证是否微信进行了授权
     */
    void doValidateAuthForWx(String url, String code, String auth_type, OnDataBackListener onDataBackListener);




    void doBindWx(String url, String accessToken,String code, String auth_type, JsonElement wxParameters, OnDataBackListener onDataBackListener);



    void doBindAli(String url, String accessToken,String code, String auth_type, JsonElement wxParameters, OnDataBackListener onDataBackListener);



    /**
     * 支付宝相关
     * 阿里获取相关支付要素，与微信不同的是，阿里的要先从服务端获取数据，之后使用获取到的数据作为客户端调用阿里sdk的参数，
     */
    void doGetAuthParamatersForAli(String url,OnDataBackListener onDataBackListener);





    /**
     * 验证是否微信进行了授权
     */
    void doValidateAuthFoAli(String url, String code, String auth_type, String user_id,OnDataBackListener onDataBackListener);


    /**
     * 绑定安信账号
     * @param url
     * @param ax_account
     * @param ax_password
     * @param onDataBackListener
     */
    void doBindAnXinAccount(String url,String ax_account,String ax_password,OnDataBackListener onDataBackListener);
}
