package org.huaanwater.work.callback.viewpager;

import android.support.v4.view.ViewPager;

import org.huaanwater.work.widget.bottomSpot.PageIndexView;

/**
 * Created by Alie on 2018/3/15.
 * 类描述
 * 版本
 */

public class ItemChildViewPagerChangeListener implements ViewPager.OnPageChangeListener {


    private PageIndexView pageIndexView;

    public ItemChildViewPagerChangeListener(PageIndexView pageIndexView) {
        this.pageIndexView = pageIndexView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pageIndexView.setCurrentPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
