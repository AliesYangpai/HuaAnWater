package org.huaanwater.work.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.EventEntity;
import org.huaanwater.work.entity.RechargeRecord;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.forfg.PresenterFgRecharges;
import org.huaanwater.work.test.TestContent;
import org.huaanwater.work.ui.BaseFragment;
import org.huaanwater.work.ui.adapter.NewsListAdapter;
import org.huaanwater.work.ui.adapter.OrderRecordsAdapter;
import org.huaanwater.work.ui.adapter.RechargeRecordsAdapter;
import org.huaanwater.work.ui.iview.IFgRechargesView;
import org.huaanwater.work.util.ToastUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRechargeList extends BaseFragment<IFgRechargesView, PresenterFgRecharges> implements
        IFgRechargesView,
        BaseQuickAdapter.RequestLoadMoreListener{


    public static final String TAG = FragmentRechargeList.class.getSimpleName();


    private PresenterFgRecharges presenterFgRecharges;


    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private RechargeRecordsAdapter rechargeRecordsAdapter;


    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.RECHARGE_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新



    @Override
    protected PresenterFgRecharges creatPresenter() {
        if (null == presenterFgRecharges) {
            presenterFgRecharges = new PresenterFgRecharges(this);
        }
        return presenterFgRecharges;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_recharge_list;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {


        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(mActivity);
        rechargeRecordsAdapter = new RechargeRecordsAdapter(R.layout.item_recharge);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(rechargeRecordsAdapter);
    }

    @Override
    protected void initListener() {


        rechargeRecordsAdapter.setOnLoadMoreListener(this, rv_list);
        //默认第一次加载会进入回调，如果不需要可以配置
        rechargeRecordsAdapter.disableLoadMoreIfNotFullPage(rv_list);

    }

    @Override
    protected void onLazyLoad() {



        presenterFgRecharges.doGetRechargeRecords(
                HttpConst.URL.RECHARGE_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);
    }

    @Override
    protected void onEventBack(EventEntity eventEntity) {

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
        ToastUtil.showMsg(mActivity.getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        rechargeRecordsAdapter.loadMoreFail();
        ToastUtil.showMsg(mActivity.getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetRecharges(List<RechargeRecord> list) {
        rechargeRecordsAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForGetRechargesInLoadMore(List<RechargeRecord> list) {
        if (null != list && list.size() > 0) {

            rechargeRecordsAdapter.addData(list);
            rechargeRecordsAdapter.loadMoreComplete();
            currentSize += ConstLocalData.USERPOINTS_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            rechargeRecordsAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onLoadMoreRequested() {


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterFgRecharges.doGetRechargeRecordsInLoadMore(
                HttpConst.URL.RECHARGE_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.RECHARGE_INCREMENT_SIZE,
                tempIndex);
    }
}
