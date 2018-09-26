package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.CommentChild;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IComment;
import org.huaanwater.work.method.impl.ICommentImpl;
import org.huaanwater.work.ui.iview.ICommentInfoView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/3/5.
 * 类描述
 * 版本
 */

public class PresenterCommentInfo extends BasePresenter<ICommentInfoView> {


    private ICommentInfoView iCommentInfoView;
    private IComment iComment;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterCommentInfo(ICommentInfoView iCommentInfoView) {
        this.iCommentInfoView = iCommentInfoView;
        this.iComment = new ICommentImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void doSetCommentInfoToUi(CommentEntity commentEntity) {
        iCommentInfoView.doSetCommentInfoToUi(commentEntity);
    }



    public void doGetCommentChildList(String url,
                                      int news_content_comment_id,
                                      int news_content_id,
                                      int size,
                                      int index) {


        iComment.doGetCommentChildList(
                url,
                news_content_comment_id,
                news_content_id,
                size,
                index,
                new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iCommentInfoView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {


                        List<CommentChild> list = parseSerilizable.getParseToList(data, CommentChild.class);
                        int parseCount = parseSerilizable.getParseCount(data);
                        if (vertifyNotNull.isNotNullListSize(list)) {
                            iCommentInfoView.onDataBackSuccessForGetChildCommentList(list);
                            iCommentInfoView.onDataBackSuccessForSetChildAllComment(parseCount);
                        }


//                onDataBackSuccessForGetChildCommentList

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iCommentInfoView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iCommentInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFinish() {
                        iCommentInfoView.dismissLoadingDialog();
                    }
                });
    }


    public void doGetCommentChildListInLoadMore(String url,
                                                int news_content_comment_id,
                                                int news_content_id,
                                                int size,
                                                int index) {


        iComment.doGetCommentChildListInLoadMore(
                url,
                news_content_comment_id,
                news_content_id,
                size,
                index,
                new OnDataBackListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String data) {


                        List<CommentChild> list = parseSerilizable.getParseToList(data, CommentChild.class);
                        iCommentInfoView.onDataBackSuccessForGetChildCommentListInLoadMore(list);
                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iCommentInfoView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                        } else {
                            iCommentInfoView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                    }
                });


    }


    public void doGetCommentChildListAfterReply(String url,
                                                int news_content_comment_id,
                                                int news_content_id,
                                                int size,
                                                int index) {


        iComment.doGetCommentChildList(
                url,
                news_content_comment_id,
                news_content_id,
                size,
                index,
                new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {


                List<CommentChild> list = parseSerilizable.getParseToList(data, CommentChild.class);

                if (vertifyNotNull.isNotNullListSize(list)) {
                    iCommentInfoView.onDataBackSuccessForGetChildCommentListAfterReply(list);
                }


//                onDataBackSuccessForGetChildCommentList

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iCommentInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iCommentInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }


    public void doCommentComment(String url,
                                 int news_content_id,
                                 String comment,
                                 int parent_comment_id) {

        iComment.doCommentComment(url, news_content_id, comment, parent_comment_id, new OnDataBackListener() {
            @Override
            public void onStart() {
                iCommentInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iCommentInfoView.onDataBackSuccessForSendCommentComment();
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iCommentInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iCommentInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iCommentInfoView.dismissLoadingDialog();
            }
        });
    }
}
