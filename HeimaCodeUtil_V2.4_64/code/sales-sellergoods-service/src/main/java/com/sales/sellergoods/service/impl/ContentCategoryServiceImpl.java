package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbContentCategoryMapper;
import com.sales.pojo.TbContentCategory;
import com.sales.pojo.TbContentCategoryExample;
//import com.pinyougou.pojo.TbContentCategoryExample;
import com.sales.sellergoods.service.ContentCategoryService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:ContentCategory的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;


    @Override
    public List<TbContentCategory> findAll() {
        return contentCategoryMapper.selectByExample(null);
    }


    /**
     * 增加ContentCategory
     * @param contentCategory
     */
    @Override
    public void insert(TbContentCategory contentCategory) {
        contentCategoryMapper.insert(contentCategory);
    }


    /***
     * 删除ContentCategory
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbContentCategoryExample example = new TbContentCategoryExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andContentCategoryIdIn(Arrays.asList(ids));
        contentCategoryMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询ContentCategory
     * @param contentCategory
     * @return
     */
    @Override
    public void update(TbContentCategory contentCategory) {
       contentCategoryMapper.updateByPrimaryKey(contentCategory);
    }


    /***
     * 根据ID查询ContentCategory
     * @param id
     * @return
     */
    @Override
    public TbContentCategory findOne(Long id) {
        return contentCategoryMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有ContentCategory
     * @return
     */
    @Override
    public PageResult findPage(TbContentCategory contentCategory, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        if(contentCategory!=null){         
            			if(contentCategory.getName()!=null && contentCategory.getName().length()>0){
				criteria.andNameLike("%"+contentCategory.getName()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbContentCategory> lists = contentCategoryMapper.selectByExample(example);
        PageInfo<TbContentCategory> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
