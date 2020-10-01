package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbAddress;
import com.sales.sellergoods.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Address的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Reference
    private AddressService addressService;

	@RequestMapping("/findAll")
    public List<TbAddress> findAll(){
        return addressService.findAll();
    }

    /***
     * 增加Address
     * @param address
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbAddress address){
        try {
            addressService.insert(address);
            return  new Result(true,"增加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,"增加失败！");
        }
    }


    /***
     * 删除操作
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(Long[] ids){
        try {
            addressService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param address
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbAddress address){
        try {
            addressService.update(address);
            return new Result(true,"修改成功！");
        } catch (Exception e) {
            return  new Result(false,"修改失败！");
        }
    }

    /***
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/findOne")
    public TbAddress findOne(Long id){
        return addressService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbAddress address, int pageNo, int pageSize) {
    	PageResult page = addressService.findPage(address, pageNo, pageSize);
        return page;
    }

}
