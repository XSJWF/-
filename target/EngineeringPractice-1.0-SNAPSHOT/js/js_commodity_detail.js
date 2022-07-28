$(document).ready(function () {
    let data_detail = JSON.parse(sessionStorage.getItem("data"));
    $("#id").text(data_detail["shangpin_id"]);
    $("#tupian1").attr("src",data_detail["shangpin_tupian"]);
    $("#tupian").val(data_detail["shangpin_tupian"]);
    $("#mingcheng").val(data_detail["shangpin_mingcheng"]);
    $("#addtime").val(data_detail["shangpin_addtime"]);
    $("#shichangjia").val(data_detail["shangpin_shichangjia"]);
    $("#cishu").val(data_detail["shangpin_cishu"]);
    if(data_detail["shangpin_tejia"] === 1){
        $("input:radio[name='tejia'][value='yes']").attr('checked','true');
    }else {
        $("input:radio[name='tejia'][value='no']").attr('checked','true');
    }
    if (data_detail["shangpin_tuijian"] === 1){
        $("input:radio[name='tuijian'][value='yes']").attr('checked','true');
    }else {
        $("input:radio[name='tuijian'][value='no']").attr('checked','true');
    }
    $("#pinpai").val(data_detail["shangpin_pinpai"]);
    $("#huiyuanjia").val(data_detail["shangpin_huiyuanjia"]);
    $("#shuliang").val(data_detail["shangpin_shuliang"]);
    $("#xinghao").val(data_detail["shangpin_xinghao"]);
    $("#typeid").val(data_detail["shangpin_typeid"]);
    $("#type2id").val(data_detail["shangpin_type2id"]);
    $("#type3id").val(data_detail["shangpin_type3id"]);
    $("#dengji").val(data_detail["shangpin_dengji"]);
    $("#jianjie").val(data_detail["shangpin_jianjie"]);

    $("#SaveChange").click(function () {
        let tejia,tuijian;
        if ($("input[name='tejia']:checked").val() === "yes"){
            tejia = 1;
        }else {
            tejia = 0;
        }
        if ($("input[name='tuijian']:checked").val() === "yes"){
            tuijian = 1;
        }else {
            tuijian = 0;
        }
        $.ajax({
            url:"/commodity_detail_post_servlet",
            type:"post",
            data:{
                "shangpin_id":$("#id").text(),
                "shangpin_mingcheng":$("#mingcheng").val(),
                "shangpin_addtime":$("#addtime").val(),
                "shangpin_shichangjia":$("#shichangjia").val(),
                "shangpin_cishu":$("#cishu").val(),
                "shangpin_tejia":tejia,
                "shangpin_tuijian":tuijian,
                "shangpin_tupian":$("#tupian").val(),
                "shangpin_pinpai":$("#pinpai").val(),
                "shangpin_huiyuanjia":$("#huiyuanjia").val(),
                "shangpin_shuliang":$("#shuliang").val(),
                "shangpin_xinghao":$("#xinghao").val(),
                "shangpin_typeid":$("#typeid").val(),
                "shangpin_type2id":$("#type2id").val(),
                "shangpin_type3id":$("#type3id").val(),
                "shangpin_dengji":$("#dengji").val(),
                "shangpin_jianjie":$("#jianjie").val()
            },
            async:false,
            dataType:"text",
            success:function (text) {
                window.alert(text);
                window.location.href = "../background/background_commodity/commodity_list.html";
            },
            error:function (text) {
                window.alert(text);
            }
        })
    })
})