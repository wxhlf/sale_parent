package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbGoodsDescMapper;
import com.sales.pojo.TbGoodsDesc;
import com.sales.pojo.TbGoodsDescExample;
//import com.pinyougou.pojo.TbGoodsDescExample;
import com.sales.sellergoods.service.GoodsDescService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:GoodsDesc的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class GoodsDescServiceImpl implements GoodsDescService {

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;


    @Override
    public List<TbGoodsDesc> findAll() {
        return goodsDescMapper.selectByExample(null);
    }


    /**
     * 增加GoodsDesc
     * @param goodsDesc
     */
    @Override
    public void insert(TbGoodsDesc goodsDesc) {
        goodsDescMapper.insert(goodsDesc);
    }


    /***
     * 删除GoodsDesc
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbGoodsDescExample example = new TbGoodsDescExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andGoodsDescIdIn(Arrays.asList(ids));
        goodsDescMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询GoodsDesc
     * @param goodsDesc
     * @return
     */
    @Override
    public void update(TbGoodsDesc goodsDesc) {
       goodsDescMapper.updateByPrimaryKey(goodsDesc);
    }


    /***
     * 根据ID查询GoodsDesc
     * @param id
     * @return
     */
    @Override
    public TbGoodsDesc findOne(Long id) {
        return goodsDescMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有GoodsDesc
     * @return
     */
    @Override
    public PageResult findPage(TbGoodsDesc goodsDesc, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbGoodsDescExample example = new TbGoodsDescExample();
        TbGoodsDescExample.Criteria criteria = example.createCriteria();
        if(goodsDesc!=null){         
            			if(goodsDesc.getIntroduction()!=null && goodsDesc.getIntroduction().length()>0){
				criteria.andIntroductionLike("%"+goodsDesc.getIntroduction()+"%");
			}
			if(goodsDesc.getSpecificationItems()!=null && goodsDesc.getSpecificationItems().length()>0){
				criteria.andSpecificationItemsLike("%"+goodsDesc.getSpecificationItems()+"%");
			}
			if(goodsDesc.getCustomAttributeItems()!=null && goodsDesc.getCustomAttributeItems().length()>0){
				criteria.andCustomAttributeItemsLike("%"+goodsDesc.getCustomAttributeItems()+"%");
			}
			if(goodsDesc.getItemImages()!=null && goodsDesc.getItemImages().length()>0){
				criteria.andItemImagesLike("%"+goodsDesc.getItemImages()+"%");
			}
			if(goodsDesc.getPackageList()!=null && goodsDesc.getPackageList().length()>0){
				criteria.andPackageListLike("%"+goodsDesc.getPackageList()+"%");
			}
			if(goodsDesc.getSaleService()!=null && goodsDesc.getSaleService().length()>0){
				criteria.andSaleServiceLike("%"+goodsDesc.getSaleService()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbGoodsDesc> lists = goodsDescMapper.selectByExample(example);
        PageInfo<TbGoodsDesc> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
