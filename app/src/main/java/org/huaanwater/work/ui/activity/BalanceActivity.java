package org.huaanwater.work.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.huaanwater.work.R;
import org.huaanwater.work.callback.RechargeAliEnterAmountCallBack;
import org.huaanwater.work.callback.RechargeWxEnterAmountCallBack;
import org.huaanwater.work.constant.ConstConfig;
import org.huaanwater.work.constant.ConstEvent;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.EventEntity;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.recharge.ali.AliRechargeNecessaryInfo;
import org.huaanwater.work.entity.recharge.ali.PayResult;
import org.huaanwater.work.entity.recharge.ali.checkresult.AliCheckResultInfo;
import org.huaanwater.work.entity.recharge.wx.WxRechargeNecessaryInfo;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterBalance;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.adapter.ConsumeRecordsAdapter;
import org.huaanwater.work.ui.fragment.FragmentConsumeList;
import org.huaanwater.work.ui.fragment.FragmentRechargeList;
import org.huaanwater.work.ui.iview.IBalanceView;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.util.VersionUtil;
import org.huaanwater.work.widget.dialog.RechargeAliEnterAmountDialog;
import org.huaanwater.work.widget.dialog.RechargeWxEnterAmountDialog;
import org.huaanwater.work.widget.recycleview.QHYDividerItemDecoration;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BalanceActivity extends BaseActivity<IBalanceView, PresenterBalance> implements
        IBalanceView,
        OnClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        RechargeAliEnterAmountCallBack,
        RechargeWxEnterAmountCallBack,
        CompoundButton.OnCheckedChangeListener {

    private PresenterBalance presenterBalance;

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

    private TextView tv_balance;
    private ImageView iv_wx_recharge;
    private ImageView iv_ali_recharge;


    private SwipeRefreshLayout srefresh_layout;







    private RadioButton rb_recharge;  //充值明细
    private RadioButton rb_consume;   //消费明细


    /**
     * dialog相关
     */

    private RechargeAliEnterAmountDialog rechargeAliEnterAmountDialog;
    private RechargeWxEnterAmountDialog rechargeWxEnterAmountDialog;

    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新













    /**
     * fragment相关
     */
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private String currentFgTag; //记录当前栈顶的fragment的Tag



    /**
     * 根据不同FragmnetTag来得到不同fragment实例
     *
     * @param fgTag
     */
    private void getDiffirentFragment(String fgTag) {

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fgTag); //通过id找到fragment
        transaction = fragmentManager.beginTransaction();

        if (null == fragment) {


            if (fgTag.equals(FragmentRechargeList.TAG)) {
                //主页fragment
                fragment = new FragmentRechargeList();

            } else if (fgTag.equals(FragmentConsumeList.TAG)) {

                //分类fragment
                fragment = new FragmentConsumeList();

            }
            Log.i("fragmentTest", "==============实例化的fragment：" + fgTag);

            Fragment currentTopFragment = fragmentManager.findFragmentByTag(currentFgTag); //得到当前栈顶部的fragmnet

            if (null != currentTopFragment) {


                Log.i("fragmentTest", "当前栈顶的fragment：---->" + currentFgTag + " 被隐藏掉" + "  新的栈顶fragment：" + fgTag);

                transaction.hide(currentTopFragment).add(R.id.fragment_container, fragment, fgTag).commit();  //如果存在则让其隐藏【解决创建后隐藏问题】
                currentFgTag = fgTag;


            } else {


                Log.i("fragmentTest", "当前栈顶没有fragment！！！！！！！！，将" + fgTag + " 设置到栈顶");

                transaction.add(R.id.fragment_container, fragment, fgTag).commit();

                currentFgTag = fgTag;


            }

            Log.i("fragmentTest11", "当前栈顶的fragment：---->" + currentFgTag);

        } else {


            Log.i("fragmentTest11", "当前栈顶的fragment：---->" + currentFgTag);

            Fragment currentFragment = fragmentManager.findFragmentByTag(currentFgTag);


            if (currentFgTag.equals(fgTag)) {

                Log.i("fragmentTest", fgTag + " ***********选中它，并且已经被实例化了,但是与currentFgTag相等，执行return");

//                doGetShowFgData(currentFragment);


                return;
            }


            Log.i("fragmentTest", fgTag + " ***********选中它，并且已经被实例化了" + " 当前栈顶的fragment：" + currentFgTag + "被隐藏掉");

//            doGetShowFgData(fragment);

            switchFragment(currentFragment, fragment);


            currentFgTag = fgTag;


        }


    }

    /**
     * fragment隐藏切换
     *
     * @param from
     * @param to
     */
    public void switchFragment(Fragment from, Fragment to) {
        if (!to.isAdded()) {    // 先判断是否被add过
            transaction.hide(from).add(R.id.fragment_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }





    /**
     * 处理支付宝支付结果返回并更新界面(就是支付宝充值返回后进行验签)
     *
     * @param stringStringMap
     */
    private void doAlipayCheckResultToUi(Map<String, String> stringStringMap) {


        PayResult payResult = new PayResult(stringStringMap);
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        String resultStatus = payResult.getResultStatus();

        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
            Log.i("ali_pay_result", resultInfo);

            presenterBalance.doCheckAliPayResult(HttpConst.URL.CHECK_ALI_PAY_RESULT_INFORMATION,
                    resultInfo,
                    ConstConfig.APP_TYPE,
                    ConstConfig.OS_TYPE,
                    ConstLocalData.PAY_SIDE_USER);


        } else {
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            ToastUtil.showMsg(getApplicationContext(), "支付充值失败");

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        EventBus.getDefault().register(this);

        presenterBalance.doGetUserInfo(HttpConst.URL.USER_CURRENT);

        rb_recharge.setChecked(true);



    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (null != rechargeAliEnterAmountDialog && rechargeAliEnterAmountDialog.isShowing()) {
            rechargeAliEnterAmountDialog.dismiss();
            rechargeAliEnterAmountDialog = null;
        }


        if (null != rechargeWxEnterAmountDialog && rechargeWxEnterAmountDialog.isShowing()) {
            rechargeWxEnterAmountDialog.dismiss();
            rechargeWxEnterAmountDialog = null;
        }
        super.onDestroy();

    }

    @Override
    protected PresenterBalance creatPresenter() {
        if (null == presenterBalance) {
            presenterBalance = new PresenterBalance(this);
        }
        return presenterBalance;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_mine = findQHYViewById(R.id.ll_mine);


        /**
         * 中间部分
         * @param savedInstanceState
         */

        tv_balance = findQHYViewById(R.id.tv_balance);
        iv_wx_recharge = findQHYViewById(R.id.iv_wx_recharge);
        iv_ali_recharge = findQHYViewById(R.id.iv_ali_recharge);


        srefresh_layout = findQHYViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());



        rb_recharge = findQHYViewById(R.id.rb_recharge);  //充值明细
        rb_consume = findQHYViewById(R.id.rb_consume);   //消费明细
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);


        srefresh_layout.setOnRefreshListener(this);





        iv_wx_recharge.setOnClickListener(this);
        iv_ali_recharge.setOnClickListener(this);


        rb_recharge.setOnCheckedChangeListener(this); //配货订单
        rb_consume.setOnCheckedChangeListener(this);//我的库存分页
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
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForGetUserInfo(User user) {


        float balance = user.getBalance();
        tv_balance.setText(balance+ ConstHz.RMB);

    }

    @Override
    public void onDataBackSuccessForGetUserInfoInFresh(User user) {
        float balance = user.getBalance();
        tv_balance.setText(balance+ConstHz.RMB);
    }



    /**
     * 在这里调起支付宝sdk
     *
     * @param aliRechargeNecessaryInfo
     */
    @Override
    public void doRxAliRecharge(final AliRechargeNecessaryInfo aliRechargeNecessaryInfo) {


        Observable.create(new Observable.OnSubscribe<Map<String, String>>() {
            @Override
            public void call(Subscriber<? super Map<String, String>> subscriber) {
                PayTask alipay = new PayTask(BalanceActivity.this);
                Map<String, String> result = alipay.payV2(aliRechargeNecessaryInfo.getAction_arguments(), true);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }

        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Map<String, String> stringStringMap) {


                        doAlipayCheckResultToUi(stringStringMap);

                    }
                });
    }

    @Override
    public void doWxRecharge(WxRechargeNecessaryInfo wxRechargeNecessaryInfo) {


        Log.i("wxPayNecessary", wxRechargeNecessaryInfo.getAction_arguments());

        Log.i("wxPayNecessary", "全部信息：" + wxRechargeNecessaryInfo.toString());

        try {
            JSONObject json = new JSONObject(wxRechargeNecessaryInfo.getAction_arguments());

            PayReq req = new PayReq();
            req.appId = json.getString("appid");
            req.partnerId = json.getString("partnerid");
            req.prepayId = json.getString("prepayid");
            req.nonceStr = json.getString("noncestr");
            req.timeStamp = json.getString("timestamp");
            req.packageValue = json.getString("package");
            req.sign = json.getString("sign");

            IWXAPI wxapi = WXAPIFactory.createWXAPI(this, req.appId);

            wxapi.sendReq(req);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * 处理微信充值返回结果
     *
     * @param eventEntity
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventInActivty(EventEntity eventEntity) {


        if (null != eventEntity) {


            switch (eventEntity.getNotifyTag()) {


                case ConstEvent.WXABOUT.WX_PAY_SUCCESS_BACK:  //微信支付成功 返回
                    presenterBalance.doGetUserInfo(HttpConst.URL.USER_CURRENT);
                    break;

            }

        }

    }

    @Override
    public void onDataBackSuccessForAliCheck() {
        presenterBalance.doGetUserInfo(HttpConst.URL.USER_CURRENT);
    }


    /**
     * 微信验签
     * 【微信支付成功后，不用进行验签，因此此方法作废】
     *
     * @param v
     */
//    @Override
//    public void onDataBackSuccessForWxCheck() {
//        presenterBalance.doGetUserInfo(HttpConst.URL.USER_CURRENT);
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_mine:
                openActivity(UserInfoActivity.class, null);
                break;

            case R.id.iv_wx_recharge:
                if (null == rechargeWxEnterAmountDialog) {

                    rechargeWxEnterAmountDialog = new RechargeWxEnterAmountDialog(this);
                    rechargeWxEnterAmountDialog.setPayChannelTag(ConstLocalData.WX);
                    rechargeWxEnterAmountDialog.setRechargeWxEnterAmountCallBack(this);
                }
                rechargeWxEnterAmountDialog.show();
                break;

            case R.id.iv_ali_recharge:

                if (null == rechargeAliEnterAmountDialog) {

                    rechargeAliEnterAmountDialog = new RechargeAliEnterAmountDialog(this);
                    rechargeAliEnterAmountDialog.setPayChannelTag(ConstLocalData.ALI);
                    rechargeAliEnterAmountDialog.setRechargeAliEnterAmountCallBack(this);
                }
                rechargeAliEnterAmountDialog.show();
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {



    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        TestConsume testConsume = (TestConsume) adapter.getData().get(position);
        ToastUtil.showMsg(getApplicationContext(), testConsume.getMoney() + testConsume.getTime());

    }

    @Override
    public void onRefresh() {
        presenterBalance.doGetUserInfoInFresh(
                HttpConst.URL.USER_CURRENT);
    }

    @Override
    public void rechargeAliFinishEnterSureCallBack(String payChannel, String amount) {
        if (null != rechargeAliEnterAmountDialog && rechargeAliEnterAmountDialog.isShowing()) {
            rechargeAliEnterAmountDialog.dismiss();
            rechargeAliEnterAmountDialog = null;
        }


        presenterBalance.doBalanceRechargeAli(
                HttpConst.URL.CREATE_PAY,
                ConstConfig.ORDER_TYPE_BALANCE_RECHAREGE,
                payChannel,
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE,
                VersionUtil.getTheIMEI(),
                amount,
                ConstLocalData.PAY_SIDE_USER);


    }

    @Override
    public void rechargeAliCancelEnterCallBack() {
        if (null != rechargeAliEnterAmountDialog && rechargeAliEnterAmountDialog.isShowing()) {
            rechargeAliEnterAmountDialog.dismiss();
            rechargeAliEnterAmountDialog = null;
        }
    }

    @Override
    public void rechargeWxFinishEnterSureCallBack(String payChannel, String amount) {

        if (null != rechargeWxEnterAmountDialog && rechargeWxEnterAmountDialog.isShowing()) {
            rechargeWxEnterAmountDialog.dismiss();
            rechargeWxEnterAmountDialog = null;
        }


        presenterBalance.doBalanceRechargeWx(
                HttpConst.URL.CREATE_PAY,
                ConstConfig.ORDER_TYPE_BALANCE_RECHAREGE,
                payChannel,
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE,
                VersionUtil.getTheIMEI(),
                amount,
                ConstLocalData.PAY_SIDE_USER);

    }

    @Override
    public void rechargeWxCancelEnterCallBack() {
        if (null != rechargeWxEnterAmountDialog && rechargeWxEnterAmountDialog.isShowing()) {
            rechargeWxEnterAmountDialog.dismiss();
            rechargeWxEnterAmountDialog = null;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {

            switch (buttonView.getId()) {

                case R.id.rb_recharge: //充值记录

                    getDiffirentFragment(FragmentRechargeList.TAG);

                    break;


                /**
                 * **********************************一下是我的库存相关********************************************************
                 */

                case R.id.rb_consume: //消费记录

                    getDiffirentFragment(FragmentConsumeList.TAG);

                    break;
            }
        }
    }
}
