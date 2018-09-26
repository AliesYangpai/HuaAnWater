package org.huaanwater.work.presenter.forfg;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.Consume;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.presenter.BasePresenter;
import org.huaanwater.work.ui.iview.IFgConsumesView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/3/2.
 * 类描述
 * 版本
 */

public class PresenterFgConsumes extends BasePresenter<IFgConsumesView> {

    private IFgConsumesView iFgConsumesView;
    private IRecord iRecord;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterFgConsumes(IFgConsumesView iFgConsumesView) {
        this.iFgConsumesView = iFgConsumesView;
        this.iRecord = new IRecordImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void doGetConsumeRecords(String url, boolean ascending,int size, int index){

        iRecord.doGetConsumeRecords(url,  ascending,size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<Consume> list = parseSerilizable.getParseToList(data,Consume.class);



                if(vertifyNotNull.isNotNullListSize(list)) {
                    iFgConsumesView.onDataBackSuccessForGetConsumes(list);
                }




            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFgConsumesView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iFgConsumesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doGetConsumeRecordsInLoadMore(String url, boolean ascending,int size, int index){

        iRecord.doGetConsumeReCordsInLoadMore(url,ascending, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                List<Consume> list = parseSerilizable.getParseToList(data,Consume.class);
                iFgConsumesView.onDataBackSuccessForGetConsumesInLoadMore(list);


            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFgConsumesView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {
                    iFgConsumesView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

            }
        });
    }

}
