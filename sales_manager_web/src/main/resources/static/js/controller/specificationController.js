//vm 虚拟模型  === 虚拟dom == vue对象
var app = new Vue({
    el: "#app",
    data: {
        list: [], //list做数据接收,
        pageNo: 1,
        pageSize: 10,
        pages:1,
        searchEntity: {},   //用于放置搜索条件的
        // entity:{},   //用于保存规格数据
        ids:[],          //用于接受选中的id
        entity:{spec:{},specOptions:[]} //接受新建或者修改的规格包装类的
    },
    methods: {
        //新增一条空白的规格选项
        addTableRow:function () {
            //
            this.entity.specOptions.push({});
        },
        //删除一条specOptions对应的规格选项数据
        delTableRow:function (index) {
            this.entity.specOptions.splice(index,1);
        },

        //根据当前的currentPage,加载规格列表
        searchList: function (currentPage) {
            this.pageNo=currentPage;
            //查询分页列表
            axios.post('../specification/findPage?pageNo='+currentPage+"&pageSize="+this.pageSize,this.searchEntity)
                .then(function (response) {
                    //var that=this
                    //把分页查询结果集合给list
                    app.list= response.data.list;

                    //把分页的总页数给pages
                    app.pages=response.data.pages;
                })
        },
        add:function () {
            axios.post('../specification/insert',this.entity).then(function (response) {
                if (response.data.success) {
                    app.searchList(1);
                }else {
                    alert(response.data.message);
                }
            })
        },
        //数据回显的方法
        findOne:function (id) {
            axios.get('../specification/findOne?id='+id)
                .then(function (response) {
                    app.entity=response.data; //数据回显
                })
        },
        update:function () {
            axios.post('../specification/update',this.entity)
                .then(function (response) {
                    if (response.data.success) {
                        //更新分页列表,让他定位到第一页
                        app.searchList(1);
                        app.$children[0].goPage(1);
                    }else{
                        alert(response.data.message);
                    }
                })
        },
        //保存按钮
        save:function () {
            if (this.entity.spec.id!=null) {
                this.update();
            }else{
                this.add();
            }
        },
        //删除方法
        del:function () {
            axios.post('../specification/delete?ids='+this.ids)
                .then(function (response) {
                    if (response.data.success) {
                        //刷新页面
                        app.searchList(1);
                        //重置分页
                        app.$children[0].goPage(1);
                        //清空ids
                        app.ids=[];
                    }else{
                        alert(response.data.message);
                    }
                })
        }

    },
    //vue完成时加载了searchList方法
    created: function () {
        this.searchList(1)
    }
});
