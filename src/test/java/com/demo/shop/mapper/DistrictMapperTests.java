package com.demo.shop.mapper;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("120100");
        list.stream().forEach(System.out :: println);
    }
    @Test
    public void findNameByCode(){
        String name = districtMapper.findNameByCode("410000");
        System.out.println(name);
    }
}
