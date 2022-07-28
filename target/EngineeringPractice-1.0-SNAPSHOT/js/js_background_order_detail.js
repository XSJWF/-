$(document).ready(function () {
    let order = JSON.parse(sessionStorage.getItem("order"));
    $("#order_id").text(order["order_id"]);
    $("#order_dingdanhao").text(order["order_dingdanhao"]);
    $("#order_shouhuoren").text(order["order_shouhuoren"]);
    $("#order_sex").text(order["order_sex"]);
    $("#order_dizhi").text(order["order_dizhi"]);
    document.getElementById("order_dizhi").title = order["order_dizhi"];
    $("#order_youbian").text(order["order_youbian"]);
    $("#order_tel").text(order["order_tel"]);
    $("#order_shsj").text(order["order_shsj"]);
    $("#order_zffs").text(order["order_zffs"]);
    $("#order_leaveword").text(order["order_leaveword"]);
    document.getElementById("order_leaveword").title = order["order_leaveword"];
    $("#order_time").text(order["order_time"]);
    $("#order_xiadanren").text(order["order_xiadanren"]);
    $("#order_zt").val(order["order_zt"]);
    $("#order_total").text(parseFloat(order["order_total"]).toFixed(2));

    $("#SaveChange").click(function () {
        $.ajax({
            url:"/background_orders_detail_servlet",
            type:"post",
            data:{
                "order_id":order["order_id"],
                "order_zt":$("#order_zt").val()
            },
            async:false,
            dataType:"text",
            success:function (text) {
                console.log(text);
                window.location.href = "../background/background_orders/background_orders_list.html";
            },
            error:function (text) {
                console.log(text);
            }
        })
    })
})