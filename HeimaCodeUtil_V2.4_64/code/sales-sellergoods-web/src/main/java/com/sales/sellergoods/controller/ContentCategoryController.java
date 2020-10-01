package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbContentCategory;
import com.sales.sellergoods.ContentCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:ContentCategory的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/contentCategory")
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

	@RequestMapping("/findAll")
    public List<TbContentCategory> findAll(){
        return contentCategoryService.findAll();
    }

    /***
     * 增加ContentCategory
     * @param contentCategory
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbContentCategory contentCategory){
        try {
            contentCategoryService.insert(contentCategory);
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
            contentCategoryService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param contentCategory
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbContentCategory contentCategory){
        try {
            contentCategoryService.update(contentCategory);
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
    public TbContentCategory findOne(Long id){
        return contentCategoryService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbContentCategory contentCategory, int pageNo, int pageSize) {
    	PageResult page = contentCategoryService.findPage(contentCategory, pageNo, pageSize);
        return page;
    }

}
