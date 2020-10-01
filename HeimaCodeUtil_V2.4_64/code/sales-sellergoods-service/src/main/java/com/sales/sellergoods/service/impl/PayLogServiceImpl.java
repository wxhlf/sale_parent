package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbPayLogMapper;
import com.sales.pojo.TbPayLog;
import com.sales.pojo.TbPayLogExample;
//import com.pinyougou.pojo.TbPayLogExample;
import com.sales.sellergoods.service.PayLogService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:PayLog的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class PayLogServiceImpl implements PayLogService {

    @Autowired
    private TbPayLogMapper payLogMapper;


    @Override
    public List<TbPayLog> findAll() {
        return payLogMapper.selectByExample(null);
    }


    /**
     * 增加PayLog
     * @param payLog
     */
    @Override
    public void insert(TbPayLog payLog) {
        payLogMapper.insert(payLog);
    }


    /***
     * 删除PayLog
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbPayLogExample example = new TbPayLogExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andPayLogIdIn(Arrays.asList(ids));
        payLogMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询PayLog
     * @param payLog
     * @return
     */
    @Override
    public void update(TbPayLog payLog) {
       payLogMapper.updateByPrimaryKey(payLog);
    }


    /***
     * 根据ID查询PayLog
     * @param id
     * @return
     */
    @Override
    public TbPayLog findOne(Long id) {
        return payLogMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有PayLog
     * @return
     */
    @Override
    public PageResult findPage(TbPayLog payLog, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbPayLogExample example = new TbPayLogExample();
        TbPayLogExample.Criteria criteria = example.createCriteria();
        if(payLog!=null){         
            			if(payLog.getOutTradeNo()!=null && payLog.getOutTradeNo().length()>0){
				criteria.andOutTradeNoLike("%"+payLog.getOutTradeNo()+"%");
			}
			if(payLog.getUserId()!=null && payLog.getUserId().length()>0){
				criteria.andUserIdLike("%"+payLog.getUserId()+"%");
			}
			if(payLog.getTransactionId()!=null && payLog.getTransactionId().length()>0){
				criteria.andTransactionIdLike("%"+payLog.getTransactionId()+"%");
			}
			if(payLog.getTradeState()!=null && payLog.getTradeState().length()>0){
				criteria.andTradeStateLike("%"+payLog.getTradeState()+"%");
			}
			if(payLog.getOrderList()!=null && payLog.getOrderList().length()>0){
				criteria.andOrderListLike("%"+payLog.getOrderList()+"%");
			}
			if(payLog.getPayType()!=null && payLog.getPayType().length()>0){
				criteria.andPayTypeLike("%"+payLog.getPayType()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbPayLog> lists = payLogMapper.selectByExample(example);
        PageInfo<TbPayLog> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
