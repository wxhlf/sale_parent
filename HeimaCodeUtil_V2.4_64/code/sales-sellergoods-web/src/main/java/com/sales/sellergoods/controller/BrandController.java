package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbBrand;
import com.sales.sellergoods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Brand的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

	@RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    /***
     * 增加Brand
     * @param brand
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbBrand brand){
        try {
            brandService.insert(brand);
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
            brandService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param brand
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
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
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbBrand brand, int pageNo, int pageSize) {
    	PageResult page = brandService.findPage(brand, pageNo, pageSize);
        return page;
    }

}
