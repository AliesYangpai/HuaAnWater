package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.Region;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.authlistabout.Authed;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/1/18.
 * 类描述
 * 版本
 */

public interface IUserInfoEditView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);


    void doPermissionCheck();

    /**
     * 关闭权限时候的打开提醒
     */
    void doShowPermissionAlert();

    void doShowPhotoPopWindow();


    void doVertifyErrorForNullFlie();

    void onUploadStart(int what);

    void onUploadCancel(int what);

    void onUploadProgress(int what, int progress);

    void onUploadFinish(int what);

    void onUploadError(int what, Exception exception);

    void onDataBackSuccessForUpload(String path);

    void onDbDataBackSuccessForUser(User user);

    void onDbDataBackSuccessForHasBindAnXinAccount();

    void onDbDataBackSuccessForUserTypeHz(String userTypeHz);


    void onDbDataBackSuccessForUserTypeShowTypeStudentLayout();

    void onDbDataBackSuccessForUserTypeShowTypeOtherLayout();


    void onDataBackSuccessForGetAuthroitions(List<Authed> list);

    void onDataBackSuccessForNoAuthroitions();

    void doVertifyErrorForNoUserName();

    void doVertifyErrorForNoAddress();

    void doVertifyErrorForNoStudentNo();

    void onDataBackSuccessForEditUserInfo(User user);


    void onDataBackSuccessForModifyAvatar();


    void onDataBackSuccessForGetUserInfo(User user);


    void onDataBackSuccessForGetUserInfoAfterAnxinBind(User user);

    void doHideSearchReminder();

    void doShowSearchReminder(String str);


    void onDataBackSuccessForFuzzySearchRegions(List<Region> regions);

    void onDataBackErrorForFuzzySearchRegions();

    void doSearchRemindPopWindowShow(List<Region> regions);


    void doSearchRemindPopWindowdismiss();

    void doSearchRemindPopWindowDestory();


    void doAnxinBind();


    void doVertifyErrorForHasAnxinBind();

    void doVertifyErrorForHasWxBind();


    void doVertifyErrorForHasAliBind();

    void doWxBindAbout();


    void doAliBindAbout();

    /**
     * 微信未绑定
     *
     * @param validateWxEntity
     */
    void onDataBackSuccessForWxNotAuth(ValidateWxEntity validateWxEntity);

    void doVertifyErrorForWxHasBindAnotherAlready();

    /**
     * 微信执行绑定成功
     *
     * @param tokenInfo
     */
    void onDataBackSuccessForBindWx(TokenInfo tokenInfo);


    void onDataBackSuccessForGetAliAuthParamElement(String data);

    void onDataBackSuccessForAliNotAuth(ValidateAliEntity validateAliEntity);

    /**
     * 支付宝绑定成功
     *
     * @param tokenInfo
     */
    void onDataBackSuccessForBindAli(TokenInfo tokenInfo);

    void doVertifyErrorForAliHasBindAnotherAlready();

}
