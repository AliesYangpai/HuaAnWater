package org.huaanwater.work.presenter;

import android.util.Log;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.constant.ConstLog;
import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.thirdabout.ali.AliAuthInfo;
import org.huaanwater.work.entity.thirdabout.ali.AliParameters;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.entity.thirdabout.wx.WxParameters;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.logic.LogicThird;
import org.huaanwater.work.method.ICodeVertify;
import org.huaanwater.work.method.IThirdAuth;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.ICodeVertifyImpl;
import org.huaanwater.work.method.impl.IThirdAuthImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.ILoginView;
import org.huaanwater.work.util.SpUtil;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/1/17.
 * 类描述
 * 版本
 */

public class PresenterLogin extends BasePresenter<ILoginView> {

    private ILoginView iLoginView;
    private IUser iUser;
    private IThirdAuth iThirdAuth;

    private ICodeVertify iCodeVertify;

    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    private IBaseDao<User> iUserDao;


    /**
     * logic
     *
     * @param iLoginView
     */

    private LogicThird logicThird;

    public PresenterLogin(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.iUser = new IUserImpl();
        this.iThirdAuth = new IThirdAuthImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iUserDao = new IUserDaoImpl();
        this.iCodeVertify = new ICodeVertifyImpl();
        this.logicThird = new LogicThird();
    }


    public void doLogin(String url,
                        String user_name,
                        String password) {


        iUser.doLogon(url, user_name, password, new OnDataBackListener() {
            @Override
            public void onStart() {
                iLoginView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                if (vertifyNotNull.isNotNullObj(tokenInfo)) {

                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, tokenInfo.getAccess_token());
                    SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_IS_LOGIN, ConstSp.SP_VALUE.IS_LOGIN);
                    iLoginView.onDataBackSuccessForLogon(tokenInfo);

                } else {

                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iLoginView.dismissLoadingDialog();
                }
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iLoginView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }


    public void doGetUserInfo(String url) {

        iUser.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {


            }

            @Override
            public void onSuccess(String data) {

                User user = parseSerilizable.getParseToObj(data, User.class);

                if (vertifyNotNull.isNotNullObj(user)) {

                    List<User> list = iUserDao.findAll(User.class);
                    if (vertifyNotNull.isNotNullListSize(list)) {
                        iUserDao.deleteAll(User.class);
                    }
                    user.save();

                    iLoginView.onDataBackSuccessForGetUserInfo(user);
                } else {


                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iLoginView.dismissLoadingDialog();
            }
        });

    }


    public void doGeneratePhonePassCode(String url, String phone, String options) {
        iCodeVertify.doGeneratePhonePassCode(url, phone, options, new OnDataBackListener() {
            @Override
            public void onStart() {
                iLoginView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iLoginView.onDataBackSuccessForGetPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iLoginView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 验证微信授权
     *
     * @param url
     * @param code
     * @param auth_type
     */
    public void doValidateWxAuth(String url, final String code, String auth_type) {


        iThirdAuth.doValidateAuthForWx(url, code, auth_type, new OnDataBackListener() {
            @Override
            public void onStart() {
                iLoginView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                Log.i(ConstLog.THIRD_AUTH, "验证绑定结果：" + data);
                ValidateWxEntity validateWxEntity = parseSerilizable.getParseToObj(data, ValidateWxEntity.class);

                if (vertifyNotNull.isNotNullObj(validateWxEntity)) {


                    validateWxEntity.setCode(code);
                    String auth_info = validateWxEntity.getAuth_info();
                    WxAuthInfo wxAuthInfo = parseSerilizable.getSpecialParseWxAthInfo(auth_info);
                    WxParameters wxParameters = validateWxEntity.getParameters();


                    if (logicThird.isWxAuthed(wxParameters)) {

                        /**
                         * 说明微信已经绑定成功
                         * 这里实现解析token bingd成功
                         */
                        TokenInfo tokenInfo = validateWxEntity.getToken();

                        if (vertifyNotNull.isNotNullObj(tokenInfo)) {
                            iLoginView.onDataBackSuccessForWxHasAuth(tokenInfo);
                        } else {
                            iLoginView.onDataBackFail(ConstError.ERROR_WX_BIND_CODE, ConstError.ERROR_WX_BIND_MSG);
                            iLoginView.dismissLoadingDialog();
                        }
                    } else {

                        /**
                         * 说明微信并未绑定
                         */
                        validateWxEntity.setWxAuthInfo(wxAuthInfo);
                        iLoginView.onDataBackSuccessForWxNotAuth(validateWxEntity);
                        iLoginView.dismissLoadingDialog();
                    }


                } else {

                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iLoginView.dismissLoadingDialog();
                }


            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iLoginView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {


            }
        });

    }


    public void doGetAuthParamatersForAli(String url) {

        iThirdAuth.doGetAuthParamatersForAli(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iLoginView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                if (vertifyNotNull.isNotNullString(data)) {

                    iLoginView.onDataBackSuccessForGetAliAuthParamElement(data);
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {
                iLoginView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 验证是否已经进行了ali授权
     *
     * @param url
     * @param code
     * @param auth_type
     */
    public void doValidateAliAuth(String url, final String code, String auth_type, String user_id) {


        iThirdAuth.doValidateAuthFoAli(url, code, auth_type, user_id, new OnDataBackListener() {
            @Override
            public void onStart() {
                iLoginView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                Log.i(ConstLog.THIRD_AUTH, "验证绑定结果：" + data);


                ValidateAliEntity validateAliEntity = parseSerilizable.getParseToObj(data, ValidateAliEntity.class);

                if (vertifyNotNull.isNotNullObj(validateAliEntity)) {


                    validateAliEntity.setCode(code);
                    String auth_info = validateAliEntity.getAuth_info();
                    AliAuthInfo aliAuthInfo = parseSerilizable.getSpecialParseAliAthInfo(auth_info);
                    AliParameters aliParameters = validateAliEntity.getParameters();


                    if (logicThird.isAliAuthed(aliParameters)) {

                        /**
                         * 说明支付宝已经绑定成功
                         * 这里实现解析token bingd成功
                         */
                        TokenInfo tokenInfo = validateAliEntity.getToken();

                        if (vertifyNotNull.isNotNullObj(tokenInfo)) {
                            iLoginView.onDataBackSuccessForAliHasAuth(tokenInfo);
                        } else {
                            iLoginView.onDataBackFail(ConstError.ERROR_ALI_BIND_CODE, ConstError.ERROR_ALI_BIND_MSG);
                            iLoginView.dismissLoadingDialog();
                        }
                    } else {

                        /**
                         * 说明支付宝并未绑定
                         */
                        validateAliEntity.setAliAuthInfo(aliAuthInfo);
                        iLoginView.onDataBackSuccessForAliNotAuth(validateAliEntity);
                        iLoginView.dismissLoadingDialog();
                    }


                } else {

                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iLoginView.dismissLoadingDialog();
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iLoginView.dismissLoadingDialog();
            }
        });
    }


}
