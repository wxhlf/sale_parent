package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbSpecificationMapper;
import com.sales.mapper.TbSpecificationOptionMapper;
import com.sales.pojo.TbSpecification;
import com.sales.pojo.TbSpecificationExample;
//import com.pinyougou.pojo.TbSpecificationExample;
import com.sales.pojo.TbSpecificationOption;
import com.sales.pojo.TbSpecificationOptionExample;
import com.sales.sellergoods.service.SpecificationService;
import entity.PageResult;
import entity.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @Autowired
    TbSpecificationOptionMapper optionMapper;//规格选项表的mapper


    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }


    /**
     * 增加Specification
     * @param specification
     */
    @Override
    public void insert(Specification specification) {
        //1.保存规格表记录
        TbSpecification spec = specification.getSpec();
        if (specification != null) {
            specificationMapper.insert(spec);  //插入新的规格
        }
        //2.维护规格和规格选项表的1:n关系 ,在循环中取出新增的规格id,将id赋给集合的每一个元素
        List<TbSpecificationOption> specOptions = specification.getSpecOptions();
        for (TbSpecificationOption option : specOptions) {
            option.setSpecId(spec.getId()); //取出保存的规格id,赋给options的每一个对象的spec_id
            optionMapper.insert(option);
        }
    }


    /***
     * 删除Specification
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        //ids传给条件对象,example
        TbSpecificationExample example = new TbSpecificationExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        //更根据条件对象删除
        specificationMapper.deleteByExample(example);
        //删除规格选项表中规格ids对应的所有记录
        TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
        optionExample.createCriteria().andSpecIdIn(Arrays.asList(ids));
        optionMapper.deleteByExample(optionExample);
    }


    /***
     * 根据ID查询Specification
     * @param specification
     * @return
     */
    @Override
    public void update(Specification specification) {

        //1.更新规格对象,使用的根据主键更新规格
        // 2.先查询出来规格id对应的规格选项列表,然后全部删除
        //3.将新的规格选项列表保存到数据库中,保存时维护规格选项和规格的关系
        TbSpecification spec = specification.getSpec();
        if (specification != null && spec!=null) {
            specificationMapper.updateByPrimaryKey(spec);
//            mapper.updateByPrimaryKey(specification);//更具主键取更新
        }
        // 2.先查询出来规格id对应的规格选项列表,然后全部删除
        TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
        optionExample.createCriteria().andSpecIdEqualTo(spec.getId());
        optionMapper.deleteByExample(optionExample);
        //3.将新的规格选项列表保存到数据库中,保存时维护规格选项和规格的关系
        List<TbSpecificationOption> specOptions = specification.getSpecOptions();
        for (TbSpecificationOption option : specOptions) {
            option.setSpecId(spec.getId()); //取出保存的规格id,赋给options的每一个对象的spec_id
            optionMapper.insert(option);
        }
    }


    /***
     * 根据ID查询Specification
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id) {
        //规格对应的记录找到,主键查询
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);

        //相关规格列表记录的全部集合全部查询来来
        TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
        optionExample.createCriteria().andSpecIdEqualTo(id);
        List<TbSpecificationOption> options = optionMapper.selectByExample(optionExample);
        //组装一个specification的包装类对象,返回
        Specification specification = new Specification();
        specification.setSpec(tbSpecification);
        specification.setSpecOptions(options);
        return specification;

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

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.SelectOptionList();
    }


}
