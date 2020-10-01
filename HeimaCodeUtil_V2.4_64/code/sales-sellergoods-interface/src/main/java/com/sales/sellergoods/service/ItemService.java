package com.sales.sellergoods.service.service;
import com.sales.pojo.TbItem;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Item的增删改查
 *
 ****/
public interface ItemService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbItem> findAll();

    /***
     * 增加Item
     * @param item
     * @return
     */
    void insert(TbItem item);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Item
     * @param item
     * @return
     */
    void update(TbItem item);

    /***
     * 根据ID查询Item
     * @param id
     * @return
     */
   TbItem findOne(Long id);

    /***
     * 查询所有Item
     * @return
     */
    PageResult findPage(TbItem item, int PageNo, int PageSize);

}
