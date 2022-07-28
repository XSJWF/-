$(document).ready(function () {
    sessionStorage.removeItem("id");
    $.ajax({
        url:"/user_MessageCenter_servlet",
        type:"post",
        data:{
            username:sessionStorage.getItem("username")
        },
        async:false,
        dataType:"json",
        success:function (data) {
            console.log(data);
            let strhtml = "";
            let admin_id = [];
            for (let i = 0;i < data.length;i++){
                strhtml += "<table class='messagecenter_message"+((i%2)+1)+"'>";
                strhtml += "<tr><td class='messagecenter_message"+((i%2)+1)+"_admin_time'>"+data[i]["admin_time"]+"</td>";
                strhtml += "<td class='messagecenter_message"+((i%2)+1)+"_title'>"+data[i]["admin_title"]+"</td>";
                strhtml += "<td class='messagecenter_message"+((i%2)+1)+"_user_time'>"+data[i]["user_time"]+"</td>";
                strhtml += "<td title='"+data[i]["user_content"]+"' class='messagecenter_message"+((i%2)+1)+"_user_content'>"+data[i]["user_content"]+"</td>";
                strhtml += "<td title='"+data[i]["admin_content"]+"' class='messagecenter_message"+((i%2)+1)+"_admin_content'>"+data[i]["admin_content"]+"</td>";
                strhtml += "<td class='messagecenter_message"+((i%2)+1)+"_zt'><a id="+(i+1)+">"+data[i]["zt"]+"</a></td></tr></table>";
                admin_id[i] = data[i]["admin_id"];
            }
            sessionStorage.setItem("id",JSON.stringify(admin_id));
            document.getElementById("messagecenter_messageTable").innerHTML = strhtml;
        },
        error:function () {
            window.alert("消息加载失败！")
        }
    })
    $('a').click(function () {
        if ($(this).text() === "确认已读"){
            let postdata = JSON.parse(sessionStorage.getItem("id"));
            let id = $(this).attr("id");
            console.log(postdata[id-1])
            $.ajax({
                url:"/user_postMessage_servlet",
                type: "post",
                data:{
                  "admin_id": postdata[id-1]
                },
                async: false,
                dataType: "text",
                success:function (data) {
                    if (data === "已读"){

                        document.getElementById(id).innerHTML = '已读';
                    }else {
                        window.alert(data);
                    }
                },
                error:function () {
                    window.alert("确认失败！")
                }
            })
        }
    })
})