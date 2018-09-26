package org.huaanwater.work.ui.activity.commen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.inputdialog.CommentInputCallBack;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.CommentChild;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterCommentInfo;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.adapter.CommentChildAdapter;
import org.huaanwater.work.ui.adapter.CommentsAdapter;
import org.huaanwater.work.ui.adapter.NewsListAdapter;
import org.huaanwater.work.ui.iview.ICommentInfoView;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.dialog.comment.CommentReplyInputDialog;

import java.util.List;

public class CommenInfoActivity extends BaseActivity<ICommentInfoView, PresenterCommentInfo> implements
        ICommentInfoView,
        OnClickListener,
        CommentInputCallBack,
        BaseQuickAdapter.RequestLoadMoreListener,
        OnItemClickListener {


    private PresenterCommentInfo presenterCommentInfo;
    private ImageView iv_common_back;


    private ImageView iv_customer_head;
    private TextView tv_customer_name;
    private TextView tv_customer_comment;
    private TextView tv_customer_time;
    private LinearLayout ll_like_count;
    private TextView tv_like_count;


    private LinearLayout ll_comment;


    private TextView tv_all_reply;
    //    private ListView lv_list;



    private RecyclerView rv_list;
    private CommentChildAdapter commentChildAdapter;
    private LinearLayoutManager layoutManager;

    /**
     * dialog相关
     */


    private CommentReplyInputDialog commentReplyInputDialog;

    /**
     * 数据
     *
     * @param savedInstanceState
     */

    private CommentEntity currentCommentEntity;

    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commen_info);


        presenterCommentInfo.doSetCommentInfoToUi(currentCommentEntity);

        presenterCommentInfo.doGetCommentChildList(
                HttpConst.URL.GET_CHILD_LIST,
                currentCommentEntity.getNews_content_comment_id(),
                currentCommentEntity.getNews_content_id(),
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);


    }

    @Override
    protected void onDestroy() {
        if (null != commentReplyInputDialog) {

            commentReplyInputDialog.dismiss();
            commentReplyInputDialog = null;
        }
        super.onDestroy();
    }

    @Override
    protected PresenterCommentInfo creatPresenter() {
        if (null == presenterCommentInfo) {
            presenterCommentInfo = new PresenterCommentInfo(this);
        }
        return presenterCommentInfo;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);


        iv_customer_head = findQHYViewById(R.id.iv_customer_head);
        tv_customer_name = findQHYViewById(R.id.tv_customer_name);
        tv_customer_comment = findQHYViewById(R.id.tv_customer_comment);
        tv_customer_time = findQHYViewById(R.id.tv_customer_time);
        ll_like_count = findQHYViewById(R.id.ll_like_count);
        tv_like_count = findQHYViewById(R.id.tv_like_count);

        rv_list = findQHYViewById(R.id.rv_list);
        commentChildAdapter = new CommentChildAdapter(R.layout.item_comment_child);
        layoutManager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(commentChildAdapter);




        tv_all_reply = findQHYViewById(R.id.tv_all_reply);

        ll_comment = findQHYViewById(R.id.ll_comment);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_comment.setOnClickListener(this);


        commentChildAdapter.setOnLoadMoreListener(this, rv_list);
        //默认第一次加载会进入回调，如果不需要可以配置
        commentChildAdapter.disableLoadMoreIfNotFullPage(rv_list);

        commentChildAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle extras = intent.getExtras();
            if (null != extras) {
                currentCommentEntity = (CommentEntity) extras.getSerializable(ConstIntent.BundleKEY.DELIVER_COMMENT);
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
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        commentChildAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void doSetCommentInfoToUi(CommentEntity commentEntity) {

//        ImgUtil.getInstance().getRadiusImgFromNetByUrl("ss",iv_customer_head,R.drawable.img_default_client_head_round,120);
//
//
        tv_customer_name.setText(commentEntity.getUser_name());
        tv_customer_comment.setText(commentEntity.getComment());
        tv_customer_time.setText(commentEntity.getCreate_time());
//          ll_like_count;
        tv_all_reply.setText(ConstHz.ALL_COMMENTS + ConstSign.COLON + String.valueOf(commentEntity.getComment_count()));
    }

    @Override
    public void onDataBackSuccessForGetChildCommentList(List<CommentChild> list) {
        commentChildAdapter.setNewData(list);

    }

    @Override
    public void onDataBackSuccessForSetChildAllComment(int count) {
        tv_all_reply.setText(ConstHz.ALL_COMMENTS + ConstSign.COLON + count);
    }

    @Override
    public void onDataBackSuccessForGetChildCommentListInLoadMore(List<CommentChild> list) {
        if (null != list && list.size() > 0) {

            commentChildAdapter.addData(list);
            commentChildAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            commentChildAdapter.loadMoreEnd();
        }
    }


    @Override
    public void onDataBackSuccessForGetChildCommentListAfterReply(List<CommentChild> list) {
        commentChildAdapter.setNewData(list);
//        scrollMyListViewToBottom();
        tv_all_reply.setText(ConstHz.ALL_COMMENTS + ConstSign.COLON + list.size());
    }

    @Override
    public void onDataBackSuccessForSendCommentComment() {
        if (null != commentReplyInputDialog) {
            commentReplyInputDialog.dismiss();
            commentReplyInputDialog = null;
        }

        presenterCommentInfo.doGetCommentChildListAfterReply(
                HttpConst.URL.GET_CHILD_LIST,
                currentCommentEntity.getNews_content_comment_id(),
                currentCommentEntity.getNews_content_id(),
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_comment:


                commentReplyInputDialog = new CommentReplyInputDialog();
                commentReplyInputDialog.setCommentInputCallBack(this);
                commentReplyInputDialog.show(getSupportFragmentManager(), "dialog");
                break;
        }
    }

    @Override
    public void onInputSend(String content) {


        presenterCommentInfo.doCommentComment(HttpConst.URL.NEWS_CONTENT_COMMENT,
                currentCommentEntity.getNews_content_id(),
                content,
                currentCommentEntity.getNews_content_comment_id());
    }

    @Override
    public void onLoadMoreRequested() {
        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;


        presenterCommentInfo.doGetCommentChildListInLoadMore(
                HttpConst.URL.GET_CHILD_LIST,
                currentCommentEntity.getNews_content_comment_id(),
                currentCommentEntity.getNews_content_id(),
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        CommentChild commentChild = (CommentChild) adapter.getData().get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_COMMENT_CHILD,commentChild);
        openActivity(CommentInfoChildActivity.class,bundle);
    }
}
