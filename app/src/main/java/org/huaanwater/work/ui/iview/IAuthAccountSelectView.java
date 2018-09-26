package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/7.
 * 类描述
 * 版本
 */

public interface IAuthAccountSelectView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void doSetWxUserInfoToUi(ValidateWxEntity validateWxEntity);



    void doSetAliUserInfoToUi(ValidateAliEntity validateAliEntity);
}
