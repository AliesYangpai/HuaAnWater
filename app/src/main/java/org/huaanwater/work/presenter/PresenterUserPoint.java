package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.UserPoint;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.ui.iview.IBalanceView;
import org.huaanwater.work.ui.iview.IUserPointView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/1/28.
 * 类描述
 * 版本
 */

public class PresenterUserPoint extends BasePresenter<IUserPointView> {


    private IUserPointView iUserPointView;

    private IUser iUser;
    private IRecord iRecord;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private IBaseDao<User> iUserDao;



    public PresenterUserPoint(IUserPointView iUserPointView) {
        this.iUserPointView = iUserPointView;
        this.iUser = new IUserImpl();
        this.iRecord = new IRecordImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iUserDao = new IUserDaoImpl();
    }



    public void doGetUserInfo(String url){

        iUser.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserPointView.showLoadingDialog();
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
                    iUserPointView.onDataBackSuccessForGetUserInfo(user);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserPointView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iUserPointView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iUserPointView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 下拉刷新获取数据
     * @param url
     */
    public void doGetUserInfoInFresh(String url){

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
                    iUserPointView.onDataBackSuccessForGetUserInfoInFresh(user);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iUserPointView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iUserPointView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iUserPointView.dismissFreshLoading();
            }
        });
    }





    /**
     * 获取用户积分
     * @param url
     * @param
     * @param size
     * @param index
     */
    public void doGetUserpointsReCords(String url,boolean ascending, int size, int index) {


        iRecord.doGetUserpointsReCords(url, ascending,size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iUserPointView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<UserPoint> list = parseSerilizable.getParseToList(data,UserPoint.class);

                if(vertifyNotNull.isNotNullListSize(list)) {
                    iUserPointView.onDataBackSuccessForGetUserPointRecords(list);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iUserPointView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iUserPointView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iUserPointView.dismissLoadingDialog();
            }
        });

    }





    public void doGetUserpointsReCordsInLoadMore(String url,boolean ascending,int size,int index) {


        iRecord.doGetUserpointsReCordsInLoadMore(url, ascending,size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {




                List<UserPoint> list = parseSerilizable.getParseToList(data,UserPoint.class);
                iUserPointView.onDataBackSuccessForGetUserPointRecordsInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iUserPointView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {

                    iUserPointView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
