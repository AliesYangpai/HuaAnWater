package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/7.
 * 类描述   没有注册过账号的绑定
 * 版本
 */

public interface IAuthAccountNoAndRegBindView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);


    void doSetWxUserInfoToUi(ValidateWxEntity validateWxEntity);


    void doSetAliUserInfoToUi(ValidateAliEntity validateAliEntity);

    void onVertifyErrorForNoUserName();

    void onVertifyErrorForNoPassWord();


    void onVertifyErrorForNoVertifyCode();

    void onDataBackSuccessForGetPhoneCode();

    void onDataBackSuccessForVertifyPhoneCode();


    void onDataBackSuccessForRegister(String userName, String passWord);

    void onDataBackSuccessForGetToken(TokenInfo tokenInfo);

    void onDataBackSuccessForBindWx(TokenInfo tokenInfo);


    void onDataBackSuccessForBindAli(TokenInfo tokenInfo);


}
