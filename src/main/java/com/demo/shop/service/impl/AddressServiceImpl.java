package com.demo.shop.service.impl;

import com.demo.shop.controller.ex.AddressCountLimitedEXception;
import com.demo.shop.controller.ex.DeleteException;
import com.demo.shop.entity.Address;
import com.demo.shop.mapper.AddressMapper;
import com.demo.shop.service.IAddressService;
import com.demo.shop.service.IDistrictService;
import com.demo.shop.service.ex.AccessDeniedException;
import com.demo.shop.service.ex.AddressNotFoundException;
import com.demo.shop.service.ex.InsertException;
import com.demo.shop.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/***
 * 收货地址业务层实现类
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    //添加用户的收货地址的业务层依赖于IDistrictService的业务层接口
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer addressMaxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //添加收货地址前先查询用户添加过地址数量
        Integer count = addressMapper.countByUid(uid);
        if (count > addressMaxCount) {
            throw new AddressCountLimitedEXception("该用户收货地址已达上限！");
        }
        //将address对象中的省市区数据补全
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //补全用户uid、isDefault、以及4项日志属性
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0 ;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("用户收货地址添加过程出现未知错误！");
        }
    }

    @Override
    public List<Address> findByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        list.stream().map(e->{
//            e.setAid(null);
//            e.setUid(null);
            e.setProvinceCode(null);
            e.setCityCode(null);
            e.setAreaCode(null);
            e.setTel(null);
            e.setIsDefault(null);
            e.setCreatedTime(null);
            e.setCreatedUser(null);
            e.setModifiedTime(null);
            e.setModifiedUser(null);
            return e;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public void setDefault(Integer uid, Integer aid, String username) {
        Address result = addressMapper.findByAid(aid);
        //判断用户地址是否存在
        if (result == null) {
            throw new AddressNotFoundException("该用户地址不存在！");
        }
        //判断该数据是否为当前用户所属
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！");
        }
        //将用户所有地址修改为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("数据更新出现异常！");
        }
        //将指定aid的用户地址修改为默认
        Integer update = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (update != 1) {
            throw new UpdateException("数据更新出现异常!");
        }
    }

    @Override
    public void delete(Integer uid, Integer aid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("该地址不存在！");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！");
        }
        //查询用户地址条数并进行删除操作，如果数据条数大于一条且删掉了默认地址则就要修改新的默认地址
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            return;
        }
        //删除
        Integer delete = addressMapper.deleteByAid(aid);
        if (delete != 1) {
            throw new DeleteException("删除出现未知错误");
        }
        //判断删除的是否是默认地址
        Integer isDefault = result.getIsDefault();
        if (count != 1 && isDefault==1) {
            Address address = addressMapper.findLastModified(uid);
            Integer updateDefault = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
            if (updateDefault != 1) {
                throw new UpdateException("修改数据出现未知异常！");
            }
        }
    }
}
