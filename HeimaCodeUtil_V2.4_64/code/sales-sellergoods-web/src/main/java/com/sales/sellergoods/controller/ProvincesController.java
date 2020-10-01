package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbProvinces;
import com.sales.sellergoods.ProvincesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Provinces的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/provinces")
public class ProvincesController {

    @Reference
    private ProvincesService provincesService;

	@RequestMapping("/findAll")
    public List<TbProvinces> findAll(){
        return provincesService.findAll();
    }

    /***
     * 增加Provinces
     * @param provinces
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbProvinces provinces){
        try {
            provincesService.insert(provinces);
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
            provincesService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param provinces
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbProvinces provinces){
        try {
            provincesService.update(provinces);
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
    public TbProvinces findOne(Long id){
        return provincesService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbProvinces provinces, int pageNo, int pageSize) {
    	PageResult page = provincesService.findPage(provinces, pageNo, pageSize);
        return page;
    }

}
