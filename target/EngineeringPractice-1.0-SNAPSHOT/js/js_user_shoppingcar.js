$(document).ready(function () {
    sessionStorage.removeItem("shoppingcar_id");
    sessionStorage.removeItem("total");
    $.ajax({
        url:"/shoppingcar_get_servlet",
        type:"post",
        data:{
            username:sessionStorage.getItem("username")
        },
        async:false,
        dataType:"json",
        success:function (data) {
            console.log(data);
            let strhtml = "";
            let shoppingcar_id = [];
            for (let i = 0;i < data.length;i++){
                strhtml += "<table id='shoppingcar_message"+i+"' class='shoppingcar_message"+((i%2)+1)+"'>";
                strhtml += "<tr><td class='shoppingcar_message_box'><input type='checkbox' name='goods' class='goods'></td>";
                strhtml += "<td class='shoppingcar_message"+((i%2)+1)+"_shangpin'>"+data[i]["shangpin_jianjie"]+"</td>";
                strhtml += "<td id='shoppingcar_message"+i+"_price' class='shoppingcar_message"+((i%2)+1)+"_price'>"+data[i]["shangpin_price"]+"</td>";
                strhtml += "<td id='shoppingcar_message"+i+"_num' class='shoppingcar_message"+((i%2)+1)+"_num'>"+data[i]["num"]+"</td>";
                strhtml += "<td class='shoppingcar_message"+((i%2)+1)+"_change'><span id="+i+">修改数量</span></td>";
                strhtml += "<td id='shoppingcar_message"+i+"_total' class='price shoppingcar_message"+((i%2)+1)+"_total'>"+(data[i]["shangpin_price"]*data[i]["num"]).toFixed(2)+"</td>";
                strhtml += "<td class='delete shoppingcar_message"+((i%2)+1)+"_delete' id='delete"+i+"'>移除</td></tr></table>";
                shoppingcar_id[i] = data[i]["shoppingcar_id"];
            }
            sessionStorage.setItem("shoppingcar_id",JSON.stringify(shoppingcar_id));
            document.getElementById("shoppingcar_messageTable").innerHTML = strhtml;
        },
        error:function () {
            window.alert("购物车信息加载失败！")
        }
    })
    $('span').click(function () {
        let id = $(this).attr("id");
        let pnum = $("#shoppingcar_message" + id + "_num").text();
        document.getElementById("shoppingcar_message" + id + "_num").innerHTML = "<div class='change_background'><label for='change_num"+id+"'></label><input type='number' id='change_num"+id+"' class='change_num'><button id='change_confirm"+id+"' class='change_confirm'>确认修改</button></div>";
        $("#change_num"+id).val(pnum);
        $("#change_confirm"+id).click(function () {
            let num = $("#change_num"+id).val();
            document.getElementById("shoppingcar_message"+ id +"_total").innerHTML = (document.getElementById("change_num"+id).value*document.getElementById("shoppingcar_message"+ id +"_price").innerText).toFixed(2).toString();
            document.getElementById("shoppingcar_message" + id + "_num").innerHTML = document.getElementById("change_num"+id).value;
            $.ajax({
                url:"/shoppingcar_post_servlet",
                type: "post",
                data: {
                    "shoppingcar_id":JSON.parse(sessionStorage.getItem("shoppingcar_id"))[id],
                    "num":num
                },
                async: false,
                dataType: "text",
                success:function (data) {
                    console.log(data);
                },
                error:function (data) {
                    console.log(data);
                }
            })
        })
    })
    $(".delete").click(function () {
        let id = $(this).attr("id");
        console.log(id);
        id = id.charAt(id.length - 1);
        price = [].slice.apply(price);
        price.splice(id,1);
        document.getElementById("shoppingcar_message"+id).remove();
        $.ajax({
            url:"/shoppingcar_delete_servlet",
            type:"post",
            data:{
                shoppingcar_id: JSON.parse(sessionStorage.getItem("shoppingcar_id"))[id]
            },
            async:false,
            dataType:"text",
            success:function (text) {
                console.log(text);
            },
            error:function (text) {
                console.log(text);
            }
        })
    })
    $("#shoppingcar_message_clear").click(function () {
        document.getElementById("shoppingcar_messageTable").innerHTML = "";
        $.ajax({
            url:"/shoppingcar_clear_servlet",
            type:"post",
            data:{
                username:sessionStorage.getItem("username")
            },
            async:false,
            dataType:"text",
            success:function (text) {
                window.alert(text);
            },
            error:function (text) {
                window.alert(text);
            }
        })
    })
    let chose_all = document.getElementById('choseAll');
    let inputList = document.getElementsByName('goods');
    let price = document.querySelectorAll('.price');
    let submit = document.querySelector('#submit');
    //全选或者取消全选
    chose_all.onclick = function(){
        for (let i = 0; i < inputList.length; i++) {
            inputList[i].checked = this.checked;
        }
    }
    //单选对全选的影响
    let flag = false;
    for (let i = 0; i < inputList.length; i++) {
        inputList[i].onclick = function(){
            for (let i = 0; i < inputList.length; i++) {
                if(inputList[i].checked === true){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
            chose_all.checked = flag;
        }
    }
    //对最后金额的计算
    submit.onclick = function(){
        let sum = 0.0;
        let shop_id = [];
        let j = 0;
        let flag = 0;
        for (let i = 0; i < inputList.length; i++) {
            if(inputList[i].checked === true){
                sum += parseFloat(price[i].innerText);
                shop_id[j++] = JSON.parse(sessionStorage.getItem("shoppingcar_id"))[i];
                flag++;
            }
        }
        if (flag === 0){
            window.alert("当前未选中任何商品！")
        }else {
            sessionStorage.setItem("shop_id",JSON.stringify(shop_id));
            sessionStorage.setItem("total",sum.toFixed(2).toString());
            window.location.href = "../orders/form_orders.html";
        }
    }
})