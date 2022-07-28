$(document).ready(function () {
    sessionStorage.removeItem("person");
    let detail_data;
    $.ajax({
        url:"/person_user_list_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strHtml="";
            detail_data = data;
            for(let i=0;i<data.length;i++){
                strHtml+="<tr id='row_"+i+"' class='td_"+(i%2+1)+"' style='height: 25px'>";
                strHtml+="<TD width='20%'>"+data[i]["person_id"]+"</TD>";
                strHtml+="<TD width='30%'>"+data[i]["person_name"]+"</TD>";
                strHtml+="<TD width='30%' class = 'span'><a id='detail_"+i+"' >查看用户详情</a></TD>";
                strHtml+="<TD width='10%'><a id='dongjie_"+i+"'>冻结用户</a></TD>";
                strHtml+="<TD width='10%'><a id='delete_"+i+"'>删除用户</a></TD></tr>";
            }
            document.getElementById("person_list").innerHTML =strHtml;
        },
        error:function () {
            window.alert("用户列表信息加载失败！")
        }
    })
    $('a').click(function () {
        let id = $(this).attr("id");
        if (id.substring(0,6) === "detail"){
            sessionStorage.setItem("person",JSON.stringify(detail_data[id.substring(7,id.length)]));
            window.location.href = "person_user_detail.html";
        }else if(id.substring(0,7) === "dongjie"){
            if($(this).text() === "冻结用户"){
                $(this).text("解冻");
                $.ajax({
                    url:"/person_user_dongjie_servlet",
                    type: "post",
                    data: {
                        "person_id":detail_data[id.substring(8,9)]["person_id"],
                        "person_dongjie":0
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
            }else {
                $(this).text("冻结用户");
                $.ajax({
                    url:"/person_user_dongjie_servlet",
                    type: "post",
                    data: {
                        "person_id":detail_data[id.substring(8,9)]["person_id"],
                        "person_dongjie":1
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
            }
        }else if(id.substring(0,6) === "delete"){
            document.getElementById("row_"+id.substring(7,8)).remove();
            $.ajax({
                url:"/person_user_delete_servlet",
                type: "post",
                data: {
                    "person_id":detail_data[id.substring(7,8)]["person_id"],
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
        }
    })
})