package org.huaanwater.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/3/8.
 * 类描述  绑定成功的返回
 * 版本
 */

public class AnXinBindBack implements Serializable{

//      "success": true,
//              "re_value": "string"

    private Boolean  success;
    private String re_value;

    public AnXinBindBack() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getRe_value() {
        return re_value;
    }

    public void setRe_value(String re_value) {
        this.re_value = re_value;
    }
}
