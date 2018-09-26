package org.huaanwater.work.presenter;

import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.verification.VertifyNotNull;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/11/2.
 * 类描述
 * 版本
 */

public abstract class BasePresenter<V>  {



    protected WeakReference<V> viewWeakReference;


    public void attachView(V view) {

        viewWeakReference  = new WeakReference<V>(view);

    }




    public void detachView() {

        if(null != viewWeakReference) {

            viewWeakReference.clear();

            viewWeakReference = null;

        }

    }

}
