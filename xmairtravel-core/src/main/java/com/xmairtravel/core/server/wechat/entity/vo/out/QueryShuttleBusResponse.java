package com.xmairtravel.core.server.wechat.entity.vo.out;

import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.wechat.entity.Pager;
import com.xmairtravel.core.server.wechat.entity.dto.ReShuttleBusDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryShuttleBusResponse {
    Pager<ReShuttleBusDTO> shuttleBusResponse;

    public QueryShuttleBusResponse(PageInfo pageInfo) {
        this.shuttleBusResponse = new Pager<>(pageInfo);
    }
}
