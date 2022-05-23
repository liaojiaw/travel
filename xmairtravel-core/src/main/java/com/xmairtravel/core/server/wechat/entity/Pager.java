package com.xmairtravel.core.server.wechat.entity;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author WangJT
 * @date 2021-6-21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager<T> {

    private int pageSize;

    private long totalRecord;

    private int totalPage;

    private int pageNum;

    private List<T> list;

    public Pager(PageInfo<T> pageInfo){
        this.list = pageInfo.getList();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.totalPage = pageInfo.getPages();
        this.totalRecord = pageInfo.getTotal();
    }

}