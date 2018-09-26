package org.huaanwater.work.method.impl;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.huaanwater.work.http.CallServer;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.HttpSingleResponseListener;
import org.huaanwater.work.http.RequestPacking;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IRegion;

/**
 * Created by Alie on 2018/3/6.
 * 类描述
 * 版本
 */

public class IRegionImpl implements IRegion {
    @Override
    public void doGetFuzzyRegions(String url, String name, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("name",name);

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
