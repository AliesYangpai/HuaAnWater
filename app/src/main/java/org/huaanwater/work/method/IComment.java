package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/2/26.
 * 类描述
 * 版本
 */

public interface IComment {

    /**
     * 获取评论列表
     * @param url
     * @param onDataBackListener
     */
    void doGetCommentList(String url, long news_content_id,int size, int index,OnDataBackListener onDataBackListener);

    /**
     * 上啦加载获取评论列表
     * @param url
     * @param size
     * @param index
     * @param onDataBackListener
     */
    void doGetCommentListInLoadMore(String url,long news_content_id,int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 点赞
     * @param url
     * @param onDataBackListener
     */
    void doLike(String url,OnDataBackListener onDataBackListener);


    /**
     * 开始评论 新闻
     * @param url
     * @param news_content_id
     * @param comment
     * @param onDataBackListener
     */
    void doCommentNews(String url,int news_content_id,String comment,OnDataBackListener onDataBackListener);



    /**
     * 开始评论好货
     * @param url
     * @param news_content_id
     * @param comment
     * @param onDataBackListener
     */
    void doCommentGoods(String url,int news_content_id,String comment,OnDataBackListener onDataBackListener);




    /**
     * 获取评论回复列表
     * @param url
     * @param news_content_comment_id
     * @param news_content_id
     * @param onDataBackListener
     */
    void doGetCommentChildList(
            String url,
            int news_content_comment_id,
            int news_content_id,
            int size,
            int index,
            OnDataBackListener onDataBackListener);



    void doGetCommentChildListInLoadMore(
            String url,
            int news_content_comment_id,
            int news_content_id,
            int size,
            int index,
            OnDataBackListener onDataBackListener);


    /**
     * 对评论进行评论
     * @param url
     * @param news_content_id
     * @param comment
     * @param parent_comment_id
     * @param onDataBackListener
     */
    void doCommentComment(
            String url,
            int news_content_id,
            String comment,
            int parent_comment_id,OnDataBackListener onDataBackListener);

}
