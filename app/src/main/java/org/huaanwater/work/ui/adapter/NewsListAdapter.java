package org.huaanwater.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.thepakege.PakegeActive;
import org.huaanwater.work.function.FunctionNews;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.util.ImgUtil;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */


public class NewsListAdapter extends BaseQuickAdapter<PakegeActive, BaseViewHolder> {


    private FunctionNews functionNews;

    public NewsListAdapter() {
        super(null);
        functionNews = new FunctionNews();
        //Step.1 setMultiTypeDelegate(), 并重写getItemType() 方法
        // 需要说明的是Entity 并不需要实现任何接口 ，只需要能够判断出该实体对应的是哪个布局类型即可
        setMultiTypeDelegate(new MultiTypeDelegate<PakegeActive>() {
            @Override
            protected int getItemType(PakegeActive pakegeActive) {
                //infer the type by entity

                return functionNews.getItemType(pakegeActive);
            }
        });
        //Step.2 getMultiTypeDelegate().registerItemType() 设置每种type对应的布局
        getMultiTypeDelegate()
                .registerItemType(ConstLocalData.ADATER_PIC_0, R.layout.item_news_pic0)
                .registerItemType(ConstLocalData.ADATER_PIC_1, R.layout.item_news_pic1)
                .registerItemType(ConstLocalData.ADATER_PIC_3, R.layout.item_news_pic3)
                .registerItemType(ConstLocalData.ADATER_ACTIVE, R.layout.item_active);
    }

    @Override
    protected void convert(BaseViewHolder helper, PakegeActive pakegeActive) {

        //Step.3分 type 进行 convert() 操作
        switch (helper.getItemViewType()) {

            case ConstLocalData.ADATER_PIC_0:

                helper
//                        .setGone(R.id.tv_commont_count0, news.is_comment())
                        .setText(R.id.tv_news_title0, pakegeActive.getNews().getTitle())
                        .setText(R.id.tv_news_summary0,  pakegeActive.getNews().getSummary())
                        .setText(R.id.tv_create_time0,  pakegeActive.getNews().getCreate_time())
                        .setText(R.id.tv_commont_count0, ConstHz.COMMENT + ConstSign.DOUBLE_QUOTE +  pakegeActive.getNews().getReply_count())
                        .setText(R.id.tv_like_count0, ConstHz.PRAISE + ConstSign.DOUBLE_QUOTE +  pakegeActive.getNews().getLike_count());


                break;


            case ConstLocalData.ADATER_PIC_1:
                // do something


                ImageView iv_news_img1 = helper.getView(R.id.iv_news_img1);


                String[] arryPic1 = functionNews.getPicArry( pakegeActive.getNews().getImage_url());

                ImgUtil.getInstance().getImgFromNetByUrl(arryPic1[0], iv_news_img1, R.drawable.test_news);


                helper
//                        .setGone(R.id.tv_commont_count1, news.is_comment())
                        .setText(R.id.tv_news_title1,  pakegeActive.getNews().getTitle())
                        .setText(R.id.tv_news_summary1,  pakegeActive.getNews().getSummary())
                        .setText(R.id.tv_create_time1,  pakegeActive.getNews().getCreate_time())
                        .setText(R.id.tv_commont_count1, ConstHz.COMMENT + ConstSign.DOUBLE_QUOTE +  pakegeActive.getNews().getReply_count())
                        .setText(R.id.tv_like_count1, ConstHz.PRAISE + ConstSign.DOUBLE_QUOTE +  pakegeActive.getNews().getLike_count());

                break;


            case ConstLocalData.ADATER_PIC_3:
                // do something

                ImageView iv_news_img3_1 = helper.getView(R.id.iv_news_img3_1);
                ImageView iv_news_img3_2 = helper.getView(R.id.iv_news_img3_2);
                ImageView iv_news_img3_3 = helper.getView(R.id.iv_news_img3_3);


                String[] arryPic3 = functionNews.getPicArry( pakegeActive.getNews().getImage_url());

                if (null != arryPic3) {
                    ImgUtil.getInstance().getImgFromNetByUrl(arryPic3[0], iv_news_img3_1, R.drawable.test_news);
                    ImgUtil.getInstance().getImgFromNetByUrl(arryPic3[1], iv_news_img3_2, R.drawable.test_news);
                    ImgUtil.getInstance().getImgFromNetByUrl(arryPic3[2], iv_news_img3_3, R.drawable.test_news);

                }


                helper
//                        .setGone(R.id.tv_commont_count3, news.is_comment())
                        .setText(R.id.tv_news_title3,  pakegeActive.getNews().getTitle())
                        .setText(R.id.tv_create_time3,  pakegeActive.getNews().getCreate_time())
                        .setText(R.id.tv_commont_count3, ConstHz.COMMENT + ConstSign.DOUBLE_QUOTE +  pakegeActive.getNews().getReply_count())
                        .setText(R.id.tv_like_count3, ConstHz.PRAISE + ConstSign.DOUBLE_QUOTE +  pakegeActive.getNews().getLike_count());
                break;

            case ConstLocalData.ADATER_ACTIVE:
                ImageView iv_first_pic = helper.getView(R.id.iv_first_pic);
                helper.setText(R.id.tv_active_title,pakegeActive.getActive().getTitle());
                ImgUtil.getInstance().getImgFromNetByUrl(pakegeActive.getActive().getFirst_pic(), iv_first_pic, R.drawable.test_news);
                break;
        }


    }
}


//public class NewsListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
//
//
//    public NewsListAdapter(@LayoutRes int layoutResId) {
//        super(layoutResId);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, News news) {
//
//
//        ImageView  iv_news_img = helper.getView(R.id.iv_news_img);
//
//        ImgUtil.getInstance().getImgFromNetByUrl(news.getImage_url(),iv_news_img,R.drawable.test_news);
//
//        helper.setGone(R.id.tv_commont_count, news.is_comment())
//                .setText(R.id.tv_news_title, news.getTitle())
//                .setText(R.id.tv_catogray_name, news.getCategory_name())
//                .setText(R.id.tv_commont_count, ConstHz.COMMENT + ConstSign.DOUBLE_QUOTE + news.getReply_count())
//                .setText(R.id.tv_create_time, news.getCreate_time());
//
//
//    }
//
//
//}
