package org.huaanwater.work.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.AboutUs;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterAboutUs;
import org.huaanwater.work.presenter.PresenterVersion;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IAboutUsView;
import org.huaanwater.work.ui.iview.IVersionView;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.util.VersionUtil;

public class AboutUsActivity extends BaseActivity<IAboutUsView, PresenterAboutUs> implements
        IAboutUsView,
        View.OnClickListener {


    private PresenterAboutUs presenterAboutUs;

    private ImageView iv_common_back;



    private TextView tv_about_us_name;
    private TextView tv_about_us_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);



        tv_about_us_name.setText(getString(R.string.about_us));
        presenterAboutUs.doGeiAboutUs(
                HttpConst.URL.VERSION,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.QHY_APP_ABOUT);
    }

    @Override
    protected PresenterAboutUs creatPresenter() {
        if(null == presenterAboutUs) {
            presenterAboutUs = new PresenterAboutUs(this);
        }
        return presenterAboutUs;
    }

    @Override
    protected void initViews() {


        iv_common_back = findQHYViewById(R.id.iv_common_back);

        tv_about_us_name = findQHYViewById(R.id.tv_about_us_name);
        tv_about_us_info = findQHYViewById(R.id.tv_about_us_info);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:

                dofinishItself();
                break;
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
    public void onDataBackSuccessForAboutUs(AboutUs aboutUs) {
        tv_about_us_info.setText(aboutUs.getSummary());
    }
}
