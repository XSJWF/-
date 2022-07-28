$(document).ready(function () {
    sessionStorage.removeItem("data");
    let detail_data;
    $.ajax({
        url:"/commodity_list_get_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strHtml="";
            detail_data = data;
            strHtml+="<TABLE width='100% ' border='0' cellpadding='0' cellspacing='0'>";
            for(let i=0;i<data.length;i++){
                strHtml+="<tr id='row_"+i+"' class='td_"+(i%2+1)+"' style='height: 25px'>";
                strHtml+="<TD width='30%'>"+data[i]["shangpin_id"]+"</TD>";
                strHtml+="<TD width='30%'>"+data[i]["shangpin_mingcheng"]+"</TD><TD WIDTH='20%'></TD>";
                strHtml+="<TD width='10%' class = 'span'><a id='detail_"+i+"' >查看商品详情</a></TD>";
                strHtml+="<TD width='10%'><a id='delete_"+i+"'>下架商品</a></TD></tr>";
            }
            strHtml+="</TABLE>";
            document.getElementById("commodity_list").innerHTML =strHtml;
        },
        error:function () {
            window.alert("商品信息加载失败！")
        }
    })
    $("a").click(function () {
        if ($(this).attr("id").substring(0,7) === "detail_"){
            sessionStorage.setItem("data",JSON.stringify(detail_data[$(this).attr("id").substring(7,$(this).attr("id").length)]));
            window.location.href = "commodity_detail.html";
        }else if ($(this).attr("id").substring(0,7) === "delete_"){
            document.getElementById("row_"+$(this).attr("id").substring(7,$(this).attr("id").length)).remove();
            $.ajax({
                url: "/commodity_list_delete_servlet",
                type: "post",
                data: {
                    "shangpin_id":detail_data[$(this).attr("id").substring(7,$(this).attr("id").length)]["shangpin_id"]
                },
                async: false,
                dataType: "text",
                success:function (text) {
                    window.alert(text);
                },
                error:function (text) {
                    window.alert(text);
                }
            })
        }
    })
})