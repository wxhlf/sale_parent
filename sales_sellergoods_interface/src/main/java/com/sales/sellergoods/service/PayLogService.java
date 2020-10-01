package com.sales.sellergoods.service;
import com.sales.pojo.TbPayLog;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:PayLog的增删改查
 *
 ****/
public interface PayLogService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbPayLog> findAll();

    /***
     * 增加PayLog
     * @param payLog
     * @return
     */
    void insert(TbPayLog payLog);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改PayLog
     * @param payLog
     * @return
     */
    void update(TbPayLog payLog);

    /***
     * 根据ID查询PayLog
     * @param id
     * @return
     */
   TbPayLog findOne(Long id);

    /***
     * 查询所有PayLog
     * @return
     */
    PageResult findPage(TbPayLog payLog, int PageNo, int PageSize);

}
