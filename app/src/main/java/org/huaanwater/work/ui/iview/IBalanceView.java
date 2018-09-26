package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.recharge.ali.AliRechargeNecessaryInfo;
import org.huaanwater.work.entity.recharge.wx.WxRechargeNecessaryInfo;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/1/18.
 * 类描述
 * 版本
 */

public interface IBalanceView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);

    void dismissFreshLoading();

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void onDataBackSuccessForGetUserInfo(User user);

    void onDataBackSuccessForGetUserInfoInFresh(User user);



    /**
     * 支付宝充值
     * @param aliRechargeNecessaryInfo
     */
    void doRxAliRecharge(AliRechargeNecessaryInfo aliRechargeNecessaryInfo);




    /**
     * 微信充值
     * @param wxRechargeNecessaryInfo
     */
    void doWxRecharge(WxRechargeNecessaryInfo wxRechargeNecessaryInfo);


    /**
     * 支付宝验签返回成功
     * @param
     */
    void onDataBackSuccessForAliCheck();


    /**
     * 微信验签返回成功
     * 微信验签 【微信支付成功后，不用进行验签，因此此方法作废】
     */
//    void  onDataBackSuccessForWxCheck();


//    void onDataBackSuccessForShowPayChannels(List<PayChannel> list);
}
