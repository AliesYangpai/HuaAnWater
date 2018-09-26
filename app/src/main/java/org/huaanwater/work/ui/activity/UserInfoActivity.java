package org.huaanwater.work.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterUserInfo;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.feedback.FeedBackListActivity;
import org.huaanwater.work.ui.activity.order.OrderListActivity;
import org.huaanwater.work.ui.iview.IUserInfoView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.StringUtil;
import org.huaanwater.work.util.ToastUtil;

public class UserInfoActivity extends BaseActivity<IUserInfoView, PresenterUserInfo> implements
        IUserInfoView,
        OnClickListener {


    private PresenterUserInfo presenterUserInfo;

    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private LinearLayout ll_right;
    private TextView tv_right_text;



    private LinearLayout ll_top_info;

    private ImageView iv_head;

    private TextView tv_user_name;
    private TextView tv_phone;

    private TextView tv_balance;
    private TextView tv_balance_detail;
    private TextView tv_user_point;
    private TextView tv_user_point_detial;

    private TextView tv_order_count;
    private TextView tv_order_count_detial;


    /**
     * 底部
     *
     * @param savedInstanceState
     */


    private ImageView iv_help;
    private ImageView iv_feed_back;
    private ImageView iv_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        presenterUserInfo.doGetUserInfo(HttpConst.URL.USER_CURRENT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (resultCode){
            case ConstIntent.ResponseCode.GO_TO_USER_INFO_EDIT_SUCCESS:

                presenterUserInfo.doGetUserInfoFromDb();

                break;

            case ConstIntent.ResponseCode.GO_TO_MODIFY_AVATAR_SUCCESS:

                presenterUserInfo.doGetUserInfoFromDb();
                break;
        }
    }

    @Override
    protected PresenterUserInfo creatPresenter() {
        if (null == presenterUserInfo) {
            presenterUserInfo = new PresenterUserInfo(this);
        }
        return presenterUserInfo;
    }

    @Override
    protected void initViews() {
        /**
         * title
         * @param savedInstanceState
         */

        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_right = findQHYViewById(R.id.ll_right);
        tv_right_text = findQHYViewById(R.id.tv_right_text);
        tv_right_text.setText(getString(R.string.sgin_in));
        iv_head = findQHYViewById(R.id.iv_head);

        tv_user_name = findQHYViewById(R.id.tv_user_name);
        tv_phone = findQHYViewById(R.id.tv_phone);

        tv_balance = findQHYViewById(R.id.tv_balance);
        tv_balance_detail = findQHYViewById(R.id.tv_balance_detail);
        tv_user_point = findQHYViewById(R.id.tv_user_point);
        tv_user_point_detial = findQHYViewById(R.id.tv_user_point_detial);

        tv_order_count = findQHYViewById(R.id.tv_order_count);
        tv_order_count_detial = findQHYViewById(R.id.tv_order_count_detial);

        ll_top_info = findQHYViewById(R.id.ll_top_info);


        /**
         * 底部
         * @param savedInstanceState
         */


        iv_help = findQHYViewById(R.id.iv_help);
        iv_feed_back = findQHYViewById(R.id.iv_feed_back);
        iv_setting = findQHYViewById(R.id.iv_setting);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_right.setOnClickListener(this);
        ll_top_info.setOnClickListener(this);

        tv_balance_detail.setOnClickListener(this);
        tv_user_point_detial.setOnClickListener(this);
        tv_order_count_detial.setOnClickListener(this);

        iv_help.setOnClickListener(this);
        iv_feed_back.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_right:
                presenterUserInfo.doSignIn(HttpConst.URL.USER_SIGN_IN);

                break;
            case R.id.ll_top_info:
                Intent intent = new Intent(UserInfoActivity.this, UserInfoEditActivity.class);
                startActivityForResult(intent, ConstIntent.RequestCode.GO_TO_USER_INFO_EDIT);
                break;

            case R.id.rl_balance:
                openActivity(BalanceActivity.class, null);
                break;

            case R.id.tv_balance_detail:


                openActivity(BalanceActivity.class, null);
                break;

            case R.id.tv_user_point_detial:

                openActivity(UserPointActivity.class, null);
                break;

            case R.id.tv_order_count_detial:


                openActivity(OrderListActivity.class, null);

                break;


            case R.id.iv_help:


                openActivity(HelpActivity.class,null);
                break;

            case R.id.iv_feed_back:



                openActivity(FeedBackListActivity.class,null);
                break;
            case R.id.iv_setting:


                openActivity(SettingActivity.class,null);
                break;


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
    public void onDataBackSuccessForGetUserInfo(User user) {


        ImgUtil.getInstance().getRadiusImgFromNetByUrl(user.getAvatar(), iv_head, R.drawable.img_default_client_head_round, 120);


        String nick_name = user.getNick_name();
        String phone = user.getPhone();



        tv_user_name.setText(StringUtil.getFullName(nick_name,phone));
        tv_phone.setText(phone);
        tv_balance.setText(user.getBalance()+ ConstHz.RMB);
        tv_user_point.setText(String.valueOf(user.getUser_points()));

        presenterUserInfo.doGetOrderCount(
                HttpConst.URL.ORDER_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.DEFUALT_LIST_INDEX);

    }

    @Override
    public void onDataBackSuccessForGetUserInfoFromDb(User user) {

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(user.getAvatar(), iv_head, R.drawable.img_default_client_head_round, 120);

        String nick_name = user.getNick_name();
        String phone = user.getPhone();


        tv_user_name.setText(StringUtil.getFullName(nick_name,phone));
        tv_phone.setText(phone);
        tv_balance.setText(String.valueOf(user.getBalance()));
        tv_user_point.setText(String.valueOf(user.getUser_points()));
    }

    @Override
    public void onDataBackSuccessForGetOrderCount(String count) {
        tv_order_count.setText(count);
    }

    @Override
    public void onDataBackSuccessForSignIn(String data) {
        ToastUtil.showMsg(getApplicationContext(),R.string.sgin_in_success);
    }


}
