package org.huaanwater.work.method.impl;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.http.CallServer;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.http.HttpSingleResponseListener;
import org.huaanwater.work.http.RequestPacking;
import org.huaanwater.work.http.requestparam.UserParam;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IUser;

/**
 * Created by Alie on 2018/1/13.
 * 类描述
 * 版本
 */

public class IUserImpl implements IUser {


    private UserParam userParam;


    public IUserImpl() {
        this.userParam = new UserParam();
    }

    @Override
    public void doRegister(String url,
                           String phone,
                           String user_name,
                           String password,
                           String pass_code,
                           String user_points, final OnDataBackListener onDataBackListener) {


        String param = userParam.getRegisterParam(phone, user_name, password, pass_code, user_points);

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
                onDataBackListener.onFail(response.responseCode(), response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });

    }

    @Override
    public void doLogon(String url,
                        String user_name,
                        String password, final OnDataBackListener onDataBackListener) {

        String param = userParam.getLogonParam(user_name, password);

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
                onDataBackListener.onFail(response.responseCode(), response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });

    }

    @Override
    public void doGetUserInfo(String url, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

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
    public void doGetUserIntegral(String url, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

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
    public void doEditUserInfoCommit(String url,
                                     String userName,
                                     String userType,
                                     String address,
                                     int regionId,
                                     String studentNo,
                                     final OnDataBackListener onDataBackListener) {


        url = url + ConstSign.QUESTION_MARK + "UserName" + ConstSign.EQUAL_SIGN + userName + ConstSign.AND +
                "UserType"+ConstSign.EQUAL_SIGN+userType+ConstSign.AND+
                "Address" + ConstSign.EQUAL_SIGN + address+ConstSign.AND+
                "RegionId"+ConstSign.EQUAL_SIGN+regionId+ConstSign.AND+
                "StudentNo"+ConstSign.EQUAL_SIGN+studentNo;


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.PUT, null);

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
    public void doEditPass(String url, String pass, final OnDataBackListener onDataBackListener) {

        url = url + ConstSign.QUESTION_MARK + "password" + ConstSign.EQUAL_SIGN + pass;


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.PUT, null);

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
    public void doEditPhone(String url, String Phone, String PassCode, String Password, final OnDataBackListener onDataBackListener) {


        url = url + ConstSign.QUESTION_MARK + "Phone" + ConstSign.EQUAL_SIGN + Phone +
                ConstSign.AND + "PassCode" + ConstSign.EQUAL_SIGN + PassCode +
                ConstSign.AND + "Password" + ConstSign.EQUAL_SIGN + Password;


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.PUT, null);

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
    public void doModifyAvatar(String url, String avatar, final OnDataBackListener onDataBackListener) {


        url = url + ConstSign.QUESTION_MARK + "avatar" + ConstSign.EQUAL_SIGN + avatar;

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.PUT, null);

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
    public void doSignIn(String url, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.PUT, null);

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
