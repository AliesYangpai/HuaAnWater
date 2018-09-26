package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/3/16.
 * 类描述
 * 版本
 */

public interface IActiveParticipateView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void dismissFreshLoading();

    void onDataBackFailInLoadMore(int code, String errorMsg);



    void onDataBackSuccessForParticipateCount(int count);
    void onDataBackSuccessForParticipateList(List<ActiveParticipate> activeParticipates);


    void onDataBackSuccessForGetParticipateListInFresh(List<ActiveParticipate> activeParticipates);

    void onDataBackSuccessForGetParticipateListInLoadMore(List<ActiveParticipate> activeParticipates);
}
