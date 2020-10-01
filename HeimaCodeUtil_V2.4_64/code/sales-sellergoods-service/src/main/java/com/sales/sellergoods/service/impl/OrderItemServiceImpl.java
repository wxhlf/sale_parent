package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbOrderItemMapper;
import com.sales.pojo.TbOrderItem;
import com.sales.pojo.TbOrderItemExample;
//import com.pinyougou.pojo.TbOrderItemExample;
import com.sales.sellergoods.service.OrderItemService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:OrderItem的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private TbOrderItemMapper orderItemMapper;


    @Override
    public List<TbOrderItem> findAll() {
        return orderItemMapper.selectByExample(null);
    }


    /**
     * 增加OrderItem
     * @param orderItem
     */
    @Override
    public void insert(TbOrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }


    /***
     * 删除OrderItem
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbOrderItemExample example = new TbOrderItemExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andOrderItemIdIn(Arrays.asList(ids));
        orderItemMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询OrderItem
     * @param orderItem
     * @return
     */
    @Override
    public void update(TbOrderItem orderItem) {
       orderItemMapper.updateByPrimaryKey(orderItem);
    }


    /***
     * 根据ID查询OrderItem
     * @param id
     * @return
     */
    @Override
    public TbOrderItem findOne(Long id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有OrderItem
     * @return
     */
    @Override
    public PageResult findPage(TbOrderItem orderItem, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        if(orderItem!=null){         
            			if(orderItem.getTitle()!=null && orderItem.getTitle().length()>0){
				criteria.andTitleLike("%"+orderItem.getTitle()+"%");
			}
			if(orderItem.getPicPath()!=null && orderItem.getPicPath().length()>0){
				criteria.andPicPathLike("%"+orderItem.getPicPath()+"%");
			}
			if(orderItem.getSellerId()!=null && orderItem.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+orderItem.getSellerId()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbOrderItem> lists = orderItemMapper.selectByExample(example);
        PageInfo<TbOrderItem> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
