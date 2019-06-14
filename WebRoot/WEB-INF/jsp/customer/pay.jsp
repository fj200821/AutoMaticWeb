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
<html>
<head>
<title>支付页面</title>
    <%@include file="../public/top.jsp"%>
</head>

<body>
<%@include file="../public/head.jsp"%>
<!--订单支付-->
<div class="Insurance_content clearfix">
<div class="page">
<!--订单信息-->
<div class="order_info">
    <div class="title_name">订单信息：订单编号【${order.ordernumber}】创建时间：${order.orderdate}</div>
 <div class="product_List clearfix">
  <table>
       <thead><tr class="title"><td class="name">商品名称</td><td class="price">商品价格</td><td class="Preferential">商城</td><td class="Quantity">购买数量</td><td class="Money">金额</td></tr></thead>
       <tbody>
        <c:forEach items="${order.orderitems}" var="orderitem" varStatus="statuc">
            <tr>
                <td class="Product_info">
                    <a href="#" class="car_img" ><img src="<%=basePath%>Home/onLineViewCourse?fileid=${orderitem.fileid}&floder=ProductImgFile" width="100px" height="100px"></a>
                    <span class="product_style"> <a href="#" class="p_name">${orderitem.productname}</a><p>商城：${orderitem.storename}</p></span>
                </td>
                <td><i>￥</i>${orderitem.price}元</td>
                <td>${orderitem.storename}</td>
                <td>${orderitem.count}</td>
                <td class="Moneys"><i>￥</i>${orderitem.subtotal}元</td>
            </tr>
        </c:forEach>
       </tbody>
      </table>
 </div>
    <div class="lump_sum">收货地址：${order.customeraddressid}</div>
 <div class="lump_sum">支付金额：<i>￥</i><b>${order.price}</b>元</div>
</div>
<!--支付方式-->
<Div class="payment_method">
<div class="title_name">支付方式</div>
<div class="Payment_tips">请根具你自己的需求，选择付款的方式，现只支持支付宝、微信、银联支付三种付款方式。</div>
<div class=" margin clearfix">
 <a href="#" class="Payment_operation"><img src="<%=relativePath%>images/zhifubao.jpg" /></a>
 <a href="#" class="Payment_operation"><img src="<%=relativePath%>images/yinlianzf.jpg" /></a>
 <a href="#" class="Payment_operation"><img src="<%=relativePath%>images/weixinzhif.jpg" /></a>
</div>
</Div>

</div>
</div>
<!--优势-->
<%@include file="../public/foot.jsp"%>
</body>
</html>
