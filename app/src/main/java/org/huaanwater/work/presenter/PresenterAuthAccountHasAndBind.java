package org.huaanwater.work.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.greenrobot.eventbus.util.AsyncExecutor;
import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.thirdabout.ali.AliAuthInfo;
import org.huaanwater.work.entity.thirdabout.ali.AliParameters;
import org.huaanwater.work.entity.thirdabout.ali.BindAliEntity;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.BindWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.entity.thirdabout.wx.WxParameters;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IThirdAuth;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.IThirdAuthImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.IAuthAccountHasAndBindView;
import org.huaanwater.work.util.SpUtil;
import org.huaanwater.work.verification.VertifyNotNull;

/**
 * Created by Alie on 2018/2/7.
 * 类描述
 * 版本
 */

public class PresenterAuthAccountHasAndBind extends BasePresenter<IAuthAccountHasAndBindView> {

    private IAuthAccountHasAndBindView iAuthAccountHasAndBindView;
    private IUser iUser;
    private IThirdAuth iThirdAuth;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterAuthAccountHasAndBind(IAuthAccountHasAndBindView iAuthAccountHasAndBindView) {
        this.iAuthAccountHasAndBindView = iAuthAccountHasAndBindView;
        this.iUser = new IUserImpl();
        this.iThirdAuth = new IThirdAuthImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    /**
     * 设置微信数据到界面
     *
     * @param validateWxEntity
     */
    public void doSetWxInfoToUi(ValidateWxEntity validateWxEntity) {

        if (vertifyNotNull.isNotNullObj(validateWxEntity)) {
            WxAuthInfo wxAuthInfo = validateWxEntity.getWxAuthInfo();
            if (vertifyNotNull.isNotNullObj(wxAuthInfo)) {
                iAuthAccountHasAndBindView.doSetWxUserInfoToUi(validateWxEntity);
            }
        }
    }


    /**
     * 设置Ali据到界面
     *
     * @param validateAliEntity
     */
    public void doSetAliInfoToUi(ValidateAliEntity validateAliEntity) {

        if (vertifyNotNull.isNotNullObj(validateAliEntity)) {
            AliAuthInfo aliAuthInfo = validateAliEntity.getAliAuthInfo();
            if (vertifyNotNull.isNotNullObj(aliAuthInfo)) {
                iAuthAccountHasAndBindView.doSetAliUserInfoToUi(validateAliEntity);
            }
        }
    }


    public void doLogonToGetToken(String url, String user_name, String password) {


        if (vertifyNotNull.isNullString(user_name)) {
            iAuthAccountHasAndBindView.onVertifyErrorForNoUserName();
            return;
        }

        if(vertifyNotNull.isNullString(password)) {
            iAuthAccountHasAndBindView.onVertifyErrorForNoPassWord();
            return;
        }


        iUser.doLogon(url, user_name, password, new OnDataBackListener() {
            @Override
            public void onStart() {
                iAuthAccountHasAndBindView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                if (vertifyNotNull.isNotNullObj(tokenInfo)) {


                    iAuthAccountHasAndBindView.onDataBackSuccessForGetToken(tokenInfo);

                } else {

                    iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iAuthAccountHasAndBindView.dismissLoadingDialog();
                }
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountHasAndBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iAuthAccountHasAndBindView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void doBindWx(String url, String accessToken, String code, String auth_type, final WxParameters parameters) {


        JsonElement jsonElement = parseSerilizable.getParseObjToJson(parameters);
        if (vertifyNotNull.isNullObj(jsonElement)) {
            return;
        }

        iThirdAuth.doBindWx(url, accessToken, code, auth_type, jsonElement, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                BindWxEntity bindWxEntity = parseSerilizable.getParseToObj(data, BindWxEntity.class);
                if (vertifyNotNull.isNotNullObj(bindWxEntity)) {
                    TokenInfo tokenInfo = bindWxEntity.getToken();
                    if (vertifyNotNull.isNotNullObj(tokenInfo)) {
                        iAuthAccountHasAndBindView.onDataBackSuccessForBindWx(tokenInfo);
                    } else {

                        iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }


                } else {

                    iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountHasAndBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iAuthAccountHasAndBindView.dismissLoadingDialog();
            }
        });


    }





    public void doBindAli(String url, String accessToken, String code, String auth_type, final AliParameters parameters) {


        JsonElement jsonElement = parseSerilizable.getParseObjToJson(parameters);
        if (vertifyNotNull.isNullObj(jsonElement)) {
            return;
        }

        iThirdAuth.doBindAli(url, accessToken, code, auth_type, jsonElement, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                BindAliEntity bindAliEntity = parseSerilizable.getParseToObj(data, BindAliEntity.class);
                if (vertifyNotNull.isNotNullObj(bindAliEntity)) {
                    TokenInfo tokenInfo = bindAliEntity.getToken();
                    if (vertifyNotNull.isNotNullObj(tokenInfo)) {
                        iAuthAccountHasAndBindView.onDataBackSuccessForBindAli(tokenInfo);
                    } else {

                        iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }


                } else {

                    iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountHasAndBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountHasAndBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iAuthAccountHasAndBindView.dismissLoadingDialog();
            }
        });


    }


}
