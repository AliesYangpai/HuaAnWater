package org.huaanwater.work.http.requestparam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.huaanwater.work.entity.recharge.ali.checkresult.AliCheckResultInfo;

/**
 * Created by Alie on 2018/2/9.
 * 类描述
 * 版本
 */

public class IPayParam extends BaseParam {


    /**
     * 获取支付宝充值必要参数
     *
     * @param order_type
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param amount
     * @param payer
     * @return
     */
    public String getRechargeAliParam(String order_type,
                                      String channel_id,
                                      String os_type,
                                      String device_type,
                                      String device_id,
                                      String amount,
                                      String payer) {

        JsonObject jsonObject = getJsonObject();
        JsonObject jsonParameters = getJsonObject();
        jsonParameters.addProperty("amount", amount);

        jsonObject.addProperty("order_type", order_type);
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.add("parameters", jsonParameters);
        jsonObject.addProperty("payer", payer);

        return jsonObject.toString();

    }


    /**
     * 获取微信充值必要参数
     *
     * @param order_type
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param amount
     * @param payer
     * @return
     */
    public String getRechargeWxParam(String order_type,
                                     String channel_id,
                                     String os_type,
                                     String device_type,
                                     String device_id,
                                     String amount,
                                     String payer) {

        JsonObject jsonObject = getJsonObject();

        JsonObject jsonParameters = getJsonObject();
        jsonParameters.addProperty("amount", amount);

        jsonObject.addProperty("order_type", order_type);
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.add("parameters", jsonParameters);
        jsonObject.addProperty("payer", payer);

        return jsonObject.toString();

    }


    /**
     * 获取支付宝验签的参数
     *
     * @param aliCheckResultInfo
     * @param app_type
     * @param os_type
     * @param payer
     * @return
     */
    public String getCheckAlipayResultParam(AliCheckResultInfo aliCheckResultInfo,
                                            String app_type,
                                            String os_type,
                                            String payer) {

        JsonObject jsonObject = getJsonObject();
        JsonElement alipay_trade_app_pay_response = getJsonElement(aliCheckResultInfo.getAlipayTradeAppPayResponse());

        jsonObject.add("alipay_trade_app_pay_response", alipay_trade_app_pay_response);
//        jsonObject.addProperty("app_pay_response", aliCheckResultInfo.getApp_pay_response());
        jsonObject.addProperty("sign", aliCheckResultInfo.getSign());
        jsonObject.addProperty("sign_type", aliCheckResultInfo.getSign_type());
        jsonObject.addProperty("app_type", app_type);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("payer", payer);

        return jsonObject.toString();

    }


    /**
     * 获取微信验签的参数
     *
     * @param app_type
     * @param os_type
     * @param payer
     * @param payment_record_id
     * @return
     */
    public String getCheckWxpayResultParam(String app_type,
                                           String os_type,
                                           String payer,
                                           int payment_record_id) {

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("app_type", app_type);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("payer", payer);
        jsonObject.addProperty("payment_record_id", payment_record_id);
        return jsonObject.toString();

    }


}
