package com.sales.sellergoods.service;
import com.sales.pojo.TbOrderItem;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:OrderItem的增删改查
 *
 ****/
public interface OrderItemService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbOrderItem> findAll();

    /***
     * 增加OrderItem
     * @param orderItem
     * @return
     */
    void insert(TbOrderItem orderItem);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改OrderItem
     * @param orderItem
     * @return
     */
    void update(TbOrderItem orderItem);

    /***
     * 根据ID查询OrderItem
     * @param id
     * @return
     */
   TbOrderItem findOne(Long id);

    /***
     * 查询所有OrderItem
     * @return
     */
    PageResult findPage(TbOrderItem orderItem, int PageNo, int PageSize);

}
