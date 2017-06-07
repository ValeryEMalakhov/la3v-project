<%--
  User: Vladyslav V. Drabynka
  Date: 01.06.2017
  Time: 17:36
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>LA3Vs</title>

    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Bootlint -->
    <link href="https://maxcdn.bootstrapcdn.com/bootlint/0.14.2/bootlint.min.js" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">

    <!-- iCheck -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/fixedheader/3.1.2/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/scroller/1.4.2/css/scroller.bootstrap.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="<c:url value="/resources/css/custom.min.css"/>" rel="stylesheet">
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="${pageContext.request.contextPath}/" class="site_title"> <img
                            src="<c:url value="/resources/images/logo-blue.png"/>"
                            width="50" class="img-circle"> <span>LA3V</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <%--                <div class="profile clearfix">
                                    <div class="profile_pic">
                                        <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                                    </div>
                                    <div class="profile_info">
                                        <span>Welcome,</span>
                                        <h2>Admin</h2>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>--%>
                <!-- /menu profile quick info -->

                <br/>

                <!-- sidebar menu -->
                <jsp:include page="../../blocks/sidebar.jsp"></jsp:include>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <jsp:include page="../../blocks/sidebar_footer.jsp"></jsp:include>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- header content -->
        <jsp:include page="../../blocks/header.jsp"></jsp:include>
        <!-- /header content -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Аналитика</h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Поиск...">
                                <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Искать</button>
                    </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <%--<h2>Поиск по типу документа</h2>--%>
                            <h2>Выберите тип документа</h2>
                            <div class="x_title">
                            </div>
                            <div class="x_content">
                                <spring:url value="/archive/analytics/doctype" var="formUrl"/>
                                <form:form method="post" action="/archive/analytics/doctype" class="form-horizontal"
                                    modelAttribute="optionString">
                                    <select class="select2_group form-control" id="optionString"
                                            name="optionString">
                                        <optgroup label="Процесс не определен">
                                            <option value="undefined">Не определено</option>
                                        </optgroup>
                                        <optgroup label="Учебный процесс">
                                            <option value="diploma">Диплом</option>
                                            <option value="coursework">Курсовая работа</option>
                                            <option value="specialtiesplan">Учебный план специальности</option>
                                            <option value="workingplan">Рабочий учебный план</option>
                                            <option value="disciplineworkingprogram">Рабочая программа дисциплины</option>
                                            <option value="disciplinelectionconspectus">Конспект лекций</option>
                                            <option value="disciplinelabworkinstructions">Методические указания к лабораторным работам
                                            </option>
                                            <option value="disciplinecourseworkinstructions">Методические указания к курсовому проекту
                                            </option>
                                            <option value="disciplineextramuralinstructions">Методические указания для студентов заочного
                                                обучения
                                            </option>
                                            <option value="studentslist">Список фамилий студентов</option>
                                            <option value="progressjournal">Журнал текущей успеваемости</option>
                                        </optgroup>
                                        <optgroup label="Научный процесс">
                                            <option value="conferencethesis">Тезисы конференций</option>
                                            <option value="article">Статья</option>
                                            <option value="monograph">Монография</option>
                                            <option value="tutorial">Учебное пособие</option>
                                            <option value="educationaledition">Учебное издание</option>
                                            <option value="patent">Патент</option>
                                            <option value="conferenceincoming">Проводимая конференция</option>
                                            <option value="researchwork">НИР кафедры</option>
                                            <option value="researchworkrequest">Заявка на НИР</option>
                                            <option value="researchworkreport">Отчёт о научной работе кафедры</option>
                                            <option value="scientificseminarprotocol">Протокол научного семинара кафедры</option>
                                            <option value="conferenceprogram">Программа конференции</option>
                                            <option value="conferencethesiscollection">Сборник тезисов</option>
                                        </optgroup>
                                        <optgroup label="Организационный процесс">
                                            <option value="pilpitsessionprotocol">Протокол заседания кафедры</option>
                                            <option value="protocolstatements">Выписка из протокола</option>
                                            <option value="positioninstructions">Должностная инструкция</option>
                                            <option value="lecturerform">Анкета преподавателеля</option>
                                            <option value="commonshedule">Штатное расписание</option>
                                            <option value="pilpitworkingplan">План работы кафедры</option>
                                            <option value="pilpitworkingreport">Отчёт работы кафедры</option>
                                            <option value="schedule">Расписание</option>
                                            <option value="classroomloadjournal">Журнал о выполнении аудиторной нагрузки
                                            </option>
                                            <option value="lecturerplan">Индивидуальный план преподавателя</option>
                                            <option value="traineeship">Стажировка</option>
                                            <option value="advert">Объявление</option>
                                        </optgroup>
                                    </select>
                                    <br/>
                                    <div class="form-group">
                                        <div class="col-xs-offset-3 col-xs-9">
                                            <input type="submit" class="btn btn-primary" value="Найти"/>
                                            <button class="btn btn-default" action="action" onclick="history.go(-1);">Назад</button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <jsp:include page="../../blocks/footer.jsp"></jsp:include>
        <!-- /footer content -->
    </div>
</div>

<!-- jquery, librarys, framework, toolkit, etc. -->
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/fastclick/1.0.6/fastclick.js"></script>
<!-- NProgress -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.js"></script>

<!-- iCheck -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/icheck.min.js"></script>
<!-- Datatables -->
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.colVis.min.js"></script>
<script src="https://cdn.datatables.net/fixedheader/3.1.2/js/dataTables.fixedHeader.min.js"></script>
<script src="https://cdn.datatables.net/keytable/2.2.1/js/dataTables.keyTable.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/scroller/1.4.2/js/dataTables.scroller.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.28/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.28/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="<c:url value="/resources/js/custom.min.js"/>"></script>
</body>
</html>