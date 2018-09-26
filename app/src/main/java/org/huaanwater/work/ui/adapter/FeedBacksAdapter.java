package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.entity.feedback.FeedBack;
import org.huaanwater.work.function.FunctionFeedBack;
import org.huaanwater.work.logic.LogicFeedBack;

/**
 * Created by Alie on 2018/2/26.
 * 类描述   评论的adapter
 * 版本
 */

public class FeedBacksAdapter extends BaseQuickAdapter<FeedBack, BaseViewHolder> {


    private FunctionFeedBack functionFeedBack;
    private LogicFeedBack logicFeedBack;

    public FeedBacksAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        this.functionFeedBack = new FunctionFeedBack();
        this.logicFeedBack = new LogicFeedBack();
    }




    @Override
    protected void convert(BaseViewHolder helper, FeedBack item) {



//        iv_alert
//                tv_feedback_title
//        tv_is_reply
//                tv_time



            helper.setGone(R.id.tv_is_reply,logicFeedBack.isReply(item.getFeedback_status()))
                    .setText(R.id.tv_feedback_title,item.getTitle())
                    .setText(R.id.tv_time, functionFeedBack.getFrontTime(item.getCreate_time()));



//        helper.setText(R.id.tv_is_read,functionFeedBack.getHzRead(item.isRec_read()))
//                .setText(R.id.tv_user_name, String.valueOf(item.getFull_name()))
//                .setText(R.id.tv_time,item.getCreate_time());

    }
}
