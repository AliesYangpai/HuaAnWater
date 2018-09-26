package org.huaanwater.work.ui.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.UserPoint;
import org.huaanwater.work.function.FunctionUserPoint;
import org.huaanwater.work.logic.LogicUserPoint;
import org.huaanwater.work.test.TestConsume;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */

public class UserPointRecordsAdapter extends BaseQuickAdapter<UserPoint, BaseViewHolder> {


    private LogicUserPoint logicUserPoint;
    private FunctionUserPoint functionUserPoint;
    public UserPointRecordsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        this.logicUserPoint = new LogicUserPoint();
        this.functionUserPoint = new FunctionUserPoint();
    }

    @Override
    protected void convert(BaseViewHolder helper, UserPoint userPoint) {


        boolean flowIn = logicUserPoint.isFlowIn(userPoint.getFlow_direction());


        TextView tv_points = helper.getView(R.id.tv_points);
        if(flowIn) {

            tv_points.setText(ConstSign.PLUS+userPoint.getPoints());
        }else {

            tv_points.setText(ConstSign.MINUS+userPoint.getPoints());
        }



        helper.setText(R.id.tv_time, userPoint.getCreate_time())
                .setText(R.id.tv_description,functionUserPoint.getHzHandleType(userPoint.getHandle_type()))
                .setText(R.id.tv_all_points, String.valueOf(userPoint.getTotal_points()));

    }


}
