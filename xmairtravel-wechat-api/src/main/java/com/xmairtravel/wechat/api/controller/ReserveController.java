package com.xmairtravel.wechat.api.controller;

import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.vo.in.OrderShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryBusinessReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryCustomerReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryBusinessReserveResponse;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryCustomerReserveResponse;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryReserveResponse;
import com.xmairtravel.core.server.wechat.service.IReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "功能类-接驳车预定",description = "接驳车预定")
@RequestMapping(value = "/wx/reserve")
public class ReserveController {

    @Autowired
    IReserveService reserveService;

    @PostMapping("order")
    @ApiOperation(value = "预定接驳车", httpMethod = "POST", notes = "预定接驳车", produces = "application/json")
    public Result orderShuttleBus(@RequestBody OrderShuttleBusRequest orderShuttleBusRequest){
        return reserveService.orderShuttleBus(orderShuttleBusRequest);
    }

    @GetMapping("cancel")
    @ApiImplicitParam(paramType = "query", name = "reserveId", required = true, value = "主键ID", dataType = "String")
    @ApiOperation(value = "取消预定", httpMethod = "GET", notes = "取消预定", produces = "application/json")
    public Result cancelOrder(String reserveId){
        return reserveService.cancelOrder(reserveId);
    }


    @PostMapping("/customer/list")
    @ApiOperation(value = "客户查询预定接驳车列表", httpMethod = "POST", notes = "客户查询预定接驳车列表", produces = "application/json")
    public Result<QueryCustomerReserveResponse> getCustomerReserveList(
            @RequestBody QueryCustomerReserveRequest queryCustomerReserveRequest){
        return reserveService.getCustomerReserveList(queryCustomerReserveRequest);
    }

    @PostMapping("/business/list")
    @ApiOperation(value = "业务人员查询预定接驳车列表", httpMethod = "POST", notes = "业务人员查询预定接驳车列表", produces = "application/json")
    public Result<QueryBusinessReserveResponse> getBusinessReserveList(
            @RequestBody QueryBusinessReserveRequest queryBusinessReserveRequest){
        return reserveService.getBusinessReserveList(queryBusinessReserveRequest);
    }

    @PostMapping("/list")
    @ApiOperation(value = "客户查询预定接驳车列表", httpMethod = "POST", notes = "查询预定接驳车列表", produces = "application/json")
    public Result<QueryReserveResponse> getReserveList(@RequestBody QueryReserveRequest queryReserveRequest){
        return reserveService.getReserveList(queryReserveRequest);
    }
}
