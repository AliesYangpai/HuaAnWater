package org.huaanwater.work.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.presenter.PresenterSetting;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.ISettingView;
import org.huaanwater.work.util.ToastUtil;

public class SettingActivity extends BaseActivity<ISettingView, PresenterSetting>
        implements
        ISettingView,
        OnClickListener {


    private PresenterSetting presenterSetting;


    private ImageView iv_common_back;
    private TextView tv_common_title;



    private LinearLayout ll_change_pass;
    private LinearLayout ll_current_version;
    private LinearLayout ll_about_us;
    private LinearLayout ll_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected PresenterSetting creatPresenter() {
        if (null == presenterSetting) {
            presenterSetting = new PresenterSetting(this);
        }
        return presenterSetting;
    }

    @Override
    protected void initViews() {


        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_common_title = findQHYViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.setting));

        ll_change_pass = findQHYViewById(R.id.ll_change_pass);
        ll_current_version = findQHYViewById(R.id.ll_current_version);
        ll_about_us = findQHYViewById(R.id.ll_about_us);
        ll_logout = findQHYViewById(R.id.ll_logout);

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        ll_change_pass.setOnClickListener(this);
        ll_current_version.setOnClickListener(this);
        ll_about_us.setOnClickListener(this);
        ll_logout.setOnClickListener(this);



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
    public void onDataBackSuccessForLogout() {
        openActivityAndFinishItself(MainActivity.class, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.iv_common_back:
                dofinishItself();
                break;



            case R.id.ll_change_pass:


                openActivity(ModifyPassActivity.class, null);
                break;
            case R.id.ll_current_version:


                openActivity(VersionActivity.class, null);

                break;
            case R.id.ll_about_us:

                openActivity(AboutUsActivity.class, null);
                break;
            case R.id.ll_logout:

                presenterSetting.doClearAll();

                break;
        }
    }
}
