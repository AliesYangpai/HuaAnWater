package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/1/24.
 * 类描述
 * 版本
 */

public interface IGoods {

    /**
     * 获取新闻详情
     * @param url
     * @param onDataBackListener
     */
    void doGetGoodsInfo(String url, OnDataBackListener onDataBackListener);


    /**
     * 服务端获取新闻列表
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetGoodsList(String url, int size, int index, String tag_code, String content_category_id, OnDataBackListener onDataBackListener);

    /**
     * 下拉差量更新
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetGoodsListInFresh(String url, int size, int index, String tag_code, String content_category_id, OnDataBackListener onDataBackListener);


    /**
     * 上拉加载更多
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetGoodsListInLoadMore(String url, int size, int index, String tag_code, String content_category_id, OnDataBackListener onDataBackListener);




}
