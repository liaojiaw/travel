package com.xmairtravel.core.server.wechat.entity.vo.out;

import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.wechat.entity.Pager;
import com.xmairtravel.core.server.wechat.entity.dto.ReCustomerReserveDTO;
import com.xmairtravel.core.server.wechat.entity.dto.ReReserveDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QueryCustomerReserveResponse extends Pager<ReCustomerReserveDTO>{
    public QueryCustomerReserveResponse(PageInfo pageInfo) {
        super(pageInfo);
    }
}
