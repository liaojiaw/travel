package com.xmairtravel.core.server.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.xmairtravel.core.server.manager.entity.bo.SysOperLog;
import com.xmairtravel.core.server.manager.mapper.SysOperLogMapper;
import com.xmairtravel.core.server.manager.service.ISysOperLogService;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService
{
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog)
    {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operLogMapper.selectOperLogList(operLog);

    }

    /**
     * 批量删除系统操作日志
     * 
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public ResultBean deleteOperLogByIds(Long[] operIds) {
        try{
            operLogMapper.deleteOperLogByIds(operIds);
        }catch (Exception e){
            return ResultBean.error("删除失败");
        }
        return ResultBean.success();
    }

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public ResultBean cleanOperLog() {
        try{
            operLogMapper.cleanOperLog();
        }catch (Exception e){
            return ResultBean.error("清除失败");
        }
        return ResultBean.success();
    }
}
