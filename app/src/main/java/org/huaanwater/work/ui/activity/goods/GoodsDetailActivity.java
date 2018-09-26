package org.huaanwater.work.ui.activity.goods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.PictureImgDisplayClickCallBack;
import org.huaanwater.work.callback.inputdialog.GoodsInputCallBack;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.Goods;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterGoodsDetail;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.activity.commen.CommenInfoActivity;
import org.huaanwater.work.ui.activity.PictureDisplayActivity;
import org.huaanwater.work.ui.activity.UserInfoActivity;
import org.huaanwater.work.ui.adapter.CommentsAdapter;
import org.huaanwater.work.ui.adapter.forviewpager.GoodsImgAdapter;
import org.huaanwater.work.ui.iview.IGoodsDetailView;
import org.huaanwater.work.ui.iview.IWebViewLoadView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.bottomSpot.PageIndexView;
import org.huaanwater.work.widget.dialog.comment.GoodsReplyInputDialog;
import org.huaanwater.work.widget.viewpager.AutoScrollViewPager;
import org.huaanwater.work.widget.webview.WebClientPicDescrib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsDetailActivity extends BaseActivity<IGoodsDetailView,PresenterGoodsDetail> implements
        IGoodsDetailView,
        IWebViewLoadView,
        View.OnClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener,
        GoodsInputCallBack,
        ViewPager.OnPageChangeListener,
        PictureImgDisplayClickCallBack {


    private PresenterGoodsDetail presenterGoodsDetail;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private LinearLayout ll_mine;


    /**
     * 滑动page相关
     */

    private AutoScrollViewPager vp_auto_banner;
    private GoodsImgAdapter adapter;
    private List<ImageView> images = new ArrayList<>();
    private PageIndexView page;
    private int nPageIndex = 0;

    /**
     * 新闻相关
     *
     * @param savedInstanceState
     */

    private TextView tv_news_title;
    private TextView tv_create_time;
    private WebView wv_picAndDescription;
    private LinearLayout ll_keywords;  //先隐藏，webView加载完成后再显示
    private LinearLayout ll_like_count;//先隐藏，webView加载完成后再显示
    private TextView tv_key_words;
    private ImageView iv_like;
    private TextView tv_like_count;


    /**
     * 评论相关
     */
    private View viewHead;
    private RecyclerView rv_list;
    private CommentsAdapter commentsAdapter;
    private LinearLayoutManager layoutManager;
    private LinearLayout  ll_comment;

    /**
     * dialog相关
     */





    private GoodsReplyInputDialog goodsReplyInputDialog;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */
    private Goods currentGoods;
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新

    private String[] currentArry;


    /**
     * 二号banner轮换
     *
     * @param
     * @return
     */


    private void startAutoBannerRunde(List<String> list) {


        if (null != list && list.size() > 0) {

            if (null != images && images.size() > 0) {

                images.clear();

            }

            for (int i = 0; i < list.size(); i++) {

                String url = list.get(i);

                images.add(getView(url));


            }


            page.setTotalPage(list.size());
            page.setCurrentPage(0);
            adapter.notifyDataSetChanged();
            vp_auto_banner.startAutoScroll();

        }

    }


    public ImageView getView(String url) {
        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewPager.LayoutParams());


        ImgUtil.getInstance().getImgFromNetByUrl(url, imageView, R.drawable.img_hold_seat_banner);



        return imageView;
    }

    /**
     * 广告页停止转动
     */
    private void stopAutoBannerRuning() {

        if (null != vp_auto_banner) {

            vp_auto_banner.stopAutoScroll();

        }
    }


    /**
     * 广告页开始转动
     */

    private void goOnAutoBannerRuning() {

        if (null != vp_auto_banner) {

            vp_auto_banner.startAutoScroll();

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        presenterGoodsDetail.doGetGoodsDetail(HttpConst.URL.NEWS_CONTENT + HttpConst.SEPARATOR + currentGoods.getNews_content_id());

    }

    @Override
    protected void onStop() {
        stopAutoBannerRuning();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        goOnAutoBannerRuning();

    }

    @Override
    protected void onDestroy() {

        if(null != goodsReplyInputDialog){

            goodsReplyInputDialog.dismiss();
            goodsReplyInputDialog = null;
        }
        stopAutoBannerRuning();
        super.onDestroy();

    }

    @Override
    protected PresenterGoodsDetail creatPresenter() {
        if (null == presenterGoodsDetail) {
            presenterGoodsDetail = new PresenterGoodsDetail(this);
        }

        return presenterGoodsDetail;
    }

    @Override
    protected void initViews() {

        /**
         * title
         */
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_mine = findQHYViewById(R.id.ll_mine);






        /**
         * 滑动page相关
         */

        viewHead = LayoutInflater.from(this).inflate(R.layout.headview_goods_detail,null);
        vp_auto_banner = (AutoScrollViewPager) viewHead.findViewById(R.id.vp_auto_banner_fix);
        page = (PageIndexView) viewHead.findViewById(R.id.store_top_indexpage_fix);
        page.seticonWidth(18);
        adapter = new GoodsImgAdapter(images);
        adapter.setPictureImgDisplayClickCallBack(this);
        vp_auto_banner.setAdapter(adapter);
        vp_auto_banner.setInterval(3500);






        /**
         * 新闻相关
         * @param savedInstanceState
         */

        tv_news_title = (TextView) viewHead.findViewById(R.id.tv_news_title);
        tv_create_time = (TextView) viewHead.findViewById(R.id.tv_create_time);


        wv_picAndDescription = (WebView) viewHead.findViewById(R.id.wv_picAndDescription); //图文详情
        wv_picAndDescription.getSettings().setJavaScriptEnabled(true);
        wv_picAndDescription.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_picAndDescription.getSettings().setDomStorageEnabled(true);
        wv_picAndDescription.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_picAndDescription.setBackgroundColor(0x00000000);

        wv_picAndDescription.getSettings().setLoadWithOverviewMode(true);
        wv_picAndDescription.setWebViewClient(new WebClientPicDescrib(this, this));


        ll_keywords = (LinearLayout) viewHead.findViewById(R.id.ll_keywords);  //先隐藏，webView加载完成后再显示
        ll_like_count = (LinearLayout) viewHead.findViewById(R.id.ll_like_count);//先隐藏，webView加载完成后再显示
        tv_key_words = (TextView) viewHead.findViewById(R.id.tv_key_words);

        iv_like = (ImageView) viewHead.findViewById(R.id.iv_like);
        tv_like_count = (TextView) viewHead.findViewById(R.id.tv_like_count);


        /**
         * 评论相关
         */



        rv_list = findQHYViewById(R.id.rv_list);
        commentsAdapter = new CommentsAdapter(R.layout.item_comment_goods);
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




        vp_auto_banner.setOnPageChangeListener(this);



        commentsAdapter.setOnLoadMoreListener(this, rv_list);
        //默认第一次加载会进入回调，如果不需要可以配置
        commentsAdapter.disableLoadMoreIfNotFullPage(rv_list);

        commentsAdapter.setOnItemClickListener(this);

        ll_like_count.setOnClickListener(this);



    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                currentGoods = (Goods) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_GOODS);
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
    public void onDataBackSuccessForSetTitle(Goods goods) {

        currentGoods = goods;
        tv_news_title.setText(currentGoods.getTitle());
        tv_create_time.setText(currentGoods.getCreate_time());

        tv_news_title.setFocusable(true);
        tv_news_title.setFocusableInTouchMode(true);
        tv_news_title.requestFocus();

    }

    @Override
    public void onDataBackSuccessForSetImgs(String[] arry) {

        this.currentArry = arry;
        startAutoBannerRunde(Arrays.asList(this.currentArry));
    }

    @Override
    public void onDataBackSuccessForSetContentHtml(Goods goods) {


        String html = goods.getContent();
        html = ConstLocalData.WEB_IMG_STYLE + html;
        wv_picAndDescription.loadDataWithBaseURL(null, html, ConstLocalData.MIME_TYPE, ConstLocalData.CODING_TYPE, null);


    }

    @Override
    public void onWebViewLoadFinsh() {


        ll_keywords.setVisibility(View.VISIBLE);
        ll_like_count.setVisibility(View.VISIBLE);

        presenterGoodsDetail.doSetDataAfterWebContentLoad(currentGoods);
        presenterGoodsDetail.doGetCommentList(HttpConst.URL.NEWS_CONTENT_COMMENT, currentGoods.getNews_content_id(),currentSize, ConstLocalData.DEFUALT_LIST_INDEX);

    }


    @Override
    public void onDataBackSuccessForSetKeyWord(Goods goods) {
        tv_key_words.setText(goods.getKey_word());
    }

    @Override
    public void onDataBackSuccessForSetLike(Goods goods) {
        tv_like_count.setText(String.valueOf(goods.getLike_count()));
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
    public void onDataBackSuccessForSendGoodsComment() {
        if(null != goodsReplyInputDialog) {
            goodsReplyInputDialog.dismiss();
            goodsReplyInputDialog = null;
        }


        presenterGoodsDetail.doGetCommentList(
                HttpConst.URL.NEWS_CONTENT_COMMENT,
                currentGoods.getNews_content_id(),
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);




    }

    @Override
    public void onDataBackSuccessForDoLike(String str) {
        tv_like_count.setText(String.valueOf(currentGoods.getLike_count()+ConstLocalData.NUM_INCREMENT_1));
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
                goodsReplyInputDialog = new GoodsReplyInputDialog();
                goodsReplyInputDialog.setGoodsInputCallBack(this);
                goodsReplyInputDialog.show(getSupportFragmentManager(), "dialog");
                break;

            case R.id.ll_like_count:

                presenterGoodsDetail.doLike(HttpConst.URL.UPDATE_LIKE_COUNT+HttpConst.SEPARATOR+currentGoods.getNews_content_id());
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


        presenterGoodsDetail.doGetCommentListInLoadMore(
                HttpConst.URL.NEWS_CONTENT_COMMENT,
                currentGoods.getNews_content_id(),
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);

    }

    @Override
    public void onInputSend(String content) {


        presenterGoodsDetail.doCommentGoods(HttpConst.URL.NEWS_CONTENT_COMMENT,currentGoods.getNews_content_id(),content);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        CommentEntity commentEntity = (CommentEntity) adapter.getData().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_COMMENT,commentEntity);
        openActivity(CommenInfoActivity.class,bundle);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        page.setCurrentPage(nPageIndex = position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDisplayClick() {
        Bundle bundle = new Bundle();
        bundle.putStringArray(ConstIntent.BundleKEY.DELIVER_PICTURES,this.currentArry);
        openActivity(PictureDisplayActivity.class,bundle);
    }
}
