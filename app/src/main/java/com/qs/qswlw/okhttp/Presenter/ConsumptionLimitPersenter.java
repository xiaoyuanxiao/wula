package com.qs.qswlw.okhttp.Presenter;

import android.util.Log;

import com.qs.qswlw.bean.ConsumptionLimitBean;
import com.qs.qswlw.okhttp.Factory.IBizFactory;
import com.qs.qswlw.okhttp.Iview.IConsumptionLimitView;
import com.qs.qswlw.okhttp.Moudle.IConsumptionLimitBiz;
import com.qs.qswlw.okhttp.oncallback.ConsumptionLimitListener;

/**
 * Created by xiaoyu on 2017/9/25.
 */

public class ConsumptionLimitPersenter {
    IConsumptionLimitBiz iConsumptionLimitBiz;
    IConsumptionLimitView iConsumptionLimitView;

    public ConsumptionLimitPersenter(IConsumptionLimitView iConsumptionLimitView) {
        iConsumptionLimitBiz = IBizFactory.getConsumptionLimitList();
        this.iConsumptionLimitView = iConsumptionLimitView;
    }
    public void getdata(String token,int p){
        iConsumptionLimitBiz.getdata(new ConsumptionLimitListener() {
            @Override
            public void onSuccess(ConsumptionLimitBean consumptionLimitBean) {
                iConsumptionLimitView.setdata(consumptionLimitBean);
            }

            @Override
            public void onTokenFail() {
                iConsumptionLimitView.setTokenFail();
            }

            @Override
            public void onFailure(String code) {
                Log.e("e",code+"");
            }
        },token,p);
    }

}
