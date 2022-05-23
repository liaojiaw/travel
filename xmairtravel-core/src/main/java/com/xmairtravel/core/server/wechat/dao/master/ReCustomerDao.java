package com.xmairtravel.core.server.wechat.dao.master;

import com.xmairtravel.core.server.manager.entity.dto.ReCustomerDTO;
import com.xmairtravel.core.server.manager.entity.vo.in.QueryCustomerRequest;
import com.xmairtravel.core.server.wechat.entity.bo.ReCustomerBO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ReCustomerDao extends Mapper<ReCustomerBO> {
    List<ReCustomerDTO> selectWithBusinessName(QueryCustomerRequest queryCustomerRequest);
}
