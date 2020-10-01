package com.sales.sellergoods.service.service;
import com.sales.pojo.TbSpecificationOption;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SpecificationOption的增删改查
 *
 ****/
public interface SpecificationOptionService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbSpecificationOption> findAll();

    /***
     * 增加SpecificationOption
     * @param specificationOption
     * @return
     */
    void insert(TbSpecificationOption specificationOption);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改SpecificationOption
     * @param specificationOption
     * @return
     */
    void update(TbSpecificationOption specificationOption);

    /***
     * 根据ID查询SpecificationOption
     * @param id
     * @return
     */
   TbSpecificationOption findOne(Long id);

    /***
     * 查询所有SpecificationOption
     * @return
     */
    PageResult findPage(TbSpecificationOption specificationOption, int PageNo, int PageSize);

}
