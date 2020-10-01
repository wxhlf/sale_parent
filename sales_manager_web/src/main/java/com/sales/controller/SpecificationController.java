package com.sales.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.sales.sellergoods.service.SpecificationService;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbSpecification;
import com.sales.sellergoods.service.SpecificationService;
import entity.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/***
 *
 * @Author:itheima
 * @Description:Specification的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/specification")
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

	@RequestMapping("/findAll")
    public List<TbSpecification> findAll(){
        return specificationService.findAll();
    }

    /***
     * 增加Specification
     * @param specification
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody Specification specification){
        try {
            specificationService.insert(specification);
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
            specificationService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param specification
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody Specification specification){
        try {
            specificationService.update(specification);
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
    public Specification findOne(Long id){
        return specificationService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbSpecification specification, int pageNo, int pageSize) {
    	PageResult page = specificationService.findPage(specification, pageNo, pageSize);
        return page;
    }

    /**
     * 规格列表选项
     */
    @RequestMapping("/selectOptionList")
    public  List<Map> SelectOptionList(){
        return  specificationService.selectOptionList();
    }


}
