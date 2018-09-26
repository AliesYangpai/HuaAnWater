package org.huaanwater.work.widget.scrollview;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Alie on 2018/3/5.
 * 类描述
 * 版本
 */

public class ScrollViewVerticalNest extends ScrollView {

    public ScrollViewVerticalNest(Context context) {
        super(context);
    }

    public ScrollViewVerticalNest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewVerticalNest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
