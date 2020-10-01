package com.sales.sellergoods.service.service;
import com.sales.pojo.TbSpecification;
import entity.PageResult;
import java.util.List;

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
    void insert(TbSpecification specification);

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
    void update(TbSpecification specification);

    /***
     * 根据ID查询Specification
     * @param id
     * @return
     */
   TbSpecification findOne(Long id);

    /***
     * 查询所有Specification
     * @return
     */
    PageResult findPage(TbSpecification specification, int PageNo, int PageSize);

}
