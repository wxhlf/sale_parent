package com.sales.sellergoods.service.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import com.sales.pojo.TbUser;
import com.sales.sellergoods.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:User的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Reference
    private UserService userService;

	@RequestMapping("/findAll")
    public List<TbUser> findAll(){
        return userService.findAll();
    }

    /***
     * 增加User
     * @param user
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody TbUser user){
        try {
            userService.insert(user);
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
            userService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbUser user){
        try {
            userService.update(user);
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
    public TbUser findOne(Long id){
        return userService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbUser user, int pageNo, int pageSize) {
    	PageResult page = userService.findPage(user, pageNo, pageSize);
        return page;
    }

}
