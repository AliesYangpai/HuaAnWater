package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/1/24.
 * 类描述
 * 版本
 */

public interface INews {

    /**
     * 获取新闻详情
     *
     * @param url
     * @param onDataBackListener
     */
    void doGetNewsInfo(String url, OnDataBackListener onDataBackListener);


    /**
     * 服务端获取新闻列表
     *
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetNewsList(String url, int size, int index, String tag_code, String content_category_id, OnDataBackListener onDataBackListener);

    /**
     * 下拉差量更新
     *
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetNewsListInFresh(String url, int size, int index, String tag_code, String content_category_id, OnDataBackListener onDataBackListener);


    /**
     * 上拉加载更多
     *
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetNewsListInLoadMore(String url, int size, int index, String tag_code, String content_category_id, OnDataBackListener onDataBackListener);


    /**
     * 转发新闻
     * @param url
     * @param
     * @param onDataBackListener
     */
    void doTranspondNews(String url,  OnDataBackListener onDataBackListener);


}
