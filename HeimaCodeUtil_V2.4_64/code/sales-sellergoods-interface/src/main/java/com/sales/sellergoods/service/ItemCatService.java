package com.sales.sellergoods.service.service;
import com.sales.pojo.TbItemCat;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:ItemCat的增删改查
 *
 ****/
public interface ItemCatService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbItemCat> findAll();

    /***
     * 增加ItemCat
     * @param itemCat
     * @return
     */
    void insert(TbItemCat itemCat);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改ItemCat
     * @param itemCat
     * @return
     */
    void update(TbItemCat itemCat);

    /***
     * 根据ID查询ItemCat
     * @param id
     * @return
     */
   TbItemCat findOne(Long id);

    /***
     * 查询所有ItemCat
     * @return
     */
    PageResult findPage(TbItemCat itemCat, int PageNo, int PageSize);

}
