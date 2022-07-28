$(document).ready(function () {
    let person = JSON.parse(sessionStorage.getItem("person"));
    $("#person_truename").text(person["person_truename"]);
    $("#person_name").text(person["person_name"]);
    $("#person_id").text(person["person_id"]);
    $("#person_email").text(person["person_email"]);
    $("#person_qq").text(person["person_qq"]);
    $("#person_tel").text(person["person_tel"]);
    $("#person_youbian").text(person["person_youbian"]);
    $("#person_tishi").text(person["person_tishi"]);
    $("#person_huida").text(person["person_huida"]);
    $("#person_pwd").text(person["person_pwd"]);
    $("#person_logincishu").text(person["person_logincishu"]);
    $("#person_dizhi").text(person["person_dizhi"]);
    $("#person_regtime").text(person["person_regtime"]);
    $("#person_ip").text(person["person_ip"]);
    $("#person_lastlogintime").text(person["person_lastlogintime"]);
})