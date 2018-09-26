package org.huaanwater.work.ui.activity.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.thepakege.PakegeActive;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterNewsList;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.UserInfoActivity;
import org.huaanwater.work.ui.activity.active.ActiveDetailActivity;
import org.huaanwater.work.ui.adapter.NewsListAdapter;
import org.huaanwater.work.ui.iview.INewsListView;
import org.huaanwater.work.util.ToastUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 动态ACITVity
 */

public class NewsListActivity extends BaseActivity<INewsListView, PresenterNewsList> implements
        INewsListView,
        OnClickListener,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private PresenterNewsList presenterNewsList;

    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private LinearLayout ll_mine;


    /**
     * list
     *
     * @param savedInstanceState
     */

    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private NewsListAdapter newsListAdapter;


    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新

    private List<Active> actives = new ArrayList<>();
    private int allCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);



        presenterNewsList.doGetNewsList(
                HttpConst.URL.NEWS_CONTENT,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.QHY_APP_NEWS,
                ConstSign.DOUBLE_QUOTE);


//        presenterNewsList.doGetActives(
//                HttpConst.URL.ACTIVES,
//                ConstLocalData.ASCENDING_FALSE,
//                currentSize,
//                ConstLocalData.DEFUALT_LIST_INDEX);
    }

    @Override
    protected PresenterNewsList creatPresenter() {
        if (null == presenterNewsList) {
            presenterNewsList = new PresenterNewsList(this);
        }
        return presenterNewsList;
    }


    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_common_title = findQHYViewById(R.id.tv_common_title);
        tv_common_title.setVisibility(View.VISIBLE);
        tv_common_title.setText(getString(R.string.activive));
        ll_mine = findQHYViewById(R.id.ll_mine);


        srefresh_layout = findQHYViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(this);
        newsListAdapter = new NewsListAdapter();
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(newsListAdapter);


    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);


        srefresh_layout.setOnRefreshListener(this);
        newsListAdapter.setOnLoadMoreListener(this, rv_list);
        newsListAdapter.setOnItemClickListener(this);

        //默认第一次加载会进入回调，如果不需要可以配置
//        newsListAdapter.disableLoadMoreIfNotFullPage(rv_list);


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
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);

    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        newsListAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetNewsList(List<News> newsList) {


//        newsListAdapter.setNewData(newsList);

    }

    @Override
    public void onDataBackSuccessForGetNewsListInFresh(List<News> newsList) {
//        newsListAdapter.setNewData(newsList);
    }

    @Override
    public void onDataBackSuccessForGetNewsListInLoadMore(List<News> newsList) {

//        if (null != newsList && newsList.size() > 0) {
//
//            newsListAdapter.addData(newsList);
//            newsListAdapter.loadMoreComplete();
//            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
//            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;
//
//        } else {
//
//            newsListAdapter.loadMoreEnd();
//        }


    }

    @Override
    public void onDataBackSuccessForGetPakageActiveList(List<PakegeActive> pakegeActives) {
        newsListAdapter.setNewData(pakegeActives);


        presenterNewsList.doGetActives(
                HttpConst.URL.ACTIVES,
                ConstLocalData.ASCENDING_FALSE,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);

    }

    @Override
    public void onDataBackSuccessForGetPakageActiveListInFresh(List<PakegeActive> pakegeActives) {
        newsListAdapter.setNewData(pakegeActives);
        presenterNewsList.doGetActives(
                HttpConst.URL.ACTIVES,
                ConstLocalData.ASCENDING_FALSE,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);
    }

    @Override
    public void onDataBackSuccessForGetPakageActiveListInLoadMore(List<PakegeActive> pakegeActives) {
        if (null != pakegeActives && pakegeActives.size() > 0) {

            newsListAdapter.addData(pakegeActives);
            newsListAdapter.loadMoreComplete();

            int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

            presenterNewsList.doGetMoreActives(
                    this.actives,
                    allCount,
                    HttpConst.URL.ACTIVES,
                    ConstLocalData.ASCENDING_FALSE,
                    tempIndex,
                    ConstLocalData.DEFUALT_LIST_INDEX);



            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;




        } else {

            newsListAdapter.loadMoreEnd();
        }


    }

    @Override
    public void onDataBackSuccessForGetActives(List<Active> actives,int allCount) {

        this.actives.addAll(actives);
        this.allCount = allCount;
        presenterNewsList.doAddActiveToItem(this.actives);
    }

    @Override
    public void onDataBackSuccessForGetActivesMore(List<Active> actives,int allCount) {
        this.actives.addAll(actives);
        this.allCount = allCount;
        presenterNewsList.doAddActiveToItem(this.actives);
    }

    @Override
    public void doAddRandomActiveToItem(PakegeActive pakegeActive) {
        newsListAdapter.addData(pakegeActive);
    }

    @Override
    public void doNewsJump(News news) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_NEWS, news);
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ACTIVE,this.actives.get(new Random().nextInt(actives.size())));
        openActivity(NewsDetailActivity.class, bundle);

    }

    @Override
    public void doActiveJump(Active active) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ACTIVE,active);
        openActivity(ActiveDetailActivity.class,bundle);
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

    @Override
    public void onRefresh() {


        presenterNewsList.doGetNewsListInFresh(
                HttpConst.URL.NEWS_CONTENT,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.QHY_APP_NEWS,
                ConstSign.DOUBLE_QUOTE);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        PakegeActive pakegeActive = (PakegeActive) adapter.getData().get(position);



        presenterNewsList.doDealItemClick(pakegeActive);





    }

    @Override
    public void onLoadMoreRequested() {


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterNewsList.doGetNewsListInLoadMore(
                HttpConst.URL.NEWS_CONTENT,
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex,
                ConstLocalData.QHY_APP_NEWS,
                ConstSign.DOUBLE_QUOTE);




//        presenterNewsList.doGetMoreActives(
//                this.actives,
//                allCount,
//                HttpConst.URL.ACTIVES,
//                ConstLocalData.ASCENDING_FALSE,
//                tempIndex,
//                ConstLocalData.DEFUALT_LIST_INDEX);



        Log.i("onLoadMore", "=============ChildFragmentFullLoad=onLoadMoreRequested");
    }
}
