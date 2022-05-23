package com.xmairtravel.core.server.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.manager.entity.dto.ReCustomerDTO;
import com.xmairtravel.core.server.manager.entity.vo.in.QueryCustomerRequest;
import com.xmairtravel.core.server.manager.entity.vo.out.QueryCustomerResponse;
import com.xmairtravel.core.server.manager.service.ICustomerService;
import com.xmairtravel.core.server.wechat.dao.master.ReCustomerDao;
import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.bo.ReCustomerBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ReCustomerDao customerDao;
    @Override
    public Result<QueryCustomerResponse> getCustomerList(QueryCustomerRequest queryCustomerRequest) {
        PageHelper.startPage(queryCustomerRequest.getPageNum(),queryCustomerRequest.getPageSize());
        List<ReCustomerDTO> reCustomerDTOS = customerDao.selectWithBusinessName(queryCustomerRequest);
        return  Result.success(
                new QueryCustomerResponse(new PageInfo(reCustomerDTOS)));
    }
}
