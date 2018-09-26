package org.huaanwater.work.presenter;

import com.google.gson.JsonElement;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.User;
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
import org.huaanwater.work.method.ICodeVertify;
import org.huaanwater.work.method.IThirdAuth;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.ICodeVertifyImpl;
import org.huaanwater.work.method.impl.IThirdAuthImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.IAuthAccountNoAndRegBindView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/7.
 * 类描述
 * 版本
 */

public class PresenterAuthAccountNoAndRegBind extends BasePresenter<IAuthAccountNoAndRegBindView> {

    private IAuthAccountNoAndRegBindView iAuthAccountNoAndRegBindView;
    private IUser iUser;
    private IThirdAuth iThirdAuth;
    private ICodeVertify iCodeVertify;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterAuthAccountNoAndRegBind(IAuthAccountNoAndRegBindView iAuthAccountNoAndRegBindView) {
        this.iAuthAccountNoAndRegBindView = iAuthAccountNoAndRegBindView;
        this.iUser = new IUserImpl();
        this.iThirdAuth = new IThirdAuthImpl();
        this.iCodeVertify = new ICodeVertifyImpl();
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
                iAuthAccountNoAndRegBindView.doSetWxUserInfoToUi(validateWxEntity);
            }
        }
    }



    /**
     * 设置ali数据到界面
     *
     * @param validateAliEntity
     */
    public void doSetAliInfoToUi(ValidateAliEntity validateAliEntity) {

        if (vertifyNotNull.isNotNullObj(validateAliEntity)) {
            AliAuthInfo aliAuthInfo = validateAliEntity.getAliAuthInfo();
            if (vertifyNotNull.isNotNullObj(aliAuthInfo)) {
                iAuthAccountNoAndRegBindView.doSetAliUserInfoToUi(validateAliEntity);
            }
        }
    }


    public void doGeneratePhonePassCode(String url, String phone, String options) {


        if (vertifyNotNull.isNullString(phone)) {

            iAuthAccountNoAndRegBindView.onVertifyErrorForNoVertifyCode();
            return;
        }

        iCodeVertify.doGeneratePhonePassCode(url, phone, options, new OnDataBackListener() {
            @Override
            public void onStart() {
                iAuthAccountNoAndRegBindView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iAuthAccountNoAndRegBindView.onDataBackSuccessForGetPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountNoAndRegBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iAuthAccountNoAndRegBindView.dismissLoadingDialog();
            }
        });
    }


    public void doValidatePhonePassCode(String url, String phone, String pass_code) {


        if (vertifyNotNull.isNullString(phone)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoUserName();
            return;
        }

        if (vertifyNotNull.isNullString(pass_code)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoVertifyCode();
            return;
        }

        iCodeVertify.doValidatePhonePassCode(url, phone, pass_code, new OnDataBackListener() {
            @Override
            public void onStart() {
                iAuthAccountNoAndRegBindView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iAuthAccountNoAndRegBindView.onDataBackSuccessForVertifyPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountNoAndRegBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iAuthAccountNoAndRegBindView.dismissLoadingDialog();

            }

            @Override
            public void onFinish() {


            }


        });
    }




    public void doRegister(String url,
                           String phone,
                           final String user_name,
                           final String password,
                           String pass_code,
                           String user_points) {


        if (vertifyNotNull.isNullString(user_name)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoUserName();
            return;
        }

        if (vertifyNotNull.isNullString(phone)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoUserName();
            return;
        }

        if (vertifyNotNull.isNullString(pass_code)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoVertifyCode();
            return;
        }


        if (vertifyNotNull.isNullString(password)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoPassWord();
            return;
        }


        iUser.doRegister(
                url,
                phone,
                user_name,
                password,
                pass_code,
                user_points, new OnDataBackListener() {
                    @Override
                    public void onStart() {



                    }

                    @Override
                    public void onSuccess(String data) {


                        iAuthAccountNoAndRegBindView.onDataBackSuccessForRegister(user_name, password);
                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iAuthAccountNoAndRegBindView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                        iAuthAccountNoAndRegBindView.dismissLoadingDialog();
                    }

                    @Override
                    public void onFinish() {




                    }
                });
    }


    public void doLogonToGetToken(String url, String user_name, String password) {


        if (vertifyNotNull.isNullString(user_name)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoUserName();
            return;
        }

        if (vertifyNotNull.isNullString(password)) {
            iAuthAccountNoAndRegBindView.onVertifyErrorForNoPassWord();
            return;
        }


        iUser.doLogon(url, user_name, password, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                if (vertifyNotNull.isNotNullObj(tokenInfo)) {

                    iAuthAccountNoAndRegBindView.onDataBackSuccessForGetToken(tokenInfo);
                } else {

                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iAuthAccountNoAndRegBindView.dismissLoadingDialog();
                }
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountNoAndRegBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iAuthAccountNoAndRegBindView.dismissLoadingDialog();
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
                        iAuthAccountNoAndRegBindView.onDataBackSuccessForBindWx(tokenInfo);
                    } else {

                        iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }


                } else {

                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountNoAndRegBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iAuthAccountNoAndRegBindView.dismissLoadingDialog();
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
                        iAuthAccountNoAndRegBindView.onDataBackSuccessForBindAli(tokenInfo);
                    } else {

                        iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }


                } else {

                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAuthAccountNoAndRegBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAuthAccountNoAndRegBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iAuthAccountNoAndRegBindView.dismissLoadingDialog();
            }
        });


    }

}
