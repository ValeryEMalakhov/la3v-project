<%--
  User: Valery E. Malakhov
  Date: 22.03.2017
  Time: 15:35
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
    <div class="menu_section">
        <h3> Основной блок </h3>
        <ul class="nav side-menu">
            <li><a><i class="fa fa-home"></i> Главная <span class="fa fa-chevron-down"></span></a>
                <ul class="nav child_menu">
                    <li><a href="${pageContext.request.contextPath}/"> Новости </a></li>
                    <li><a href="<spring:url value="/schedule"/>"> Расписание </a></li>
                </ul>
            </li>
            <li><a><i class="fa fa-edit"></i> Документы <span class="fa fa-chevron-down"></span></a>
                <ul class="nav child_menu">
                    <li><a href="<spring:url value="/document/all"/>"> Документы </a></li>
                    <li><a href="<spring:url value="/new/fileUpload"/>"> Загрузить документ </a></li>
                    <li><a target="_blank" href="http://91.208.138.238:81/owncloud"> Облачное хранилище </a></li>
                    <%--<li><a href="<spring:url value="/document/pattern"/>"> Генератор шаблонов </a></li>--%>
                </ul>
            </li>
            <li><a><i class="fa fa-archive"></i> Архив <span class="fa fa-chevron-down"></span></a>
                <ul class="nav child_menu">
                    <li><a href="<spring:url value="/archive/all"/>"> Документы </a></li>
                    <li><a href="<spring:url value="/archive/protocols"/>"> Общие протоколы </a></li>
                    <li><a href="<spring:url value="/archive/dprotocols"/>"> Протоколы удаления </a></li>
                    <li><a href="<spring:url value="/archive/analytics"/>"> Аналитика </a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="menu_section">
        <h3> Вспомогательный блок </h3>
        <ul class="nav side-menu">
            <li><a><i class="fa fa-bug"></i> Кабинет <span class="fa fa-chevron-down"></span></a>
                <ul class="nav child_menu">
                    <li><a href="<spring:url value="/profile/"/>"> Профиль </a></li>
                    <%--<li><a href="<spring:url value="/profile/docs"/>"> Личные документы </a></li>--%>
                </ul>
            </li>
        </ul>
    </div>
</div>