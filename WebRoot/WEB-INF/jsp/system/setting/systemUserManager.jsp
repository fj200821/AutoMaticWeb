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
    <title>用户管理</title>
    <%@ include file="../admin/head.jsp"%>
    <script src="<%=basePath %>bootstrap/assets/bootstrapValidator/validator/emailAddress.js"></script>
</head>
<body class="overflow-hidden">
<%@ include file="../admin/message.jsp" %>
<div class="wrapper preload">
    <!-- 左侧菜单-->
     <%
        request.setAttribute("PCode", "Setting");
        request.setAttribute("MenuCode", "UserMgr");
      %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
    <div class="main-container">
        <div class="padding-md">
            <ul class="breadcrumb">
                <li><span class="primary-font"><i class="icon-home"></i></span><a href="<%=basePath%>main/index">主页</a></li>
                <li>基础设置</li>
                <li>用户管理</li>
            </ul>
            <div class="smart-widget">
                <div class="smart-widget-header">
                    用户管理
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
                                        <h4>用户列表</h4>
                                    </div>
                                    <form class="form-horizontal no-margin required-validate" id="queryForm">
                                        <div class="row" style="margin-left: 20px;">
                                            <div class="form-group">
                                                 <div class="col-md-2">
                                                    <label class="control-label">用户名</label>
                                                    <input id="userName"  name="username" type="text" class="form-control input-sm" placeholder="用户名">
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label">角色</label>
                                                    <select id="roleId" name="roleid" class="form-control input-sm">
                                                    </select>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label">状态</label>
                                                    <select id="status" name="status" class="form-control input-sm">
                                                    </select>
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
                                            <div id="toolbar" class="col-md-12">
                                                <button id="btn_add" type="button" onclick="Add();" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                </button>
                                                <button id="btn_edit" type="button" onclick="Edit();"  class="btn btn-default">
                                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                                </button>
                                                <button id="btn_delete" type="button"onclick="Del();" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                                </button>
                                            </div>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal no-margin required-validate" id="baseForm">
                    <input type="hidden" id="userid" name="userid">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="control-label">登录名</label>
                                <input id="loginId"  name="loginId" type="text" class="form-control input-sm"
                                       required  data-bv-notempty-message="登录名不能为空" placeholder="登录名">
                            </div>
                        <div class="col-md-6">
                                <label class="control-label">密码</label>
                                <input id="password"  name="password" type="password" class="form-control input-sm">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="control-label">用户名</label>
                                <input id="firstName"  name="firstName" type="text" class="form-control input-sm"
                                       required  data-bv-notempty-message="用户名不能为空" placeholder="用户名">
                            </div>
                            <div class="col-md-6">
                                <label class="control-label">邮箱</label>
                                <input id="email"  name="email" type="text" class="form-control input-sm"
                                       required  data-bv-notempty-message="邮箱不能为空" data-bv-emailaddress-message="邮箱格式不正确" placeholder="邮箱">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="control-label">会员生效时间</label>
                                <div class="input-group date form_date col-md-12" data-date="" data-date-format="yyyy-mm-dd 00:00:00" data-link-field="effectStartDate" data-link-format="yyyy-mm-dd 00:00:00">
                                    <input class="form-control" id="showEffectStartDateTime" name="effectStartDate" size="2" type="text" value="" >
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <input type="hidden" id="effectStartDate" /><br/>
                            </div>
                            <div class="col-md-6">
                                <label class="control-label">会员失效时间</label>
                                <div class="input-group date form_date col-md-12" data-date="" data-date-format="yyyy-mm-dd 23:59:59" data-link-field="effectDate" data-link-format="yyyy-mm-dd 23:59:59">
                                    <input class="form-control" id="showEffectDateTime" name="effectDate" size="2" type="text" value="" >
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <input type="hidden" id="effectDate" /><br/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="CloseModal();">关闭</button>
                <button type="button" class="btn btn-primary" onclick="SaveModel();">保存</button>
            </div>
        </div>
    </div>
</div>
</div>
</body>

<script type="application/javascript">
    $(function () {
        loadSelect('roleId','roleid','rolename','<%=basePath%>Basic/Setting/RoleMgr/queryRoleAll');
        loadSelectWithPara('status',{pcode:'status'},'code','name','<%=basePath%>Basic/SystemDict/queryDictCodeByPCode');
        query();
    });
    
    function query() {
        var postData = convertArray($("#queryForm").serializeArray());
        initTable(postData);
    }

    function SaveModel() {
        $("#baseForm").bootstrapValidator();
        $('#baseForm').data('bootstrapValidator').validate();
        var flag = $('#baseForm').data('bootstrapValidator').isValid();
        if(!flag){
            return;
        }
        $('#baseForm').data("bootstrapValidator").resetForm();
        $("#myModal").modal("hide");
        var postData = convertArray($("#baseForm").serializeArray());
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/SystemUserMgr/save',
            dataType: "json",
            data:postData,
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("保存成功。");
                else
                    showMessageModal(data.error);
                query();
            }
        });
    }

    function Del() {
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        if(typeof (row) == "undefined" || row == null){
            showMessageModal("请选择您要删除的用户条目。");
            return;
        }
        $('#confirmModal').modal('show');
    }

    function confirm() {
        $('#confirmModal').modal('hide');
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/SystemUserMgr/delete', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{id:row.userid},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("删除成功。");
                else
                    showMessageModal(data.error);
                query();
            }
        });
    }

    function CloseModal() {
        $('#baseForm').data("bootstrapValidator").resetForm();
        $('#baseForm')[0].reset();
    }

    function Add(){
        $('#baseForm')[0].reset();
        $("#myModal").modal("show");
        $("#myModalLabel").empty();
        $("#myModalLabel").html("新增");
        $("#userid").val("");
    }

    function Edit() {
        $('#baseForm')[0].reset();
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        if (typeof (row) == "undefined" || row == null) {
            showMessageModal("请选择您要修改的用户条目。");
            return;
        }
        $("#myModal").modal("show");
        $("#myModalLabel").empty();
        $("#myModalLabel").html("修改");
        $("#userid").val(row.userid);
        $("#loginId").val(row.loginid);
        // $("#password").val("123456");
        $("#firstName").val(row.firstname);
        $("#email").val(row.email);
        var t=new Date();
        t.setTime(row.effectdate);
        $("#showEffectDateTime").val(t.format("yyyy-MM-dd hh:mm:ss"));
        var t1 = new Date();
        t1.setTime(row.effectstartdate);
        $("#showEffectStartDateTime").val(t1.format("yyyy-MM-dd hh:mm:ss"));
    }


    function initTable(postData) {
        var queryParams = function (params) {
            var temp = {
                rows: params.limit,   //页面大小
                page: params.offset,  //页码
                username:postData['username'],
                status:postData['status'],
                roleid:postData['roleid']
            };
            return temp;
        };
        $('#baseTable').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/Setting/SystemUserMgr/queryUserAll",
            method:'POST',
            striped:true,
            cache:false,
            toolbar: '#toolbar',
            pagination:true,
            contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            queryParams:queryParams,
            sidePagination:'server',
            pageSize:10,
            pageList: [2,10,25,],
            showRefresh: true,
            clickToSelect: true,
            singleSelect:true,
            uniqueId: "userid",
            columns: [{
                checkbox: true
            },{
                field: 'userid',title: 'userid',visible:false,
            },{
                field: 'rolename',title: '角色名称',align:'center'
            },{
                field: 'loginid',title: '登录名',align:'center'
            },{
                field: 'firstname',title: '姓名', align:'center'
            },{
                field: 'email',title: '邮箱', align:'center'
            },{
                title: '会员生效', align:'center',
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.effectstartdate);
                    return t.format("yyyy-MM-dd hh:mm:ss");
                }
            },{
                title: '会员到期', align:'center',
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.effectdate);

                    var now = new Date();
                    if(t - now <= 3*1000*60*60*24){
                        return  "<span style='color: red'>"+t.format("yyyy-MM-dd hh:mm:ss")+"</span>";
                    }
                    return t.format("yyyy-MM-dd hh:mm:ss");
                }
            },{
                title: '最后一次上线时间', align:'center',
                formatter:function(value,row,index){
                    var t = new Date();
                    t.setTime(row.logindatetime);
                    return t.format("yyyy-MM-dd hh:mm:ss");
                }
            },{
                field: 'statusvalue',title: '状态', align:'center',
                formatter:function(value,row,index){
                   if(row.logindatetime && !row.logoutdatetime){
                       return "<span style='color: red'>在线</span>";
                   }else{
                       return "<span style='color: slategray'>离线</span>";
                   }
                }
            },{
                field: 'status',title: '状态', visible:false
            },{
                field: 'statusvalue',title: '状态', align:'center',visible:false,
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