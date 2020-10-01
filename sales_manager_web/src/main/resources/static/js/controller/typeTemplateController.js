/*****
 * 定义一个TypeTemplate控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
//注册全局组件
Vue.component('v-select', VueSelect.VueSelect);
var app=new Vue({
    el:'#app',
    data:{
        pages:1,            //总页数
        pageNo:1,           //当前页
        pageSize:10,        //每页记录数
        list:[],            //集合数据
        searchEntity:{},    //搜索对象
        entity:{brandIds:[],specIds:[],customAttributeItems:[]},          //增加的数据封装
        ids:[],              //复选框选中的ID
        brandList:[],       //品牌列表
        specList:[]         //规格列表
    },
    methods:{

        addTableRow:function(){
            this.entity.customAttributeItems.push({});
        },

        delTableRow:function (index) {
            this.entity.customAttributeItems.splice(index,1);
        },
        //加载品牌列表
        findBrandList:function () {
            axios.get('../brand/selectOptionList')
                .then(function (response) {
                    app.brandList=response.data;
                })
        },

        //加载规格列表
        findSpecList:function () {
            axios.get('../specification/selectOptionList')
                .then(function (response) {
                    app.specList=response.data;
                })
        },
        //格式化数据
        jsonToString:function (str,key) {
            var returnStr="";
            var jsonStr=JSON.parse(str);
            for(var i=0;i<jsonStr.length;i++){
                //如果i!=0,则returnStr+=",";如果i=0,则returnStr+=jsonStr[i][key]
                if (i!=0) {
                    returnStr+=",";
                }
                returnStr+=jsonStr[i][key];
            }
            return returnStr;
        },
        //搜索方法
        searchList:function (currentPage) {
            axios.post('../typeTemplate/findPage?pageNo='+currentPage+"&pageSize="+this.pageSize,this.searchEntity).then(function (response) {
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
                alert("insert start");
                app.add();
            }else{
                alert("update start");
                //修改操作
                app.update();
            }
        },

        //新增TypeTemplate
        add:function () {
            axios.post('../typeTemplate/insert',this.entity).then(function (response) {
                if(response.data.success){
                    //页面刷新
                    alert("insert success");
                    app.searchList(1);

		            //页码设置成第1页
                    app.$children[0].goPage(1);
                }else{
                    alert("insert failure");
                    alert(response.message);
                }
            });
        },

        //修改
        update:function () {
            axios.post('../typeTemplate/update',this.entity).then(function (response) {
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
            axios.get('../typeTemplate/findOne?id='+id).then(function (response) {
                app.entity=response.data;
                app.entity.brandIds=JSON.parse(app.entity.brandIds);
                app.entity.specIds=JSON.parse(app.entity.specIds);
                app.entity.customAttributeItems=JSON.parse(app.entity.customAttributeItems);
            })
        },

        //删除方法
        del:function () {
            axios.get('../typeTemplate/delete?ids='+this.ids).then(function (response) {
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
       this.findBrandList();//加载品牌列表
       this.findSpecList();    //加载规格列表
    }
});
