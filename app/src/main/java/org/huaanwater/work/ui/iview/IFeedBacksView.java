package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.feedback.FeedBack;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public interface IFeedBacksView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);

    void dismissFreshLoading();

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void onDataBackSuccessForGetFeedBacks(List<FeedBack> feedBacks);


    void onDataBackSuccessForGetFeedBacksInFresh(List<FeedBack> feedBacks);

    void onDataBackSuccessForGetFeedBacksInLoadMore(List<FeedBack> feedBacks);
}
