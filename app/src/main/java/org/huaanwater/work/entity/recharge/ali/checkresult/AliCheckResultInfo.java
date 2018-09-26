package org.huaanwater.work.entity.recharge.ali.checkresult;

/**
 * Created by Alie on 2018/2/9.
 * 类描述
 * 版本
 */

public class AliCheckResultInfo {

    private AlipayTradeAppPayResponse alipayTradeAppPayResponse;
//    private String app_pay_response; //app支付返回信息 ,
    private String sign; //:阿里支付签名 ,
    private String sign_type;//签名方式 ,


    public AliCheckResultInfo() {
    }


    public AlipayTradeAppPayResponse getAlipayTradeAppPayResponse() {
        return alipayTradeAppPayResponse;
    }

    public void setAlipayTradeAppPayResponse(AlipayTradeAppPayResponse alipayTradeAppPayResponse) {
        this.alipayTradeAppPayResponse = alipayTradeAppPayResponse;
    }

//    public String getApp_pay_response() {
//        return app_pay_response;
//    }
//
//    public void setApp_pay_response(String app_pay_response) {
//        this.app_pay_response = app_pay_response;
//    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }


    @Override
    public String toString() {
        return "AliCheckResultInfo{" +
                "alipayTradeAppPayResponse=" + alipayTradeAppPayResponse +
                ", sign='" + sign + '\'' +
                ", sign_type='" + sign_type + '\'' +
                '}';
    }
}
