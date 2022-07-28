$(document).ready(function () {
    $.ajax({
        url:"/notice_list_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            for (let i = 0;i < data.length;i++){
                strhtml += "<li><div class='notice_nav'> <div style='padding-left: 5px;padding-top: 10px;width: 100px;'>公告主题：</div>";
                strhtml += "<div class='title' id='notice_title'>"+"【"+data[i]["notice_title"]+"】"+"</div><div class='time' id='notice_time'>"+data[i]["notice_time"]+"</div>";
                strhtml += "</div><div class='zhuti' id='main'><div class='notice_content'>"+data[i]["notice_content"]+"</div></div></li>";
            }
            document.getElementById("gonggao_list").innerHTML = strhtml;
        },
        error:function () {
            window.alert("公告信息加载失败！");
        }
    })
})