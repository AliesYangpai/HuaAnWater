package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.User;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/1/18.
 * 类描述
 * 版本
 */

public interface IUserInfoView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForGetUserInfo(User user);

    void onDataBackSuccessForGetUserInfoFromDb(User user);


    void onDataBackSuccessForGetOrderCount(String count);

    void onDataBackSuccessForSignIn(String data);
}
