package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.feedback.FeedBackPackege;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public interface IFeedBackInfoView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);


    void onDataBackSuccessForFeedBackInfoHeadImg(String[] arry);


    void onDataBackSuccessForFeedBackInfo(FeedBackPackege feedBackPackege);

    void onDataBackSuccessForFeedBackInfoAfterReply(FeedBackPackege feedBackPackege);


    void onVertifyErrorForNoEnterContent();


    void onDataBackSuccessForSendReply();



}
