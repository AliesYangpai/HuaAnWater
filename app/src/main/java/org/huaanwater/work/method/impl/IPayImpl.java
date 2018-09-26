package org.huaanwater.work.method.impl;

import com.google.gson.JsonElement;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.huaanwater.work.entity.recharge.ali.checkresult.AliCheckResultInfo;
import org.huaanwater.work.http.CallServer;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.HttpSingleResponseListener;
import org.huaanwater.work.http.RequestPacking;
import org.huaanwater.work.http.requestparam.IPayParam;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IPay;

/**
 * Created by Alie on 2018/2/9.
 * 类描述
 * 版本
 */

public class IPayImpl implements IPay {


    private IPayParam iPayParam;


    public IPayImpl() {
        this.iPayParam = new IPayParam();
    }

    @Override
    public void doGetPayChannels(String url, String order_type, String os_type, String device_type, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("order_type", order_type);
        request.add("os_type", os_type);
        request.add("device_type", device_type);


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


    /**
     * 支付宝充值
     *
     * @param url
     * @param order_type
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param amount
     * @param payer
     * @param onDataBackListener
     */
    @Override
    public void doBalanceRechargeAli(
            String url,
            String order_type,
            String channel_id,
            String os_type,
            String device_type,
            String device_id,
            String amount,
            String payer, final OnDataBackListener onDataBackListener) {


        String jsonString = iPayParam.getRechargeAliParam(
                order_type,
                channel_id,
                os_type,
                device_type,
                device_id,
                amount,
                payer);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.POST,
                jsonString);

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
    public void doBalanceRechargeWx(
            String url,
            String order_type,
            String channel_id,
            String os_type,
            String device_type,
            String device_id,
            String amount,
            String payer, final OnDataBackListener onDataBackListener) {



        String jsonString = iPayParam.getRechargeWxParam(
                order_type,
                channel_id,
                os_type,
                device_type,
                device_id,
                amount,
                payer);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.POST,
                jsonString);

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
    public void doCheckAliPayResult(
            String url,
            AliCheckResultInfo aliCheckResultInfo,
            String app_type,
            String os_type,
            String payer, final OnDataBackListener onDataBackListener) {


        String param = iPayParam.getCheckAlipayResultParam(aliCheckResultInfo, app_type, os_type, payer);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.POST,
                param);

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




//    /**
//     * 微信验签 【微信支付成功后，不用进行验签，因此此方法作废】
//     *
//     * @param url
//     */
//    @Override
//    public void doWxCheckWxPayResultInfo(
//            String url,
//            String app_type,
//            String os_type,
//            String payer, int payment_record_id, final OnDataBackListener onDataBackListener) {
//
//
//
//        String param = iPayParam.getCheckWxpayResultParam(app_type, os_type, payer,payment_record_id);
//
//        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
//                url,
//                RequestMethod.POST,
//                param);
//
//        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
//            @Override
//            protected void OnHttpStart(int what) {
//
//                onDataBackListener.onStart();
//
//            }
//
//            @Override
//            protected void OnHttpSuccessed(int what, Response<String> response) {
//
//
//                onDataBackListener.onSuccess(response.get());
//            }
//
//            @Override
//            protected void onHttpFailed(int what, Response<String> response) {
//
//
//                onDataBackListener.onFail(response.responseCode(), response.get());
//
//            }
//
//            @Override
//            protected void OnHttpFinish(int what) {
//
//
//                onDataBackListener.onFinish();
//
//            }
//        });
//
//    }
}
