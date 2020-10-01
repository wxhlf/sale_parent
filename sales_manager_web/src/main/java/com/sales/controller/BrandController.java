package com.sales.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.sales.pojo.TbBrand;
import com.sales.sellergoods.service.BrandService;
//import entity.PageResult;
import entity.Result;
import entity.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    BrandService brandService;


    @RequestMapping("/findall")
    public List<TbBrand>  findall(){
        return  brandService.findAll();
    }

    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
       return brandService.findOne(id);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbBrand brand, int pageNo, int pageSize){
        return   brandService.findPage(brand, pageNo, pageSize);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            //更新操作
            brandService.update(brand);
            return new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return    new Result(false,"修改失败");
        }
    }
    @RequestMapping("/insert")
    public Result add(@RequestBody TbBrand brand){
        try {
            //保存操作
            brandService.insert(brand);
            return new Result(true,"保存成功");
        }catch (Exception e){
            e.printStackTrace();
            return    new Result(false,"保存失败");
        }
    }
    @RequestMapping("/del")
    public Result delete(Long[] ids){
        //将ids传给传给条件语句
        //删除,有条件的删除
        try {
            //根据ids删除对应的品牌列表
            brandService.delete(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return    new Result(false,"删除失败");
        }
    }


    /**
     * 获取品牌列表选项
     * @return
     */
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandService.selectOptionList();
    }

}
