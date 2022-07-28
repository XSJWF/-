$(document).ready(function () {
    sessionStorage.removeItem("shangpin_item");
    sessionStorage.removeItem("shangpin_list");
    $.ajax({
        url:"/picture_list_servlet",
        type:"post",
        data:{},
        dataType:"json",
        async:false,
        success:function (data) {
            document.getElementById("guanggao1").setAttribute("src",data[0]["picture_path"]);
            document.getElementById("guanggao2").setAttribute("src",data[1]["picture_path"]);
            document.getElementById("guanggao3").setAttribute("src",data[2]["picture_path"]);
        },
        error:function () {
            window.alert("�ֲ�������ʧ�ܣ�");
        }
    })
    let index = 0; // ͼƬ����
    let timer;
    time();
    // ��ʱ������
    function time() {
        timer = setInterval(function () {
            index++
            let box_img = $('.box img');
            let jump_li= $('.jump li');
            if (index === box_img.length) {
                index = 0;
                // ����ͼƬ͸�����
                box_img.css("opacity", "0");
                box_img.eq(index).css("opacity", "1");

                // �����·�Բ��仯��ʽ
                jump_li.css("background-color", "white");
                jump_li.eq(index).css("background-color", "red");
            } else {
                // ����ͼƬ͸�����
                box_img.css("opacity", "0");
                box_img.eq(index).css("opacity", "1");

                // �����·�Բ��仯��ʽ
                jump_li.css("background-color", "white");
                jump_li.eq(index).css("background-color", "red");
            }

        }, 3000)
    }

    // �������ͼƬ����ʱ���Ұ�ť��ʾ
    let box = $('.box');
    box.mouseover(function() {
        $('.leftBtn').css('display','block');
        $('.rightBtn').css('display','block');
    })

    box.mouseout(function() {
        $('.leftBtn').css('display','none');
        $('.rightBtn').css('display','none');
    })

    // ������ť
    $('.leftBtn').click(function() {
        clearInterval(timer);
        let box_img = $('.box img');
        let jump_li = $('.jump li');
        if (index === 0) {
            index = box_img.length - 1;
        } else {
            index--;
        }
        // ����ͼƬ͸�����
        box_img.css("opacity", "0");
        box_img.eq(index).css("opacity", "1");

        // �����·�Բ��仯��ʽ
        jump_li.css("background-color", "white");
        jump_li.eq(index).css("background-color", "red");

        time();
    })

    // �����Ұ�ť
    $('.rightBtn').click(function () {
        clearInterval(timer);
        let box_img = $('.box img');
        let jump_li = $('.jump li');
        if (index === box_img.length - 1) {
            index = 0;
        } else {
            index++;
        }
        // ����ͼƬ͸�����
        box_img.css("opacity", "0");
        box_img.eq(index).css("opacity", "1");

        // �����·�Բ��仯��ʽ
        jump_li.css("background-color", "white");
        jump_li.eq(index).css("background-color", "red");
        time();
    })
    // ��������������ת��СԲ��
    let li = $('li');
    for (let i = 0; i <= li.length; i++) {
        li.eq(i).click(function () {
            clearInterval(timer);
            index = i;
            let box_img = $('.box img');
            let jump_li = $('.jump li');
            // ����ͼƬ͸�����
            box_img.css("opacity", "0");
            box_img.eq(index).css("opacity", "1");

            // �����·�Բ��仯��ʽ
            jump_li.css("background-color", "white");
            jump_li.eq(index).css("background-color", "red");

            time();
        })
    }
    if (sessionStorage.getItem("status") === "1"){
        document.getElementById("nav_status").innerHTML = "<a class='style_red'>"+sessionStorage.getItem("username")+"</a><a class='style_red'></a>";
    }else {
        let target1 = "../login.html",target2 = "../register.html";
        document.getElementById("nav_status").innerHTML = "<a href="+target1+" class='style_red'>hi,���¼</a>&nbsp&nbsp<a href="+target2+" class='style_red'>���ע��</a>"
    }
    $.ajax({
        url:"/Mallhomepage_commodity_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            for(let i = 0;i < data.length;i++){
                strhtml += "<li><a style='color: black' class='shangpin_item' id='"+data[i]["shangpin_id"]+"'><img class='shangpin_tupian' src='"+data[i]["shangpin_tupian"]+"' alt='ͼƬ����ʧ�ܣ�'>"+data[i]["shangpin_jianjie"]+"</a><div class='price'>��"+data[i]["shangpin_huiyuanjia"]+"</div></li>";
            }
            document.getElementById("commodity_list").innerHTML = strhtml;
        },
        error:function (){
            window.alert("��Ʒ��Ϣ����ʧ�ܣ�")
        }
    })
    $.ajax({
        url: "/commodity_category_get_servlet",
        type: "post",
        data: {},
        async: false,
        dataType: "json",
        success:function (data) {
            let strhtml = "";
            for (let i = 0;i < data[0].length;i++){
                strhtml += "<li class='nav_item'><a id='category_1_"+data[0][i]["type_id"]+"' class='shangpin_category'>"+data[0][i]["type_name"]+"</a><span class='jt'>&gt;</span><div class='sub_menu'><div class='left-menu'>";
                for(let j = 0;j < data[1].length;j++){
                    if (data[1][j]["type_id"] === data[0][i]["type_id"]){
                        strhtml += "<dl><dt><a id='category_2_"+data[1][j]["type2_id"]+"' class='shangpin_category'>"+data[1][j]["type2_name"]+"</a></dt><dd>";
                        for(let k = 0;k < data[2].length;k++) {
                           if (data[2][k]["type2_id"] === data[1][j]["type2_id"]) {
                                strhtml += "<a id='category_3_"+data[2][k]["type3_id"]+"' class='shangpin_category'>" + data[2][k]["type3_name"] + "</a>";
                           }
                        }
                    }
                    strhtml += "</dd></dl>";
                }
                strhtml += "</div></div></li>";
            }
            document.getElementById("shangpin_category").innerHTML = strhtml;
        },
        error:function () {
            window.alert("��Ʒ������Ϣ����ʧ�ܣ�")
        }
    })
    $.ajax({
        url:"/notice_list_servlet",
        type:"post",
        data:{},
        async:false,
        dataType:"json",
        success:function (data) {
            let strhtml = "";
            for(let i = 0;i < data.length;i++){
                strhtml += "<li title='"+data[i]["notice_content"]+"' style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'><a><strong>"+"��"+data[i]["notice_title"]+"��"+"</strong>"+data[i]["notice_content"]+"</a></li>";
            }
            document.getElementById("main_page_gonggao").innerHTML = strhtml;
        },
        error:function () {
            window.alert("������Ϣ����ʧ�ܣ�")
        }
    })
    $("a").click(function () {
        let category = $(this).attr("class");
        if(category === "shangpin_item"){
            let id = $(this).attr("id");
            sessionStorage.setItem("shangpin_item",id);
            window.location.href = "shangpin_detail.html";
        }else if (category === "shangpin_category"){
            $.ajax({
                url:"/sousuo_category_detail_servlet",
                type:"post",
                data:{
                    "category":$(this).attr("id").substring(9,10),
                    "keyword":$(this).attr("id").substring(11,$(this).attr("id").length)
                },
                async:false,
                dataType:"json",
                success:function (data) {
                    $("#sousuo_value").val("");
                    sessionStorage.setItem("shangpin_list",JSON.stringify(data));
                    window.location.href = "shangpin_list.html";
                },
                error:function () {
                    window.alert("�޷����ظ÷��࣡")
                }
            })
        }
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