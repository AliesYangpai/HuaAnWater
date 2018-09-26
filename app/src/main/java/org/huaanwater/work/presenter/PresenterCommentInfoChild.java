package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.CommentChild;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IComment;
import org.huaanwater.work.method.impl.ICommentImpl;
import org.huaanwater.work.ui.iview.ICommentInfoChildView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/3/15.
 * 类描述
 * 版本
 */

public class PresenterCommentInfoChild extends BasePresenter<ICommentInfoChildView> {

    private ICommentInfoChildView iCommentInfoChildView;
    private IComment iComment;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterCommentInfoChild(ICommentInfoChildView iCommentInfoChildView) {
        this.iCommentInfoChildView = iCommentInfoChildView;
        this.iComment = new ICommentImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }

    public void doSetChildCommentDataToUi(CommentChild commentChild) {

        iCommentInfoChildView.doSetCommentInfoToUi(commentChild);
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
                        iCommentInfoChildView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {


                        List<CommentChild> list = parseSerilizable.getParseToList(data, CommentChild.class);
                        int parseCount = parseSerilizable.getParseCount(data);
                        if (vertifyNotNull.isNotNullListSize(list)) {
                            iCommentInfoChildView.onDataBackSuccessForGetChildCommentList(list);
                            iCommentInfoChildView.onDataBackSuccessForSetChildAllComment(parseCount);
                        }


//                onDataBackSuccessForGetChildCommentList

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iCommentInfoChildView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iCommentInfoChildView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFinish() {
                        iCommentInfoChildView.dismissLoadingDialog();
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
                        iCommentInfoChildView.onDataBackSuccessForGetChildCommentListInLoadMore(list);
                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iCommentInfoChildView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                        } else {
                            iCommentInfoChildView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
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
                            iCommentInfoChildView.onDataBackSuccessForGetChildCommentListAfterReply(list);
                        }


//                onDataBackSuccessForGetChildCommentList

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iCommentInfoChildView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iCommentInfoChildView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
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
                iCommentInfoChildView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iCommentInfoChildView.onDataBackSuccessForSendCommentComment();
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iCommentInfoChildView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iCommentInfoChildView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iCommentInfoChildView.dismissLoadingDialog();
            }
        });
    }
}
