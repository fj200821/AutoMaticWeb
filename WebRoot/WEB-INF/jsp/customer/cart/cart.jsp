
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

<title>购物车</title>
    <%@include file="../../public/top.jsp"%>
</head>

<body>
<%@include file="../../public/head.jsp"%>
<!--购物车-->
<div class="pages_style">
<div class="page clearfix"> 
  <div class="shopping_cart" id="shopping_cart">
    <div class="hd">
     <ul>
      <li>我的购物车<b>(${cart.itemcount})</b></li>
     </ul>
    </div>
    <div class="bd">
    <c:if test="${cart.itemcount <= 0}">
            <div class="empty_style">
                <p><img src="<%=relativePath%>images/icon_empty.png" /></p>
                <h2>购物车里什么都没有，赶快去选择自己喜欢的商品吧！</h2>
            </div>
    </c:if>
    <ul class="cartBox">
    <div class="flow_step_no1 Process clearfix">
  <ul class="clearfix">
    <li class="step_number">1</li>
    <li class="step_name">选择商品添加到购物车</li>
    <li class="step_number">2</li>
    <li class="step_name">确认商品，生成订单</li>
    <li class="step_number">3</li>
    <li class="step_name">确认信息，选择支付方式</li>
  </ul>
  </div>
  <div class="Shopping_list">
  <div class="title_name">
    <ul class="clearfix">
	<li class="checkbox"></li>
	<li class="name">商品名称</li>
	<li class="scj">售价</li>
	<li class="bdj">商城</li>
	<li class="sl">购买数量</li>
	<li class="xj">小计</li>
	<li class="cz">操作</li>
  </ul>
 </div>
 <div class="shopping">
 <table class="table_list">
   <tbody>
   <c:forEach items="${cart.cartitems}" var="cartitem" varStatus="statuc">
       <tr class="tr
        <c:if test="${(statuc.index+1) % 2 == 0}">on</c:if>
        ">
          <%-- <td class="checkbox"><label class="inline"><input type="checkbox" checked="checked" class="ace"><span class="lbl"></span></label> </td>--%>
           <td class="name">
               <div class="img"><a href="<%=basePath%>Home/toProduct?productid=${cartitem.productid}"><img src="<%=basePath%>Home/onLineViewCourse?fileid=${cartitem.fileid}&floder=ProductImgFile"></a></div>
               <div class="p_name"><a href="#">${cartitem.productname}</a></div>
           </td>
           <td class="scj sp">￥${cartitem.price}元</td>
           <td class="bgj sp">${cartitem.storename}</td>
           <td class="sl">
               <div class="Numbers">
                   <input id="price${statuc.index+1}" name="price" type="hidden" value="${cartitem.price}">
                   <input id="subtotal${statuc.index+1}" name="subtotal" type="hidden" value="${cartitem.subtotal}">
                   <a href="javascript:void(0);" onclick="del('${statuc.index+1}','${cartitem.cartitemid}');" class="jian">-</a>
                   <input id="number${statuc.index+1}" name="number" type="text" value="${cartitem.count}" class="number_text">
                   <a href="javascript:void(0);" onclick="add('${statuc.index+1}','${cartitem.cartitemid}');" class="jia">+</a>
               </div>
           </td>
           <td class="xj" id="xj${statuc.index+1}">￥${cartitem.subtotal}元</td>
           <td class="cz">
               <a href="javascript:"  onclick="member_del(this,'${cartitem.cartitemid}')" class="btn  btn_huise mb15">移出购物车</a>
               <a href="<%=basePath%>Home/toProduct?productid=${cartitem.productid}" class="btn btn_bule mb15">查看详细</a>
           </td>
       </tr>
   </c:forEach>
 </tbody></table>
 <div class="sp_Operation">
  <div class="select-all">
  <%--<div class="cart-checkbox"><label class="inline"><input type="checkbox" class="ace"  checked="checked"><span class="lbl">全选</span></label></div>--%>
    </div>
	 <!--结算-->
	<div class="toolbar_right">
    <div class="p_Total">
        <input type="hidden" id="total" value="${cart.total}">
	  <span class="text">总价：</span><span class="price sumPrice" ><b id="totalb">${cart.total}</b>元</span>
	</div>
	
	<div class="btn_style clearfix">
	 <a class="cartsubmit btn" style="margin-bottom: 10px;margin-top: 10px" href="<%=basePath%>Home/index">继续购买</a>

	 <a class="continueFind btn" href="#" onclick="createdOrder('${cart.cartid}')">支付</a>
	</div>
  </div>
  </div>
 </div>
</div>
    </ul>
    </div>
  </div>
</div>
<!--底部样式-->
<%@include file="../../public/foot.jsp"%>
</body>
</html>
<script>
    function del(inputid,id) {
        if (parseInt($("#number"+inputid).val())>0) {
            var num_dec= parseInt($("#number"+inputid).val())-1;
            if (num_dec<1) {
                return;
            }
            var price = $("#price"+inputid).val();
            var subtotal = $("#subtotal"+inputid).val();
            var total = parseFloat(subtotal)-parseFloat(price);
            $("#number"+inputid).val(num_dec);
            $("#xj"+inputid).html("￥"+total+"元");
            $("#subtotal"+inputid).val(total)

            var carttotal = $("#total").val();
            $("#totalb").html(parseFloat(carttotal)-parseFloat(price))
            $("#total").val(parseFloat(carttotal)-parseFloat(price));

            $.ajax({
                type : "POST",
                url : '<%=basePath%>Customer/Cart/delCount',
                data : {
                    "cartitemid" : id
                },
                dataType:'json',
                cache: false,
                success: function(data){

                }
            });
        }

    }

    function add(inputid,id) {
        var num_add= parseInt($("#number"+inputid).val())+1;
        var price = $("#price"+inputid).val();
        var subtotal = $("#subtotal"+inputid).val();
        var total = (parseFloat(subtotal)+parseFloat(price)).toFixed(2);
        $("#number"+inputid).val(num_add);
        $("#xj"+inputid).html("￥"+total+"元");
        $("#subtotal"+inputid).val(total)

        var carttotal = $("#total").val();
        $("#totalb").html((parseFloat(carttotal)+parseFloat(price)).toFixed(2))
        $("#total").val((parseFloat(carttotal)+parseFloat(price)).toFixed(2));
        $.ajax({
            type : "POST",
            url : '<%=basePath%>Customer/Cart/addCount',
            data : {
                "cartitemid" : id
            },
            dataType:'json',
            cache: false,
            success: function(data){

            }
        });
    }
 /*产品-删除*/
function member_del(obj,id){
	layer.confirm('确定要删除吗？',function(index){
        $.ajax({
            type : "POST",
            url : '<%=basePath%>Customer/Cart/deleteCart',
            data : {
                "id" : id
            },
            dataType:'json',
            cache: false,
            success: function(data){
                if("200" == data.resultCode){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                    location.reload();
                }else{
                    layer.alert('删除失败！',{
                        title: '提示框',
                        icon:1,
                    });
                }
            }
        });
	});
}
 function createdOrder(id){
     $.ajax({
         type : "POST",
         url : '<%=basePath%>Customer/Pay/createdOrder',
         data : {
             "cartid" : id
         },
         dataType:'json',
         cache: false,
         success: function(data){
             if(data.resultCode != '404'){
                 location.href='<%=basePath%>Customer/Pay/toOrder?orderid='+data;
             }else{
                 layer.alert('生成订单失败！'+data.error,{
                     title: '提示框',
                     icon:1,
                 });
             }
         }
     });
 }
</script>
