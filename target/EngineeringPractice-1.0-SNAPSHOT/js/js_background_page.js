$(document).ready(function () {
    $.ajax({
        url:'Left_menu.html',
        type:'post',
        dataType:'html',
        data: {},
        error: function(){
            alert('error');
        },
        success:function(data){
            document.getElementById("left_navi").innerHTML = data;
        }
    });
})