$(document).ready(function () {
    sessionStorage.removeItem("dingdanhao");
    $.ajax({
        url:'/Orders_servlet',
        type:'post',
        data:{
            'xiadanren':sessionStorage.getItem("username")
        },
        async:false,
        dataType:'json',
        success:function (data) {
            let strhtml = "";
            for(let i = 0;i < data.length;i++){
                console.log(data[i]);
                strhtml += "<table class='orders_0"+((i%2)+1)+"'>";
                strhtml += "<tr><td class='orders_0"+((i%2+1))+"_nav'><div class='orders_0"+((i%2)+1)+"_nav_time'>"+data[i]["time"]+"</div><div class='orders_0"+((i%2)+1)+"_nav_number'>订单号：<a class='orders_0"+((i%2)+1)+"_nav_numberValue'>"+data[i]["dingdanhao"]+"</a></div></td></tr>";
                strhtml += "<tr><td class='orders_0"+((i%2)+1)+"_leaveword'>"+data[i]["leaveword"]+"</div></td>";
                strhtml += "<td class='orders_0"+((i%2)+1)+"_connect'>"+data[i]["xiadanren"]+"</td>";
                strhtml += "<td class='orders_0"+((i%2)+1)+"_people'><img class='orders_0"+((i%2)+1)+"_person' src='../../img/个人信息.svg' alt='图标加载失败'><span class='orders_01_span' title='"+"姓名："+data[i]["shouhuoren"]+"&#10;"+"性别："+data[i]["sex"]+"&#10;"+"电话："+data[i]["tel"]+"&#10;"+"邮编："+data[i]["youbian"]+"&#10;"+"地址："+data[i]["dizhi"]+"'>"+data[i]["shouhuoren"]+"</span></td>";
                strhtml += "<td class='orders_0"+((i%2)+1)+"_price'><span class='orders_0"+((i%2)+1)+"_priceValue'>￥"+data[i]["total"]+"</span><br><span class='orders_0"+((i%2)+1)+"_priceWay'>"+data[i]["zffs"]+"</span></td>";
                strhtml += "<td class='orders_0"+((i%2)+1)+"_state'>"+data[i]["zt"]+"</td></tr></table>";
            }
            document.getElementById("orders_02_div").innerHTML = strhtml;
        },
        error:function (){
            window.alert("订单信息加载失败！")
        }
    })
    $('a').click(function () {
        sessionStorage.setItem("dingdanhao",$(this).text());
        window.location.href = "orders_detail.html";
    })
})