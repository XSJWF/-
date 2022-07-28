$(document).ready(function () {
    $("#tupian").blur(function () {
        document.getElementById("TP").innerHTML = "<img id='tupian1' src='"+$(this).val()+"' alt='Í¼Æ¬¼ÓÔØÊ§°Ü£¡'>";
    })
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
            url:"/commodity_add_post_servlet",
            type:"post",
            data:{
                "shangpin_mingcheng":$("#mingcheng").val(),
                "shangpin_shichangjia":$("#shichangjia").val(),
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
                window.location.href = "commodity_list.html";
            },
            error:function (text) {
                window.alert(text);
            }
        })
    })
})