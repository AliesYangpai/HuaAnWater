package org.huaanwater.work.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstCode;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.AnXinBindBack;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterAnXinBind;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IAnXinBindView;
import org.huaanwater.work.util.ToastUtil;

public class AnXinBindActivity extends BaseActivity<IAnXinBindView,PresenterAnXinBind>
        implements
        IAnXinBindView,
        OnClickListener{


    private PresenterAnXinBind presenterAnXinBind;

    private ImageView iv_common_back;
    private EditText et_reg_name;
    private ImageView iv_clear_account;


    private EditText et_reg_pass;
    private ImageView iv_clear_pass;



    private RelativeLayout rl_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_xin_bind);
    }

    @Override
    protected PresenterAnXinBind creatPresenter() {
        if(null == presenterAnXinBind) {
            presenterAnXinBind = new PresenterAnXinBind(this);
        }
        return presenterAnXinBind;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        et_reg_name = findQHYViewById(R.id.et_reg_name);
        iv_clear_account = findQHYViewById(R.id.iv_clear_account);
        et_reg_pass = findQHYViewById(R.id.et_reg_pass);
        iv_clear_pass = findQHYViewById(R.id.iv_clear_pass);


        rl_reg = findQHYViewById(R.id.rl_reg);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        iv_clear_account.setOnClickListener(this);
        iv_clear_pass.setOnClickListener(this);


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
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void onVertifyErrorForNoAnXinUserName() {
        ToastUtil.showMsg(getApplicationContext(),R.string.enter_anxin_account_login);
    }

    @Override
    public void onVertifyErrorForNoPass() {
        ToastUtil.showMsg(getApplicationContext(),R.string.enter_password_login);
    }

    @Override
    public void onDataBackSuccessForAnXinBind(AnXinBindBack anXinBindBack) {


        this.setResult(ConstIntent.ResponseCode.GO_TO_ANXIN_BIND_SUCCESS);
        this.finish();
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






            case R.id.rl_reg:
                String account = et_reg_name.getText().toString().trim();
                String pass= et_reg_pass.getText().toString().trim();


                presenterAnXinBind.doAnXinBind(
                        HttpConst.URL.BIND_ANXIN_ACCOUNT,
                        account,
                        pass);
                break;
        }
    }
}
