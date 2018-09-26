package org.huaanwater.work.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.huaanwater.work.constant.ConstEvent;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.EventEntity;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {



    private IWXAPI api;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, ConstLocalData.WX_APPID);
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



        Log.i("wexinzhifu","==================onReq  "+baseResp.getType()+ "code:"+baseResp.errCode+"openId:"+baseResp.openId+"transaction:"+baseResp.transaction+"=========ToString:"+baseResp.toString());






        WXPayEntryActivity.this.finish();

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX && baseResp.errCode == 0) {

            postEventToWeight(ConstEvent.WXABOUT.WX_PAY_SUCCESS_BACK);

        }


    }



    private void postEventToWeight(int tag) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setNotifyTag(tag);

        EventBus.getDefault().post(eventEntity);

    }





}