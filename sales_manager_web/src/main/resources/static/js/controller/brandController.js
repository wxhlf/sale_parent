//vm 虚拟模型  === 虚拟dom == vue对象
var app = new Vue({
    el: "#app",
    data: {
        list: [], //list做数据接收,
        pageNo: 1,
        pageSize: 10,
        pages:1,
        searchEntity: {},   //用于放置搜索条件的
        entity:{},   //用于保存品牌数据
        ids:[]          //用于接受选中的id
    },
    methods: {
        //根据当前的currentPage,加载品牌列表
        searchList: function (currentPage) {
            this.pageNo=currentPage;
            //查询分页列表
            axios.post('../brand/findPage?pageNo='+currentPage+"&pageSize="+this.pageSize,this.searchEntity)
                .then(function (response) {
                    //var that=this
                    //把分页查询结果集合给list
                    app.list= response.data.list;

                    //把分页的总页数给pages
                    app.pages=response.data.pages;
                })
        },
        add:function () {
            axios.post('../brand/insert',this.entity).then(function (response) {
                if (response.data.success) {
                    app.searchList(1);
                    app.$children[0].goPage(2);
                }else {
                    alert(response.data.message);
                }
            })
        },
        //数据回显的方法
        findOne:function (id) {
            axios.get('../brand/findOne?id='+id)
                .then(function (response) {
                    app.entity=response.data; //数据回显
                })
        },
        update:function () {
            axios.post('../brand/update',this.entity)
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
            if (this.entity.id!=null) {
                this.update();
            }else{
                this.add();
            }
        },
        //删除方法
        del:function () {
            axios.post('../brand/del?ids='+this.ids)
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
