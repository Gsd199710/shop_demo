package com.demo.shop.service;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.User;
import com.demo.shop.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;
    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setAddress("北京市海淀区");
        address.setName("老板");
        address.setPhone("132356677");
        addressService.addNewAddress(1002,"xiaoming",address);
    }
    @Test
    public void setDefault(){
        addressService.setDefault(10,16,"马冬梅");
    }
    @Test
    public void delete(){
        addressService.delete(10,24,"管理员");
    }
    @Test
    public void getIsDefault(){
        int isDefault = addressService.getIsDefault(19);
        System.out.println(isDefault);
    }
}
