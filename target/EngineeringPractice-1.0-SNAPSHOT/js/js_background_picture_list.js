$(document).ready(function () {
    let detail_data;
    $.ajax({
        url:"/picture_list_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            detail_data = data;
            for(let i=0;i<data.length;i++){
                strhtml+="<tr id='row_"+i+"' class='td_"+(i%2+1)+"' style='height: 25px'>";
                strhtml+="<TD width='10%'>"+data[i]["picture_id"]+"</TD>";
                strhtml+="<TD width='30%'><input name='"+i+"' class='path' style='width: 80%;border-style: none' id='path_"+i+"' value='"+data[i]["picture_path"]+"' ></TD>";
                strhtml+="<TD WIDTH='30%'><input name='"+i+"' class='url' style='width: 80%;border-style: none' id='url_"+i+"' value='"+data[i]["picture_url"]+"' ></TD>";
                strhtml+="<TD width='10%'><input name='"+i+"' class='width' style='width: 80%;border-style: none' id='width_"+i+"' value='"+data[i]["picture_width"]+"'></TD>";
                strhtml+="<TD width='10%'><input name='"+i+"' class='height' style='width: 80%;border-style: none' id='height_"+i+"' value='"+data[i]["picture_height"]+"'></TD><TD width='10%'></TD></tr>";
            }
            document.getElementById("picture_list").innerHTML = strhtml;
        },
        error:function () {
            window.alert("广告信息加载失败！")
        }
    })
    $("input").blur(function () {
        $.ajax({
            url:"/picture_edit_servlet",
            type: "post",
            data: {
                "picture_id":detail_data[$(this).attr("name")]["picture_id"],
                "category":$(this).attr("class"),
                "var":$(this).val()
            },
            async: false,
            dataType: "text",
            success:function (text) {
                console.log(text);
            },
            error:function (text) {
                window.alert(text);
            }
        })
    })
})