package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbAreas;
import com.sales.sellergoods.AreasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Areas的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/areas")
public class AreasController {

    @Reference
    private AreasService areasService;

	@RequestMapping("/findAll")
    public List<TbAreas> findAll(){
        return areasService.findAll();
    }

    /***
     * 增加Areas
     * @param areas
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbAreas areas){
        try {
            areasService.insert(areas);
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
            areasService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param areas
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbAreas areas){
        try {
            areasService.update(areas);
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
    public TbAreas findOne(Long id){
        return areasService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbAreas areas, int pageNo, int pageSize) {
    	PageResult page = areasService.findPage(areas, pageNo, pageSize);
        return page;
    }

}
