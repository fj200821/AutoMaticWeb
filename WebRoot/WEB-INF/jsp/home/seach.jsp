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
<title>搜索</title>
    <%@include file="../public/top.jsp"%>
</head>

<body>
<div class="search_header">
 <div class="header clearfix">
  <div class="search_logo"></div>
  <div class="search_bar">
   <input type="text" id="seach" class="text add_search" placeholder="请输入关键字">
      <button name="" type="submit"  onclick="submit_btn()" class="Search_btn">
          <i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
  </div>
 </div>
</div>
<div class="page">
 <div class="mt20 Search_Content clearfix">
  <div class="left_content">
  <div class="search_results mb15">
   <h4>搜索结果：</h4>
   <p>关键字<b>"${pd.seach}"</b>共搜索出${count}条结果。</p>
   <a href="<%=basePath%>Home/index" class="btn btn_huise radius">返回首页</a>
  </div>
  <!--热销模块-->
  <div class="Hot_modules mb15">
   <div class="modules_name"><a href="#"><b>${pd.seach}</b>热销商品 <span class="prompt"></span></a></div>
   <ul class="hot_list clearfix">
       <c:forEach items="${products}" var="product" varStatus="status">
           <li class="Introduction ">
               <em class="Ranking <c:if test="${status.index+1 <= 3}">color_red</c:if>">${status.index+1}</em>
               <span class="hot_carimg"><a href="<%=basePath%>Home/toProduct?productid=${product.productid}"><img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile"  width="100%"/></a></span>
               <span class="car_content">
                  <a href="#" class="name">【${product.storename}】${product.productname}</a>
                  <span class="Quantity">售价：<i>${product.pricesale}元</i><br />价格：<em>${product.pricesale}元</em><br /></span>
                </span>
           </li>
       </c:forEach>
   </ul>
   <div class="more"><a href="<%=basePath%>Home/index" target="_blank" data="" class="hoveraddunderline">查看更多&gt;&gt;</a></div>
  </div>
   <!--产品模块-->
<!--   <div class="Product_module mb15">
   <div class="modules_name"><a href="cars_list.html"><b>大众</b>相关车源&nbsp;&nbsp;苏车保汽车城</a></div>
   <ul class="clearfix Newcar_list">
    <li class="Newcar_content">
     <a href="产品详细.html" class="car_img"><img src="<%=relativePath%>images/products/3.jpg"  width="100%"/></a>
     <a href="产品详细.html" class="name">上汽<i class="font_red">大众</i>—全新途观L</a>
     <p class=" clearfix"><span class="Pricing">￥999元</span><span class="Sales">已销售<i>123</i>辆</span></p>
    </li>
     <li class="Newcar_content">
     <a href="#" class="car_img"><img src="<%=relativePath%>images/products/3.jpg"  width="100%"/></a>
     <a href="#" class="name">上汽大众—全新途观L</a>
     <p class=" clearfix"><span class="Pricing">￥999元</span><span class="Sales">已销售<i>123</i>辆</span></p>
    </li>
     <li class="Newcar_content">
     <a href="#" class="car_img"><img src="<%=relativePath%>images/products/3.jpg"  width="100%"/></a>
     <a href="#" class="name">上汽<i class="font_red">大众</i>—全新途观L</a>
     <p class=" clearfix"><span class="Pricing">￥999元</span><span class="Sales">已销售<i>123</i>辆</span></p>
    </li>
     <li class="Newcar_content">
     <a href="#" class="car_img"><img src="<%=relativePath%>images/products/3.jpg"  width="100%"/></a>
     <a href="#" class="name">上汽<i class="font_red">大众</i>—全新途观L</a>
     <p class=" clearfix"><span class="Pricing">￥999元</span><span class="Sales">已销售<i>123</i>辆</span></p>
    </li>
   </ul>
    <div class="more"><a href="#" target="_blank" data="" class="hoveraddunderline">查看更多新车来源&gt;&gt;</a></div>
   </div>-->
    <!--搜索-->
    <div class="footer_search_style">
      <div class="search_bar">
   <input name="" type="text" class="text add_search" placeholder="请输入关键字"><button name="" type="submit" class="Search_btn"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
  </div>
    </div>
  </div>
  <!---->
  <%--<div class="right_content">--%>
   <%--<a href="#" class="searchad_img"><img src="<%=relativePath%>images/ad_6.jpg"  width="100%"/></a>--%>
   <%--<div class="recommend_style mt15">--%>
   <%--<div class="recommend_title">新车推荐</div>--%>
   <%--<div class="recommend_car_name mb15">--%>
    <%--<a href="#" class="recommend_carimg"><img src="<%=relativePath%>images/products/3.jpg"  width="100%"/></a>--%>
    <%--<a href="#" class="name">英朗 2016款 15N 自动精英型</a>--%>
    <%--<p class="Introduction">【通远汽车】只需一张身份证首付3万提车，代办京津冀验车</p>--%>
    <%--<p><span class="Quantity">裸车价：<i>11.45</i>万</span></p>--%>
   <%--</div>--%>
   <%--</div>--%>
  <%--</div>--%>
 </div>
</div>
<!---->
<%@include file="../public/foot.jsp"%>
</body>
</html>
<script>
function submit_btn(){
	 $(".search_bar input[type$='text']").each(function(n){
		  if($(this).val()==""){
			   layer.alert("搜索框不能为空！",{
                title: '提示框',				
				icon:0
                });
            return false;            
          } else{
              var seach = $("#seach").val();
              post("<%=basePath%>Home/toSeach",{seach:seach});
          }
		 });
}
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
