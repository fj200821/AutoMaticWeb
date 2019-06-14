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
    <title>我的收藏</title>
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
        request.setAttribute("PCode", "UserMgr");
        request.setAttribute("MenuCode", "UserGoodsManager");
      %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
    <div class="main-container">
        <div class="padding-md">
            <ul class="breadcrumb">
                <li><span class="primary-font"><i class="icon-home"></i></span><a href="<%=basePath%>main/index">主页</a></li>
                <li>我的</li>
                <li>我的收藏</li>
            </ul>
            <div class="smart-widget widget-yellow">
                <div class="smart-widget-header">
                    收藏的商品
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
                                    <form class="form-horizontal no-margin required-validate" id="queryForm">
                                        <div class="row" style="margin-left: 20px;">
                                            <div class="form-group">
                                                <div class="col-md-2">
                                                    <label class="control-label">一级分类</label>
                                                    <select id="firstCategory" name="firstCategory" class="form-control input-sm">
                                                    </select>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label">二级分类</label>
                                                    <select id="secondCategory" name="secondCategory" class="form-control input-sm">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-left: 20px;">
                                            <div class="form-group">
                                                <div class="col-md-2">
                                                    <label class="control-label">关键字</label>
                                                    <input id="nameQuery"  name="goods_name" type="text" class="form-control input-sm" placeholder="关键字">
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label">上架时间</label>
                                                    <div class="input-group date form_date col-md-12" data-date="" data-date-format="yyyy-mm-dd" data-link-field="createStartDateTime" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control" id="showCreateStartDateTime" size="2" type="text" value="" >
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                    <input type="hidden" id="createStartDateTime" name="createStartDateTime"/>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label">开始时间</label>
                                                    <div class="input-group date form_date col-md-12" data-date="" data-date-format="yyyy-mm-dd 00:00:00" data-link-field="startDateTime" data-link-format="yyyy-mm-dd hh:00:00">
                                                        <input class="form-control" id="showStartDateTime" name="startDateTime" size="2" type="text" value="" >
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                    <input type="hidden" id="startDateTime" /><br/>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label">结束时间</label>
                                                    <div class="input-group date form_date col-md-12" data-date="" data-date-format="yyyy-mm-dd 23:59:59" datatime-link-field="endDateTime" datatime-link-format="yyyy-mm-dd 23:59:59">
                                                        <input class="form-control" size="2" type="text" value=""  name="endDateTime">
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                    <input type="hidden" id="endDateTime"/><br/>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label"></label>
                                                    <br/>
                                                    <button type="button" class=" btn btn-effect-ripple btn-primary" onclick="query();">查询</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="alert alert-info alert-custom alert-dismissible" role="alert">
                                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <i class="fa fa-exclamation-circle m-right-xs"></i><strong>注意!</strong> 此处时间范围是限制查询商品增量的时间范围
                                    </div>
                                    <div class="table-responsive">
                                        <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
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
            <div class="smart-widget widget-purple" hidden id ="table2">
                <div class="smart-widget-header">
                    增量明细
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
                                        <h4>增量明细列表</h4>
                                    </div>
                                    <div class="table-responsive">
                                        <div id="dataTables-example_wrapper2" class="dataTables_wrapper form-inline" role="grid">
                                            <table id="baseTableDetail" class="table table-striped table-bordered table-hover dataTable no-footer" aria-describedby="dataTables-example_info">
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
        hasCollectionGoodsID =${hasCollectionGoodsID}
        var t=new Date();
        t.setTime(t.getTime()-24*3600*1000);
        $("#startDateTime").val(t.format("yyyy-MM-dd hh:00:00"));
        $("#showStartDateTime").val(t.format("yyyy-MM-dd hh:00:00"));
        loadSelectWithPara('firstCategory',{"parentId":0},'id','name','<%=basePath%>Basic/CategoryMgr/CategoryManager/queryCategoryByParentID');
        $('#firstCategory').change(function(){
            var fistCategoryId = $(this).val();
            if(fistCategoryId==""){
                $('#secondCategory').empty();
                $('#secondCategory').append('<option selected="selected" value="">--</option>');
            }else{
                loadSelectWithPara('secondCategory',{"parentId":fistCategoryId},'id','name','<%=basePath%>Basic/CategoryMgr/CategoryManager/queryCategoryByParentID');
            }
        });
        query();
    });

    function query() {
        $("#table2").attr("hidden","hidden");
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
                    query();
                } else{
                    $("#"+goods_id+"_btn").removeAttrs('disabled');
                    $("#"+goods_id+"_refresh").attr('hidden',"hidden");
                    showMessageModal(data.error);
                }
            }
        });
    }

    function cancleCollect(goods_id,goodsId) {
        $("#"+goods_id+"_btn_coll").attr('disabled',"true");
        $("#"+goods_id+"_refresh_coll").removeAttrs("hidden");
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/GoodsMgr/GoodsManager/deleteUserGoods', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{goodsID:goodsId},
            async:true,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200"){
                    hasCollectionGoodsID.splice(hasCollectionGoodsID.indexOf(goodsId)+1,1);
                    query()
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
                firstCategory_id:postData['firstCategory'],
                secondCategory_id:postData['secondCategory'],
                goods_id:postData['goods_id'],
                goods_name:postData['goods_name'],
                createStartDateTime:postData["createStartDateTime"],
                startdatetime:postData["startDateTime"],
                enddatetime:postData["endDateTime"],
                goodsIds:hasCollectionGoodsID
            };
            return temp;
        };
        $('#baseTable').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/GoodsMgr/GoodsManager/queryGoodsForMy",
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
                field: 'goods_name',title: '商品名称',align:'center',
                formatter:function(value,row,index){
                    return "<a href='"+row.goods_url+"' target='_Blank'>"+row.goods_name+"</a>";
                }
            },{
                field: 'goods_price',title: '价格',align:'center',sortable : true,
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
                        return row.add_num
                    }
                }
            },{
                field: 'query_add_num',title: '增量',align:'center',
                formatter:function(value,row,index){
                    if(row.query_add_num <= 0){
                        return  row.query_add_num;
                    }
                    return "<span style='color: red'>"+row.query_add_num+"</span>";
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
                    collection +='<span id="'+row.goods_id+'_btn_coll" > <a type="button" class="btn btn-primary " onclick="cancleCollect(\''+row.goods_id+'\',\''+row.id+'\')">' +
                        '<span id ="'+row.goods_id+'_refresh_coll" hidden ><i  class="fa fa-spinner fa-spin m-right-xs" ></i></span>' +
                        '取消收藏'+
                        '</a></span>'
                    var synchro = '<a type="button" id="'+row.goods_id+'_btn" class="btn btn-primary " onclick="synchro(\''+row.goods_id+'\')">' +
                        '<span id ="'+row.goods_id+'_refresh" hidden ><i  class="fa fa-spinner fa-spin m-right-xs" ></i></span>' +
                        '同步'+
                        '</a>';
                    var detail = '<a type="button" class="btn btn-primary " ' +
                        'href="<%=basePath%>Basic/UserMgr/GoodsDetailManager?goodsId='+ row.id + '">' +
                        '详情'+
                        '</a>';
                    return synchro+'   '+detail+'   '+collection
                }
            }],
            onClickRow:function (row,e,field) {
                initTableDetail(row.id,postData);
                $('.success').removeClass('success');
                $(e).addClass('success');
                $("#table2").removeAttrs("hidden")
            }
        });
    }

    function initTableDetail(goods_id,postData) {
        var queryParams = function (params) {
            if($('#baseTableDetail').bootstrapTable('getOptions').pageNumber){
                params.pageNumber = $('#baseTableDetail').bootstrapTable('getOptions').pageNumber;
            }else{
                params.pageNumber = 0
            }
            var temp = {
                rows: params.limit,   //页面大小
                page: params.pageNumber,  //页码
                sort: params.sort,
                order:params.order,
                goods_id:goods_id,
                startdatetime:postData["startDateTime"],
                enddatetime:postData["endDateTime"]
            };
            return temp;
        };
        $('#baseTableDetail').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/GoodsMgr/GoodsManager/queryGoodsItems",
            method:'POST',
            striped:true,
            cache:false,
            toolbar: '#toolbar2',
            pagination:true,
            contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            queryParams:queryParams,
            sidePagination:'server',
            pageSize:10,
            pageList: [2,10,25,],
            showRefresh: true,
            clickToSelect: true,
            singleSelect:true,
            sortable: true,
            uniqueId: "id",
            columns: [{
                field: 'id',title: 'id',visible:false,
            },{
                field: 'create_time',title: '更新时间', align:'center',
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.create_time);
                    return t.format("yyyy-MM-dd hh:mm:ss");
                }
            },{
                field: 'goods_id_true',title: '商品ID',align:'center'
            },{
                field: 'sell_num',title: '总售量',align:'center'
            },{
                field: 'add_num',title: '较上次增量',align:'center'
            }],
            onClickRow:function (row,e,field) {
                $('.success').removeClass('success');
                $(e).addClass('success');
            }
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