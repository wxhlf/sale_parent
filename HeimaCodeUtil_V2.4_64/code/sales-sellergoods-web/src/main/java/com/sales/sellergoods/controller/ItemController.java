package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbItem;
import com.sales.sellergoods.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Item的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Reference
    private ItemService itemService;

	@RequestMapping("/findAll")
    public List<TbItem> findAll(){
        return itemService.findAll();
    }

    /***
     * 增加Item
     * @param item
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbItem item){
        try {
            itemService.insert(item);
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
            itemService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param item
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbItem item){
        try {
            itemService.update(item);
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
    public TbItem findOne(Long id){
        return itemService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbItem item, int pageNo, int pageSize) {
    	PageResult page = itemService.findPage(item, pageNo, pageSize);
        return page;
    }

}
