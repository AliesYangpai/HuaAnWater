package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.logic.LogicActive;
import org.huaanwater.work.method.IActive;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.impl.IActiveImpl;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.ui.iview.IActiveDetialView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/3/11.
 * 类描述
 * 版本
 */

public class PresenterActiveDetail extends BasePresenter<IActiveDetialView> {

    private IActiveDetialView iActiveDetialView;
    private IActive iActive;
    private IRecord iRecord;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private LogicActive logicActive;

    public PresenterActiveDetail(IActiveDetialView iActiveDetialView) {
        this.iActiveDetialView = iActiveDetialView;
        this.iActive = new IActiveImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iRecord = new IRecordImpl();
        this.logicActive = new LogicActive();
    }


    public void doSetActiveDataToUi(Active active) {

        iActiveDetialView.doSetActiveDaraToUi(active);

    }


    /**
     * 获取活动详情
     *
     * @param url
     * @param activity_management_id
     */
    public void doGetActiveInfo(String url, long activity_management_id) {

        iActive.doGetActiveDetial(url, activity_management_id, new OnDataBackListener() {
            @Override
            public void onStart() {
                iActiveDetialView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                Active active = parseSerilizable.getParseToObj(data, Active.class);
                if (vertifyNotNull.isNotNullObj(active)) {
                    iActiveDetialView.onDataBackSuccessForActiveDetail(active);
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iActiveDetialView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iActiveDetialView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iActiveDetialView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 检查用户是否已经参与该活动了
     *
     * @param url
     * @param activity_management_id
     */
    public void doCheckActiveUserExist(String url, long activity_management_id) {

        iActive.doCheckActiveUserExist(url, activity_management_id, new OnDataBackListener() {
            @Override
            public void onStart() {
                iActiveDetialView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                if (vertifyNotNull.isNotNullString(data)) {

                    if (Boolean.valueOf(data)) {

                        iActiveDetialView.onDataBackSuccessForUserExist();
                        iActiveDetialView.dismissLoadingDialog();
                    } else {

                        iActiveDetialView.onDataBackSuccessForUserNotExist();
                    }
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iActiveDetialView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iActiveDetialView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iActiveDetialView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }


    public void doActiveParticipate(String url) {


        iActive.doActiveParticipate(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                iActiveDetialView.onDataBackSuccessForParticipate();

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iActiveDetialView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iActiveDetialView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iActiveDetialView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 获取参加活动的信息
     *
     * @param url
     * @param ascending
     * @param size
     * @param index
     */
    public void doGetParticipateList(String url, long activity_management_id, boolean ascending, int size, int index) {

        iRecord.doGetActivesParticipateRecord(url, activity_management_id, ascending, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iActiveDetialView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<ActiveParticipate> activeParticipates = parseSerilizable.getParseToList(data, ActiveParticipate.class);
                int parseCount = parseSerilizable.getParseCount(data);
                if (vertifyNotNull.isNotNullListSize(activeParticipates)) {


                    iActiveDetialView.onDataBackSuccessForParticipateToShowAvatarLayout();
                    if (logicActive.isJust1(activeParticipates)) {
                        iActiveDetialView.onDataBackSuccessForParticipatePersonList1(activeParticipates);
                    } else if (logicActive.isJust2(activeParticipates)) {
                        iActiveDetialView.onDataBackSuccessForParticipatePersonList2(activeParticipates);
                    } else {
                        iActiveDetialView.onDataBackSuccessForParticipatePersonList3(activeParticipates);
                    }

                    iActiveDetialView.onDataBackSuccessForParticipatePersonCount(parseCount);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iActiveDetialView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iActiveDetialView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iActiveDetialView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 检查用户是否已经参与该活动了
     *
     * @param url
     * @param activity_management_id
     */
    public void doCheckActiveUserExistFirstIn(String url, long activity_management_id) {

        iActive.doCheckActiveUserExist(url, activity_management_id, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {


                if (vertifyNotNull.isNotNullString(data)) {

                    if (Boolean.valueOf(data)) {

                        iActiveDetialView.onDataBackSuccessForUserExistFirstIn();

                    } else {

                        iActiveDetialView.onDataBackSuccessForUserNotExistFirstIn();
                    }
                }
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iActiveDetialView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iActiveDetialView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

            }
        });

    }
}
