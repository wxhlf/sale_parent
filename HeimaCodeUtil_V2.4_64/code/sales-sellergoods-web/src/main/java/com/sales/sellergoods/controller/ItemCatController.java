package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbItemCat;
import com.sales.sellergoods.ItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:ItemCat的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

	@RequestMapping("/findAll")
    public List<TbItemCat> findAll(){
        return itemCatService.findAll();
    }

    /***
     * 增加ItemCat
     * @param itemCat
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbItemCat itemCat){
        try {
            itemCatService.insert(itemCat);
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
            itemCatService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param itemCat
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbItemCat itemCat){
        try {
            itemCatService.update(itemCat);
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
    public TbItemCat findOne(Long id){
        return itemCatService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbItemCat itemCat, int pageNo, int pageSize) {
    	PageResult page = itemCatService.findPage(itemCat, pageNo, pageSize);
        return page;
    }

}
