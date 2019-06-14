<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String relativePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/ecommerceBefore/";
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<head>
<title>用户中心-用户信息</title>
    <%@include file="../../public/top.jsp"%>
</head>

<body>
<!--顶部样式-->
<%@include file="../../public/head.jsp"%>
<!--用户中心-用户信息-->
  <div class="Insurance_content  pages_style">
  <div class="page clearfix">
    <div class="user_style clearfix">
      <!--左侧栏目样式属性-->
        <div class="left_style">
            <div class="menu_style">
                <div class="user_title">用户中心</div>
                <div class="user_Head">
                    <div class="user_portrait">
                        <a href="#" title="修改头像" class="btn_link"></a> <img src="<%=relativePath%>images/people.png">
                        <div class="background_img"></div>
                    </div>
                    <div class="user_name">
                        <p><span class="name">${sessionScope.sessionCustomerUser.loginId}</span></p>
                    </div>
                </div>
                <div class="sideMen">
                    <!--菜单列表图层-->
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage "><a href="#"><em class="icon_jiantou"></em>我的订单</a></dt>
                        <dd>
                            <ul>
                                <li> <a href="<%=basePath%>Customer/User/toUser">所有订单</a></li>
                            </ul>
                        </dd>
                    </dl>
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage on"><a href="#"><em class="icon_jiantou"></em>账户管理</a></dt>
                        <dd>
                            <ul>
                                <li> <a href="<%=basePath%>Customer/User/toUserMessage">个人信息</a></li>
                            </ul>
                        </dd>
                    </dl>
                </div>
                <script>jQuery(".sideMen").slide({titCell:"dt", targetCell:"dd",trigger:"click",defaultIndex:0,effect:"slideDown",delayTime:300,returnDefault:false});</script>
            </div>
        </div>
  <!--右侧样式-->
    <div class="right_style Plate_info ">
   
  <div class="title_name">用户信息</div>

  <div class="Personal_info " id="Personal">
  <ul class="xinxi">
  <li><label>登陆名：</label>  <span class="info_style"><input name="loginid" id="loginid" type="text" value="${sessionScope.sessionCustomerUser.loginId}" class="text text_info " disabled="disabled"></span></li>
  <li><label>真实姓名：</label>  <span class="info_style"><input name="name" id="name" type="text" value="${sessionScope.sessionCustomerUser.name}" class="text text_info " disabled="disabled"></span></li>
  <li><label>收货地址：</label>  <span class="info_style"><input name="address" id="address" type="text" value="${sessionScope.sessionCustomerUser.address}" class="text text_info " disabled="disabled"></span></li>
  <li><label>电子邮箱：</label>  <span class="info_style"><input name="email" id="email" type="text" value="${sessionScope.sessionCustomerUser.email}" class="text text_info " disabled="disabled"></span></li>
  <li><label>手机号：</label>  <span class="info_style"><input name="phonenumber" id="phonenumber" type="text" value="${sessionScope.sessionCustomerUser.phoneNumber}" class="text text_info " disabled="disabled"></span></li>
  <div class="Button_operation clearfix">
      <button onclick="modify();"    class="btn btn_bule radius" type="submit">修改信息</button>
      <button onclick="save_info();" class="btn btn_red radius " id="Save_info" type="button"  style="display:none">保存修改</button>
  </div>
  </ul>
  </div>
  </div>
    </div>
  </div>
  </div>
<!--底部样式-->
<%@include file="../../public/foot.jsp"%>
</body>
</html>
<script>
//按钮点击事件
function modify(){
	 $('.text_info').attr("disabled", false);
	 $('.text_info').addClass("add");
	  $('#Personal').find('.xinxi').addClass("hover");
	  $('#Personal').find('#Save_info').css({'display':'block'});
	};
function save_info(){
	    var num=0;
		 var str="";
     $(".xinxi input[type$='text']").each(function(n){
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
		      var postData ={
                  userid:'${sessionScope.sessionCustomerUser.userId}',
                  loginid:$('#loginid').val(),
                  name:$('#name').val(),
                  address:$('#address').val(),
                  email:$('#email').val(),
                  phonenumber:$('#phonenumber').val()
              };
              post('<%=basePath%>Customer/User/updateUser',postData);
		  }
	};

function post(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}
</script>