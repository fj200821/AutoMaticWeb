<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String pathtop = request.getContextPath();
    String basePathtop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            +pathtop+ "/";
    String relativePathtop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/ecommerce/";
%>
<header class="top-nav">
    <div class="top-nav-inner">
        <div class="nav-header">
            <button type="button" class="navbar-toggle pull-left sidebar-toggle" id="sidebarToggleSM">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <ul class="nav-notification pull-right">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cog fa-lg"></i></a>
                    <span class="badge badge-danger bounceIn">1</span>
                    <ul class="dropdown-menu dropdown-sm pull-right user-dropdown">
                        <li class="user-avatar">
                            <img src="<%=relativePathtop%>images/profile/profile1.jpg" alt="" class="img-circle">
                            <div class="user-content">
                                <h5 class="no-m-bottom">${sessionScope.sessionUser.loginId}</h5>
                                <div class="m-top-xs">
                                    <a href="profile.html" class="m-right-sm">Profile</a>
                                    <a href="<%=basePath%>loginOut">Log out</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <a href="">
                                Inbox
                                <span class="badge badge-danger bounceIn animation-delay2 pull-right">1</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Notification
                                <span class="badge badge-purple bounceIn animation-delay3 pull-right">2</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="sidebarRight-toggle">
                                Message
                                <span class="badge badge-success bounceIn animation-delay4 pull-right">7</span>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">Setting</a>
                        </li>
                    </ul>
                </li>
            </ul>

            <a href="" class="brand">
                <i class="fa fa-database"></i><span class="brand-name">二类电商爆款推荐</span>
            </a>
        </div>
        <div class="nav-container">
            <button type="button" class="navbar-toggle pull-left sidebar-toggle" id="sidebarToggleLG">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<ul class="nav-notification">--%>
                <%--<li class="search-list">--%>
                    <%--<div class="search-input-wrapper">--%>
                        <%--<div class="search-input">--%>
                            <%--<input type="text" class="form-control input-sm inline-block">--%>
                            <%--<a href="#" class="input-icon text-normal"><i class="ion-ios7-search-strong"></i></a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</li>--%>
            <%--</ul>--%>
            <div class="pull-right m-right-sm">
                <div class="user-block hidden-xs">
                    <a href="#" id="userToggle" data-toggle="dropdown">
                        <img src="<%=relativePathtop%>images/profile/profile1.jpg" alt="" class="img-circle inline-block user-profile-pic">
                        <div class="user-detail inline-block">
                            ${sessionScope.sessionUser.loginId}
                            <i class="fa fa-angle-down"></i>
                        </div>
                    </a>
                    <div class="panel border dropdown-menu user-panel">
                        <div class="panel-body paddingTB-sm">
                            <ul>
                                <li>
                                    <a href="<%=basePath%>loginOut">
                                        <i class="fa fa-power-off fa-lg"></i><span class="m-left-xs">Sign out</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <ul class="nav-notification">
                    <%--<li>--%>
                        <%--<a href="#" data-toggle="dropdown"><i class="fa fa-envelope fa-lg"></i></a>--%>
                        <%--<span class="badge badge-purple bounceIn animation-delay5 active">2</span>--%>
                        <%--<ul class="dropdown-menu message pull-right">--%>
                            <%--<li><a>You have 4 new unread messages</a></li>--%>
                            <%--<li>--%>
                                <%--<a class="clearfix" href="#">--%>
                                    <%--<img src="<%=relativePathtop%>images/profile/profile2.jpg" alt="User Avatar">--%>
                                    <%--<div class="detail">--%>
                                        <%--<strong>John Doe</strong>--%>
                                        <%--<p class="no-margin">--%>
                                            <%--Lorem ipsum dolor sit amet...--%>
                                        <%--</p>--%>
                                        <%--<small class="text-muted"><i class="fa fa-check text-success"></i> 27m ago</small>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a class="clearfix" href="#">--%>
                                    <%--<img src="<%=relativePathtop%>images/profile/profile3.jpg" alt="User Avatar">--%>
                                    <%--<div class="detail">--%>
                                        <%--<strong>Jane Doe</strong>--%>
                                        <%--<p class="no-margin">--%>
                                            <%--Lorem ipsum dolor sit amet...--%>
                                        <%--</p>--%>
                                        <%--<small class="text-muted"><i class="fa fa-check text-success"></i> 5hr ago</small>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a class="clearfix" href="#">--%>
                                    <%--<img src="<%=relativePathtop%>images/profile/profile4.jpg" alt="User Avatar">--%>
                                    <%--<div class="detail m-left-sm">--%>
                                        <%--<strong>Bill Doe</strong>--%>
                                        <%--<p class="no-margin">--%>
                                            <%--Lorem ipsum dolor sit amet...--%>
                                        <%--</p>--%>
                                        <%--<small class="text-muted"><i class="fa fa-reply"></i> Yesterday</small>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a class="clearfix" href="#">--%>
                                    <%--<img src="<%=relativePathtop%>images/profile/profile5.jpg" alt="User Avatar">--%>
                                    <%--<div class="detail">--%>
                                        <%--<strong>Baby Doe</strong>--%>
                                        <%--<p class="no-margin">--%>
                                            <%--Lorem ipsum dolor sit amet...--%>
                                        <%--</p>--%>
                                        <%--<small class="text-muted"><i class="fa fa-reply"></i> 9 Feb 2013</small>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li><a href="#">View all messages</a></li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                    <li>
                        <a href="#" data-toggle="dropdown"><i class="fa fa-bell fa-lg"></i></a>
                        <span class="badge badge-info bounceIn animation-delay6 active" id="BellCount">0</span>
                        <ul class="dropdown-menu notification dropdown-3 pull-right" id="BellCountMessage">
                            <li><a href="#" >暂无提醒</a></li>

                        </ul>
                    </li>
                    <%--<li class="chat-notification">--%>
                        <%--<a href="#" class="sidebarRight-toggle"><i class="fa fa-comments fa-lg"></i></a>--%>
                        <%--<span class="badge badge-danger bounceIn">1</span>--%>

                        <%--<div class="chat-alert">--%>
                            <%--Hello, Are you there?--%>
                        <%--</div>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
    </div>
</header>


<script type="text/javascript">
    $(function () {
        <%--if('${sessionScope.sessionRole.roleCode}' == 'Admin'){--%>
            <%--checkStoreApply();--%>
            <%--window.setInterval("checkStoreApply()",3000);--%>
        <%--}--%>
        <%--if('${sessionScope.sessionRole.roleCode}' == 'Shoper'){--%>
            <%--checkAddStock();--%>
            <%--window.setInterval("checkAddStock()",3000);--%>
        <%--}--%>
    });

    function checkStoreApply() {
        var count=0;
        var html = '';
            $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/StoreMgr/StoreApply/queryStoreApply', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{status:'Init'},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200"){
                    count += data.total;
                    $.each(data.rows,function (index,item) {
                        html += '<li style="height: 50px"><a href="<%=basePath%>Basic/StoreMgr/StoreApply"><span class="notification-icon bg-success"><i class="fa fa-plus"></i></span>'+
                                 '<span class="m-left-xs">'+item.loginid+'</br>提交创建【'+item.storename+'】商城的申请。</span>' +
                                 '<span class="time text-muted">'+item.createddate+'</span></a></li>';
                    });

                }
            }
        });
        $('#BellCount').empty();
        $('#BellCount').html(count);
        var tital = '<li><a href="#" >暂无提醒</a></li>';
        if(count > 0){
            tital = '<li><a href="#" >你有'+count+'条消息未处理！</a></li>'
        }
        tital+=html;
        $('#BellCountMessage').empty();
        $('#BellCountMessage').html(tital);
    }


    function checkAddStock() {
        var count=0;
        var html = '';
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/ProductMgr/ProductManager/queryStockLessProduct', //url  action是方法的名称
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            data:{status:'Used',loginid:'${sessionScope.sessionUser.loginId}'},
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200"){
                    count += data.total;
                    $.each(data.rows,function (index,item) {
                        html += '<li style="height: 50px"><a href="<%=basePath%>Basic/ProductMgr/AddStock"><span class="notification-icon bg-success"><i class="fa fa-plus"></i></span>'+
                            '<span class="m-left-xs">产品【'+item.productname+'】，</br>库存还剩'+item.stock+',需要补货!</span>' +
                            '<span class="time text-muted">'+item.createddate+'</span></a></li>';
                    });

                }
            }
        });
        $('#BellCount').empty();
        $('#BellCount').html(count);
        var tital = '<li><a href="#" >暂无提醒</a></li>';
        if(count > 0){
            tital = '<li><a href="#" >你有'+count+'条消息未处理！</a></li>'
        }
        tital+=html;
        $('#BellCountMessage').empty();
        $('#BellCountMessage').html(tital);
    }


</script>
