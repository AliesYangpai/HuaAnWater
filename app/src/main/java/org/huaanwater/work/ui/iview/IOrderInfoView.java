package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.Order;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/3/2.
 * 类描述
 * 版本
 */

public interface IOrderInfoView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForOrderInfo(Order order);

    void onSetOrderInfoToUi(Order order);


    void onSetOrderInfoStatusToUi(String status);

    void onSetOrderInfoTypeToUi(String type);
}
