package org.huaanwater.work.method.impl;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.huaanwater.work.http.CallServer;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.HttpSingleResponseListener;
import org.huaanwater.work.http.RequestPacking;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRecord;

/**
 * Created by Alie on 2018/2/2.
 * 类描述
 * 版本
 */

public class IRecordImpl implements IRecord {


    @Override
    public void doGetRechargeReCords(String url,boolean ascending ,int page_size, int page_index,final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("page_size", page_size);
        request.add("page_index", page_index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetRechargeReCordsInLoadMore(
            String url,
            boolean ascending ,
            int page_size,
            int page_index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("page_size", page_size);
        request.add("page_index", page_index);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetUserpointsReCords(String url, boolean ascending, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("size", size);
        request.add("index", index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetUserpointsReCordsInLoadMore(String url, boolean ascending, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("size", size);
        request.add("index", index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetOrderReCords(String url, boolean ascending, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("page_size", size);
        request.add("page_index", index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetOrderReCordsInLoadMore(String url, boolean ascending, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("page_size", size);
        request.add("page_index", index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetOrderInfo(String url, final OnDataBackListener onDataBackListener) {



        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetConsumeRecords(String url, boolean ascending, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("size", size);
        request.add("index", index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetConsumeReCordsInLoadMore(String url, boolean ascending, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("ascending",ascending);
        request.add("size", size);
        request.add("index", index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }

    @Override
    public void doGetActivesParticipateRecord(String url, long activity_management_id,boolean ascending, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);
        request.add("activity_management_id",activity_management_id);
        request.add("ascending",ascending);
        request.add("size", size);
        request.add("index", index);



        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                onDataBackListener.onStart();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(), response.get());

            }

            @Override
            protected void OnHttpFinish(int what) {


                onDataBackListener.onFinish();

            }
        });
    }
}
