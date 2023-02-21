package com.demo.shop.service;

import com.demo.shop.entity.District;

import java.util.List;

public interface IDistrictService {
    /***
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 区域信息列表
     */
    List<District > getByParent(String parent);

    String getNameByCode(String code);

}
