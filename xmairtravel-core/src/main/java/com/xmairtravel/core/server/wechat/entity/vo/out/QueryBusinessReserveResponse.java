package com.xmairtravel.core.server.wechat.entity.vo.out;

import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.wechat.entity.Pager;
import com.xmairtravel.core.server.wechat.entity.dto.ReBusinessReserveDTO;
import com.xmairtravel.core.server.wechat.entity.dto.ReCustomerReserveDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QueryBusinessReserveResponse extends Pager<ReBusinessReserveDTO>{
    public QueryBusinessReserveResponse(PageInfo pageInfo) {
        super(pageInfo);
    }
}
