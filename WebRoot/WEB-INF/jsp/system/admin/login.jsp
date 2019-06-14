<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String relativePathtop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/ecommerce/";
%>
<!DOCTYPE html>
<html>
  	<head>
	    <meta charset="utf-8">
	    <title>二类电商爆款推荐</title>
		<%@ include file="head.jsp" %>
  	</head>
  	<body class="overflow-hidden light-background">
		<div class="wrapper no-navigation preload">
			<div class="sign-in-wrapper">
				<div class="sign-in-inner">
					<div class="login-brand text-center">
						<i class="fa fa-database m-right-xs"></i> 二类电商爆款推荐
					</div>
					<form>
						<div class="form-group m-bottom-md">
							<input type="text" id="UserName" name="UserName" class="form-control" placeholder="登录名">
						</div>
						<div class="form-group">
							<input type="password" id="Password" name="Password"  class="form-control" placeholder="密码">
						</div>

						<div class="form-group">
							<div class="custom-checkbox">
								<input type="checkbox" id="chkRemember">
								<label for="chkRemember"></label>
							</div>
							记住密码
						</div>

						<div class="m-top-md p-top-sm">
							<a href="#" id="loginsubmit" class="btn btn-success block">登录</a>
						</div>

						<div class="m-top-md p-top-sm">
							<div class="font-12 text-center m-bottom-xs">
								<a href="#" class="font-12">登录遇到问题 ?</a>
							</div>
							<div class="font-12 text-center m-bottom-xs">请联系客服，微信号：17362125457</div>
							<a href="<%=basePath%>regist" class="btn btn-default block">免费注册</a>

						</div>
						<div class="m-top-md p-top-sm">
							<a href="#" class="btn btn-default block"  data-toggle="modal" data-target="#largeModal">充值续费</a>
						</div>
					</form>
				</div><!-- ./sign-in-inner -->
			</div><!-- ./sign-in-wrapper -->
		</div><!-- /wrapper -->
		<a href="" id="scroll-to-top" class="hidden-print"><i class="icon-chevron-up"></i></a>
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
								<h4>请添加管理员客服：17362125457，或直接转账（转账时请备注充值账号）</h4>
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
  	</body>
	<script type="text/javascript">
        $("#loginsubmit").click(function() {
            var _username = $("#UserName").val();
            var _password = $("#Password").val();
			$("#loginsubmit").attr('disabled',"true");
            $.ajax({
                type : "POST",
                url : '<%=basePath%>checkUser',
                data : {
                    "userName" : _username,
                    "password" : _password
                },
                dataType:'json',
                cache: false,
				async:true,
                success: function(data){
                    if("200" == data.resultCode){
						$.message({
							message:'登录成功，正在跳转中...',
							type:'success'
						});
                        window.location.href="<%=basePath%>main/index";
                    }else{
						$.message({
							message:data.error,
							type:'error'
						});
                        $("#UserName").focus();
                    }
					$("#loginsubmit").removeAttrs('disabled');
                }
            });
        });
        $('body').bind('keypress',function(event){
            if(event.keyCode == "13")
            {
                $("#loginsubmit").click();
            }
        });
	</script>
</html>
