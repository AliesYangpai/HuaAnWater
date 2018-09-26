package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.huaanwater.work.R;

import org.huaanwater.work.callback.viewpager.ItemChildViewPagerChangeListener;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.function.FunctionNews;
import org.huaanwater.work.ui.adapter.forviewpager.child.GoodsChildImgAdapter;
import org.huaanwater.work.widget.bottomSpot.PageIndexView;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */


public class GoodsListAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {


    private FunctionNews functionNews;


    public GoodsListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        functionNews = new FunctionNews();
//        //Step.1 setMultiTypeDelegate(), 并重写getItemType() 方法
//        // 需要说明的是Entity 并不需要实现任何接口 ，只需要能够判断出该实体对应的是哪个布局类型即可
//        setMultiTypeDelegate(new MultiTypeDelegate<Goods>() {
//            @Override
//            protected int getItemType(Goods item) {
//                //infer the type by entity
//
//                return functionNews.getPicCount(item.getImage_url());
//            }
//        });
//        //Step.2 getMultiTypeDelegate().registerItemType() 设置每种type对应的布局
//        getMultiTypeDelegate()
//                .registerItemType(ConstLocalData.ADATER_PIC_0, R.layout.item_news_pic0)
//                .registerItemType(ConstLocalData.ADATER_PIC_1, R.layout.item_news_pic1)
//                .registerItemType(ConstLocalData.ADATER_PIC_3, R.layout.item_news_pic3);
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item) {


        helper.setText(R.id.tv_news_title, item.getTitle())
                .setText(R.id.tv_create_time, item.getCreate_time());
        PageIndexView pageIndexView = helper.getView(R.id.store_top_indexpage_fix);
        pageIndexView.seticonWidth(18);
        pageIndexView.setTotalPage(functionNews.getPicCount(item.getImage_url()));
        pageIndexView.setCurrentPage(0);

        ViewPager viewPager = helper.getView(R.id.vp_goods_pic);
        GoodsChildImgAdapter goodsChildImgAdapter =
                new GoodsChildImgAdapter(mContext, item,functionNews);
        viewPager.setAdapter(goodsChildImgAdapter);

        viewPager.addOnPageChangeListener(new ItemChildViewPagerChangeListener(pageIndexView));



    }
}



