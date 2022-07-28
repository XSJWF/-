$(document).ready(function () {
    $.ajax({
        url:'/user_info_get_servlet',
        type:'post',
        data:{
            'username':sessionStorage.getItem("username").toString()
        },
        async:false,
        dataType:'json',
        success:function (data) {
            $("#info_04_username").val(data["username"]);
            $("#info_05_telephone").val(data["telephone"]);
            $("#info_06_email").val(data["email"]);
            $("#info_07_QQ").val(data["qq"]);
            $("#info_08_address").val(data["dizhi"]);
            $("#info_09_postalcode").val(data["youbian"]);
            $("#info_10_truename").val(data["truename"]);
            sessionStorage.setItem("rename",data["username"]);
        },
        error:function () {
            window.alert("发生未知错误，请联系管理员！");
        }
    })
    $("#info_submit").click(function () {
        $.ajax({
            url: '/user_info_post_servlet',
            type: 'post',
            data: {
                'rename':sessionStorage.getItem("rename").toString(),
                'username':$("#info_04_username").val(),
                'telephone':$("#info_05_telephone").val(),
                'email':$("#info_06_email").val(),
                'QQ':$("#info_07_QQ").val(),
                'dizhi':$("#info_08_address").val(),
                'youbian':$("#info_09_postalcode").val(),
                'truename':$("#info_10_truename").val()
            },
            async: false,
            dataType: 'text',
            success: function (flag) {
                if (flag === "1"){
                    window.alert("修改个人信息成功！")
                    window.history.back();
                }else {
                    window.alert("修改信息失败！")
                }
            },
            error: function () {
                window.alert("发生未知错误，请联系管理员！");
            }
        })
    })
})