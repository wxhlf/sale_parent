package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbSeckillOrder;
import com.sales.sellergoods.SeckillOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SeckillOrder的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/seckillOrder")
public class SeckillOrderController {

    @Reference
    private SeckillOrderService seckillOrderService;

	@RequestMapping("/findAll")
    public List<TbSeckillOrder> findAll(){
        return seckillOrderService.findAll();
    }

    /***
     * 增加SeckillOrder
     * @param seckillOrder
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbSeckillOrder seckillOrder){
        try {
            seckillOrderService.insert(seckillOrder);
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
            seckillOrderService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param seckillOrder
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbSeckillOrder seckillOrder){
        try {
            seckillOrderService.update(seckillOrder);
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
    public TbSeckillOrder findOne(Long id){
        return seckillOrderService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbSeckillOrder seckillOrder, int pageNo, int pageSize) {
    	PageResult page = seckillOrderService.findPage(seckillOrder, pageNo, pageSize);
        return page;
    }

}
