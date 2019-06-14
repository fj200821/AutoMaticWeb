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
        .ztree li a.curSelectedNode_Edit {padding-top:0px; background-color:#e5e5e5; color:black; height:21px; border:1px #666 solid; opacity:0.8;}
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
                <li>角色权限</li>
            </ul>
            <div class="smart-widget">
                <div class="smart-widget-header">
                    角色权限
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
                        <div class="col-md-3">
                            <div class="block">
                                <div class="block-title">
                                    <h4>权限设置</h4>
                                </div>
                                <div id="toolbar">
                                    <button id="btn_add" type="button" onclick="Back();" class="btn btn-default">
                                        <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>返回
                                    </button>
                                    <button id="btn_edit" type="button" onclick="Save();"  class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>保存
                                    </button>
                                </div>
                                <div>
                                    <ul id="tree" class="ztree"></ul>
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
<script>
    
    function Back() {
        window.location.href="<%=basePath%>Basic/Setting/RoleMgr";
    }
    
    function Save() {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        var nodes = zTree.getCheckedNodes(true);
        var ids = new Array();
        for(var i =0;i<nodes.length;i++){
            ids.push(nodes[i].menuId);
        }
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/Setting/AccessMgr/saveRoleAccess',
            dataType: "json",
            data:{ids:JSON.stringify(ids),roleid:'${roleid}'},
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
       check:{
            enable:true,
           chkStyle:"checkbox",
           chkboxType: { "Y": "p", "N": "s" }
       }
    };
    function dblClickExpand(treeId, treeNode) {
        return treeNode.level > 0;
    }
    var t = $("#tree");
    var zNodes;
    function initZtree() {
        zNodes = ${systemMenu};
        zNodes["open"]=true;
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
    }

    $(document).ready(function(){
        initZtree();
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
