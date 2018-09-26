package org.huaanwater.work.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstCode;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterModityPhone;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IModifyPhoneView;
import org.huaanwater.work.util.ToastUtil;

public class ModifyPhoneActivity extends BaseActivity<IModifyPhoneView, PresenterModityPhone> implements
        IModifyPhoneView,
        OnClickListener {


    private PresenterModityPhone presenterModityPhone;


    private ImageView iv_common_back;
    private TextView tv_common_title;


    private EditText et_reg_name;
    private ImageView iv_clear_account;


    private EditText et_reg_pass;
    private ImageView iv_clear_pass;


    private EditText et_reg_phone_code;
    private ImageView iv_generate_phone_code;


    private RelativeLayout rl_reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_phone);
    }

    @Override
    protected PresenterModityPhone creatPresenter() {
        if (null == presenterModityPhone) {

            presenterModityPhone = new PresenterModityPhone(this);
        }
        return presenterModityPhone;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_common_title = findQHYViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.modify_phone));
        et_reg_name = findQHYViewById(R.id.et_reg_name);
        iv_clear_account = findQHYViewById(R.id.iv_clear_account);
        et_reg_pass = findQHYViewById(R.id.et_reg_pass);
        iv_clear_pass = findQHYViewById(R.id.iv_clear_pass);

        et_reg_phone_code = findQHYViewById(R.id.et_reg_phone_code);
        iv_generate_phone_code = findQHYViewById(R.id.iv_generate_phone_code);
        rl_reg = findQHYViewById(R.id.rl_reg);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        iv_clear_account.setOnClickListener(this);
        iv_clear_pass.setOnClickListener(this);
        iv_generate_phone_code.setOnClickListener(this);


        rl_reg.setOnClickListener(this);
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
    public void onVertifyErrorForNoPhone() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_user_phone);
    }

    @Override
    public void onVertifyErrorForNoVertifyCode() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_vertify_code);
    }

    @Override
    public void onVertifyErrorForNoPass() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_password_login);
    }

    @Override
    public void onDataBackSuccessForGetPhoneCode() {
        ToastUtil.showMsg(getApplicationContext(), R.string.phone_code_sended);
    }

    @Override
    public void onDataBackSuccessForVertifyPhoneCode() {
        String phone = et_reg_name.getText().toString().trim();

        String phoneCode = et_reg_phone_code.getText().toString().trim();
        String pass = et_reg_pass.getText().toString().trim();

        presenterModityPhone.doModifyPhone(
                HttpConst.URL.USER_MODIFY_PHONE,
                phone,
                phoneCode,
                pass);

    }

    @Override
    public void onDataBackSuccessForModifyPhone() {


        presenterModityPhone.doGetUserInfo(HttpConst.URL.USER_CURRENT);
    }

    @Override
    public void onDataBackSuccessForGetUserInfo(User user) {
        this.setResult(ConstIntent.ResponseCode.GO_TO_MODIFY_PHONE_SUCCESS);
        dofinishItself();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
                presenterModityPhone.doGeneratePhonePassCode(
                        HttpConst.URL.GENERATE_PASS_CODE,
                        phone1,
                        ConstCode.USER_NOT_EXISTED);
                break;

            case R.id.rl_reg:
                String phone2 = et_reg_name.getText().toString().trim();
                String phoneCode = et_reg_phone_code.getText().toString().trim();
                String pass = et_reg_pass.getText().toString().trim();

                presenterModityPhone.doValidatePhonePassCode(
                        HttpConst.URL.VALIDATE_PASS_CODE,
                        phone2,
                        phoneCode,
                        pass);
                break;
        }
    }
}
