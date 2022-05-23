package com.xmairtravel.core.server.manager.entity.vo.out;

import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.manager.entity.dto.ReCustomerDTO;
import com.xmairtravel.core.server.wechat.entity.Pager;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QueryCustomerResponse extends Pager<ReCustomerDTO>{

    public QueryCustomerResponse(PageInfo pageInfo) {
        super(pageInfo);
    }
}
