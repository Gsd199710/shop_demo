package com.demo.shop.mapper;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(1001);
        address.setPhone("17477773298");
        address.setAddress("上海市");
        address.setName("大C");
        addressMapper.insert(address);
    }
    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(1001);
        System.out.println("该用户地址个数为："+count);
    }
    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(10);
        list.forEach(System.out::println);
    }
    @Test
    public void findByAid(){
        Address address = addressMapper.findByAid(14);
        System.out.println(address);
    }
    @Test
    public void updateNonDefault(){
        Integer rows = addressMapper.updateNonDefault(10);
        System.out.println(rows);
    }
    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(14, "王菲", new Date());
    }
    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(12);
    }
    @Test
    public void findLastModified(){
        Address lastModified = addressMapper.findLastModified(10);
        System.out.println(lastModified);
    }
}
