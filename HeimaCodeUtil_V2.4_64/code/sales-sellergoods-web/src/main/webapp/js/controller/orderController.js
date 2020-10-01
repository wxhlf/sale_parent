/*****
 * 定义一个Order控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
var app=new Vue({
    el:'#app',
    data:{
        pages:1,            //总页数
        pageNo:1,           //当前页
        pageSize:10,        //每页记录数
        list:[],            //集合数据
        searchEntity:{},    //搜索对象
        entity:{},          //增加的数据封装
        ids:[]              //复选框选中的ID       
    },
    methods:{
        //搜索方法
        searchList:function (currentPage) {
            axios.post('../order/findPage?pageNo='+currentPage+"&pageSize="+this.pageSize,this.searchEntity).then(function (response) {
                //获取数据
                app.list=response.data.list;

                //当前页
                app.pageNo=currentPage;
                app.pages=response.data.pages;
            });
        },

        //保存
        save:function () {
            //增加操作
            if(app.entity.id==null){
                app.add();
            }else{
                //修改操作
                app.update();
            }
        },

        //新增Order
        add:function () {
            axios.post('../order/insert',this.entity).then(function (response) {
                if(response.data.success){
                    //页面刷新
                    app.searchList(1);

		            //页码设置成第1页
                    app.$children[0].goPage(1);
                }else{
                    alert(response.message);
                }
            });
        },

        //修改
        update:function () {
            axios.post('../order/update',this.entity).then(function (response) {
                if(response.data.success){
                    //刷新页面
                    app.searchList(1);

		            //页码设置成第1页
                    app.$children[0].goPage(1);
                }else{
                    alert(response.message);
                }
            });
        },

        //根据ID查询
        findOne:function (id) {
            axios.get('../order/findOne?id='+id).then(function (response) {
                app.entity=response.data;
            })
        },

        //删除方法
        del:function () {
            axios.get('../order/delete?ids='+this.ids).then(function (response) {
                if(response.data.success){
                    //删除成功，刷新页面
                    app.searchList(1);

                    //清空选择的数据
                    app.ids=[];

		            //页码设置成第1页
                    app.$children[0].goPage(1);
                }else{
                    alert(response.data.message);
                }
            });
        }
    },
    created:function () {
        this.searchList(1);
    }
});
