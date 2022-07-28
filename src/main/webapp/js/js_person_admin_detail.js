$(document).ready(function () {
    let data_detail;
    $.ajax({
        url:"/person_admin_detail_servlet",
        type:"post",
        data:{
            "admin_name":sessionStorage.getItem("admin_name")
        },
        async:false,
        dataType:"json",
        success:function (data) {
            data_detail = data;
            $("#admin_id").text(data["person_id"]);
            $("#admin_name").val(data["person_name"]);
            $("#admin_pwd").val(data["person_pwd"]);
        },
        error:function () {
            window.alert("管理员信息加载失败！");
        }
    })
    $("input").blur(function () {
        let id = $(this).attr("id");
        let value = $(this).val();
        if (id.substring(6,id.length) === "name"){
            sessionStorage.setItem("admin_name",value);
        }
        $.ajax({
            url: "/person_admin_detail_edit_servlet",
            type: "post",
            data: {
                "category":id.substring(6,id.length),
                "person_id":data_detail["person_id"],
                "var":value
            },
            async: false,
            dataType: "text",
            success:function (text) {
                console.log(text);
            },
            error:function (text) {
                console.log(text);
            }
        })
    })
})