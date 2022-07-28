$(document).ready(function () {
    let detail_data;
    sessionStorage.removeItem("id");
    $.ajax({
        url:"/admin_user_leaveword_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            detail_data = data;
            let strhtml = "";
            for (let i = 0;i < data.length;i++){
                strhtml += "<tr id='row_"+i+"' class='td_"+(i%2+1)+"' style='height: 25px'>";
                strhtml += "<TD WIDTH='15%'>"+data[i]["user_time"]+"</TD>";
                strhtml += "<TD WIDTH='10%'>"+data[i]["user_title"]+"</TD>";
                strhtml += "<TD WIDTH='15%'>"+data[i]["user_name"]+"</TD>";
                strhtml += "<TD WIDTH='50%'><div title='"+data[i]["user_content"]+"' style='width: 560px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+data[i]["user_content"]+"</div></TD>";
                strhtml += "<TD WIDTH='10%'><span class='huifu' id="+i+">"+data[i]["zt"]+"</span></TD></tr></table>";
            }
            document.getElementById("leaveword_list").innerHTML = strhtml;
        },
        error:function () {
            window.alert("留言加载失败！")
        }
    })
    $('span').click(function () {
        document.getElementById("leaveword_list_add").style.display = "";
        let id = $(this).attr("id");
        $("#leaveword_list_add_cancel").click(function () {
            document.getElementById("leaveword_list_add").style.display = "none";
        })
        $("#leaveword_list_add_confirm").click(function () {
            $.ajax({
                url:"/admin_huifu_servlet",
                type: "post",
                data:{
                    "user_id": detail_data[id]["user_id"],
                    "leaveword_id": detail_data[id]["leaveword_id"],
                    "admin_title": $("#leaveword_list_add_title").val(),
                    "admin_content":$("#leaveword_list_add_content").val()
                },
                async: false,
                dataType: "text",
                success:function (data) {
                    document.getElementById("leaveword_list_add").style.display = "none";
                    document.getElementById("row_"+id).remove();
                    console.log(data);
                },
                error:function () {
                    window.alert(data)
                }
            })
        })
    })
})