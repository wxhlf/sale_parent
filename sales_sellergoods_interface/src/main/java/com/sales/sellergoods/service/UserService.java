package com.sales.sellergoods.service;
import com.sales.pojo.TbUser;
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:User的增删改查
 *
 ****/
public interface UserService {

    
	/**
     * 查询全部
     * @return
     */
    List<TbUser> findAll();

    /***
     * 增加User
     * @param user
     * @return
     */
    void insert(TbUser user);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改User
     * @param user
     * @return
     */
    void update(TbUser user);

    /***
     * 根据ID查询User
     * @param id
     * @return
     */
   TbUser findOne(Long id);

    /***
     * 查询所有User
     * @return
     */
    PageResult findPage(TbUser user, int PageNo, int PageSize);

}
