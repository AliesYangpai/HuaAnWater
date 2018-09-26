package org.huaanwater.work.ui.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.inputdialog.NewsInputCallBack;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterNewsDetail;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.commen.CommenInfoActivity;
import org.huaanwater.work.ui.activity.UserInfoActivity;
import org.huaanwater.work.ui.adapter.CommentsAdapter;
import org.huaanwater.work.ui.iview.INewsDetailView;
import org.huaanwater.work.ui.iview.IWebViewLoadView;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.dialog.comment.NewsReplyInputDialog;
import org.huaanwater.work.widget.webview.WebClientPicDescrib;

import java.util.List;

public class NewsDetailActivity extends BaseActivity<INewsDetailView, PresenterNewsDetail> implements
        INewsDetailView,
        IWebViewLoadView,
        OnClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener,
        NewsInputCallBack {


    private PresenterNewsDetail presenterNewsDetail;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private LinearLayout ll_mine;


    /**
     * 新闻相关
     *
     * @param savedInstanceState
     */

    private TextView tv_news_title;
    private TextView tv_create_time;
    private WebView wv_picAndDescription;
    private LinearLayout ll_keywords;  //先隐藏，webView加载完成后再显示
    private RelativeLayout rl_like_count;//先隐藏，webView加载完成后再显示
    private TextView tv_key_words;
    private ImageView iv_like;
    private TextView tv_like_count;
    private ImageView iv_redirect_send;


    /**
     * 评论相关
     */

    private RecyclerView rv_list;
    private View viewHead;
    private CommentsAdapter commentsAdapter;
    private LinearLayoutManager layoutManager;
    private LinearLayout ll_comment;

    /**
     * dialog相关
     */


    private NewsReplyInputDialog newsReplyInputDialog;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */
    private News currentNews;
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        presenterNewsDetail.doGetNewsDetail(HttpConst.URL.NEWS_CONTENT + HttpConst.SEPARATOR + currentNews.getNews_content_id());



    }


    @Override
    protected void onDestroy() {

        if (null != newsReplyInputDialog) {

            newsReplyInputDialog.dismiss();
            newsReplyInputDialog = null;
        }
        super.onDestroy();

    }

    @Override
    protected PresenterNewsDetail creatPresenter() {
        if (null == presenterNewsDetail) {
            presenterNewsDetail = new PresenterNewsDetail(this);
        }

        return presenterNewsDetail;
    }

    @Override
    protected void initViews() {

        /**
         * title
         */
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_mine = findQHYViewById(R.id.ll_mine);


        /**
         * 新闻相关
         * @param savedInstanceState
         */

//        tv_news_title = findQHYViewById(R.id.tv_news_title);
//        tv_create_time = findQHYViewById(R.id.tv_create_time);
//        wv_picAndDescription = findQHYViewById(R.id.wv_picAndDescription);
//
//
//        wv_picAndDescription = (WebView) findViewById(R.id.wv_picAndDescription); //图文详情
//        wv_picAndDescription.getSettings().setJavaScriptEnabled(true);
//        wv_picAndDescription.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        wv_picAndDescription.getSettings().setDomStorageEnabled(true);
//        wv_picAndDescription.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        wv_picAndDescription.setBackgroundColor(0x00000000);
//
//        wv_picAndDescription.getSettings().setLoadWithOverviewMode(true);
//        wv_picAndDescription.setWebViewClient(new WebClientPicDescrib(this, this));
//
//
//        ll_keywords = findQHYViewById(R.id.ll_keywords);  //先隐藏，webView加载完成后再显示
//        ll_like_count = findQHYViewById(R.id.ll_like_count);//先隐藏，webView加载完成后再显示
//        tv_key_words = findQHYViewById(R.id.tv_key_words);
//
//        iv_like = findQHYViewById(R.id.iv_like);
//        tv_like_count = findQHYViewById(R.id.tv_like_count);


        /**
         * 评论相关
         */

        rv_list = findQHYViewById(R.id.rv_list);

        viewHead = LayoutInflater.from(this).inflate(R.layout.headview_news_detail, null);


        tv_news_title = (TextView) viewHead.findViewById(R.id.tv_news_title);
        tv_create_time = (TextView) viewHead.findViewById(R.id.tv_create_time);
        wv_picAndDescription = (WebView) viewHead.findViewById(R.id.wv_picAndDescription);


        wv_picAndDescription = (WebView) viewHead.findViewById(R.id.wv_picAndDescription); //图文详情
        wv_picAndDescription.getSettings().setJavaScriptEnabled(true);
        wv_picAndDescription.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_picAndDescription.getSettings().setDomStorageEnabled(true);
        wv_picAndDescription.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_picAndDescription.setBackgroundColor(0x00000000);

        wv_picAndDescription.getSettings().setLoadWithOverviewMode(true);
        wv_picAndDescription.setWebViewClient(new WebClientPicDescrib(this, this));


        ll_keywords = (LinearLayout) viewHead.findViewById(R.id.ll_keywords);  //先隐藏，webView加载完成后再显示
        rl_like_count = (RelativeLayout) viewHead.findViewById(R.id.rl_like_count);//先隐藏，webView加载完成后再显示
        tv_key_words = (TextView) viewHead.findViewById(R.id.tv_key_words);

        iv_like = (ImageView) viewHead.findViewById(R.id.iv_like);
        iv_redirect_send = (ImageView) viewHead.findViewById(R.id.iv_redirect_send);
        tv_like_count = (TextView) viewHead.findViewById(R.id.tv_like_count);


        commentsAdapter = new CommentsAdapter(R.layout.item_comment_news);
        commentsAdapter.addHeaderView(viewHead);
        layoutManager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(commentsAdapter);
        ll_comment = findQHYViewById(R.id.ll_comment);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        ll_comment.setOnClickListener(this);

        commentsAdapter.setOnLoadMoreListener(this, rv_list);
        //默认第一次加载会进入回调，如果不需要可以配置
//        commentsAdapter.disableLoadMoreIfNotFullPage();
        commentsAdapter.disableLoadMoreIfNotFullPage(rv_list);
        commentsAdapter.setOnItemClickListener(this);


        iv_like.setOnClickListener(this);
        iv_redirect_send.setOnClickListener(this);


    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle bundle = intent.getExtras();

            if (null != bundle) {
                currentNews = (News) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_NEWS);

            }
        }
    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        commentsAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForSetTitle(News news) {

        currentNews = news;
        tv_news_title.setText(news.getTitle());
        tv_create_time.setText(news.getCreate_time());


        tv_news_title.setFocusable(true);
        tv_news_title.setFocusableInTouchMode(true);
        tv_news_title.requestFocus();

    }

    @Override
    public void onDataBackSuccessForSetContentHtml(News news) {


        String html = news.getContent();
        html = ConstLocalData.WEB_IMG_STYLE + html;
        wv_picAndDescription.loadDataWithBaseURL(null, html, ConstLocalData.MIME_TYPE, ConstLocalData.CODING_TYPE, null);


    }

    @Override
    public void onWebViewLoadFinsh() {


        ll_keywords.setVisibility(View.VISIBLE);
        rl_like_count.setVisibility(View.VISIBLE);
        presenterNewsDetail.doSetDataAfterWebContentLoad(currentNews);


        presenterNewsDetail.doGetCommentList(
                HttpConst.URL.NEWS_CONTENT_COMMENT,
                currentNews.getNews_content_id(),
                currentSize, ConstLocalData.DEFUALT_LIST_INDEX);
    }


    @Override
    public void onDataBackSuccessForSetKeyWord(News news) {
        tv_key_words.setText(news.getKey_word());
    }

    @Override
    public void onDataBackSuccessForSetLike(News news) {
        tv_like_count.setText(String.valueOf(news.getLike_count()));
    }


    @Override
    public void onDataBackSuccessForGetCommentList(List<CommentEntity> list) {

        commentsAdapter.setNewData(list);


    }

    @Override
    public void onDataBackSuccessForGetCommentListInLoadMore(List<CommentEntity> list) {
        if (null != list && list.size() > 0) {

            commentsAdapter.addData(list);
            commentsAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            commentsAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onDataBackSuccessForSendNewsComment() {
        if (null != newsReplyInputDialog) {
            newsReplyInputDialog.dismiss();
            newsReplyInputDialog = null;
        }


        presenterNewsDetail.doGetCommentList(
                HttpConst.URL.NEWS_CONTENT_COMMENT,
                currentNews.getNews_content_id(),
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);


    }

    @Override
    public void onDataBackSuccessForDoLike(String str) {
        tv_like_count.setText(String.valueOf(currentNews.getLike_count() + ConstLocalData.NUM_INCREMENT_1));
    }

    @Override
    public void onDataBackSuccessForTranspond(String str) {
        ToastUtil.showMsg(getApplicationContext(),getString(R.string.transpond_success));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:

                dofinishItself();
                break;

            case R.id.ll_mine:
                openActivity(UserInfoActivity.class, null);
                break;

            case R.id.ll_comment:

//                scrollMyListViewToBottom();
                newsReplyInputDialog = new NewsReplyInputDialog();
                newsReplyInputDialog.setNewsInputCallBack(this);
                newsReplyInputDialog.show(getSupportFragmentManager(), "dialog");
                break;

            case R.id.iv_like:

                presenterNewsDetail.doLike(HttpConst.URL.UPDATE_LIKE_COUNT + HttpConst.SEPARATOR + currentNews.getNews_content_id());
                break;


            case R.id.iv_redirect_send:

                presenterNewsDetail.doTranspond(HttpConst.URL.UPDATE_REPRINT_COUNT+HttpConst.SEPARATOR+currentNews.getNews_content_id());
                break;

        }
    }


//    /**
//     * listView滑动到底部
//     */
//    private void scrollMyListViewToBottom() {
//        rv_list.post(new Runnable() {
//            @Override
//            public void run() {
//                // Select the last row so it will scroll into view...
//                rv_list.scrollToPosition(commentsAdapter.getItemCount()-1);
//            }
//        });
//    }


    @Override
    public void onLoadMoreRequested() {


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;


        presenterNewsDetail.doGetCommentListInLoadMore(
                HttpConst.URL.NEWS_CONTENT_COMMENT,
                currentNews.getNews_content_id(),
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);

    }

    @Override
    public void onInputSend(String content) {


        presenterNewsDetail.doCommentNews(HttpConst.URL.NEWS_CONTENT_COMMENT, currentNews.getNews_content_id(), content);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        CommentEntity commentEntity = (CommentEntity) adapter.getData().get(position);


        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_COMMENT, commentEntity);
        openActivity(CommenInfoActivity.class, bundle);
    }
}
