package org.huaanwater.work.widget.dialog.comment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.inputdialog.NewsInputCallBack;


public class NewsReplyInputDialog extends DialogFragment implements
        View.OnClickListener{

    //点击发表，内容不为空时的回调
    private NewsInputCallBack newsInputCallBack;


    public void setNewsInputCallBack(NewsInputCallBack newsInputCallBack) {
        this.newsInputCallBack = newsInputCallBack;
    }

    private Dialog dialog;
    private EditText et_content;
    private TextView tv_publish;

    public NewsReplyInputDialog() {
    }







    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        dialog = new Dialog(getActivity(), R.style.inputDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View contentview = View.inflate(getActivity(), R.layout.dialog_input_news, null);
        dialog.setContentView(contentview);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.alpha = 1;
        lp.dimAmount = 0.5f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);



        initViews(contentview);

        initListener();



        et_content.setFocusable(true);
        et_content.setFocusableInTouchMode(true);
        et_content.requestFocus();




        return dialog;

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        hideSoftkeyboard();
        super.onDismiss(dialog);
    }

    private void initViews(View view) {
        et_content = (EditText) view.findViewById(R.id.et_content);
        tv_publish = (TextView) view.findViewById(R.id.tv_publish);
    }

    private void initListener() {
        tv_publish.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_publish:


                if(null != newsInputCallBack) {


                    String content = et_content.getText().toString().trim();

                    newsInputCallBack.onInputSend(content);
                }

                break;
        }
    }

    public void hideSoftkeyboard() {
        try {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {

        }
    }





}
