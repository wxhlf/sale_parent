package com.sales.sellergoods.service;
import com.sales.pojo.TbSeller;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Seller的增删改查
 *
 ****/
public interface SellerService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbSeller> findAll();

    /***
     * 增加Seller
     * @param seller
     * @return
     */
    void insert(TbSeller seller);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(String[] ids);

    /**
     * 修改Seller
     * @param seller
     * @return
     */
    void update(TbSeller seller);

    /**
     * 根据状态值和sellerId做商家的状态修改
     * @param sellerId
     * @param status
     */
    public void updateStatus(String sellerId, String status);
    /***
     * 根据ID查询Seller
     * @param id
     * @return
     */
   TbSeller findOne(String id);

    /***
     * 查询所有Seller
     * @return
     */
    PageResult findPage(TbSeller seller, int PageNo, int PageSize);

}
