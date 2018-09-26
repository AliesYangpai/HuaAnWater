package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/2/2.
 * 类描述  获取记录相关列表
 * 版本
 */

public interface IRecord {


    /**
     * 充值记录
     * @param url
     * @param page_size
     * @param page_index
     * @param onDataBackListener
     */
    void doGetRechargeReCords(String url, boolean ascending ,int page_size, int page_index, OnDataBackListener onDataBackListener);

    void doGetRechargeReCordsInLoadMore(String url,  boolean ascending ,int page_size, int page_index, OnDataBackListener onDataBackListener);


    /**
     * 积分记录
     * @param url
     * @param ascending
     * @param size
     * @param index
     * @param onDataBackListener
     */
    void doGetUserpointsReCords(String url,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);

    void doGetUserpointsReCordsInLoadMore(String url,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 订单记录
     */

    void doGetOrderReCords(String url,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);

    void doGetOrderReCordsInLoadMore(String url,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);

    void doGetOrderInfo(String url,OnDataBackListener onDataBackListener);


    /**
     * 消费记录
     */


    void doGetConsumeRecords(String url,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);

    void doGetConsumeReCordsInLoadMore(String url,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 获取活动参加记录
     */
    void doGetActivesParticipateRecord(String url,long activity_management_id,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);
}
