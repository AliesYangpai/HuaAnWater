package org.huaanwater.work.ui.adapter.forviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.huaanwater.work.callback.PictureImgDisplayClickCallBack;

import java.util.List;

/**
 * 广告的adapter
 */
public class GoodsImgAdapter extends PagerAdapter {


	private List<ImageView> imageViews;

	public GoodsImgAdapter(List<ImageView> imageViews){
		this.imageViews = imageViews;
	}

	private PictureImgDisplayClickCallBack pictureImgDisplayClickCallBack;

	public void setPictureImgDisplayClickCallBack(PictureImgDisplayClickCallBack pictureImgDisplayClickCallBack) {
		this.pictureImgDisplayClickCallBack = pictureImgDisplayClickCallBack;
	}

	@Override
	public int getCount() {
		if(imageViews != null)
			return imageViews.size();
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(imageViews.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		ImageView iv = imageViews.get(position);
		container.addView(iv);
		iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(null != pictureImgDisplayClickCallBack) {
					pictureImgDisplayClickCallBack.onDisplayClick();
				}
			}
		});
		return iv;
	}
	
	

}
