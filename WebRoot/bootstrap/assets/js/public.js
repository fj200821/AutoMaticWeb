function loadSelect(id,valueField,textField,uri) {
    $.ajax({
        type: 'POST',
        url: uri, //url  action是方法的名称
        dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
        async:false,
        ContentType: "application/json; charset=utf-8",
        success: function (data) {
            $('#'+id).empty();
            $('#'+id).append('<option selected="selected" value="">--</option>');
            var info;
            if(typeof(data.total) != "undefined"){
                info = data.rows;
            }else {
                info = data;
            }
            $.each(info,function (i,o) {
                var propertys = Object.keys(o);
                var value;
                var text;
                $.each(propertys,function (index,obj) {
                    if(propertys[index] == valueField){
                        value = o[propertys[index]];
                    }
                    if(propertys[index] == textField){
                        text = o[propertys[index]];
                    }
                });
                $('#'+id).append('<option value="'+value+'">'+text+'</option>');
            });
        },
        error: function (data) {
            $('#'+id).empty();
            $('#'+id).append('<option value="">--</option>');
        }
    });
}

function loadSelectWithPara(id,param,valueField,textField,uri) {
    $.ajax({
        type: 'POST',
        url: uri, //url  action是方法的名称
        dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
        async:false,
        data:param,
        ContentType: "application/json; charset=utf-8",
        success: function (data) {
            $('#'+id).empty();
            $('#'+id).append('<option selected="selected" value="">--</option>');
            var info;
            if(typeof(data.total) != "undefined"){
                info = data.rows;
            }else {
                info = data;
            }
            $.each(info,function (i,o) {
                var propertys = Object.keys(o);
                var value;
                var text;
                $.each(propertys,function (index,obj) {
                    if(propertys[index] == valueField){
                        value = o[propertys[index]];
                    }
                    if(propertys[index] == textField){
                        text = o[propertys[index]];
                    }
                });
                $('#'+id).append('<option value="'+value+'">'+text+'</option>');
            });
        },
        error: function (data) {
            $('#'+id).empty();
            $('#'+id).append('<option value="">--</option>');
        }
    });
}


function loadSelectWithParaAndNotEmpty(id,param,valueField,textField,uri) {
    $.ajax({
        type: 'POST',
        url: uri, //url  action是方法的名称
        dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
        async:false,
        data:param,
        ContentType: "application/json; charset=utf-8",
        success: function (data) {
            $('#'+id).empty();
            var info;
            if(typeof(data.total) != "undefined"){
                info = data.rows;
            }else {
                info = data;
            }
            $.each(info,function (i,o) {
                var propertys = Object.keys(o);
                var value;
                var text;
                $.each(propertys,function (index,obj) {
                    if(propertys[index] == valueField){
                        value = o[propertys[index]];
                    }
                    if(propertys[index] == textField){
                        text = o[propertys[index]];
                    }
                });
                $('#'+id).append('<option value="'+value+'">'+text+'</option>');
            });
        },
        error: function (data) {
            $('#'+id).empty();
            $('#'+id).append('<option value="">--</option>');
        }
    });
}

function showMessageModal(messgae) {
    $("#messageModal").modal("show");
    $("#messageModalDetail").empty();
    $("#messageModalDetail").html(messgae);
}


function convertArray(o) { //主要是推荐这个函数。它将jquery系列化后的值转为name:value的形式。
    var v = {};
    for (var i in o) {
        if (typeof (v[o[i].name]) == 'undefined')
            v[o[i].name] = o[i].value;
        else
            v[o[i].name] += "," + o[i].value;
    }
    return v;
}

