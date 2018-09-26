package org.huaanwater.work.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.ActiveParticipateSuccessDialogCallback;


/**
 * Created by Administrator on 2017/4/15.
 * 类描述  反馈成功的dialog
 * 版本
 */

public class ActiveParticipateSuccessDialog extends AlertDialog implements View.OnClickListener {


    private TextView tv_message;//消息提示文本
    private TextView tv_sure; //底部文字按钮
    private ActiveParticipateSuccessDialogCallback activeParticipateSuccessDialogCallback;

    public void setActiveParticipateSuccessDialogCallback(ActiveParticipateSuccessDialogCallback activeParticipateSuccessDialogCallback) {
        this.activeParticipateSuccessDialogCallback = activeParticipateSuccessDialogCallback;
    }

    public ActiveParticipateSuccessDialog(Context context) {
        super(context, R.style.activeParticipateSuccessDialog);

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_active_participate_success);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initEvent();


    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        tv_sure.setOnClickListener(this);

    }



    /**
     * 初始化界面控件
     */
    private void initView() {


        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_sure = (TextView) findViewById(R.id.tv_sure);

    }




    @Override
    public void onClick(View v) {
       if(null != activeParticipateSuccessDialogCallback) {

           activeParticipateSuccessDialogCallback.onClickSure();
       }
    }



}
