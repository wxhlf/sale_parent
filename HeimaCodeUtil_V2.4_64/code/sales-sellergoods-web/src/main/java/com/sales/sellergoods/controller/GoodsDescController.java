package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbGoodsDesc;
import com.sales.sellergoods.GoodsDescService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:GoodsDesc的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/goodsDesc")
public class GoodsDescController {

    @Reference
    private GoodsDescService goodsDescService;

	@RequestMapping("/findAll")
    public List<TbGoodsDesc> findAll(){
        return goodsDescService.findAll();
    }

    /***
     * 增加GoodsDesc
     * @param goodsDesc
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbGoodsDesc goodsDesc){
        try {
            goodsDescService.insert(goodsDesc);
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
            goodsDescService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param goodsDesc
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbGoodsDesc goodsDesc){
        try {
            goodsDescService.update(goodsDesc);
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
    public TbGoodsDesc findOne(Long id){
        return goodsDescService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbGoodsDesc goodsDesc, int pageNo, int pageSize) {
    	PageResult page = goodsDescService.findPage(goodsDesc, pageNo, pageSize);
        return page;
    }

}
