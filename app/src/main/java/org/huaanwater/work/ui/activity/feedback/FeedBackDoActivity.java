package org.huaanwater.work.ui.activity.feedback;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.huaanwater.work.R;
import org.huaanwater.work.callback.popwindow.PhotoPopwindowCallBack;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.entity.photo.PhotoConfig;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterFeedBackDo;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.iview.IFeedbackDoView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.popwindow.PhotoPopWindow;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedBackDoActivity extends BaseActivity<IFeedbackDoView, PresenterFeedBackDo> implements
        IFeedbackDoView,
        OnClickListener,
        TakePhoto.TakeResultListener,
        InvokeListener,
        PermissionListener,
        PhotoPopwindowCallBack {


    private PresenterFeedBackDo presenterFeedBackDo;


    private ImageView iv_common_back;


    private EditText et_feedback_title;

    private EditText et_feedback_content;
    private ImageView iv_feed_1;
    private ImageView iv_feed_2;
    private ImageView iv_feed_3;

    private ImageView iv_commit;





    private int currentViewId;//缓存viewId

    /**
     * 图片相关
     *
     * @param savedInstanceState
     */

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private PhotoConfig photoConfig;


    /**
     * popwindow相关
     */

    private PhotoPopWindow photoPopWindow;


    private Map<Integer,String> tempMap;//缓存数据

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    /**
     * 获得裁剪的相关实体类
     *
     * @return
     */
    private PhotoConfig getPhotoConfig() {

        if (photoConfig == null) {

            photoConfig = new PhotoConfig();

            photoConfig.setCrop(false);
            photoConfig.setOwnCropTool(true);
            photoConfig.setCropWidth(800);
            photoConfig.setCropHight(800);


            /**
             * 压缩工具配置
             */
            photoConfig.setCompress(true);
            photoConfig.setCompressWidth(2500);
            photoConfig.setCompressHeight(2500);
            photoConfig.setMaxSize(102400);
            photoConfig.setShowCompressProcess(true);
            /**
             * 选择相片配置
             */
            photoConfig.setTakePhotoGallery(true);
            photoConfig.setMaxSelectPicCount(1);
            photoConfig.setFixRotationAngle(true);
            photoConfig.setSaveRawPic(true);


        }


        return photoConfig;

    }


    /**
     * 从相册从相机中获取 不裁剪
     */
    private void fromCamera() {

        takePhoto = getTakePhoto();
        photoConfig = getPhotoConfig();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(takePhoto, photoConfig);  //设置压缩图片类型
        configTakePhotoOption(takePhoto, photoConfig); //使用takePhoto自带相册

        takePhoto.onPickFromCapture(imageUri);//不裁剪
//        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(photoConfig));
    }

    /**
     * 从相册中获取 不裁剪
     */
    private void fromGallary() {

        takePhoto = getTakePhoto();
        photoConfig = getPhotoConfig();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto, photoConfig);  //设置压缩图片类型
        configTakePhotoOption(takePhoto, photoConfig); //使用takePhoto自带相册

        takePhoto.onPickFromGallery();  //不裁剪

//        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(photoConfig));


    }


    /**
     * 编辑压缩图片
     *
     * @param takePhoto
     */
    private void configCompress(TakePhoto takePhoto, PhotoConfig photoConfig) {


        CompressConfig config;
        config = new CompressConfig.Builder()
                .setMaxSize(photoConfig.getMaxSize())
                .setMaxPixel(photoConfig.getCompressWidth() >= photoConfig.getCompressHeight() ? photoConfig.getCompressWidth() : photoConfig.getCompressHeight())
                .enableReserveRaw(photoConfig.isSaveRawPic())
                .create();
        takePhoto.onEnableCompress(config, photoConfig.isShowCompressProcess());

    }


    /**
     * 选择图片配置，使用系统takePhoto相册
     *
     * @param takePhoto
     */
    private void configTakePhotoOption(TakePhoto takePhoto, PhotoConfig photoConfig) {


        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(photoConfig.isTakePhotoGallery()); //使用takephoto自带相册
        builder.setCorrectImage(photoConfig.isFixRotationAngle());//纠正拍照旋转
        takePhoto.setTakePhotoOptions(builder.create());


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_do);
        tempMap = new HashMap<>();
    }

    @Override
    protected PresenterFeedBackDo creatPresenter() {
        if (null == presenterFeedBackDo) {
            presenterFeedBackDo = new PresenterFeedBackDo(this);
        }
        return presenterFeedBackDo;
    }

    @Override
    protected void initViews() {


        et_feedback_title = findQHYViewById(R.id.et_feedback_title);
        iv_feed_1 = findQHYViewById(R.id.iv_feed_1);
        iv_feed_2=findQHYViewById(R.id.iv_feed_2);
        iv_feed_3 = findQHYViewById(R.id.iv_feed_3);

        iv_common_back = findQHYViewById(R.id.iv_common_back);
        et_feedback_content = findQHYViewById(R.id.et_feedback_content);
        iv_commit = findQHYViewById(R.id.iv_commit);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        iv_commit.setOnClickListener(this);

        iv_feed_1.setOnClickListener(this);
        iv_feed_2.setOnClickListener(this);
        iv_feed_3.setOnClickListener(this);
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
    public void onDataBackFail(int code, String errorMsg) {

        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onVertifyErrorForNoEnterTitle() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_your_advice_title);
    }

    @Override
    public void onVertifyErrorForNoEnterContent() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_your_advice);
    }

    @Override
    public void onDataBackSuccessForSendFeedOption() {


        this.setResult(ConstIntent.ResponseCode.GO_TO_FEED_BACK_ENTER_SUCCESS);
        this.finish();
    }

    @Override
    public void doPermissionCheck() {
        AndPermission
                .with(this)
                .requestCode(ConstIntent.RequestCode.APPLY_FOR_PERMISSION)
                .permission(Manifest.permission.CAMERA)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                        AndPermission.rationaleDialog(FeedBackDoActivity.this, rationale).show();
                    }
                })
                .callback(this)
                .start();
    }

    @Override
    public void doShowPermissionAlert() {
        AndPermission.defaultSettingDialog(FeedBackDoActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(FeedBackDoActivity.this.getString(R.string.permission_title))
                .setMessage(FeedBackDoActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(FeedBackDoActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(FeedBackDoActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void doShowPhotoPopWindow() {
        if (null == photoPopWindow) {
            photoPopWindow = new PhotoPopWindow(this);
            photoPopWindow.setPhotoPopwindowCallBack(this);
        }
        photoPopWindow.showAtLocation(iv_common_back,
                Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void doVertifyErrorForNullFlie() {
        ToastUtil.showMsg(getApplicationContext(), R.string.null_pic_file);
    }

    @Override
    public void onUploadStart(int what) {
        showLoadDialog();
    }

    @Override
    public void onUploadCancel(int what) {

    }

    @Override
    public void onUploadProgress(int what, int progress) {

    }

    @Override
    public void onUploadFinish(int what) {
        dismissLoadingDialog();
    }

    @Override
    public void onUploadError(int what, Exception exception) {
        ToastUtil.showMsg(getApplicationContext(), exception.getMessage());
    }

    @Override
    public void onDataBackSuccessForUpload(String path) {
        Log.i("upload_path", path);


        path = HttpConst.HTTP_PREFIX + path;


        switch (currentViewId) {

            case R.id.iv_feed_1:
                ImgUtil.getInstance().getImgFromNetByUrl(path, iv_feed_1, R.drawable.img_default_client_head_round);
                tempMap.put(currentViewId,path);
                break;

            case R.id.iv_feed_2:
                ImgUtil.getInstance().getImgFromNetByUrl(path, iv_feed_2, R.drawable.img_default_client_head_round);
                tempMap.put(currentViewId,path);
                break;

            case R.id.iv_feed_3:
                ImgUtil.getInstance().getImgFromNetByUrl(path, iv_feed_3, R.drawable.img_default_client_head_round);
                tempMap.put(currentViewId,path);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.iv_commit:


                String content = et_feedback_content.getText().toString().trim();
                String title = et_feedback_title.getText().toString().trim();

                presenterFeedBackDo.doFeedBack(HttpConst.URL.FEED_BACK,title, content,tempMap);

                break;

            case R.id.iv_feed_1:

                currentViewId = v.getId();
                presenterFeedBackDo.doPermissionCheck();
                break;


            case R.id.iv_feed_2:

                currentViewId = v.getId();
                presenterFeedBackDo.doPermissionCheck();
                break;


            case R.id.iv_feed_3:

                currentViewId = v.getId();
                presenterFeedBackDo.doPermissionCheck();
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        Log.i("upload_path", "takeSuccess中回调方法" + result.getImage().getCompressPath());
        presenterFeedBackDo.doUpload(HttpConst.URL.UPLOAD, result);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        ToastUtil.showMsg(this.getApplicationContext(), R.string.upload_fail);
    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }
    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {


        /**
         * 申请权限全部允许之前不会回调该方法
         */
        Log.i("quanxianxxxx", "onSucceed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                presenterFeedBackDo.doShowPhotoPopWindow();

                break;

        }

    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。
                presenterFeedBackDo.doShowPermissionAlert();

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        try {
            getTakePhoto().onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);

        } catch (Exception e) {

            ToastUtil.showMsg(getApplicationContext(), R.string.pic_error);

        }


    }


    @Override
    public void callBackFromCamera() {
        fromCamera();
    }

    @Override
    public void callBackFromGallery() {
        fromGallary();
    }
}
