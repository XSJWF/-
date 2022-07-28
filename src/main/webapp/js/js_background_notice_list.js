$(document).ready(function () {
    let detail_data;
    $.ajax({
        url:"/notice_list_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            detail_data = data;
            for(let i=0;i<data.length;i++){
                strhtml+="<tr id='row_"+i+"' class='td_"+(i%2+1)+"' style='height: 25px'>";
                strhtml+="<TD width='7%'>"+data[i]["notice_id"]+"</TD>";
                strhtml+="<TD width='15%'>"+data[i]["notice_time"]+"</TD>";
                strhtml+="<TD WIDTH='22%'><input title='"+data[i]["notice_title"]+"' value='"+data[i]["notice_title"]+"' style='border-style: none;text-align: center;width: 115px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'></TD>";
                strhtml+="<TD width='49%'><input title='"+data[i]["notice_content"]+"' value='"+data[i]["notice_content"]+"' style='border-style: none;width: 460px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'></TD>";
                strhtml+="<TD width='7%'><a style='color: #7db5d3' id='delete_"+i+"'>删除公告</a></TD></tr>";
            }
            document.getElementById("notice_list").innerHTML = strhtml;
        },
        error:function () {
            window.alert("评论信息加载失败！")
        }
    })
    $("a").click(function () {
        let id = $(this).attr("id");
        $.ajax({
            url:"/notice_delete_servlet",
            type: "post",
            data: {
                "notice_id":detail_data[id.substring(7,id.length)]["notice_id"]
            },
            async: false,
            dataType: "text",
            success:function (text) {
                document.getElementById("row_"+id.substring(7,id.length)).remove();
                console.log(text);
            },
            error:function (text) {
                window.alert(text)
            }
        })
    })
    $("span").click(function () {
        document.getElementById("notice_add_main").style.display = "";
        $("#notice_add_cancel").click(function () {
            document.getElementById("notice_add_main").style.display = "none";
        })
        $("#notice_add_confirm").click(function () {
            $.ajax({
                url:"/notice_add_servlet",
                type: "post",
                data:{
                    "notice_title": $("#notice_add_title").val(),
                    "notice_content":$("#notice_add_content").val()
                },
                async: false,
                dataType: "text",
                success:function (text) {
                    console.log(text);
                    window.location.href = "";
                },
                error:function (text) {
                    window.alert(text);
                }
            })
        })
    })
})