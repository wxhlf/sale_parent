package com.sales.sellergoods.service;
import com.sales.pojo.TbContentCategory;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:ContentCategory的增删改查
 *
 ****/
public interface ContentCategoryService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbContentCategory> findAll();

    /***
     * 增加ContentCategory
     * @param contentCategory
     * @return
     */
    void insert(TbContentCategory contentCategory);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改ContentCategory
     * @param contentCategory
     * @return
     */
    void update(TbContentCategory contentCategory);

    /***
     * 根据ID查询ContentCategory
     * @param id
     * @return
     */
   TbContentCategory findOne(Long id);

    /***
     * 查询所有ContentCategory
     * @return
     */
    PageResult findPage(TbContentCategory contentCategory, int PageNo, int PageSize);

}
