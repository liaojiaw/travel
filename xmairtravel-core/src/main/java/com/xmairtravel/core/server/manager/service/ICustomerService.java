package com.xmairtravel.core.server.manager.service;

import com.xmairtravel.core.server.manager.entity.vo.in.QueryCustomerRequest;
import com.xmairtravel.core.server.manager.entity.vo.out.QueryCustomerResponse;
import com.xmairtravel.core.server.wechat.entity.Result;

public interface ICustomerService {
    Result<QueryCustomerResponse> getCustomerList(QueryCustomerRequest queryCustomerRequest);
}
