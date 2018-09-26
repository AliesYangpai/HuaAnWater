package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.logic.LogicUser;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.IModifyPassView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/6.
 * 类描述
 * 版本
 */

public class PresenterModifyPassword extends BasePresenter<IModifyPassView> {


    private IModifyPassView iModifyPassView;
    private IUser iUser;
    private IBaseDao<User> iUserDao;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    private LogicUser logicUser;


    public PresenterModifyPassword(IModifyPassView iModifyPassView) {
        this.iModifyPassView = iModifyPassView;
        this.iUser = new IUserImpl();
        this.iUserDao = new IUserDaoImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();

        this.logicUser = new LogicUser();
    }


    public void doModifyPass(String url, String pass, String passAgain) {


        if (vertifyNotNull.isNullString(pass)) {
            iModifyPassView.doVertifyErrorForNoPass();
            return;
        }


        if (vertifyNotNull.isNullString(passAgain)) {
            iModifyPassView.doVertifyErrorForNoPassAgain();
            return;
        }


        if (!logicUser.isSame2Pass(pass, passAgain)) {
            iModifyPassView.doVertifyErrorForNotSame2Pass();
            return;
        }


        iUser.doEditPass(url, pass, new OnDataBackListener() {
            @Override
            public void onStart() {
                iModifyPassView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                if (vertifyNotNull.isNotNullString(data)) {

                    Boolean result = Boolean.valueOf(data);
                    if (result) {
                        iModifyPassView.onDataBackSuccessForModifyPass();
                    } else {
                        iModifyPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }
                } else {
                    iModifyPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iModifyPassView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iModifyPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {


                iModifyPassView.dismissLoadingDialog();
            }
        });

    }
}
