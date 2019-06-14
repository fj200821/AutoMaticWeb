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
<title>用户登录</title>
    <%@include file="../public/top.jsp"%>
</head>
<body>
<div class="login_top">
 <div class="top clearfix">
  <div class="login_logo" style="margin-top: 50px;"><h2 style="margin-left: 120px;"><a href="<%=basePathtop%>home/index">综合商城</a></h2></div>
  <div class="login_title">用户登录</div>
   <div class="fw_phone">
    咨询服务热线：
    <h2>17317235396</h2>
   </div>
 </div>
</div>
<!--登录-->
<div class="login_content Reg_log_style">
 <div class="mian_style relative">
  <div class="login_ad"></div>
  <div class="login_style">
   <div class="login_Switch">
    <div class="hd" style="background: blue;text-align: center"><h3>账户登录</h3></div>
    <div class="bd">
     <ul>
       <form class="write_login">
	     <fieldset>
		 <ul>
         <li class="frame_style form_error"><label class="user_icon"></label><input placeholder="用户名" type="text"  id="username"/></li>
         <li class="frame_style form_error"><label class="password_icon"></label><input placeholder="密码" type="password"   id="password"/></li>
         </ul>
		 <div class="space"></div>
         <div class="clearfix mb15 color9" >
          <label class="inline"><input type="checkbox" class="ace"><span class="lbl">保存密码</span></label>  
        </div>        
          <div class="space-4"><button type="button" class="width-35  btn btn-sm btn-primary" id="login_btn" onclick="">登陆</button></div>
          <div class="link_bz"><a href="#">忘记密码</a> | <a href="<%=basePath%>Home/toRegister">注册账号</a></div>
       </fieldset>
      </form>
     </ul>
    </div>
   </div>
  </div>
 </div>
</div>
<%@include file="../public/foot.jsp"%>
</body>
</html>
<script>
$('#login_btn').on('click', function(){
	     var num=0;
		 var str="";
    var _username = $("#username").val();
    var _password = $("#password").val();
     $("input[type$='text'],input[type$='password']").each(function(n){
          if($(this).val()=="")
          {
			   layer.alert(str+=""+$(this).attr("name")+"不能为空！\r\n",{
                title: '提示框',				
				icon:0,								
          }); 
		    num++;
            return false;            
          } 
		 });
		  if(num>0){  return false;}	 	
          else{

              $.ajax({
                  type : "POST",
                  url : '<%=basePath%>Customer/User/login',
                  data : {
                      "LoginId" : _username,
                      "Password" : _password
                  },
                  dataType:'json',
                  cache: false,
                  success: function(data){
                      if("200" == data.resultCode){
                          layer.alert('登陆成功！',{
                              title: '提示框',
                              icon:1
                          });
                          window.location.href="<%=basePath%>Home/index";
                          layer.close(index);
                      }else{
                          layer.alert('登陆失败！',{
                              title: '提示框',
                              icon:1,
                          });
                          $("#UserName").focus();
                      }
                  }
              });
		  }
	});
</script>
