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
    <title>菜单操作</title>
    <%@ include file="../admin/head.jsp"%>

    <script type="text/javascript" src="<%=basePath%>bootstrap/assets/zTree3.5.26/jquery.ztree.all.min.js"></script>
    <style type="text/css">
        .ztree * {padding:0; margin:0; font-size:12px; font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif}
        .ztree {margin:0; padding:5px; color:#333}
        .ztree li{padding:0; margin:0; list-style:none; line-height:21px; text-align:left; white-space:nowrap; outline:0;}
        .ztree li ul{ margin:0; padding:0 0 0 18px;width: 80%;}
        .ztree li ul.line{ background:url(<%=basePath%>bootstrap/assets/zTree3.5.26/zTreeStyle/img/line_conn.png) 0 0 repeat-y;}

        .ztree li a {padding-right:3px; margin:0; cursor:pointer; height:21px; color:#333; background-color: transparent; text-decoration:none; display: inline-block}
        .ztree li a:hover {text-decoration:underline}
        .ztree li a.curSelectedNode {padding-top:0px; background-color:#e5e5e5; color:black; height:21px; opacity:0.8;}
        .ztree li a.curSelectedNode_Edit {padding-top:0px; background-color: #cfdde5; color:black; height:21px; border:1px #666 solid; opacity:0.8;}
        .ztree li a.tmpTargetNode_inner {padding-top:0px; background-color:#aaa; color:white; height:21px; border:1px #666 solid;
            opacity:0.8; filter:alpha(opacity=80)}
        .ztree li a.tmpTargetNode_prev {}
        .ztree li a.tmpTargetNode_next {}
        .ztree li a input.rename {height:14px; width:80px; padding:0; margin:0;
            font-size:12px; border:1px #7EC4CC solid; *border:0px}
        .ztree li span {line-height:21px; margin-right:2px;}
        .ztree li span.button {line-height:0; margin:0; width:21px; height:21px; display: inline-block; vertical-align:middle;
            border:0 none; cursor: pointer;outline:none;
            background-color:transparent; background-repeat:no-repeat; background-attachment: scroll;
            background-image:url("<%=basePath%>bootstrap/assets/zTree3.5.26/zTreeStyle/img/metro.png"); *background-image:url("<%=basePath%>bootstrap/assets/zTree3.5.26/zTreeStyle/img/metro.gif")}

        .ztree li span.button.chk {width:13px; height:13px; margin:0 2px; cursor: auto}
        .ztree li span.button.chk.checkbox_false_full {background-position: -5px -5px;}
        .ztree li span.button.chk.checkbox_false_full_focus {background-position: -5px -26px;}
        .ztree li span.button.chk.checkbox_false_part {background-position: -5px -48px;}
        .ztree li span.button.chk.checkbox_false_part_focus {background-position: -5px -68px;}
        .ztree li span.button.chk.checkbox_false_disable {background-position: -5px -89px;}
        .ztree li span.button.chk.checkbox_true_full {background-position: -26px -5px;}
        .ztree li span.button.chk.checkbox_true_full_focus {background-position: -26px -26px;}
        .ztree li span.button.chk.checkbox_true_part {background-position: -26px -48px;}
        .ztree li span.button.chk.checkbox_true_part_focus {background-position: -26px -68px;}
        .ztree li span.button.chk.checkbox_true_disable {background-position: -26px -89px;}
        .ztree li span.button.chk.radio_false_full {background-position: -47px -5px;}
        .ztree li span.button.chk.radio_false_full_focus {background-position: -47px -26px;}
        .ztree li span.button.chk.radio_false_part {background-position: -47px -47px;}
        .ztree li span.button.chk.radio_false_part_focus {background-position: -47px -68px;}
        .ztree li span.button.chk.radio_false_disable {background-position: -47px -89px;}
        .ztree li span.button.chk.radio_true_full {background-position: -68px -5px;}
        .ztree li span.button.chk.radio_true_full_focus {background-position: -68px -26px;}
        .ztree li span.button.chk.radio_true_part {background-position: -68px -47px;}
        .ztree li span.button.chk.radio_true_part_focus {background-position: -68px -68px;}
        .ztree li span.button.chk.radio_true_disable {background-position: -68px -89px;}

        .ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
        .ztree li ul.level0 {padding:0; background:none;}
        .ztree li span.button.switch {width:21px; height:21px}
        .ztree li span.button.root_open{background-position: -105px 0;}
        .ztree li span.button.root_close{background-position:-126px 0;}
        .ztree li span.button.roots_open{background-position: -105px 0;}
        .ztree li span.button.roots_close{background-position: -126px 0;}
        .ztree li span.button.center_open{background-position: -105px -21px;}
        .ztree li span.button.center_close{background-position: -126px -21px;}
        .ztree li span.button.bottom_open{background-position: -105px -42px;}
        .ztree li span.button.bottom_close{background-position: -126px -42px;}
        .ztree li span.button.noline_open{background-position: -126px -84px;}
        .ztree li span.button.noline_close{background-position: -105px -84px;}
        .ztree li span.button.root_docu{ background:none;}
        .ztree li span.button.roots_docu{background-position: -84px 0;}
        .ztree li span.button.center_docu{background-position: -84px -21px;}
        .ztree li span.button.bottom_docu{background-position: -84px -42px;}
        .ztree li span.button.noline_docu{ background:none;}

        .ztree li span.button.ico_open{margin-right:2px; background-position: -147px -21px; vertical-align:top; *vertical-align:middle}
        .ztree li span.button.ico_close{margin-right:2px; margin-right:2px; background-position: -147px 0; vertical-align:top; *vertical-align:middle}
        .ztree li span.button.ico_docu{margin-right:2px; background-position: -147px -42px; vertical-align:top; *vertical-align:middle}
        .ztree li span.button.edit {margin-left:2px; margin-right: -1px; background-position: -189px -21px; vertical-align:top; *vertical-align:middle}
        .ztree li span.button.edit:hover {
            background-position: -168px -21px;
        }
        .ztree li span.button.remove {margin-left:2px; margin-right: -1px; background-position: -189px -42px; vertical-align:top; *vertical-align:middle}
        .ztree li span.button.remove:hover {
            background-position: -168px -42px;
        }
        .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position: -189px 0; vertical-align:top; *vertical-align:middle}
        .ztree li span.button.add:hover {
            background-position: -168px 0;
        }
        .ztree li span.button.ico_loading{margin-right:2px; background:url(<%=basePath%>bootstrap/assets/zTree3.5.26/zTreeStyle/img/loading.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}

        ul.tmpTargetzTree {background-color:#FFE6B0; opacity:0.8; filter:alpha(opacity=80)}

        span.tmpzTreeMove_arrow {width:16px; height:21px; display: inline-block; padding:0; margin:2px 0 0 1px; border:0 none; position:absolute;
            background-color:transparent; background-repeat:no-repeat; background-attachment: scroll;
            background-position:-168px -84px; background-image:url("<%=basePath%>bootstrap/assets/zTree3.5.26/zTreeStyle/img/metro.png"); *background-image:url("<%=basePath%>bootstrap/assets/zTree3.5.26/zTreeStyle/img/metro.gif")}

        ul.ztree.zTreeDragUL {margin:0; padding:0; position:absolute; width:auto; height:auto;overflow:hidden; background-color:#cfcfcf; border:1px #00B83F dotted; opacity:0.8; filter:alpha(opacity=80)}
        .zTreeMask {z-index:10000; background-color:#cfcfcf; opacity:0.0; filter:alpha(opacity=0); position:absolute}
    </style>
</head>
<body  class="overflow-hidden">
<%@ include file="../admin/message.jsp" %>
<div class="wrapper preload">
    <!-- 左侧菜单-->
    <%
        request.setAttribute("PCode", "Setting");
        request.setAttribute("MenuCode", "MenuMgr");
    %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
        <div class="main-container">
            <div class="padding-md">
                <ul class="breadcrumb">
                    <li><span class="primary-font"><i class="icon-home"></i></span><a href="<%=basePath%>main/index">主页</a></li>
                    <li>基础设置</li>
                    <li>菜单操作</li>
                </ul>

                <div class="smart-widget">
                    <div class="smart-widget-header">
                        菜单操作
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
                                <div class="col-lg-3">
                                    <div class="block">
                                        <div class="block-title">
                                            <h4>菜单树</h4>
                                        </div>
                                        <div>
                                            <ul id="tree" class="ztree"></ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9">
                                    <div class="block">
                                        <div class="block-title">
                                            <h4>菜单列表</h4>
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
                    <input id="menuid" name="menuid" type="hidden">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-xs-6">
                                <label class="control-label">父菜单</label>
                                <select id="parentMenuName" name="pid" class="form-control input-sm"  required  data-bv-notempty-message="父菜单不能为空" >
                                </select>
                            </div>
                           <div class="col-xs-6">
                                <label class="control-label">菜单名称</label>
                                <input id="menuName"  name="menuName" type="text" class="form-control input-sm"  required  data-bv-notempty-message="菜单名称不能为空" placeholder="菜单名称">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-xs-6">
                                <label class="control-label">菜单编码</label>
                                <input id="menuCode" name="menuCode" type="text" class="form-control input-sm" required  data-bv-notempty-message="菜单编码不能为空"  placeholder="菜单编码">
                            </div>
                             <div class="col-xs-6">
                                <label class="control-label">排序</label>
                                <input id="seq" name="seq" type="text" class="form-control input-sm"  required  data-bv-notempty-message="排序不能为空" placeholder="排序">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                              <div class="col-xs-6">
                                <label class="control-label">访问路径</label>
                                <input id="requestURL" name="requestURL" type="text" class="form-control input-sm" required  data-bv-notempty-message="访问路径不能为空" placeholder="访问路径">
                            </div>
                            <div class="col-xs-4">
                                <label class="control-label">图标</label>
                                <select id="iconName" name="iconName" class="form-control input-sm" required  data-bv-notempty-message="图标不能为空">
                                </select>
                            </div>
                             <div class="col-xs-2">
                                <label class="control-label"></label>
                                <i class="" id="iconShow" ></i>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label">使用状态</label>
                                <select id="status" name="status" class="form-control input-sm" required  data-bv-notempty-message="使用状态不能为空">
                                </select>
                            </div>
                            <div class="col-xs-6">
                                <label class="control-label"></label>
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

<script type="application/javascript">
    function sopStatus(id) {
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/MenuMgr/stopMenu', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{id:id},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("关闭成功。");
                else
                    showMessageModal(data.error);
            }
        });
    }
    function startStatus(id) {
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/MenuMgr/startMenu', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{id:id},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("启动成功。");
                else
                    showMessageModal(data.error);
            }
        });
    }
    function CloseModal() {
        $('#baseForm').data("bootstrapValidator").resetForm();
        $('#baseForm')[0].reset();
    }

    function SaveModel() {
        $("#baseForm").bootstrapValidator();
        $('#baseForm').data('bootstrapValidator').validate();
        var flag = $('#baseForm').data('bootstrapValidator').isValid();
        if(!flag){
            return;
        }
        $("#myModal").modal("hide");
        $('#baseForm').data("bootstrapValidator").resetForm();
        $('#parentMenuName').removeAttr("disabled");
        var postData = convertArray($("#baseForm").serializeArray());
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/MenuMgr/save', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:postData,
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("保存成功。");
                else
                    showMessageModal(data.error);
                initTable(-1);
            }
        });
    }
    function Add(){
        $('#baseForm')[0].reset();
        $("#myModal").modal("show");
        $("#myModalLabel").empty();
        $("#myModalLabel").html("新增");
        var zTree = $.fn.zTree.getZTreeObj("tree");
        var nodes =zTree.getSelectedNodes();
        $('#parentMenuName').removeAttr("disabled");
        loadSelectWithPara('parentMenuName',{parentId:nodes[0].pId},'menuid','menuname','<%=basePath%>Basic/Setting/MenuMgr/findMenuByParentId');
        loadSelectWithPara('status',{pcode:'status'},'code','name','<%=basePath%>Basic/SystemDict/queryDictCodeByPCode');
        loadSelect('iconName','iconname','iconname','<%=basePath%>Basic/SystemDict/queryIconAll');
        $("#iconName").change(function () {
            $('#iconShow').removeClass().addClass($(this).val()+' fa-3x');
        });
        $('#parentMenuName').val(nodes[0].menuId);
        $('#status').val('Used');
        $('#menuid').val("");
    }
    function Edit() {
        $('#baseForm')[0].reset();
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        if(typeof (row) == "undefined" || row == null){
            showMessageModal("请选择您要修改的菜单条目。");
            return;
        }
        $("#myModal").modal("show");
        $("#myModalLabel").empty();
        $("#myModalLabel").html("修改");
        loadSelectWithPara('parentMenuName',{menuid:row.pid},'menuid','menuname','<%=basePath%>Basic/Setting/MenuMgr/findParentListByMenuId');
        loadSelectWithPara('status',{pcode:'status'},'code','name','<%=basePath%>Basic/SystemDict/queryDictCodeByPCode');
        loadSelect('iconName','iconname','iconname','<%=basePath%>Basic/SystemDict/queryIconAll');
        $("#iconName").change(function () {
            $('#iconShow').removeClass().addClass($(this).val()+' fa-3x');
        });
        $('#menuid').val(row.menuid);
        $('#parentMenuName').val(row.pid);
        $('#parentMenuName').attr("disabled","disabled");
        $('#menuName').val(row.menuname);
        $('#menuCode').val(row.menucode);
        $('#seq').val(row.seq);
        $('#requestURL').val(row.requesturl);
        $('#iconName').val(row.iconname);
        $('#status').val(row.status);
        $('#iconShow').removeClass().addClass(row.iconname+' fa-3x');
    }

    function Del() {
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        if(typeof (row) == "undefined" || row == null){
            showMessageModal("请选择您要删除的菜单条目。");
            return;
        }
        $('#confirmModal').modal('show');
    }

    function confirm() {
        $('#confirmModal').modal('hide');
        var row = $('#baseTable').bootstrapTable('getSelections')[0];
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/MenuMgr/delete', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{id:row.menuid},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("删除成功。");
                else
                    showMessageModal(data.error);
                initTable(-1);
            }
        });
    }

    function initTable(parentId) {

        var queryParams = function (params) {
            var temp = {
                rows: params.limit,   //页面大小
                page: params.offset,  //页码
                parentId:parentId
            };
            return temp;
        };
        $('#baseTable').bootstrapTable('destroy').bootstrapTable({
            url:"<%=basePath%>Basic/Setting/MenuMgr/findMenuByParentId",
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
            uniqueId: "menuid",
            columns: [{
                checkbox: true
            },{
                field: 'menuid',title: 'menuId',visible:false,
            },{
                field: 'pid',title: 'pid', visible:false,
            },{
                field: 'menuname',title: '菜单名称',align:'center'
            },{
                field: 'menucode',title: '菜单编码',align:'center'
            },{
                field: 'pmenuname',title: '父菜单',align:'center'
            }, {
                field: 'requesturl', title: '访问路径', align:'center'
            },{
                field: 'seq',title: '排序', align:'center'
            }, {
                field: 'iconname', title: '图标', align:'center'
            },{
                field: 'status', title: 'status', visible:false
            },{
                field: '#', title: '使用状态', align:'center',
                formatter:function(value,row,index){
                    var e = '';
                    if(row.status == "Used"){
                        e = '<div class="switch"><label><font size="2" color="red">不使用</font>' +
                            '<input type="checkbox" onclick="sopStatus(\''+row.menuid+'\')" checked>' +
                            '<span class="lever"></span><font size="2" color="#4682b4">使用</font> </label></div>';
                    }else{
                        e = '<div class="switch"><label><font size="2" color="red">不使用</font>' +
                            '<input type="checkbox" onclick="startStatus(\''+row.menuid+'\')">' +
                            '<span class="lever"></span><font size="2" color="#4682b4">使用 </font></label></div>';
                    }
                    return e;
                }
            }
//            ,{title: '操作',field: '#',align: 'center',
//                formatter:function(value,row,index){
//                    var e = '<a href="#" mce_href="#" onclick="bindEdit(\''+ row.menuid + '\')">编辑</a> ';
//                    var d = '<a href="#" mce_href="#" onclick="bingDelete(\''+ row.menuid +'\')">删除</a> ';
//                    return e+d;
//                }
            ]
        });
    }

    var zTree;
    var demoIframe;
    var setting = {
        view: {
            dblClickExpand: dblClickExpand,
            showLine: true
        },
        data: {
            key:{
                children:"childrenMenus",
                name:"menuName",
                title:""
            }
        },
        callback: {
            onClick: function(e,treeId, treeNode) {
                initTable(treeNode.menuId);
            }
        }
    };
    function dblClickExpand(treeId, treeNode) {
        return treeNode.level > 0;
    }
    var t = $("#tree");
    var zNodes;
    function initZtree() {
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/MenuMgr/queryMenuAll', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                zNodes = data;
            }
        });
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
        zTree.selectNode(zTree.getNodeByParam("menuId", -1));
    }

    $(document).ready(function(){

        $('#baseTable').bootstrapTable({
            toolbar: '#toolbar',
        });
        initZtree();
        initTable(-1);
    });

    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
            htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
            maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
            h = demoIframe.height() >= maxH ? minH:maxH ;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }
</script>
</body>
</html>
