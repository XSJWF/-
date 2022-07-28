$(document).ready(function (){
    $("#user_ordersName").click(function () {
        if (sessionStorage.getItem("status") === "1"){
            window.location.href='../orders.html';
        }else{
            window.alert("您还未登陆，请先登录！");
            window.location.href='../login.html';
        }
    })
    $("#user_shoppingCartName").click(function (){
        if(sessionStorage.getItem("status") === "1"){
            window.location.href='user_shoppingcar.html';
        }else{
            window.alert("您还未登陆，请先登录！");
            window.location.href='../login.html';
        }
    })
    $("#user_messageName").click(function (){
        if(sessionStorage.getItem("status") === "1"){
            window.location.href='user_leaveword.html';
        }else{
            window.alert("您还未登陆，请先登录！");
            window.location.href='../login.html';
        }
    })
    $("#userInfo_changeName").click(function (){
        if(sessionStorage.getItem("status") === "1"){
            window.location.href='user_info.html';
        }else{
            window.alert("您还未登陆，请先登录！");
            window.location.href='../login.html';
        }
    })
    $("#user_newsName").click(function (){
        if(sessionStorage.getItem("status") === "1"){
            window.location.href='user_MessageCenter.html';
        }else{
            window.alert("您还未登陆，请先登录！");
            window.location.href='../login.html';
        }
    })
    $("#user_loginOut").click(function (){
        sessionStorage.setItem("status","0");
        sessionStorage.removeItem("username");
        window.alert("成功退出登录！");
        window.location.href='../login.html';
    })
})