package com.xmairtravel.core.server.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmairtravel.core.server.wechat.dao.master.ReShuttleBusDao;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.server.wechat.entity.bo.ReShuttleBusBO;
import com.xmairtravel.core.server.wechat.entity.dto.ReShuttleBusDTO;
import com.xmairtravel.core.server.wechat.entity.enums.ShuttleBusStatusEnum;
import com.xmairtravel.core.server.wechat.entity.vo.in.QueryShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.in.ShuttleBusRequest;
import com.xmairtravel.core.server.wechat.entity.vo.out.QueryShuttleBusResponse;
import com.xmairtravel.core.server.wechat.service.IShuttleBusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ShuttleBusServiceImpl implements IShuttleBusService {

    @Autowired
    ReShuttleBusDao shuttleBusDao;

    @Override
    public ResultBean<QueryShuttleBusResponse> getShuttleBusList(QueryShuttleBusRequest queryShuttleBusRequest) {
        PageHelper.startPage(queryShuttleBusRequest.getPageNum(),queryShuttleBusRequest.getPageSize());
        List<ReShuttleBusBO> reShuttleBusBOS = shuttleBusDao.selectAll();
        return  ResultBean.success(
                new QueryShuttleBusResponse(new PageInfo(ReShuttleBusDTO.copyPorperties(reShuttleBusBOS))));
    }

    @Override
    public ResultBean<Boolean> insertShuttleBusList(ShuttleBusRequest shuttleBusRequest) {
        shuttleBusDao.insertSelective(new ReShuttleBusBO(shuttleBusRequest));
        return ResultBean.success(true);
    }

    @Override
    public ResultBean<Boolean> updateShuttleBusList(ShuttleBusRequest shuttleBusRequest) {
        Objects.requireNonNull(shuttleBusRequest.getShuttleBusId());
        shuttleBusDao.updateByPrimaryKeySelective(new ReShuttleBusBO(shuttleBusRequest));
        return ResultBean.success(true);
    }

    @Override
    public ResultBean<Boolean> disableShuttleBusList(Integer shuttleBusId) {
        Objects.requireNonNull(shuttleBusId);
        ShuttleBusRequest shuttleBusRequest = new ShuttleBusRequest();
        shuttleBusRequest.setShuttleBusId(shuttleBusId);
        shuttleBusRequest.setStatus(ShuttleBusStatusEnum.PAUSE.status());
        shuttleBusDao.updateByPrimaryKeySelective(new ReShuttleBusBO(shuttleBusRequest));
        return ResultBean.success(true);
    }

    @Override
    public ResultBean<Boolean> enableShuttleBusList(Integer shuttleBusId) {
        Objects.requireNonNull(shuttleBusId);
        ShuttleBusRequest shuttleBusRequest = new ShuttleBusRequest();
        shuttleBusRequest.setShuttleBusId(shuttleBusId);
        shuttleBusRequest.setStatus(ShuttleBusStatusEnum.LEISURE.status());
        shuttleBusDao.updateByPrimaryKeySelective(new ReShuttleBusBO(shuttleBusRequest));
        return ResultBean.success(true);
    }
}
