//vm 虚拟模型  === 虚拟dom == vue对象
var app = new Vue({
    el: "#app",
    data: {
        // name:{} //接受用户信息
        name:""
    },
    methods: {

        showUser:function () {
            axios.get('../loginController/showName').then(function (response) {
                app.name = response.data.loginName;
            })
        }

    },
    //vue完成时加载了searchList方法
    created: function () {
        this.showUser();
    }
});
