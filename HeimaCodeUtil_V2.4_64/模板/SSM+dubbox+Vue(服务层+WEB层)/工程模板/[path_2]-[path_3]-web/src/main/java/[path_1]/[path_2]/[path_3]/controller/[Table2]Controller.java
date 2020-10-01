package [package].controller;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import [path_1].[path_2].pojo.Tb[Table2];
import [path_1].[path_2].[path_3].[Table2]Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:[Table2]的增删改查
 *
 ****/
@RestController
@RequestMapping(value = "/[table2]")
public class [Table2]Controller {

    @Reference
    private [Table2]Service [table2]Service;

	@RequestMapping("/findAll")
    public List<Tb[Table2]> findAll(){
        return [table2]Service.findAll();
    }

    /***
     * 增加[Table2]
     * @param [table2]
     * @return
     */
    @RequestMapping(value = "/insert")
    public Result insert(@RequestBody Tb[Table2] [table2]){
        try {
            [table2]Service.insert([table2]);
            return  new Result(true,"增加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,"增加失败！");
        }
    }


    /***
     * 删除操作
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(Long[] ids){
        try {
            [table2]Service.delete(ids);
            return new Result(true,"删除成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    /***
     * 修改操作
     * @param [table2]
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody Tb[Table2] [table2]){
        try {
            [table2]Service.update([table2]);
            return new Result(true,"修改成功！");
        } catch (Exception e) {
            return  new Result(false,"修改失败！");
        }
    }

    /***
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/findOne")
    public Tb[Table2] findOne(Long id){
        return [table2]Service.findOne(id);
    }

    /***
     * 集合查询
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody Tb[Table2] [table2], int pageNo, int pageSize) {
    	PageResult page = [table2]Service.findPage([table2], pageNo, pageSize);
        return page;
    }

}
