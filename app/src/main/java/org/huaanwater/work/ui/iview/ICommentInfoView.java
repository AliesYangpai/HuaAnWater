package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.CommentChild;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/3/5.
 * 类描述
 * 版本
 */

public interface ICommentInfoView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);



    void onDataBackFailInLoadMore(int code, String errorMsg);

    void doSetCommentInfoToUi(CommentEntity commentEntity);



    /**
     * 获取评论的评论列表
     */
    void onDataBackSuccessForGetChildCommentList(List<CommentChild> list);

    /**
     * 获取评论的评论列表
     */
    void onDataBackSuccessForSetChildAllComment(int count);


    /**
     * 上拉加载更多
     * @param list
     */
    void onDataBackSuccessForGetChildCommentListInLoadMore(List<CommentChild> list);




    void onDataBackSuccessForGetChildCommentListAfterReply(List<CommentChild> commentChildList);

    void onDataBackSuccessForSendCommentComment();
}
