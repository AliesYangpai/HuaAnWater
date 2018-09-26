package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.function.FunctionGoods;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IComment;
import org.huaanwater.work.method.IGoods;
import org.huaanwater.work.method.impl.ICommentImpl;
import org.huaanwater.work.method.impl.IGoodsImpl;
import org.huaanwater.work.ui.iview.IGoodsDetailView;
import org.huaanwater.work.ui.iview.IGoodsDetailView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/26.
 * 类描述  新闻详情的界面
 * 版本
 */

public class PresenterGoodsDetail extends BasePresenter<IGoodsDetailView> {


    private IGoodsDetailView iGoodsDetailView;
    private IGoods iGoods;
    private IComment iComment;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private FunctionGoods functionGoods;


    public PresenterGoodsDetail(IGoodsDetailView iGoodsDetailView) {
        this.iGoodsDetailView = iGoodsDetailView;
        this.iGoods = new IGoodsImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iComment = new ICommentImpl();
        this.functionGoods = new FunctionGoods();
    }


    /**
     * 获取新闻详情
     *
     * @param url
     */
    public void doGetGoodsDetail(final String url) {


        iGoods.doGetGoodsInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iGoodsDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                Goods goods = parseSerilizable.getParseToObj(data, Goods.class);

                if (vertifyNotNull.isNotNullObj(goods)) {

                    iGoodsDetailView.onDataBackSuccessForSetTitle(goods);

                    String[] arry = functionGoods.getGoodsArryImgs(goods.getImage_url());
                    if(vertifyNotNull.isNotNullArrySize(arry)) {
                        iGoodsDetailView.onDataBackSuccessForSetImgs(arry);
                    }


                    iGoodsDetailView.onDataBackSuccessForSetContentHtml(goods);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iGoodsDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iGoodsDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iGoodsDetailView.dismissLoadingDialog();

            }

            @Override
            public void onFinish() {

            }
        });

    }


    /**
     * webView加载完成后 设置后面的加载
     *
     * @param goods
     */
    public void doSetDataAfterWebContentLoad(Goods goods) {


        iGoodsDetailView.onDataBackSuccessForSetKeyWord(goods);
        iGoodsDetailView.onDataBackSuccessForSetLike(goods);

    }


    /**
     * 获取评论列表
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetCommentList(String url, long goods_content_id, int size, int index) {


        iComment.doGetCommentList(url, goods_content_id, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {


            }

            @Override
            public void onSuccess(String data) {


                List<CommentEntity> list = parseSerilizable.getParseToList(data, CommentEntity.class);

                if (vertifyNotNull.isNotNullListSize(list)) {
                    iGoodsDetailView.onDataBackSuccessForGetCommentList(list);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iGoodsDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iGoodsDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iGoodsDetailView.dismissLoadingDialog();

            }
        });


    }




    public void doGetCommentListInLoadMore(String url, long goods_content_id, int size, int index) {


        iComment.doGetCommentListInLoadMore(url, goods_content_id, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<CommentEntity> list = parseSerilizable.getParseToList(data, CommentEntity.class);
                iGoodsDetailView.onDataBackSuccessForGetCommentListInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iGoodsDetailView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iGoodsDetailView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doCommentGoods(String url, int goods_content_id, String comment) {


        iComment.doCommentNews(url, goods_content_id, comment, new OnDataBackListener() {
            @Override
            public void onStart() {
                iGoodsDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                iGoodsDetailView.onDataBackSuccessForSendGoodsComment();
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iGoodsDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iGoodsDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iGoodsDetailView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 点赞
     *
     * @param url
     */
    public void doLike(String url) {

        iComment.doLike(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                iGoodsDetailView.onDataBackSuccessForDoLike(data);

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iGoodsDetailView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iGoodsDetailView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
