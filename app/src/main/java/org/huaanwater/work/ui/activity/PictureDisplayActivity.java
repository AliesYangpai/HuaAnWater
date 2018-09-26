package org.huaanwater.work.ui.activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.PictureImgDisplayClickCallBack;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.presenter.PresenterPictureDisplay;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.adapter.forviewpager.PicturePageAdapter;
import org.huaanwater.work.ui.iview.IPictureDisplayView;

import java.util.Arrays;
import java.util.List;

public class PictureDisplayActivity extends BaseActivity<IPictureDisplayView, PresenterPictureDisplay>
        implements
        IPictureDisplayView,
        ViewPager.OnPageChangeListener,
        PictureImgDisplayClickCallBack {


    private PresenterPictureDisplay presenterPictureDisplay;
    private ViewPager mViewPager;
    private PicturePageAdapter picturePageAdapter;

    private TextView tv_img_index;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */
    private List<String> currentPictures;
    private int currentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picture_display);

    }

    @Override
    protected PresenterPictureDisplay creatPresenter() {
        if (null == presenterPictureDisplay) {
            presenterPictureDisplay = new PresenterPictureDisplay(this);
        }
        return presenterPictureDisplay;
    }

    @Override
    protected void initViews() {
        tv_img_index = findQHYViewById(R.id.tv_img_index);
        mViewPager = findQHYViewById(R.id.show_origin_pic_vp);
        picturePageAdapter = new PicturePageAdapter(this,currentPictures);
        picturePageAdapter.setPictureImgDisplayClickCallBack(this);
        mViewPager.setAdapter(picturePageAdapter);
        mViewPager.setCurrentItem(currentIndex);
    }

    @Override
    protected void initListener() {
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();
        if (null != intent) {

            Bundle extras = intent.getExtras();
            if (null != extras) {

                String[] arry = extras.getStringArray(ConstIntent.BundleKEY.DELIVER_PICTURES);
                currentPictures = Arrays.asList(arry);
            }
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        currentIndex = position;
        tv_img_index.setText(currentIndex + 1 + "/" + currentPictures.size());
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onDisplayClick() {
        dofinishItself();
    }
}
