package [package].service;
import [path_1].[path_2].pojo.Tb[Table2];
import entity.PageResult;
import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:[Table2]的增删改查
 *
 ****/
public interface [Table2]Service {

    
	/**
     * 查询全部
     * @return
     */
    List<Tb[Table2]> findAll();

    /***
     * 增加[Table2]
     * @param [table2]
     * @return
     */
    void insert(Tb[Table2] [table2]);

    /***
     * 删除操作
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改[Table2]
     * @param [table2]
     * @return
     */
    void update(Tb[Table2] [table2]);

    /***
     * 根据ID查询[Table2]
     * @param id
     * @return
     */
   Tb[Table2] findOne(Long id);

    /***
     * 查询所有[Table2]
     * @return
     */
    PageResult findPage(Tb[Table2] [table2], int PageNo, int PageSize);

}
