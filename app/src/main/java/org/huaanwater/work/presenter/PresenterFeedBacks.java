package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.feedback.FeedBack;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IFeedBack;
import org.huaanwater.work.method.impl.IFeedBackImpl;
import org.huaanwater.work.ui.iview.IFeedBacksView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class PresenterFeedBacks extends BasePresenter<IFeedBacksView> {

    private IFeedBacksView iFeedBacksView;
    private IFeedBack iFeedBack;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    public PresenterFeedBacks(IFeedBacksView iFeedBacksView) {
        this.iFeedBacksView = iFeedBacksView;
        this.iFeedBack = new IFeedBackImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
    }



    public void doGetFeedBacks(String url, boolean ascending, int page_size, int page_index) {

        iFeedBack.doGetFeedBacks(url, ascending, page_size, page_index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFeedBacksView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<FeedBack> list = parseSerilizable.getParseToList(data,FeedBack.class);



                if(vertifyNotNull.isNotNullListSize(list)) {
                    iFeedBacksView.onDataBackSuccessForGetFeedBacks(list);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFeedBacksView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iFeedBacksView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iFeedBacksView.dismissLoadingDialog();
            }
        });
    }



    public void doGetFeedBacksInEditSuccess(String url, boolean ascending, int page_size, int page_index) {

        iFeedBack.doGetFeedBacks(url, ascending, page_size, page_index, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {

                List<FeedBack> list = parseSerilizable.getParseToList(data,FeedBack.class);



                if(vertifyNotNull.isNotNullListSize(list)) {
                    iFeedBacksView.onDataBackSuccessForGetFeedBacks(list);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFeedBacksView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iFeedBacksView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
            }
        });
    }


    /**
     * 下拉刷新
     * @param url
     * @param
     * @param
     * @param
     * @param
     */
    public void doGetFeedBacksInFresh(String url,boolean ascending, int page_size, int page_index) {



        iFeedBack.doGetFeedBacksInFresh(url, ascending, page_size, page_index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {
                List<FeedBack> list = parseSerilizable.getParseToList(data,FeedBack.class);
                iFeedBacksView.onDataBackSuccessForGetFeedBacksInFresh(list);
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iFeedBacksView.onDataBackFail(code,errorEntity.getError_message());
                }else  {

                    iFeedBacksView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {
                iFeedBacksView.dismissFreshLoading();
            }
        });

    }





    /**
     * 上拉加载更多
     * @param url
     * @param
     * @param
     * @param
     * @param
     */
    public void doGetFeedBacksInLoadMore(String url,boolean ascending,int page_size, int page_index ) {

        iFeedBack.doGetFeedBacksInLoadMore(url, ascending, page_size, page_index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {
                List<FeedBack> list = parseSerilizable.getParseToList(data,FeedBack.class);
                iFeedBacksView.onDataBackSuccessForGetFeedBacksInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFeedBacksView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {
                    iFeedBacksView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });


    }


}
