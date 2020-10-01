package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbSpecificationOption;
import com.sales.sellergoods.SpecificationOptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SpecificationOption的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/specificationOption")
public class SpecificationOptionController {

    @Reference
    private SpecificationOptionService specificationOptionService;

	@RequestMapping("/findAll")
    public List<TbSpecificationOption> findAll(){
        return specificationOptionService.findAll();
    }

    /***
     * 增加SpecificationOption
     * @param specificationOption
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbSpecificationOption specificationOption){
        try {
            specificationOptionService.insert(specificationOption);
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
            specificationOptionService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param specificationOption
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbSpecificationOption specificationOption){
        try {
            specificationOptionService.update(specificationOption);
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
    public TbSpecificationOption findOne(Long id){
        return specificationOptionService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbSpecificationOption specificationOption, int pageNo, int pageSize) {
    	PageResult page = specificationOptionService.findPage(specificationOption, pageNo, pageSize);
        return page;
    }

}
