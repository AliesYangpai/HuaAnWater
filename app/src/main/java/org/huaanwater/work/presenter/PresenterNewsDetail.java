package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IComment;
import org.huaanwater.work.method.INews;
import org.huaanwater.work.method.impl.ICommentImpl;
import org.huaanwater.work.method.impl.INewsImpl;
import org.huaanwater.work.ui.iview.INewsDetailView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/26.
 * 类描述  新闻详情的界面
 * 版本
 */

public class PresenterNewsDetail extends BasePresenter<INewsDetailView> {


    private INewsDetailView iNewsDetailView;
    private INews iNews;
    private IComment iComment;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterNewsDetail(INewsDetailView iNewsDetailView) {
        this.iNewsDetailView = iNewsDetailView;
        this.iNews = new INewsImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iComment = new ICommentImpl();
    }


    /**
     * 获取新闻详情
     *
     * @param url
     */
    public void doGetNewsDetail(String url) {


        iNews.doGetNewsInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iNewsDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                News news = parseSerilizable.getParseToObj(data, News.class);

                if (vertifyNotNull.isNotNullObj(news)) {

                    iNewsDetailView.onDataBackSuccessForSetTitle(news);
                    iNewsDetailView.onDataBackSuccessForSetContentHtml(news);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iNewsDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iNewsDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iNewsDetailView.dismissLoadingDialog();

            }

            @Override
            public void onFinish() {

            }
        });

    }


    /**
     * webView加载完成后 设置后面的加载
     *
     * @param news
     */
    public void doSetDataAfterWebContentLoad(News news) {


        iNewsDetailView.onDataBackSuccessForSetKeyWord(news);
        iNewsDetailView.onDataBackSuccessForSetLike(news);

    }


    /**
     * 获取评论列表
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetCommentList(String url, long news_content_id,int size, int index) {


        iComment.doGetCommentList(url,news_content_id, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {


            }

            @Override
            public void onSuccess(String data) {


                List<CommentEntity> list = parseSerilizable.getParseToList(data, CommentEntity.class);

                if (vertifyNotNull.isNotNullListSize(list)) {
                    iNewsDetailView.onDataBackSuccessForGetCommentList(list);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iNewsDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iNewsDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iNewsDetailView.dismissLoadingDialog();

            }
        });


    }


    public void doGetCommentListInLoadMore(String url,long news_content_id, int size, int index) {


        iComment.doGetCommentListInLoadMore(url,news_content_id, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<CommentEntity> list = parseSerilizable.getParseToList(data, CommentEntity.class);
                iNewsDetailView.onDataBackSuccessForGetCommentListInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iNewsDetailView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iNewsDetailView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doCommentNews(String url, int news_content_id, String comment) {


        iComment.doCommentNews(url, news_content_id, comment, new OnDataBackListener() {
            @Override
            public void onStart() {
                iNewsDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                iNewsDetailView.onDataBackSuccessForSendNewsComment();
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iNewsDetailView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iNewsDetailView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iNewsDetailView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 点赞
     * @param url
     */
    public void doLike(String url) {

        iComment.doLike(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                iNewsDetailView.onDataBackSuccessForDoLike(data);

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iNewsDetailView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iNewsDetailView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }



    public void doTranspond(String url) {

        iNews.doTranspondNews(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iNewsDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iNewsDetailView.onDataBackSuccessForTranspond(data);

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iNewsDetailView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iNewsDetailView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iNewsDetailView.dismissLoadingDialog();
            }
        });

    }
}
