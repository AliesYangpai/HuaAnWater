package org.huaanwater.work.entity.recharge.ali.checkresult;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/9.
 * 类描述 阿里交易-APP支付结果模型类
 * 版本
 */

public class AlipayTradeAppPayResponse implements Serializable {


//    {
//        "code": "string",
//            "msg": "string",
//            "app_id": "string",
//            "auth_app_id": "string",
//            "out_trade_no": "string",
//            "trade_no": "string",
//            "total_amount": "string",
//            "seller_id": "string",
//            "charset": "string",
//            "timestamp": "string"
//
//    }

    private String code;  //结果码
    private String msg;  //处理结果的描述，信息来自于code返回结果的描述
    private String app_id;//支付宝分配给开发者的应用Id。 例如：2014072300007148 ,
    private String auth_app_id;//认证APPId ,
    private String out_trade_no;//商户网站唯一订单号 例如：70501111111S001111119 ,
    private String trade_no;//该交易在支付宝系统中的交易流水号。最长64位 ,
    private String total_amount;//该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位
    private String seller_id;//收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字 例如：2088111111116894 ,
    private String charset;//编码格式 例如：utf-8 ,
    private String timestamp;//时间 例如：2016-10-11 17:43:36


    public AlipayTradeAppPayResponse() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getAuth_app_id() {
        return auth_app_id;
    }

    public void setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "AlipayTradeAppPayResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", app_id='" + app_id + '\'' +
                ", auth_app_id='" + auth_app_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", trade_no='" + trade_no + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", charset='" + charset + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}