<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="sidebar-menu fixed">
    <div class="sidebar-inner scrollable-sidebar">
        <div class="main-menu">
            <ul class="accordion">
                <li class="menu-header">
                    Main Menu
                </li>
                <li class="bg-palette1">
                    <a href="<%=basePath%>main/index">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
                            <span class="text m-left-sm">首页</span>
                        </span>
                        <span class="menu-content-hover block">主页</span>
                    </a>
                </li>
                <c:forEach var="menu" items="${allmenuList}">
                    <li class="openable bg-palette3 <c:if test="${requestScope.PCode == menu.menuCode}">open</c:if>">
                        <a href="#">
                            <span class="menu-content block">
                                <span class="menu-icon"><i class="block fa ${menu.iconName} fa-lg"></i></span>
                                <span class="text m-left-sm">${menu.menuName}</span>
                                <span class="submenu-icon"></span>
                            </span>
                            <span class="menu-content-hover block">${menu.menuName}</span>
                        </a>
                        <ul class="submenu bg-palette4">
                            <c:forEach items="${menu.childrenMenus}" var="ChildrenMenu">
                                <c:choose>
                                    <c:when test="${requestScope.MenuCode == ChildrenMenu.menuCode}">
                                        <li class="active"><a href="<%=basePath%>${ChildrenMenu.requestURL}"><span class="submenu-label">${ChildrenMenu.menuName}</span></a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="<%=basePath%>${ChildrenMenu.requestURL}"><span class="submenu-label">${ChildrenMenu.menuName}</span></a></li>
                                    </c:otherwise>
                                 </c:choose>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</aside>