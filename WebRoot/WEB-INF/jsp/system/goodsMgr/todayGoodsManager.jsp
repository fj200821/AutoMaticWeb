<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>今日热销</title>
    <%@ include file="../admin/head.jsp"%>
    <%--清除弹窗堆积--%>

    <script src="<%=basePath %>ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=basePath%>ckfinder/ckfinder.js"></script>
    <script type="text/javascript" src="<%=basePath%>ckfinder/ckfinder_v1.js"></script>
</head>
<body class="overflow-hidden">
<%@ include file="../admin/message.jsp" %>
<div class="wrapper preload">
    <!-- 左侧菜单-->
     <%
        request.setAttribute("PCode", "GoodsMgr");
        request.setAttribute("MenuCode", "ToDayGoodsManager");
      %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
    <div class="main-container">
        <div class="padding-md">
            <ul class="breadcrumb">
                <li><span class="primary-font"><i class="icon-home"></i></span><a href="<%=basePath%>main/index">主页</a></li>
                <li>商品</li>
                <li>今日热销</li>
            </ul>
            <div class="smart-widget widget-yellow">
                <div class="smart-widget-header">
                    今日热销
                    <span class="smart-widget-option">
                        <span class="refresh-icon-animated">
                            <i class="fa fa-circle-o-notch fa-spin"></i>
                        </span>
                        <a href="#" class="widget-toggle-hidden-option">
                            <i class="fa fa-cog"></i>
                        </a>
                        <a href="#" class="widget-collapse-option" data-toggle="collapse">
                            <i class="fa fa-chevron-up"></i>
                        </a>
	                </span>
                </div>
                <div class="smart-widget-inner">
                    <div class="smart-widget-hidden-section">
                        <ul class="widget-color-list clearfix">
                            <li style="background-color:#20232b;" data-color="widget-dark"></li>
                            <li style="background-color:#4c5f70;" data-color="widget-dark-blue"></li>
                            <li style="background-color:#23b7e5;" data-color="widget-blue"></li>
                            <li style="background-color:#2baab1;" data-color="widget-green"></li>
                            <li style="background-color:#edbc6c;" data-color="widget-yellow"></li>
                            <li style="background-color:#fbc852;" data-color="widget-orange"></li>
                            <li style="background-color:#e36159;" data-color="widget-red"></li>
                            <li style="background-color:#7266ba;" data-color="widget-purple"></li>
                            <li style="background-color:#f5f5f5;" data-color="widget-light-grey"></li>
                            <li style="background-color:#fff;" data-color="reset"></li>
                        </ul>
                    </div>
                    <div class="smart-widget-body">
                        <div class="row">
                             <div class="col-md-12">
                                <div class="block">
                                    <div class="block-title">
                                        <h4>热销商品</h4>
                                    </div>
                                    <form class="form-horizontal no-margin required-validate" id="queryForm">

                                        <div class="row" style="margin-left: 20px;">
                                            <div class="form-group">
                                                <div class="col-md-2">
                                                    <label class="control-label">数据源</label>
                                                    <select id="dataSource" name="dataSource" class="form-control input-sm">
                                                    </select>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label">关键字</label>
                                                    <input id="nameQuery"  name="goods_name" type="text" class="form-control input-sm" placeholder="关键字">
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label"></label>
                                                    <br/>
                                                    <button type="button" class=" btn btn-effect-ripple btn-primary" onclick="query();">查询</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="table-responsive">
                                        <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                            <%--<div id="toolbar" class="col-md-12" >--%>
                                                <%--<button id="btn_delete" type="button" onclick="Export();" class="btn btn-default"--%>
                                                        <%--data-toggle="tooltip" data-placement="bottom" title="导出数据量太大时，速度较慢，请耐心等待。切勿刷新页面！">--%>
                                                    <%--<span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出--%>
                                                <%--</button>--%>
                                            <%--</div>--%>
                                            <table id="baseTable" class="table table-striped table-bordered table-hover dataTable no-footer" aria-describedby="dataTables-example_info">
                                            </table>
                                        </div>
                                    </div>
                                </div>
                              </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../admin/foot.jsp"%>
</div>
</body>

<script type="application/javascript">

    var hasCollectionGoodsID = [];

    $(function () {
        loadSelectWithPara('dataSource',{pcode:'DataSource'},'code','name','<%=basePath%>Basic/SystemDict/queryDictCodeByPCode');
        $("#dataSource ").get(0).selectedIndex=0;
        hasCollectionGoodsID = ${hasCollectionGoodsID}
        query();
    });



    function Export() {
        $('#confirmModal3').modal('show');
    }

    function confirm3() {
        $('#confirmModal3').modal('hide');
        var postData = convertArray($("#queryForm").serializeArray());
        var temp = {
            biz_type:postData['dataSource'],
            goods_name:postData['goods_name']
        };
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', '<%=basePath%>Basic/GoodsMgr/GoodsManager/exportData2');
        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'goods_name');
        input4.attr('value', postData['goods_name']);
        $('body').append(form);
        form.append(input4);
        form.submit();
        $('body').remove(form);
    }

    function query() {
        var postData = convertArray($("#queryForm").serializeArray());
        initTable(postData);
    }
    
    function synchro(goods_id) {
        $("#"+goods_id+"_btn").attr('disabled',"true");
        $("#"+goods_id+"_refresh").removeAttrs("hidden");
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/GoodsMgr/GoodsManager/updateGoodsById', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{goods_id:goods_id},
            async:true,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200"){
                    showMessageModal("同步成功，最新数据具有几分钟延迟，请稍后刷新重试！");
                    query();
                } else{
                    $("#"+goods_id+"_btn").removeAttrs('disabled');
                    $("#"+goods_id+"_refresh").attr('hidden',"hidden");
                    showMessageModal(data.error);
                }
            }
        });
    }

    function collect(goods_id,goodsId) {
        $("#"+goods_id+"_btn_coll").attr('disabled',"true");
        $("#"+goods_id+"_refresh_coll").removeAttrs("hidden");
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/GoodsMgr/GoodsManager/saveUserGoods', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{goodsID:goodsId},
            async:true,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200"){
                    $("#"+goods_id+"_btn_coll").attr('hidden',"hidden");
                    $("#"+goods_id+"_btn_colled").removeAttrs("hidden");
                } else{
                    $("#"+goods_id+"_btn_coll").removeAttrs('disabled');
                    $("#"+goods_id+"_refresh_coll").attr('hidden',"hidden");
                    showMessageModal(data.error);
                }
            }
        });
    }

    function initTable(postData) {
        var queryParams = function (params) {
            if($('#baseTable').bootstrapTable('getOptions').pageNumber){
                params.pageNumber = $('#baseTable').bootstrapTable('getOptions').pageNumber;
            }else{
                params.pageNumber = 0
            }
            var temp = {
                rows: params.limit,   //页面大小
                page: params.pageNumber,  //页码
                sort: params.sort,
                order:params.order,
                goods_name:postData['goods_name'],
                biz_type:postData['dataSource'],
            };
            return temp;
        };
        $('#baseTable').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/GoodsMgr/GoodsManager/queryToDayGoods",
            method:'POST',
            striped:true,
            cache:false,
            toolbar: '#toolbar',
            pagination:true,
            contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            queryParams:queryParams,
            sidePagination:'server',
            pageSize:10,
            pageList: [2,10,25],
            showRefresh: true,
            clickToSelect: true,
            singleSelect:true,
            sortable: true,
            uniqueId: "id",
            columns: [{
                field: 'id',title: 'id',visible:false
            },{
                field: 'goods_id',title: '商品图片',align:'center',
                formatter:function(value,row,index){
                    return '<div class="user-profile-pic clearfix">' +
                        '<a href="'+row.goods_picture_url+'" target="_Blank"><img  style="height:80px;" src="'+row.goods_picture_url+'" alt=""></a>' +
                        '</div>';
                }
            },{
                field: 'biz_type',title: '数据源',align:'center',
                formatter:function(value,row,index){
                    if (row.biz_type == 1){
                        return '<span style="color: green">鲁班</span>'
                    }else{
                        return '<span style="color: red">值点/自媒体</span>'
                    }
                    // return "<a href='"+row.goods_url+"' target='_Blank'>"+row.goods_name+"</a>";
                }
            },{
                field: 'goods_name',title: '商品名称',align:'center',
                formatter:function(value,row,index){
                    return "<a href='"+row.goods_url+"' target='_Blank'>"+row.goods_name+"</a>";
                }
            },{
                field: 'goods_price',title: '价格',align:'center',
                formatter:function(value,row,index){
                    return "￥"+row.goods_price+"";
                }
            },{
                field: 'sell_num',title: '总销量',align:'center',sortable : true
            },{
                field: 'add_num',title: '今日增量',align:'center',sortable : true,
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.edit_time);
                    var editDay = t.format("yyyy-MM-dd")
                    var now = new Date();
                    var toDay = now.format("yyyy-MM-dd")
                    if(editDay != toDay){
                        return 0
                    }else{
                        if(row.add_num < 0 ){
                            return 0
                        }else{
                            return row.add_num
                        }
                    }
                }
            },{
                title: '更新时间', align:'center',sortable : true,field:'edit_time',
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.edit_time);
                    return t.format("yyyy-MM-dd hh:mm:ss");
                }
            },{
                title: '上架时间', align:'center',
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.create_time);
                    return t.format("yyyy-MM-dd hh:mm:ss");
                }
            },{
                title: '操作',align:'center',
                formatter:function(value,row,index){
                    // var is_selling = row.is_selling;
                    var collection ='';
                    if(hasCollectionGoodsID.indexOf(row.id) != -1){
                        collection ='<span id="'+row.goods_id+'_btn_colled"><a type="button" class="btn btn-default ">已收藏</a></span>';
                        collection += '<span id="'+row.goods_id+'_btn_coll" hidden><a type="button" class="btn btn-primary " onclick="collect(\''+row.goods_id+'\',\''+row.id+'\')">' +
                            '<span id ="'+row.goods_id+'_refresh_coll" hidden ><i  class="fa fa-spinner fa-spin m-right-xs" ></i></span>' +
                            '收藏'+
                            '</a></span>';
                    }else{
                        collection ='<span id="'+row.goods_id+'_btn_colled" hidden><a type="button"  class="btn btn-default ">已收藏</a></span>';
                        collection +='<span id="'+row.goods_id+'_btn_coll" > <a type="button" class="btn btn-primary " onclick="collect(\''+row.goods_id+'\',\''+row.id+'\')">' +
                            '<span id ="'+row.goods_id+'_refresh_coll" hidden ><i  class="fa fa-spinner fa-spin m-right-xs" ></i></span>' +
                            '收藏'+
                            '</a></span>';
                    }
                    var synchro = '<a type="button" id="'+row.goods_id+'_btn" class="btn btn-primary " onclick="synchro(\''+row.goods_id+'\')">' +
                        '<span id ="'+row.goods_id+'_refresh" hidden ><i  class="fa fa-spinner fa-spin m-right-xs" ></i></span>' +
                        '同步'+
                        '</a>';
                    var detail = '<a type="button" class="btn btn-primary " ' +
                        'href="<%=basePath%>Basic/GoodsMgr/ToDayGoodsDetailManager?goodsId='+ row.id + '">' +
                        '详情'+
                        '</a>';
                        //synchro+'   '+
                    return detail+'   '+collection
                }
            }]
        });

    }


    Date.prototype.format = function(format) {
        var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
        }
        return format;
    }

    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>