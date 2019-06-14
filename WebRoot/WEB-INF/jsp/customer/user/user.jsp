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

<title>用户中心</title>
    <%@include file="../../public/top.jsp"%>
</head>

<body>
<!--顶部样式-->
<!--顶部样式-->
<%@include file="../../public/head.jsp"%>
   <!--用户中心-->
 <div class="Insurance_content  pages_style">
  <div class="page clearfix">
  <div class="user_style clearfix">
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
             <dt class="transaction_manage"><a href="#"><em class="icon_jiantou"></em>我的订单</a></dt>
             <dd>
                 <ul>
                     <li> <a href="<%=basePath%>Customer/User/toUser">所有订单</a></li>
                 </ul>
             </dd>
         </dl>
         <dl class="accountSideOption1">
             <dt class="transaction_manage"><a href="#"><em class="icon_jiantou"></em>账户管理</a></dt>
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
  <!--右侧内容-->
  <div class="right_style user_center ">
   <div class="user_header ">
   <ul class="Order_link">
       <li class="Balance"><a href="#"><img src="<%=relativePath%>images/icon_zhifu.png"><h4>待支付订单</h4>
           <c:choose>
               <c:when test="${empty statuscount.Init}"><h5>0</h5></c:when>
               <c:otherwise>
                   <h5>${statuscount.Init}</h5>
               </c:otherwise>
           </c:choose>
       </a></li>
       <li class="Order_form"><a href="#"><img src="<%=relativePath%>images/icon_car.png"><h4>已取消订单</h4>
           <c:choose>
               <c:when test="${empty statuscount.Cancel}"><h5>0</h5></c:when>
               <c:otherwise>
                   <h5>${statuscount.Cancel}</h5>
               </c:otherwise>
           </c:choose>
       </a></li>
       <li class="grade"><a href="#"><img src="<%=relativePath%>images/icon_Order.png"><h4>待确认订单</h4>
           <c:choose>
               <c:when test="${empty statuscount.Pay && empty statuscount.Deliver}"><h5>0</h5></c:when>
               <c:otherwise>
                   <h5>${statuscount.Pay + statuscount.Deliver}</h5>
               </c:otherwise>
           </c:choose>
       </a></li>
       <li class="Favorable"><a href="#"><img src="<%=relativePath%>images/icon_Order.png"><h4>已完成订单</h4>
           <c:choose>
               <c:when test="${empty statuscount.Complete}"><h5>0</h5></c:when>
               <c:otherwise>
                   <h5>${statuscount.Complete}</h5>
               </c:otherwise>
           </c:choose>
       </a></li>
      </ul>
   </div>
   <div class="Order_style clearfix">
    <div class="left_order_list">
      <div class="Order_name">
       <ul>
        <li class="on">全部订单(${ordercount})</li>
       </ul>
      </div>
      <div>
       <table class="Inquiry_order">
 <thead>
  <tr>
   <th width="350px">商品信息</th>
   <th width="200px">商城</th>
   <th width="100px">状态</th>
   <th width="200px">价格</th>
  </tr>
 </thead>
   <c:forEach items="${orderlist}" var="order">
       <tbody style="margin-bottom: 20px;">
       <tr class="order_number">
           <td colspan="5"><span class="Numbering">订单号：</span>${order.ordernumber} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="time">时间：${order.orderdate}</span></td>
       </tr>
       <c:forEach items="${order.orderitems}" var="orderitem">
           <tr class="Inquiry_order_content">
               <td class="Product_info">
                   <a href="#" class="car_img" ><img src="<%=basePath%>Home/onLineViewCourse?fileid=${orderitem.fileid}&floder=ProductImgFile" width="100px" height="100px"></a>
               </td>
               <td>${orderitem.storename}</td>
               <td>${order.statusvalue}</td>
               <td><i>￥</i>${orderitem.price}元</td>
           </tr>
       </c:forEach>
       <tr class="order_number" >
           <td colspan="2" style="font-weight: bold;color: #FF4F53;">总价：￥${order.price}元</td>
           <c:if test="${order.status == 'Init' }">
               <td> <a href="#" onclick="updateStatus('Cancel','${order.orderid}');" class="btn btn_red">取消</a></td>
               <td> <a href="#" onclick="updateStatus('Pay','${order.orderid}');" class="btn btn_red">支付</a></td>
           </c:if>
           <c:if test="${order.status == 'Deliver' }">
               <td> <a href="#" onclick="updateStatus('Complete','${order.orderid}');" class="btn btn_red">确认收货</a></td>
           </c:if>
       </tr>
       </tbody>
   </c:forEach>
</table>
      </div>
    </div>
    <div class="right_"></div>
   </div>
  </div>
  </div>
 </div>
 </div>
<!--优势-->
<%@include file="../../public/foot.jsp"%>
</body>
</html>
<script type="application/javascript">
    function updateStatus(status,orderid) {
        $.ajax({
            type : "POST",
            url : '<%=basePath%>Customer/User/updateOrderStatus',
            data : {
                "status" : status,
                "orderid" : orderid
            },
            dataType:'json',
            cache: false,
            success: function(data){
                if("200" == data.resultCode){
                    layer.alert("操作成功！",{
                        title: '提示框',
                        icon:0,
                    });
                    location.reload();
                }else{
                    layer.alert('操作失败！',{
                        title: '提示框',
                        icon:1,
                    });
                }
            }
        });
    }
</script>

