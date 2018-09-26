package org.huaanwater.work.util;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Alie on 2018/1/24.
 * 类描述
 * 版本
 */

public class StringUtil {

    public static String getFileReName() {

        return String.valueOf(System.currentTimeMillis()) + ".png";
    }


    /**
     * 没有设置用户名称的时候显示 电话号码
     * @param nick_name
     * @param phone
     * @return
     */
    public static String  getFullName(String nick_name,String phone) {

        String target = phone;


        if(!TextUtil.isEmpty(nick_name)) {
            target = nick_name;
        }

        return target;
    }

}
