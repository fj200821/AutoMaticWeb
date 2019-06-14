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
    <title>店铺列表</title>
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
        request.setAttribute("PCode", "ShopMgr");
        request.setAttribute("MenuCode", "ShopManager");
      %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
    <div class="main-container">
        <div class="padding-md">
            <ul class="breadcrumb">
                <li><span class="primary-font"><i class="icon-home"></i></span><a href="<%=basePath%>main/index">主页</a></li>
                <li>店铺</li>
                <li>店铺列表</li>
            </ul>
            <div class="smart-widget widget-yellow">
                <div class="smart-widget-header">
                    店铺列表
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
                                        <h4>店铺列表</h4>
                                    </div>
                                    <form class="form-horizontal no-margin required-validate" id="queryForm">
                                        <div class="row" style="margin-left: 20px;">
                                            <div class="form-group">
                                                 <div class="col-md-2">
                                                    <label class="control-label">店铺ID</label>
                                                    <input id="nameQuery"  name="shop_id" type="text" class="form-control input-sm" placeholder="店铺ID">
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label"></label>
                                                    <button type="button" class="btn btn-effect-ripple btn-primary" onclick="query();">查询</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
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

        </div>
    </div>
    <%@ include file="../admin/foot.jsp"%>
</div>
</body>

<script type="application/javascript">
    $(function () {
        query();
    });
    
    function query() {
        var postData = convertArray($("#queryForm").serializeArray());
        initTable(postData);
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
                shop_id:postData['shop_id']
            };
            return temp;
        };
        $('#baseTable').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/ShopMgr/ShopManager/queryShop",
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
                field: 'shop_id',title: '店铺ID',align:'center'
            },{
                field: 'shop_url',title: '店铺链接',align:'center',
                formatter:function(value,row,index){
                    return "<a href='"+row.shop_url+"'>"+row.shop_url+"</a>";
                }
            },{
                field: 'shop_total_num',title: '总销量',align:'center',sortable : true
            },{
                field: 'shop_total_num_today',title: '当日销量',align:'center',sortable : true
            },{
                title: '创建时间', align:'center',
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.create_time);
                    return t.format("yyyy-MM-dd hh:mm:ss");
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