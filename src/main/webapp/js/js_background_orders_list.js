$(document).ready(function () {
    sessionStorage.removeItem("order");
    let detail_data;
    $.ajax({
        url:"/background_orders_list_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            detail_data = data;
            for(let i=0;i<data.length;i++){
                strhtml+="<tr id='row_"+i+"' class='td_"+(i%2+1)+"' style='height: 25px'>";
                strhtml+="<TD width='10%'>"+data[i]["order_id"]+"</TD>";
                strhtml+="<TD width='40%'>"+data[i]["order_dingdanhao"]+"</TD><TD WIDTH='10%'></TD>";
                strhtml+="<TD width='20%' class = 'span'><a id='detail_"+i+"' >查看订单详情</a></TD><TD WIDTH='10%'></TD>";
                strhtml+="<TD width='10%'><a id='delete_"+i+"'>删除订单</a></TD></tr>";
            }
            document.getElementById("orders_list").innerHTML = strhtml;
        },
        error:function () {
            window.alert("订单信息加载失败！")
        }
    })
    $("a").click(function () {
        let id = $(this).attr("id");
        if (id.substring(0,6) === "detail"){
            sessionStorage.setItem("order",JSON.stringify(detail_data[id.substring(7,id.length)]));
            window.location.href = "background_order_detail.html";
        }else if(id.substring(0,6) === "delete"){
            document.getElementById("row_"+id.substring(7,id.length)).remove();
            $.ajax({
                url:"/background_orders_delete_servlet",
                type: "post",
                data: {
                    "order_id":detail_data[id.substring(7,id.length)]["order_id"]
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
    $("#orders_sousuo").click(function () {
        $.ajax({
            url:"/background_orders_sousuo_servlet",
            type:"post",
            data:{
                "dingdanhao":$("#sousuo_value").val()
            },
            async:false,
            dataType:"json",
            success:function (data) {
                console.log(data)
                let strhtml = "";
                strhtml+="<tr id='row_"+0+"' class='td_"+(0%2+1)+"' style='height: 25px'>";
                strhtml+="<TD width='10%'>"+data[0]["order_id"]+"</TD>";
                strhtml+="<TD width='40%'>"+data[0]["order_dingdanhao"]+"</TD><TD WIDTH='10%'></TD>";
                strhtml+="<TD width='20%' class = 'span'><a id='detail_"+0+"' >查看订单详情</a></TD><TD WIDTH='10%'></TD>";
                strhtml+="<TD width='10%'><a id='delete_"+0+"'>删除订单</a></TD></tr>";
                document.getElementById("orders_list").innerHTML = strhtml;
                $("a").click(function () {
                    let id = $(this).attr("id");
                    if (id.substring(0,6) === "detail"){
                        sessionStorage.setItem("order",JSON.stringify(detail_data[0]));
                        window.location.href = "background_order_detail.html";
                    }else if(id.substring(0,6) === "delete"){
                        document.getElementById("row_"+id.substring(7,id.length)).remove();
                        $.ajax({
                            url:"/background_orders_delete_servlet",
                            type: "post",
                            data: {
                                "order_id":detail_data[0]["order_id"]
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
            },
            error:function () {
                window.alert("查找失败！")
            }
        })
    })
})