package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/2/5.
 * 类描述
 * 版本
 */

public interface IGoodsListView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void dismissFreshLoading();

    void onDataBackFailInLoadMore(int code, String errorMsg);


    void onDataBackSuccessForGetGoodsList(List<Goods> newsList);


    void onDataBackSuccessForGetGoodsListInFresh(List<Goods> newsList);

    void onDataBackSuccessForGetGoodsListInLoadMore(List<Goods> newsList);
}
