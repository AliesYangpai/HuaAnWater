package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IActive;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.impl.IActiveImpl;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.ui.iview.IActiveParticipateView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/3/16.
 * 类描述
 * 版本
 */

public class PresenterActiveParticipateList extends BasePresenter<IActiveParticipateView> {

    private IActiveParticipateView iActiveParticipateView;
    private IRecord iRecord;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterActiveParticipateList(IActiveParticipateView iActiveParticipateView) {
        this.iActiveParticipateView = iActiveParticipateView;
        this.iRecord = new IRecordImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    /**
     * 获取已经参加的人员信息
     *
     * @param url
     * @param activity_management_id
     * @param ascending
     * @param size
     * @param index
     */
    public void doGetActivesParticipatesRecord(
            String url,
            long activity_management_id,
            boolean ascending,
            int size,
            int index) {

        iRecord.doGetActivesParticipateRecord(url,
                activity_management_id,
                ascending,
                size,
                index, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iActiveParticipateView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        List<ActiveParticipate> list = parseSerilizable.getParseToList(data, ActiveParticipate.class);
                        int parseCount = parseSerilizable.getParseCount(data);
                        if (vertifyNotNull.isNotNullListSize(list)) {

                            iActiveParticipateView.onDataBackSuccessForParticipateCount(parseCount);
                            iActiveParticipateView.onDataBackSuccessForParticipateList(list);
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iActiveParticipateView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iActiveParticipateView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                        iActiveParticipateView.dismissLoadingDialog();
                    }
                }
        );
    }



    /**
     * 下拉刷新
     * @param url
     * @param activity_management_id
     * @param ascending
     * @param size
     * @param index
     */
    public void doGetActivesParticipatesRecordInFresh(String url,
                                                      long activity_management_id,
                                                      boolean ascending,
                                                      int size,
                                                      int index) {




        iRecord.doGetActivesParticipateRecord(
                url,
                activity_management_id,
                ascending,
                size,
                index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<ActiveParticipate> list = parseSerilizable.getParseToList(data, ActiveParticipate.class);
                iActiveParticipateView.onDataBackSuccessForGetParticipateListInFresh(list);
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iActiveParticipateView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iActiveParticipateView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {
                iActiveParticipateView.dismissFreshLoading();
            }
        });

    }




    /**
     * 上拉加载更多
     * @param url
     * @param activity_management_id
     * @param ascending
     * @param size
     * @param index
     */
    public void doGetActivesParticipatesRecordInLoadMore(String url,
                                                         long activity_management_id,
                                                         boolean ascending,
                                                         int size,
                                                         int index) {

        iRecord.doGetActivesParticipateRecord(
                url,
                activity_management_id,
                ascending,
                size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                List<ActiveParticipate> list = parseSerilizable.getParseToList(data, ActiveParticipate.class);
                iActiveParticipateView.onDataBackSuccessForGetParticipateListInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iActiveParticipateView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iActiveParticipateView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }


}
