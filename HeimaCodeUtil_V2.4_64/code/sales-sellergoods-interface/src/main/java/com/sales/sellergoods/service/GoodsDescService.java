package com.sales.sellergoods.service.service;
import com.sales.pojo.TbGoodsDesc;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:GoodsDesc的增删改查
 *
 ****/
public interface GoodsDescService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbGoodsDesc> findAll();

    /***
     * 增加GoodsDesc
     * @param goodsDesc
     * @return
     */
    void insert(TbGoodsDesc goodsDesc);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改GoodsDesc
     * @param goodsDesc
     * @return
     */
    void update(TbGoodsDesc goodsDesc);

    /***
     * 根据ID查询GoodsDesc
     * @param id
     * @return
     */
   TbGoodsDesc findOne(Long id);

    /***
     * 查询所有GoodsDesc
     * @return
     */
    PageResult findPage(TbGoodsDesc goodsDesc, int PageNo, int PageSize);

}
