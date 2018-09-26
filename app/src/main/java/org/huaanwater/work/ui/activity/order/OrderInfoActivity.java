package org.huaanwater.work.ui.activity.order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.entity.Order;
import org.huaanwater.work.presenter.PresenterOrderInfo;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.UserInfoActivity;
import org.huaanwater.work.ui.iview.IOrderInfoView;
import org.huaanwater.work.util.ToastUtil;

public class OrderInfoActivity extends BaseActivity<IOrderInfoView, PresenterOrderInfo> implements
        IOrderInfoView,
        View.OnClickListener {


    private PresenterOrderInfo presenterOrderInfo;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private LinearLayout ll_mine;


    private TextView tv_order_no;
    private TextView tv_date;
    private TextView tv_type;
    private TextView tv_price;
    private TextView tv_status;


    private TextView tv_device_name;
    private TextView tv_device_no;
    private TextView tv_device_imei;

    private TextView tv_watering_time;
    private TextView tv_out_water_time;
    private TextView tv_close_water_time;
    private TextView tv_remark;



    /**
     * 数据相关
     */
    private Order currentOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        presenterOrderInfo.doSetOrderInfoToUi(currentOrder);
    }

    @Override
    protected PresenterOrderInfo creatPresenter() {
        if (null == presenterOrderInfo) {
            presenterOrderInfo = new PresenterOrderInfo(this);
        }
        return presenterOrderInfo;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_mine = findQHYViewById(R.id.ll_mine);


        tv_order_no = findQHYViewById(R.id.tv_order_no);
        tv_date = findQHYViewById(R.id.tv_date);
        tv_type = findQHYViewById(R.id.tv_type);
        tv_price = findQHYViewById(R.id.tv_price);
        tv_status = findQHYViewById(R.id.tv_status);


        tv_device_name = findQHYViewById(R.id.tv_device_name);
        tv_device_no = findQHYViewById(R.id.tv_device_no);
        tv_device_imei = findQHYViewById(R.id.tv_device_imei);


        tv_watering_time = findQHYViewById(R.id.tv_watering_time);
        tv_out_water_time = findQHYViewById(R.id.tv_out_water_time);
        tv_close_water_time = findQHYViewById(R.id.tv_close_water_time);
        tv_remark = findQHYViewById(R.id.tv_remark);


    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();
        if(null != intent) {

            Bundle bundle = intent.getExtras();

           currentOrder = (Order) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_ORDER);
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
    public void onDataBackSuccessForOrderInfo(Order order) {


    }

    @Override
    public void onSetOrderInfoToUi(Order order) {


        tv_order_no.setText(ConstHz.ORDER_NO+order.getOrder_no());
        tv_date.setText(ConstHz.ORDER_DATE+order.getCreate_times());





        tv_price.setText(ConstHz.ORDER_AMOUNT+order.getAmount()+ConstHz.RMB);


        tv_device_name.setText(ConstHz.DEVICE_NAME+order.getName());
        tv_device_no.setText(ConstHz.DEVICE_NO+order.getDevice_id());
        tv_device_imei.setText(ConstHz.DEVICE_IMEI+order.getImei());


        tv_watering_time.setText(ConstHz.WATERING_TIME +order.getUse_time());
        tv_out_water_time.setText(ConstHz.OPEN_WATER_TIME + order.getOpen_time());
        tv_close_water_time.setText(ConstHz.CLOSE_WATER_TIME + order.getClose_time());
        tv_remark.setText(ConstHz.ORDER_REMARK+order.getRemark());
    }

    @Override
    public void onSetOrderInfoStatusToUi(String status) {
        tv_status.setText(ConstHz.ORDER_STATUE+status);
    }

    @Override
    public void onSetOrderInfoTypeToUi(String type) {
        tv_type.setText(ConstHz.ORDER_TYPE+type);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_mine:
                openActivity(UserInfoActivity.class, null);
                break;
        }
    }
}
