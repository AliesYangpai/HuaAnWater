package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.User;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/6.
 * 类描述
 * 版本
 */

public interface IModifyPhoneView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);

    void onVertifyErrorForNoPhone();

    void onVertifyErrorForNoVertifyCode();

    void onVertifyErrorForNoPass();

    void onDataBackSuccessForGetPhoneCode();

    void onDataBackSuccessForVertifyPhoneCode();

    void onDataBackSuccessForModifyPhone();


    void onDataBackSuccessForGetUserInfo(User user);


}
