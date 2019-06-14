<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String relativePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/ecommerceBefore/";
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
<title>用户注册</title>
    <%@include file="../public/top.jsp"%>
</head>

<body>
<div class="login_top">
 <div class="top clearfix">
   <div class="login_logo" style="margin-top: 50px;"><h2 style="margin-left: 120px;"><a href="<%=basePathtop%>home/index">综合商城</a></h2></div>
  <div class="login_title">用户注册</div>
   <div class="fw_phone">
    咨询服务热线：
    <h2>17317235393</h2>
   </div>
 </div>
</div>
<!--登录-->
<div class="login_content Reg_log_style">
 <div class="mian_style relative">
     <div class="login_ad"></div>
  <div class="login_style">
   <div class="login_Switch">
       <div class="hd" style="background: blue;text-align: center"><h3>用户注册</h3></div>
    <div class="bd">
     <ul>
      <form class="write_login"  method="post">
	     <fieldset>
		 <ul>
             <li class="frame_style form_error"><label class="phone_icon"></label><input name="LoginId" type="text"  id="LoginId" placeholder="登录名"/></li>
         <li class="frame_style form_error"><label class="password_icon"></label><input name="password" type="password"   id="tbPassword" placeholder="输入密码"/></li>
         <li class="frame_style form_error"><label class="password_icon"></label><input  type="password"   id="userpwd" placeholder="确认密码"/></li>
         </ul>
      <div class="space-4"><button type="button" class="width-35  btn btn-sm btn-primary" id="p_login_btn" >注册</button></div>
      <div class="link_bz"><a href="<%=basePath%>Home/toLogin">用户登录</a></div>
       </fieldset>
      </form>
     </ul>
    </div>
    <div class="others-login-box  clearfix">
      <span class="name">使用合作网站账号登录</span>
        <div class="inline-block">
        <a href="#" class="share-icon share-wx-icon ml10"></a>
        <a href="#" class="share-icon share-qq-icon ml10"></a>
        </div></div>
   </div>
  </div>
 </div>
</div>
<%@include file="../public/foot.jsp"%>
</body>
<script type="application/javascript">
    $('#p_login_btn').on('click', function(){
        var _username = $("#LoginId").val();
        var _password = $("#tbPassword").val();
        var _repassword = $("#userpwd").val();
        if(_password != _repassword){
            layer.alert('两次密码不一致！',{
                title: '提示框',
                icon:1
            });
        }
        $.ajax({
            type : "POST",
            url : '<%=basePath%>Customer/User/register',
            data : {
                "LoginId" : _username,
                "Password" : _password
            },
            dataType:'json',
            cache: false,
            success: function(data){
                if("200" == data.resultCode){
                    layer.alert('注册成功！',{
                        title: '提示框',
                        icon:1
                    });
                    window.location.href="<%=basePath%>Home/toLogin";
                    layer.close(index);
                }else{
                    layer.alert('注册失败！',{
                        title: '提示框',
                        icon:1,
                    });
                    $("#UserName").focus();
                }
            }
        });
    });
</script>
</html>
