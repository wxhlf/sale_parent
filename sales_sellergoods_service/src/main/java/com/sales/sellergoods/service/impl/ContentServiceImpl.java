package com.sales.sellergoods.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sales.mapper.TbContentMapper;
import com.sales.pojo.TbContent;
import com.sales.pojo.TbContentExample;
//import com.pinyougou.pojo.TbContentExample;
import com.sales.sellergoods.service.ContentService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Content的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;


    @Override
    public List<TbContent> findAll() {
        return contentMapper.selectByExample(null);
    }


    /**
     * 增加Content
     * @param content
     */
    @Override
    public void insert(TbContent content) {
        contentMapper.insert(content);
    }


    /***
     * 删除Content
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	TbContentExample example = new TbContentExample();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().andContentIdIn(Arrays.asList(ids));
        contentMapper.deleteByExample(example);
    }


    /***
     * 根据ID查询Content
     * @param content
     * @return
     */
    @Override
    public void update(TbContent content) {
       contentMapper.updateByPrimaryKey(content);
    }


    /***
     * 根据ID查询Content
     * @param id
     * @return
     */
    @Override
    public TbContent findOne(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有Content
     * @return
     */
    @Override
    public PageResult findPage(TbContent content, int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        if(content!=null){         
            			if(content.getTitle()!=null && content.getTitle().length()>0){
				criteria.andTitleLike("%"+content.getTitle()+"%");
			}
			if(content.getUrl()!=null && content.getUrl().length()>0){
				criteria.andUrlLike("%"+content.getUrl()+"%");
			}
			if(content.getPic()!=null && content.getPic().length()>0){
				criteria.andPicLike("%"+content.getPic()+"%");
			}
			if(content.getStatus()!=null && content.getStatus().length()>0){
				criteria.andStatusLike("%"+content.getStatus()+"%");
			}
   
        }

        //进行查询，传入条件是example
        List<TbContent> lists = contentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
