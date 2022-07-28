$(document).ready(function () {
    $("#leaveword_submit").click(function () {
        $.ajax({
            url:"/user_leaveword_servlet",
            type:"post",
            data:{
                username:sessionStorage.getItem("username"),
                title:$("#leaveword_03_div_background_titleText").val(),
                content:$("#leaveword_03_div_background_contentText").val()
            },
            async:false,
            dataType:"text",
            success:function (flag) {
                if (flag === "1"){
                    window.alert("留言提交成功！")
                }
            },
            error:function () {
                window.alert("留言提交失败！");
            }
        })
    })
})