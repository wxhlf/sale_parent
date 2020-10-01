/*****
 * 定义一个Goods控制层 controller
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
        entity:{goods:{},goodsDesc:{},itemList:[]},          //增加的数据封装
        ids:[],              //复选框选中的ID
        itemCatList1: [],
        itemCatList2: [],
        itemCatList3: [],
        brandList: [],
        specList: [],
        image_entity:{url:'',color:''}
    },
        watch: {
            //1级分类发生变化，加载2级分类
            'entity.goods.category1Id': function (newValue, oldValue) {
                //加载二级分类
                if (newValue > 0) {
                    this.getItemCatList(newValue, 2);
                }
                //清空三级分类
                this.itemCatList3 = [];
                //模版id归零
                this.entity.goods.typeTemplateId = 0;
            },
            //2级分类发生变化，加载3级分类
            'entity.goods.category2Id': function (newValue, oldValue) {
                //加载三级分类
                if (newValue > 0) {
                    this.getItemCatList(newValue, 3);
                }
                //模版id归零
                this.entity.goods.typeTemplateId = 0;
            },
            //监控3级分类变化
            'entity.goods.category3Id': function (newValue, oldValue) {
                //加载模板ID[根据ID查询当前分类，然后获取type_id(模板ID)]
                if (newValue > 0) {
                    axios.get('/itemCat/findOne?id=' + newValue).then(function (response) {
                        //获取模板ID,注意一定要初始化否则会出现赋值错误
                        app.entity.goods.typeTemplateId = response.data.typeId;
                    })
                }
            },
            //监控模板ID，如果模板ID发生变化，根据模板ID查询模板信息
            'entity.goods.typeTemplateId': function (newValue, oldValue) {

                if (newValue > 0) {
                    //根据ID查询模板数据
                    axios.get('/typeTemplate/findOne?id=' + newValue).then(function (response) {
                        //获取品牌数据
                        app.brandList = JSON.parse(response.data.brandIds);
                        //获取扩展属性
                        var id = app.getParameterByName('id');
                        if (id == null) {
                            app.entity.goodsDesc.customAttributeItems = JSON.parse(response.data.customAttributeItems);
                        }
                    })

                    //查询规格组装数据
                    axios.get('/typeTemplate/findSpecList?id=' + newValue).then(function (response) {
                        app.specList = response.data;
                    })
                }

            }

        }
    ,
    methods:{
        //搜索方法
        searchList:function (currentPage) {
            axios.post('../goods/findPage?pageNo='+currentPage+"&pageSize="+this.pageSize,this.searchEntity).then(function (response) {
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

        //新增Goods
        add:function () {
            //富文本编辑器通过editor.html()获取内容
            this.entity.goodsDesc.introduction = editor.html();
            axios.post('../goods/add',this.entity).then(function (response) {
                if(response.data.success){
                    location.href = "goods.html";
                }else{
                    alert(response.message);
                }
            });
        },

        //修改
        update:function () {
            axios.post('../goods/update',this.entity).then(function (response) {
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
            axios.get('../goods/findOne?id='+id).then(function (response) {
                app.entity=response.data;
            })
        },

        //删除方法
        del:function () {
            axios.get('../goods/delete?ids='+this.ids).then(function (response) {
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
        },
        //根据parentId取分类列表,返回给对应的'itemCatList' + grade
        getItemCatList: function (id, grade) {
            axios.get('../itemCat/findListByParentId?id=' + id).then(function (response) {
                app['itemCatList' + grade] = response.data;
            })
        },

    },
    created:function () {
        this.getItemCatList(0,1);
    }
});
