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
    <title>个人中心</title>
    <%@ include file="../admin/head.jsp"%>
    <script src="<%=basePath %>bootstrap/assets/bootstrapValidator/validator/emailAddress.js"></script>
</head>
<body class="overflow-hidden">
<%@ include file="../admin/message.jsp" %>
<div class="wrapper preload">
    <!-- 左侧菜单-->
     <%
        request.setAttribute("PCode", "UserMgr");
        request.setAttribute("MenuCode", "UserManager");
      %>
    <%@ include file="../admin/top.jsp" %>
    <%@ include file="../admin/left.jsp" %>
    <div class="wrapper preload">
        <div class="main-container">
            <div class="padding-md">
                <div class="row user-profile-wrapper">
                    <div class="col-md-3 user-profile-sidebar m-bottom-md">
                        <div class="row">
                            <div class="col-sm-4 col-md-12">
                                <div class="user-profile-pic">
                                    <img src="<%=relativePathtop%>images/profile/profile1.jpg" alt="">
                                    <div class="ribbon-wrapper">
                                        <div class="ribbon-inner shadow-pulse bg-success">
                                            头像
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-12">
                                <div class="user-name m-top-sm">${user.firstName}<i class="fa fa-circle text-success m-left-xs font-14"></i></div>
                                <div class="m-top-sm">

                                    <div class="m-top-xs">
                                        <i class="fa fa-external-link user-profile-icon"></i>
                                        ${user.email}
                                    </div>
                                </div>
                                <div class="m-top-sm text-centers">
                                    <a href="#formInModal" class="btn btn-danger" data-toggle="modal"><i class="fa fa-edit m-right-xs"></i>修改密码</a>
                                </div>
                            </div>
                        </div><!-- ./row -->
                    </div><!-- ./col -->
                    <div class="col-md-9">
                        <div class="smart-widget">
                            <div class="smart-widget-inner">
                                <ul class="nav nav-tabs tab-style2 tab-right bg-grey">
                                    <li>
                                        <a href="#profileTab2" data-toggle="tab">
                                            <span class="icon-wrapper"><i class="fa fa-book"></i></span>
                                            <span class="text-wrapper">修改信息</span>
                                        </a>
                                    </li>
                                    <li class="active">
                                        <a href="#profileTab1" data-toggle="tab">
                                            <span class="icon-wrapper"><i class="fa fa-trophy"></i></span>
                                            <span class="text-wrapper">总览</span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="smart-widget-body">
                                    <div class="tab-content">
                                        <div class="tab-pane fade in active" id="profileTab1">
                                            <h4 class="header-text m-bottom-md">
                                                ${user.firstName}
                                                <span class="sub-header">
														欢迎回来！
													</span>
                                            </h4>
                                            <div class="progress progress-striped active">
                                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="${rate}" aria-valuemin="0" aria-valuemax="100" style="width: ${rate}%">
                                                    <span class="sr-only">${rate}% Complete (danger)</span>
                                                </div>
                                            </div>
                                            <div class="alert alert-warning alert-dismissible" role="alert" <c:if test="${hasDays >= 10}">hidden</c:if>>
                                                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                <i class="fa fa-exclamation-circle m-right-xs"></i> <strong>提示!</strong>
                                                距离会员到期(${effectDay})，仅余${hasDays}天！
                                            </div>
                                            <%--<div class="alert alert-info alert-dismissible" role="alert">--%>
                                                <%--<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>--%>
                                                <%--<i class="fa fa-exclamation-circle m-right-xs"></i> <strong>提示!</strong>--%>
                                                <%--由于更换服务器，部分功能尚处于审核备案中，导致无法使用。特此，凡于备案完成前（预计2019-06-04）购买普通会员，即可获赠超级会员权益。更多优惠信息，请与管理员联系。微信号：xiuwuren--%>
                                            <%--</div>--%>
                                            <div class="row m-top-md">
                                                <%--<div class="col-md-6 col-sm-12">--%>
                                                    <%--<div class="pricing-widget clean-pricing">--%>
                                                        <%--<div class="pricing-type bg-info text-center">普通会员</div>--%>
                                                        <%--<div class="pricing-value bg-grey">--%>
                                                            <%--<span class="value">￥---</span>--%>
                                                            <%--/月--%>
                                                        <%--</div>--%>

                                                        <%--<ul class="pricing-service m-top-sm">--%>
                                                            <%--<li>所有商品数据 <span class="font-semi-bold">不限量</span></li>--%>
                                                            <%--<li>核心功能 <span class="font-semi-bold">不限量</span></li>--%>
                                                            <%--<li>自定义消息提醒规则 <span class="font-semi-bold">5条</span></li>--%>
                                                            <%--<li>后续功能待定 <span class="font-semi-bold">...</span></li>--%>
                                                            <%--<li>获取更多权益请，与管理员联系</li>--%>
                                                        <%--</ul>--%>

                                                        <%--<div class="m-top-md m-bottom-md text-center">--%>
                                                            <%--<button type="button" class="btn btn-purple" data-container="body" data-toggle="modal" data-target="#largeModal">--%>
                                                                <%--充值--%>
                                                            <%--</button>--%>
                                                        <%--</div>--%>
                                                    <%--</div><!-- ./pricing-widget -->--%>
                                                <%--</div><!-- .col -->--%>
                                                    <div class="col-md-4 col-sm-4">
                                                    </div>
                                                <div class="col-md-4 col-sm-4">
                                                    <div class="pricing-widget clean-pricing">
                                                        <div class="pricing-type bg-purple text-center">超级会员</div>
                                                        <div class="pricing-value bg-grey">
                                                            <span class="value">￥---</span>
                                                            /月
                                                        </div>
                                                        <ul class="pricing-service m-top-sm">
                                                            <li>所有商品数据 <span class="font-semi-bold">不限量</span></li>
                                                            <li>核心功能 <span class="font-semi-bold">不限量</span></li>
                                                            <%--<li>自定义消息提醒规则 <span class="font-semi-bold">不限量</span></li>--%>
                                                            <li>后续功能待定 <span class="font-semi-bold">...</span></li>
                                                            <li>获取更多权益请，与管理员联系</li>
                                                        </ul>
                                                        <div class="m-top-md m-bottom-md text-center">
                                                            <%--<a class="btn btn-purple">充值</a>--%>
                                                            <button type="button" class="btn btn-purple" data-container="body" data-toggle="modal" data-target="#largeModal">
                                                                充值
                                                            </button>
                                                        </div>
                                                    </div><!-- ./pricing-widget -->
                                                </div><!-- .col -->
                                                    <div class="col-md-4 col-sm-4">
                                                    </div>
                                            </div><!-- ./row -->

                                        </div><!-- ./tab-pane -->
                                        <div class="tab-pane fade" id="profileTab2">
                                            <h4 class="header-text m-top-md">基本信息</h4>
                                            <form class="form-horizontal no-margin required-validate" id="baseForm">
                                                <input type="hidden" id="userId" name="userId" value="${user.userId}"/>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">真实姓名</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control input-sm" id="firstName" name="firstName" value="${user.firstName}"
                                                               required  data-bv-notempty-message="真实姓名不能为空" placeholder="真实姓名"/>
                                                    </div>
                                                </div>
                                                <%--<div class="form-group">--%>
                                                    <%--<label class="col-sm-3 control-label">登&nbsp;&nbsp;录&nbsp;&nbsp;名</label>--%>
                                                    <%--<div class="col-sm-6">--%>
                                                        <%--<input type="text" class="form-control input-sm" id="loginID" name="loginID" value="${user.loginId}"--%>
                                                               <%--required  data-bv-notempty-message="登录名不能为空" placeholder="登录"/>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control input-sm" id="email" name="email" value="${user.email}"
                                                               required  data-bv-notempty-message="邮箱不能为空" data-bv-emailaddress-message="邮箱格式不正确" placeholder="邮箱"/>
                                                    </div>
                                                </div>
                                                <%--<div class="form-group">--%>
                                                    <%--<label class="col-sm-3 control-label">是否接受系统提醒</label>--%>
                                                    <%--<div class="col-sm-9">--%>
                                                        <%--<div class="m-top-xs">--%>
                                                            <%--<div class="custom-checkbox">--%>
                                                                <%--<input type="checkbox" id="securityChk1">--%>
                                                                <%--<label for="securityChk1"></label>--%>
                                                            <%--</div>--%>
                                                            <%--Yes--%>
                                                            <%--<div class="custom-checkbox m-left-sm">--%>
                                                                <%--<input type="checkbox" id="securityChk2">--%>
                                                                <%--<label for="securityChk2"></label>--%>
                                                            <%--</div>--%>
                                                            <%--No--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                                <div class="form-group m-top-lg">
                                                    <label class="col-sm-3 control-label"></label>
                                                    <div class="col-sm-9">
                                                        <a class="btn btn-default" onclick="submit()">提交</a>
                                                        <a class="btn btn-info m-left-xs">取消</a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div><!-- ./tab-pane -->
                                    </div><!-- ./tab-content -->
                                </div><!-- ./smart-widget-body -->
                            </div><!-- ./smart-widget-inner -->
                        </div><!-- ./smart-widget -->
                    </div>
                </div>
            </div><!-- ./padding-md -->
        </div><!-- /main-container -->
    </div><!-- /wrapper -->

    <div class="modal fade" id="formInModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                <div class="modal-body">
                    <form role="form" id="form2">
                        <input type="hidden" id="userId" name="userId" value="${user.userId}" />
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                        </div>
                        <div class="form-group">
                            <label>确认密码</label>
                            <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="请输确认密码">
                        </div>
                    </form>
                    <a class="btn btn-danger block m-top-md" onclick="submit2()">确认</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Large Modal -->
    <div class="modal fade" id="largeModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
                    <h4 class="modal-title">我们的定价计划</h4>
                </div>
                <div class="modal-body">
                    <div class="padding-md">
                            <div class="row user-profile-wrapper" style="text-align: center">
                                <h4 style="color: red">充值成功后，我们将短信或微信通知您，请耐心等待...</h4>
                                <h6>续费处理周期一般不超过一个工作日，如超时未充值成功，请联系客服：17362125457</h6>
                            </div>
                            <div class="row user-profile-wrapper">
                                <div class="col-md-6 user-profile-sidebar m-bottom-md">
                                    <div class="row">
                                        <div class="col-sm-4 col-md-12">
                                            <div class="user-profile-pic">
                                                <img src="<%=relativePathtop%>images/pay/zhifubao.png"  alt="">
                                                <div class="ribbon-wrapper">
                                                    <div class="ribbon-inner shadow-pulse bg-success">
                                                        支付宝
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- ./row -->
                                </div><!-- ./col -->
                                <div class="col-md-6 user-profile-sidebar m-bottom-md">
                                    <div class="row">
                                        <div class="col-sm-4 col-md-12">
                                            <div class="user-profile-pic">
                                                <img src="<%=relativePathtop%>images/pay/weixin.png" alt="">
                                                <div class="ribbon-wrapper">
                                                    <div class="ribbon-inner shadow-pulse bg-success">
                                                        微信
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- ./row -->
                                </div><!-- ./col -->
                            </div>
                            <div class="row user-profile-wrapper" style="text-align: center">
                                <h2>首充<span style="color: green">300￥</span>/月,次月<span style="color: red">400￥</span>/月</h2>
                                <h2>多充多送，包年更优惠，具体优惠信息请咨询客服：17362125457</h2>
                                <h4>请添加客服微信：17362125457，或直接转账（转账时请备注充值账号）</h4>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                </div>
            </div>
        </div>
    </div>

    <a href="#" class="scroll-to-top hidden-print"><i class="fa fa-chevron-up fa-lg"></i></a>
    <%@ include file="../admin/foot.jsp"%>
</div>
</body>

<script type="application/javascript">
    $(function () {

    });

    function submit2(){
        var password = $("#password").val();
        if(!password){
            $('#formInModal').modal('hide');
            showMessageModal("密码不能为空。");
            return
        }
        var passwordConfirm = $("#passwordConfirm").val();
        if(!passwordConfirm){
            $('#formInModal').modal('hide');
            showMessageModal("确认密码不能为空。");
            return
        }else if(passwordConfirm != password){
            $('#formInModal').modal('hide');
            showMessageModal("两次输入密码不一致！");
            return
        }
        var postData = convertArray($("#form2").serializeArray());
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/UserMgr/UserManager/save',
            dataType: "json",
            data:postData,
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                $('#formInModal').modal('hide');
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("保存成功。重新登录后生效！");
                else
                    showMessageModal(data.error);
            }
        });
    }

    function submit(){
        // $("#baseForm").bootstrapValidator();
        // var flag = $('#baseForm').data('bootstrapValidator').isValid();
        // if(!flag){
        //     showMessageModal("必填项不能为空。");
        //     return;
        // }
        var firstName = $("#firstName").val();
        if(!firstName){
            showMessageModal("真实姓名不能为空。");
            return
        }
        // var loginID = $("#loginID").val();
        // if(!loginID){
        //     showMessageModal("登录名不能为空。");
        //     return
        // }
        var postData = convertArray($("#baseForm").serializeArray());
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>Basic/UserMgr/UserManager/save',
            dataType: "json",
            data:postData,
            async:false,
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
                if(typeof data != "undefined" && data.resultCode == "200")
                    showMessageModal("保存成功。重新登录后生效！");
                else
                    showMessageModal(data.error);
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