package com.sales.sellergoods.service;
import com.sales.pojo.TbAddress;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Address的增删改查
 *
 ****/
public interface AddressService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbAddress> findAll();

    /***
     * 增加Address
     * @param address
     * @return
     */
    void insert(TbAddress address);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Address
     * @param address
     * @return
     */
    void update(TbAddress address);

    /***
     * 根据ID查询Address
     * @param id
     * @return
     */
   TbAddress findOne(Long id);

    /***
     * 查询所有Address
     * @return
     */
    PageResult findPage(TbAddress address, int PageNo, int PageSize);

}
