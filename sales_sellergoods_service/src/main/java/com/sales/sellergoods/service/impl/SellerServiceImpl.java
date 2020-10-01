package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbSellerMapper;
import com.sales.pojo.TbSeller;
import com.sales.pojo.TbSellerExample;
//import com.pinyougou.pojo.TbSellerExample;
import com.sales.sellergoods.service.SellerService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/***
 *
 * @Author:itheima
 * @Description:Seller的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private TbSellerMapper sellerMapper;


    @Override
    public List<TbSeller> findAll() {
        return sellerMapper.selectByExample(null);
    }


    /**
     * 增加Seller
     * @param seller
     */
    @Override
    public void insert(TbSeller seller) {
		//注意设置默认值
		seller.setStatus("0"); //未审核
		seller.setCreateTime(new Date());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//给用户密码加密
		seller.setPassword(encoder.encode(seller.getPassword()));
    	sellerMapper.insert(seller);
    }


    /***
     * 删除Seller
     * @param ids
     */
    @Override
	public void delete(String[] ids) {
		TbSellerExample example = new TbSellerExample();
//        example.createCriteria().andIdIn(Arrays.asList(ids));
		example.createCriteria().andSellerIdIn(ids);
		sellerMapper.deleteByExample(example);
	}


    /***
     * 根据ID查询Seller
     * @param seller
     * @return
     */
    @Override
    public void update(TbSeller seller) {
       sellerMapper.updateByPrimaryKey(seller);
    }

	@Override
	public void updateStatus(String sellerId, String status) {
		TbSeller seller = sellerMapper.selectByPrimaryKey(sellerId);
		seller.setStatus(status);
		sellerMapper.updateByPrimaryKey(seller);
	}


	/***
     * 根据ID查询Seller
     * @param id
     * @return
     */
    @Override
    public TbSeller findOne(String id) {
        return sellerMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Seller
     * @return
     */
    @Override
    public PageResult findPage(TbSeller seller, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbSellerExample example = new TbSellerExample();
        TbSellerExample.Criteria criteria = example.createCriteria();
        if(seller!=null){         
            			if(seller.getSellerId()!=null && seller.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+seller.getSellerId()+"%");
			}
			if(seller.getName()!=null && seller.getName().length()>0){
				criteria.andNameLike("%"+seller.getName()+"%");
			}
			if(seller.getNickName()!=null && seller.getNickName().length()>0){
				criteria.andNickNameLike("%"+seller.getNickName()+"%");
			}
			if(seller.getPassword()!=null && seller.getPassword().length()>0){
				criteria.andPasswordLike("%"+seller.getPassword()+"%");
			}
			if(seller.getEmail()!=null && seller.getEmail().length()>0){
				criteria.andEmailLike("%"+seller.getEmail()+"%");
			}
			if(seller.getMobile()!=null && seller.getMobile().length()>0){
				criteria.andMobileLike("%"+seller.getMobile()+"%");
			}
			if(seller.getTelephone()!=null && seller.getTelephone().length()>0){
				criteria.andTelephoneLike("%"+seller.getTelephone()+"%");
			}
			if(seller.getStatus()!=null && seller.getStatus().length()>0){
				criteria.andStatusLike("%"+seller.getStatus()+"%");
			}
			if(seller.getAddressDetail()!=null && seller.getAddressDetail().length()>0){
				criteria.andAddressDetailLike("%"+seller.getAddressDetail()+"%");
			}
			if(seller.getLinkmanName()!=null && seller.getLinkmanName().length()>0){
				criteria.andLinkmanNameLike("%"+seller.getLinkmanName()+"%");
			}
			if(seller.getLinkmanQq()!=null && seller.getLinkmanQq().length()>0){
				criteria.andLinkmanQqLike("%"+seller.getLinkmanQq()+"%");
			}
			if(seller.getLinkmanMobile()!=null && seller.getLinkmanMobile().length()>0){
				criteria.andLinkmanMobileLike("%"+seller.getLinkmanMobile()+"%");
			}
			if(seller.getLinkmanEmail()!=null && seller.getLinkmanEmail().length()>0){
				criteria.andLinkmanEmailLike("%"+seller.getLinkmanEmail()+"%");
			}
			if(seller.getLicenseNumber()!=null && seller.getLicenseNumber().length()>0){
				criteria.andLicenseNumberLike("%"+seller.getLicenseNumber()+"%");
			}
			if(seller.getTaxNumber()!=null && seller.getTaxNumber().length()>0){
				criteria.andTaxNumberLike("%"+seller.getTaxNumber()+"%");
			}
			if(seller.getOrgNumber()!=null && seller.getOrgNumber().length()>0){
				criteria.andOrgNumberLike("%"+seller.getOrgNumber()+"%");
			}
			if(seller.getLogoPic()!=null && seller.getLogoPic().length()>0){
				criteria.andLogoPicLike("%"+seller.getLogoPic()+"%");
			}
			if(seller.getBrief()!=null && seller.getBrief().length()>0){
				criteria.andBriefLike("%"+seller.getBrief()+"%");
			}
			if(seller.getLegalPerson()!=null && seller.getLegalPerson().length()>0){
				criteria.andLegalPersonLike("%"+seller.getLegalPerson()+"%");
			}
			if(seller.getLegalPersonCardId()!=null && seller.getLegalPersonCardId().length()>0){
				criteria.andLegalPersonCardIdLike("%"+seller.getLegalPersonCardId()+"%");
			}
			if(seller.getBankUser()!=null && seller.getBankUser().length()>0){
				criteria.andBankUserLike("%"+seller.getBankUser()+"%");
			}
			if(seller.getBankName()!=null && seller.getBankName().length()>0){
				criteria.andBankNameLike("%"+seller.getBankName()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbSeller> lists = sellerMapper.selectByExample(example);
        PageInfo<TbSeller> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
