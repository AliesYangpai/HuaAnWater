package org.huaanwater.work.method.impl;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.huaanwater.work.http.CallServer;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.HttpSingleResponseListener;
import org.huaanwater.work.http.RequestPacking;
import org.huaanwater.work.http.requestparam.WaterParam;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IWater;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class IWaterImpl implements IWater {

    private WaterParam waterParam;


    public IWaterImpl() {
        this.waterParam = new WaterParam();
    }

    @Override
    public void doOutPutWater(String url, String imei, String order_no, final OnDataBackListener onDataBackListener) {
        String param = waterParam.getOutPutWaterParam(imei, order_no);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST, param);

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
                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });
    }
}
