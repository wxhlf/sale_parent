package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbItemCatMapper;
import com.sales.pojo.TbItemCat;
import com.sales.pojo.TbItemCatExample;
//import com.pinyougou.pojo.TbItemCatExample;
import com.sales.sellergoods.service.ItemCatService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:ItemCat的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;


    @Override
    public List<TbItemCat> findAll() {
        return itemCatMapper.selectByExample(null);
    }


    /**
     * 增加ItemCat
     * @param itemCat
     */
    @Override
    public void insert(TbItemCat itemCat) {
        itemCatMapper.insert(itemCat);
    }


    /***
     * 删除ItemCat
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbItemCatExample example = new TbItemCatExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andItemCatIdIn(Arrays.asList(ids));
        itemCatMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询ItemCat
     * @param itemCat
     * @return
     */
    @Override
    public void update(TbItemCat itemCat) {
       itemCatMapper.updateByPrimaryKey(itemCat);
    }


    /***
     * 根据ID查询ItemCat
     * @param id
     * @return
     */
    @Override
    public TbItemCat findOne(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有ItemCat
     * @return
     */
    @Override
    public PageResult findPage(TbItemCat itemCat, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        if(itemCat!=null){         
            			if(itemCat.getName()!=null && itemCat.getName().length()>0){
				criteria.andNameLike("%"+itemCat.getName()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbItemCat> lists = itemCatMapper.selectByExample(example);
        PageInfo<TbItemCat> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
