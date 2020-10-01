/*****
 * 定义一个ItemCat控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
var app=new Vue({
    el:'#app',
    data:{
        pages:1,            //总页数
        pageNo:1,           //当前页
        pageSize:5,        //每页记录数
        list:[],            //集合数据
        searchEntity:{},    //搜索对象
        entity:{parentId:0},          //增加的数据封装
        ids:[] ,             //复选框选中的ID
        grade:1,             //定义等级
        entity_1:{"id":0},    //顶级菜单对应的对象
        entity_2:{},            //第二级菜单对应的对象
        entity_3:{} ,             //三级菜单对应的对象
        parentId:0,
        id:0
    },
    methods:{
        //点击下一级菜单触发
        selectCat : function(itemCat) {
            this.parentId =itemCat.id;
            this.grade++; //每次点击下一级后触发
            if (this.grade == 1) {
                this.entity_2 = {};
                this.entity_3 = {};
            } else if (this.grade == 2) {
                this.entity_2 = itemCat;
                this.entity_3 = {};
            } else if (this.grade == 3) {
                this.entity_3 = itemCat;
            }
           // this.findListByParent(itemCat.id,1);//触发一级分类的查询
           //  alert("app.pageNo="+app.pageNo);
           //  alert("itemCat.id="+itemCat.id);
            this.searchList(app.pageNo,itemCat.id);
        },

        findListByParent:function (id,currentPage) {

            axios.get('../itemCat/findListByParentId?id='+id+"&pageNo="+currentPage+"&pageSize="+this.pageSize)
                .then(function (response) {
                    app.list=response.data;
                    //当前页
                    app.pageNo=currentPage;
                    app.pages=response.data.pages;
                    console.log(app.list);
                })
        },
        //搜索方法
        searchList:function (currentPage,id) {
           // alert("currentPage="+currentPage);
            if(id==null)
            {
                id=app.list[0].parentId;
               // alert("id="+id);

            }
            axios.post('../itemCat/findPage?pageNo='+currentPage+"&pageSize="+this.pageSize+"&id="+id,this.searchEntity).then(function (response) {
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

        //新增ItemCat
        add:function () {
            axios.post('../itemCat/insert',this.entity).then(function (response) {
                if(response.data.success){
                    //页面刷新
                 //   app.findListByParent(app.parentId,1);
                    app.searchList(1,app.list[0].parentId);
                    //
                    // //页码设置成第1页
                    app.$children[0].goPage(1);
                }else{
                    alert(response.message);
                }
            });
        },

        //修改
        update:function () {
            axios.post('../itemCat/update',this.entity).then(function (response) {
                if(response.data.success){
                    //刷新页面
                   // app.findListByParent(app.parentId,1);
                     app.searchList(1,app.list[0].parentId);
                    //
                    // //页码设置成第1页
                     app.$children[0].goPage(1);
                }else{
                    alert(response.message);
                }
            });
        },

        //根据ID查询
        findOne:function (id) {
            axios.get('../itemCat/findOne?id='+id).then(function (response) {
                app.entity=response.data;
            })
        },

        //删除方法
        del:function () {
            axios.get('../itemCat/delete?ids='+this.ids).then(function (response) {
                if(response.data.success){
                    //删除成功，刷新页面
                    app.searchList(1,app.list[0].parentId);
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
         this.searchList(1,0);
       // this.findListByParent(0,1);//加载顶级分类
    }
});
