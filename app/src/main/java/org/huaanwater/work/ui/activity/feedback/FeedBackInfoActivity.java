package org.huaanwater.work.ui.activity.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.R;
import org.huaanwater.work.callback.inputdialog.FeedBackInputCallBack;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.feedback.FeedBack;
import org.huaanwater.work.entity.feedback.FeedBackPackege;
import org.huaanwater.work.entity.feedback.FeedBackReply;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterFeedBackInfo;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.PictureDisplayActivity;
import org.huaanwater.work.ui.adapter.forlistview.FeedBackReplyAdapter;
import org.huaanwater.work.ui.iview.IFeedBackInfoView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.dialog.comment.FeedBackReplyInputDialog;

import java.util.ArrayList;
import java.util.List;

public class FeedBackInfoActivity extends BaseActivity<IFeedBackInfoView, PresenterFeedBackInfo>
        implements
        IFeedBackInfoView,
        OnClickListener,
        FeedBackInputCallBack {


    private PresenterFeedBackInfo presenterFeedBackInfo;
    private ImageView iv_common_back;


    private ListView lv_list;
    private FeedBackReplyAdapter feedBackReplyAdapter;

    /**
     * 底部
     */
    private LinearLayout ll_comment;

    /**
     * view相关
     *
     * @param savedInstanceState
     */
    private TextView tv_title;
    private TextView tv_pulish_user_name;
    private TextView tv_time;
    private TextView tv_content;
    private LinearLayout ll_images;
    private ImageView iv_feed_1;
    private ImageView iv_feed_2;
    private ImageView iv_feed_3;


    /**
     * dialog相关
     */



    private FeedBackReplyInputDialog feedBackReplyInputDialog;
    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private FeedBack currentFeedBack;

    private List<ImageView> viewImgList;

    private String[] arry ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_info);

        presenterFeedBackInfo.doGetFeedBackInfo(HttpConst.URL.FEED_BACK + HttpConst.SEPARATOR + currentFeedBack.getFeedback_id());

    }

    @Override
    protected void onDestroy() {

        if(null != feedBackReplyInputDialog){

            feedBackReplyInputDialog.dismiss();
            feedBackReplyInputDialog = null;
        }
        super.onDestroy();

    }



    @Override
    protected PresenterFeedBackInfo creatPresenter() {
        if (null == presenterFeedBackInfo) {
            presenterFeedBackInfo = new PresenterFeedBackInfo(this);
        }
        return presenterFeedBackInfo;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        lv_list = findQHYViewById(R.id.lv_list);


        View view = LayoutInflater.from(this).inflate(R.layout.headview_reply, null);
        tv_time = (TextView) view.findViewById(R.id.tv_time);


        /**
         * view相关
         *
         * @param savedInstanceState
         */
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_pulish_user_name = (TextView) view.findViewById(R.id.tv_pulish_user_name);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        ll_images = (LinearLayout) view.findViewById(R.id.ll_images);
        iv_feed_1 = (ImageView) view.findViewById(R.id.iv_feed_1);
        iv_feed_2 = (ImageView) view.findViewById(R.id.iv_feed_2);
        iv_feed_3 = (ImageView) view.findViewById(R.id.iv_feed_3);


        lv_list.addHeaderView(view);
        feedBackReplyAdapter = new FeedBackReplyAdapter(this);
        lv_list.setAdapter(feedBackReplyAdapter);
        ll_comment = findQHYViewById(R.id.ll_comment);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        ll_images.setOnClickListener(this);
        ll_comment.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle bundle = intent.getExtras();
            if (null != bundle) {

                currentFeedBack = (FeedBack) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_FEED_BACK);
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
    public void onDataBackSuccessForFeedBackInfoHeadImg(String[] arry) {

        this.arry = arry;
        ll_images.setVisibility(View.VISIBLE);
        if(null == viewImgList) {
            viewImgList = new ArrayList<>();
            viewImgList.add(iv_feed_1);
            viewImgList.add(iv_feed_2);
            viewImgList.add(iv_feed_3);
        }


        for (int i = 0; i < arry.length; i++) {
            ImgUtil.getInstance().getImgFromNetByUrl(arry[i], viewImgList.get(i), R.drawable.img_default_client_head_round);

        }






    }


    @Override
    public void onDataBackSuccessForFeedBackInfo(FeedBackPackege feedBackPackege) {


        String title = feedBackPackege.getTitle();
        String full_name = feedBackPackege.getFull_name();
        String create_time = feedBackPackege.getCreate_time();
        String content = feedBackPackege.getContent();

        tv_title.setText(title);
        tv_pulish_user_name.setText(full_name + ConstHz.PUBLISH + ConstSign.COLON);
        tv_time.setText(create_time);
        tv_content.setText(content);


        List<FeedBackReply> feedBackReplyList = feedBackPackege.getFeedback_replies();
        feedBackReplyAdapter.setList(feedBackReplyList);



    }

    @Override
    public void onDataBackSuccessForFeedBackInfoAfterReply(FeedBackPackege feedBackPackege) {



        List<FeedBackReply> feedBackReplyList = feedBackPackege.getFeedback_replies();
        feedBackReplyAdapter.setList(feedBackReplyList);
        scrollMyListViewToBottom();

    }

    @Override
    public void onVertifyErrorForNoEnterContent() {
        ToastUtil.showMsg(getApplicationContext(),R.string.enter_your_advice);
    }

    @Override
    public void onDataBackSuccessForSendReply() {




        presenterFeedBackInfo.doGetFeedBackInfoAfterReply(HttpConst.URL.FEED_BACK + HttpConst.SEPARATOR + currentFeedBack.getFeedback_id());


    }



    /**
     * listView滑动到底部
     */
    private void scrollMyListViewToBottom() {
        lv_list.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                lv_list.setSelection(feedBackReplyAdapter.getCount() - 1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;



            case R.id.ll_images:



                Bundle bundle = new Bundle();
                bundle.putStringArray(ConstIntent.BundleKEY.DELIVER_PICTURES,this.arry);
                openActivity(PictureDisplayActivity.class,bundle);

                break;

            case R.id.ll_comment:


//                scrollMyListViewToBottom();
                feedBackReplyInputDialog = new FeedBackReplyInputDialog();
                feedBackReplyInputDialog.setFeedBackInputCallBack(this);
                feedBackReplyInputDialog.show(getSupportFragmentManager(), "dialog");


                break;
        }
    }

    @Override
    public void onInputSend(String content) {

        if(!TextUtil.isEmpty(content)) {

            if(null != feedBackReplyInputDialog){

                feedBackReplyInputDialog.dismiss();
                feedBackReplyInputDialog = null;
            }
        }


        presenterFeedBackInfo.doFeedBackReply(HttpConst.URL.FEED_BACK_REPLY,currentFeedBack.getFeedback_id(),content);

    }
}
