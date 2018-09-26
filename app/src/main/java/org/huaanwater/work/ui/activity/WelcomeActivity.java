package org.huaanwater.work.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.presenter.PresenterWelcome;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IWelcomView;
import org.huaanwater.work.util.SpUtil;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.bottomSpot.CirclePageIndicator;
import org.huaanwater.work.widget.bottomSpot.WelViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 欢迎页，viewpager
 */
public class WelcomeActivity extends BaseActivity<IWelcomView, PresenterWelcome> implements IWelcomView {


    private PresenterWelcome presenterWelcome;


    private ViewPager viewPager;
    private CirclePageIndicator circlePageIndicator;
    private WelViewPagerAdapter mAdapter;


    @Override
    protected PresenterWelcome creatPresenter() {
        if (null == presenterWelcome) {

            presenterWelcome = new PresenterWelcome(this);
        }
        return presenterWelcome;
    }

    @Override
    protected void initViews() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        View pager01 = getLayoutInflater().inflate(R.layout.view_single_page, null);
        View pager02 = getLayoutInflater().inflate(R.layout.view_single_page, null);
        View pager03 = getLayoutInflater().inflate(R.layout.view_single_page_go, null);

        ImageView pageImage01 = (ImageView) pager01.findViewById(R.id.wizard_image);
        ImageView pageImage02 = (ImageView) pager02.findViewById(R.id.wizard_image);
        RelativeLayout rl_page = (RelativeLayout) pager03.findViewById(R.id.rl_page);

        TextView tv_go = (TextView) pager03.findViewById(R.id.tv_go);

        tv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                presenterWelcome.doGoToMain();

            }
        });


        try {

            pageImage01.setBackgroundResource(R.drawable.welcome_page1);
            pageImage02.setBackgroundResource(R.drawable.welcome_page2);
            rl_page.setBackgroundResource(R.drawable.welcome_page3);

        } catch (Exception e) {

        }

        List<View> views = new ArrayList<View>();

        views.add(pager01);
        views.add(pager02);
        views.add(pager03);


        mAdapter = new WelViewPagerAdapter(views);

        viewPager.setAdapter(mAdapter);
        circlePageIndicator.setViewPager(viewPager, 0);
        circlePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void processIntent() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void doGoToMain() {


        /**
         * 开始跳转到主界面
         */


        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_LOAD_OR_NOT, true);


        openActivity(MainActivity.class,null);



    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }


}
