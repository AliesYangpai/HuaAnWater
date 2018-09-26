package org.huaanwater.work.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstEvent;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstLog;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.entity.EventEntity;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.ali.fromali.AuthResult;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterLogin;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.ILoginView;
import org.huaanwater.work.util.SpUtil;
import org.huaanwater.work.util.ToastUtil;

import java.util.Map;
import java.util.UUID;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity<ILoginView, PresenterLogin> implements
        ILoginView,
        View.OnClickListener {


    private PresenterLogin presenterLogin;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;


    private EditText et_username;
    private EditText et_password;
    private ImageView iv_clear_account;
    private ImageView iv_pass_right;


    private RelativeLayout rl_login;
    private RelativeLayout rl_reg;
    private ImageView iv_wx_login;
    private ImageView iv_qq_login;
    private ImageView iv_ali_login;

    private TextView tv_reg;


    /**
     * 检查获取授权要素
     * @param authResult
     * @return
     */
    private boolean checkAuthElementBack(AuthResult authResult) {

        boolean result = false;

        if (null != authResult) {
            String resultStatus = authResult.getResultStatus();
            // 判断resultStatus 为“9000”且result_code
            // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
            if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                // 获取alipay_open_id，调支付时作为参数extern_token 的value
                // 传入，则支付账户为该授权账户

                Log.i(ConstLog.THIRD_AUTH, "支付宝获取授权要素成功返回：" + authResult.getAuthCode());

                result = true;

            } else {


                result = false;

                ToastUtil.showMsg(getApplicationContext(), "授权失败");

                Log.i(ConstLog.THIRD_AUTH, "支付宝获取授权要素失败：" + authResult.getAuthCode());
            }


        }


        return result;

    }


    /**
     * wx授权登陆路
     */
    private void doWxGetImportantCode() {


        String uuid = UUID.randomUUID().toString();

        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, ConstLocalData.WX_APPID, true);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = uuid;
        wxapi.sendReq(req);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        EventBus.getDefault().register(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }




    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();
        if (null != bundle) {

            TokenInfo tokenInfo = (TokenInfo) bundle.getSerializable(ConstIntent.BundleKEY.THIRD_BIND_SUCCESS);
            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, tokenInfo.getAccess_token());
            SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_IS_LOGIN, ConstSp.SP_VALUE.IS_LOGIN);

            presenterLogin.doGetUserInfo(HttpConst.URL.USER_CURRENT);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected PresenterLogin creatPresenter() {
        if (null == presenterLogin) {
            presenterLogin = new PresenterLogin(this);
        }
        return presenterLogin;
    }

    @Override
    protected void initViews() {

        /**
         * title
         *
         */

        iv_common_back = findQHYViewById(R.id.iv_common_back);


        et_username = findQHYViewById(R.id.et_username);
        et_password = findQHYViewById(R.id.et_password);


        tv_reg = findQHYViewById(R.id.tv_reg);


        iv_clear_account = findQHYViewById(R.id.iv_clear_account);
        iv_pass_right = findQHYViewById(R.id.iv_pass_right);


        rl_login = findQHYViewById(R.id.rl_login);
        rl_reg = findQHYViewById(R.id.rl_reg);
        iv_wx_login = findQHYViewById(R.id.iv_wx_login);
        iv_qq_login = findQHYViewById(R.id.iv_qq_login);
        iv_ali_login = findQHYViewById(R.id.iv_ali_login);
    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);

        tv_reg.setOnClickListener(this);
        iv_pass_right.setOnClickListener(this);
        iv_clear_account.setOnClickListener(this);
        rl_login.setOnClickListener(this);
        rl_reg.setOnClickListener(this);
        iv_wx_login.setOnClickListener(this);

        iv_qq_login.setOnClickListener(this);

        iv_ali_login.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForLogon(TokenInfo tokenInfo) {


        presenterLogin.doGetUserInfo(HttpConst.URL.USER_CURRENT);


    }

    @Override
    public void onDataBackSuccessForGetUserInfo(User user) {

        openActivityAndFinishItself(UserInfoActivity.class, null);

    }

    @Override
    public void onDataBackSuccessForGetPhoneCode() {
        ToastUtil.showMsg(getApplicationContext(), R.string.phone_code_sended);
    }

    @Override
    public void onDataBackSuccessForWxNotAuth(ValidateWxEntity validateWxEntity) {
        Intent intent = new Intent(this, AuthAccountSelectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.THIRD_AUTH_TAG, validateWxEntity.getAuth_type());
        bundle.putSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY, validateWxEntity);
        intent.putExtras(bundle);
        startActivityForResult(intent, ConstIntent.RequestCode.NOT_BIND_TO_SELECT_ACCOUNT_TO_BIND);
    }

    @Override
    public void onDataBackSuccessForWxHasAuth(TokenInfo tokenInfo) {

        String access_token = tokenInfo.getAccess_token();

        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, access_token);
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_IS_LOGIN, ConstSp.SP_VALUE.IS_LOGIN);

        presenterLogin.doGetUserInfo(HttpConst.URL.USER_CURRENT);

    }




    /**
     * 获取到了阿里授权要素，这里拉起阿里并获取相应code
     * @param element
     */
    @Override
    public void onDataBackSuccessForGetAliAuthParamElement(String element) {

        final String authInfo = element;
        Log.i(ConstLog.THIRD_AUTH, element);
        if (!TextUtils.isEmpty(element)) {


            Observer<AuthResult> observer = new Observer<AuthResult>() {
                @Override
                public void onCompleted() {


                }

                @Override
                public void onError(Throwable e) {
                    Log.i(" rxJavaxxxx", " onError ---> " + e);
                }

                @Override
                public void onNext(AuthResult authResult) {
                    Log.i(" rxJavaxxxx", " onNext ---> " + authResult.toString());

                    if (checkAuthElementBack(authResult)) {


                        String authCode = authResult.getAuthCode();
                        String user_id = authResult.getUser_id();

                        /**
                         * 这里开始验证调用验证方法
                         */
                        presenterLogin.doValidateAliAuth(HttpConst.URL.VALID_AUTH,authCode,ConstLocalData.ALI,user_id);


                    }
                }
            };


            Observable.create(new Observable.OnSubscribe<AuthResult>() {
                @Override
                public void call(Subscriber<? super AuthResult> subscriber) {
                    AuthTask authTask = new AuthTask(LoginActivity.this);
                    Map<String, String> map = authTask.authV2(authInfo, true);
                    AuthResult authResult = new AuthResult(map, true);
                    subscriber.onNext(authResult);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    @Override
    public void onDataBackSuccessForAliHasAuth(TokenInfo tokenInfo) {

        String access_token = tokenInfo.getAccess_token();
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, access_token);
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_IS_LOGIN, ConstSp.SP_VALUE.IS_LOGIN);
        presenterLogin.doGetUserInfo(HttpConst.URL.USER_CURRENT);

    }

    @Override
    public void onDataBackSuccessForAliNotAuth(ValidateAliEntity validateAliEntity) {
        Intent intent = new Intent(this, AuthAccountSelectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.THIRD_AUTH_TAG, validateAliEntity.getAuth_type());
        bundle.putSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY, validateAliEntity);
        intent.putExtras(bundle);
        startActivityForResult(intent, ConstIntent.RequestCode.NOT_BIND_TO_SELECT_ACCOUNT_TO_BIND);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventInActivty(EventEntity eventEntity) {


        if (null != eventEntity) {


            String notifyMsg = eventEntity.getNotifyMsg();

            switch (eventEntity.getNotifyTag()) {

                case ConstEvent.WXABOUT.GET_CODE_SUCCESS:

                    Log.i(ConstLog.THIRD_AUTH, eventEntity.getNotifyMsg());

                    presenterLogin.doValidateWxAuth(HttpConst.URL.VALID_AUTH, notifyMsg, ConstLocalData.WX);

                    break;

                case ConstEvent.WXABOUT.GET_CODE_CANCEL:


                    ToastUtil.showMsg(getApplicationContext(), notifyMsg);

                    break;


                case ConstEvent.WXABOUT.GET_CODE_FAIL:

                    break;



            }

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.rl_login:
                String useName = et_username.getText().toString().trim();
                String passWord = et_password.getText().toString().trim();
                presenterLogin.doLogin(HttpConst.URL.LOGON, useName, passWord);
                break;

            case R.id.rl_reg:

                openActivity(RegActivity.class, null);

                break;

            case R.id.tv_reg:


                openActivity(RegActivity.class, null);

                break;

            case R.id.iv_clear_account:
                et_username.setText(ConstSign.DOUBLE_QUOTE);
                break;


            case R.id.iv_pass_right:


                et_password.setText(ConstSign.DOUBLE_QUOTE);
//                String phone = et_username.getText().toString().trim();
//                presenterLogin.doGeneratePhonePassCode(
//                        HttpConst.URL.GENERATE_PASS_CODE,
//                        phone,
//                        ConstCode.ANYWAY);
                break;
            case R.id.iv_wx_login:


                doWxGetImportantCode();


                break;

            case R.id.iv_qq_login:

                ToastUtil.showMsg(getApplicationContext(), "qq授权登录");
                break;

            case R.id.iv_ali_login:

                presenterLogin.doGetAuthParamatersForAli(HttpConst.URL.ALI_AUTH_PARAM);

                break;



        }


    }
}
