package org.huaanwater.work.method.impl;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.huaanwater.work.http.CallServer;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.HttpSingleResponseListener;
import org.huaanwater.work.http.RequestPacking;
import org.huaanwater.work.http.requestparam.PhoneCodeParam;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.ICodeVertify;

/**
 * Created by Alie on 2018/1/13.
 * 类描述
 * 版本
 */

public class ICodeVertifyImpl implements ICodeVertify {


    private PhoneCodeParam phoneCodeParam;

    public ICodeVertifyImpl() {
        phoneCodeParam = new PhoneCodeParam();
    }

    @Override
    public void doGeneratePhonePassCode(String url, String phone, String options, final OnDataBackListener onDataBackListener) {

        String param = phoneCodeParam.getGeneratePhoneCodeParam(phone, options);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, param);

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




    @Override
    public void doValidatePhonePassCode(String url,
                                        String phone,
                                        String pass_code, final OnDataBackListener onDataBackListener) {

        String param = phoneCodeParam.getValidatePhoneCodeParam(phone, pass_code);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, param);

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
