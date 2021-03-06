package com.qs.qswlw.okhttp.oncallback;

import com.qs.qswlw.bean.ConsumptionRecordBean;
import com.qs.qswlw.bean.MainBean;

/**
 * Created by xiaoyu on 2017/9/11.
 */

public interface ConsumptionRecordListener extends BaseOnlistener {
    void onSuccess(MainBean<ConsumptionRecordBean> consumptionRecordBean);
    void onTokenFail();
}
