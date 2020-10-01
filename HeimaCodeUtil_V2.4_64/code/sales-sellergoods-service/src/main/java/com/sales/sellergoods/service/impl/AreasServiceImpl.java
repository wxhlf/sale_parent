package com.sales.sellergoods.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbAreasMapper;
import com.sales.pojo.TbAreas;
import com.sales.pojo.TbAreasExample;
//import com.pinyougou.pojo.TbAreasExample;
import com.sales.sellergoods.service.AreasService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Areas的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class AreasServiceImpl implements AreasService {

    @Autowired
    private TbAreasMapper areasMapper;


    @Override
    public List<TbAreas> findAll() {
        return areasMapper.selectByExample(null);
    }


    /**
     * 增加Areas
     * @param areas
     */
    @Override
    public void insert(TbAreas areas) {
        areasMapper.insert(areas);
    }


    /***
     * 删除Areas
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbAreasExample example = new TbAreasExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andAreasIdIn(Arrays.asList(ids));
        areasMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Areas
     * @param areas
     * @return
     */
    @Override
    public void update(TbAreas areas) {
       areasMapper.updateByPrimaryKey(areas);
    }


    /***
     * 根据ID查询Areas
     * @param id
     * @return
     */
    @Override
    public TbAreas findOne(Long id) {
        return areasMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Areas
     * @return
     */
    @Override
    public PageResult findPage(TbAreas areas, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbAreasExample example = new TbAreasExample();
        TbAreasExample.Criteria criteria = example.createCriteria();
        if(areas!=null){         
            			if(areas.getAreaid()!=null && areas.getAreaid().length()>0){
				criteria.andAreaidLike("%"+areas.getAreaid()+"%");
			}
			if(areas.getArea()!=null && areas.getArea().length()>0){
				criteria.andAreaLike("%"+areas.getArea()+"%");
			}
			if(areas.getCityid()!=null && areas.getCityid().length()>0){
				criteria.andCityidLike("%"+areas.getCityid()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbAreas> lists = areasMapper.selectByExample(example);
        PageInfo<TbAreas> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
