<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String relativePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/ecommerce/";
	String relativePathTravel = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/travel/";
	String bootstrapPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/bootstrap/assets/";
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
<!-- ionicons -->
<!-- Morris -->
<link href="<%=relativePath%>css/morris.css" rel="stylesheet"/>
<!-- Datepicker -->
<link href="<%=relativePath%>css/datepicker.css" rel="stylesheet"/>
<!-- Animate -->
<link href="<%=relativePath%>css/animate.min.css" rel="stylesheet">
<!-- Owl Carousel -->
<link href="<%=relativePath%>css/owl.carousel.min.css" rel="stylesheet">
<link href="<%=relativePath%>css/owl.theme.default.min.css" rel="stylesheet">
<!-- Simplify -->
<link href="<%=relativePath%>css/simplify.min.css" rel="stylesheet">

<link rel="stylesheet" href="<%=bootstrapPath%>css/bootstrap-table.css">
<link rel="stylesheet" href="<%=bootstrapPath%>select/css/bootstrap-select.min.css">
<link rel="stylesheet" href="<%=relativePath%>js/bootstrap-datepicker/css/bootstrap-datetimepicker.min.css">

<script src="<%=relativePathTravel%>js/vendor/jquery-2.2.4.min.js"></script>
<script src="<%=relativePathTravel%>js/vendor/bootstrap.min.js"></script>

<%--<script src="<%=relativePathTravel%>js/vendor/modernizr-3.3.1.min.js"></script>
<script src="<%=relativePathTravel%>js/plugins.js"></script>
<script src="<%=relativePathTravel%>js/app.js"></script>
<script src="<%=relativePathTravel%>js/pages/readyDashboard.js"></script>--%>

<%--ECommerce--%>
<%--<script src="<%=relativePath%>js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="<%=relativePath%>js/bootstrap.min.js"></script>--%>
<!-- Flot -->
<%--<script src='<%=relativePath%>js/jquery.flot.min.js'></script>--%>
<!-- Slimscroll -->
<script src='<%=relativePath%>js/jquery.slimscroll.min.js'></script>
<!-- Morris -->
<script src='<%=relativePath%>js/rapheal.min.js'></script>
<script src='<%=relativePath%>js/morris.min.js'></script>
<!-- Datepicker -->
<script src='<%=relativePath%>js/uncompressed/datepicker.js'></script>
<!-- Sparkline -->
<script src='<%=relativePath%>js/sparkline.min.js'></script>
<!-- Skycons -->
<script src='<%=relativePath%>js/uncompressed/skycons.js'></script>
<!-- Popup Overlay -->
<script src='<%=relativePath%>js/jquery.popupoverlay.min.js'></script>
<!-- Easy Pie Chart -->
<script src='<%=relativePath%>js/jquery.easypiechart.min.js'></script>
<!-- Sortable -->
<script src='<%=relativePath%>js/uncompressed/jquery.sortable.js'></script>
<!-- Owl Carousel -->
<script src='<%=relativePath%>js/owl.carousel.min.js'></script>
<!-- Modernizr -->
<script src='<%=relativePath%>js/modernizr.min.js'></script>
<!-- Simplify -->
<script src="<%=relativePath%>js/simplify/simplify.js"></script>

<%--<script src="<%=relativePath%>js/simplify/simplify_dashboard.js"></script>--%>


<%--Common--%>
<script src="<%=bootstrapPath %>js/bootstrap-table.js"></script>
<script src="<%=bootstrapPath%>bootstrapValidator/bootstrapValidator.js"></script>
<script src="<%=bootstrapPath %>bootstrapValidator/language/zh_CN.js"></script>
<script src="<%=bootstrapPath %>bootstrapValidator/validator/notEmpty.js"></script>
<script src="<%=bootstrapPath %>js/bootstrap-table-zh-CN.js"></script>
<script src="<%=bootstrapPath %>select/bootstrap-select.min.js"></script>
<script src="<%=bootstrapPath %>js/form/form.js"></script>
<script src="<%=bootstrapPath %>js/public.js"></script>
<script src="<%=bootstrapPath %>js/jquery-validate/jquery.validate.js"></script>
<script src="<%=bootstrapPath %>js/bootstrap-datepicker/bootstrap-datetimepicker.js"></script>
<script src="<%=bootstrapPath %>js/bootstrap-datepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=bootstrapPath %>js/message.js"></script>
<style>
	td {
		vertical-align: middle !important;
	}
</style>