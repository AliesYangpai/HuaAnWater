package org.huaanwater.work.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.thirdabout.ali.AliAuthInfo;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.presenter.PresenterAuthAccountSelect;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IAuthAccountSelectView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.ToastUtil;

public class AuthAccountSelectActivity extends BaseActivity<IAuthAccountSelectView, PresenterAuthAccountSelect>
        implements
        IAuthAccountSelectView,
        OnClickListener {


    private PresenterAuthAccountSelect presenterAuthAccountSelect;


    private ImageView iv_common_back;
    private ImageView iv_third_head;
    private TextView tv_third_name;
    private ImageView iv_has_account;
    private ImageView iv_no_account;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private Bundle passBundle;
    private String currentTag;//微信的还是支付宝的
    private ValidateWxEntity validateWxEntity; //微信授权返回的验证实体类
    private ValidateAliEntity validateAliEntity;  //阿里授权返回的验证实体类 这两者二选一

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_account_select);


        switch (currentTag) {

            case ConstLocalData.WX:
                validateWxEntity = (ValidateWxEntity) passBundle.getSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY);
                presenterAuthAccountSelect.doSetWxInfoToUi(validateWxEntity);
                break;

            case ConstLocalData.ALI:
                validateAliEntity = (ValidateAliEntity) passBundle.getSerializable(ConstIntent.BundleKEY.THIRD_AUTH_ENTITY);
                presenterAuthAccountSelect.doSetAliInfoToUi(validateAliEntity);
                break;
        }


    }

    @Override
    protected PresenterAuthAccountSelect creatPresenter() {
        if (null == presenterAuthAccountSelect) {
            presenterAuthAccountSelect = new PresenterAuthAccountSelect(this);
        }
        return presenterAuthAccountSelect;
    }

    @Override
    protected void initViews() {

        iv_common_back = findQHYViewById(R.id.iv_common_back);
        iv_third_head = findQHYViewById(R.id.iv_third_head);
        tv_third_name = findQHYViewById(R.id.tv_third_name);
        iv_has_account = findQHYViewById(R.id.iv_has_account);
        iv_no_account = findQHYViewById(R.id.iv_no_account);


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        iv_has_account.setOnClickListener(this);
        iv_no_account.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();
        if (null != intent) {
            passBundle = intent.getExtras();
            if (null != passBundle) {
                currentTag = passBundle.getString(ConstIntent.BundleKEY.THIRD_AUTH_TAG, ConstIntent.BundleValue.DEFAULT_STRING);
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
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void doSetWxUserInfoToUi(ValidateWxEntity validateWxEntity) {
        WxAuthInfo wxAuthInfo = validateWxEntity.getWxAuthInfo();
        String headimgurl = wxAuthInfo.getHeadimgurl();
        String nickname = wxAuthInfo.getNickname();
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(headimgurl, iv_third_head, R.drawable.img_client_normal, 120);
        tv_third_name.setText(nickname);

    }

    @Override
    public void doSetAliUserInfoToUi(ValidateAliEntity validateAliEntity) {




        AliAuthInfo aliAuthInfo = validateAliEntity.getAliAuthInfo();
        String headimgurl = aliAuthInfo.getAvatar();
        String nickname = aliAuthInfo.getNick_name();
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(headimgurl, iv_third_head, R.drawable.img_client_normal, 150);
        tv_third_name.setText(nickname);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.iv_has_account:

                openActivity(AuthAccountHasAndBindActivity.class, passBundle);

                break;

            case R.id.iv_no_account:

                openActivity(AuthAccountNoAndRegBindActivity.class, passBundle);
                break;
        }
    }
}
