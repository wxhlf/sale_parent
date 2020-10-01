package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbPayLog;
import com.sales.sellergoods.PayLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:PayLog的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/payLog")
public class PayLogController {

    @Reference
    private PayLogService payLogService;

	@RequestMapping("/findAll")
    public List<TbPayLog> findAll(){
        return payLogService.findAll();
    }

    /***
     * 增加PayLog
     * @param payLog
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbPayLog payLog){
        try {
            payLogService.insert(payLog);
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
            payLogService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param payLog
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbPayLog payLog){
        try {
            payLogService.update(payLog);
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
    public TbPayLog findOne(Long id){
        return payLogService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbPayLog payLog, int pageNo, int pageSize) {
    	PageResult page = payLogService.findPage(payLog, pageNo, pageSize);
        return page;
    }

}
