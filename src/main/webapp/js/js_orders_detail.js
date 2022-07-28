$(document).ready(function () {
    $.ajax({
        url:'/Orders_detail_servlet',
        type:'post',
        data:{
            'dingdanhao':sessionStorage.getItem("dingdanhao")
        },
        async:false,
        dataType:'json',
        success:function (data) {
            $("#orders_details_04_selfValue").text(data["dingdanhao"]);
            $("#orders_details_number").text(data["dingdanhao"]);
            $("#orders_details_subValue").text(data["zt"]);
            $("#orders_details_process_time").text(data["time"]);
            if(data["zt"] === "已发货"){
                document.getElementById("orders_details_process_img3").innerHTML = "<img id='orders_details_process_img3value' src='../img/出库.svg' alt='图标加载失败'>"
            }else if(data["zt"] === "运输中"){
                document.getElementById("orders_details_process_img3").innerHTML = "<img id='orders_details_process_img3value' src='../img/出库.svg' alt='图标加载失败'>"
                document.getElementById("orders_details_process_img4").innerHTML = "<img id='orders_details_process_img4value' src='../img/运输中实心.svg' alt='图标加载失败'>"
            }else if (data["zt"] === "已签收"){
                $("#orders_details_process_completeTime").text(data["shsj"]);
                document.getElementById("orders_details_process_img3").innerHTML = "<img id='orders_details_process_img3value' src='../img/出库.svg' alt='图标加载失败'>"
                document.getElementById("orders_details_process_img4").innerHTML = "<img id='orders_details_process_img4value' src='../img/运输中实心.svg' alt='图标加载失败'>"
                document.getElementById("orders_details_process_img5").innerHTML = "<img id='orders_details_process_img5value' src='../img/已完成.svg' alt='图标加载失败'>"
            }
        },
        error:function () {
            window.alert("订单详情获取失败！")
        }
    })
})