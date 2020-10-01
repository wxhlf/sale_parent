package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbSeckillGoodsMapper;
import com.sales.pojo.TbSeckillGoods;
import com.sales.pojo.TbSeckillGoodsExample;
//import com.pinyougou.pojo.TbSeckillGoodsExample;
import com.sales.sellergoods.service.SeckillGoodsService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SeckillGoods的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private TbSeckillGoodsMapper seckillGoodsMapper;


    @Override
    public List<TbSeckillGoods> findAll() {
        return seckillGoodsMapper.selectByExample(null);
    }


    /**
     * 增加SeckillGoods
     * @param seckillGoods
     */
    @Override
    public void insert(TbSeckillGoods seckillGoods) {
        seckillGoodsMapper.insert(seckillGoods);
    }


    /***
     * 删除SeckillGoods
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbSeckillGoodsExample example = new TbSeckillGoodsExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andSeckillGoodsIdIn(Arrays.asList(ids));
        seckillGoodsMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询SeckillGoods
     * @param seckillGoods
     * @return
     */
    @Override
    public void update(TbSeckillGoods seckillGoods) {
       seckillGoodsMapper.updateByPrimaryKey(seckillGoods);
    }


    /***
     * 根据ID查询SeckillGoods
     * @param id
     * @return
     */
    @Override
    public TbSeckillGoods findOne(Long id) {
        return seckillGoodsMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有SeckillGoods
     * @return
     */
    @Override
    public PageResult findPage(TbSeckillGoods seckillGoods, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbSeckillGoodsExample example = new TbSeckillGoodsExample();
        TbSeckillGoodsExample.Criteria criteria = example.createCriteria();
        if(seckillGoods!=null){         
            			if(seckillGoods.getTitle()!=null && seckillGoods.getTitle().length()>0){
				criteria.andTitleLike("%"+seckillGoods.getTitle()+"%");
			}
			if(seckillGoods.getSmallPic()!=null && seckillGoods.getSmallPic().length()>0){
				criteria.andSmallPicLike("%"+seckillGoods.getSmallPic()+"%");
			}
			if(seckillGoods.getSellerId()!=null && seckillGoods.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+seckillGoods.getSellerId()+"%");
			}
			if(seckillGoods.getStatus()!=null && seckillGoods.getStatus().length()>0){
				criteria.andStatusLike("%"+seckillGoods.getStatus()+"%");
			}
			if(seckillGoods.getIntroduction()!=null && seckillGoods.getIntroduction().length()>0){
				criteria.andIntroductionLike("%"+seckillGoods.getIntroduction()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbSeckillGoods> lists = seckillGoodsMapper.selectByExample(example);
        PageInfo<TbSeckillGoods> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
