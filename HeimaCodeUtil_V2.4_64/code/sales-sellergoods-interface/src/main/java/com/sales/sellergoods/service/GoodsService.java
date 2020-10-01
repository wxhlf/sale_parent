package com.sales.sellergoods.service.service;
import com.sales.pojo.TbGoods;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Goods的增删改查
 *
 ****/
public interface GoodsService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbGoods> findAll();

    /***
     * 增加Goods
     * @param goods
     * @return
     */
    void insert(TbGoods goods);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Goods
     * @param goods
     * @return
     */
    void update(TbGoods goods);

    /***
     * 根据ID查询Goods
     * @param id
     * @return
     */
   TbGoods findOne(Long id);

    /***
     * 查询所有Goods
     * @return
     */
    PageResult findPage(TbGoods goods, int PageNo, int PageSize);

}
