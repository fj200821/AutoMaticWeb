<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
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
	    <meta charset="utf-8">
	    <title>二类电商爆款推荐</title>
		<%@ include file="head.jsp" %>
  	</head>

	<style type="text/css">
		body{
			margin: 0;
			padding: 0;
		}
		.modal_content{
			padding: 30px;
			display: flex;
			justify-content: center;
			flex-direction: column;
		}

		.modal_content>div{
			margin-bottom: 20px;
		}
		.modal_content>h5:first-child{
			margin:30px 0px;
		}
		#dialog label{
			color: #666;
		}

		#btnSendCode1{
			width: 90px;
			height: 30px;
			padding: 0 5px;
			margin: 0;
			font-size: 14px;
			text-align: center;
			background: transparent;
			border-radius: 30px;
			color: #a07941;
			border-color: #a07941;

		}
		::-webkit-input-placeholder { /* WebKit browsers */
			font-size: 14px;
			color:   rgba(0,0,0,.4);
		}
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
			font-size: 14px;
			color:   rgba(0,0,0,.4);
		}
		::-moz-placeholder { /* Mozilla Firefox 19+ */
			font-size: 14px;
			color:  rgba(0,0,0,.4);
		}
		:-ms-input-placeholder { /* Internet Explorer 10+ */
			font-size: 14px;
			color:   rgba(0,0,0,.4);
		}

		.next{
			text-align: center;
			margin: 20px 0;
		}
		.next button{
			width: 100%;
			height: 45px;
			padding: 0;
			margin: 0;
			background: #007BFF;
			color: #fff;
			border: 0;
			outline:none;
			border-radius: 3px;
		}
	</style>
  	<body class="overflow-hidden light-background">
		<div class="wrapper no-navigation preload">
			<div class="sign-in-wrapper">
				<div class="sign-in-inner">
					<div class="login-brand text-center">
						<i class="fa fa-database m-right-xs"></i> 二类电商爆款推荐
					</div>
					<form>
						<div class="form-group m-bottom-md">
							<input type="text" class="form-control" id="loginId" name="loginId" placeholder="手机号">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password" placeholder="密码">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="确认密码">
						</div>
						<div class="form-group">
							<div class="col-md-9" style="padding-left: 0px">
								<input type="text" class="form-control" id="message" name="message" autocomplete="off" placeholder="短信验证码"/>
							</div>
							<div class="code1">
								<input id="btnSendCode1" type="button" class="btn btn-default" value="获取验证码" onClick="sendMessage1()" />
							</div>
						</div>
						<div class="form-group">
							<%--<div class="custom-checkbox">--%>
								<%--<input type="checkbox" id="chkAccept">--%>
								<%--<label for="chkAccept"></label>--%>
							<%--</div>--%>
							您将获得一周（七天）的免费试用账户
						</div>
						<div class="m-top-md p-top-sm">
							<a href="#" id="registsubmit" class="btn btn-success block">注册</a>
						</div>
						<div class="m-top-md p-top-sm">
							<div class="font-12 text-center m-bottom-xs">已经拥有账号?</div>
							<a href="<%=basePath%>adminlogin" class="btn btn-default block">返回登录</a>
						</div>
					</form>
				</div><!-- ./sign-in-inner -->
			</div><!-- ./sign-in-wrapper -->
		</div><!-- /wrapper -->
		<a href="" id="scroll-to-top" class="hidden-print"><i class="icon-chevron-up"></i></a>
  	</body>


	<script type="text/javascript">
        $("#registsubmit").click(function() {
            var loginId = $("#loginId").val();
            var password = $("#password").val();
            var confirmPassword  = $("#confirmPassword").val();
			var message  = $("#message").val();
            if(!loginId){
				$.message({
					message:'登录名不能为空!',
					type:'error'
				});
				return
			}
			if(!password){
				$.message({
					message:'密码不能为空!',
					type:'error'
				});
				return
			}
			if(!confirmPassword){
				$.message({
					message:'确认密码不能为空!',
					type:'error'
				});
				return
			}
			if(confirmPassword != password){
				$.message({
					message:'两次输入密码不一致!',
					type:'error'
				});
				return
			}
			if(message != message){
				$.message({
					message:'短信验证码不能为空!',
					type:'error'
				});
				return
			}
			$("#registsubmit").attr('disabled',"true");
            $.ajax({
                type : "POST",
                url : '<%=basePath%>createNewCount',
                data : {
                    "loginId" : loginId,
                    "password" : password,
					"validateNumber":message
                },
                dataType:'json',
                cache: false,
				async:true,
                success: function(data){
                    if("200" == data.resultCode){
                        window.location.href="<%=basePath%>adminlongin";
                    }else{
						$.message({
							message:data.error,
							type:'error'
						});
                        $("#loginId").focus();
                    }
					$("#registsubmit").removeAttrs('disabled');
                }
            });
        });
        $('body').bind('keypress',function(event){
            if(event.keyCode == "13"){
                $("#registsubmit").click();
            }
        });

		var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;//手机号正则
		var count = 60; //间隔函数，1秒执行
		var InterValObj1; //timer变量，控制时间
		var curCount1;//当前剩余秒数
		/*第一*/
		function sendMessage1() {
			curCount1 = count;
			var phone = $.trim($('#loginId').val());
			if (!phoneReg.test(phone)) {
				$.message({
					message:'请输入有效的手机号码!',
					type:'error'
				});
				return false;
			}
			//设置button效果，开始计时
			$("#btnSendCode1").attr("disabled", "true");
			$("#btnSendCode1").val( + curCount1 + "秒再获取");
			InterValObj1 = window.setInterval(SetRemainTime1, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
			$.ajax({
				type : "POST",
				url : '<%=basePath%>sendPhontValidateNumber',
				data : {
					"phone" : phone
				},
				dataType:'json',
				cache: false,
				async:true,
				success: function(data){
					if("200" == data.resultCode){

					}else{
						$.message({
							message:data.error,
							type:'error'
						});
						$("#loginId").focus();
					}
				}
			});
		}
		function SetRemainTime1() {
			if (curCount1 == 0) {
				window.clearInterval(InterValObj1);//停止计时器
				$("#btnSendCode1").removeAttr("disabled");//启用按钮
				$("#btnSendCode1").val("重新发送");
			}
			else {
				curCount1--;
				$("#btnSendCode1").val( + curCount1 + "秒再获取");
			}
		}
	</script>
</html>
