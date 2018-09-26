package org.huaanwater.work.entity.thirdabout.wx;

import org.huaanwater.work.entity.TokenInfo;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/7.
 * 类描述  绑定微信成功是的返回对象
 * 版本
 */

public class BindWxEntity implements Serializable{



    private String auth_type; //授权类型，Weixin、其他
    private String auth_info; //微信授权信息String
    private WxAuthInfo wxAuthInfo;//微信授权信息对象
    private WxParameters parameters;  //作用：1.为null时，说明已经授权过了,这里是要绑定的数据，绑定成功后，无此字段
    private TokenInfo token;  //未bind时，无此字段，绑定成功后 有此字段




    public BindWxEntity() {
    }


    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public String getAuth_info() {
        return auth_info;
    }

    public void setAuth_info(String auth_info) {
        this.auth_info = auth_info;
    }

    public WxAuthInfo getWxAuthInfo() {
        return wxAuthInfo;
    }

    public void setWxAuthInfo(WxAuthInfo wxAuthInfo) {
        this.wxAuthInfo = wxAuthInfo;
    }

    public WxParameters getParameters() {
        return parameters;
    }

    public void setParameters(WxParameters parameters) {
        this.parameters = parameters;
    }


    public TokenInfo getToken() {
        return token;
    }

    public void setToken(TokenInfo token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "BindWxEntity{" +
                "auth_type='" + auth_type + '\'' +
                ", auth_info='" + auth_info + '\'' +
                ", wxAuthInfo=" + wxAuthInfo +
                ", parameters=" + parameters +
                ", token=" + token +
                '}';
    }
}
