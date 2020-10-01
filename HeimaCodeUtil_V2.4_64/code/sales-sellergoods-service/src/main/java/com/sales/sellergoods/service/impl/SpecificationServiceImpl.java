package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbSpecificationMapper;
import com.sales.pojo.TbSpecification;
import com.sales.pojo.TbSpecificationExample;
//import com.pinyougou.pojo.TbSpecificationExample;
import com.sales.sellergoods.service.SpecificationService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Specification的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;


    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }


    /**
     * 增加Specification
     * @param specification
     */
    @Override
    public void insert(TbSpecification specification) {
        specificationMapper.insert(specification);
    }


    /***
     * 删除Specification
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbSpecificationExample example = new TbSpecificationExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andSpecificationIdIn(Arrays.asList(ids));
        specificationMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Specification
     * @param specification
     * @return
     */
    @Override
    public void update(TbSpecification specification) {
       specificationMapper.updateByPrimaryKey(specification);
    }


    /***
     * 根据ID查询Specification
     * @param id
     * @return
     */
    @Override
    public TbSpecification findOne(Long id) {
        return specificationMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Specification
     * @return
     */
    @Override
    public PageResult findPage(TbSpecification specification, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(specification!=null){         
            			if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbSpecification> lists = specificationMapper.selectByExample(example);
        PageInfo<TbSpecification> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
