package com.demo.shop.service.impl;

import com.demo.shop.entity.District;
import com.demo.shop.mapper.DistrictMapper;
import com.demo.shop.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);

//        list.forEach(e->e.setId(null));
        list.stream().map(e->{
            e.setId(null);
            e.setParent(null);
            return e;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
