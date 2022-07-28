$(document).ready(function () {
    sessionStorage.setItem("person","");
    $("#login_person").click(function () {
        if (document.getElementById("login_person").innerText === "管理员登录"){
            $("#login_person").text("用户登录");
            sessionStorage.setItem("person","管理员");
        }else{
            $("#login_person").text("管理员登录");
            sessionStorage.setItem("person","用户");
        }
    })
    $("#login_submit") .click(function () {
        if (document.getElementById("login_04_name").value === "") {
            window.alert("账户名不能为空！");
            $("#login_04_name").focus();
        } else if ($("#login_05_password").val() === "") {
            window.alert("密码不能为空！");
            $("#login_05_password").focus();
        } else if(sessionStorage.getItem("person") === "用户" || sessionStorage.getItem("person") === "") {
            $.ajax({
                url: '/login_user_servlet',
                type: 'post',
                data: {
                    "username": $("#login_04_name").val(),
                    "password": $("#login_05_password").val()
                },
                async: false,
                dataType: "text",
                success: function (flag) {
                    if (flag === '1') {
                        sessionStorage.setItem('status', "1");
                        sessionStorage.setItem("username", $("#login_04_name").val());
                        window.alert("恭喜您！登录成功！");
                        window.location.href = 'mainpage/Mall homepage.html';
                    } else if (flag === '0') {
                        window.alert("账户【" + $("#login_04_name").val() + "】不存在，请检查账户名！");
                        window.document.getElementById("login_04_name").focus();
                    } else if (flag === '2') {
                        window.alert("密码错误！请重新输入");
                        $("#login_05_password").focus();
                    }
                },
                error: function () {
                    window.alert("发生未知错误！请联系管理员！")
                }
            })
        }else if(sessionStorage.getItem("person") === "管理员"){
            $.ajax({
                url: '/login_administrator_servlet',
                type: 'post',
                data: {
                    "adminname": $("#login_04_name").val(),
                    "password": $("#login_05_password").val()
                },
                async: false,
                dataType: "text",
                success: function (flag){
                    if (flag === '1'){
                        window.alert("恭喜您！登录成功！");
                        sessionStorage.setItem("admin_name",$("#login_04_name").val());
                        window.location.href = '../background/background_mainpage/background_page.html';
                    }else if (flag === '0'){
                        window.alert("账户【"+$("#login_04_name").val()+"】不存在，请检查账户名！");
                        window.document.getElementById("login_04_name").focus();
                    }else if(flag === '2'){
                        window.alert("密码错误！请重新输入");
                        $("#login_05_password").focus();
                    }
                },
                error:function (){
                    window.alert("发生未知错误！请联系管理员！")
                }
            })
        }
    })
})