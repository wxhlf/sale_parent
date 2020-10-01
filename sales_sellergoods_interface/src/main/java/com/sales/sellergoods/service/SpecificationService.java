package com.sales.sellergoods.service;
import com.sales.pojo.TbSpecification;
import entity.PageResult;
import entity.Specification;

import java.util.List;
import java.util.Map;

/***
 *
 * @Author:itheima
 * @Description:Specification的增删改查
 *
 ****/
public interface SpecificationService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbSpecification> findAll();

    /***
     * 增加Specification
     * @param specification
     * @return
     */
    void insert(Specification specification);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Specification
     * @param specification
     * @return
     */
    void update(Specification specification);

    /***
     * 根据ID查询Specification
     * @param id
     * @return
     */
   Specification findOne(Long id);

    /***
     * 查询所有Specification
     * @return
     */
    PageResult findPage(TbSpecification specification, int PageNo, int PageSize);
    /**
     * 加载配牌选项
     * @return
     */
    List<Map> selectOptionList();

}
