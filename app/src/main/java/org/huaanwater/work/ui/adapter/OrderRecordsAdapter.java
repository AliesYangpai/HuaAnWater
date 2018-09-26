package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.Order;
import org.huaanwater.work.entity.UserPoint;
import org.huaanwater.work.function.FunctionOrder;
import org.huaanwater.work.function.FunctionUserPoint;
import org.huaanwater.work.logic.LogicUserPoint;
import org.huaanwater.work.test.TestConsume;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */

public class OrderRecordsAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {


    private FunctionOrder functionOrder;

    public OrderRecordsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        functionOrder = new FunctionOrder();
    }

    @Override
    protected void convert(BaseViewHolder helper, Order order) {


        helper.setText(R.id.tv_time, order.getCreate_times())
                .setText(R.id.tv_status, functionOrder.getHzOrderStatus(order.getStatus()))
                .setText(R.id.tv_amount, order.getAmount()+ ConstHz.RMB);

    }


}
