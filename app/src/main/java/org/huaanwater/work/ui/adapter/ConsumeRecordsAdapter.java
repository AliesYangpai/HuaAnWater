package org.huaanwater.work.ui.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.Consume;
import org.huaanwater.work.function.FunctionConsume;
import org.huaanwater.work.test.TestConsume;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */

public class ConsumeRecordsAdapter extends BaseQuickAdapter<Consume, BaseViewHolder> {



    private FunctionConsume functionConsume;

    public ConsumeRecordsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        functionConsume = new FunctionConsume();
    }

    @Override
    protected void convert(BaseViewHolder helper, Consume consume) {



        helper.setText(R.id.tv_time, consume.getCreate_times())
                .setText(R.id.tv_description,functionConsume.getHzOrderType(consume.getType()))
                .setText(R.id.tv_count, ConstSign.MINUS+consume.getAmount()+ ConstHz.RMB);

    }


}
