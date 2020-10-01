package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbItemMapper;
import com.sales.pojo.TbItem;
import com.sales.pojo.TbItemExample;
//import com.pinyougou.pojo.TbItemExample;
import com.sales.sellergoods.service.ItemService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Item的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;


    @Override
    public List<TbItem> findAll() {
        return itemMapper.selectByExample(null);
    }


    /**
     * 增加Item
     * @param item
     */
    @Override
    public void insert(TbItem item) {
        itemMapper.insert(item);
    }


    /***
     * 删除Item
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbItemExample example = new TbItemExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andItemIdIn(Arrays.asList(ids));
        itemMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Item
     * @param item
     * @return
     */
    @Override
    public void update(TbItem item) {
       itemMapper.updateByPrimaryKey(item);
    }


    /***
     * 根据ID查询Item
     * @param id
     * @return
     */
    @Override
    public TbItem findOne(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Item
     * @return
     */
    @Override
    public PageResult findPage(TbItem item, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        if(item!=null){         
            			if(item.getTitle()!=null && item.getTitle().length()>0){
				criteria.andTitleLike("%"+item.getTitle()+"%");
			}
			if(item.getSellPoint()!=null && item.getSellPoint().length()>0){
				criteria.andSellPointLike("%"+item.getSellPoint()+"%");
			}
			if(item.getBarcode()!=null && item.getBarcode().length()>0){
				criteria.andBarcodeLike("%"+item.getBarcode()+"%");
			}
			if(item.getImage()!=null && item.getImage().length()>0){
				criteria.andImageLike("%"+item.getImage()+"%");
			}
			if(item.getStatus()!=null && item.getStatus().length()>0){
				criteria.andStatusLike("%"+item.getStatus()+"%");
			}
			if(item.getItemSn()!=null && item.getItemSn().length()>0){
				criteria.andItemSnLike("%"+item.getItemSn()+"%");
			}
			if(item.getIsDefault()!=null && item.getIsDefault().length()>0){
				criteria.andIsDefaultLike("%"+item.getIsDefault()+"%");
			}
			if(item.getSellerId()!=null && item.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+item.getSellerId()+"%");
			}
			if(item.getCartThumbnail()!=null && item.getCartThumbnail().length()>0){
				criteria.andCartThumbnailLike("%"+item.getCartThumbnail()+"%");
			}
			if(item.getCategory()!=null && item.getCategory().length()>0){
				criteria.andCategoryLike("%"+item.getCategory()+"%");
			}
			if(item.getBrand()!=null && item.getBrand().length()>0){
				criteria.andBrandLike("%"+item.getBrand()+"%");
			}
			if(item.getSpec()!=null && item.getSpec().length()>0){
				criteria.andSpecLike("%"+item.getSpec()+"%");
			}
			if(item.getSeller()!=null && item.getSeller().length()>0){
				criteria.andSellerLike("%"+item.getSeller()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbItem> lists = itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
