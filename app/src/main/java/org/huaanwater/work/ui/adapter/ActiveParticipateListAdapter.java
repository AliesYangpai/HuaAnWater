package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.viewpager.ItemChildViewPagerChangeListener;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.function.FunctionNews;
import org.huaanwater.work.ui.adapter.forviewpager.child.GoodsChildImgAdapter;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.widget.bottomSpot.PageIndexView;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */


public class ActiveParticipateListAdapter extends BaseQuickAdapter<ActiveParticipate, BaseViewHolder> {




    public ActiveParticipateListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);

    }

    @Override
    protected void convert(BaseViewHolder helper, ActiveParticipate item) {


        ImageView iv_head = helper.getView(R.id.iv_head);
        helper.setText(R.id.tv_num, item.getFull_name());

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(
                item.getAvatar(),
                iv_head,
                R.drawable.img_default_client_head_round,
                50);


    }
}



