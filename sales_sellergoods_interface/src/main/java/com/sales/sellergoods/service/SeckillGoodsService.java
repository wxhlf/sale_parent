package com.sales.sellergoods.service;
import com.sales.pojo.TbSeckillGoods;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SeckillGoods的增删改查
 *
 ****/
public interface SeckillGoodsService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbSeckillGoods> findAll();

    /***
     * 增加SeckillGoods
     * @param seckillGoods
     * @return
     */
    void insert(TbSeckillGoods seckillGoods);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改SeckillGoods
     * @param seckillGoods
     * @return
     */
    void update(TbSeckillGoods seckillGoods);

    /***
     * 根据ID查询SeckillGoods
     * @param id
     * @return
     */
   TbSeckillGoods findOne(Long id);

    /***
     * 查询所有SeckillGoods
     * @return
     */
    PageResult findPage(TbSeckillGoods seckillGoods, int PageNo, int PageSize);

}
