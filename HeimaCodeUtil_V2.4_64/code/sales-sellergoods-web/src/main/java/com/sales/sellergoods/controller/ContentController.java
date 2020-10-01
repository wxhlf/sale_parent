package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbContent;
import com.sales.sellergoods.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Content的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/content")
public class ContentController {

    @Reference
    private ContentService contentService;

	@RequestMapping("/findAll")
    public List<TbContent> findAll(){
        return contentService.findAll();
    }

    /***
     * 增加Content
     * @param content
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbContent content){
        try {
            contentService.insert(content);
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
            contentService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param content
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbContent content){
        try {
            contentService.update(content);
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
    public TbContent findOne(Long id){
        return contentService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbContent content, int pageNo, int pageSize) {
    	PageResult page = contentService.findPage(content, pageNo, pageSize);
        return page;
    }

}
