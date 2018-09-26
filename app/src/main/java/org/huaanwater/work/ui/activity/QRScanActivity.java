package org.huaanwater.work.ui.activity;

import android.os.Vibrator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterQRScan;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IQRScanView;
import org.huaanwater.work.util.ToastUtil;

import cn.bingoogolapple.qrcode.core.QRCodeView;

public class QRScanActivity extends BaseActivity<IQRScanView, PresenterQRScan> implements
        IQRScanView,
        View.OnClickListener,
        QRCodeView.Delegate {


    private PresenterQRScan presenterQRScan;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;


    /**
     * 二维码控件
     */

    private QRCodeView mQRCodeView;
    private Vibrator vibrator;//震动控件

    private void startVibrate() {


        if (null == vibrator) {

            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        }
        vibrator.vibrate(200);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (null != mQRCodeView) {

            mQRCodeView.startCamera();
            mQRCodeView.showScanRect();
            mQRCodeView.startSpot();
        }
    }


    @Override
    protected void onStop() {
        if (null != mQRCodeView) {

            mQRCodeView.stopCamera();
            mQRCodeView.stopSpot();

        }
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        if (null != mQRCodeView) {
            mQRCodeView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    protected PresenterQRScan creatPresenter() {
        if (null == presenterQRScan) {
            presenterQRScan = new PresenterQRScan(this);
        }
        return presenterQRScan;
    }

    @Override
    protected void initViews() {
        /**
         * title
         */


        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_common_title = findQHYViewById(R.id.tv_common_title);
        tv_common_title.setText(this.getString(R.string.sweep));


        /**
         * 二维码控件
         *
         */

        mQRCodeView = findQHYViewById(R.id.mQRCodeView);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
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
        mQRCodeView.startSpot();
    }

    @Override
    public void onDataBackSuccessForOutPutWater(String data) {

        ToastUtil.showMsg(getApplicationContext(), data);
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
    public void onScanQRCodeSuccess(String result) {


        ToastUtil.showMsg(getApplicationContext(), result);
        Log.i("scanResult", result);
        startVibrate();
//        mQRCodeView.startSpot();
        mQRCodeView.stopSpot();

        presenterQRScan.doOutPutWater(HttpConst.URL.OUT_PUT_WATER, result, ConstSign.DOUBLE_QUOTE);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

        ToastUtil.showMsg(getApplicationContext(), R.string.sweep_device_error);

    }
}
