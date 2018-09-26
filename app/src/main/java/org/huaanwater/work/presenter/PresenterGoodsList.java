package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IGoods;
import org.huaanwater.work.method.impl.IGoodsImpl;
import org.huaanwater.work.ui.iview.IGoodsListView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/5.
 * 类描述  好货的presenter
 * 版本
 */

public class PresenterGoodsList extends BasePresenter<IGoodsListView> {

    private IGoodsListView iGoodsListView;
    private IGoods iGoods;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterGoodsList(IGoodsListView iGoodsListView) {
        this.iGoodsListView = iGoodsListView;
        this.iGoods = new IGoodsImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    /**
     * 获取新闻列表
     * @param url
     * @param size
     * @param index
     * @param tag_code
     * @param content_category_id
     */
    public void doGetGoodsList(String url, int size, int index,String tag_code,String content_category_id) {

        iGoods.doGetGoodsList(url, size, index, tag_code, content_category_id, new OnDataBackListener() {
            @Override
            public void onStart() {
                iGoodsListView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<Goods> list = parseSerilizable.getParseToList(data,Goods.class);
                
                if(vertifyNotNull.isNotNullListSize(list)) {
                    iGoodsListView.onDataBackSuccessForGetGoodsList(list);
                }


            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iGoodsListView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iGoodsListView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iGoodsListView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 下拉刷新
     * @param url
     * @param size
     * @param index
     * @param tag_code
     * @param content_category_id
     */
    public void doGetGoodsListInFresh(String url, int size, int index,String tag_code,String content_category_id) {


        iGoods.doGetGoodsListInFresh(url, size,index,tag_code,content_category_id,new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<Goods> list = parseSerilizable.getParseToList(data,Goods.class);
                iGoodsListView.onDataBackSuccessForGetGoodsListInFresh(list);
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iGoodsListView.onDataBackFail(code,errorEntity.getError_message());
                }else  {

                    iGoodsListView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {
                iGoodsListView.dismissFreshLoading();
            }
        });

    }


    /**
     * 上拉加载更多
     * @param url
     * @param size
     * @param index
     * @param tag_code
     * @param content_category_id
     */
    public void doGetGoodsListInLoadMore(String url,int size, int index,String tag_code,String content_category_id ) {

        iGoods.doGetGoodsListInLoadMore(url, size, index, tag_code, content_category_id, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                List<Goods> list = parseSerilizable.getParseToList(data,Goods.class);
                iGoodsListView.onDataBackSuccessForGetGoodsListInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iGoodsListView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {
                    iGoodsListView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }


}
