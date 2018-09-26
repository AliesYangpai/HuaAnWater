package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/2/26.
 * 类描述
 * 版本
 */

public interface INewsDetailView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);




    /**
     * 设置 新闻内容html
     * @param news
     */
    void onDataBackSuccessForSetContentHtml(News news);



    /**
     * 设置标题
     * @param news
     */
    void onDataBackSuccessForSetTitle(News news);

    /**
     * 设置关键字
     * @param news
     */
    void onDataBackSuccessForSetKeyWord(News news);


    /**
     * 设置点赞次数
     */
    void onDataBackSuccessForSetLike(News news);






    /**
     * 获取评论列表
     */
    void onDataBackSuccessForGetCommentList(List<CommentEntity> list);


    void onDataBackSuccessForGetCommentListInLoadMore(List<CommentEntity> list);


    /**
     * 完成新闻评论
     */
    void onDataBackSuccessForSendNewsComment();


    /**
     * 设置点赞次数
     */
    void onDataBackSuccessForDoLike(String str);




    /**
     * 设置点赞次数
     */
    void onDataBackSuccessForTranspond(String str);
}
