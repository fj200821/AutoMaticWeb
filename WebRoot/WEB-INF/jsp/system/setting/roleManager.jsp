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
    <title>角色管理</title>
    <%@ include file="../admin/head.jsp"%>
</head>
<body class="overflow-hidden">
<%@ include file="../admin/message.jsp" %>
<div class="wrapper preload">
    <!-- 左侧菜单-->
    <%
        request.setAttribute("PCode", "Setting");
        request.setAttribute("MenuCode", "RoleMgr");
    %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
    <div class="main-container">
        <div class="padding-md">
            <ul class="breadcrumb">
                <li><span class="primary-font"><i class="icon-home"></i></span><a href="<%=basePath%>main/index">主页</a></li>
                <li>基础设置</li>
                <li>角色管理</li>
            </ul>
            <div class="smart-widget">
                <div class="smart-widget-header">
                    角色管理
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
                        <div class="col-md-6">
                            <div class="block">
                                <div class="block-title">
                                    <h4>角色列表</h4>
                                </div>
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
                        <div class="col-md-6">
                            <div class="block">
                                <div class="block-title">
                                    <h4>用户列表</h4>
                                </div>
                                <div class="card-content">
                                    <div class="table-responsive">
                                        <div class="dataTables_wrapper form-inline" role="grid">
                                            <div id="toolbarUser" class="col-md-12">
                                                <button id="btn_addUser" type="button" onclick="AddUser();" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                </button>
                                                <button id="btn_deleteUser" type="button"onclick="DelUser();" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                                </button>
                                            </div>
                                            <table id="baseTableUser" class="table table-striped table-bordered table-hover dataTable no-footer" aria-describedby="dataTables-example_info">
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
                    <input id="roleid" name="roleid" type="hidden">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-xs-6">
                                <label class="control-label">编码</label>
                                <input id="roleCode"  name="roleCode" type="text" class="form-control input-sm"
                                   required  data-bv-notempty-message="编码不能为空" placeholder="编码">
                            </div>
                            <div class="col-xs-6">
                                <label class="control-label">角色名</label>
                                 <input id="roleName"  name="roleName" type="text" class="form-control input-sm"
                                   required  data-bv-notempty-message="角色名不能为空" placeholder="角色名">
                             </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                             <div class="col-xs-6">
                                <label class="control-label">排序</label>
                                <input id="seq"  name="seq" type="text" class="form-control input-sm"
                                       required  data-bv-notempty-message="排序不能为空" placeholder="排序">
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
<div class="modal fade" id="myModalUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUser" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUser"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal no-margin required-validate" id="baseFormUser">
                    <input id="roleid2" name="roleid" type="hidden">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-xs-6">
                                <label class="control-label">角色名称</label>
                                <input id="roleName2" name="roleName" type="text" class="form-control input-sm" disabled>
                            </div>
                            <div class="col-xs-6">
                                <label class="control-label">用户</label>
                                <select id="userName" name="userid" class="form-control input-sm"  required  data-bv-notempty-message="用户不能为空" >
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="CloseUserModal();">关闭</button>
                <button type="button" class="btn btn-primary" onclick="SaveUserModel();">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="application/javascript">
    $(function () {
        $('#baseTable').bootstrapTable({
            toolbar: '#toolbar',
        });
        $('#baseTableUser').bootstrapTable({
            toolbar: '#toolbarUser',
        });
        initTable();
    });

    function DelUser() {
        var row = $('#baseTableUser').bootstrapTable('getSelections');
        if(typeof (row[0]) == "undefined" || row == null){
            showMessageModal("请选择您要删除的用户条目。");
            return;
        }
        $('#confirmModal2').modal('show');
    }

    function confirm2() {
        $('#confirmModal2').modal('hide');
        var row = $('#baseTableUser').bootstrapTable('getSelections');
        var ids = new Array();
        for(var i =0;i<row.length;i++){
            ids.push(row[i].roleuserid);
        }
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/RoleMgr/deleteRoleUser',
            dataType: "json",
            data:{ids:JSON.stringify(ids)},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("删除成功。");
                else
                    showMessageModal(data.error);
            }
        });
    }

    function SaveUserModel() {
        $("#baseFormUser").bootstrapValidator();
        $('#baseFormUser').data('bootstrapValidator').validate();
        var flag = $('#baseFormUser').data('bootstrapValidator').isValid();
        if(!flag){
            return;
        }
        $('#baseFormUser').data("bootstrapValidator").resetForm();
        $("#myModalUser").modal("hide");
        var postData = convertArray($("#baseFormUser").serializeArray());
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/RoleMgr/saveRoleUser',
            dataType: "json",
            data:postData,
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("保存成功。");
                else
                    showMessageModal(data.error);
            }
        });
    }

    function CloseUserModal() {
        $('#baseFormUser').data("bootstrapValidator").resetForm();
        $('#baseFormUser')[0].reset();
    }

    function AddUser(){
        $('#baseFormUser')[0].reset();
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        if (typeof (row) == "undefined" || row == null) {
            showMessageModal("请选择您要添加的角色条目。");
            return;
        }
        $("#myModalUser").modal("show");
        $("#myModalLabelUser").empty();
        $("#myModalLabelUser").html("新增");
        $('#roleid2').val(row.roleid);
        $('#roleName2').val(row.rolename);
        loadSelect('userName','userid','firstname','<%=basePath%>Basic/Setting/UserMgr/querySystemUser');
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
        $('#roleCode').removeAttr("disabled");
        var postData = convertArray($("#baseForm").serializeArray());
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/RoleMgr/save',
            dataType: "json",
            data:postData,
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("保存成功。");
                else
                    showMessageModal(data.error);
                initTable();
            }
        });
    }

    function Del() {
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        if(typeof (row) == "undefined" || row == null){
            showMessageModal("请选择您要删除的角色条目。");
            return;
        }
        $('#confirmModal').modal('show');
    }

    function confirm() {
        $('#confirmModal').modal('hide');
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/RoleMgr/delete', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{id:row.roleid},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("删除成功。");
                else
                    showMessageModal(data.error);
                initTable();
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
        $("#roleCode").removeAttr("disabled");
        $("#roleid").val("");
    }

    function Edit() {
        $('#baseForm')[0].reset();
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        if (typeof (row) == "undefined" || row == null) {
            showMessageModal("请选择您要修改的角色条目。");
            return;
        }
        $("#myModal").modal("show");
        $("#myModalLabel").empty();
        $("#myModalLabel").html("修改");
        $("#roleid").val(row.roleid);
        $("#roleCode").val(row.rolecode);
        $("#roleCode").attr("disabled","disabled");
        $("#roleName").val(row.rolename);
        $("#seq").val(row.seq);
    }


    function initTable() {
        var queryParams = function (params) {
            var temp = {
                rows: params.limit,   //页面大小
                page: params.offset  //页码
            };
            return temp;
        };
        $('#baseTable').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/Setting/RoleMgr/queryRoleAll",
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
            uniqueId: "roleid",
            columns: [{
                checkbox: true
            },{
                field: 'roleid',title: 'roleid',visible:false,
            },{
                field: 'rolename',title: '角色名称',align:'center'
            },{
                field: 'rolecode',title: '角色编码',align:'center'
            },{
                field: 'seq',title: '排序', align:'center'
            },{title: '操作',field: '#',align: 'center',
                formatter:function(value,row,index){
                    var e = '<a href="<%=basePath%>Basic/Setting/AccessMgr/queryAuthorityAccessByRoleId?roleid='+ row.roleid + '"  mce_href="#" ">权限设置</a> ';
                    return e;
                }
            }],
            onClickRow:function (row,e) {
                initUserTable(row.roleid);
            }
        });
    }

    function initUserTable(roleId) {
        var queryParams = function (params) {
            var temp = {
                rows: params.limit,   //页面大小
                page: params.offset,  //页码
                roleid:roleId
            };
            return temp;
        };
        $('#baseTableUser').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/Setting/RoleMgr/queryUserByRoleId",
            method:'POST',
            striped:true,
            cache:false,
            toolbar: '#toolbarUser',
            pagination:true,
            contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            queryParams:queryParams,
            sidePagination:'server',
            pageSize:10,
            pageList: [2,10,25,],
            showRefresh: true,
            clickToSelect: true,
            singleSelect:false,
            uniqueId: "userid",
            columns:[{
                checkbox: true
            },{
                field: 'roleuserid',title: 'roleuserid',visible:false,
            },{
                field: 'userid',title: 'userid',visible:false,
            },{
                field: 'loginid',title: '登录名',align:'center'
            },{
                field: 'firstname',title: '名称',align:'center'
            },{
                field: 'email',title: '邮箱', align:'center'
            } ,{title: '操作',field: '#',align: 'center',
                formatter:function(value,row,index){
                    var e = '<a href="<%=basePath%>Basic/Setting/AccessMgr/queryAuthorityAccessByUserId?userid='+ row.userid + '"  mce_href="#" ">权限设置</a> ';
                    return e;
                }
            }]
        });
    }
</script>