package com.xmairtravel.core.server.wechat.entity.bo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "od_goods")
public class GoodsBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodsId;

    private String goodsName;

    private String goodsDepict;

    private String goodsImgUrl;

    private Integer goodsSales;

    private Double goodsSocre;

    private String goodsNo;

    private Integer categoryId;

    private Integer brandId;
}
