package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbFreightTemplateMapper;
import com.sales.pojo.TbFreightTemplate;
import com.sales.pojo.TbFreightTemplateExample;
//import com.pinyougou.pojo.TbFreightTemplateExample;
import com.sales.sellergoods.service.FreightTemplateService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:FreightTemplate的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class FreightTemplateServiceImpl implements FreightTemplateService {

    @Autowired
    private TbFreightTemplateMapper freightTemplateMapper;


    @Override
    public List<TbFreightTemplate> findAll() {
        return freightTemplateMapper.selectByExample(null);
    }


    /**
     * 增加FreightTemplate
     * @param freightTemplate
     */
    @Override
    public void insert(TbFreightTemplate freightTemplate) {
        freightTemplateMapper.insert(freightTemplate);
    }


    /***
     * 删除FreightTemplate
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbFreightTemplateExample example = new TbFreightTemplateExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andFreightTemplateIdIn(Arrays.asList(ids));
        freightTemplateMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询FreightTemplate
     * @param freightTemplate
     * @return
     */
    @Override
    public void update(TbFreightTemplate freightTemplate) {
       freightTemplateMapper.updateByPrimaryKey(freightTemplate);
    }


    /***
     * 根据ID查询FreightTemplate
     * @param id
     * @return
     */
    @Override
    public TbFreightTemplate findOne(Long id) {
        return freightTemplateMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有FreightTemplate
     * @return
     */
    @Override
    public PageResult findPage(TbFreightTemplate freightTemplate, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbFreightTemplateExample example = new TbFreightTemplateExample();
        TbFreightTemplateExample.Criteria criteria = example.createCriteria();
        if(freightTemplate!=null){         
            			if(freightTemplate.getSellerId()!=null && freightTemplate.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+freightTemplate.getSellerId()+"%");
			}
			if(freightTemplate.getIsDefault()!=null && freightTemplate.getIsDefault().length()>0){
				criteria.andIsDefaultLike("%"+freightTemplate.getIsDefault()+"%");
			}
			if(freightTemplate.getName()!=null && freightTemplate.getName().length()>0){
				criteria.andNameLike("%"+freightTemplate.getName()+"%");
			}
			if(freightTemplate.getSendTimeType()!=null && freightTemplate.getSendTimeType().length()>0){
				criteria.andSendTimeTypeLike("%"+freightTemplate.getSendTimeType()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbFreightTemplate> lists = freightTemplateMapper.selectByExample(example);
        PageInfo<TbFreightTemplate> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
