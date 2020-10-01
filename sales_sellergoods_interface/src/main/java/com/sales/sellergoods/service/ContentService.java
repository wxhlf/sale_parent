package com.sales.sellergoods.service;
import com.sales.pojo.TbContent;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Content的增删改查
 *
 ****/
public interface ContentService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbContent> findAll();

    /***
     * 增加Content
     * @param content
     * @return
     */
    void insert(TbContent content);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Content
     * @param content
     * @return
     */
    void update(TbContent content);

    /***
     * 根据ID查询Content
     * @param id
     * @return
     */
   TbContent findOne(Long id);

    /***
     * 查询所有Content
     * @return
     */
    PageResult findPage(TbContent content, int PageNo, int PageSize);

}
