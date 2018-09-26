package org.huaanwater.work.ui.adapter.forviewpager.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.function.FunctionNews;
import org.huaanwater.work.ui.activity.goods.GoodsDetailActivity;
import org.huaanwater.work.util.ImgUtil;

import java.util.List;

public class GoodsChildImgAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private Goods goods;
    private String[] mPhotoUrlArry;


    public GoodsChildImgAdapter(
            Context context,
            Goods goods,
            FunctionNews functionNews) {
        this.context = context;
        this.goods = goods;
        this.mPhotoUrlArry = functionNews.getPicArry(goods.getImage_url());
        this.mLayoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return mPhotoUrlArry.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv;
        String photoUrl = mPhotoUrlArry[position];

        iv = (ImageView) mLayoutInflater.inflate(R.layout.item_image_goods_child_view, container, false);
        ImgUtil.getInstance().getImgFromNetByUrl(photoUrl, iv, R.drawable.img_hold_seat_banner);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context,GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_GOODS, goods);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


        container.addView(iv);

        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
