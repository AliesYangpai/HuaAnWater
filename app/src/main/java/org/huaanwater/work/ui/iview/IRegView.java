package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.User;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/1/22.
 * 类描述
 * 版本
 */

public interface IRegView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);


    void onVertifyErrorForNoUserName();

    void onVertifyErrorForNoPass();

    void onVertifyErrorForNoVertifyCode();

    void onDataBackSuccessForGetPhoneCode();

    void onDataBackSuccessForVertifyPhoneCode();

    void onDataBackSuccessForReg();
}
