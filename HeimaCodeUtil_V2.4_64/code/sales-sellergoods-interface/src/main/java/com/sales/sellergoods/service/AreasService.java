package com.sales.sellergoods.service.service;
import com.sales.pojo.TbAreas;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Areas的增删改查
 *
 ****/
public interface AreasService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbAreas> findAll();

    /***
     * 增加Areas
     * @param areas
     * @return
     */
    void insert(TbAreas areas);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改Areas
     * @param areas
     * @return
     */
    void update(TbAreas areas);

    /***
     * 根据ID查询Areas
     * @param id
     * @return
     */
   TbAreas findOne(Long id);

    /***
     * 查询所有Areas
     * @return
     */
    PageResult findPage(TbAreas areas, int PageNo, int PageSize);

}
