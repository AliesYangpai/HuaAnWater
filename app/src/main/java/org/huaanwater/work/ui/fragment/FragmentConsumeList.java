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
import org.huaanwater.work.entity.Consume;
import org.huaanwater.work.entity.EventEntity;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.forfg.PresenterFgConsumes;
import org.huaanwater.work.test.TestContent;
import org.huaanwater.work.ui.BaseFragment;
import org.huaanwater.work.ui.adapter.ConsumeRecordsAdapter;
import org.huaanwater.work.ui.iview.IFgConsumesView;
import org.huaanwater.work.util.ToastUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConsumeList  extends BaseFragment<IFgConsumesView, PresenterFgConsumes> implements
        IFgConsumesView,
        BaseQuickAdapter.RequestLoadMoreListener {


    public static final String TAG = FragmentConsumeList.class.getSimpleName();


    private PresenterFgConsumes presenterFgConsumes;


    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private ConsumeRecordsAdapter consumeRecordsAdapter;


    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.CONSUME_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新



    @Override
    protected PresenterFgConsumes creatPresenter() {
        if (null == presenterFgConsumes) {
            presenterFgConsumes = new PresenterFgConsumes(this);
        }
        return presenterFgConsumes;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_consume_list;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {


        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(mActivity);
        consumeRecordsAdapter = new ConsumeRecordsAdapter(R.layout.item_consume);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(consumeRecordsAdapter);
    }

    @Override
    protected void initListener() {


        consumeRecordsAdapter.setOnLoadMoreListener(this, rv_list);
        //默认第一次加载会进入回调，如果不需要可以配置
        consumeRecordsAdapter.disableLoadMoreIfNotFullPage(rv_list);

    }

    @Override
    protected void onLazyLoad() {



        presenterFgConsumes.doGetConsumeRecords(
                HttpConst.URL.CONSUME_RECORDS,
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
        consumeRecordsAdapter.loadMoreFail();
        ToastUtil.showMsg(mActivity.getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetConsumes(List<Consume> consumes) {
        consumeRecordsAdapter.setNewData(consumes);
    }

    @Override
    public void onDataBackSuccessForGetConsumesInLoadMore(List<Consume> consumes) {
        if (null != consumes && consumes.size() > 0) {

            consumeRecordsAdapter.addData(consumes);
            consumeRecordsAdapter.loadMoreComplete();
            currentSize += ConstLocalData.USERPOINTS_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            consumeRecordsAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onLoadMoreRequested() {


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterFgConsumes.doGetConsumeRecordsInLoadMore(HttpConst.URL.USER_POINT_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.CONSUME_INCREMENT_SIZE,
                tempIndex);
    }
}
