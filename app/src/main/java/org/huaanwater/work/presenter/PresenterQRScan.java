package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IWater;
import org.huaanwater.work.method.impl.IWaterImpl;
import org.huaanwater.work.ui.iview.IQRScanView;
import org.huaanwater.work.verification.VertifyNotNull;

/**
 * Created by Alie on 2018/1/22.
 * 类描述
 * 版本
 */

public class PresenterQRScan extends BasePresenter<IQRScanView> {

    private IQRScanView iQrScanView;
    private IWater iWater;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterQRScan(IQRScanView iQrScanView) {
        this.iQrScanView = iQrScanView;
        this.iWater = new IWaterImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }



    public void doOutPutWater(String url, String imei, String order_no) {

        iWater.doOutPutWater(url, imei, order_no, new OnDataBackListener() {
            @Override
            public void onStart() {
                iQrScanView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                iQrScanView.onDataBackSuccessForOutPutWater(data);
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iQrScanView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iQrScanView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iQrScanView.dismissLoadingDialog();
            }
        });

    }


}
