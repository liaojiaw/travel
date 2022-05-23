package com.xmairtravel.core.server.wechat.entity.vo.out;

import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.wechat.entity.Pager;
import com.xmairtravel.core.server.wechat.entity.bo.ReReserveBO;
import com.xmairtravel.core.server.wechat.entity.dto.ReReserveDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QueryReserveResponse extends Pager<ReReserveDTO>{
    public QueryReserveResponse(PageInfo pageInfo) {
        super(pageInfo);
    }
}
