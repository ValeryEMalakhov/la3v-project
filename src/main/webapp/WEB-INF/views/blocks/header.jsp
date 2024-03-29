<%--
  User: Valery E. Malakhov
  Date: 11.03.2017
  Time: 20:34
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="<c:url value="/resources/images/icon_user.png"/>" alt="">${pageContext.request.userPrincipal.name}
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <%--<li><a href="<spring:url value="profile/"/>"> Профиль </a></li>--%>
                                <%--<li><a href="<spring:url value="settings/"/>"> Настройки </a></li>--%>
                                <%--<li><a href="<spring:url value="help/"/>"> Помощь </a></li>--%>
                                <form id="logoutForm" method="post" action="${contextPath}/logout">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                                <li><a onclick="document.forms['logoutForm'].submit()"><i class="fa fa-sign-out pull-right"></i> Выход </a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="<c:url value="/resources/images/icon_guest.png"/>" alt="">Guest
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <%--<li><a href="<spring:url value="help/"/>"> Помощь </a></li>--%>
                                <li><a href="/login"><i class="fa fa-sign-in pull-right"></i> Вход </a></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </nav>
    </div>
</div>











