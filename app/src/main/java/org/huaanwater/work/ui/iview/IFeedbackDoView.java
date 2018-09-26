package org.huaanwater.work.ui.iview;

import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public interface IFeedbackDoView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);


    void onVertifyErrorForNoEnterTitle();

    void onVertifyErrorForNoEnterContent();

    void onDataBackSuccessForSendFeedOption();



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
}
