<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <%@ include file="head.jsp" %>
</head>
<body class="overflow-hidden">
<div class="wrapper preload">
        <%@ include file="top.jsp" %>
         <!-- 左侧菜单 -->
        <%@ include file="left.jsp" %>
        <div class="main-container">
            <div class="padding-md">
                <h3 class="header-text">
                    总览
                </h3>
                <div class="row m-top-md">
                    <div class="col-lg-3 col-sm-6">
                        <div class="statistic-box bg-danger m-bottom-md">
                            <div class="statistic-title">
                                已抓取店铺数量
                            </div>

                            <div class="statistic-value">
                                ${result.shopNum}个
                            </div>

                            <div class="m-top-md"></div>
                            <div class="statistic-icon-background">
                                <i class="ion-eye"></i>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="statistic-box bg-info m-bottom-md">
                            <div class="statistic-title">
                               已抓取商品总数
                            </div>

                            <div class="statistic-value">
                                ${result.goodsNum}件
                            </div>

                            <div class="m-top-md"></div>

                            <div class="statistic-icon-background">
                                <i class="ion-ios7-cart-outline"></i>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="statistic-box bg-purple m-bottom-md">
                            <div class="statistic-title">
                                今日新增商品总数
                            </div>

                            <div class="statistic-value">
                                ${result.todayNewGoodsCounts}件
                            </div>

                            <div class="m-top-md"></div>

                            <div class="statistic-icon-background">
                                <i class="ion-person-add"></i>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="statistic-box bg-success m-bottom-md">
                            <div class="statistic-title">
                                今日爆品增量值
                            </div>
                            <div class="statistic-value">
                                ${result.maxNum}件
                            </div>
                            <div class="m-top-md"></div>
                            <div class="statistic-icon-background">
                                <i class="ion-stats-bars"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <h3 class="header-text">
                    功能
                </h3>
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="pricing-widget bg-danger colorful-widget">
                            <div class="pricing-icon">
                                <div class="pricing-icon-inner">
                                    <i class="fa fa-rocket"></i>
                                </div>
                            </div>

                            <div class="text-center">
                                <h4>收藏商品</h4>
                                <h4 class="pricing-value"><br></h4>
                            </div>

                            <ul class="pricing-service m-top-sm">
                                <li><br/><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><h3>收藏您关注的商品</h3><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                            </ul>
                            <div class="m-top-md text-center">
                                <%--<a class="btn btn-danger"></a>--%>
                                <%--<br><br>--%>
                            </div>
                        </div><!-- ./pricing-widget -->
                    </div><!-- .col -->

                    <div class="col-md-3 col-sm-6">
                        <div class="pricing-widget bg-warning colorful-widget">
                            <div class="pricing-icon">
                                <div class="pricing-icon-inner">
                                    <i class="fa fa-cubes"></i>
                                </div>
                            </div>

                            <div class="text-center">
                                <h4>今日热销</h4>
                                <h4 class="pricing-value"><br></h4>
                            </div>

                            <ul class="pricing-service m-top-sm">
                                <li><br/><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><h3>当日畅销的商品排行</h3><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                            </ul>

                            <div class="m-top-md text-center">
                                <%--<a class="btn btn-warning">即将上线，敬请期待</a>--%>
                                <%--<br><br>--%>
                            </div>
                        </div><!-- ./pricing-widget -->
                    </div><!-- .col -->

                    <div class="col-md-3 col-sm-6">
                        <div class="pricing-widget bg-success colorful-widget">
                            <div class="pricing-icon">
                                <div class="pricing-icon-inner">
                                    <i class="fa fa-desktop"></i>
                                </div>
                            </div>

                            <div class="text-center">
                                <h4>历史热销</h4>
                                <h4 class="pricing-value"><br></h4>
                            </div>

                            <ul class="pricing-service m-top-sm">
                                <li><br/><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><h4>往期畅销商品快照，快速对比热点商品</h4><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                            </ul>

                            <div class="m-top-md text-center">
                                <br>
                                <%--<a class="btn btn-success">即将上线，敬请期待</a>--%>
                            </div>
                        </div><!-- ./pricing-widget -->
                    </div><!-- .col -->

                    <div class="col-md-3 col-sm-6">
                        <div class="pricing-widget bg-info colorful-widget">
                            <div class="pricing-icon">
                                <div class="pricing-icon-inner">
                                    <i class="fa fa-trophy"></i>
                                </div>
                            </div>

                            <div class="text-center">
                                <h4>更多功能</h4>
                                <h4 class="pricing-value"><br></h4>
                            </div>

                            <ul class="pricing-service m-top-sm">
                                <li><br/><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><h3>持续更新中，为您贴心服务</h3><span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                                <li><br/> <span class="font-semi-bold"></span></li>
                            </ul>

                            <div class="m-top-md text-center">
                                <%--<a class="btn btn-info">即将上线，敬请期待</a>--%>
                            </div>
                        </div><!-- ./pricing-widget -->
                    </div><!-- .col -->
                </div><!-- ./row -->
            </div>
        </div>

    <%@ include file="foot.jsp"%>
</div>

<%--<script>$(function(){ ReadyDashboard.init(); });</script>--%>
</body>
</html>
