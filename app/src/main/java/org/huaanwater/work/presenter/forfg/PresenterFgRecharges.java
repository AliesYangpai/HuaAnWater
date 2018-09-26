package org.huaanwater.work.presenter.forfg;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.RechargeRecord;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.presenter.BasePresenter;
import org.huaanwater.work.ui.iview.IFgRechargesView;
import org.huaanwater.work.verification.VertifyNotNull;
import org.w3c.dom.ProcessingInstruction;

import java.util.List;

/**
 * Created by Alie on 2018/3/2.
 * 类描述
 * 版本
 */

public class PresenterFgRecharges extends BasePresenter<IFgRechargesView> {

    private IFgRechargesView iFgRechargesView;
    private IRecord iRecord;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterFgRecharges(IFgRechargesView iFgRechargesView) {
        this.iFgRechargesView = iFgRechargesView;
        this.iRecord = new IRecordImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void doGetRechargeRecords(String url, boolean ascending ,int page_size, int page_index){

        iRecord.doGetRechargeReCords(url,ascending, page_size, page_index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<RechargeRecord> list = parseSerilizable.getParseToList(data,RechargeRecord.class);



                if(vertifyNotNull.isNotNullListSize(list)) {

                    iFgRechargesView.onDataBackSuccessForGetRecharges(list);
                }

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFgRechargesView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iFgRechargesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doGetRechargeRecordsInLoadMore(String url, boolean ascending ,int page_size, int page_index){

        iRecord.doGetRechargeReCordsInLoadMore(url, ascending,page_size, page_index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                List<RechargeRecord> list = parseSerilizable.getParseToList(data,RechargeRecord.class);
                iFgRechargesView.onDataBackSuccessForGetRechargesInLoadMore(list);


            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFgRechargesView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {
                    iFgRechargesView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

            }
        });
    }

}
