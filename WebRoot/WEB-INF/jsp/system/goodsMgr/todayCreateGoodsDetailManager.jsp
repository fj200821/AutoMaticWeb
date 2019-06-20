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
    <title>商品详情</title>
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
        request.setAttribute("MenuCode", "GoodsManager");
      %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
    <div class="main-container">
        <div class="padding-md">
            <ul class="breadcrumb">
                <li><span class="primary-font"><i class="icon-home"></i></span><a href="<%=basePath%>main/index">主页</a></li>
                <li>商品</li>
                <li>商品详情</li>
            </ul>
            <div class="row">
                <div class="col-lg-4">
                    <div id="toolbar">
                        <button id="btn_add" type="button" onclick="Back();" class="btn btn-default">
                            <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>返回
                        </button>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-4">
                    <div class="panel panel-default clearfix">
                        <div class="panel-body">
                            <div class="user-wrapper" style="float: left;">
                                <div class="user-avatar">
                                    <img class="small-img img-circle img-thumbnail" style="height:240px;" src="${detail.goods_picture_url}" alt="">
                                </div>
                            </div><!-- ./user-wrapper -->
                            <div class="user-detail small-img">
                                <div class="font-16">${detail.goods_name}</div>
                                <%--<small>--%>
                                    <%--<small class="freelancer-rating">--%>
                                        <%--<i class="fa fa-star text-warning"></i>--%>
                                        <%--<i class="fa fa-star text-warning"></i>--%>
                                        <%--<i class="fa fa-star text-warning"></i>--%>
                                        <%--<i class="fa fa-star text-warning"></i>--%>
                                    <%--</small>--%>
                                <%--</small>--%>
                            </div>
                        </div>
                    </div>
                </div><!-- ./col -->
                <div class="col-lg-6">
                    <div class="task-widget">
                        <div class="task-widget-body clearfix">
                            <div class="pie-chart-wrapper">
                                <div class="chart task-pie-chart line-normal" data-percent="${rate}">
                                    <h1 class="m-top-lg m-bottom-none">${rate}%</h1>
                                    <div>（增长率）</div>
                                </div>
                            </div>
                            <div class="widget-detail">
                                <small class="text-upper text-muted block font-sm">今日增长</small>
                                <h1 class="no-margin">${detail.add_num}</h1>
                            </div>
                        </div><!-- ./task-widget-body -->
                        <div class="task-widget-statatistic">
                            <ul class="clearfix">
                                <li class="bg-grey border-success">
                                    <div class="text-muted text-upper font-sm">更新时间</div>
                                    ${detail.edit_time}
                                </li>
                                <li class="bg-grey border-danger">
                                    <div class="text-muted text-upper font-sm">总销量</div>
                                    ${detail.sell_num}
                                </li>
                                <li class="bg-grey border-purple">
                                    <div class="text-muted text-upper font-sm">价格</div>
                                    ￥${detail.goods_price}
                                </li>
                            </ul><!-- ./row -->
                        </div>
                    </div><!-- ./task-widget -->
                </div><!-- ./col -->
            </div><!-- ./row -->

            <div class="smart-widget widget-purple" id ="table2">
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

    $(function () {
        $('.chart').easyPieChart({
            easing: 'easeOutBounce',
            size: '140',
            lineWidth: '7',
            barColor: '#7266ba',
            onStep: function(from, to, percent) {
                $(this.el).find('.percent').text(Math.round(percent));
            }
        });
        query();
    });


    function Back() {
        window.location.href="<%=basePath%>Basic/GoodsMgr/TodayCreateGoodsManager";
    }

    function query() {
        initTableDetail(${goodsId});
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

    function initTableDetail(goodsId) {
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
                goods_id:goodsId
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