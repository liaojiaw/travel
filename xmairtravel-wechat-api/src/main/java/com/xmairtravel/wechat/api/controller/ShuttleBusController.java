package com.xmairtravel.wechat.api.controller;

import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.server.wechat.entity.vo.in.ShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryShuttleBusResponse;
import com.xmairtravel.core.server.wechat.service.IShuttleBusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "管理类-接驳车管理",description = "接驳车管理")
@RequestMapping(value = "/shuttle/bus")
public class ShuttleBusController {

    @Autowired
    IShuttleBusService shuttleBusService;

    @PostMapping("list")
    @ApiOperation(value = "查询接驳车列表", httpMethod = "POST", notes = "查询接驳车列表", produces = "application/json")
    public ResultBean<QueryShuttleBusResponse> getShuttleBusList(@RequestBody QueryShuttleBusRequest queryShuttleBusRequest){
        return shuttleBusService.getShuttleBusList(queryShuttleBusRequest);
    }

    @PostMapping("insert")
    @ApiOperation(value = "新增接驳车", httpMethod = "POST", notes = "新增接驳车", produces = "application/json")
    public ResultBean<Boolean> insertShuttleBusList(@RequestBody ShuttleBusRequest shuttleBusRequest){
        return shuttleBusService.insertShuttleBusList(shuttleBusRequest);
    }

    @PostMapping("update")
    @ApiOperation(value = "编辑接驳车", httpMethod = "POST", notes = "编辑接驳车", produces = "application/json")
    public ResultBean<Boolean> updateShuttleBusList(@RequestBody ShuttleBusRequest shuttleBusRequest){
        return shuttleBusService.updateShuttleBusList(shuttleBusRequest);
    }

    @GetMapping("disable")
    @ApiImplicitParam(paramType = "query", name = "shuttleBusId", required = true, value = "主键", dataType = "Integer")
    @ApiOperation(value = "停用接驳车", httpMethod = "GET", notes = "停用接驳车")
    public ResultBean<Boolean> disableShuttleBusList(Integer shuttleBusId){
        return shuttleBusService.disableShuttleBusList(shuttleBusId);
    }

    @GetMapping("enable")
    @ApiImplicitParam(paramType = "query", name = "shuttleBusId", required = true, value = "主键", dataType = "Integer")
    @ApiOperation(value = "启用接驳车", httpMethod = "GET", notes = "启用接驳车")
    public ResultBean<Boolean> enableShuttleBusList(Integer shuttleBusId){
        return shuttleBusService.enableShuttleBusList(shuttleBusId);
    }
}
