package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbSeckillGoods;
import com.sales.sellergoods.SeckillGoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SeckillGoods的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/seckillGoods")
public class SeckillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;

	@RequestMapping("/findAll")
    public List<TbSeckillGoods> findAll(){
        return seckillGoodsService.findAll();
    }

    /***
     * 增加SeckillGoods
     * @param seckillGoods
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbSeckillGoods seckillGoods){
        try {
            seckillGoodsService.insert(seckillGoods);
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
            seckillGoodsService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param seckillGoods
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbSeckillGoods seckillGoods){
        try {
            seckillGoodsService.update(seckillGoods);
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
    public TbSeckillGoods findOne(Long id){
        return seckillGoodsService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbSeckillGoods seckillGoods, int pageNo, int pageSize) {
    	PageResult page = seckillGoodsService.findPage(seckillGoods, pageNo, pageSize);
        return page;
    }

}
