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
<title>网站首页</title>
    <%@include file="../public/top.jsp"%>
</head>

<body>
<!--顶部样式-->
<%@include file="../public/head.jsp"%>
<!--汽车列表-->
<div class="pages_style">
    <div class="page clearfix">
        <div class="marginsx20">
            <!--面包屑-->
            <div class="Location_link">
                <em></em><a href="<%=basePath%>Home/index">首页</a>  &lt;   <a href="#">商品</a>
            </div>
            <div class="left_filter">
                <!--分类-->
                <div class="classification Brand_selection">
                    <div class="title_name">商品分类 <a href="<%=basePath%>Home/index" class="Brand_link">全部</a></div>
                    <div id="Brandselectbox" class="clearfix sideMenu" >
                        <div class="Menu_list">
                            <c:forEach items="${categoryList}" var="parentCategory">
                                <div class="name">
                                    <div class="Menu_name"><a href="#" >${parentCategory.categoryname}</a></div>
                                    <ul class="link_name">
                                        <c:forEach items="${parentCategory.childrencategorys}" var="childrenCategory">
                                            <a href="<%=basePath%>Home/index?categoryid=${childrenCategory.categoryid}" class="Brand_link_name">${childrenCategory.categoryname}</a>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div >
                <script type="text/javascript">
                    jQuery(".sideMenu").slide({
                        titCell:".Menu_name", //鼠标触发对象
                        targetCell:"ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
                        effect:"slideDown", //targetCell下拉效果
                        trigger:"click",
                        defaultIndex:0,
                        triggerTime:150, //鼠标延迟触发时间（默认150）
                        defaultPlay:true,//默认是否执行效果（默认true）
                        returnDefault:false //鼠标从.sideMen移走后返回默认状态（默认false）
                    });
                </script>
            </div>
            <div class="right_cars_list">
                <!--条件筛选-->
                <div class="Filter">
                    <div id="demo5"></div>
                    <div id="demo6"></div>
                    <div id="demo7"></div>
                    <div id="demo9"></div>
                </div>
                <!--产品列表-->
                <div class="p_car_list clearfix marginsx">
                    <ul class="">

                        <c:forEach items="${products}" var="product">
                            <li class="gl-item">
                                <div class="Borders">
                                    <div class="img"><a href="<%=basePath%>Home/Product/toProduct?productid=${product.productid}"><img src="<%=basePath%>Home/onLineViewCourse?fileid=${product.fileid}&floder=ProductImgFile" style="width:220px;height:165px"></a></div>
                                    <div class="Price"><em>￥</em><b>${product.pricesale}元</b><span>${product.price}元</span></div>
                                    <div class="name"><a href="#" title="【'+${product.storename}'+】${product.productname}">【${product.storename}】${product.productname}</a></div>
                                    <div class="carbox-tip" title="'+${product.descriptionshort}+'">${product.descriptionshort}</div>
                                    <%--<div class="Review">已有<a href="#">2345</a>购买记录</div>--%>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <c:if test="${empty products}">
                       <div class="empty_style">
                        <p><img src="<%=relativePath%>images/icon_empty.png" /></p>
                        <h2>商品列表为空，你查找的商不存在，请从新查找，</h2>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部样式-->
<%@include file="../public/foot.jsp"%>
<!--浮动图层-->
<!--右侧菜单栏购物车样式-->
<%@include file="../public/cart.jsp"%>

</body>
</html>

