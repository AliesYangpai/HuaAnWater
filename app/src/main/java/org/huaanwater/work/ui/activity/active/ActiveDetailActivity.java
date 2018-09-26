package org.huaanwater.work.ui.activity.active;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.huaanwater.work.R;
import org.huaanwater.work.callback.ActiveParticipateSuccessDialogCallback;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterActiveDetail;
import org.huaanwater.work.test.TestContent;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.PictureDisplayActivity;
import org.huaanwater.work.ui.iview.IActiveDetialView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.dialog.ActiveParticipateSuccessDialog;

import java.util.ArrayList;
import java.util.List;

public class ActiveDetailActivity extends BaseActivity<IActiveDetialView, PresenterActiveDetail> implements
        IActiveDetialView,
        OnClickListener,
        ActiveParticipateSuccessDialogCallback {


    private PresenterActiveDetail presenterActiveDetail;
    private ImageView iv_common_back;


    private TextView tv_news_title;

    private ImageView iv_first_pic;
    private TextView tv_active_content;


    private TextView tv_active_start_time;
    private TextView tv_active_end_time;
    private TextView tv_max_join_count;

    private LinearLayout ll_hlv;
    private ImageView iv_user_head1;
    private ImageView iv_user_head2;
    private ImageView iv_user_head3;
    private TextView tv_add_count;
    private TextView tv_join_in;


    /**
     * dialog相关
     */

    private ActiveParticipateSuccessDialog activeParticipateSuccessDialog;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private Active currentActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_detail);

        presenterActiveDetail.doSetActiveDataToUi(currentActive);


        presenterActiveDetail.doGetParticipateList(
                HttpConst.URL.ACTIVES_PARTICIPATION_RECORD,
                currentActive.getActivity_management_id(),
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.ACTIVE_PARTICIPATE_BOTTOM_SIZE,
                ConstLocalData.DEFUALT_LIST_INDEX);



        presenterActiveDetail.doCheckActiveUserExistFirstIn(
                HttpConst.URL.CHECK_ACTIVITY_USER_EXIST,
                currentActive.getActivity_management_id());
    }


    @Override
    protected PresenterActiveDetail creatPresenter() {
        if (null == presenterActiveDetail) {

            presenterActiveDetail = new PresenterActiveDetail(this);
        }
        return presenterActiveDetail;
    }

    @Override
    protected void initViews() {


        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_news_title = findQHYViewById(R.id.tv_news_title);
        iv_first_pic = findQHYViewById(R.id.iv_first_pic);


        tv_active_content = findQHYViewById(R.id.tv_active_content);
        tv_active_start_time = findQHYViewById(R.id.tv_active_start_time);
        tv_active_end_time = findQHYViewById(R.id.tv_active_end_time);
        tv_max_join_count = findQHYViewById(R.id.tv_max_join_count);


        ll_hlv = findQHYViewById(R.id.ll_hlv);

        iv_user_head1 = findQHYViewById(R.id.iv_user_head1);
        iv_user_head2 = findQHYViewById(R.id.iv_user_head2);
        iv_user_head3 = findQHYViewById(R.id.iv_user_head3);


        tv_add_count = findQHYViewById(R.id.tv_add_count);
        tv_join_in = findQHYViewById(R.id.tv_join_in);


    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        iv_first_pic.setOnClickListener(this);
        ll_hlv.setOnClickListener(this);
        tv_join_in.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle extras = intent.getExtras();
            if (null != extras) {

                currentActive = (Active) extras.getSerializable(ConstIntent.BundleKEY.DELIVER_ACTIVE);
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
    public void doSetActiveDaraToUi(Active active) {

        ImgUtil.getInstance().getImgFromNetByUrl(active.getFirst_pic(), iv_first_pic, R.drawable.img_hold_seat_banner);

        tv_news_title.setText(active.getTitle());

        tv_active_content.setText(active.getContent());
        tv_active_start_time.setText(ConstHz.ACTIVE_START_TIME + ConstSign.COLON + active.getStart_time());
        tv_active_end_time.setText(ConstHz.ACTIVE_END_TIME + ConstSign.COLON + active.getEnd_time());
        tv_max_join_count.setText(ConstHz.ACTIVE_MAX_JONINED_COUNT + ConstSign.COLON + active.getMax_particpation_count());

    }

    @Override
    public void onDataBackSuccessForActiveDetail(Active active) {
        /**
         * 暂时不使用
         */
    }

    @Override
    public void onDataBackSuccessForUserExist() {

        ToastUtil.showMsg(getApplicationContext(), R.string.you_have_joinde_this_active);

    }

    @Override
    public void onDataBackSuccessForUserNotExist() {

        presenterActiveDetail.doActiveParticipate(
                HttpConst.URL.ACTIVES_PARTICIPATION_RECORD+HttpConst.SEPARATOR+currentActive.getActivity_management_id());

    }

    @Override
    public void onDataBackSuccessForParticipate() {


        if (null == activeParticipateSuccessDialog) {
            activeParticipateSuccessDialog = new ActiveParticipateSuccessDialog(this);
        }

        activeParticipateSuccessDialog.setActiveParticipateSuccessDialogCallback(this);
        activeParticipateSuccessDialog.show();

    }

    @Override
    public void onDataBackSuccessForParticipatePersonList1(List<ActiveParticipate> activeParticipates) {


        iv_user_head2.setVisibility(View.GONE);
        iv_user_head3.setVisibility(View.GONE);

        ActiveParticipate activeParticipate = activeParticipates.get(0);
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(
                activeParticipate.getAvatar(),
                iv_user_head1,
                R.drawable.img_default_client_head_round,
                50);


    }

    @Override
    public void onDataBackSuccessForParticipatePersonList2(List<ActiveParticipate> activeParticipates) {

        iv_user_head3.setVisibility(View.GONE);
        ActiveParticipate activeParticipate1 = activeParticipates.get(0);
        ActiveParticipate activeParticipate2 = activeParticipates.get(1);
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(
                activeParticipate1.getAvatar(),
                iv_user_head1,
                R.drawable.img_default_client_head_round,
                50);

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(
                activeParticipate2.getAvatar(),
                iv_user_head2,
                R.drawable.img_default_client_head_round,
                50);

    }

    @Override
    public void onDataBackSuccessForParticipatePersonList3(List<ActiveParticipate> activeParticipates) {

        ActiveParticipate activeParticipate1 = activeParticipates.get(0);
        ActiveParticipate activeParticipate2 = activeParticipates.get(1);
        ActiveParticipate activeParticipate3 = activeParticipates.get(2);
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(
                activeParticipate1.getAvatar(),
                iv_user_head1,
                R.drawable.img_default_client_head_round,
                50);

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(
                activeParticipate2.getAvatar(),
                iv_user_head2,
                R.drawable.img_default_client_head_round,
                50);

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(
                activeParticipate3.getAvatar(),
                iv_user_head3,
                R.drawable.img_default_client_head_round,
                50);
    }

    @Override
    public void onDataBackSuccessForParticipateToShowAvatarLayout() {
        ll_hlv.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDataBackSuccessForParticipatePersonCount(int count) {
        tv_add_count.setText(count + ConstHz.ACTIVE_HAS_PARTICIPATE_COUNT);
    }

    @Override
    public void onDataBackSuccessForUserNotExistFirstIn() {
        tv_join_in.setVisibility(View.VISIBLE);
        tv_join_in.setEnabled(true);
        tv_join_in.setText(getString(R.string.i_want_join));
    }

    @Override
    public void onDataBackSuccessForUserExistFirstIn() {
        tv_join_in.setVisibility(View.VISIBLE);
        tv_join_in.setEnabled(false);
        tv_join_in.setText(getString(R.string.has_join_in));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.iv_first_pic:
                Bundle bundle = new Bundle();
                bundle.putStringArray(ConstIntent.BundleKEY.DELIVER_PICTURES, new String[]{currentActive.getFirst_pic()});
                openActivity(PictureDisplayActivity.class, bundle);

                break;
            case R.id.tv_join_in:


                presenterActiveDetail.doCheckActiveUserExist(HttpConst.URL.CHECK_ACTIVITY_USER_EXIST, currentActive.getActivity_management_id());
                break;

            case R.id.ll_hlv:



                Bundle bundle1 = new Bundle();
                bundle1.putSerializable(ConstIntent.BundleKEY.DELIVER_ACTIVE,currentActive);
                openActivity(ActiveParticipatesInActivity.class,bundle1);

                break;
        }
    }

    @Override
    public void onClickSure() {

        if (null != activeParticipateSuccessDialog && activeParticipateSuccessDialog.isShowing()) {
            activeParticipateSuccessDialog.dismiss();
            activeParticipateSuccessDialog = null;
        }
        dofinishItself();

    }
}
