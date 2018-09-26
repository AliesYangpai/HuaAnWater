package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.Order;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.UserPoint;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.ui.iview.IOrderView;
import org.huaanwater.work.ui.iview.IUserPointView;
import org.huaanwater.work.util.StringUtil;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created  by Alie on 2018/1/28.
 * 类描述
 * 版本
 */

public class PresenterOrderList extends BasePresenter<IOrderView> {







    private IOrderView iOrderView;

    private IUser iUser;
    private IRecord iRecord;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private IBaseDao<User> iUserDao;



    public PresenterOrderList(IOrderView iOrderView) {
        this.iOrderView = iOrderView;
        this.iUser = new IUserImpl();
        this.iRecord = new IRecordImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iUserDao = new IUserDaoImpl();
    }



//    public void doGetUserInfo(String url){
//
//        iUser.doGetUserInfo(url, new OnDataBackListener() {
//            @Override
//            public void onStart() {
//                iOrderView.showLoadingDialog();
//            }
//
//            @Override
//            public void onSuccess(String data) {
//
//                User user = parseSerilizable.getParseToObj(data, User.class);
//
//                if (vertifyNotNull.isNotNullObj(user)) {
//
//                    List<User> list = iUserDao.findAll(User.class);
//                    if (vertifyNotNull.isNotNullListSize(list)) {
//                        iUserDao.deleteAll(User.class);
//                    }
//                    user.save();
//                    iOrderView.onDataBackSuccessForGetUserInfo(user);
//                }
//
//            }
//
//            @Override
//            public void onFail(int code, String data) {
//
//                ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
//                if(vertifyNotNull.isNotNullObj(errorEntity)) {
//                    iOrderView.onDataBackFail(code,errorEntity.getError_message());
//                }else {
//                    iOrderView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                iOrderView.dismissLoadingDialog();
//            }
//        });
//    }


//    /**
//     * 下拉刷新获取数据
//     * @param url
//     */
//    public void doGetUserInfoInFresh(String url){
//
//        iUser.doGetUserInfo(url, new OnDataBackListener() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onSuccess(String data) {
//
//                User user = parseSerilizable.getParseToObj(data, User.class);
//
//                if (vertifyNotNull.isNotNullObj(user)) {
//
//                    List<User> list = iUserDao.findAll(User.class);
//                    if (vertifyNotNull.isNotNullListSize(list)) {
//                        iUserDao.deleteAll(User.class);
//                    }
//                    user.save();
//                    iOrderView.onDataBackSuccessForGetUserInfoInFresh(user);
//                }
//
//            }
//
//            @Override
//            public void onFail(int code, String data) {
//
//                ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
//                if(vertifyNotNull.isNotNullObj(errorEntity)) {
//                    iOrderView.onDataBackFail(code,errorEntity.getError_message());
//                }else {
//                    iOrderView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                iOrderView.dismissFreshLoading();
//            }
//        });
//    }





    /**
     * 获取用户积分
     * @param url
     * @param
     * @param size
     * @param index
     */
    public void doGetOrderReCords(String url,boolean ascending, int size, int index) {


        iRecord.doGetOrderReCords(url, ascending,size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iOrderView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<Order> list = parseSerilizable.getParseToList(data,Order.class);


                int count = parseSerilizable.getParseCount(data);
                if(vertifyNotNull.isNotNullListSize(list)) {

                    iOrderView.onDataBackSuccessForGetOrderRecordsCount(String.valueOf(count));
                    iOrderView.onDataBackSuccessForGetOrderRecords(list);



                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iOrderView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iOrderView.dismissLoadingDialog();
            }
        });

    }





    public void doGetOrderReCordsInLoadMore(String url,boolean ascending,int size,int index) {


        iRecord.doGetOrderReCordsInLoadMore(url, ascending,size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {




                List<Order> list = parseSerilizable.getParseToList(data,Order.class);


                int count = parseSerilizable.getParseCount(data);
                iOrderView.onDataBackSuccessForGetOrderRecordsCount(String.valueOf(count));
                iOrderView.onDataBackSuccessForGetOrderRecordsInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {

                    iOrderView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {

            }
        });
    }

}
