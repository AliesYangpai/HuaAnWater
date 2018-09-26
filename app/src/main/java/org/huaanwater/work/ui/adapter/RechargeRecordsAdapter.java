package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.RechargeRecord;
import org.huaanwater.work.entity.UserPoint;
import org.huaanwater.work.function.FunctionPay;
import org.huaanwater.work.function.FunctionUserPoint;
import org.huaanwater.work.logic.LogicUserPoint;
import org.huaanwater.work.test.TestConsume;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */

public class RechargeRecordsAdapter extends BaseQuickAdapter<RechargeRecord, BaseViewHolder> {

    private FunctionPay functionPay;

    public RechargeRecordsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        functionPay = new FunctionPay();

    }

    @Override
    protected void convert(BaseViewHolder helper, RechargeRecord rechargeRecord) {





//        TextView tv_points = helper.getView(R.id.tv_points);
//        if(flowIn) {
//
//            tv_points.setText(ConstSign.PLUS+userPoint.getPoints());
//        }else {
//
//            tv_points.setText(ConstSign.MINUS+userPoint.getPoints());
//        }





        helper.setText(R.id.tv_time, rechargeRecord.getCreate_time())
                .setText(R.id.tv_description,functionPay.getHzPayChannel(rechargeRecord.getPayment_channel_id()))
                .setText(R.id.tv_count, ConstSign.PLUS+rechargeRecord.getAmount()+ ConstHz.RMB);

    }


}
