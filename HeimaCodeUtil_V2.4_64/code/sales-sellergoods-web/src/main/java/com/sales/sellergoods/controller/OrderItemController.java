package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbOrderItem;
import com.sales.sellergoods.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:OrderItem的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/orderItem")
public class OrderItemController {

    @Reference
    private OrderItemService orderItemService;

	@RequestMapping("/findAll")
    public List<TbOrderItem> findAll(){
        return orderItemService.findAll();
    }

    /***
     * 增加OrderItem
     * @param orderItem
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbOrderItem orderItem){
        try {
            orderItemService.insert(orderItem);
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
            orderItemService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param orderItem
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbOrderItem orderItem){
        try {
            orderItemService.update(orderItem);
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
    public TbOrderItem findOne(Long id){
        return orderItemService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbOrderItem orderItem, int pageNo, int pageSize) {
    	PageResult page = orderItemService.findPage(orderItem, pageNo, pageSize);
        return page;
    }

}
