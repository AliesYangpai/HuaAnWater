package org.huaanwater.work.entity.thirdabout.ali;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/7.
 * 类描述  ali的授权用户信息
 * 版本
 */

public class AliAuthInfo implements Serializable{

    /**
     * 阿里信息
     */

    private String nick_name;
    private String avatar;
    private String user_id;



    public AliAuthInfo() {
    }


    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
