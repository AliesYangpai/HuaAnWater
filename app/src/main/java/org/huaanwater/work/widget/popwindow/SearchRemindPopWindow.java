package org.huaanwater.work.widget.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ListPopupWindow;

import org.huaanwater.work.R;
import org.huaanwater.work.ui.adapter.forlistview.SearchHistoryAdapter;

import java.lang.ref.WeakReference;

/**
 * Created by Alie on 2018/3/7.
 * 类描述
 * 版本
 */

public class SearchRemindPopWindow extends ListPopupWindow {


    public SearchRemindPopWindow(@NonNull Context context) {
        super(context);

        ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(context, R.color.white));


        this.setBackgroundDrawable(colorDrawable);

    }

    public SearchRemindPopWindow(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchRemindPopWindow(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
