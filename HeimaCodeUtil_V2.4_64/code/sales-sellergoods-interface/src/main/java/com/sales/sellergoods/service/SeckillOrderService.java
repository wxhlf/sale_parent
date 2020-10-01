package com.sales.sellergoods.service.service;
import com.sales.pojo.TbSeckillOrder;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SeckillOrder的增删改查
 *
 ****/
public interface SeckillOrderService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbSeckillOrder> findAll();

    /***
     * 增加SeckillOrder
     * @param seckillOrder
     * @return
     */
    void insert(TbSeckillOrder seckillOrder);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改SeckillOrder
     * @param seckillOrder
     * @return
     */
    void update(TbSeckillOrder seckillOrder);

    /***
     * 根据ID查询SeckillOrder
     * @param id
     * @return
     */
   TbSeckillOrder findOne(Long id);

    /***
     * 查询所有SeckillOrder
     * @return
     */
    PageResult findPage(TbSeckillOrder seckillOrder, int PageNo, int PageSize);

}
