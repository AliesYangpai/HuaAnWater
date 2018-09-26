package org.huaanwater.work.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.thirdabout.ali.AliAuthInfo;
import org.huaanwater.work.entity.thirdabout.ali.AliParameters;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.entity.thirdabout.wx.WxParameters;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterAuthAccountHasAndBind;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IAuthAccountHasAndBindView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.ToastUtil;


/**
 * 曾经注册过账号，这里进行登录绑定
 */
public class AuthAccountHasAndBindActivity extends BaseActivity<IAuthAccountHasAndBindView, PresenterAuthAccountHasAndBind>
        implements
        IAuthAccountHasAndBindView,
        OnClickListener{


    private PresenterAuthAccountHasAndBind presenterAuthAccountHasAndBind;


    private ImageView iv_common_back;
    private ImageView iv_third_head;
    private TextView tv_third_name;

    private EditText et_username;
    private ImageView iv_clear_account;
    private EditText et_password;
    private ImageView iv_clear_pass;


    private ImageView iv_bind;

    /**
     * 数据相关
     * @param savedInstanceState
     */

    private Bundle passBundle;
    private String currentTag;//微信的还是支付宝的
    private ValidateWxEntity validateWxEntity;
    private ValidateAliEntity validateAliEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_account_has_and_bind);

        switch (currentTag) {
            case ConstLocalData.WX:

                validateWxEntity = (ValidateWxEntity) passBundle.getSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY);
                presenterAuthAccountHasAndBind.doSetWxInfoToUi(validateWxEntity);

                break;


            case ConstLocalData.ALI:

                validateAliEntity = (ValidateAliEntity) passBundle.getSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY);
                presenterAuthAccountHasAndBind.doSetAliInfoToUi(validateAliEntity);

                break;
        }
    }

    @Override
    protected PresenterAuthAccountHasAndBind creatPresenter() {
        if (null == presenterAuthAccountHasAndBind) {
            presenterAuthAccountHasAndBind = new PresenterAuthAccountHasAndBind(this);
        }
        return presenterAuthAccountHasAndBind;
    }

    @Override
    protected void initViews() {

        iv_common_back = findQHYViewById(R.id.iv_common_back);
        iv_third_head = findQHYViewById(R.id.iv_third_head);
        tv_third_name = findQHYViewById(R.id.tv_third_name);

        et_username = findQHYViewById(R.id.et_username);
        iv_clear_account = findQHYViewById(R.id.iv_clear_account);
        et_password = findQHYViewById(R.id.et_password);
        iv_clear_pass = findQHYViewById(R.id.iv_clear_pass);


        iv_bind = findQHYViewById(R.id.iv_bind);


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);

        iv_clear_account.setOnClickListener(this);
        iv_clear_pass.setOnClickListener(this);
        iv_bind.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();
        if(null != intent) {
            passBundle = intent.getExtras();
            if(null != passBundle) {
                currentTag =  passBundle.getString(ConstIntent.BundleKEY.THIRD_AUTH_TAG,ConstIntent.BundleValue.DEFAULT_STRING);
            }
        }
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
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void doSetWxUserInfoToUi(ValidateWxEntity validateWxEntity) {


        WxAuthInfo wxAuthInfo = validateWxEntity.getWxAuthInfo();
        String headimgurl = wxAuthInfo.getHeadimgurl();
        String nickname = wxAuthInfo.getNickname();
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(headimgurl,iv_third_head,R.drawable.img_client_normal,120);
        tv_third_name.setText(nickname);
    }

    @Override
    public void doSetAliUserInfoToUi(ValidateAliEntity validateAliEntity) {


        AliAuthInfo aliAuthInfo = validateAliEntity.getAliAuthInfo();
        String headimgurl = aliAuthInfo.getAvatar();
        String nickname = aliAuthInfo.getNick_name();
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(headimgurl,iv_third_head,R.drawable.img_client_normal,150);
        tv_third_name.setText(nickname);

    }

    @Override
    public void onVertifyErrorForNoUserName() {
        ToastUtil.showMsg(getApplicationContext(),R.string.enter_username_login);


    }

    @Override
    public void onVertifyErrorForNoPassWord() {

        ToastUtil.showMsg(getApplicationContext(),R.string.enter_password_login);

    }

    @Override
    public void onDataBackSuccessForGetToken(TokenInfo tokenInfo) {

        String access_token = tokenInfo.getAccess_token();



        switch (currentTag) {


            case ConstLocalData.WX:
                WxParameters parameters = validateWxEntity.getParameters();
                String code = validateWxEntity.getCode();
                presenterAuthAccountHasAndBind.doBindWx(
                        HttpConst.URL.BIND_AUTH,
                        access_token,
                        code,
                        currentTag,
                        parameters);
                break;


            case ConstLocalData.ALI:

                AliParameters aliParameters = validateAliEntity.getParameters();
                String aliCode = validateAliEntity.getCode();


                presenterAuthAccountHasAndBind.doBindAli(
                        HttpConst.URL.BIND_AUTH,
                        access_token,
                        aliCode,
                        currentTag,
                        aliParameters);


                break;
        }

    }

    @Override
    public void onDataBackSuccessForBindWx(TokenInfo tokenInfo) {


        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.THIRD_BIND_SUCCESS,tokenInfo);
        openActivityAndFinishItself(LoginActivity.class,bundle);
    }

    @Override
    public void onDataBackSuccessForBindAli(TokenInfo tokenInfo) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.THIRD_BIND_SUCCESS,tokenInfo);
        openActivityAndFinishItself(LoginActivity.class,bundle);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.iv_clear_account:
                et_username.setText(ConstSign.DOUBLE_QUOTE);
                break;

            case R.id.iv_clear_pass:
                et_password.setText(ConstSign.DOUBLE_QUOTE);
                break;

            case R.id.iv_bind:


                String useName = et_username.getText().toString().trim();
                String passWord = et_password.getText().toString().trim();
                presenterAuthAccountHasAndBind.doLogonToGetToken(HttpConst.URL.LOGON,useName,passWord);

                break;
        }
    }
}
