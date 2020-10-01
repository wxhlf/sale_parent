package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbProvincesMapper;
import com.sales.pojo.TbProvinces;
import com.sales.pojo.TbProvincesExample;
//import com.pinyougou.pojo.TbProvincesExample;
import com.sales.sellergoods.service.ProvincesService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Provinces的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class ProvincesServiceImpl implements ProvincesService {

    @Autowired
    private TbProvincesMapper provincesMapper;


    @Override
    public List<TbProvinces> findAll() {
        return provincesMapper.selectByExample(null);
    }


    /**
     * 增加Provinces
     * @param provinces
     */
    @Override
    public void insert(TbProvinces provinces) {
        provincesMapper.insert(provinces);
    }


    /***
     * 删除Provinces
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbProvincesExample example = new TbProvincesExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andProvincesIdIn(Arrays.asList(ids));
        provincesMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Provinces
     * @param provinces
     * @return
     */
    @Override
    public void update(TbProvinces provinces) {
       provincesMapper.updateByPrimaryKey(provinces);
    }


    /***
     * 根据ID查询Provinces
     * @param id
     * @return
     */
    @Override
    public TbProvinces findOne(Long id) {
        return provincesMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Provinces
     * @return
     */
    @Override
    public PageResult findPage(TbProvinces provinces, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbProvincesExample example = new TbProvincesExample();
        TbProvincesExample.Criteria criteria = example.createCriteria();
        if(provinces!=null){         
            			if(provinces.getProvinceid()!=null && provinces.getProvinceid().length()>0){
				criteria.andProvinceidLike("%"+provinces.getProvinceid()+"%");
			}
			if(provinces.getProvince()!=null && provinces.getProvince().length()>0){
				criteria.andProvinceLike("%"+provinces.getProvince()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbProvinces> lists = provincesMapper.selectByExample(example);
        PageInfo<TbProvinces> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
