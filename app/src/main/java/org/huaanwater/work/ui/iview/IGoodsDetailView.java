package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/2/26.
 * 类描述   好货详情界面
 * 版本
 */

public interface IGoodsDetailView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);




    /**
     * 设置 新闻内容html
     * @param goods
     */
    void onDataBackSuccessForSetContentHtml(Goods goods);



    /**
     * 设置标题
     * @param goods
     */
    void onDataBackSuccessForSetTitle(Goods goods);


    /**
     * 设置image
     * 到viewpager
     *
     */
    void onDataBackSuccessForSetImgs(String[] arry);

    /**
     * 设置关键字
     * @param goods
     */
    void onDataBackSuccessForSetKeyWord(Goods goods);


    /**
     * 设置点赞次数
     */
    void onDataBackSuccessForSetLike(Goods goods);



    /**
     * 获取评论列表
     */
    void onDataBackSuccessForGetCommentList(List<CommentEntity> list);



    void onDataBackSuccessForGetCommentListInLoadMore(List<CommentEntity> list);


    /**
     * 完成新闻评论
     */
    void onDataBackSuccessForSendGoodsComment();


    /**
     * 设置点赞次数
     */
    void onDataBackSuccessForDoLike(String str);
}
