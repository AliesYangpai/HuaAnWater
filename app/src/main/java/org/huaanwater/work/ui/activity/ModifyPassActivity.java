package org.huaanwater.work.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterModifyPassword;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IModifyPassView;
import org.huaanwater.work.util.ToastUtil;

public class ModifyPassActivity extends BaseActivity<IModifyPassView, PresenterModifyPassword>
        implements
        IModifyPassView,
        OnClickListener {


    private PresenterModifyPassword presenterModifyPassword;


    /**
     * title
     *
     * @param savedInstanceState
     */


    private ImageView iv_common_back;
    private TextView tv_common_title;


    private EditText et_user_pass;
    private ImageView iv_pass_clear;
    private EditText et_user_pass_again;
    private ImageView iv_pass_again_clear;
    private RelativeLayout rl_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pass);
    }

    @Override
    protected PresenterModifyPassword creatPresenter() {
        if (null == presenterModifyPassword) {
            presenterModifyPassword = new PresenterModifyPassword(this);
        }
        return presenterModifyPassword;
    }

    @Override
    protected void initViews() {


        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_common_title = findQHYViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.modify_pass));
        et_user_pass = findQHYViewById(R.id.et_user_pass);
        iv_pass_clear = findQHYViewById(R.id.iv_pass_clear);
        et_user_pass_again = findQHYViewById(R.id.et_user_pass_again);
        iv_pass_again_clear = findQHYViewById(R.id.iv_pass_again_clear);
        rl_submit = findQHYViewById(R.id.rl_submit);

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        iv_pass_clear.setOnClickListener(this);
        iv_pass_again_clear.setOnClickListener(this);
        rl_submit.setOnClickListener(this);

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
    public void doVertifyErrorForNoPass() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_password_login);
    }

    @Override
    public void doVertifyErrorForNoPassAgain() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_password_login_again);
    }

    @Override
    public void doVertifyErrorForNotSame2Pass() {
        ToastUtil.showMsg(getApplicationContext(), R.string.not_same_pass);
    }

    @Override
    public void onDataBackSuccessForModifyPass() {


        ToastUtil.showMsg(getApplicationContext(), R.string.modify_pass_success);
        dofinishItself();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.iv_pass_clear:
                et_user_pass.setText(ConstSign.DOUBLE_QUOTE);
                break;

            case R.id.iv_pass_again_clear:
                et_user_pass_again.setText(ConstSign.DOUBLE_QUOTE);
                break;

            case R.id.rl_submit:

                String userPass = et_user_pass.getText().toString().trim();
                String userPassAgain = et_user_pass_again.getText().toString().trim();

                presenterModifyPassword.doModifyPass(
                        HttpConst.URL.USER_MODIFY_PASS,
                        userPass,
                        userPassAgain);

                break;
        }

    }
}
