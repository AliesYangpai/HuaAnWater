package org.huaanwater.work.http.requestparam;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.huaanwater.work.entity.thirdabout.wx.WxParameters;

/**
 * Created by Alie on 2018/2/7.
 * 类描述  第三方授权、登录的相关线束
 * 版本
 */

public class ThirdAuthParam extends BaseParam {


    public String getValidateWxAuthParam(String code,String auth_type) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("code",code);
        jsonObject.addProperty("auth_type",auth_type);
        return jsonObject.toString();
    }


    /**
     * 获取微信绑定的参数
     * @param code
     * @param auth_type
     * @param parameters
     * @return
     */
    public String getWxBindParam(String code, String auth_type, JsonElement parameters ) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("code",code);
        jsonObject.addProperty("auth_type",auth_type);
        jsonObject.add("parameters", parameters);
        return jsonObject.toString();
    }




    /**
     * 获取Ali绑定的参数
     * @param code
     * @param auth_type
     * @param parameters
     * @return
     */
    public String getAliBindParam(String code, String auth_type, JsonElement parameters ) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("code",code);
        jsonObject.addProperty("auth_type",auth_type);
        jsonObject.add("parameters", parameters);
        return jsonObject.toString();
    }




    /**
     * 支付宝验证是否授权的参数
     * @param code
     * @param auth_type
     * @return
     */
    public String getValidateAliAuthParam(String code,String auth_type,String userId) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("code",code);
        jsonObject.addProperty("auth_type",auth_type);
        JsonObject jsonObjectparameters = getJsonObject();
        jsonObjectparameters.addProperty("AlipayUserId",userId);
        jsonObject.add("parameters",jsonObjectparameters);

        return jsonObject.toString();
    }

}
