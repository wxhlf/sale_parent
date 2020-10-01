package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbSpecificationOptionMapper;
import com.sales.pojo.TbSpecificationOption;
import com.sales.pojo.TbSpecificationOptionExample;
//import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.sales.sellergoods.service.SpecificationOptionService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:SpecificationOption的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class SpecificationOptionServiceImpl implements SpecificationOptionService {

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;


    @Override
    public List<TbSpecificationOption> findAll() {
        return specificationOptionMapper.selectByExample(null);
    }


    /**
     * 增加SpecificationOption
     * @param specificationOption
     */
    @Override
    public void insert(TbSpecificationOption specificationOption) {
        specificationOptionMapper.insert(specificationOption);
    }


    /***
     * 删除SpecificationOption
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andSpecificationOptionIdIn(Arrays.asList(ids));
        specificationOptionMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询SpecificationOption
     * @param specificationOption
     * @return
     */
    @Override
    public void update(TbSpecificationOption specificationOption) {
       specificationOptionMapper.updateByPrimaryKey(specificationOption);
    }


    /***
     * 根据ID查询SpecificationOption
     * @param id
     * @return
     */
    @Override
    public TbSpecificationOption findOne(Long id) {
        return specificationOptionMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有SpecificationOption
     * @return
     */
    @Override
    public PageResult findPage(TbSpecificationOption specificationOption, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        if(specificationOption!=null){         
            			if(specificationOption.getOptionName()!=null && specificationOption.getOptionName().length()>0){
				criteria.andOptionNameLike("%"+specificationOption.getOptionName()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbSpecificationOption> lists = specificationOptionMapper.selectByExample(example);
        PageInfo<TbSpecificationOption> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
