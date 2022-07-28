$(document).ready(function () {
    $.ajax({
        url:"/form_orders_servlet",
        type:"post",
        data:{
            username:sessionStorage.getItem("username")
        },
        async:false,
        dataType:"json",
        success:function (data) {
            $("#shouhuoren").val(data["username"]);
            $("#youbian").val(data["youbian"]);
            $("#tel").val(data["telephone"]);
            $("#dizhi").val(data["dizhi"]);
            $("#totalValue").text(sessionStorage.getItem("total")+"元");
            console.log(sessionStorage.getItem("total"));
        },
        error:function () {
            window.alert("订单填写信息加载失败！")
        }
    })
    $("#dingdan_submit").click(function () {
        if (document.getElementById("shouhuoren").value == null){
            window.alert("收货人姓名不能为空！");
            document.getElementById("shouhuoren").focus();
        }else if (document.getElementById("sex").value == null){
            window.alert("性别不能为空！");
            document.getElementById("sex").focus();
        }else if(document.getElementById("youbian").value == null){
            window.alert("邮编不能为空！");
            document.getElementById("youbian").focus();
        }else if (document.getElementById("tel").value == null){
            window.alert("电话不能为空");
            document.getElementById("tel").focus();
        }else if(document.getElementById("dizhi").value == null){
            window.alert("地址不能为空！");
            document.getElementById("dizhi").focus();
        }else {
            $.ajax({
                url: "/form_orders_post_servlet",
                type: "post",
                data: {
                    "shouhuoren": $("#shouhuoren").val(),
                    "sex": $("#sex").val(),
                    "youbian": $("#youbian").val(),
                    "tel": $("#tel").val(),
                    "dizhi": $("#dizhi").val(),
                    "leaveword": $("#leaveword").val(),
                    "total": $("#totalValue").text(),
                    "xiadanren": sessionStorage.getItem("username")
                },
                async: false,
                dataType: "text",
                success: function (text) {
                    for (let i = 0;i < JSON.parse(sessionStorage.getItem("shop_id")).length;i++){
                        $.ajax({
                            url:"/shoppingcar_delete_servlet",
                            type:"post",
                            data:{
                                shoppingcar_id: JSON.parse(sessionStorage.getItem("shop_id"))[i]
                            },
                            async:false,
                            dataType:"text",
                            success:function (text) {
                                console.log(text);
                            },
                            error:function (text) {
                                console.log(text);
                            }
                        })
                        window.history.back();
                    }
                    sessionStorage.removeItem("shop_id");
                    window.alert(text);
                },
                error: function (text) {
                    window.alert(text);
                }
            })
        }
    })
})