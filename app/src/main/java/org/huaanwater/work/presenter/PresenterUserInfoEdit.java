package org.huaanwater.work.presenter;

import android.util.Log;

import com.google.gson.JsonElement;
import com.jph.takephoto.model.TResult;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.OnUploadListener;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.constant.ConstLog;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.Region;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.thirdabout.ali.AliAuthInfo;
import org.huaanwater.work.entity.thirdabout.ali.AliParameters;
import org.huaanwater.work.entity.thirdabout.ali.BindAliEntity;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.authlistabout.Authed;
import org.huaanwater.work.entity.thirdabout.wx.BindWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.entity.thirdabout.wx.WxParameters;
import org.huaanwater.work.function.FunctionUser;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.logic.LogicThird;
import org.huaanwater.work.logic.LogicUser;
import org.huaanwater.work.method.IRegion;
import org.huaanwater.work.method.IThirdAuth;
import org.huaanwater.work.method.IUpload;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.IRegionImpl;
import org.huaanwater.work.method.impl.IThirdAuthImpl;
import org.huaanwater.work.method.impl.IUploadImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.IUserInfoEditView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Alie on 2018/1/18.
 * 类描述
 * 版本
 */

public class PresenterUserInfoEdit extends BasePresenter<IUserInfoEditView> {

    private IUserInfoEditView iUserInfoEditView;
    private IUser iUser;

    private IUpload iUpload;
    private IThirdAuth iThirdAuth;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    private IRegion iRegion;
    private IBaseDao<User> iUserDao;
    private FunctionUser functionUser;
    private LogicUser logicUser;
    private LogicThird logicThird;

    public PresenterUserInfoEdit(IUserInfoEditView iUserInfoEditView) {
        this.iUserInfoEditView = iUserInfoEditView;
        this.iUser = new IUserImpl();
        this.iUpload = new IUploadImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iUserDao = new IUserDaoImpl();
        this.iThirdAuth = new IThirdAuthImpl();
        this.logicUser = new LogicUser();
        this.iRegion = new IRegionImpl();
        this.functionUser = new FunctionUser();
        this.logicThird = new LogicThird();
    }


    public void doGetUserDataFromDbToUi() {

        User user = iUserDao.findFirst(User.class);
        if (vertifyNotNull.isNotNullObj(user)) {


            iUserInfoEditView.onDbDataBackSuccessForUser(user);


            if (vertifyNotNull.isNotNullString(user.getAn_xin_account())) {
                iUserInfoEditView.onDbDataBackSuccessForHasBindAnXinAccount();
            }


            iUserInfoEditView.onDbDataBackSuccessForUserTypeHz(functionUser.getUserTypeHz(user.getUser_type()));

            if (logicUser.isShowStudentNoLayout(user.getUser_type())) {
                iUserInfoEditView.onDbDataBackSuccessForUserTypeShowTypeStudentLayout();
            } else {

                iUserInfoEditView.onDbDataBackSuccessForUserTypeShowTypeOtherLayout();
            }


        }


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
                    iUserInfoEditView.onDataBackSuccessForGetUserInfo(user);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iUserInfoEditView.dismissLoadingDialog();
            }
        });

    }


    public void doGetUserInfoAfterAnXinBind(String url) {


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
                    iUserInfoEditView.onDataBackSuccessForGetUserInfoAfterAnxinBind(user);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iUserInfoEditView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 权限检查
     */
    public void doPermissionCheck() {

        iUserInfoEditView.doPermissionCheck();

    }


    /**
     * 权限关闭提醒
     */
    public void doShowPermissionAlert() {

        iUserInfoEditView.doShowPermissionAlert();

    }

    /**
     * 显示photopopWindow
     */

    public void doShowPhotoPopWindow() {
        iUserInfoEditView.doShowPhotoPopWindow();
    }


    public void doUpload(String url, TResult result) {


        if (!vertifyNotNull.isNotNullObj(result)) {
            iUserInfoEditView.doVertifyErrorForNullFlie();
            return;

        }

        File file = new File(result.getImage().getCompressPath());


        FileBinary fileBinary = new FileBinary(file);

        Log.i("uploadsss", "文件的长度：" + file.length() + " fileBinary:" + fileBinary.getBinaryLength());

        fileBinary.setUploadListener(HttpConst.HTTP_WHAT, new OnUploadListener() {
            @Override
            public void onStart(int what) {
                iUserInfoEditView.onUploadStart(what);

            }

            @Override
            public void onCancel(int what) {
                iUserInfoEditView.onUploadCancel(what);


            }

            @Override
            public void onProgress(int what, int progress) {
                iUserInfoEditView.onUploadProgress(what, progress);
            }

            @Override
            public void onFinish(int what) {
                iUserInfoEditView.onUploadFinish(what);

//                iUserInfoEditView.dismissLoadingDialog();
            }

            @Override
            public void onError(int what, Exception exception) {
                iUserInfoEditView.onUploadError(what, exception);
            }
        });


        iUpload.doUpLoad(url, fileBinary, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserInfoEditView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iUserInfoEditView.onDataBackSuccessForUpload(data);

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iUserInfoEditView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 提交用户信息
     *
     * @param url
     * @param userName
     * @param address
     */


    public void doCommitUserInfo(
            String url,
            String userName,
            String currentUserTypeCode,
            String address,
            int currentRegionId,
            String studentNo) {

        if (vertifyNotNull.isNullString(userName)) {
            iUserInfoEditView.doVertifyErrorForNoUserName();
            return;
        }

        if (vertifyNotNull.isNullString(address)) {
            iUserInfoEditView.doVertifyErrorForNoAddress();
            return;
        }

        if (logicUser.isStudtentType(currentUserTypeCode)) {
            if (vertifyNotNull.isNullString(studentNo)) {
                iUserInfoEditView.doVertifyErrorForNoStudentNo();
                return;
            }
        }


        iUser.doEditUserInfoCommit(
                url,
                userName,
                currentUserTypeCode,
                address,
                currentRegionId,
                studentNo, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iUserInfoEditView.showLoadingDialog();
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
                            iUserInfoEditView.onDataBackSuccessForEditUserInfo(user);
                        } else {
                            iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                        iUserInfoEditView.dismissLoadingDialog();

                    }
                });


    }


    public void doGetAuthodritionList(String url) {


        iThirdAuth.doGetAuthoritionList(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserInfoEditView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<Authed> list = parseSerilizable.getParseToNoItemsList(data, Authed.class);

                if (vertifyNotNull.isNotNullListSize(list)) {
                    iUserInfoEditView.onDataBackSuccessForGetAuthroitions(list);
                } else {

                    iUserInfoEditView.onDataBackSuccessForNoAuthroitions();

                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {


                iUserInfoEditView.dismissLoadingDialog();

            }
        });
    }


    public void doGetAuthodritionListAfterBind(String url) {


        iThirdAuth.doGetAuthoritionList(url, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {

                List<Authed> list = parseSerilizable.getParseToNoItemsList(data, Authed.class);

                if (vertifyNotNull.isNotNullListSize(list)) {
                    iUserInfoEditView.onDataBackSuccessForGetAuthroitions(list);
                } else {

                    iUserInfoEditView.onDataBackSuccessForNoAuthroitions();

                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {


            }
        });
    }

    /**
     * 修改头像地址
     *
     * @param url
     * @param avatar
     */
    public void doModifyAvatar(String url, String avatar) {

        iUser.doModifyAvatar(url, avatar, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserInfoEditView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                if (!TextUtil.isEmpty(data) && Boolean.valueOf(data)) {
                    iUserInfoEditView.onDataBackSuccessForModifyAvatar();
                } else {

                    iUserInfoEditView.dismissLoadingDialog();
                }

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iUserInfoEditView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {


            }
        });
    }


    /**
     * 处理是否要显示智能提示文本
     *
     * @param text
     */
    public void doDealAlertSearchList(String text) {

        if (logicUser.isHasEnter(text)) {
            iUserInfoEditView.doShowSearchReminder(text);
        } else {

            iUserInfoEditView.doHideSearchReminder();
        }
    }


    /**
     * 模糊查询区域名称
     *
     * @param url
     * @param name
     */
    public void doGetFuzzySearch(String url, String name) {


        iRegion.doGetFuzzyRegions(url, name, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {
                List<Region> list = parseSerilizable.getParseToNoItemsList(data, Region.class);

                if (vertifyNotNull.isNotNullListSize(list)) {
                    iUserInfoEditView.onDataBackSuccessForFuzzySearchRegions(list);
                } else {

                    iUserInfoEditView.onDataBackErrorForFuzzySearchRegions();


                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);


                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iUserInfoEditView.onDataBackErrorForFuzzySearchRegions();

            }

            @Override
            public void onFinish() {

            }
        });
    }


    /**
     * 初始化popwindow
     */
    public void doSearchRemindPopWindowShow(List<Region> regions) {


        iUserInfoEditView.doSearchRemindPopWindowShow(regions);
    }


    public void doSearchRemindPopWindowDismiss() {

        iUserInfoEditView.doSearchRemindPopWindowdismiss();
    }


    /**
     * 销毁popwindow
     */
    public void doSearchRemindPopWindowDestory() {


        iUserInfoEditView.doSearchRemindPopWindowDestory();
    }


    /**
     * 判断执行是否进行安信绑定
     *
     * @param anxinAccount
     */
    public void doDealAnxinBind(String anxinAccount) {


        if (TextUtil.isEmpty(anxinAccount)) {

            iUserInfoEditView.doAnxinBind();

        } else {

            iUserInfoEditView.doVertifyErrorForHasAnxinBind();

        }

    }

    public void doDealWxBind(List<Authed> autheds) {

        if (logicThird.isWxBindInList(autheds)) {


            iUserInfoEditView.doVertifyErrorForHasWxBind();


        } else {
            iUserInfoEditView.doWxBindAbout();
        }


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
                iUserInfoEditView.showLoadingDialog();
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


                    if (!logicThird.isWxAuthed(wxParameters)) {


                        validateWxEntity.setWxAuthInfo(wxAuthInfo);
                        iUserInfoEditView.onDataBackSuccessForWxNotAuth(validateWxEntity);
                        iUserInfoEditView.dismissLoadingDialog();
                    } else {

                        iUserInfoEditView.doVertifyErrorForWxHasBindAnotherAlready();

                        iUserInfoEditView.dismissLoadingDialog();
                    }


                } else {

                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iUserInfoEditView.dismissLoadingDialog();
                }


            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iUserInfoEditView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {


            }
        });

    }


    /**
     * 绑定微信
     *
     * @param url
     * @param accessToken
     * @param code
     * @param auth_type
     * @param parameters
     */
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
                        iUserInfoEditView.onDataBackSuccessForBindWx(tokenInfo);
                    } else {

                        iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }


                } else {

                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iUserInfoEditView.dismissLoadingDialog();
            }
        });


    }


    public void doDealAliBind(List<Authed> autheds) {

        if (logicThird.isAliBindInList(autheds)) {


            iUserInfoEditView.doVertifyErrorForHasAliBind();


        } else {
            iUserInfoEditView.doAliBindAbout();
        }


    }


    public void doGetAuthParamatersForAli(String url) {

        iThirdAuth.doGetAuthParamatersForAli(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserInfoEditView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                if (vertifyNotNull.isNotNullString(data)) {

                    iUserInfoEditView.onDataBackSuccessForGetAliAuthParamElement(data);
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {
                iUserInfoEditView.dismissLoadingDialog();
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
                iUserInfoEditView.showLoadingDialog();
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


                    if (!logicThird.isAliAuthed(aliParameters)) {

                        /**
                         * 说明支付宝并未绑定
                         */

                        validateAliEntity.setAliAuthInfo(aliAuthInfo);
                        iUserInfoEditView.onDataBackSuccessForAliNotAuth(validateAliEntity);
                    } else {

                        iUserInfoEditView.doVertifyErrorForAliHasBindAnotherAlready();
                        iUserInfoEditView.dismissLoadingDialog();
                    }


                } else {

                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iUserInfoEditView.dismissLoadingDialog();
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iUserInfoEditView.dismissLoadingDialog();
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
                        iUserInfoEditView.onDataBackSuccessForBindAli(tokenInfo);
                    } else {

                        iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }


                } else {

                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoEditView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iUserInfoEditView.dismissLoadingDialog();
            }
        });


    }




}
