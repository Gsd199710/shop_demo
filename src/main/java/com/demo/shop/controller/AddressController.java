package com.demo.shop.controller;

import com.demo.shop.entity.Address;
import com.demo.shop.service.IAddressService;
import com.demo.shop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<Address>> findByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.findByUid(uid);
        return new JsonResult<>(OK,data);
    }
}
