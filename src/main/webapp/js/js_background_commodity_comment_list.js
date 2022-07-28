$(document).ready(function () {
    let detail_data;
    $.ajax({
        url:"/commodity_comment_list_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            detail_data = data;
            for(let i=0;i<data.length;i++){
                strhtml+="<tr id='row_"+i+"' class='td_"+(i%2+1)+"' style='height: 25px'>";
                strhtml+="<TD width='7%'>"+data[i]["comment_id"]+"</TD>";
                strhtml+="<TD width='22%'>"+data[i]["comment_time"]+"</TD>";
                strhtml += "<TD WIDTH='7%'>"+data[i]["comment_userid"]+"</TD>";
                strhtml += "<TD WIDTH='7%'>"+data[i]["comment_spid"]+"</TD>";
                strhtml += "<TD WIDTH='10%'><div title='"+data[i]["comment_title"]+"' style='text-align: center;width: 115px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+data[i]["comment_title"]+"</div></TD>";
                strhtml+="<TD width='40%'><div title='"+data[i]["comment_content"]+"' style='width: 460px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+data[i]["comment_content"]+"</div></TD>";
                strhtml+="<TD width='7%'><a style='color: #7db5d3' id='delete_"+i+"'>删除评论</a></TD></tr>";
            }
            document.getElementById("comment_list").innerHTML = strhtml;
        },
        error:function () {
            window.alert("评论信息加载失败！")
        }
    })
    $("a").click(function () {
        let id = $(this).attr("id");
        $.ajax({
            url:"/commodity_comment_delete_servlet",
            type: "post",
            data: {
              "comment_id":detail_data[id.substring(7,id.length)]["comment_id"]
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
})