package org.huaanwater.work.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.huaanwater.work.constant.ConstEvent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.EventEntity;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.pay_result);
//        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, ConstLocalData.WX_APPID);
////
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {


        WXEntryActivity.this.finish();

        if (null != baseResp) {

            if (baseResp instanceof SendAuth.Resp) {

                SendAuth.Resp resp = (SendAuth.Resp) baseResp;

                int errCode = resp.errCode;

                switch (errCode) {

                    case BaseResp.ErrCode.ERR_OK:


                        Log.i("wx_autonLoginback", "返回码:" + resp.errCode + " country:" + resp.country + " resp.code" + resp.code);


                        postEventToWXAuth(ConstEvent.WXABOUT.GET_CODE_SUCCESS,resp.code);



                        break;


                    case BaseResp.ErrCode.ERR_USER_CANCEL:


                        Log.i("wx_autonLoginback", "返回码:" + resp.errCode + " country:" + resp.country + " resp.code" + resp.code);

                        postEventToWXAuth(ConstEvent.WXABOUT.GET_CODE_CANCEL,"授权取消");


                        break;


                    case BaseResp.ErrCode.ERR_AUTH_DENIED:


                        Log.i("wx_autonLoginback", "返回码:" + resp.errCode + " country:" + resp.country + " resp.code" + resp.code);

                        postEventToWXAuth(ConstEvent.WXABOUT.GET_CODE_FAIL,"");
                        break;
                }
            }
        }

    }


    private void postEventToWXAuth(int tag,String params) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setNotifyTag(tag);
        eventEntity.setNotifyMsg(params);
        EventBus.getDefault().post(eventEntity);

    }


}