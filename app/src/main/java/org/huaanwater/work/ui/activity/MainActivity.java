package org.huaanwater.work.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.presenter.PresenterMain;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.goods.GoodsListActivity;
import org.huaanwater.work.ui.activity.news.NewsListActivity;
import org.huaanwater.work.ui.iview.IMainView;
import org.huaanwater.work.util.SpUtil;

import java.util.List;

public class MainActivity extends BaseActivity<IMainView, PresenterMain> implements
        IMainView,
        OnClickListener,
        PermissionListener {

    private PresenterMain presenterMain;


    /**
     * title
     */

    private LinearLayout ll_mine;


    private ImageView iv_scan;

    /**
     * bottom
     *
     * @param savedInstanceState
     */
    private ImageView iv_goods;
    private ImageView iv_active;
    private ImageView iv_balance;
    private ImageView iv_user_point;


    /**
     * 是否已经等乐居
     *
     * @return
     */
    private boolean isLogin() {


//        return TestContent.isLogin;
        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN, ConstSp.SP_VALUE.DEFAULT_BOOLEAN);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected PresenterMain creatPresenter() {
        if (null == presenterMain) {
            presenterMain = new PresenterMain(this);
        }
        return presenterMain;
    }

    @Override
    protected void initViews() {

        /**
         * title
         *
         */

        ll_mine = findQHYViewById(R.id.ll_mine);


        iv_scan = findQHYViewById(R.id.iv_scan);

        /**
         * bottom
         */
        iv_goods = findQHYViewById(R.id.iv_goods);
        iv_active = findQHYViewById(R.id.iv_active);
        iv_balance = findQHYViewById(R.id.iv_balance);
        iv_user_point = findQHYViewById(R.id.iv_user_point);

    }

    @Override
    protected void initListener() {

        /**
         * title
         *
         */

        ll_mine.setOnClickListener(this);



        iv_scan.setOnClickListener(this);
        /**
         * bottom
         */
        iv_goods.setOnClickListener(this);
        iv_active.setOnClickListener(this);
        iv_balance.setOnClickListener(this);
        iv_user_point.setOnClickListener(this);

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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_mine:
                if (isLogin()) {

                    openActivity(UserInfoActivity.class, null);
                } else {
                    openActivity(LoginActivity.class, null);
                }


                break;



            case R.id.iv_scan:




                if(isLogin()) {
                    presenterMain.doPermissionCheck();
                }else {
                    openActivity(LoginActivity.class, null);
                }

//                presenterMain.doPermissionCheck();
//                openActivity(QRScanActivity.class,null);



                break;
            case R.id.iv_goods:

                if (isLogin()) {
                    openActivity(GoodsListActivity.class, null);
                } else {
                    openActivity(LoginActivity.class, null);
                }

                break;

            case R.id.iv_active:
                if (isLogin()) {
                    openActivity(NewsListActivity.class, null);
                } else {
                    openActivity(LoginActivity.class, null);
                }
                break;

            case R.id.iv_balance:

                if (isLogin()) {


                    openActivity(BalanceActivity.class, null);
                } else {
                    openActivity(LoginActivity.class, null);
                }
                break;

            case R.id.iv_user_point:

                if (isLogin()) {


                    openActivity(UserPointActivity.class, null);
                } else {
                    openActivity(LoginActivity.class, null);
                }
                break;
        }
    }

    @Override
    public void doPermissionCheck() {


        AndPermission
                .with(this)
                .requestCode(ConstIntent.RequestCode.APPLY_FOR_PERMISSION)
                .permission(Manifest.permission.CAMERA,
                        Manifest.permission.VIBRATE)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {


                        AndPermission.rationaleDialog(MainActivity.this, rationale).show();

                    }
                })
                .callback(this)
                .start();

    }

    @Override
    public void doShowPermissionDialog() {
        AndPermission.defaultSettingDialog(this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(this.getString(R.string.permission_title))
                .setMessage(this.getString(R.string.permission_msg))
                .setPositiveButton(this.getString(R.string.permission_sure))
                .setNegativeButton(this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {



        /**
         * 申请权限全部允许之前不会回调该方法
         */
        Log.i("quanxianxxxx", "onSucceed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

              openActivity(QRScanActivity.class,null);

                break;

        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {


        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。


                presenterMain.doShowPermissionDialog();


                break;

        }
    }
}
