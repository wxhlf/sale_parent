package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbTypeTemplate;
import com.sales.sellergoods.TypeTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:TypeTemplate的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/typeTemplate")
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;

	@RequestMapping("/findAll")
    public List<TbTypeTemplate> findAll(){
        return typeTemplateService.findAll();
    }

    /***
     * 增加TypeTemplate
     * @param typeTemplate
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbTypeTemplate typeTemplate){
        try {
            typeTemplateService.insert(typeTemplate);
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
            typeTemplateService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param typeTemplate
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbTypeTemplate typeTemplate){
        try {
            typeTemplateService.update(typeTemplate);
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
    public TbTypeTemplate findOne(Long id){
        return typeTemplateService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbTypeTemplate typeTemplate, int pageNo, int pageSize) {
    	PageResult page = typeTemplateService.findPage(typeTemplate, pageNo, pageSize);
        return page;
    }

}
