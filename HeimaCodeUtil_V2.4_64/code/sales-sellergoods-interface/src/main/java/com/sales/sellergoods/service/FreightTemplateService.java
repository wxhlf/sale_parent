package com.sales.sellergoods.service.service;
import com.sales.pojo.TbFreightTemplate;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:FreightTemplate的增删改查
 *
 ****/
public interface FreightTemplateService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbFreightTemplate> findAll();

    /***
     * 增加FreightTemplate
     * @param freightTemplate
     * @return
     */
    void insert(TbFreightTemplate freightTemplate);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改FreightTemplate
     * @param freightTemplate
     * @return
     */
    void update(TbFreightTemplate freightTemplate);

    /***
     * 根据ID查询FreightTemplate
     * @param id
     * @return
     */
   TbFreightTemplate findOne(Long id);

    /***
     * 查询所有FreightTemplate
     * @return
     */
    PageResult findPage(TbFreightTemplate freightTemplate, int PageNo, int PageSize);

}
