<%--
  User: Valery E. Malakhov
  Date: 22.03.2017
  Time: 15:37
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar-footer hidden-small">
    <a data-toggle="tooltip" data-placement="top" title="Настройки">
        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
    </a>
    <a data-toggle="tooltip" data-placement="top" title="Что-то">
        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
    </a>
    <a data-toggle="tooltip" data-placement="top" title="Полный экран">
        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
    </a>

    <security:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
        <a data-toggle="tooltip" data-placement="top" title="Вход" href="<spring:url value="/login"/>">
            <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
        </a>
    </security:authorize>

    <security:authorize access="hasAnyRole('ROLE_lecturer', 'ROLE_admin', 'ROLE_methodologist')">
        <a data-toggle="tooltip" data-placement="top" title="Выход" onclick="document.forms['logoutForm'].submit()">
            <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
        </a>
    </security:authorize>
</div>