package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbCitiesMapper;
import com.sales.pojo.TbCities;
import com.sales.pojo.TbCitiesExample;
//import com.pinyougou.pojo.TbCitiesExample;
import com.sales.sellergoods.service.CitiesService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Cities的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private TbCitiesMapper citiesMapper;


    @Override
    public List<TbCities> findAll() {
        return citiesMapper.selectByExample(null);
    }


    /**
     * 增加Cities
     * @param cities
     */
    @Override
    public void insert(TbCities cities) {
        citiesMapper.insert(cities);
    }


    /***
     * 删除Cities
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbCitiesExample example = new TbCitiesExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andCitiesIdIn(Arrays.asList(ids));
        citiesMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Cities
     * @param cities
     * @return
     */
    @Override
    public void update(TbCities cities) {
       citiesMapper.updateByPrimaryKey(cities);
    }


    /***
     * 根据ID查询Cities
     * @param id
     * @return
     */
    @Override
    public TbCities findOne(Long id) {
        return citiesMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Cities
     * @return
     */
    @Override
    public PageResult findPage(TbCities cities, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbCitiesExample example = new TbCitiesExample();
        TbCitiesExample.Criteria criteria = example.createCriteria();
        if(cities!=null){         
            			if(cities.getCityid()!=null && cities.getCityid().length()>0){
				criteria.andCityidLike("%"+cities.getCityid()+"%");
			}
			if(cities.getCity()!=null && cities.getCity().length()>0){
				criteria.andCityLike("%"+cities.getCity()+"%");
			}
			if(cities.getProvinceid()!=null && cities.getProvinceid().length()>0){
				criteria.andProvinceidLike("%"+cities.getProvinceid()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbCities> lists = citiesMapper.selectByExample(example);
        PageInfo<TbCities> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
