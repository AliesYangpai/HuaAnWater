package org.huaanwater.work.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstCode;
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
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.presenter.PresenterAuthAccountNoAndRegBind;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IAuthAccountNoAndRegBindView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.ToastUtil;


/**
 * 曾经未注册过账户，这里进行注册-登录-绑定
 */
public class AuthAccountNoAndRegBindActivity extends BaseActivity<IAuthAccountNoAndRegBindView, PresenterAuthAccountNoAndRegBind>
 implements
        IAuthAccountNoAndRegBindView,
        View.OnClickListener{


    private PresenterAuthAccountNoAndRegBind presenterAuthAccountNoAndRegBind;


    private ImageView iv_common_back;
    private ImageView iv_third_head;
    private TextView tv_third_name;

    private EditText et_reg_name;
    private ImageView iv_clear_account;
    private EditText et_reg_pass;
    private ImageView iv_clear_pass;
    private EditText et_reg_phone_code;
    private ImageView iv_generate_phone_code;
    private ImageView iv_reg_bind;



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
        setContentView(R.layout.activity_auth_account_no_and_reg_bind);


        switch (currentTag) {
            case ConstLocalData.WX:

                validateWxEntity = (ValidateWxEntity) passBundle.getSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY);
                presenterAuthAccountNoAndRegBind.doSetWxInfoToUi(validateWxEntity);
                break;

            case ConstLocalData.ALI:
                validateAliEntity = (ValidateAliEntity) passBundle.getSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY);
                presenterAuthAccountNoAndRegBind.doSetAliInfoToUi(validateAliEntity);

                break;
        }
    }

    @Override
    protected PresenterAuthAccountNoAndRegBind creatPresenter() {
        if (null == presenterAuthAccountNoAndRegBind) {
            presenterAuthAccountNoAndRegBind = new PresenterAuthAccountNoAndRegBind(this);
        }
        return presenterAuthAccountNoAndRegBind;
    }

    @Override
    protected void initViews() {


        iv_common_back = findQHYViewById(R.id.iv_common_back);
        iv_third_head = findQHYViewById(R.id.iv_third_head);
        tv_third_name = findQHYViewById(R.id.tv_third_name);

        et_reg_name = findQHYViewById(R.id.et_reg_name);
        iv_clear_account = findQHYViewById(R.id.iv_clear_account);
        et_reg_pass = findQHYViewById(R.id.et_reg_pass);
        iv_clear_pass = findQHYViewById(R.id.iv_clear_pass);
        et_reg_phone_code = findQHYViewById(R.id.et_reg_phone_code);
        iv_generate_phone_code = findQHYViewById(R.id.iv_generate_phone_code);
        iv_reg_bind = findQHYViewById(R.id.iv_reg_bind);
    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        iv_clear_account.setOnClickListener(this);
        iv_clear_pass.setOnClickListener(this);
        iv_generate_phone_code.setOnClickListener(this);
        iv_reg_bind.setOnClickListener(this);
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
    public void onVertifyErrorForNoVertifyCode() {
        ToastUtil.showMsg(getApplicationContext(),R.string.enter_vertify_code);
    }

    @Override
    public void onDataBackSuccessForGetPhoneCode() {
        ToastUtil.showMsg(getApplicationContext(), R.string.phone_code_sended);
    }

    @Override
    public void onDataBackSuccessForVertifyPhoneCode() {

        String userName = et_reg_name.getText().toString().trim();
        String pass = et_reg_pass.getText().toString().trim();
        String phoneCode = et_reg_phone_code.getText().toString().trim();


        presenterAuthAccountNoAndRegBind.doRegister(
                HttpConst.URL.REGISTER,
                userName,
                userName,
                pass,
                phoneCode,
                ConstSign.DOUBLE_QUOTE);


    }

    @Override
    public void onDataBackSuccessForRegister(String userName, String passWord) {


        presenterAuthAccountNoAndRegBind.doLogonToGetToken(HttpConst.URL.LOGON,userName,passWord);
    }

    @Override
    public void onDataBackSuccessForGetToken(TokenInfo tokenInfo) {
        String access_token = tokenInfo.getAccess_token();

        switch (currentTag) {

            case ConstLocalData.WX:
                WxParameters parameters = validateWxEntity.getParameters();
                String code = validateWxEntity.getCode();
                presenterAuthAccountNoAndRegBind.doBindWx(
                        HttpConst.URL.BIND_AUTH,
                        access_token,
                        code,
                        currentTag,
                        parameters);
                break;


            case ConstLocalData.ALI:
                AliParameters aliParameters= validateAliEntity.getParameters();
                String aliCode = validateAliEntity.getCode();
                presenterAuthAccountNoAndRegBind.doBindAli(
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
        switch (v.getId()){

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.iv_clear_account:
                et_reg_name.setText(ConstSign.DOUBLE_QUOTE);

                break;

            case R.id.iv_clear_pass:

                et_reg_pass.setText(ConstSign.DOUBLE_QUOTE);
                break;

            case R.id.iv_generate_phone_code:

                String phone1 = et_reg_name.getText().toString().trim();
                presenterAuthAccountNoAndRegBind.doGeneratePhonePassCode(
                        HttpConst.URL.GENERATE_PASS_CODE,
                        phone1,
                        ConstCode.USER_NOT_EXISTED);

                break;

            case R.id.iv_reg_bind:



                String phone2 = et_reg_name.getText().toString().trim();
                String phoneCode= et_reg_phone_code.getText().toString().trim();


                presenterAuthAccountNoAndRegBind.doValidatePhonePassCode(
                        HttpConst.URL.VALIDATE_PASS_CODE,
                        phone2,
                        phoneCode);


                break;
        }
    }
}
