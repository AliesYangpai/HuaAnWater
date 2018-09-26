package org.huaanwater.work.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 头条新闻Viewpager
 * 
 * @author Kevin
 * @date 2015-11-13
 */
public class ChildGoodsPicViewPager extends ViewPager {

	private int startX;
	private int startY;

	public ChildGoodsPicViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ChildGoodsPicViewPager(Context context) {
		super(context);
	}

	/**
	 * 分情况来决定何时需要拦截,何时不需要拦截 1. 上下滑动, 需要拦截 2. 向右滑动,并且当前是第一个item,需要拦截 3.
	 * 向左滑动,并且当前是最后一个item,需要拦截
	 * 
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);// 请求所有父控件不拦截事件

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getX();
			int moveY = (int) ev.getY();

			int dx = moveX - startX;
			int dy = moveY - startY;

			if (Math.abs(dx) > Math.abs(dy)) {
				int currentItem = getCurrentItem();

				// 左右滑动
				if (dx > 0) {
					// 向右划
					if (currentItem == 0) {
						// 第一个
						getParent().requestDisallowInterceptTouchEvent(false);// 需要父控件拦截
					}
				} else {
					// 向左划
					if (currentItem == getAdapter().getCount() - 1) {
						// 最后一个
						getParent().requestDisallowInterceptTouchEvent(false);// 需要父控件拦截
					}
				}

			} else {
				// 上下滑动
				getParent().requestDisallowInterceptTouchEvent(false);// 需要父控件拦截
			}

			break;

		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

}
