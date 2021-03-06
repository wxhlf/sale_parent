package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbGoods;
import com.sales.sellergoods.GoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Goods的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

	@RequestMapping("/findAll")
    public List<TbGoods> findAll(){
        return goodsService.findAll();
    }

    /***
     * 增加Goods
     * @param goods
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbGoods goods){
        try {
            goodsService.insert(goods);
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
            goodsService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param goods
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbGoods goods){
        try {
            goodsService.update(goods);
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
    public TbGoods findOne(Long id){
        return goodsService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbGoods goods, int pageNo, int pageSize) {
    	PageResult page = goodsService.findPage(goods, pageNo, pageSize);
        return page;
    }

}
