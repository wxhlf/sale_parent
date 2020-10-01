package [package].service;
import java.util.List;
import [path_1].[path_2].pojo.Tb[Table2];

import entity.PageResult;
/**
 * [comment]服务层接口
 * @author Administrator
 *
 */
public interface [Table2]Service {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Tb[Table2]> findAll();
	
	
	/**
	 * 增加
	*/
	public void add(Tb[Table2] [table2]);
	
	
	/**
	 * 修改
	 */
	public void update(Tb[Table2] [table2]);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Tb[Table2] findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 分页+模糊搜索
	 * 参数一模糊搜索条件
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findAll(Tb[Table2] [table2], int pageNum,int pageSize);
	
}
