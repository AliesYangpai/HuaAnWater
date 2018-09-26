package org.huaanwater.work.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.popwindow.UserTypeSelectPopwindowCallBack;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.UserType;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/4/5 0005.
 * 类描述   用于用户类型选择
 * 版本
 */
public class UserTypeSelPopWindow extends PopupWindow implements OnClickListener {

    /**
     * 初始化配置
     */
    private Context context;

    private LayoutInflater inflater;


    private WeakReference<Context> weakReference;


    /**
     * 控件相关
     */

    private View view;

    private TextView tv_student; //学生
    private TextView tv_enterprise; //企业
    private TextView tv_housing_estate;//小区
    private TextView tv_other; //其他
    private TextView tv_photo_cancel; //取消

    private UserType userType;


    private UserTypeSelectPopwindowCallBack userTypeSelectPopwindowCallBack;

    public void setUserTypeSelectPopwindowCallBack(UserTypeSelectPopwindowCallBack userTypeSelectPopwindowCallBack) {
        this.userTypeSelectPopwindowCallBack = userTypeSelectPopwindowCallBack;
    }

    public UserTypeSelPopWindow(Context context) {
        super(context);

        weakReference = new WeakReference<Context>(context);

        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();

    }




    private void initViews() {

        view = this.inflater.inflate(R.layout.popwindow_user_select, null);






        tv_student = (TextView) view.findViewById(R.id.tv_student); //学生
        tv_enterprise = (TextView) view.findViewById(R.id.tv_enterprise); //企业
        tv_housing_estate = (TextView) view.findViewById(R.id.tv_housing_estate); //小区
        tv_other = (TextView) view.findViewById(R.id.tv_other); //其他
        tv_photo_cancel = (TextView) view.findViewById(R.id.tv_photo_cancel); //取消
        this.setContentView(view);

        ColorDrawable colorDrawable = new ColorDrawable(0x0000000);

        this.setBackgroundDrawable(colorDrawable);


        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        this.setOutsideTouchable(true); //这一步要在showAsDropDown之前调用

        this.setFocusable(true);

        this.setAnimationStyle(R.style.popwindowPhotostyle);



    }

    private void initListener() {

        tv_student.setOnClickListener(this);
        tv_enterprise.setOnClickListener(this);
        tv_housing_estate.setOnClickListener(this);
        tv_other.setOnClickListener(this);
        tv_photo_cancel.setOnClickListener(this);

    }


    @Override
    public boolean isShowing() {


        boolean type = super.isShowing();

        Log.i("payPop", "isSHowIng:" + type);


        if(type) {


            backgroundAlpha((Activity)weakReference.get(),1f);

        }else {

            backgroundAlpha((Activity)weakReference.get(),0.5f);
        }




        return type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {



            case R.id.tv_student:

                if(null != userTypeSelectPopwindowCallBack) {

                    userTypeSelectPopwindowCallBack.
                            onSelectStudentType(getUserType(ConstLocalData.STUDENT, ConstHz.STUDENT));
                }

                break;

            case R.id.tv_enterprise:

                if(null != userTypeSelectPopwindowCallBack) {

                    userTypeSelectPopwindowCallBack
                            .onSelectEnterpriseType(getUserType(ConstLocalData.ENTERPRISE,ConstHz.ENTERPRISE));
                }

                break;

            case R.id.tv_housing_estate:
                if(null != userTypeSelectPopwindowCallBack) {

                    userTypeSelectPopwindowCallBack
                            .onSelectHousingEstateType(getUserType(ConstLocalData.HOUSING_ESTATE,ConstHz.HOUSING_ESTATE));
                }
                break;

            case R.id.tv_other:
                if(null != userTypeSelectPopwindowCallBack) {

                    userTypeSelectPopwindowCallBack
                            .onSelectOtherType(getUserType(ConstLocalData.OTHER,ConstHz.OTHER));
                }
                break;

            case R.id.tv_photo_cancel:
                break;
        }


        UserTypeSelPopWindow.this.dismiss();
    }





    /**
     * 背景色变暗
     */

    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    private UserType getUserType(String typeCode, String typeHz) {

        if(null == userType) {

            userType = new UserType();
        }

        userType.setTypeCode(typeCode);
        userType.setTypeHz(typeHz);
        return userType;
    }
}
