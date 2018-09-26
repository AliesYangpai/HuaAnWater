package org.huaanwater.work.ui.activity.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.feedback.FeedBack;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterFeedBacks;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.adapter.FeedBacksAdapter;
import org.huaanwater.work.ui.iview.IFeedBacksView;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.dialog.FeedBackSuccessDialog;

import java.util.List;

public class FeedBackListActivity extends BaseActivity<IFeedBacksView,PresenterFeedBacks> implements
        IFeedBacksView,
        OnClickListener,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemLongClickListener,
        BaseQuickAdapter.RequestLoadMoreListener{



    private PresenterFeedBacks presenterFeedBacks;



    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;





    /**
     * list
     *
     * @param savedInstanceState
     */


    private RelativeLayout rl_add_feedback;
    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private FeedBacksAdapter feedBacksAdapter;


    /**
     * dialog相关
     */

    private FeedBackSuccessDialog feedBackSuccessDialog;

    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.FEEDBACK_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_list);

        presenterFeedBacks.doGetFeedBacks(
                HttpConst.URL.FEED_BACK,
                ConstLocalData.ASCENDING_FALSE,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);
    }


    @Override
    protected void onDestroy() {

        if(null != feedBackSuccessDialog && feedBackSuccessDialog.isShowing()) {

            feedBackSuccessDialog.dismiss();
            feedBackSuccessDialog = null;
        }

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case ConstIntent.ResponseCode.GO_TO_FEED_BACK_ENTER_SUCCESS:

                if(null == feedBackSuccessDialog) {
                    feedBackSuccessDialog = new FeedBackSuccessDialog(this);
                }
                feedBackSuccessDialog.show();


                presenterFeedBacks.doGetFeedBacksInEditSuccess(
                        HttpConst.URL.FEED_BACK,
                        ConstLocalData.ASCENDING_FALSE,
                        currentSize,
                        ConstLocalData.DEFUALT_LIST_INDEX);
                break;
        }

    }

    @Override
    protected PresenterFeedBacks creatPresenter() {
        if(null == presenterFeedBacks)  {
            presenterFeedBacks= new PresenterFeedBacks(this);
        }
        return presenterFeedBacks;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);




        rl_add_feedback = findQHYViewById(R.id.rl_add_feedback);
        srefresh_layout = findQHYViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(this);
        feedBacksAdapter = new FeedBacksAdapter(R.layout.item_feedback);
//        rv_list.addItemDecoration(new QHYDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(feedBacksAdapter);


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);



        rl_add_feedback.setOnClickListener(this);
        srefresh_layout.setOnRefreshListener(this);
        feedBacksAdapter.setOnLoadMoreListener(this, rv_list);
        feedBacksAdapter.setOnItemClickListener(this);

        feedBacksAdapter.setOnItemLongClickListener(this);
        //默认第一次加载会进入回调，如果不需要可以配置
        feedBacksAdapter.disableLoadMoreIfNotFullPage(rv_list);
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
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        feedBacksAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetFeedBacks(List<FeedBack> feedBacks) {
        feedBacksAdapter.setNewData(feedBacks);
    }

    @Override
    public void onDataBackSuccessForGetFeedBacksInFresh(List<FeedBack> feedBacks) {
        feedBacksAdapter.setNewData(feedBacks);
    }

    @Override
    public void onDataBackSuccessForGetFeedBacksInLoadMore(List<FeedBack> feedBacks) {
        if (null != feedBacks && feedBacks.size() > 0) {

            feedBacksAdapter.addData(feedBacks);
            feedBacksAdapter.loadMoreComplete();
            currentSize += ConstLocalData.FEEDBACK_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {

            feedBacksAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.rl_add_feedback:
                Intent intent = new Intent(this,FeedBackDoActivity.class);
                startActivityForResult(intent, ConstIntent.RequestCode.GO_TO_FEED_BACK);
                break;

        }
    }

    @Override
    public void onRefresh() {
        presenterFeedBacks.doGetFeedBacksInFresh(
                HttpConst.URL.FEED_BACK,
                ConstLocalData.ASCENDING_FALSE,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


       FeedBack feedBack = (FeedBack) adapter.getData().get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_FEED_BACK,feedBack);
        openActivity(FeedBackInfoActivity.class,bundle);

    }

    @Override
    public void onLoadMoreRequested() {



        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterFeedBacks.doGetFeedBacksInLoadMore(
                HttpConst.URL.FEED_BACK,
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.FEEDBACK_INCREMENT_SIZE,
                tempIndex);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

        ToastUtil.showMsg(getApplicationContext(),"点击长按");
        return true;
    }
}
