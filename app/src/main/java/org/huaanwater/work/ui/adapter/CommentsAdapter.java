package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.function.FunctionComment;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.StringUtil;

import java.util.List;

/**
 * Created by Alie on 2018/2/26.
 * 类描述   评论的adapter
 * 版本
 */

public class CommentsAdapter extends BaseQuickAdapter<CommentEntity, BaseViewHolder> {

    private FunctionComment functionComment;

    public CommentsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        this.functionComment = new FunctionComment();
    }




    @Override
    protected void convert(BaseViewHolder helper, CommentEntity item) {


//        ImgUtil.getInstance().getRadiusImgFromNetByUrl(item.getUser_name(),);



        helper.setText(R.id.tv_customer_name,item.getUser_name())
                .setText(R.id.tv_reply, ConstHz.REPLY+ ConstSign.SPACE+functionComment.getCommentCount(item.getComment_count()))
                .setText(R.id.tv_customer_comment,item.getComment())
                .setText(R.id.tv_customer_time,item.getCreate_time());

    }
}
