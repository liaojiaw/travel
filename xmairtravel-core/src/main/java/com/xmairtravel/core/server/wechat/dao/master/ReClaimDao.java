package com.xmairtravel.core.server.wechat.dao.master;

import com.xmairtravel.core.server.wechat.entity.bo.ReClaimBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.awt.print.Pageable;
import java.util.List;

/**
 * (ReClaimBO)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-24 16:07:20
 */
public interface ReClaimDao extends Mapper<ReClaimBO> {

    /**
     * 通过ID查询单条数据
     *
     * @param claimId 主键
     * @return 实例对象
     */
    ReClaimBO queryById(Integer claimId);

    /**
     * 查询指定行数据
     *
     * @param reClaim 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ReClaimBO> queryAllByLimit(ReClaimBO reClaim, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param reClaim 查询条件
     * @return 总行数
     */
    long count(ReClaimBO reClaim);

    /**
     * 新增数据
     *
     * @param reClaim 实例对象
     * @return 影响行数
     */
    int insert(ReClaimBO reClaim);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReClaimBO> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReClaimBO> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReClaimBO> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReClaimBO> entities);

    /**
     * 修改数据
     *
     * @param reClaim 实例对象
     * @return 影响行数
     */
    int update(ReClaimBO reClaim);

    /**
     * 通过主键删除数据
     *
     * @param claimId 主键
     * @return 影响行数
     */
    int deleteById(Integer claimId);

}

