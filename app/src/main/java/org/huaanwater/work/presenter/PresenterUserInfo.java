package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.Order;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.IUserInfoView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/1/18.
 * 类描述
 * 版本
 */

public class PresenterUserInfo extends BasePresenter<IUserInfoView> {

    private IUserInfoView iUserInfoView;
    private IUser iUser;
    private IRecord iRecord;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private IBaseDao<User> iUserDao;


    public PresenterUserInfo(IUserInfoView iUserInfoView) {
        this.iUserInfoView = iUserInfoView;
        this.iUser = new IUserImpl();
        this.iRecord = new IRecordImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iUserDao = new IUserDaoImpl();
    }


    public void doGetUserInfo(String url) {


        iUser.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserInfoView.showLoadingDialog();
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
                    iUserInfoView.onDataBackSuccessForGetUserInfo(user);
                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iUserInfoView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }


    public void doGetOrderCount(String url, boolean ascending, int size, int index) {

        iRecord.doGetOrderReCords(url, ascending,size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {



                int count = parseSerilizable.getParseCount(data);

                iUserInfoView.onDataBackSuccessForGetOrderCount(String.valueOf(count));




            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iUserInfoView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iUserInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iUserInfoView.dismissLoadingDialog();
            }
        });

    }


    public void doGetUserInfoFromDb() {


        User user = iUserDao.findFirst(User.class);

        if(vertifyNotNull.isNotNullObj(user)) {

        iUserInfoView.onDataBackSuccessForGetUserInfoFromDb(user);


        }
    }


    public void doSignIn(String url) {

        iUser.doSignIn(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iUserInfoView.onDataBackSuccessForSignIn(data);
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iUserInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iUserInfoView.dismissLoadingDialog();
            }
        });
    }
}
