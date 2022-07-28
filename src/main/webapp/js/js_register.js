$(document).ready(function () {
    $("#register_submit") .click(function () {
        let pwd = /(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\W_]).{8,}/;
        let number = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
        if (document.getElementById("register_04_username").value === "") {
            window.alert("用户名不能为空！");
            $("#register_04_username").focus();
        } else if ($("#register_05_password").val() === "") {
            window.alert("密码不能为空！");
            $("#register_05_password").focus();
        } else if ($("#register_06_telephone").val() === "") {
            window.alert("电话号码不能为空！");
            document.getElementById("register_06_telephone").focus();
        } else if (!pwd.test($("#register_05_password").val())){
            window.alert("请输入正确的密码格式！（要同时包含大小写字母、数字以及特殊符号，密码长度8~15位）");
            $("#register_05_password").focus();
        } else if (!number.test($("#register_06_telephone").val())) {
            window.alert("请输入正确的手机号码！");
            document.getElementById("register_06_telephone").focus();
        } else if($("#register_10_c-password").val() !== $("#register_05_password").val()){
            window.alert("两次密码输入不一致！")
            document.getElementById("register_10_c-password").focus();
        } else {
            $.ajax({
                url: '/register_servlet',
                type: 'post',
                data: {
                    "username": $("#register_04_username").val(),
                    "password": $("#register_05_password").val(),
                    "c-password": $("#register_10_c-password").val(),
                    "telephone": $("#register_06_telephone").val(),
                    "email": $("#register_07_email").val(),
                    "question": $("#register_08_security").find('option:selected').val(),
                    "s-answer": $("#register_09_s-answer").val()
                },
                async: false,
                dataType: "text",
                success: function (flag){
                    if (flag === '1'){
                        window.alert("恭喜您！注册成功！")
                        window.location.href = 'login.html';
                    }else if (flag === '0'){
                        console.log(flag);
                        window.alert("对不起！“"+$("#register_04_username").val()+"”用户名已经被注册!")
                    }
                },
                error:function (){
                    window.alert("发生未知错误！请联系管理员！")
                }
            })
        }
    })
})