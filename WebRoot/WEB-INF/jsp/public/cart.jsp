<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String pathcart = request.getContextPath();
    String relativePathcart = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + pathcart + "/ecommerceBefore/";
    String basePathcart = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + pathcart + "/";
%>
<div class="fixedBox">
    <ul class="fixedBoxList">
        <li class="fixeBoxLi cart_bd" style="display:block;" >
            <c:choose>
                <c:when test="${!empty cart}">
                    <p class="good_cart">${cart.itemcount}</p>
                </c:when>
                <c:otherwise>
                    <p class="good_cart">0</p>
                </c:otherwise>
            </c:choose>
            <span class="fixeBoxSpan"></span> <a href="<%=basePath%>Customer/Cart/toCart"><strong>购物车</strong></a></li>
        <li class="fixeBoxLi Home"> <a href="<%=basePathcart%>Home/index"> <span class="fixeBoxSpan"></span> <strong>返回首页</strong> </a> </li>
        <li class="fixeBoxLi BackToTop"> <span class="fixeBoxSpan"></span> <strong>返回顶部</strong> </li>
    </ul>
</div>