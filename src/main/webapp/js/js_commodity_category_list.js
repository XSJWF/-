$(document).ready(function () {
    $("#category1_add").hide();
    $("#category2_add").hide();
    $("#category3_add").hide();
    let detail_data;
    $.ajax({
        url: "/commodity_category_get_servlet",
        type: "post",
        data: {},
        async: false,
        dataType: "json",
        success: function (data) {
            let strHtml1 = "";
            let strHtml2 = "";
            let strHtml3 = "";
            detail_data = data;
            for (let i = 0; i < data[0].length; i++) {
                strHtml1 += "<TR id='category1_row_" + i + "' class='td_" + ((i % 2) + 1) + "' style='height: 25px'>";
                strHtml1 += "<TD width='40%'>" + data[0][i]["type_id"] + "</TD>";
                strHtml1 += "<TD width='40%'><input style='width: 100%;text-align: center' id='category1_typename_" + i + "' value='" + data[0][i]["type_name"] + "'></TD>";
                strHtml1 += "<TD width='20%'><a id='category1_delete_" + i + "'>删除</a></TD></TR>";
            }
            for (let i = 0; i < data[1].length; i++) {
                strHtml2 += "<tr id='category2_row_" + i + "' class='td_" + ((i % 2) + 1) + "' style='height: 25px'>";
                strHtml2 += "<TD width='27.7777%'><input style='width: 100%;text-align: center' name='id' id='category2_typeid_" + i + "' value='" + data[1][i]["type_id"] + "'></TD>";
                strHtml2 += "<TD width='27.7777%'>" + data[1][i]["type2_id"] + "</TD>";
                strHtml2 += "<TD width='27.7777%'><input style='width: 100%;text-align: center' name='name' id='category2_type2name_" + i + "' value='" + data[1][i]["type2_name"] + "'></TD>";
                strHtml2 += "<TD width='16.6666%'><a id='category2_delete_" + i + "'>删除</a></TD></tr>";
            }
            for (let i = 0; i < data[2].length; i++) {
                strHtml3 += "<tr id='category3_row_" + i + "' class='td_" + ((i % 2) + 1) + "' style='height: 25px'>";
                strHtml3 += "<TD width='28.5714%'><input style='width: 100%;text-align: center' name='id' id='category3_type2id_" + i + "' value='" + data[2][i]["type2_id"] + "'></TD>";
                strHtml3 += "<TD width='28.5714%'>" + data[2][i]["type3_id"] + "</TD>";
                strHtml3 += "<TD width='28.5714%'><input style='width: 100%;text-align: center' name='name' id='category3_type3name_" + i + "' value='" + data[2][i]["type3_name"] + "'></TD>";
                strHtml3 += "<TD width='14.2857%'><a id='category3_delete_" + i + "'>删除</a></TD></tr>";
            }
            document.getElementById("commodity_category1_list").innerHTML = strHtml1;
            document.getElementById("commodity_category2_list").innerHTML = strHtml2;
            document.getElementById("commodity_category3_list").innerHTML = strHtml3;
        },
        error: function () {
            window.alert("商品类别信息加载失败！");
        }
    })
    $('input').blur(function () {
        let id = $(this).attr("id");
        if (id.substring(8, 9) === "1" && id.substring(10,13) !== "add") {
            $.ajax({
                url: "/commodity_category_edit_servlet",
                type: "post",
                data: {
                    "category": "",
                    "second_category": "typename",
                    "id": detail_data[0][id.substring(19, id.length)]["type_id"],
                    "var": $(this).val()
                },
                async: false,
                dataType: "text",
                success: function (text) {
                    console.log(text);
                },
                error: function (text) {
                    console.log(text);
                }
            })
        } else if (id.substring(8, 9) === "2"&& id.substring(10,13) !== "add") {
            if ($(this).attr("name") === "id") {
                $.ajax({
                    url: "/commodity_category_edit_servlet",
                    type: "post",
                    data: {
                        "category": 2,
                        "second_category": "typeid",
                        "id": detail_data[1][id.substring(17, id.length)]["type2_id"],
                        "var": $(this).val()
                    },
                    async: false,
                    dataType: "text",
                    success: function (text) {
                        console.log(text);
                    },
                    error: function (text) {
                        console.log(text);
                    }
                })
            } else {
                $.ajax({
                    url: "/commodity_category_edit_servlet",
                    type: "post",
                    data: {
                        "category": 2,
                        "second_category": "typename",
                        "id": detail_data[1][id.substring(20, id.length)]["type2_id"],
                        "var": $(this).val()
                    },
                    async: false,
                    dataType: "text",
                    success: function (text) {
                        console.log(text);
                    },
                    error: function (text) {
                        console.log(text);
                    }
                })
            }
        } else if (id.substring(8, 9) === "3"&& id.substring(10,13) !== "add") {
            if ($(this).attr("name") === "id") {
                $.ajax({
                    url: "/commodity_category_edit_servlet",
                    type: "post",
                    data: {
                        "category": 3,
                        "second_category": "typeid",
                        "id": detail_data[2][id.substring(18, id.length)]["type3_id"],
                        "var": $(this).val()
                    },
                    async: false,
                    dataType: "text",
                    success: function (text) {
                        console.log(text);
                    },
                    error: function (text) {
                        console.log(text);
                    }
                })
            } else {
                $.ajax({
                    url: "/commodity_category_edit_servlet",
                    type: "post",
                    data: {
                        "category": 3,
                        "second_category": "typename",
                        "id": detail_data[2][id.substring(20, id.length)]["type3_id"],
                        "var": $(this).val()
                    },
                    async: false,
                    dataType: "text",
                    success: function (text) {
                        console.log(text);
                    },
                    error: function (text) {
                        console.log(text);
                    }
                })
            }
        }
    })
    $('a').click(function () {
        let id = $(this).attr("id");
        if (id.substring(8, 9) === "1") {
            document.getElementById("category"+id.substring(8,9)+"_row_"+id.substring(17,id.length)).remove();
            $.ajax({
                url: "/commodity_category_delete_servlet",
                type: "post",
                data: {
                    "category": "",
                    "id": detail_data[0][id.substring(17, id.length)]["type_id"]
                },
                async: false,
                dataType: "text",
                success: function (text) {
                    console.log(text);
                },
                error: function (text) {
                    console.log(text)
                }
            })
        } else if (id.substring(8, 9) === "2") {
            document.getElementById("category"+id.substring(8,9)+"_row_"+id.substring(17,id.length)).remove();
            $.ajax({
                url: "/commodity_category_delete_servlet",
                type: "post",
                data: {
                    "category": 2,
                    "id": detail_data[1][id.substring(17, id.length)]["type2_id"]
                },
                async: false,
                dataType: "text",
                success: function (text) {
                    console.log(text);
                },
                error: function (text) {
                    console.log(text)
                }
            })
        } else if (id.substring(8, 9) === "3") {
            document.getElementById("category"+id.substring(8,9)+"_row_"+id.substring(17,id.length)).remove();
            $.ajax({
                url: "/commodity_category_delete_servlet",
                type: "post",
                data: {
                    "category": 3,
                    "id": detail_data[2][id.substring(17, id.length)]["type3_id"]
                },
                async: false,
                dataType: "text",
                success: function (text) {
                    console.log(text);
                },
                error: function (text) {
                    console.log(text)
                }
            })
        }else if (id.substring(0,3) === "add"){
            if (id.substring(4,5) === "1"){
                document.getElementById("category1_add").style.display = "";
                $("#category1_confirm_add").click(function () {
                    if($("#category1_add_typename").val() === ""){
                        window.alert("商品类别名不能为空！")
                    }else {
                        $.ajax({
                            url:"/commodity_category_add_servlet",
                            type:"post",
                            data:{
                                "category":1,
                                "typename":document.getElementById("category1_add_typename").value,
                                "typeid":""
                            },
                            async:false,
                            dataType:"text",
                            success:function (text) {
                                console.log(text);
                                window.location.href = "";
                            },
                            error:function (text) {
                                console.log(text);
                            }
                        })
                    }
                })
            }else if(id.substring(4,5) === "2"){
                console.log(23322)
                document.getElementById("category2_add").style.display="";
                $("#category2_confirm_add").click(function () {
                    if($("#category2_add_typename").val() === ""){
                        window.alert("商品类别名不能为空！")
                    }else if ($("#category2_add_typeid") === ""){
                        window.alert("商品所属类别id不能为空！")
                    }else {
                        $.ajax({
                            url:"/commodity_category_add_servlet",
                            type:"post",
                            data:{
                                "category":2,
                                "typename":document.getElementById("category2_add_typename").value,
                                "typeid":document.getElementById("category2_add_typeid").value
                            },
                            async:false,
                            dataType:"text",
                            success:function (text) {
                                console.log(text);
                                window.location.href = "";
                            },
                            error:function (text) {
                                console.log(text);
                            }
                        })
                    }
                })
            }else if(id.substring(4,5) === "3"){
                document.getElementById("category3_add").style.display="";
                $("#category3_confirm_add").click(function () {
                    if($("#category3_add_typename").val() === ""){
                        window.alert("商品类别名不能为空！")
                    }else if ($("#category3_add_typeid") === ""){
                        window.alert("商品所属类别id不能为空！")
                    }else {
                        $.ajax({
                            url:"/commodity_category_add_servlet",
                            type:"post",
                            data:{
                                "category":3,
                                "typename":document.getElementById("category3_add_typename").value,
                                "typeid":document.getElementById("category3_add_typeid").value
                            },
                            async:false,
                            dataType:"text",
                            success:function (text) {
                                console.log(text);
                                window.location.href = "";
                            },
                            error:function (text) {
                                console.log(text);
                            }
                        })
                    }
                })
            }
        }
    })
})