package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.thepakege.PakegeActive;
import org.huaanwater.work.function.FunctionActive;
import org.huaanwater.work.function.FunctionNews;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IActive;
import org.huaanwater.work.method.INews;
import org.huaanwater.work.method.impl.IActiveImpl;
import org.huaanwater.work.method.impl.INewsImpl;
import org.huaanwater.work.ui.iview.INewsListView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/2/5.
 * 类描述
 * 版本
 */

public class PresenterNewsList extends BasePresenter<INewsListView> {

    private INewsListView iNewsListView;
    private INews iNews;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private IActive iActive;

    private FunctionActive functionActive;
    private FunctionNews functionNews;

    public PresenterNewsList(INewsListView iNewsListView) {
        this.iNewsListView = iNewsListView;
        this.iNews = new INewsImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iActive = new IActiveImpl();
        this.functionActive = new FunctionActive();
        this.functionNews = new FunctionNews();
    }


    /**
     * 获取新闻列表
     *
     * @param url
     * @param size
     * @param index
     * @param tag_code
     * @param content_category_id
     */
    public void doGetNewsList(String url, int size, int index, String tag_code, String content_category_id) {

        iNews.doGetNewsList(url, size, index, tag_code, content_category_id, new OnDataBackListener() {
            @Override
            public void onStart() {
                iNewsListView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<News> list = parseSerilizable.getParseToList(data, News.class);


                if (vertifyNotNull.isNotNullListSize(list)) {

//                    iNewsListView.onDataBackSuccessForGetNewsList(list);

                    List<PakegeActive> pakegeActiveList = functionNews.getPakegeActiveList(list);
                    iNewsListView.onDataBackSuccessForGetPakageActiveList(pakegeActiveList);

                }


            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iNewsListView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iNewsListView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iNewsListView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 下拉刷新
     *
     * @param url
     * @param size
     * @param index
     * @param tag_code
     * @param content_category_id
     */
    public void doGetNewsListInFresh(String url, int size, int index, String tag_code, String content_category_id) {


        iNews.doGetNewsListInFresh(url, size, index, tag_code, content_category_id, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<News> list = parseSerilizable.getParseToList(data, News.class);

//                iNewsListView.onDataBackSuccessForGetNewsListInFresh(list);

                List<PakegeActive> pakegeActiveList = functionNews.getPakegeActiveList(list);
                iNewsListView.onDataBackSuccessForGetPakageActiveListInFresh(pakegeActiveList);

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iNewsListView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iNewsListView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {
                iNewsListView.dismissFreshLoading();
            }
        });

    }


    /**
     * 上拉加载更多
     *
     * @param url
     * @param size
     * @param index
     * @param tag_code
     * @param content_category_id
     */
    public void doGetNewsListInLoadMore(String url, int size, int index, String tag_code, String content_category_id) {

        iNews.doGetNewsListInLoadMore(url, size, index, tag_code, content_category_id, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                List<News> list = parseSerilizable.getParseToList(data, News.class);
//                iNewsListView.onDataBackSuccessForGetNewsListInLoadMore(list);


                List<PakegeActive> pakegeActiveList = functionNews.getPakegeActiveList(list);
                iNewsListView.onDataBackSuccessForGetPakageActiveListInLoadMore(pakegeActiveList);
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iNewsListView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iNewsListView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }


    /**
     * 获取活动列表
     */

    public void doGetActives(String url, boolean ascending, int size, int index) {

        iActive.doGetActives(url, ascending, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                List<Active> list = parseSerilizable.getParseToList(data, Active.class);
                int parseCount = parseSerilizable.getParseCount(data);
                if (vertifyNotNull.isNotNullListSize(list)) {


                    iNewsListView.onDataBackSuccessForGetActives(list, parseCount);

                }


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iNewsListView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iNewsListView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });

    }


    /**
     * 获取更多活动
     *
     * @param url
     * @param ascending
     * @param size
     * @param index
     */
    public void doGetMoreActives(List<Active> actives, int allCount, String url, boolean ascending, int size, int index) {


        if (!functionActive.isMaxActives(actives, allCount)) {

            iActive.doGetActives(url, ascending, size, index, new OnDataBackListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(String data) {

                    List<Active> list = parseSerilizable.getParseToList(data, Active.class);
                    int parseCount = parseSerilizable.getParseCount(data);
                    if (vertifyNotNull.isNotNullListSize(list)) {
                        iNewsListView.onDataBackSuccessForGetActivesMore(list, parseCount);
                    }


                }

                @Override
                public void onFail(int code, String data) {


                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                    if (vertifyNotNull.isNotNullObj(errorEntity)) {
                        iNewsListView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                    } else {
                        iNewsListView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }
                }

                @Override
                public void onFinish() {

                }
            });
        }
    }


    public void doAddActiveToItem(List<Active> actives) {

        PakegeActive randomActiveToPakage = functionActive.getRandomActiveToPakage(actives);

        iNewsListView.doAddRandomActiveToItem(randomActiveToPakage);
    }


    public void doDealItemClick(PakegeActive pakegeActive) {
        Active active = pakegeActive.getActive();
        News news = pakegeActive.getNews();
        if (vertifyNotNull.isNotNullObj(active)) {
            iNewsListView.doActiveJump(active);
            return;
        }

        if (vertifyNotNull.isNotNullObj(news)) {
            iNewsListView.doNewsJump(news);
            return;
        }

    }


}
