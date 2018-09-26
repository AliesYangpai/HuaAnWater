package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.RechargeRecord;
import org.huaanwater.work.entity.feedback.FeedBack;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/3/2.
 * 类描述
 * 版本
 */

public interface IFgRechargesView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void onDataBackSuccessForGetRecharges(List<RechargeRecord> rechargeRecords);

    void onDataBackSuccessForGetRechargesInLoadMore(List<RechargeRecord> rechargeRecords);



}
