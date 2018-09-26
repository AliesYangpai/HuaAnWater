package org.huaanwater.work.widget.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import org.huaanwater.work.ui.iview.IWebViewLoadView;
import org.huaanwater.work.widget.AdhDialog;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7 0007.
 * 类描述
 * 版本
 */
public class WebClientPicDescrib extends WebViewClient {


    private Context context;//内存优化前
    private WeakReference<Context> weakReference;//内存优化后






    private IWebViewLoadView iWebViewLoadView;

    public WebClientPicDescrib(Context context,IWebViewLoadView iWebViewLoadView) {
//        this.context = context;//内存优化前
        this.weakReference = new WeakReference<Context>(context);//内存优化后
        this.iWebViewLoadView = iWebViewLoadView;

    }













    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候

        /**
         * 普通版Dialog
         */

        Log.i("webClientDescrib","onPageStarted:"+url);



        if(null != iWebViewLoadView) {

            iWebViewLoadView.showLoadingDialog();
        }




    }



    @Override
    public void onPageFinished(WebView view, String url) {// 网页加载结束的时候



        if(null != iWebViewLoadView) {


            Log.i("webclient","网页加载完毕onPageFinished");
            iWebViewLoadView.onWebViewLoadFinsh();

        }

    }






    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) { // 网页加载时的连接的网址
//        view.loadUrl(url);
//        return true;




        Log.i("webClientDescrib","shouldOverrideUrlLoading:"+url);






        if(url.startsWith("http:") || url.startsWith("https:") ) {
            view.loadUrl(url);
            return false;
        }else{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            context.startActivity(intent);//内存优化前
            weakReference.get().startActivity(intent);//内存优化后
            return true;
        }
    }










}
