package com.xmairtravel.core.server.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.xmairtravel.core.server.manager.entity.bo.SysLogininfor;
import com.xmairtravel.core.server.manager.mapper.SysLogininforMapper;
import com.xmairtravel.core.server.manager.service.ISysLogininforService;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public ResultBean deleteLogininforByIds(Long[] infoIds) {
        try{
            logininforMapper.deleteLogininforByIds(infoIds);
        }catch (Exception e){
            return ResultBean.error("删除失败");
        }
        return ResultBean.success();
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public ResultBean cleanLogininfor() {
        try{
            logininforMapper.cleanLogininfor();
        }catch (Exception e){
            return ResultBean.error("清除失败");
        }
        return ResultBean.success();
    }
}
