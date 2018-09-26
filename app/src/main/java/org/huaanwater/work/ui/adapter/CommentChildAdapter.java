package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.CommentChild;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.function.FunctionComment;

/**
 * Created by Alie on 2018/2/26.
 * 类描述   评论的adapter
 * 版本
 */

public class CommentChildAdapter extends BaseQuickAdapter<CommentChild, BaseViewHolder> {


    public CommentChildAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }




    @Override
    protected void convert(BaseViewHolder helper, CommentChild item) {


//        ImgUtil.getInstance().getRadiusImgFromNetByUrl(item.getUser_name(),iv_customer_head);







        helper.setText(R.id.tv_customer_name,item.getUser_name())
                .setText(R.id.tv_customer_comment,item.getComment())
                .setText(R.id.tv_customer_time,item.getCreate_time());

    }
}
