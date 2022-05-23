package com.xmairtravel.core.server.wechat.service;

import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.vo.in.OrderShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryBusinessReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryCustomerReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryBusinessReserveResponse;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryCustomerReserveResponse;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryReserveResponse;

public interface IReserveService {
    Result<Boolean> orderShuttleBus(OrderShuttleBusRequest orderShuttleBusRequest);

    Result<QueryReserveResponse> getReserveList(QueryReserveRequest queryReserveRequest);

    Result cancelOrder(String reserveId);

    Result<QueryCustomerReserveResponse> getCustomerReserveList(QueryCustomerReserveRequest queryCustomerReserveRequest);

    Result<QueryBusinessReserveResponse> getBusinessReserveList(QueryBusinessReserveRequest queryBusinessReserveRequest);
}
