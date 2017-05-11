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
                    <li><a href="<spring:url value="/schedule"/>"> Рассписание </a></li>
                </ul>
            </li>
            <li><a><i class="fa fa-edit"></i> Документы <span class="fa fa-chevron-down"></span></a>
                <ul class="nav child_menu">
                    <li><a href="127.0.0.1:80/owncloud" target="_blank"> Облачное хранилище </a></li>
                    <li><a href="<spring:url value="/document/all"/>"> Список документов </a></li>
                    <li><a href="<spring:url value="/document/analytics"/>"> Аналитика </a></li>
                    <li><a href="<spring:url value="/document/pattern_gen"/>"> Генератор шаблонов </a></li>

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
                    <%--<li><a href="<spring:url value="/document/personal"/>"> Личные документы </a></li>--%>
                </ul>
            </li>
            <%--                            <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                                            <ul class="nav child_menu">
                                                <li><a href="page_403.html">403 Error</a></li>
                                                <li><a href="page_404.html">404 Error</a></li>
                                                <li><a href="page_500.html">500 Error</a></li>
                                                <li><a href="test.html">Plain Page</a></li>
                                                <li><a href="login.html">Login Page</a></li>
                                                <li><a href="pricing_tables.html">Pricing Tables</a></li>
                                            </ul>
                                        </li>
                                        <li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span class="fa fa-chevron-down"></span></a>
                                            <ul class="nav child_menu">
                                                <li><a href="#level1_1">Level One</a>
                                                <li><a>Level One<span class="fa fa-chevron-down"></span></a>
                                                    <ul class="nav child_menu">
                                                        <li class="sub_menu"><a href="level2.html">Level Two</a>
                                                        </li>
                                                        <li><a href="#level2_1">Level Two</a>
                                                        </li>
                                                        <li><a href="#level2_2">Level Two</a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li><a href="#level1_2">Level One</a>
                                                </li>
                                            </ul>
                                        </li>--%>
            <%--<li><a href="javascript:void(0)"><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a></li>--%>
        </ul>
    </div>
</div>