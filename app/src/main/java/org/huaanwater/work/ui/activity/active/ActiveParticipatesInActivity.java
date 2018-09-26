package org.huaanwater.work.ui.activity.active;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterActiveParticipateList;
import org.huaanwater.work.presenter.PresenterGoodsList;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.UserInfoActivity;
import org.huaanwater.work.ui.adapter.ActiveParticipateListAdapter;
import org.huaanwater.work.ui.adapter.GoodsListAdapter;
import org.huaanwater.work.ui.iview.IActiveParticipateView;
import org.huaanwater.work.util.ToastUtil;

import java.util.List;

public class ActiveParticipatesInActivity extends BaseActivity<IActiveParticipateView,PresenterActiveParticipateList> implements
        IActiveParticipateView,
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{


    private PresenterActiveParticipateList presenterActiveParticipateList;




    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;

    private TextView tv_join_count;
    /**
     * list
     *
     * @param savedInstanceState
     */

    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private ActiveParticipateListAdapter activeParticipateListAdapter;


    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.ACTIVE_PARTICIPATE_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新
    private Active currentActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_participates_in);

        presenterActiveParticipateList.doGetActivesParticipatesRecord(
                HttpConst.URL.ACTIVES_PARTICIPATION_RECORD,
                currentActive.getActivity_management_id(),
                ConstLocalData.ASCENDING_FALSE,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);




    }

    @Override
    protected PresenterActiveParticipateList creatPresenter() {
        if(null == presenterActiveParticipateList) {
            presenterActiveParticipateList = new PresenterActiveParticipateList(this);
        }
        return presenterActiveParticipateList;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        tv_join_count = findQHYViewById(R.id.tv_join_count);



        srefresh_layout = findQHYViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(this);
        activeParticipateListAdapter = new ActiveParticipateListAdapter(R.layout.item_active_participates);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(activeParticipateListAdapter);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        srefresh_layout.setOnRefreshListener(this);
        activeParticipateListAdapter.setOnLoadMoreListener(this, rv_list);
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
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        activeParticipateListAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForParticipateCount(int count) {
        tv_join_count.setText(count+ ConstHz.ACTIVE_HAS_PARTICIPATE_COUNT);
    }

    @Override
    public void onDataBackSuccessForParticipateList(List<ActiveParticipate> activeParticipates) {
        activeParticipateListAdapter.setNewData(activeParticipates);
    }

    @Override
    public void onDataBackSuccessForGetParticipateListInFresh(List<ActiveParticipate> activeParticipates) {
        activeParticipateListAdapter.setNewData(activeParticipates);
    }

    @Override
    public void onDataBackSuccessForGetParticipateListInLoadMore(List<ActiveParticipate> activeParticipates) {
        if (null != activeParticipates && activeParticipates.size() > 0) {

            activeParticipateListAdapter.addData(activeParticipates);
            activeParticipateListAdapter.loadMoreComplete();
            currentSize += ConstLocalData.ACTIVE_PARTICIPATE_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {

            activeParticipateListAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onRefresh() {

        presenterActiveParticipateList.doGetActivesParticipatesRecordInFresh(
                HttpConst.URL.ACTIVES_PARTICIPATION_RECORD,
                currentActive.getActivity_management_id(),
                ConstLocalData.ASCENDING_FALSE,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;


        }
    }

    @Override
    public void onLoadMoreRequested() {


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterActiveParticipateList.doGetActivesParticipatesRecordInLoadMore(
                HttpConst.URL.ACTIVES_PARTICIPATION_RECORD,
                currentActive.getActivity_management_id(),
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.ACTIVE_PARTICIPATE_INCREMENT_SIZE,
                tempIndex);






    }
}
