package com.xmairtravel.core.server.wechat.service;

import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.server.wechat.entity.vo.in.ShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryShuttleBusResponse;

public interface IShuttleBusService {
    ResultBean<QueryShuttleBusResponse> getShuttleBusList(QueryShuttleBusRequest queryShuttleBusRequest);

    ResultBean<Boolean> insertShuttleBusList(ShuttleBusRequest shuttleBusRequest);

    ResultBean<Boolean> updateShuttleBusList(ShuttleBusRequest shuttleBusRequest);

    ResultBean<Boolean> disableShuttleBusList(Integer shuttleBusId);

    ResultBean<Boolean> enableShuttleBusList(Integer shuttleBusId);
}
