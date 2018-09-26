package org.huaanwater.work.ui.adapter.forviewpager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.PictureImgDisplayClickCallBack;
import org.huaanwater.work.util.ImgUtil;

import java.util.ArrayList;
import java.util.List;

public class PicturePageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> mPhotoUrlList;

    private PictureImgDisplayClickCallBack pictureImgDisplayClickCallBack;

    public void setPictureImgDisplayClickCallBack(PictureImgDisplayClickCallBack pictureImgDisplayClickCallBack) {
        this.pictureImgDisplayClickCallBack = pictureImgDisplayClickCallBack;
    }

    public PicturePageAdapter(Context context, List<String> photoUrlList) {
        mLayoutInflater = LayoutInflater.from(context);
        mPhotoUrlList = photoUrlList;
    }


    public PicturePageAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    public void setmPhotoUrlList(List<String> mPhotoUrlList) {
        if(null == mPhotoUrlList) {
            this.mPhotoUrlList = new ArrayList<>();
        }
        this.mPhotoUrlList = mPhotoUrlList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPhotoUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv;
        String photoUrl = mPhotoUrlList.get(position);

        iv = (ImageView) mLayoutInflater.inflate(R.layout.item_image_view, container, false);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != pictureImgDisplayClickCallBack) {

                    pictureImgDisplayClickCallBack.onDisplayClick();
                }
            }
        });
        ImgUtil.getInstance().getImgFromNetByUrl(photoUrl,iv, R.drawable.img_hold_seat_banner);
        container.addView(iv);

        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
