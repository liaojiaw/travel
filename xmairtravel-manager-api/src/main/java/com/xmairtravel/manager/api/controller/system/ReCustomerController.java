package com.xmairtravel.manager.api.controller.system;

import com.xmairtravel.core.server.manager.entity.vo.in.QueryCustomerRequest;
import com.xmairtravel.core.server.manager.entity.vo.out.QueryCustomerResponse;
import com.xmairtravel.core.server.manager.service.ICustomerService;
import com.xmairtravel.core.server.wechat.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "管理类-查询客户信息",description = "接驳车预定")
@RequestMapping("/system/customer")
public class ReCustomerController {
    @Autowired
    ICustomerService customerService;


    @PostMapping("list")
    @ApiOperation(value = "查询客户列表", httpMethod = "POST", notes = "查询客户列表", produces = "application/json")
    public Result<QueryCustomerResponse> getCustomerList(@RequestBody QueryCustomerRequest queryCustomerRequest){
        return customerService.getCustomerList(queryCustomerRequest);
    }
}
