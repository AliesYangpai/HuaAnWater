package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.ICodeVertify;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.ICodeVertifyImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.IModifyPhoneView;
import org.huaanwater.work.verification.VertifyNotNull;
import org.w3c.dom.ProcessingInstruction;

import java.util.List;

/**
 * Created by Alie on 2018/2/6.
 * 类描述
 * 版本
 */

public class PresenterModityPhone extends BasePresenter<IModifyPhoneView> {

    private IModifyPhoneView iModifyPhoneView;
    private IUser iUser;
    private ICodeVertify iCodeVertify;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;
    private IBaseDao<User> iUserDao;


    public PresenterModityPhone(IModifyPhoneView iModifyPhoneView) {
        this.iModifyPhoneView = iModifyPhoneView;
        this.iUser = new IUserImpl();
        this.iCodeVertify = new ICodeVertifyImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.iUserDao = new IUserDaoImpl();
    }



    public void doGeneratePhonePassCode(String url,
                                        String phone,
                                        String options) {


        iCodeVertify.doGeneratePhonePassCode(url, phone, options, new OnDataBackListener() {
            @Override
            public void onStart() {
                iModifyPhoneView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iModifyPhoneView.onDataBackSuccessForGetPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iModifyPhoneView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iModifyPhoneView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iModifyPhoneView.dismissLoadingDialog();

            }
        });

    }


    public void doValidatePhonePassCode(String url, String phone, String pass_code,String pass){
        if (vertifyNotNull.isNullString(phone)) {
            iModifyPhoneView.onVertifyErrorForNoPhone();
            return;
        }

        if (vertifyNotNull.isNullString(pass_code)) {
            iModifyPhoneView.onVertifyErrorForNoVertifyCode();
            return;
        }

        if(vertifyNotNull.isNullString(pass)) {

            iModifyPhoneView.onVertifyErrorForNoPass();
            return;
        }

        iCodeVertify.doValidatePhonePassCode(url, phone, pass_code, new OnDataBackListener() {
            @Override
            public void onStart() {
                iModifyPhoneView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iModifyPhoneView.onDataBackSuccessForVertifyPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iModifyPhoneView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iModifyPhoneView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iModifyPhoneView.dismissLoadingDialog();

            }

            @Override
            public void onFinish() {


            }


        });
    }


    public void doModifyPhone(String url,String Phone,String PassCode,String Password) {

        iUser.doEditPhone(url, Phone, PassCode, Password, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {



                if(vertifyNotNull.isNotNullString(data)) {

                    Boolean result = Boolean.valueOf(data);
                    if(result) {
                        iModifyPhoneView.onDataBackSuccessForModifyPhone();
                    }else {

                        iModifyPhoneView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        iModifyPhoneView.dismissLoadingDialog();
                    }
                }else {
                    iModifyPhoneView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iModifyPhoneView.dismissLoadingDialog();
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iModifyPhoneView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iModifyPhoneView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iModifyPhoneView.dismissLoadingDialog();

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
                    iModifyPhoneView.onDataBackSuccessForGetUserInfo(user);
                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iModifyPhoneView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iModifyPhoneView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iModifyPhoneView.dismissLoadingDialog();
            }
        });

    }

}
