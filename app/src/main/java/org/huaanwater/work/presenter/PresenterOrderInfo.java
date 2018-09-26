package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.Order;
import org.huaanwater.work.function.FunctionOrder;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.ui.iview.IOrderInfoView;
import org.huaanwater.work.verification.VertifyNotNull;

/**
 * Created by Alie on 2018/3/2.
 * 类描述
 * 版本
 */

public class PresenterOrderInfo extends BasePresenter<IOrderInfoView> {

    private IOrderInfoView iOrderInfoView;
    private IRecord iRecord;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    private FunctionOrder functionOrder;

    public PresenterOrderInfo(IOrderInfoView iOrderInfoView) {
        this.iOrderInfoView = iOrderInfoView;
        this.iRecord = new IRecordImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.functionOrder = new FunctionOrder();
    }


//    public void doGetOrderInfo(String url) {
//
//
//        iRecord.doGetOrderInfo(url, new OnDataBackListener() {
//            @Override
//            public void onStart() {
//                iOrderInfoView.showLoadingDialog();
//            }
//
//            @Override
//            public void onSuccess(String data) {
//
//
//                iOrderInfoView.onDataBackSuccessForOrderInfo(data);
//            }
//
//            @Override
//            public void onFail(int code, String data) {
//                ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
//                if(vertifyNotNull.isNotNullObj(errorEntity)) {
//                    iOrderInfoView.onDataBackFail(code,errorEntity.getError_message());
//                }else {
//                    iOrderInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                iOrderInfoView.dismissLoadingDialog();
//            }
//        });
//
//    }


    public void doSetOrderInfoToUi(Order order) {





        iOrderInfoView.onSetOrderInfoToUi(order);
        iOrderInfoView.onSetOrderInfoStatusToUi(functionOrder.getHzOrderStatus(order.getStatus()));
        iOrderInfoView.onSetOrderInfoTypeToUi(functionOrder.getHzOrderType(order.getType()));


    }
}
