package org.huaanwater.work.presenter;

import com.google.gson.JsonObject;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.dao.impl.IUserDaoImpl;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.entity.recharge.ali.AliRechargeNecessaryInfo;
import org.huaanwater.work.entity.recharge.ali.checkresult.AliCheckResultInfo;
import org.huaanwater.work.entity.recharge.wx.WxRechargeNecessaryInfo;
import org.huaanwater.work.function.FunctionPay;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IPay;
import org.huaanwater.work.method.IRecord;
import org.huaanwater.work.method.IUser;
import org.huaanwater.work.method.impl.IPayImpl;
import org.huaanwater.work.method.impl.IRecordImpl;
import org.huaanwater.work.method.impl.IUserImpl;
import org.huaanwater.work.test.TestConsume;
import org.huaanwater.work.ui.iview.IBalanceView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

import javax.net.ssl.SSLServerSocket;

/**
 * Created by Alie on 2018/1/18.
 * 类描述
 * 版本
 */

public class PresenterBalance extends BasePresenter<IBalanceView> {

    private IBalanceView iBalanceView;
    private IUser iUser;
    private IRecord iRecord;

    private IPay iPay;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private IBaseDao<User> iUserDao;


    private FunctionPay functionPay;

    public PresenterBalance(IBalanceView iBalanceView) {
        this.iBalanceView = iBalanceView;

        this.iUser = new IUserImpl();
        this.iPay = new IPayImpl();
        this.iRecord = new IRecordImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iUserDao = new IUserDaoImpl();
        this.functionPay = new FunctionPay();
    }


    public void doGetUserInfo(String url) {

        iUser.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iBalanceView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                User user = parseSerilizable.getParseToObj(data, User.class);

                if (vertifyNotNull.isNotNullObj(user)) {

                    List<User> list = iUserDao.findAll(User.class);
                    if (vertifyNotNull.isNotNullListSize(list)) {
                        iUserDao.deleteAll(User.class);
                    }
                    user.save();
                    iBalanceView.onDataBackSuccessForGetUserInfo(user);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iBalanceView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iBalanceView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iBalanceView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 下拉刷新获取数据
     *
     * @param url
     */
    public void doGetUserInfoInFresh(String url) {

        iUser.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                User user = parseSerilizable.getParseToObj(data, User.class);

                if (vertifyNotNull.isNotNullObj(user)) {

                    List<User> list = iUserDao.findAll(User.class);
                    if (vertifyNotNull.isNotNullListSize(list)) {
                        iUserDao.deleteAll(User.class);
                    }
                    user.save();
                    iBalanceView.onDataBackSuccessForGetUserInfoInFresh(user);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iBalanceView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iBalanceView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iBalanceView.dismissFreshLoading();
            }
        });
    }





    public void doBalanceRechargeAli(
            String url,
            String order_type,
            String channel_id,
            String os_type,
            String device_type,
            String device_id,
            String amount,
            String payer) {




        iPay.doBalanceRechargeAli(
                url,
                order_type,
                channel_id,
                os_type,
                device_type,
                device_id,
                amount,
                payer, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iBalanceView.showLoadingDialog();

                    }

                    @Override
                    public void onSuccess(String data) {


                        AliRechargeNecessaryInfo aliRechargeNecessaryInfo = parseSerilizable.getParseToObj(data, AliRechargeNecessaryInfo.class);

                        if (vertifyNotNull.isNotNullObj(aliRechargeNecessaryInfo)) {

                            iBalanceView.doRxAliRecharge(aliRechargeNecessaryInfo);

                        } else {

                            iBalanceView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iBalanceView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                        } else {
                            iBalanceView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFinish() {

                        iBalanceView.dismissLoadingDialog();

                    }
                });


    }


    public void doBalanceRechargeWx(
            String url,
            String order_type,
            String channel_id,
            String os_type,
            String device_type,
            String device_id,
            String amount,
            String payer) {


        JsonObject jsonObjectAmount = new JsonObject();
        jsonObjectAmount.addProperty("amount", amount);


        iPay.doBalanceRechargeWx(
                url,
                order_type,
                channel_id,
                os_type,
                device_type,
                device_id,
                amount,
                payer, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iBalanceView.showLoadingDialog();

                    }

                    @Override
                    public void onSuccess(String data) {


                        WxRechargeNecessaryInfo wxRechargeNecessaryInfo = parseSerilizable.getParseToObj(data, WxRechargeNecessaryInfo.class);

                        if (vertifyNotNull.isNotNullObj(wxRechargeNecessaryInfo)) {



                            iBalanceView.doWxRecharge(wxRechargeNecessaryInfo);

                        } else {

                            iBalanceView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iBalanceView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                        } else {
                            iBalanceView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFinish() {

                        iBalanceView.dismissLoadingDialog();

                    }
                });


    }


    /**
     * 支付宝支付验证签名
     *
     * @param url
     * @param result
     * @param app_type
     * @param os_type
     * @param payer
     */
    public void doCheckAliPayResult(String url,
                                    String result,
                                    String app_type,
                                    String os_type,
                                    String payer) {


        AliCheckResultInfo aliCheckResultInfo = functionPay.getToGenerateAliPayResultDto(result);
        if (vertifyNotNull.isNotNullObj(aliCheckResultInfo) &&
                vertifyNotNull.isNotNullObj(aliCheckResultInfo.getAlipayTradeAppPayResponse())) {


            iPay.doCheckAliPayResult(url, aliCheckResultInfo, app_type, os_type, payer, new OnDataBackListener() {
                @Override
                public void onStart() {

                    iBalanceView.showLoadingDialog();

                }

                @Override
                public void onSuccess(String data) {


                    if (vertifyNotNull.isNotNullString(data)) {


                        if (Boolean.valueOf(data)) {
                            iBalanceView.onDataBackSuccessForAliCheck();
                        } else {
                            iBalanceView.onDataBackFail(ConstError.ERROR_ALI_CONFIREM_CODE, ConstError.ERROR_ALI_CONFIREM_MSG);
                            iBalanceView.dismissLoadingDialog();
                        }

                    } else {
                        iBalanceView.onDataBackFail(ConstError.ERROR_ALI_CONFIREM_CODE, ConstError.ERROR_ALI_CONFIREM_MSG);
                        iBalanceView.dismissLoadingDialog();
                    }
                }

                @Override
                public void onFail(int code, String data) {

                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                    if (vertifyNotNull.isNotNullObj(errorEntity)) {
                        iBalanceView.onDataBackFail(code, errorEntity.getError_message());
                    } else {
                        iBalanceView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }
                    iBalanceView.dismissLoadingDialog();

                }

                @Override
                public void onFinish() {


                }
            });


        }


    }





//    /**
//     * 微信验签 【微信支付成功后，不用进行验签，因此此方法作废】
//     *
//     * @param url
//     */
//    public void doWxCheckWxPayResultInfo(String url,
//                                         String app_type,
//                                         String os_type,
//                                         String payer,
//                                         int payment_record_id) {
//
//        iPay.doWxCheckWxPayResultInfo(
//                url,
//                app_type,
//                os_type,
//                payer,
//                payment_record_id,
//                new OnDataBackListener() {
//                    @Override
//                    public void onStart() {
//                        iBalanceView.showLoadingDialog();
//                    }
//
//                    @Override
//                    public void onSuccess(String data) {
//
//
//                        if (vertifyNotNull.isNotNullString(data)) {
//
//                            if (Boolean.valueOf(data)) {
//
//                                iBalanceView.onDataBackSuccessForWxCheck();
//
//                            } else {
//                                iBalanceView.onDataBackFail(ConstError.ERROR_WX_CONFIREM_CODE, ConstError.ERROR_WX_CONFIREM_MSG);
//
//                            }
//                        }else {
//                            iBalanceView.onDataBackFail(ConstError.ERROR_WX_CONFIREM_CODE, ConstError.ERROR_WX_CONFIREM_MSG);
//                        }
//
//                    }
//
//                    @Override
//                    public void onFail(int code, String data) {
//
//                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
//                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
//                            iBalanceView.onDataBackFail(code, errorEntity.getError_message());
//                        } else {
//                            iBalanceView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
//                        }
//                        iBalanceView.dismissLoadingDialog();
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//                });
//
//    }












//    public void doGetPayChannels(String url,
//                                 String order_type,
//                                 String os_type,
//                                 String device_type) {
//
//
//        iPay.doGetPayChannels(url, order_type, os_type, device_type, new OnDataBackListener() {
//            @Override
//            public void onStart() {
//                iBalanceView.showLoadingDialog();
//            }
//
//            @Override
//            public void onSuccess(String data) {
//
//                List<PayChannel> list = parseSerilizable.getParseToNoItemsList(data, PayChannel.class);
//
//                if(vertifyNotNull.isNotNullListSize(list)) {
//                    iBalanceView.onDataBackSuccessForShowPayChannels(list);
//                }
//
//            }
//
//            @Override
//            public void onFail(int code, String data) {
//
//                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
//                if (vertifyNotNull.isNotNullObj(errorEntity)) {
//                    iBalanceView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
//                } else {
//                    iBalanceView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                iBalanceView.dismissLoadingDialog();
//            }
//        });
//
//    }
}
