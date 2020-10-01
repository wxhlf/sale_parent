package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbCities;
import com.sales.sellergoods.CitiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Cities的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/cities")
public class CitiesController {

    @Reference
    private CitiesService citiesService;

	@RequestMapping("/findAll")
    public List<TbCities> findAll(){
        return citiesService.findAll();
    }

    /***
     * 增加Cities
     * @param cities
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbCities cities){
        try {
            citiesService.insert(cities);
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
            citiesService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param cities
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbCities cities){
        try {
            citiesService.update(cities);
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
    public TbCities findOne(Long id){
        return citiesService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbCities cities, int pageNo, int pageSize) {
    	PageResult page = citiesService.findPage(cities, pageNo, pageSize);
        return page;
    }

}
