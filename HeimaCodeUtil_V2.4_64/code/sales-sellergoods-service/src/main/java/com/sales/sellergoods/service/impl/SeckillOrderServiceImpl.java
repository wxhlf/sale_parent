package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbSeckillOrderMapper;
import com.sales.pojo.TbSeckillOrder;
import com.sales.pojo.TbSeckillOrderExample;
//import com.pinyougou.pojo.TbSeckillOrderExample;
import com.sales.sellergoods.service.SeckillOrderService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SeckillOrder的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private TbSeckillOrderMapper seckillOrderMapper;


    @Override
    public List<TbSeckillOrder> findAll() {
        return seckillOrderMapper.selectByExample(null);
    }


    /**
     * 增加SeckillOrder
     * @param seckillOrder
     */
    @Override
    public void insert(TbSeckillOrder seckillOrder) {
        seckillOrderMapper.insert(seckillOrder);
    }


    /***
     * 删除SeckillOrder
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbSeckillOrderExample example = new TbSeckillOrderExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andSeckillOrderIdIn(Arrays.asList(ids));
        seckillOrderMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询SeckillOrder
     * @param seckillOrder
     * @return
     */
    @Override
    public void update(TbSeckillOrder seckillOrder) {
       seckillOrderMapper.updateByPrimaryKey(seckillOrder);
    }


    /***
     * 根据ID查询SeckillOrder
     * @param id
     * @return
     */
    @Override
    public TbSeckillOrder findOne(Long id) {
        return seckillOrderMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有SeckillOrder
     * @return
     */
    @Override
    public PageResult findPage(TbSeckillOrder seckillOrder, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbSeckillOrderExample example = new TbSeckillOrderExample();
        TbSeckillOrderExample.Criteria criteria = example.createCriteria();
        if(seckillOrder!=null){         
            			if(seckillOrder.getUserId()!=null && seckillOrder.getUserId().length()>0){
				criteria.andUserIdLike("%"+seckillOrder.getUserId()+"%");
			}
			if(seckillOrder.getSellerId()!=null && seckillOrder.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+seckillOrder.getSellerId()+"%");
			}
			if(seckillOrder.getStatus()!=null && seckillOrder.getStatus().length()>0){
				criteria.andStatusLike("%"+seckillOrder.getStatus()+"%");
			}
			if(seckillOrder.getReceiverAddress()!=null && seckillOrder.getReceiverAddress().length()>0){
				criteria.andReceiverAddressLike("%"+seckillOrder.getReceiverAddress()+"%");
			}
			if(seckillOrder.getReceiverMobile()!=null && seckillOrder.getReceiverMobile().length()>0){
				criteria.andReceiverMobileLike("%"+seckillOrder.getReceiverMobile()+"%");
			}
			if(seckillOrder.getReceiver()!=null && seckillOrder.getReceiver().length()>0){
				criteria.andReceiverLike("%"+seckillOrder.getReceiver()+"%");
			}
			if(seckillOrder.getTransactionId()!=null && seckillOrder.getTransactionId().length()>0){
				criteria.andTransactionIdLike("%"+seckillOrder.getTransactionId()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbSeckillOrder> lists = seckillOrderMapper.selectByExample(example);
        PageInfo<TbSeckillOrder> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
