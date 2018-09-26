package org.huaanwater.work.ui.activity.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.Order;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterOrderList;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.UserInfoActivity;
import org.huaanwater.work.ui.adapter.OrderRecordsAdapter;
import org.huaanwater.work.ui.iview.IOrderView;
import org.huaanwater.work.util.ToastUtil;

import java.util.List;

public class OrderListActivity extends BaseActivity<IOrderView,PresenterOrderList> implements
        View.OnClickListener,
        IOrderView,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener{


    private PresenterOrderList presenterOrderList;


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

    private TextView tv_order_count;



    private RecyclerView rv_list;
    private RecyclerView.LayoutManager layoutManager;
    private OrderRecordsAdapter orderRecordsAdapter;


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
        setContentView(R.layout.activity_order_list);




        presenterOrderList.doGetOrderReCords(
                HttpConst.URL.ORDER_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                currentSize,ConstLocalData.DEFUALT_LIST_INDEX);


//        orderRecordsAdapter.setNewData(TestContent.getTest());

    }

    @Override
    protected PresenterOrderList creatPresenter() {
        if(null == presenterOrderList) {
            presenterOrderList = new PresenterOrderList(this);
        }
        return presenterOrderList;
    }

    @Override
    protected void initViews() {


        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_mine = findQHYViewById(R.id.ll_mine);

        /**
         * 中间部分
         * @param savedInstanceState
         */

        tv_order_count = findQHYViewById(R.id.tv_order_count);



        rv_list = findQHYViewById(R.id.rv_list);
        layoutManager = new LinearLayoutManager(this);
        orderRecordsAdapter = new OrderRecordsAdapter(R.layout.item_order);
//        rv_list.addItemDecoration(new QHYDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(orderRecordsAdapter);


    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);




        orderRecordsAdapter.setOnLoadMoreListener(this, rv_list);
        orderRecordsAdapter.setOnItemClickListener(this);

        //默认第一次加载会进入回调，如果不需要可以配置
        orderRecordsAdapter.disableLoadMoreIfNotFullPage(rv_list);
    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_mine:
                openActivity(UserInfoActivity.class,null);
                break;
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
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        orderRecordsAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetOrderRecordsCount(String count) {
        tv_order_count.setText(count);
    }


    @Override
    public void onDataBackSuccessForGetOrderRecords(List<Order> orders) {
        orderRecordsAdapter.setNewData(orders);
    }

    @Override
    public void onDataBackSuccessForGetOrderRecordsInLoadMore(List<Order> orders) {
        if (null != orders && orders.size() > 0) {

            orderRecordsAdapter.addData(orders);
            orderRecordsAdapter.loadMoreComplete();
            currentSize += ConstLocalData.USERPOINTS_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            orderRecordsAdapter.loadMoreEnd();
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

       Order order = (Order) adapter.getData().get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER,order);
        openActivity(OrderInfoActivity.class,bundle);
    }

    @Override
    public void onLoadMoreRequested() {
        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterOrderList.doGetOrderReCordsInLoadMore(HttpConst.URL.ORDER_RECORDS,
                ConstLocalData.ASCENDING_FALSE,
                ConstLocalData.USERPOINTS_INCREMENT_SIZE,
                tempIndex);
    }
}
