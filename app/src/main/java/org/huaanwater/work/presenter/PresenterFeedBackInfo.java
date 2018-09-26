package org.huaanwater.work.presenter;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.feedback.FeedBack;
import org.huaanwater.work.entity.feedback.FeedBackPackege;
import org.huaanwater.work.function.FunctionFeedBack;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.logic.LogicFeedBack;
import org.huaanwater.work.method.IFeedBack;
import org.huaanwater.work.method.impl.IFeedBackImpl;
import org.huaanwater.work.ui.iview.IFeedBackInfoView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class PresenterFeedBackInfo extends BasePresenter<IFeedBackInfoView> {

    private IFeedBackInfoView iFeedBackInfoView;
    private IFeedBack iFeedBack;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;
    private FunctionFeedBack functionFeedBack;

    private LogicFeedBack logicFeedBack;

    public PresenterFeedBackInfo(IFeedBackInfoView iFeedBackInfoView) {
        this.iFeedBackInfoView = iFeedBackInfoView;
        this.iFeedBack = new IFeedBackImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.functionFeedBack = new FunctionFeedBack();
        this.logicFeedBack = new LogicFeedBack();
    }


    public void doGetFeedBackInfo(String url) {

        iFeedBack.doGetFeedBackInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFeedBackInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                FeedBackPackege feedBackPackege = parseSerilizable.getParseToObj(data, FeedBackPackege.class);


                if (vertifyNotNull.isNotNullObj(feedBackPackege)) {


                    String feedback_images = feedBackPackege.getFeedback_images();
                    if (!TextUtil.isEmpty(feedback_images)) {
                        String[] arry = functionFeedBack.getFeedBackImgArry(feedback_images);
                        iFeedBackInfoView.onDataBackSuccessForFeedBackInfoHeadImg(arry);
                    }

                    iFeedBackInfoView.onDataBackSuccessForFeedBackInfo(feedBackPackege);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFeedBackInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iFeedBackInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iFeedBackInfoView.dismissLoadingDialog();
            }
        });
    }




    public void doGetFeedBackInfoAfterReply(String url) {

        iFeedBack.doGetFeedBackInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFeedBackInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                FeedBackPackege feedBackPackege = parseSerilizable.getParseToObj(data, FeedBackPackege.class);


                if (vertifyNotNull.isNotNullObj(feedBackPackege)) {



                    iFeedBackInfoView.onDataBackSuccessForFeedBackInfoAfterReply(feedBackPackege);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFeedBackInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iFeedBackInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iFeedBackInfoView.dismissLoadingDialog();
            }
        });
    }



    public void doFeedBackReply(String url, int feedback_id,String reply_content) {


        if(logicFeedBack.isNoEnterContent(reply_content)) {
            iFeedBackInfoView.onVertifyErrorForNoEnterContent();
            return;
        }


        iFeedBack.doSendFeedBackReplyContent(
                url,
                feedback_id,
                reply_content, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iFeedBackInfoView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {


                        if(vertifyNotNull.isNotNullString(data)) {
                            iFeedBackInfoView.onDataBackSuccessForSendReply();
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                        if(vertifyNotNull.isNotNullObj(errorEntity)) {
                            iFeedBackInfoView.onDataBackFail(code,errorEntity.getError_message());
                        }else {
                            iFeedBackInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {
                        iFeedBackInfoView.dismissLoadingDialog();
                    }
                });
    }





}
