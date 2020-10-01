package com.sales.sellergoods.service;
import com.sales.pojo.TbBrand;
import entity.PageResult;
import java.util.List;
import java.util.Map;

/***
 *
 * @Author:itheima
 * @Description:Brand的增删改查
 *
 ****/
public interface BrandService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbBrand> findAll();

    /***
     * 增加Brand
     * @param brand
     * @return
     */
    void insert(TbBrand brand);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Brand
     * @param brand
     * @return
     */
    void update(TbBrand brand);

    /***
     * 根据ID查询Brand
     * @param id
     * @return
     */
   TbBrand findOne(Long id);

    /***
     * 查询所有Brand
     * @return
     */
    PageResult findPage(TbBrand brand, int PageNo, int PageSize);
    /**
     * 加载配牌选项
     * @return
     */
    List<Map> selectOptionList();

}
