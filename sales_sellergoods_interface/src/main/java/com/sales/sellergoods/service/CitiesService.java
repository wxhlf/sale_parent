package com.sales.sellergoods.service;
import com.sales.pojo.TbCities;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Cities的增删改查
 *
 ****/
public interface CitiesService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbCities> findAll();

    /***
     * 增加Cities
     * @param cities
     * @return
     */
    void insert(TbCities cities);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Cities
     * @param cities
     * @return
     */
    void update(TbCities cities);

    /***
     * 根据ID查询Cities
     * @param id
     * @return
     */
   TbCities findOne(Long id);

    /***
     * 查询所有Cities
     * @return
     */
    PageResult findPage(TbCities cities, int PageNo, int PageSize);

}
