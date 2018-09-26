package org.huaanwater.work.entity.thirdabout.wx;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/7.
 * 类描述  微信的授权用户信息
 * 版本
 */

public class WxAuthInfo implements Serializable{

    /**
     * wx信息
     */
    private String openid;
    private String nickname;
    private String headimgurl;
    private String unionid;



    public WxAuthInfo() {
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }


    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "WxAuthInfo{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
