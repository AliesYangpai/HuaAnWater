package org.huaanwater.work.ui.activity;

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
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.UserPoint;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterUserPoint;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.adapter.ConsumeRecordsAdapter;
import org.huaanwater.work.ui.adapter.UserPointRecordsAdapter;
import org.huaanwater.work.ui.iview.IUserPointView;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.recycleview.QHYDividerItemDecoration;

import java.util.List;

public class UserPointActivity extends BaseActivity<IUserPointView,PresenterUserPoint>
        implements
        OnClickListener,
        IUserPointView,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener{


    private PresenterUserPoint presenterUserPoint;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private LinearLayout ll_mine;


    /**
     * 中间部分
     *
     * @param savedInstanceState
     */

    private TextView tv_user_point;
    private LinearLayout ll_how_to_get_user_point;



    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private UserPointRecordsAdapter userPointRecordsAdapter;


    /**
     * 数据相关
     * @param savedInstanceState
     */


    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.USERPOINTS_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_point);

        presenterUserPoint.doGetUserInfo(HttpConst.URL.USER_CURRENT);

        presenterUserPoint.doGetUserpointsReCords(
                HttpConst.URL.USER_POINT_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                currentSize,ConstLocalData.DEFUALT_LIST_INDEX);

    }

    @Override
    protected PresenterUserPoint creatPresenter() {
        if(null == presenterUserPoint) {
            presenterUserPoint= new PresenterUserPoint(this);
        }
        return presenterUserPoint;
    }

    @Override
    protected void initViews() {


        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_mine = findQHYViewById(R.id.ll_mine);

        /**
         * 中间部分
         * @param savedInstanceState
         */

        tv_user_point = findQHYViewById(R.id.tv_user_point);
        ll_how_to_get_user_point = findQHYViewById(R.id.ll_how_to_get_user_point);


        srefresh_layout = findQHYViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(this);
        userPointRecordsAdapter = new UserPointRecordsAdapter(R.layout.item_user_point);
//        rv_list.addItemDecoration(new QHYDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(userPointRecordsAdapter);


    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);



        ll_how_to_get_user_point.setOnClickListener(this);

        srefresh_layout.setOnRefreshListener(this);
        userPointRecordsAdapter.setOnLoadMoreListener(this, rv_list);
        userPointRecordsAdapter.setOnItemClickListener(this);

        //默认第一次加载会进入回调，如果不需要可以配置
        userPointRecordsAdapter.disableLoadMoreIfNotFullPage(rv_list);

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
        userPointRecordsAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetUserInfo(User user) {


        float userPoints = user.getUser_points();
        tv_user_point.setText(String.valueOf(userPoints));

    }

    @Override
    public void onDataBackSuccessForGetUserInfoInFresh(User user) {

        float userPoints = user.getUser_points();
        tv_user_point.setText(String.valueOf(userPoints));
    }

    @Override
    public void onDataBackSuccessForGetUserPointRecords(List<UserPoint> userPoints) {
        userPointRecordsAdapter.setNewData(userPoints);
    }

    @Override
    public void onDataBackSuccessForGetUserPointRecordsInLoadMore(List<UserPoint> userPoints) {
        if (null != userPoints && userPoints.size() > 0) {

            userPointRecordsAdapter.addData(userPoints);
            userPointRecordsAdapter.loadMoreComplete();
            currentSize += ConstLocalData.USERPOINTS_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            userPointRecordsAdapter.loadMoreEnd();
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

            case R.id.ll_how_to_get_user_point:
                ToastUtil.showMsg(getApplicationContext(), "如何获取用户积分");
                break;

        }
    }

    @Override
    public void onLoadMoreRequested() {
        /**
         *
         */


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterUserPoint.doGetUserpointsReCordsInLoadMore(HttpConst.URL.USER_POINT_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.USERPOINTS_INCREMENT_SIZE,
                tempIndex);



    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {




    }

    @Override
    public void onRefresh() {
        presenterUserPoint.doGetUserInfoInFresh(
                HttpConst.URL.USER_CURRENT);
    }
}
