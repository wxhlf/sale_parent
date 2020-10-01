package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbBrandMapper;
import com.sales.pojo.TbBrand;
import com.sales.pojo.TbBrandExample;
//import com.pinyougou.pojo.TbBrandExample;
import com.sales.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/***
 *
 * @Author:itheima
 * @Description:Brand的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;


    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }


    /**
     * 增加Brand
     * @param brand
     */
    @Override
    public void insert(TbBrand brand) {
        brandMapper.insert(brand);
    }


    /***
     * 删除Brand
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbBrandExample example = new TbBrandExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andBrandIdIn(Arrays.asList(ids));
        brandMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Brand
     * @param brand
     * @return
     */
    @Override
    public void update(TbBrand brand) {
       brandMapper.updateByPrimaryKey(brand);
    }


    /***
     * 根据ID查询Brand
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Brand
     * @return
     */
    @Override
    public PageResult findPage(TbBrand brand, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if(brand!=null){         
            			if(brand.getName()!=null && brand.getName().length()>0){
				criteria.andNameLike("%"+brand.getName()+"%");
			}
			if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
				criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbBrand> lists = brandMapper.selectByExample(example);
        PageInfo<TbBrand> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }

    @Override
    public List<Map> selectOptionList() {
        return brandMapper.SelectOptionList();
    }


}
