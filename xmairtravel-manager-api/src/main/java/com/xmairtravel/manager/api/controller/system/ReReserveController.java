package com.xmairtravel.manager.api.controller.system;

import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryReserveResponse;
import com.xmairtravel.core.server.wechat.service.IReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "管理类-接驳车预定",description = "接驳车预定")
@RequestMapping("/system/reserve")
public class ReReserveController {

    @Autowired
    IReserveService reserveService;


    @PostMapping("list")
    @ApiOperation(value = "查询预定接驳车列表", httpMethod = "POST", notes = "查询预定接驳车列表", produces = "application/json")
    public Result<QueryReserveResponse> getReserveList(@RequestBody QueryReserveRequest queryReserveRequest){
        return reserveService.getReserveList(queryReserveRequest);
    }
}
