package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbTypeTemplateMapper;
import com.sales.pojo.TbTypeTemplate;
import com.sales.pojo.TbTypeTemplateExample;
//import com.pinyougou.pojo.TbTypeTemplateExample;
import com.sales.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:TypeTemplate的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper typeTemplateMapper;


    @Override
    public List<TbTypeTemplate> findAll() {
        return typeTemplateMapper.selectByExample(null);
    }


    /**
     * 增加TypeTemplate
     * @param typeTemplate
     */
    @Override
    public void insert(TbTypeTemplate typeTemplate) {
        typeTemplateMapper.insert(typeTemplate);
    }


    /***
     * 删除TypeTemplate
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbTypeTemplateExample example = new TbTypeTemplateExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andTypeTemplateIdIn(Arrays.asList(ids));
        typeTemplateMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询TypeTemplate
     * @param typeTemplate
     * @return
     */
    @Override
    public void update(TbTypeTemplate typeTemplate) {
       typeTemplateMapper.updateByPrimaryKey(typeTemplate);
    }


    /***
     * 根据ID查询TypeTemplate
     * @param id
     * @return
     */
    @Override
    public TbTypeTemplate findOne(Long id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有TypeTemplate
     * @return
     */
    @Override
    public PageResult findPage(TbTypeTemplate typeTemplate, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if(typeTemplate!=null){         
            			if(typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
				criteria.andNameLike("%"+typeTemplate.getName()+"%");
			}
			if(typeTemplate.getSpecIds()!=null && typeTemplate.getSpecIds().length()>0){
				criteria.andSpecIdsLike("%"+typeTemplate.getSpecIds()+"%");
			}
			if(typeTemplate.getBrandIds()!=null && typeTemplate.getBrandIds().length()>0){
				criteria.andBrandIdsLike("%"+typeTemplate.getBrandIds()+"%");
			}
			if(typeTemplate.getCustomAttributeItems()!=null && typeTemplate.getCustomAttributeItems().length()>0){
				criteria.andCustomAttributeItemsLike("%"+typeTemplate.getCustomAttributeItems()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbTypeTemplate> lists = typeTemplateMapper.selectByExample(example);
        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
