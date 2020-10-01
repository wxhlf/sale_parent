package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbGoodsMapper;
import com.sales.pojo.TbGoods;
import com.sales.pojo.TbGoodsExample;
//import com.pinyougou.pojo.TbGoodsExample;
import com.sales.sellergoods.service.GoodsService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Goods的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper goodsMapper;


    @Override
    public List<TbGoods> findAll() {
        return goodsMapper.selectByExample(null);
    }


    /**
     * 增加Goods
     * @param goods
     */
    @Override
    public void insert(TbGoods goods) {
        goodsMapper.insert(goods);
    }


    /***
     * 删除Goods
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbGoodsExample example = new TbGoodsExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andGoodsIdIn(Arrays.asList(ids));
        goodsMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Goods
     * @param goods
     * @return
     */
    @Override
    public void update(TbGoods goods) {
       goodsMapper.updateByPrimaryKey(goods);
    }


    /***
     * 根据ID查询Goods
     * @param id
     * @return
     */
    @Override
    public TbGoods findOne(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Goods
     * @return
     */
    @Override
    public PageResult findPage(TbGoods goods, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbGoodsExample example = new TbGoodsExample();
        TbGoodsExample.Criteria criteria = example.createCriteria();
        if(goods!=null){         
            			if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+goods.getSellerId()+"%");
			}
			if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
				criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
			}
			if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
				criteria.andAuditStatusLike("%"+goods.getAuditStatus()+"%");
			}
			if(goods.getIsMarketable()!=null && goods.getIsMarketable().length()>0){
				criteria.andIsMarketableLike("%"+goods.getIsMarketable()+"%");
			}
			if(goods.getCaption()!=null && goods.getCaption().length()>0){
				criteria.andCaptionLike("%"+goods.getCaption()+"%");
			}
			if(goods.getSmallPic()!=null && goods.getSmallPic().length()>0){
				criteria.andSmallPicLike("%"+goods.getSmallPic()+"%");
			}
			if(goods.getIsEnableSpec()!=null && goods.getIsEnableSpec().length()>0){
				criteria.andIsEnableSpecLike("%"+goods.getIsEnableSpec()+"%");
			}
			if(goods.getIsDelete()!=null && goods.getIsDelete().length()>0){
				criteria.andIsDeleteLike("%"+goods.getIsDelete()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbGoods> lists = goodsMapper.selectByExample(example);
        PageInfo<TbGoods> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
