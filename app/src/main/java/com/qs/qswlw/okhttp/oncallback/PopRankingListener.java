package com.qs.qswlw.okhttp.oncallback;

import com.qs.qswlw.bean.PopRankingBean;

/**
 * Created by xiaoyu on 2017/12/13.
 */

public interface PopRankingListener extends BaseOnlistener {
    void onSuccess(PopRankingBean popRankingBean);
}
