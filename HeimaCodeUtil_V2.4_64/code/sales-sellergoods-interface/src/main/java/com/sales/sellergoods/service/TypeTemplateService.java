package com.sales.sellergoods.service.service;
import com.sales.pojo.TbTypeTemplate;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:TypeTemplate的增删改查
 *
 ****/
public interface TypeTemplateService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbTypeTemplate> findAll();

    /***
     * 增加TypeTemplate
     * @param typeTemplate
     * @return
     */
    void insert(TbTypeTemplate typeTemplate);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改TypeTemplate
     * @param typeTemplate
     * @return
     */
    void update(TbTypeTemplate typeTemplate);

    /***
     * 根据ID查询TypeTemplate
     * @param id
     * @return
     */
   TbTypeTemplate findOne(Long id);

    /***
     * 查询所有TypeTemplate
     * @return
     */
    PageResult findPage(TbTypeTemplate typeTemplate, int PageNo, int PageSize);

}
