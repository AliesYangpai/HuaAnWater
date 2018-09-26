package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/1/17.
 * 类描述
 * 版本
 */

public interface ILoginView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForLogon(TokenInfo tokenInfo);


    void onDataBackSuccessForGetUserInfo(User user);


    void onDataBackSuccessForGetPhoneCode();


    /**
     * 微信未绑定
     * @param validateWxEntity
     */
    void onDataBackSuccessForWxNotAuth(ValidateWxEntity validateWxEntity);


    /**
     * 微信已经绑定
     * @param tokenInfo  返回的token信息
     */
    void onDataBackSuccessForWxHasAuth( TokenInfo tokenInfo);



    void onDataBackSuccessForGetAliAuthParamElement(String element);




    /**
     * 支付宝已经绑定
     * @param tokenInfo  返回的token信息
     */
    void onDataBackSuccessForAliHasAuth( TokenInfo tokenInfo);



    /**
     * 支付宝未绑定
     * @param validateAliEntity
     */
    void onDataBackSuccessForAliNotAuth(ValidateAliEntity validateAliEntity);

}
