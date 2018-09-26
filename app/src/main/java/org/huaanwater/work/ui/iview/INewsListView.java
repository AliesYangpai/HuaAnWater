package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.thepakege.PakegeActive;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/2/5.
 * 类描述
 * 版本
 */

public interface INewsListView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void dismissFreshLoading();

    void onDataBackFailInLoadMore(int code, String errorMsg);


    void onDataBackSuccessForGetNewsList(List<News> newsList);

    void onDataBackSuccessForGetNewsListInFresh(List<News> newsList);

    void onDataBackSuccessForGetNewsListInLoadMore(List<News> newsList);



    void onDataBackSuccessForGetPakageActiveList(List<PakegeActive> pakegeActives);

    void onDataBackSuccessForGetPakageActiveListInFresh(List<PakegeActive> pakegeActives);

    void onDataBackSuccessForGetPakageActiveListInLoadMore(List<PakegeActive> pakegeActives);



    void onDataBackSuccessForGetActives(List<Active> actives,int allCount);

    void onDataBackSuccessForGetActivesMore(List<Active> actives,int allCount);


    void doAddRandomActiveToItem(PakegeActive pakegeActive);


    void doNewsJump(News news);

    void doActiveJump(Active active);
}
