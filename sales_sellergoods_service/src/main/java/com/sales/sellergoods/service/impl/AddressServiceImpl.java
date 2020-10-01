package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbAddressMapper;
import com.sales.pojo.TbAddress;
import com.sales.pojo.TbAddressExample;
//import com.pinyougou.pojo.TbAddressExample;
import com.sales.sellergoods.service.AddressService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Address的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private TbAddressMapper addressMapper;


    @Override
    public List<TbAddress> findAll() {
        return addressMapper.selectByExample(null);
    }


    /**
     * 增加Address
     * @param address
     */
    @Override
    public void insert(TbAddress address) {
        addressMapper.insert(address);
    }


    /***
     * 删除Address
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbAddressExample example = new TbAddressExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andAddressIdIn(Arrays.asList(ids));
        addressMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Address
     * @param address
     * @return
     */
    @Override
    public void update(TbAddress address) {
       addressMapper.updateByPrimaryKey(address);
    }


    /***
     * 根据ID查询Address
     * @param id
     * @return
     */
    @Override
    public TbAddress findOne(Long id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Address
     * @return
     */
    @Override
    public PageResult findPage(TbAddress address, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbAddressExample example = new TbAddressExample();
        TbAddressExample.Criteria criteria = example.createCriteria();
        if(address!=null){         
            			if(address.getUserId()!=null && address.getUserId().length()>0){
				criteria.andUserIdLike("%"+address.getUserId()+"%");
			}
			if(address.getProvinceId()!=null && address.getProvinceId().length()>0){
				criteria.andProvinceIdLike("%"+address.getProvinceId()+"%");
			}
			if(address.getCityId()!=null && address.getCityId().length()>0){
				criteria.andCityIdLike("%"+address.getCityId()+"%");
			}
			if(address.getTownId()!=null && address.getTownId().length()>0){
				criteria.andTownIdLike("%"+address.getTownId()+"%");
			}
			if(address.getMobile()!=null && address.getMobile().length()>0){
				criteria.andMobileLike("%"+address.getMobile()+"%");
			}
			if(address.getAddress()!=null && address.getAddress().length()>0){
				criteria.andAddressLike("%"+address.getAddress()+"%");
			}
			if(address.getContact()!=null && address.getContact().length()>0){
				criteria.andContactLike("%"+address.getContact()+"%");
			}
			if(address.getIsDefault()!=null && address.getIsDefault().length()>0){
				criteria.andIsDefaultLike("%"+address.getIsDefault()+"%");
			}
			if(address.getNotes()!=null && address.getNotes().length()>0){
				criteria.andNotesLike("%"+address.getNotes()+"%");
			}
			if(address.getAlias()!=null && address.getAlias().length()>0){
				criteria.andAliasLike("%"+address.getAlias()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbAddress> lists = addressMapper.selectByExample(example);
        PageInfo<TbAddress> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
