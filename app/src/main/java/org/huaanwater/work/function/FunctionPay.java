package org.huaanwater.work.function;

import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.recharge.ali.checkresult.AliCheckResultInfo;
import org.huaanwater.work.entity.recharge.ali.checkresult.AlipayTradeAppPayResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alie on 2018/2/9.
 * 类描述
 * 版本
 */

public class FunctionPay {


    /**
     * 这个是 org的jsonobject
     *
     * @param back
     * @param key
     * @return
     */
    public String getParseString(String back, String key) {

        String json = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(back);
            json = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 生成延签的支付对象
     * @param result
     * @return
     */
    public AliCheckResultInfo getToGenerateAliPayResultDto(String result) {


        AliCheckResultInfo aliCheckResultInfo = new AliCheckResultInfo();
        AlipayTradeAppPayResponse alipayTradeAppPayResponse = new AlipayTradeAppPayResponse();


        String sign = getParseString(result, "sign");
        String sign_type = getParseString(result, "sign_type");

        String alipay_trade_app_pay_response = getParseString(result, "alipay_trade_app_pay_response");

        String code = getParseString(alipay_trade_app_pay_response,"code");
        String msg = getParseString(alipay_trade_app_pay_response,"msg");
        String app_id = getParseString(alipay_trade_app_pay_response,"app_id");
        String out_trade_no = getParseString(alipay_trade_app_pay_response,"out_trade_no");
        String trade_no = getParseString(alipay_trade_app_pay_response,"trade_no");
        String total_amount = getParseString(alipay_trade_app_pay_response,"total_amount");
        String seller_id = getParseString(alipay_trade_app_pay_response,"seller_id");
        String charset = getParseString(alipay_trade_app_pay_response,"charset");
        String timestamp = getParseString(alipay_trade_app_pay_response,"timestamp");


        alipayTradeAppPayResponse.setCode(code);
        alipayTradeAppPayResponse.setMsg(msg);
        alipayTradeAppPayResponse.setApp_id(app_id);
        alipayTradeAppPayResponse.setAuth_app_id(app_id);
        alipayTradeAppPayResponse.setOut_trade_no(out_trade_no);
        alipayTradeAppPayResponse.setTrade_no(trade_no);
        alipayTradeAppPayResponse.setTotal_amount(total_amount);
        alipayTradeAppPayResponse.setSeller_id(seller_id);
        alipayTradeAppPayResponse.setCharset(charset);
        alipayTradeAppPayResponse.setTimestamp(timestamp);


        aliCheckResultInfo.setSign(sign);
        aliCheckResultInfo.setSign_type(sign_type);
        aliCheckResultInfo.setAlipayTradeAppPayResponse(alipayTradeAppPayResponse);

        return aliCheckResultInfo;
    }


    /**
     * 获取支付汉字
     * @param payChannel_id
     * @return
     */
    public String getHzPayChannel(String payChannel_id) {

        String target = ConstHz.PAY_WX;
        switch (payChannel_id) {
            case ConstLocalData.WX:
                target = ConstHz.PAY_WX;
                break;

            case ConstLocalData.ALI:
                target = ConstHz.PAY_ALI;
                break;
        }

        return target;


    }
}
