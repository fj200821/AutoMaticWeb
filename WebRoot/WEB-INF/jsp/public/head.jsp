<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String pathhead = request.getContextPath();
    String relativePathhead = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + pathhead + "/ecommerceBefore/";
    String basePathhead = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + pathhead + "/";
%>
<div id="header_top">
    <div id="top">
        <div class="top">
            <div class="hd_top_manu clearfix">
                <ul class="clearfix">
                    <li class="hd_menu_tit zhuce" data-addclass="hd_menu_hover">
                        <c:choose>
                            <c:when test="${ empty sessionScope.sessionCustomerUser}">
                                <a href="<%=basePathhead%>home/toLogin" class="red">[请登录]</a>
                                <a href="<%=basePath%>Home/toRegister" class="red">[免费注册]</a></li>
                            </c:when>
                            <c:otherwise>
                                    欢迎您，
                                <a href="#" class="red">${sessionScope.sessionCustomerUser.loginId}</a>
                                <a href="<%=basePath%>Customer/User/logout" class="red">[退出登录]</a></li>
                            </c:otherwise>
                        </c:choose>
                    <%--<c:if test="${ !empty sessionScope.sessionCustomerUser}">--%>
                        <%--<li class="hd_menu_tit list_name">--%>
                            <%--<a href="<%=basePath%>Customer/User/toUser" class="hd_menu">我的订单</a>--%>
                        <%--</li>--%>
                    <%--</c:if>--%>
                </ul>
            </div>
        </div>
    </div>
    <!--栏目-->
    <div class="header relative clearfix" style="height: 100px;">
        <div class="logo_style"><a href="<%=basePath%>Home/index" >
            <h2 class="pingtai_name" style="text-align: center;margin-top: 35px;">美妆综合商城</h2></a>
        </div>
        <div class="middle_style">
            <div class="Search">
                <p><select name="" class="select_name"><option value="1">全部</option></select>
                    <input id="seach" type="text" class="text" placeholder="请输入关键字">
                    <input name="" onclick="submit_btn()" type="submit" value="搜索" class="Search_btn"></p>
                <%--<p class="Words">热搜关键字：<a href="搜索.html">大众</a><a href="#">宝马</a><a href="#">太平洋保险</a><a href="#">福特</a><a href="#">本田</a><a href="#">哈佛</a></p>--%>
            </div>
        </div>
        <div class="contacttop_style">
        <div class="header_mycount" style="margin-top: 20px;">

            <a href="<%=basePath%>Customer/Cart/toCart" class="cart_style"><i class="icon_cart"></i>
                我的购物车
                <c:choose>
                    <c:when test="${!empty cart}">
                        <i class="ci-count">${cart.itemcount}</i>
                    </c:when>
                    <c:otherwise>
                        <i class="ci-count">0</i>
                    </c:otherwise>
                </c:choose>
            </a>
            <a href="<%=basePath%>Customer/User/toUser" class="user_header_style">
                <i class="icon_user"></i>个人中心
            </a>
        </div>
    </div>
    </div>
</div>
<script>
    /*********搜索*********/
    function submit_btn(){
        $(".Search input[type$='text']").each(function(n){
            if($(this).val()=="")
            {
                layer.alert("搜索框不能为空！",{
                    title: '提示框',
                    icon:0,
                });
                return false;
            }
            else{
                var seach = $("#seach").val();
                post("<%=basePath%>Home/toSeach",{seach:seach});

            }
        })
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