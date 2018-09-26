package org.huaanwater.work.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.presenter.PresenterHelp;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IHelpView;

public class HelpActivity extends BaseActivity<IHelpView,PresenterHelp> implements
        IHelpView,
        OnClickListener{

    private PresenterHelp presenterHelp;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private LinearLayout ll_mine;
    private ImageView iv_arrow1;
    private TextView tv_detial_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    @Override
    protected PresenterHelp creatPresenter() {
        if(null ==presenterHelp) {
            presenterHelp= new PresenterHelp(this);
        }
        return presenterHelp;
    }

    @Override
    protected void initViews() {
        iv_common_back = findQHYViewById(R.id.iv_common_back);
        ll_mine = findQHYViewById(R.id.ll_mine);
        iv_arrow1 = findQHYViewById(R.id.iv_arrow1);
        tv_detial_1 = findQHYViewById(R.id.tv_detial_1);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        iv_arrow1.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

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
    public void doSetLocalDataToUi() {

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

            case R.id.iv_arrow1:

                if(tv_detial_1.getVisibility() ==View.VISIBLE) {
                    tv_detial_1.setVisibility(View.GONE);
                }else {

                    tv_detial_1.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
