package org.huaanwater.work.ui.activity.goods;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterGoodsList;
import org.huaanwater.work.presenter.PresenterNewsList;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.UserInfoActivity;
import org.huaanwater.work.ui.adapter.GoodsListAdapter;
import org.huaanwater.work.ui.adapter.NewsListAdapter;
import org.huaanwater.work.ui.iview.IGoodsListView;
import org.huaanwater.work.util.ToastUtil;

import java.util.List;

public class GoodsListActivity extends BaseActivity<IGoodsListView,PresenterGoodsList> implements
        IGoodsListView,
        View.OnClickListener,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private PresenterGoodsList presenterGoodsList;

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
    private GoodsListAdapter goodsListAdapter;


    /**
     * 数据相关
     * @param savedInstanceState
     */

    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);


        presenterGoodsList.doGetGoodsList(
                HttpConst.URL.NEWS_CONTENT,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.QHY_APP_GOODS,
                ConstSign.DOUBLE_QUOTE);
    }

    @Override
    protected PresenterGoodsList creatPresenter() {
        if(null == presenterGoodsList) {
            presenterGoodsList = new PresenterGoodsList(this);
        }
        return presenterGoodsList;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_common_title = findQHYViewById(R.id.tv_common_title);
        tv_common_title.setVisibility(View.VISIBLE);
        tv_common_title.setText(getString(R.string.goods));
        ll_mine = findQHYViewById(R.id.ll_mine);


        srefresh_layout = findQHYViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(this);
        goodsListAdapter = new GoodsListAdapter(R.layout.item_goods);

        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(goodsListAdapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper(); //用于实现纵向ViewPager效果的类
        snapHelper.attachToRecyclerView(rv_list);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);

        srefresh_layout.setOnRefreshListener(this);
        goodsListAdapter.setOnLoadMoreListener(this, rv_list);
        goodsListAdapter.setOnItemClickListener(this);
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
        goodsListAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetGoodsList(List<Goods> goodses) {
        goodsListAdapter.setNewData(goodses);
    }

    @Override
    public void onDataBackSuccessForGetGoodsListInFresh(List<Goods> goodses) {
        goodsListAdapter.setNewData(goodses);
    }

    @Override
    public void onDataBackSuccessForGetGoodsListInLoadMore(List<Goods> goodses) {

        if (null != goodses && goodses.size() > 0) {

            goodsListAdapter.addData(goodses);
            goodsListAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {

            goodsListAdapter.loadMoreEnd();
        }
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


        presenterGoodsList.doGetGoodsListInFresh(
                HttpConst.URL.NEWS_CONTENT,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX,
                ConstLocalData.QHY_APP_GOODS,
                ConstSign.DOUBLE_QUOTE);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {



        Goods goods = (Goods) adapter.getData().get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_GOODS,goods);
        openActivity(GoodsDetailActivity.class,bundle);




    }

    @Override
    public void onLoadMoreRequested() {


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterGoodsList.doGetGoodsListInLoadMore(
                HttpConst.URL.NEWS_CONTENT,
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex,
                ConstLocalData.QHY_APP_GOODS,
                ConstSign.DOUBLE_QUOTE);


        Log.i("onLoadMore", "=============ChildFragmentFullLoad=onLoadMoreRequested");
    }
}
