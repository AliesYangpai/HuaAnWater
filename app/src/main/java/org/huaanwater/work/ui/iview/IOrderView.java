package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.Order;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.UserPoint;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/1/28.
 * 类描述
 * 版本
 */

public interface IOrderView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);





    void dismissFreshLoading();

    void onDataBackFailInLoadMore(int code, String errorMsg);



//    void onDataBackSuccessForGetUserInfo(User user);
//
//    void onDataBackSuccessForGetUserInfoInFresh(User user);



    void onDataBackSuccessForGetOrderRecordsCount(String count);

    void onDataBackSuccessForGetOrderRecords(List<Order> orders);

    void onDataBackSuccessForGetOrderRecordsInLoadMore(List<Order> orders);



}
