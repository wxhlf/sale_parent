package com.sales.sellergoods.service.service;
import com.sales.pojo.TbProvinces;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Provinces的增删改查
 *
 ****/
public interface ProvincesService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbProvinces> findAll();

    /***
     * 增加Provinces
     * @param provinces
     * @return
     */
    void insert(TbProvinces provinces);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Provinces
     * @param provinces
     * @return
     */
    void update(TbProvinces provinces);

    /***
     * 根据ID查询Provinces
     * @param id
     * @return
     */
   TbProvinces findOne(Long id);

    /***
     * 查询所有Provinces
     * @return
     */
    PageResult findPage(TbProvinces provinces, int PageNo, int PageSize);

}
