package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbOrder;
import com.sales.sellergoods.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Order的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Reference
    private OrderService orderService;

	@RequestMapping("/findAll")
    public List<TbOrder> findAll(){
        return orderService.findAll();
    }

    /***
     * 增加Order
     * @param order
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbOrder order){
        try {
            orderService.insert(order);
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
            orderService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param order
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbOrder order){
        try {
            orderService.update(order);
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
    public TbOrder findOne(Long id){
        return orderService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbOrder order, int pageNo, int pageSize) {
    	PageResult page = orderService.findPage(order, pageNo, pageSize);
        return page;
    }

}
