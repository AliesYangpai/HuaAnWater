package org.huaanwater.work.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
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
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.huaanwater.work.R;
import org.huaanwater.work.callback.popwindow.PhotoPopwindowCallBack;
import org.huaanwater.work.callback.popwindow.UserTypeSelectPopwindowCallBack;
import org.huaanwater.work.constant.ConstEvent;
import org.huaanwater.work.constant.ConstIntent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstLog;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.entity.EventEntity;
import org.huaanwater.work.entity.Region;
import org.huaanwater.work.entity.TokenInfo;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.UserType;
import org.huaanwater.work.entity.photo.PhotoConfig;
import org.huaanwater.work.entity.thirdabout.ali.AliParameters;
import org.huaanwater.work.entity.thirdabout.ali.ValidateAliEntity;
import org.huaanwater.work.entity.thirdabout.ali.fromali.AuthResult;
import org.huaanwater.work.entity.thirdabout.authlistabout.Authed;
import org.huaanwater.work.entity.thirdabout.wx.ValidateWxEntity;
import org.huaanwater.work.entity.thirdabout.wx.WxParameters;
import org.huaanwater.work.http.HttpConst;
import org.huaanwater.work.presenter.PresenterUserInfoEdit;
import org.huaanwater.work.test.TestContent;
import org.huaanwater.work.ui.BaseActivity;
import org.huaanwater.work.ui.adapter.forlistview.SearchHistoryAdapter;
import org.huaanwater.work.ui.iview.IUserInfoEditView;
import org.huaanwater.work.util.ImgUtil;
import org.huaanwater.work.util.SpUtil;
import org.huaanwater.work.util.StringUtil;
import org.huaanwater.work.util.ToastUtil;
import org.huaanwater.work.widget.popwindow.PhotoPopWindow;
import org.huaanwater.work.widget.popwindow.SearchRemindPopWindow;
import org.huaanwater.work.widget.popwindow.UserTypeSelPopWindow;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserInfoEditActivity extends BaseActivity<IUserInfoEditView, PresenterUserInfoEdit>
        implements
        IUserInfoEditView,
        OnClickListener,
        TakePhoto.TakeResultListener,
        InvokeListener,
        PhotoPopwindowCallBack,
        PermissionListener,
        TextWatcher,
        UserTypeSelectPopwindowCallBack,
        AdapterView.OnItemClickListener {


    private PresenterUserInfoEdit presenterUserInfoEdit;

    private ImageView iv_common_back;
    private ImageView iv_head;


    /**
     * 图片相关
     *
     * @param savedInstanceState
     */

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private PhotoConfig photoConfig;


    /**
     * 中间
     */

    private EditText et_user_name;
    private EditText et_address;
    private ImageView iv_clear_user_name;
    private ImageView iv_clear_address;

    private LinearLayout ll_address;//地址相关布局
    private TextView tv_user_type; //类型
    private LinearLayout ll_type_hide; //隐藏布局
    private EditText et_hide;
    private ImageView iv_clear_hide;


    private TextView tv_change_phone;

    private RelativeLayout rl_submit;

    private ImageView iv_wx_bind;
    private ImageView iv_qq_bind;
    private ImageView iv_ali_bind;
    private ImageView iv_anxin_bind;
    /**
     * popwindow相关
     */


    private PhotoPopWindow photoPopWindow;
    private UserTypeSelPopWindow userTypeSelPopWindow;
    private SearchRemindPopWindow searchRemindPopWindow;
    private SearchHistoryAdapter searchHistoryAdapter;
    private View view_address_divide;

    /**
     * 数据相关
     */


    private boolean isModifyPhone;
    private boolean isModifyAvatar;
    private List<Authed> autheds;
    private String currentUserTypeCode;//用于更换用户模式
    private int currentRegionId;//当前区域Id
    private boolean isClickFromPop = false; //该标记用于点击 是否继续fuzzysearch，
    private User currnentUser;


    /**
     * 绑定授权相关=====================================================================
     */
    /**
     * wx授权登陆路
     */
    private void doWxGetImportantCode() {


        String uuid = UUID.randomUUID().toString();

        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, ConstLocalData.WX_APPID, true);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = uuid;
        wxapi.sendReq(req);

    }


    /**
     * 检查获取授权要素
     * @param authResult
     * @return
     */
    private boolean checkAuthElementBack(AuthResult authResult) {

        boolean result = false;

        if (null != authResult) {
            String resultStatus = authResult.getResultStatus();
            // 判断resultStatus 为“9000”且result_code
            // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
            if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                // 获取alipay_open_id，调支付时作为参数extern_token 的value
                // 传入，则支付账户为该授权账户

                Log.i(ConstLog.THIRD_AUTH, "支付宝获取授权要素成功返回：" + authResult.getAuthCode());

                result = true;

            } else {


                result = false;

                ToastUtil.showMsg(getApplicationContext(), "授权失败");

                Log.i(ConstLog.THIRD_AUTH, "支付宝获取授权要素失败：" + authResult.getAuthCode());
            }


        }


        return result;

    }

    /**
     * 绑定授权相关=====================================================================
     */


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


    /**
     * 获取屏幕高度
     */
    public int getAndroiodScreenHeightPx() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;


        Log.d("h_bl", "屏幕高度（像素）：" + height);

        return height;

    }


    private void hideKeyboard(Context context, View v) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);

        boolean result = imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


        Log.i("ruanjianpan", result + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);


        presenterUserInfoEdit.doGetUserDataFromDbToUi();


        presenterUserInfoEdit.doGetAuthodritionList(HttpConst.URL.GET_AUTH_LIST);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected PresenterUserInfoEdit creatPresenter() {
        if (null == presenterUserInfoEdit) {
            presenterUserInfoEdit = new PresenterUserInfoEdit(this);
        }
        return presenterUserInfoEdit;
    }

    @Override
    protected void initViews() {

        iv_common_back = findQHYViewById(R.id.iv_common_back);
        iv_head = findQHYViewById(R.id.iv_head);


        /**
         * 中间
         */

        et_user_name = findQHYViewById(R.id.et_user_name);
        et_address = findQHYViewById(R.id.et_address);
        ll_address = findQHYViewById(R.id.ll_address);

        iv_clear_user_name = findQHYViewById(R.id.iv_clear_user_name);
        iv_clear_address = findQHYViewById(R.id.iv_clear_address);
        tv_change_phone = findQHYViewById(R.id.tv_change_phone);
        rl_submit = findQHYViewById(R.id.rl_submit);


        tv_user_type = findQHYViewById(R.id.tv_user_type); //类型
        ll_type_hide = findQHYViewById(R.id.ll_type_hide); //隐藏布局
        et_hide = findQHYViewById(R.id.et_hide);
        iv_clear_hide = findQHYViewById(R.id.iv_clear_hide);


        iv_wx_bind = findQHYViewById(R.id.iv_wx_bind);
        iv_qq_bind = findQHYViewById(R.id.iv_qq_bind);
        iv_ali_bind = findQHYViewById(R.id.iv_ali_bind);
        iv_anxin_bind = findQHYViewById(R.id.iv_anxin_bind);
        view_address_divide = findQHYViewById(R.id.view_address_divide);


        searchRemindPopWindow = new SearchRemindPopWindow(this);
        searchHistoryAdapter = new SearchHistoryAdapter(this);
        searchRemindPopWindow.setHeight(getAndroiodScreenHeightPx() / 3);
        searchRemindPopWindow.setAdapter(searchHistoryAdapter);
        searchRemindPopWindow.setAnchorView(view_address_divide);


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        iv_head.setOnClickListener(this);


        iv_clear_user_name.setOnClickListener(this);
        iv_clear_address.setOnClickListener(this);
        iv_clear_hide.setOnClickListener(this);
        ll_type_hide.setOnClickListener(this);

        tv_user_type.setOnClickListener(this);
        et_address.addTextChangedListener(this);


        searchRemindPopWindow.setOnItemClickListener(this);
        tv_change_phone.setOnClickListener(this);

        rl_submit.setOnClickListener(this);

        iv_wx_bind.setOnClickListener(this);
        iv_qq_bind.setOnClickListener(this);
        iv_ali_bind.setOnClickListener(this);
        iv_anxin_bind.setOnClickListener(this);


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
    public void doPermissionCheck() {
        AndPermission
                .with(this)
                .requestCode(ConstIntent.RequestCode.APPLY_FOR_PERMISSION)
                .permission(Manifest.permission.CAMERA)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                        AndPermission.rationaleDialog(UserInfoEditActivity.this, rationale).show();
                    }
                })
                .callback(this)
                .start();
    }

    @Override
    public void doShowPermissionAlert() {
        AndPermission.defaultSettingDialog(UserInfoEditActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(UserInfoEditActivity.this.getString(R.string.permission_title))
                .setMessage(UserInfoEditActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(UserInfoEditActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(UserInfoEditActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
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

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(path, iv_head, R.drawable.img_default_client_head_round, 120);

        presenterUserInfoEdit.doModifyAvatar(HttpConst.URL.AVATAR, path);

    }

    @Override
    public void onDbDataBackSuccessForUser(User user) {
        currnentUser = user;

        String avatar = user.getAvatar();
        currentUserTypeCode = user.getUser_type();
        currentRegionId = user.getRegion_id();
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_head, R.drawable.img_default_client_head_round, 120);

    }

    @Override
    public void onDbDataBackSuccessForHasBindAnXinAccount() {
        iv_anxin_bind.setImageResource(R.drawable.img_anxin_normal);
    }

    @Override
    public void onDbDataBackSuccessForUserTypeHz(String userTypeHz) {
        tv_user_type.setText(userTypeHz + getString(R.string.click_to_change));
    }

    @Override
    public void onDbDataBackSuccessForUserTypeShowTypeStudentLayout() {
        ll_type_hide.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDbDataBackSuccessForUserTypeShowTypeOtherLayout() {
        ll_type_hide.setVisibility(View.GONE);

    }

    @Override
    public void onDataBackSuccessForGetAuthroitions(List<Authed> list) {

        autheds = list;

        for (Authed authed : autheds) {

            String authoType = authed.getAuth_type();

            switch (authoType) {

                case ConstLocalData.WX:
                    iv_wx_bind.setImageResource(R.drawable.img_login_wx_normal);
                    break;
                case ConstLocalData.QQ:
                    iv_qq_bind.setImageResource(R.drawable.img_login_qq_normal);
                    break;
                case ConstLocalData.ALI:
                    iv_ali_bind.setImageResource(R.drawable.img_login_ali_normal);
                    break;
            }

        }


    }

    @Override
    public void onDataBackSuccessForNoAuthroitions() {
        iv_wx_bind.setImageResource(R.drawable.img_login_wx_press);
        iv_qq_bind.setImageResource(R.drawable.img_login_qq_press);
        iv_ali_bind.setImageResource(R.drawable.img_login_ali_press);
    }

    @Override
    public void doVertifyErrorForNoUserName() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_username_login);
    }

    @Override
    public void doVertifyErrorForNoAddress() {


        ToastUtil.showMsg(getApplicationContext(), R.string.enter_user_address);
    }

    @Override
    public void doVertifyErrorForNoStudentNo() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_student_no);
    }

    @Override
    public void onDataBackSuccessForEditUserInfo(User user) {


        this.setResult(ConstIntent.ResponseCode.GO_TO_USER_INFO_EDIT_SUCCESS);
        this.finish();
    }

    @Override
    public void onDataBackSuccessForModifyAvatar() {
        Log.i("upload_path", "图片修改成功返回");

        presenterUserInfoEdit.doGetUserInfo(HttpConst.URL.USER_CURRENT);
    }

    @Override
    public void onDataBackSuccessForGetUserInfo(User user) {
        isModifyAvatar = true;
    }

    @Override
    public void onDataBackSuccessForGetUserInfoAfterAnxinBind(User user) {
        currnentUser = user;
        iv_anxin_bind.setImageResource(R.drawable.img_anxin_normal);
    }


    @Override
    public void doHideSearchReminder() {


        if (null != searchRemindPopWindow) {
            searchRemindPopWindow.dismiss();
        }


    }


    @Override
    public void doShowSearchReminder(String str) {


        presenterUserInfoEdit.doGetFuzzySearch(HttpConst.URL.REGIONS, str);
    }

    @Override
    public void onDataBackSuccessForFuzzySearchRegions(List<Region> regions) {


        presenterUserInfoEdit.doSearchRemindPopWindowShow(regions);


    }

    @Override
    public void onDataBackErrorForFuzzySearchRegions() {


        presenterUserInfoEdit.doSearchRemindPopWindowDismiss();

    }

    @Override
    public void doSearchRemindPopWindowShow(List<Region> regions) {

        if (null != searchRemindPopWindow) {
            searchHistoryAdapter.setList(regions);
            searchRemindPopWindow.show();

        }

    }

    @Override
    public void doSearchRemindPopWindowdismiss() {

        if (null != searchRemindPopWindow && searchRemindPopWindow.isShowing()) {
            searchRemindPopWindow.dismiss();
        }

    }


    @Override
    public void doSearchRemindPopWindowDestory() {
        if (null == searchRemindPopWindow && searchRemindPopWindow.isShowing()) {
            searchRemindPopWindow.dismiss();
            searchRemindPopWindow = null;
        }
    }

    @Override
    public void doAnxinBind() {

        Intent intentAnxin = new Intent(this, AnXinBindActivity.class);
        startActivityForResult(intentAnxin, ConstIntent.RequestCode.GO_TO_ANXIN_BIND);

    }

    @Override
    public void doVertifyErrorForHasAnxinBind() {
        ToastUtil.showMsg(getApplicationContext(), R.string.has_andxin_bind);
    }

    @Override
    public void doVertifyErrorForHasWxBind() {
        ToastUtil.showMsg(getApplicationContext(), R.string.has_wx_bind);
    }

    @Override
    public void doVertifyErrorForHasAliBind() {
        ToastUtil.showMsg(getApplicationContext(), R.string.has_ali_bind);
    }

    @Override
    public void doWxBindAbout() {
        doWxGetImportantCode();
    }

    @Override
    public void doAliBindAbout() {

        presenterUserInfoEdit.doGetAuthParamatersForAli(HttpConst.URL.ALI_AUTH_PARAM);
    }

    @Override
    public void onDataBackSuccessForWxNotAuth(ValidateWxEntity validateWxEntity) {

        WxParameters parameters = validateWxEntity.getParameters();
        String auth_type = validateWxEntity.getAuth_type();
        String code = validateWxEntity.getCode();
        String access_token = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING);
        presenterUserInfoEdit.doBindWx(
                HttpConst.URL.BIND_AUTH,
                access_token,
                code,
                auth_type,
                parameters);

    }

    @Override
    public void doVertifyErrorForWxHasBindAnotherAlready() {
        ToastUtil.showMsg(getApplicationContext(),R.string.has_wx_bind_another);
    }



    @Override
    public void onDataBackSuccessForBindWx(TokenInfo tokenInfo) {

        ToastUtil.showMsg(getApplicationContext(),R.string.wx_bind_success);
        presenterUserInfoEdit.doGetAuthodritionListAfterBind(HttpConst.URL.GET_AUTH_LIST);
    }

    @Override
    public void onDataBackSuccessForGetAliAuthParamElement(String element) {
        final String authInfo = element;
        Log.i(ConstLog.THIRD_AUTH, element);
        if (!TextUtils.isEmpty(element)) {


            Observer<AuthResult> observer = new Observer<AuthResult>() {
                @Override
                public void onCompleted() {


                }

                @Override
                public void onError(Throwable e) {
                    Log.i(" rxJavaxxxx", " onError ---> " + e);
                }

                @Override
                public void onNext(AuthResult authResult) {
                    Log.i(" rxJavaxxxx", " onNext ---> " + authResult.toString());

                    if (checkAuthElementBack(authResult)) {


                        String authCode = authResult.getAuthCode();
                        String user_id = authResult.getUser_id();

                        /**
                         * 这里开始验证调用验证方法
                         */
                        presenterUserInfoEdit.doValidateAliAuth(HttpConst.URL.VALID_AUTH,authCode,ConstLocalData.ALI,user_id);


                    }
                }
            };


            Observable.create(new Observable.OnSubscribe<AuthResult>() {
                @Override
                public void call(Subscriber<? super AuthResult> subscriber) {
                    AuthTask authTask = new AuthTask(UserInfoEditActivity.this);
                    Map<String, String> map = authTask.authV2(authInfo, true);
                    AuthResult authResult = new AuthResult(map, true);
                    subscriber.onNext(authResult);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    @Override
    public void onDataBackSuccessForAliNotAuth(ValidateAliEntity validateAliEntity) {
        AliParameters aliParameters = validateAliEntity.getParameters();
        String aliCode = validateAliEntity.getCode();
        String auth_type = validateAliEntity.getAuth_type();
        String access_token = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING);

        presenterUserInfoEdit.doBindAli(
                HttpConst.URL.BIND_AUTH,
                access_token,
                aliCode,
                auth_type,
                aliParameters);
    }

    @Override
    public void onDataBackSuccessForBindAli(TokenInfo tokenInfo) {
        ToastUtil.showMsg(getApplicationContext(),R.string.ali_bind_success);
        presenterUserInfoEdit.doGetAuthodritionListAfterBind(HttpConst.URL.GET_AUTH_LIST);
    }

    @Override
    public void doVertifyErrorForAliHasBindAnotherAlready() {
        ToastUtil.showMsg(getApplicationContext(),R.string.has_ali_bind_another);
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventInActivty(EventEntity eventEntity) {

        if (null != eventEntity) {


            String notifyMsg = eventEntity.getNotifyMsg();

            switch (eventEntity.getNotifyTag()) {

                case ConstEvent.WXABOUT.GET_CODE_SUCCESS:

                    Log.i(ConstLog.THIRD_AUTH, eventEntity.getNotifyMsg());

                    presenterUserInfoEdit.doValidateWxAuth(HttpConst.URL.VALID_AUTH, notifyMsg, ConstLocalData.WX);

                    break;

                case ConstEvent.WXABOUT.GET_CODE_CANCEL:


                    ToastUtil.showMsg(getApplicationContext(), notifyMsg);

                    break;


                case ConstEvent.WXABOUT.GET_CODE_FAIL:

                    break;


            }

        }

    }

    @Override
    public void onBackPressed() {


        if (isModifyPhone) {

            this.setResult(ConstIntent.ResponseCode.GO_TO_USER_INFO_EDIT_SUCCESS);
            this.finish();

        } else if (isModifyAvatar) {


            this.setResult(ConstIntent.ResponseCode.GO_TO_MODIFY_AVATAR_SUCCESS);
            this.finish();

        } else {
            dofinishItself();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:


                if (isModifyPhone) {

                    this.setResult(ConstIntent.ResponseCode.GO_TO_USER_INFO_EDIT_SUCCESS);
                    this.finish();

                } else if (isModifyAvatar) {


                    this.setResult(ConstIntent.ResponseCode.GO_TO_MODIFY_AVATAR_SUCCESS);
                    this.finish();

                } else {
                    dofinishItself();
                }

                break;

            case R.id.iv_head:
                presenterUserInfoEdit.doPermissionCheck();
                break;


            case R.id.rl_submit:

                String userName = et_user_name.getText().toString().trim();
                String address = et_address.getText().toString().trim();
                String studentNo = et_hide.getText().toString().trim();


                presenterUserInfoEdit.doCommitUserInfo(
                        HttpConst.URL.USER_EDIT,
                        userName,
                        currentUserTypeCode,
                        address,
                        currentRegionId,
                        studentNo);
                break;


            case R.id.tv_change_phone:


                Intent intent = new Intent(this, ModifyPhoneActivity.class);
                startActivityForResult(intent, ConstIntent.RequestCode.GO_TO_MODIFY_PHONE);

                break;

            case R.id.iv_clear_user_name:
                et_user_name.setText(ConstSign.DOUBLE_QUOTE);
                break;

            case R.id.iv_clear_address:
                et_address.setText(ConstSign.DOUBLE_QUOTE);
                break;

            case R.id.iv_clear_hide:
                et_hide.setText(ConstSign.DOUBLE_QUOTE);
                break;


            case R.id.iv_wx_bind:

//                ToastUtil.showMsg(getApplicationContext(), "微信绑定");
//                presenterUserInfoEdit.doGetFuzzySearch(HttpConst.URL.REGIONS, "");


                presenterUserInfoEdit.doDealWxBind(autheds);
                break;

            case R.id.iv_qq_bind:


                ToastUtil.showMsg(getApplicationContext(), "qq绑定");

                break;

            case R.id.iv_anxin_bind:


                presenterUserInfoEdit.doDealAnxinBind(currnentUser.getAn_xin_account());

                break;

            case R.id.iv_ali_bind:



                presenterUserInfoEdit.doDealAliBind(autheds);
                break;

            case R.id.tv_user_type:

                hideKeyboard(this, tv_user_type);

                if (null == userTypeSelPopWindow) {
                    userTypeSelPopWindow = new UserTypeSelPopWindow(this);
                    userTypeSelPopWindow.setUserTypeSelectPopwindowCallBack(this);
                }
                userTypeSelPopWindow.showAtLocation(iv_common_back,
                        Gravity.BOTTOM, 0, 0);

                break;


        }
    }

    @Override
    public void takeSuccess(TResult result) {

        Log.i("upload_path", "takeSuccess中回调方法" + result.getImage().getCompressPath());

        presenterUserInfoEdit.doUpload(HttpConst.URL.UPLOAD, result);
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

                presenterUserInfoEdit.doShowPhotoPopWindow();

                break;

        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。
                presenterUserInfoEdit.doShowPermissionAlert();

                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (resultCode) {
            case ConstIntent.ResponseCode.GO_TO_MODIFY_PHONE_SUCCESS:
                isModifyPhone = true;
                break;

            case ConstIntent.ResponseCode.GO_TO_ANXIN_BIND_SUCCESS:
                presenterUserInfoEdit.doGetUserInfoAfterAnXinBind(HttpConst.URL.USER_CURRENT);
                ToastUtil.showMsg(getApplicationContext(), R.string.anxin_account_bind_success);
                break;

            default:
                try {
                    getTakePhoto().onActivityResult(requestCode, resultCode, data);
                    super.onActivityResult(requestCode, resultCode, data);

                } catch (Exception e) {

                    ToastUtil.showMsg(getApplicationContext(), R.string.pic_error);

                }
                break;


        }

//        if (resultCode == ConstIntent.ResponseCode.GO_TO_MODIFY_PHONE_SUCCESS) {
//            isModifyPhone = true;
//        }
//
//        if (resultCode == ConstIntent.ResponseCode.GO_TO_ANXIN_BIND_SUCCESS) {
//
//
//            presenterUserInfoEdit.doGetUserInfoAfterAnXinBind(HttpConst.URL.USER_CURRENT);
//
//            ToastUtil.showMsg(getApplicationContext(), R.string.anxin_account_bind_success);
//
//
//        } else {
//
//            try {
//                getTakePhoto().onActivityResult(requestCode, resultCode, data);
//                super.onActivityResult(requestCode, resultCode, data);
//
//            } catch (Exception e) {
//
//                ToastUtil.showMsg(getApplicationContext(), R.string.pic_error);
//
//            }
//
//        }


    }


    @Override
    public void callBackFromCamera() {
        fromCamera();
    }

    @Override
    public void callBackFromGallery() {
        fromGallary();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String str = s.toString().trim();


        if (isClickFromPop) {
            isClickFromPop = false;
            return;
        }

        presenterUserInfoEdit.doDealAlertSearchList(str);
//        Log.i("search","editeText中afterTextChanged======截取后的值："+str+" result："+result);


    }


    @Override
    public void onSelectStudentType(UserType userType) {

        currentUserTypeCode = userType.getTypeCode();
        tv_user_type.setText(userType.getTypeHz() + getString(R.string.click_to_change));
        et_hide.setText(ConstSign.DOUBLE_QUOTE);
        ll_type_hide.setVisibility(View.VISIBLE);
        presenterUserInfoEdit.doSearchRemindPopWindowDismiss();

    }

    @Override
    public void onSelectHousingEstateType(UserType userType) {


        currentUserTypeCode = userType.getTypeCode();
        tv_user_type.setText(userType.getTypeHz() + getString(R.string.click_to_change));
        et_hide.setText(ConstSign.DOUBLE_QUOTE);
        ll_type_hide.setVisibility(View.GONE);


    }

    @Override
    public void onSelectEnterpriseType(UserType userType) {

        currentUserTypeCode = userType.getTypeCode();
        tv_user_type.setText(userType.getTypeHz() + getString(R.string.click_to_change));
        et_hide.setText(ConstSign.DOUBLE_QUOTE);
        ll_type_hide.setVisibility(View.GONE);
    }

    @Override
    public void onSelectOtherType(UserType userType) {
        currentUserTypeCode = userType.getTypeCode();
        tv_user_type.setText(userType.getTypeHz() + getString(R.string.click_to_change));
        et_hide.setText(ConstSign.DOUBLE_QUOTE);
        ll_type_hide.setVisibility(View.GONE);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Region item = (Region) searchHistoryAdapter.getItem(position);
        isClickFromPop = true;
        currentRegionId = item.getId();
        presenterUserInfoEdit.doSearchRemindPopWindowDismiss();
        et_address.setText(item.getCity() + item.getName());


    }
}
