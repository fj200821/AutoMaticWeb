<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String pathtop = request.getContextPath();
    String relativePathtop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + pathtop + "/ecommerceBefore/";
    String basePathtop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + pathtop + "/";
%>
<link href="<%=relativePathtop%>css/common.css" rel="stylesheet"  type="text/css" />
<link href="<%=relativePathtop%>css/style.css" rel="stylesheet" type="text/css" />
<Link href="<%=relativePathtop%>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<script src="<%=relativePathtop%>js/jquery.min.1.8.2.js" type="text/javascript"></script>
<script src="<%=relativePathtop%>js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
<%--<script src="<%=relativePathtop%>js/common_js.js" type="text/javascript"></script>--%>
<%--<script src="<%=relativePathtop%>js/footer.js" type="text/javascript"></script>--%>


<script src="<%=relativePathtop%>js/jquery.searchableSelect.js"></script>
<script src="<%=relativePathtop%>js/layer/layer.js" type="text/javascript"></script>

<script src="<%=relativePathtop%>js/jqzoom.js" type="text/javascript"></script>


<script src="<%=relativePathtop%>js/laydate/laydate.js" type="text/javascript"></script>
