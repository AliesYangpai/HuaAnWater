package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/2/28.
 * 类描述  意见反馈方法
 * 版本
 */

public interface IFeedBack {


    /**
     * 获取意见反馈详情
     * @param url
     * @param onDataBackListener
     */
    void doGetFeedBackInfo(String url,OnDataBackListener onDataBackListener);


    /**
     * 获取意见反馈列表
     * @param url
     * @param
     * @param
     * @param
     */
    void doGetFeedBacks(String url,boolean ascending, int page_size, int page_index, OnDataBackListener onDataBackListener);

    /**
     * 下拉差量更新
     * @param url
     * @param
     * @param
     * @param
     */
    void doGetFeedBacksInFresh(String url,boolean ascending,  int page_size, int page_index, OnDataBackListener onDataBackListener);


    /**
     * 上拉加载更多
     * @param url
     * @param
     * @param
     * @param
     */
    void doGetFeedBacksInLoadMore(String url,boolean ascending, int page_size, int page_index,OnDataBackListener onDataBackListener);



    /**
     * 发送反馈信息
     * @param url
     * @param title
     * @param content
     * @param feedback_images
     * @param onDataBackListener
     */
    void doSendFeedBack(String url,String title,String content,String feedback_images,OnDataBackListener onDataBackListener);



    /**
     * 发送反馈回复信息
     * @param url
     * @param reply_content
     * @param onDataBackListener
     */
    void doSendFeedBackReplyContent(String url,int feedback_id,String reply_content,OnDataBackListener onDataBackListener);

}
