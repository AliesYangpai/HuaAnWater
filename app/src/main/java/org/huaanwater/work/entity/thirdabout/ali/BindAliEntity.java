package org.huaanwater.work.entity.thirdabout.ali;

import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.entity.thirdabout.wx.WxParameters;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/7.
 * 类描述  绑定微信成功是的返回对象
 * 版本
 */

public class BindAliEntity implements Serializable{



    private String auth_type; //授权类型，Weixin、其他
    private String auth_info; //微信授权信息String
    private AliAuthInfo aliAuthInfo;//微信授权信息对象
    private AliParameters parameters;  //作用：1.为null时，说明已经授权过了,这里是要绑定的数据，绑定成功后，无此字段
    private TokenInfo token;  //未bind时，无此字段，绑定成功后 有此字段




    public BindAliEntity() {
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

    public AliAuthInfo getAliAuthInfo() {
        return aliAuthInfo;
    }

    public void setAliAuthInfo(AliAuthInfo aliAuthInfo) {
        this.aliAuthInfo = aliAuthInfo;
    }

    public AliParameters getParameters() {
        return parameters;
    }

    public void setParameters(AliParameters parameters) {
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
        return "BindAliEntity{" +
                "auth_type='" + auth_type + '\'' +
                ", auth_info='" + auth_info + '\'' +
                ", aliAuthInfo=" + aliAuthInfo +
                ", parameters=" + parameters +
                ", token=" + token +
                '}';
    }
}
