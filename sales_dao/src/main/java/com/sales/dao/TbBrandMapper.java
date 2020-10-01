package com.sales.dao;

import com.sales.pojo.TbBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
//问题原因
//原因在于DAO接口添加的@Mapper并不是Spring的注解，而是ibatis的注解，并没有声明这个DAO接口作为Spring的Bean，因此Spring不能进行管理，导致注入报错。
//解决办法
//只需在DAO接口加上@Component 或者 @Repository（@Repository实际上也包含了@Component注解）注解声明为Spring的Bean即可。

//@Repository
//@Mapper
public interface TbBrandMapper {
	
	@Select("select id,name,first_char as firstChar from tb_brand")
	List<TbBrand> findAll();

	void add(TbBrand brand);

	@Select("select id,name,first_char as firstChar from tb_brand where id = #{id}")
	TbBrand findOne(Long id);

	void update(TbBrand brand);

	void dele(Long id);

//	Page<TbBrand> search(TbBrand brand);

	List<Map> findBrandList();

}
