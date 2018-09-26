package org.huaanwater.work.method;

import com.google.gson.JsonElement;

import org.huaanwater.work.entity.recharge.ali.checkresult.AliCheckResultInfo;
import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/2/9.
 * 类描述  支付相关的方法
 * 版本
 */

public interface IPay {

    void doGetPayChannels(
            String url,
            String order_type,
            String os_type,
            String device_type, OnDataBackListener onDataBackListener);


    /**
     * 零钱支付宝充值
     *
     * @param url
     * @param order_type
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param amount
     * @param payer
     * @param onDataBackListener
     */
    void doBalanceRechargeAli(String url,
                              String order_type,
                              String channel_id,
                              String os_type,
                              String device_type,
                              String device_id,
                              String amount,
                              String payer, OnDataBackListener onDataBackListener);

    /**
     * 零钱微信充值
     *
     * @param url
     * @param order_type
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param amount
     * @param payer
     * @param onDataBackListener
     */
    void doBalanceRechargeWx(String url,
                             String order_type,
                             String channel_id,
                             String os_type,
                             String device_type,
                             String device_id,
                             String amount,
                             String payer, OnDataBackListener onDataBackListener);


    /**
     * 验证阿里支付返回
     *
     * @param url
     * @param aliCheckResultInfo
     * @param app_type           APP类型 = ['ClientApp'],
     * @param os_type            系统类型 = ['iOS', 'Android', 'Windows'],
     * @param payer              付款方 = ['User']
     * @param onDataBackListener
     */
    void doCheckAliPayResult(String url,
                             AliCheckResultInfo aliCheckResultInfo,
                             String app_type,
                             String os_type,
                             String payer,
                             OnDataBackListener onDataBackListener);



//    /**
//     * 微信验签 【微信支付成功后，不用进行验签，因此此方法作废】
//     *
//     * @param url
//     */
//    void doWxCheckWxPayResultInfo(String url,
//                                  String app_type,
//                                  String os_type,
//                                  String payer,
//                                  int payment_record_id,
//                                  OnDataBackListener onDataBackListener);
}
