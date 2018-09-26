package org.huaanwater.work.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.Version;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterVersion;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IVersionView;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.util.VersionUtil;

public class VersionActivity extends BaseActivity<IVersionView, PresenterVersion> implements
        IVersionView,
        OnClickListener {


    private PresenterVersion presenterVersion;

    private ImageView iv_common_back;


    private TextView tv_version_name;
    private TextView tv_version_info;
    private RelativeLayout rl_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);


        tv_version_name.setText(VersionUtil.getVersionName());
        presenterVersion.doGetVersion(
                HttpConst.URL.VERSION,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.QHY_APP_VERSION);
    }

    @Override
    protected PresenterVersion creatPresenter() {
        if (null == presenterVersion) {
            presenterVersion = new PresenterVersion(this);
        }
        return presenterVersion;
    }

    @Override
    protected void initViews() {

        iv_common_back = findQHYViewById(R.id.iv_common_back);

        tv_version_name = findQHYViewById(R.id.tv_version_name);
        tv_version_info = findQHYViewById(R.id.tv_version_info);
        rl_update = findQHYViewById(R.id.rl_update);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        rl_update.setOnClickListener(this);
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
    public void onDataBackSuccessForVersion(Version version) {

//        Log.i("sssssw", data);
        tv_version_info.setText(version.getSummary());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:

                dofinishItself();
                break;

            case R.id.rl_update:
                ToastUtil.showMsg(getApplicationContext(),"调用获取版本信息");
                break;
        }
    }
}
