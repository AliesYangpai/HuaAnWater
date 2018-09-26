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
import org.huaanwater.work.ui.iview.IRegView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/1/22.
 * 类描述
 * 版本
 */

public class PresenterReg extends BasePresenter<IRegView> {

    private ICodeVertify iCodeVertify;
    private IUser iUser;
    private IRegView iRegView;

    private IBaseDao<User> iUserDao;


    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterReg(IRegView iRegView) {
        this.iRegView = iRegView;
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iCodeVertify = new ICodeVertifyImpl();
        this.iUser = new IUserImpl();
        this.iUserDao = new IUserDaoImpl();

    }


    public void doGeneratePhonePassCode(String url, String phone, String options) {


        if (vertifyNotNull.isNullString(phone)) {

            iRegView.onVertifyErrorForNoVertifyCode();
            return;
        }

        iCodeVertify.doGeneratePhonePassCode(url, phone, options, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iRegView.onDataBackSuccessForGetPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iRegView.dismissLoadingDialog();
            }
        });
    }


    public void doValidatePhonePassCode(String url, String phone, String pass_code) {


        if (vertifyNotNull.isNullString(phone)) {
            iRegView.onVertifyErrorForNoUserName();
            return;
        }

        if (vertifyNotNull.isNullString(pass_code)) {
            iRegView.onVertifyErrorForNoVertifyCode();
            return;
        }

        iCodeVertify.doValidatePhonePassCode(url, phone, pass_code, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iRegView.onDataBackSuccessForVertifyPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iRegView.dismissLoadingDialog();

            }

            @Override
            public void onFinish() {


            }


        });
    }


    public void doRegister(String url,
                           String phone,
                           String user_name,
                           String password,
                           String pass_code,
                           String user_points) {


        if (vertifyNotNull.isNullString(user_name)) {
            iRegView.onVertifyErrorForNoUserName();
            return;
        }


        if (vertifyNotNull.isNullString(phone)) {
            iRegView.onVertifyErrorForNoUserName();
            return;
        }

        if (vertifyNotNull.isNullString(pass_code)) {
            iRegView.onVertifyErrorForNoVertifyCode();
            return;
        }


        if (vertifyNotNull.isNullString(password)) {
            iRegView.onVertifyErrorForNoPass();
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
//                        User user = parseSerilizable.getParseToObj(data, User.class);
//                        if (vertifyNotNull.isNotNullObj(user)) {
//                            List<User> userList = iUserDao.findAll(User.class);
//
//                            if (vertifyNotNull.isNotNullListSize(userList)) {
//                                iUserDao.deleteAll(User.class);
//                            }
//                            iUserDao.save(user);
//                        }
                        iRegView.onDataBackSuccessForReg();
                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iRegView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iRegView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {


                        iRegView.dismissLoadingDialog();

                    }
                });
    }
}
