package com.sales.shop.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sales.pojo.TbSeller;
import com.sales.sellergoods.service.SellerService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Seller的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

	@RequestMapping("/findAll")
    public List<TbSeller> findAll(){
        return sellerService.findAll();
    }

    /***
     * 增加Seller
     * @param seller
     * @return
     */
    @RequestMapping(value = "/add")
    public Result insert(@RequestBody TbSeller seller){
        try {
            sellerService.insert(seller);
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
    public Result delete(String[] ids){
        try {
            sellerService.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param seller
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody TbSeller seller){
        try {
//            sellerService.update(seller);
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
    public TbSeller findOne(String id){
        return sellerService.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbSeller seller, int pageNo, int pageSize) {
    	PageResult page = sellerService.findPage(seller, pageNo, pageSize);
        return page;
    }

    @RequestMapping("/updateStatus")
    public Result updateStatus(String sellerId, String status) {
        try {
            sellerService.updateStatus(sellerId,status);
            return new Result(true,"修改成功！");
        } catch (Exception e) {
            return  new Result(false,"修改失败！");
        }
    }

}
