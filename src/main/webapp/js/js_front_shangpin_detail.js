$(document).ready(function () {
    if (sessionStorage.getItem("status") === "1"){
        document.getElementById("nav_status").innerHTML = "<a class='style_red'>"+sessionStorage.getItem("username")+"</a><a class='style_red'></a>";
    }else {
        let target1 = "../login.html",target2 = "../register.html";
        document.getElementById("nav_status").innerHTML = "<a href="+target1+" class='style_red'>hi,���¼</a>&nbsp&nbsp<a href="+target2+" class='style_red'>���ע��</a>"
    }
    $.ajax({
        url:"/shangpin_detail_get_servlet",
        type:"post",
        data:{
            "shangpin_id":sessionStorage.getItem("shangpin_item")
        },
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            if (data["shangpin_tuijian"] === "1"){
                data["shangpin_tuijian"] = "�Ƽ�����";
            }else {
                data["shangpin_tuijian"] = "���Ƽ�����";
            }
            strhtml += "<div class='box_1'><img style='width: 500px;height: 500px' src='"+data["shangpin_tupian"]+"' alt='ͼƬ����ʧ�ܣ�'></div>";
            strhtml += "<div class='box_2'><div class='detail_head'><h1>"+data["shangpin_jianjie"]+"</h1>" ;
            strhtml += "<p class='style_red'>"+data["shangpin_tuijian"]+"</p><div class='detail_sellpoint'></div></div><div class='fcs_panel'>";
            strhtml += "<dl class='price_panel'><dt class='metatit'>�г��ۣ�</dt><dd><em class='tm_yen'>��</em>" ;
            strhtml += "<span class='tm_price'>"+data["shangpin_shichangjia"]+"</span></dd> </dl><dl class='promo_panel'><dt class='metatit'>��Ա�ۣ�</dt>";
            strhtml += "<dd> <div class=promo_price'> <em class='tm_Yen'>��</em><span id='danjia' class='tm_Price'>"+data["shangpin_huiyuanjia"]+"</span> <em class='tm_promo_type'>";
            strhtml += "<s></s>��Ա������</em></div></dd></dl></div><div class='tb_key'><div class='tb_skin'><div class='tb_sku'>";
            strhtml += "<dl class='tb_amount'><dt class='tb_metatit' style='position: absolute;top: 80px;font-size: 22px'>������</dt><dd id='J_amount'><span class='tb_amount_widget'>";
            strhtml += "<input id='shangpin_num' value='1' style='position: absolute;top: 70px;height: 40px;width: 150px;left: 80px;text-align: center;font-size: 22px' type='number' class='tb_amount_text' title='�����빺������'><span style='position: absolute;top: 80px;font-size:22px;left: 250px' class='unit'>��</span></span></dd></dl>";
            strhtml += "<div class='tb_action'><div class='tb_buy' ><a id='jiesuan' title='����˰�ť������һ��֧��' role='button'><ul class='buy_button'>��������</ul>";
            strhtml += "</a></div><div class='tb_basket'><a id='gouwuche' role='button'><ul class='basket_button'>���빺�ﳵ</ul></a>";
            strhtml += "</div></div></div></div></div></div>";
            document.getElementById("shangpin_main").innerHTML = strhtml;
        },
        error:function () {
            window.alert("��Ʒ��Ϣ����ʧ�ܣ�")
        }
    })
    $.ajax({
        url:"/shangpin_pinglun_get_servlet",
        type: "post",
        data: {
            "shangpin_id":sessionStorage.getItem("shangpin_item")
        },
        async: false,
        dataType: "json",
        success:function (data) {
            console.log(data);
            let strhtml = "";
            for (let i = 0;i < data.length;i++){
                strhtml += "<li class='gitment-comment'><div class='gitment-comment-main'><div class='gitment-comment-header'>";
                strhtml += "<a class='gitment-comment-name'>"+data[i]["comment_username"]+"</a><span>&nbsp&nbsp&nbsp"+data[i]["comment_time"]+"</span></div>";
                strhtml += "<div class='gitment-comment-body gitment-markdown'><p>"+data[i]["comment_content"]+"</p></div></div></li>";
            }
            document.getElementById("user_commentlist").innerHTML = strhtml;
        },
        error:function () {
            window.alert("�û�������Ϣ����ʧ�ܣ�");
        }
    })
    $("#pinglun").click(function () {
        $.ajax({
            url:"/shangpin_pinglun_post_servlet",
            type:"post",
            data:{
                "username":sessionStorage.getItem("username"),
                "shangpin_id":sessionStorage.getItem("shangpin_item"),
                "comment_title":$("#comment_title").val(),
                "comment_content":$("#comment_content").val(),
            },
            async:false,
            dataType:"text",
            success:function (text) {
                $("#comment_title").val("");
                $("#comment_content").val("");
                $.ajax({
                    url:"/shangpin_pinglun_get_servlet",
                    type: "post",
                    data: {
                        "shangpin_id":sessionStorage.getItem("shangpin_item")
                    },
                    async: false,
                    dataType: "json",
                    success:function (data) {
                        console.log(data);
                        let strhtml = "";
                        for (let i = 0;i < data.length;i++){
                            strhtml += "<li class='gitment-comment'><div class='gitment-comment-main'><div class='gitment-comment-header'>";
                            strhtml += "<a class='gitment-comment-name'>"+data[i]["comment_username"]+"</a><span>&nbsp&nbsp&nbsp"+data[i]["comment_time"]+"</span></div>";
                            strhtml += "<div class='gitment-comment-body gitment-markdown'><p>"+data[i]["comment_content"]+"</p></div></div></li>";
                        }
                        document.getElementById("user_commentlist").innerHTML = strhtml;
                    },
                    error:function () {
                        window.alert("�û�������Ϣ����ʧ�ܣ�");
                    }
                })
                console.log(text);
            },
            error:function (text) {
                window.alert(text);
            }
        })
    })
    $("#gouwuche").click(function () {
        $.ajax({
            url:"/shoppingcar_add_servlet",
            type:"post",
            data:{
                "username":sessionStorage.getItem("username"),
                "shangpin_id":sessionStorage.getItem("shangpin_item"),
                "shangpin_num":$("#shangpin_num").val()
            },
            async:false,
            dataType:"text",
            success:function (text) {
                window.alert(text);
                $("#shangpin_num").val("1");
            },
            error:function (text) {
                window.alert(text);
            }
        })
    })
    $("#jiesuan").click(function () {
        sessionStorage.setItem("shop_id",sessionStorage.getItem("shangpin_item"));
        let total = parseFloat($("#shangpin_num").val())*parseFloat($("#danjia").text());
        sessionStorage.setItem("total",total.toFixed(2).toString());
        window.location.href = "../orders/form_orders.html";
    })

    $("#sousuo").click(function () {
        if (document.getElementById("sousuo_value").value === ""){

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
                    sessionStorage.setItem("shangpin_list",JSON.stringify(data));
                    window.location.href = "shangpin_list.html";
                },
                error:function () {
                    window.alert("����ʧ�ܣ�")
                }
            })
        }
    })
})