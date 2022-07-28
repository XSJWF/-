$(document).ready(function () {
    sessionStorage.removeItem("shangpin_item");
    let strhtml = "";
    let data = JSON.parse(sessionStorage.getItem("shangpin_list"));
    if (data){
        for (let i = 0;i < data.length;i++){
            strhtml += "<li><div class='item'>";
            strhtml += "<IMG id='shangpin_img"+data[i]["shangpin_id"]+"' name='shop_item' name='"+data[i]["shangpin_id"]+"' class='picture' src='"+data[i]["shangpin_tupian"]+"' alt='Í¼Æ¬¼ÓÔØÊ§°Ü£¡'>";
            strhtml += "<div class='price'><em>£¤</em>"+data[i]["shangpin_huiyuanjia"]+"</div>";
            strhtml += "<A id='shangpin_jianjie"+data[i]["shangpin_id"]+"' name='shop_item' name='"+data[i]["shangpin_id"]+"' class='jianjie'>"+data[i]["shangpin_jianjie"]+"</A>";
            strhtml += "</div></li>";
        }
    }
    document.getElementById("shangpin_list").innerHTML = strhtml;
    $("#sousuo").click(function () {
        if (document.getElementById("sousuo_value").value===""){

        }else {
            $.ajax({
                url:"/sousuo_detail_servlet",
                type:"post",
                data:{
                    "keyword":$("#sousuo_value").val()
                },
                async:false,
                dataType:"json",
                success:function (data) {
                    $("#sousuo_value").val("");
                    for (let i = 0;i < data.length;i++){
                        strhtml += "<li><div class='item'>";
                        strhtml += "<a><img class='picture' src='"+data[i]["shangpin_tupian"]+"' alt='Í¼Æ¬¼ÓÔØÊ§°Ü£¡'></a>";
                        strhtml += "<div class='price'><em>£¤</em>1999</div>";
                        strhtml += "<a class='jianjie'>"+data[i]["shangpin_jianjie"]+"</a>";
                        strhtml += "<a class='shopcar'>¼ÓÈë¹ºÎï³µ</a></div></li>";
                    }
                    let first = document.getElementById("shangpin_list").firstElementChild;
                    while (first) {
                        first.remove();
                        first = document.getElementById("shangpin_list").firstElementChild;
                    }
                    sessionStorage.setItem("shangpin_list",JSON.stringify(data));
                    document.getElementById("shangpin_list").innerHTML = strhtml;
                    window.location.href = "";
                },
                error:function () {
                    window.alert("ËÑË÷Ê§°Ü£¡")
                }
            })
        }
    })
    $("[name=shop_item]").click(function () {
        let id;
        if ($(this).attr("id").toString().substring(0,12) === "shangpin_img"){
            id = $(this).attr("id").toString().substring(12,$(this).attr("id").toString().length);
        }else {
            id = $(this).attr("id").toString().substring(16,$(this).attr("id").toString().length);
        }
        sessionStorage.setItem("shangpin_item",id);
        window.location.href = "shangpin_detail.html";
    })
})