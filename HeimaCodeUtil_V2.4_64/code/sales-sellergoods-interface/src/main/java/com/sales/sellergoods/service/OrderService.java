package com.sales.sellergoods.service.service;
import com.sales.pojo.TbOrder;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Order的增删改查
 *
 ****/
public interface OrderService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbOrder> findAll();

    /***
     * 增加Order
     * @param order
     * @return
     */
    void insert(TbOrder order);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Order
     * @param order
     * @return
     */
    void update(TbOrder order);

    /***
     * 根据ID查询Order
     * @param id
     * @return
     */
   TbOrder findOne(Long id);

    /***
     * 查询所有Order
     * @return
     */
    PageResult findPage(TbOrder order, int PageNo, int PageSize);

}
