package org.huaanwater.work.logic;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.thirdabout.ali.AliParameters;
import org.huaanwater.work.entity.thirdabout.authlistabout.Authed;
import org.huaanwater.work.entity.thirdabout.wx.WxParameters;

import java.util.List;

/**
 * Created by Alie on 2018/2/7.
 * 类描述  第三方授权的判断逻辑类
 * 版本
 */

public class LogicThird {


    /**
     * 微信已经授权过
     * @param wxParameters
     * @return
     */
    public boolean isWxAuthed(WxParameters wxParameters) {

        return null == wxParameters;
    }


    public boolean isAliAuthed(AliParameters aliParameters) {

        return null == aliParameters;
    }


    /**
     * 用于判断 app接口反馈的绑定判断
     * @return
     */
    public boolean isWxBindInList(List<Authed> autheds) {


        boolean result = false;

        if(null != autheds && autheds.size() > 0) {
            for (Authed authed : autheds) {

                String authoType = authed.getAuth_type();

                if(authoType.equals(ConstLocalData.WX)) {

                    result = true;
                    break;
                }
            }
        }
        return  result;
    }


    public boolean isAliBindInList(List<Authed> autheds) {


        boolean result = false;

        if(null != autheds && autheds.size() > 0) {
            for (Authed authed : autheds) {

                String authoType = authed.getAuth_type();

                if(authoType.equals(ConstLocalData.ALI)) {

                    result = true;
                    break;
                }
            }
        }
        return  result;
    }




}
