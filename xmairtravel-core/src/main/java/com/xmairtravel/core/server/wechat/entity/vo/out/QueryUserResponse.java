package com.xmairtravel.core.server.wechat.entity.vo.out;

import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.wechat.entity.Pager;
import com.xmairtravel.core.server.wechat.entity.bo.ReUserBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserResponse {
    Pager<ReUserBO> userResponse;

    public QueryUserResponse(PageInfo pageInfo) {
        this.userResponse = new Pager<>(pageInfo);
    }
}
