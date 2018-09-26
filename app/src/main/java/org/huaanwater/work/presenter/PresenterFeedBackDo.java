package org.huaanwater.work.presenter;

import android.util.Log;

import com.jph.takephoto.model.TResult;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.OnUploadListener;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.function.FunctionFeedBack;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.logic.LogicFeedBack;
import org.huaanwater.work.method.IFeedBack;
import org.huaanwater.work.method.IUpload;
import org.huaanwater.work.method.impl.IFeedBackImpl;
import org.huaanwater.work.method.impl.IUploadImpl;
import org.huaanwater.work.ui.iview.IFeedbackDoView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.io.File;
import java.util.Map;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class PresenterFeedBackDo extends BasePresenter<IFeedbackDoView> {

    private IFeedbackDoView iFeedbackDoView;
    private IFeedBack iFeedBack;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;
    private LogicFeedBack logicFeedBack;
    private FunctionFeedBack functionFeedBack;
    private IUpload iUpload;


    public PresenterFeedBackDo(IFeedbackDoView iFeedBacksView) {
        this.iFeedbackDoView = iFeedBacksView;
        this.iFeedBack = new IFeedBackImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.logicFeedBack = new LogicFeedBack();
        this.functionFeedBack = new FunctionFeedBack();
        this.iUpload = new IUploadImpl();
    }

    public void doFeedBack(String url, String title, String content, Map map) {

        if (logicFeedBack.isNoEnterTitle(content)) {

            iFeedbackDoView.onVertifyErrorForNoEnterTitle();
            return;
        }


        if (logicFeedBack.isNoEnterContent(content)) {

            iFeedbackDoView.onVertifyErrorForNoEnterContent();
            return;
        }


        String image = functionFeedBack.getFeedBackImgFromMap(map);

        iFeedBack.doSendFeedBack(url, title, content, image, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFeedbackDoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                if (vertifyNotNull.isNotNullString(data)) {
                    iFeedbackDoView.onDataBackSuccessForSendFeedOption();
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFeedbackDoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iFeedbackDoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iFeedbackDoView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 权限检查
     */
    public void doPermissionCheck() {

        iFeedbackDoView.doPermissionCheck();

    }


    /**
     * 权限关闭提醒
     */
    public void doShowPermissionAlert() {

        iFeedbackDoView.doShowPermissionAlert();

    }

    /**
     * 显示photopopWindow
     */

    public void doShowPhotoPopWindow() {
        iFeedbackDoView.doShowPhotoPopWindow();
    }


    public void doUpload(String url, TResult result) {


        if (!vertifyNotNull.isNotNullObj(result)) {
            iFeedbackDoView.doVertifyErrorForNullFlie();
            return;

        }

        File file = new File(result.getImage().getOriginalPath());


        FileBinary fileBinary = new FileBinary(file);

        Log.i("uploadsss", "文件的长度：" + file.length() + " fileBinary:" + fileBinary.getBinaryLength());

        fileBinary.setUploadListener(HttpConst.HTTP_WHAT, new OnUploadListener() {
            @Override
            public void onStart(int what) {
                iFeedbackDoView.onUploadStart(what);

            }

            @Override
            public void onCancel(int what) {
                iFeedbackDoView.onUploadCancel(what);


            }

            @Override
            public void onProgress(int what, int progress) {
                iFeedbackDoView.onUploadProgress(what, progress);
            }

            @Override
            public void onFinish(int what) {
                iFeedbackDoView.onUploadFinish(what);

//                iUserInfoEditView.dismissLoadingDialog();
            }

            @Override
            public void onError(int what, Exception exception) {
                iFeedbackDoView.onUploadError(what, exception);
            }
        });


        iUpload.doUpLoad(url, fileBinary, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFeedbackDoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iFeedbackDoView.onDataBackSuccessForUpload(data);

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFeedbackDoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iFeedbackDoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iFeedbackDoView.dismissLoadingDialog();
            }
        });
    }


}
