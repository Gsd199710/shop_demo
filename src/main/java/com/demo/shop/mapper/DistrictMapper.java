package com.demo.shop.mapper;

import com.demo.shop.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 区域信息的列表
     */
    List<District> findByParent(String parent);

    /***
     * 根据当前code获取省市区名称
     * @param code 地址代号
     * @return 目标省市区名称
     */
    String findNameByCode(String code);
}
