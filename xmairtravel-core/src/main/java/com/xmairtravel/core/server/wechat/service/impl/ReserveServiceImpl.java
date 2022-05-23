package com.xmairtravel.core.server.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.wechat.dao.master.ReCustomerDao;
import com.xmairtravel.core.server.wechat.dao.master.ReReserveDao;
import com.xmairtravel.core.server.wechat.dao.master.ReShuttleBusDao;
import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.bo.ReCustomerBO;
import com.xmairtravel.core.server.wechat.entity.bo.ReReserveBO;
import com.xmairtravel.core.server.wechat.entity.dto.ReBusinessReserveDTO;
import com.xmairtravel.core.server.wechat.entity.dto.ReCustomerReserveDTO;
import com.xmairtravel.core.server.wechat.entity.dto.ReReserveDTO;
import com.xmairtravel.core.server.wechat.entity.enums.ReserveStatusEnum;
import com.xmairtravel.core.server.wechat.entity.vo.in.OrderShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryBusinessReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryCustomerReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryReserveRequest;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryBusinessReserveResponse;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryCustomerReserveResponse;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryReserveResponse;
import com.xmairtravel.core.server.wechat.service.IReserveService;
import com.xmairtravel.core.utils.SecurityUtil;
import com.xmairtravel.core.utils.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

@Service
public class ReserveServiceImpl implements IReserveService {

    @Autowired
    ReReserveDao reReserveDao;

    @Autowired
    ReShuttleBusDao shuttleBusDao;

    @Autowired
    ReCustomerDao customerDao;

    @Override
    public Result<Boolean> orderShuttleBus(OrderShuttleBusRequest orderShuttleBusRequest) {
        ReCustomerBO customerParam = new ReCustomerBO();
        ReReserveBO reReserveBO = new ReReserveBO();
        customerParam.setOpenid(SecurityUtil.getOpenId());
        ReCustomerBO customerBO = customerDao.selectOne(customerParam);
        //如果用户第一次预定  记录用户信息
        if(Objects.isNull(customerBO)){
            customerParam.setCustomerName(orderShuttleBusRequest.getUserName());
            customerParam.setPhoneNum(orderShuttleBusRequest.getMobile());
            customerParam.setSex(orderShuttleBusRequest.getSex());
            customerDao.insertSelective(customerParam);
            reReserveBO.setCustomerId(customerParam.getCustomerId());
        }else{
            customerBO.setCustomerName(orderShuttleBusRequest.getUserName());
            customerBO.setPhoneNum(orderShuttleBusRequest.getMobile());
            customerDao.updateByPrimaryKeySelective(customerBO);
            reReserveBO.setCustomerId(customerBO.getCustomerId());
        }

        //预定接驳车
        reReserveBO.setCreateTime(System.currentTimeMillis());
        reReserveBO.setUpdateTime(System.currentTimeMillis());
        reReserveBO.setStatus(ReserveStatusEnum.ACTIVE.status());
        reReserveBO.setOpenid(SecurityUtil.getOpenId());
        reReserveBO.setFlight(orderShuttleBusRequest.getFlight());
        reReserveBO.setHall(orderShuttleBusRequest.getHall());
        reReserveBO.setRemark(orderShuttleBusRequest.getRemark());
        reReserveBO.setDeparture(orderShuttleBusRequest.getDeparture());
        reReserveBO.setCustomerName(orderShuttleBusRequest.getUserName());
        reReserveBO.setDestination(orderShuttleBusRequest.getDestination());
        reReserveBO.setReserveEndTime(orderShuttleBusRequest.getReserveEndTime());
        reReserveBO.setReserveStartTime(orderShuttleBusRequest.getReserveStartTime());
        reReserveBO.setDepartureCoordinate(orderShuttleBusRequest.getDepartureCoordinate());
        reReserveBO.setDestinationCoordinate(orderShuttleBusRequest.getDestinationCoordinate());

        reReserveDao.insertSelective(reReserveBO);


        return Result.success("预订成功");
    }

    @Override
    public Result<QueryReserveResponse> getReserveList(QueryReserveRequest queryReserveRequest) {
        PageHelper.startPage(queryReserveRequest.getPageNum(),queryReserveRequest.getPageSize());
        Example example = new Example(ReReserveBO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hall", queryReserveRequest.getHall());
        criteria.andEqualTo("openid", queryReserveRequest.getOpenid());
        criteria.andEqualTo("status", queryReserveRequest.getStatus());
        criteria.andEqualTo("customerId", queryReserveRequest.getCustomerId());
        criteria.andLike("customerName",queryReserveRequest.getCustomerName());
        criteria.andGreaterThanOrEqualTo("reserveStartTime", queryReserveRequest.getReserveStartTime());
        criteria.andLessThanOrEqualTo("reserveStartTime", queryReserveRequest.getReserveEndTime());
        List<ReReserveBO> reReserveBOS =  reReserveDao.selectByExample(example);
        return  Result.success(
                new QueryReserveResponse(new PageInfo(ReReserveDTO.copyPorperties(reReserveBOS))));
    }

    @Override
    public Result cancelOrder(String reserveId) {
        if(StringUtils.isEmpty(reserveId)){
            return Result.error();
        }
        ReReserveBO reReserveBO = reReserveDao.selectByPrimaryKey(reserveId);
        if(Objects.isNull(reReserveBO)){
            return Result.error("预定信息不存在");
        }
        reReserveBO.setStatus(ReserveStatusEnum.CANCEL.status());
        reReserveDao.updateByPrimaryKeySelective(reReserveBO);
        return Result.success("取消成功");
    }

    @Override
    public Result<QueryCustomerReserveResponse> getCustomerReserveList(QueryCustomerReserveRequest queryCustomerReserveRequest) {
        PageHelper.startPage(queryCustomerReserveRequest.getPageNum(),queryCustomerReserveRequest.getPageSize());
        Example example = new Example(ReReserveBO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openid", queryCustomerReserveRequest.getOpenid());
        criteria.andEqualTo("status", queryCustomerReserveRequest.getStatus());
        criteria.andGreaterThanOrEqualTo("reserveStartTime", queryCustomerReserveRequest.getReserveStartTime());
        criteria.andLessThanOrEqualTo("reserveStartTime", queryCustomerReserveRequest.getReserveEndTime());
        List<ReReserveBO> reReserveBOS =  reReserveDao.selectByExample(example);
        return  Result.success(
                new QueryCustomerReserveResponse(new PageInfo(ReCustomerReserveDTO.copyPorperties(reReserveBOS))));
    }

    @Override
    public Result<QueryBusinessReserveResponse> getBusinessReserveList(QueryBusinessReserveRequest queryBusinessReserveRequest) {
        PageHelper.startPage(queryBusinessReserveRequest.getPageNum(),queryBusinessReserveRequest.getPageSize());
        Example example = new Example(ReReserveBO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openid", queryBusinessReserveRequest.getOpenid());
        criteria.andEqualTo("status", queryBusinessReserveRequest.getStatus());
        criteria.andGreaterThanOrEqualTo("reserveStartTime", queryBusinessReserveRequest.getReserveStartTime());
        criteria.andLessThanOrEqualTo("reserveStartTime", queryBusinessReserveRequest.getReserveEndTime());
        List<ReReserveBO> reReserveBOS =  reReserveDao.selectByExample(example);
        return  Result.success(
                new QueryBusinessReserveResponse(new PageInfo(ReBusinessReserveDTO.copyPorperties(reReserveBOS))));
    }
}
