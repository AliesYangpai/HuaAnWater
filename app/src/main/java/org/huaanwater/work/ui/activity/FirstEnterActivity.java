package org.huaanwater.work.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;


import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.presenter.PresenterFirstEnter;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IFirstEnterView;

import java.util.List;


/**
 * app的入口，
 */
public class FirstEnterActivity extends BaseActivity<IFirstEnterView, PresenterFirstEnter>
        implements IFirstEnterView, PermissionListener {


    private PresenterFirstEnter presenterFirstEnter;


    @Override
    protected PresenterFirstEnter creatPresenter() {

        if (null == presenterFirstEnter) {

            presenterFirstEnter = new PresenterFirstEnter(this);
        }
        return presenterFirstEnter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterFirstEnter.doPermissionCheck();


    }


    @Override
    protected void initViews() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {


            /**
             * 申请权限全部允许之前不会回调该方法
             */
            Log.i("quanxianxxxx", "onSucceed " + requestCode + "   ");
            switch (requestCode) {
                case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:


                    presenterFirstEnter.doGoToMainOrWelCome();


                    break;

            }

    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。


                presenterFirstEnter.doShowPermissionDialog();

                break;

        }
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void doPermissionCheck() {

        AndPermission
                .with(this)
                .requestCode(ConstIntent.RequestCode.APPLY_FOR_PERMISSION)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {


                        AndPermission.rationaleDialog(FirstEnterActivity.this, rationale).show();

                    }
                })
                .callback(this)
                .start();


    }

    @Override
    public void doGoToWelcome() {


        openActivityAndFinishItself(WelcomeActivity.class, null);


    }

    @Override
    public void doGoToMain() {

        openActivityAndFinishItself(MainActivity.class, null);



    }

    @Override
    public void doShowPermissionDialog() {
        AndPermission.defaultSettingDialog(FirstEnterActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(FirstEnterActivity.this.getString(R.string.permission_title))
                .setMessage(FirstEnterActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(FirstEnterActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(FirstEnterActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirstEnterActivity.this.finish();
                    }
                })
                .show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case ConstIntent.RequestCode.SYSYEM_SETTING:


                if (AndPermission.hasPermission(FirstEnterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    presenterFirstEnter.doGoToMainOrWelCome();

                } else {


                    presenterFirstEnter.doShowPermissionDialog();

                }

                Log.i("quanxianxxxx", "onActivityResult " + requestCode + "   ");


                break;

        }
    }


}
