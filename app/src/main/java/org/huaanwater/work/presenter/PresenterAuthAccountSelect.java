package org.huaanwater.work.presenter;

import org.huaanwater.work.entity.thirdabout.ali.AliAuthInfo;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.ui.iview.IAuthAccountSelectView;
import org.huaanwater.work.verification.VertifyNotNull;

/**
 * Created by Alie on 2018/2/7.
 * 类描述
 * 版本
 */

public class PresenterAuthAccountSelect extends BasePresenter<IAuthAccountSelectView> {


    private IAuthAccountSelectView iAuthAccountSelectView;
    private VertifyNotNull vertifyNotNull;

    public PresenterAuthAccountSelect(IAuthAccountSelectView iAuthAccountSelectView) {
        this.iAuthAccountSelectView = iAuthAccountSelectView;
        this.vertifyNotNull = new VertifyNotNull();
    }


    /**
     * 设置微信数据到界面
     * @param validateWxEntity
     */
    public void doSetWxInfoToUi(ValidateWxEntity validateWxEntity) {

        if(vertifyNotNull.isNotNullObj(validateWxEntity)) {
            WxAuthInfo wxAuthInfo = validateWxEntity.getWxAuthInfo();
            if(vertifyNotNull.isNotNullObj(wxAuthInfo)) {
                iAuthAccountSelectView.doSetWxUserInfoToUi(validateWxEntity);
            }
        }
    }



    /**
     * 设置支付宝数据到界面
     * @param validateAliEntity
     */
    public void doSetAliInfoToUi(ValidateAliEntity validateAliEntity) {

        if(vertifyNotNull.isNotNullObj(validateAliEntity)) {
            AliAuthInfo aliAuthInfo = validateAliEntity.getAliAuthInfo();
            if(vertifyNotNull.isNotNullObj(aliAuthInfo)) {
                iAuthAccountSelectView.doSetAliUserInfoToUi(validateAliEntity);
            }
        }
    }
}
