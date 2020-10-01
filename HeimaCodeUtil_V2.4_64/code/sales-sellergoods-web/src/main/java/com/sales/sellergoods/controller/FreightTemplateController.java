package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbFreightTemplate;
import com.sales.sellergoods.FreightTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:FreightTemplate的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/freightTemplate")
public class FreightTemplateController {

    @Reference
    private FreightTemplateService freightTemplateService;

	@RequestMapping("/findAll")
    public List<TbFreightTemplate> findAll(){
        return freightTemplateService.findAll();
    }

    /***
     * 增加FreightTemplate
     * @param freightTemplate
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbFreightTemplate freightTemplate){
        try {
            freightTemplateService.insert(freightTemplate);
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
            freightTemplateService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param freightTemplate
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbFreightTemplate freightTemplate){
        try {
            freightTemplateService.update(freightTemplate);
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
    public TbFreightTemplate findOne(Long id){
        return freightTemplateService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbFreightTemplate freightTemplate, int pageNo, int pageSize) {
    	PageResult page = freightTemplateService.findPage(freightTemplate, pageNo, pageSize);
        return page;
    }

}
