<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String relativePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/ecommerceBefore/";
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<head>
<title>产品详细</title>
    <%@include file="../public/top.jsp"%>
    <script type="application/javascript">
        function add() {
                var num_add= parseInt($("#buy-num").val())+1;
                $("#buy-num").val(num_add);
        }
        function del() {
            if (parseInt($("#buy-num").val())>0) {
                var num_dec= parseInt($("#buy-num").val())-1;
                if (num_dec<0) {
                    //alert("必须大于0！");
                } else {
                    $("#buy-num").val(num_dec);
                }
            }
        }
    </script>
</head>

<body>
<!--顶部样式-->
<%@include file="../public/head.jsp"%>
<!--汽车详细介绍-->
<div class="pages_style">
<div class="page clearfix"> 
 <!--面包屑-->
   <div class="marginsx20">
       <div class="Location_link"><em></em><a href="#">首页<</a><a href="#">商品</a> < <a href="#">${prodcut.categoryname}</a>  </div>
     <div class="cars_detailed" id="goodsInfo">
      <div class="left_cars_img mod_picfold ">
 <div class="clearfix" id="detail_main_img">
	 <div class="layout_wrap preview">
     <div id="vertical" class="bigImg">
		<img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile" width="" height="" alt="" id="midimg" />
		<div id="winSelector"></div>
	</div>
     <%--<div class="smallImg">--%>
         <%--<div class="scrollbutton smallImgUp disabled">&lt;</div>--%>
         <%--<div id="imageMenu">--%>
             <%--<ul>--%>
                 <%--<li><img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile" width="68" height="68" alt="汽车"/></li>--%>
                 <%--<li><img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile" width="68" height="68" alt="汽车"/></li>--%>
                 <%--<li><img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile" width="68" height="68" alt="汽车"/></li>--%>
                 <%--<li><img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile" width="68" height="68" alt="汽车"/></li>--%>
                 <%--<li><img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile" width="68" height="68" alt="汽车"/></li>--%>
             <%--</ul>--%>
         <%--</div>--%>
         <%--<div class="scrollbutton smallImgDown">&gt;</div>--%>
     <%--</div>--%>
	<div id="bigView" style="display:none;"><div><img width="1000" height="800" alt="" src="" /></div></div>
	 </div>
	</div>
      </div>
      <!--右侧内容-->
      <div class="right_cars_content">
        <div class="Product_title">
         <h2>【${product.storename}】${product.productname}</h2>
         <h5>${product.descriptionshort}</h5>
        </div>
        <div class="cars_price">
         <dl>
         <dt>一口价：</dt>
         <dd><em>￥</em><b>${product.price}</b><em>元</em></dd>
         <dd class="Guide_price">
         <a href="#" class="Landing_price" id="Landing_price">原价:&nbsp;<span>${product.pricesale} <span class="iconDetail"></span>元</span>
         </a>
         </dd>
         </dl>
         <div class="inventory"><p>库存</p><b>${product.virtualstock}</b></div>
        </div>

        <div class="btn_operating" style="margin-top: 200px;">
        <div class="choose-amount left">
			<a class="btn-reduce" href="javascript:;" onclick="del()">-</a>
			<a class="btn-add" href="javascript:;" onclick="add()">+</a>
			<input class="text" id="buy-num" value="1"/>
		 </div>
           <a class="btn btn-buy2 btn-red" id="detailBuyButton" href="javascript:void(0)" onclick="shopping_cart_btn();" target="_self">加入购物车</a>
        </div>
        <div class="Guarantee clearfix">
	   <dl><dt>支付方式</dt><dd><em></em>在线支付</dd><dd><em></em>安全保障</dd> 
	   <dd> <div class="payment" id="payment"> 
       <a href="javascript:void(0);" class="paybtn">支持付款<span class="iconDetail"></span></a><ul><li>货到付款</li><li>支付宝</li><li>微信支付</li><li>银行转账</li></ul>
       </div>
	  </dd>
      </dl>
     </div>
      </div>
     </div> 
     <!--内容-->
     <div class="clearfix marginsx20">
      <div class="left_filter">
      <!--品牌-->
       <div class="car_Brand_style">
       <div class="title_name">${product.storename}</div>
        <div class="logo_Brand"><img src="<%=relativePath%>images/logo/logo.jpg"  width="100%" height="100%"/></div>
        <%--<div class="xs_species">种类：6</div>--%>
       </div>
       <!--其他汽车-->
       <div class="other_cars">
         <div class="title_name">该商铺下其他商品</div>
         <ul>
             <c:forEach items="${others}" var="otherProduct">
                 <li class="gl-item Brand_car_list">
                     <div class="Borders">
                         <div class="img"><a href="<%=basePath%>Home/toProduct?productid=${otherProduct.productid}"><img src="<%=basePath%>Home/onLineViewCourse?fileid=${otherProduct.fileid}&floder=ProductImgFile" style="width:220px;height:165px"></a></div>
                         <div class="Price"><em>￥</em><b>${otherProduct.pricesale}元</b><span>${otherProduct.price}元</span></div>
                         <div class="name"><a href="#" title="【'+${otherProduct.storename}'+】${otherProduct.productname}">【${otherProduct.storename}】${otherProduct.productname}</a></div>
                         <div class="carbox-tip" title="'+${otherProduct.descriptionshort}+'">${otherProduct.descriptionshort}</div>
                         <div class="Review">已有<a href="#">2345</a>购买记录</div>
                     </div>
                 </li>
             </c:forEach>
         </ul>
       </div>
      </div>

      <div class="right_cars_list">
      <div class="slidecarBox">
       <div class="inDetail_boxOut ">
	 <div class="inDetail_box">
	  <div class="fixed_out hd">
	   <ul class="inLeft_btn fixed_bar">
           <li class="on">产品介绍</li>
           <li >评论</li>
       </ul>
          <div class="subbuy">
          <span class="extra currentPrice"> </span>
          <a href="#" class="extra  notice J_BuyButtonSub"></a></div>
	  </div>
	 </div>	  
	</div>
    <div class="info_style bd">
     <ul>
       ${product.description}
     </ul>
        <ul>
            <div class="productContent" id="status2">
                <div class="commentBox" >
                    <form action="javascript:;"  method="post" name="commentForm" id="commentForm">
                        <h4>评论内容</h4>
                        <div class="bd">
                            <textarea name="message" id="message" class="textarea_long"></textarea>
                            <p class="g">
                                <label>&nbsp; </label>
                                <input type="button" onclick="addComment();" value="发表" class="btn btn_common">
                                <%--<span t="word_calc" class="word"><b id="sy">0</b>/1000</span> </p>--%>
                            </p>
                        </div>
                    </form>
                </div>
                <div id="ECS_COMMENT"> <div class="Comment">
                    <div class="CommentTab">
                        <ul>
                            <li class="active">全部评论(${fn:length(comments)})</li>
                        </ul>
                    </div>
                    <div class="CommentText" id="goodsdetail_comments_list" style="display:block">
                        <ul class="clearfix">
                            <c:forEach items="${comments}" var="comment">
                                <li class="Comments_content">
                                    <div class="username">
                                        <h5>${comment.username}</h5>
                                    </div>
                                    <div class="commentC">
                                       ${comment.message}
                                        <span class="commenttime">发表时间：${comment.createdtime}</span></div>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="comment_page clearfix">
                            <div class="comment_Number"> <span class="f_l f6" style="margin-right:10px;">共 <b>${fn:length(comments)}</b> 条评论</span>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
                <script type="text/javascript">
                    $('.CommentTab ul').find('li').click(function(){
                        $('.CommentTab ul').find('li').removeClass('active');
                        $('.CommentText').css({display:'none'});
                        $(this).addClass('active');
                        $('.CommentText').eq($(this).index()).css({display:'block'});
                    });
                </script>
            </div>
        </ul>
    </div>
      </div>
      <script>jQuery(".slidecarBox").slide({trigger:"click",delayTime:0});</script>
     </div>
   </div>
   </div>
</div>
</div>
<!--底部样式-->
<!--底部样式-->
<%@include file="../public/foot.jsp"%>
<!--浮动图层-->
 <!--右侧菜单栏购物车样式-->
<%@include file="../public/cart.jsp"%>
<!--确认购买-->
</div>
<div class="none Shopping_cart_tips" id="Shopping_cart_tips">
 <img src="<%=relativePath%>images/success.png" />
 <h4>加入购物车成功</h4>
</div>
</body>
</html>
<script type="application/javascript">
    function addComment(){
        var postData ={
            userid:'${sessionScope.sessionCustomerUser.userId}',
            productid:'${product.productid}',
            message:$('#message').val()
        };
        post('<%=basePath%>Customer/User/addComment',postData);
    }
function shopping_cart_btn(){
    var count = $('#buy-num').val();
    if('${sessionScope.sessionCustomerUser}' == ''){
        location.href="<%=basePath%>Home/toLogin";
    }
    $.ajax({
        type : "POST",
        url : '<%=basePath%>Customer/Cart/addCart',
        data : {
            "productid" : '${product.productid}',
            "count":count,
            "userid" : '${sessionScope.sessionCustomerUser.userId}'
        },
        dataType:'json',
        cache: false,
        success: function(data){
            if("200" == data.resultCode){

                layer.open({
                    type: 1,
                    shade: false,
                    title:'提示框',
                    area: ['350px'],
                    content:$('#Shopping_cart_tips'),
                    btn: ['立即支付','继续购物'],
                    yes:function(index){
                        location.href="<%=basePath%>Customer/Cart/toCart";
                        layer.close(index);
                    },
                    btn2:function () {
                        location.reload();
                    }
                });

            }else{
                layer.alert('添加购物车失败！',{
                    title: '提示框',
                    icon:1,
                });
            }
        }
    });

}
</script>
