package com.xmairtravel.core.server.manager.service;


import com.xmairtravel.core.server.manager.entity.bo.SysLogininfor;
import com.xmairtravel.core.server.wechat.entity.ResultBean;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 * 
 * @author ruoyi
 */
public interface ISysLogininforService
{
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    public ResultBean deleteLogininforByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     */
    public ResultBean cleanLogininfor();
}
