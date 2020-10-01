package [package].service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import [path_1].[path_2].mapper.Tb[Table2]Mapper;
import [path_1].[path_2].pojo.Tb[Table2];
import [path_1].[path_2].pojo.Tb[Table2]Example;
//import com.pinyougou.pojo.Tb[Table2]Example;
import [package].[Table2]Service;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.Arrays;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:[Table2]的增删改查
 * @date: ${date}
 *
 ****/
@Service
public class [Table2]ServiceImpl implements [Table2]Service {

    @Autowired
    private Tb[Table2]Mapper [table2]Mapper;


    @Override
    public List<Tb[Table2]> findAll() {
        return [table2]Mapper.selectByExample(null);
    }


    /**
     * 增加[Table2]
     * @param [table2]
     */
    @Override
    public void insert(Tb[Table2] [table2]) {
        [table2]Mapper.insert([table2]);
    }


    /***
     * 删除[Table2]
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
    	Tb[Table2]Example example = new Tb[Table2]Example();
		  example.createCriteria().andIdIn(Arrays.asList(ids));
    //    example.createCriteria().and[Table2]IdIn(Arrays.asList(ids));
        [table2]Mapper.deleteByExample(example);
    }


    /***
     * 根据ID查询[Table2]
     * @param [table2]
     * @return
     */
    @Override
    public void update(Tb[Table2] [table2]) {
       [table2]Mapper.updateByPrimaryKey([table2]);
    }


    /***
     * 根据ID查询[Table2]
     * @param id
     * @return
     */
    @Override
    public Tb[Table2] findOne(Long id) {
        return [table2]Mapper.selectByPrimaryKey(id);
    }

    /***
     * 使用通用Mapper查询所有[Table2]
     * @return
     */
    @Override
    public PageResult findPage(Tb[Table2] [table2], int PageNo, int PageSize) {
        //分页实现
        PageHelper.startPage(PageNo,PageSize);


        //TbBrandExample可以理解为查询条件,"不能固定为TbBrand"
     //   TbBrandExample example = new TbBrandExample();
    //    TbBrandExample.Criteria criteria = example.createCriteria();
		Tb[Table2]Example example = new Tb[Table2]Example();
        Tb[Table2]Example.Criteria criteria = example.createCriteria();
        if([table2]!=null){         
            <条件查询.String.txt>   
        }

        //进行查询，传入条件是example
        List<Tb[Table2]> lists = [table2]Mapper.selectByExample(example);
        PageInfo<Tb[Table2]> pageInfo = new PageInfo<>(lists);

        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }


}
