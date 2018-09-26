package org.huaanwater.work.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.RechargeWxEnterAmountCallBack;


/**
 * Created by Administrator on 2017/8/25.
 * 类描述  微信输入充值金额的dialog
 * 版本
 */

public class RechargeWxEnterAmountDialog extends Dialog implements View.OnClickListener {







    private EditText et_recharge_content;

    private TextView tv_recharge_cancel; //底部取消
    private TextView tv_recharge_sure;//底部确认


    private Context context;

    private String payChannelTag; //充值渠道




    private RechargeWxEnterAmountCallBack rechargeWxEnterAmountCallBack;


    public void setPayChannelTag(String payChannelTag) {
        this.payChannelTag = payChannelTag;
    }


    public void setRechargeWxEnterAmountCallBack(RechargeWxEnterAmountCallBack rechargeWxEnterAmountCallBack) {
        this.rechargeWxEnterAmountCallBack = rechargeWxEnterAmountCallBack;
    }

    public RechargeWxEnterAmountDialog(Context context) {
        super(context, R.style.RechargeWxEnterAmountDialog);
        this.context = context;

    }







    public RechargeWxEnterAmountDialog(Context context, AttributeSet attrs) {
        super(context, R.style.RechargeWxEnterAmountDialog);
        this.context = context;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_recharge_wx_enter_amount);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initListener();


    }


    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {
        /**
         * 底部按钮
         */
        tv_recharge_cancel.setOnClickListener(this); //底部取消
        tv_recharge_sure.setOnClickListener(this);//底部确认

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {






    }




    /**
     * 初始化界面控件
     */
    private void initView() {



        /**
         * 文本信息
         */
        et_recharge_content = (EditText) findViewById(R.id.et_recharge_content);

        /**
         * 底部按钮
         */
        tv_recharge_cancel = (TextView) findViewById(R.id.tv_recharge_cancel); //底部取消
        tv_recharge_sure = (TextView) findViewById(R.id.tv_recharge_sure);//底部确认



    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.tv_recharge_cancel:  //取消

                if(null != rechargeWxEnterAmountCallBack) {

                    rechargeWxEnterAmountCallBack.rechargeWxCancelEnterCallBack();
                }


                break;


            case R.id.tv_recharge_sure:    //确认
                if(null != rechargeWxEnterAmountCallBack) {
                    String amout = et_recharge_content.getText().toString().trim();
                    rechargeWxEnterAmountCallBack.rechargeWxFinishEnterSureCallBack(payChannelTag,amout);
                }

                break;


        }
    }







}
