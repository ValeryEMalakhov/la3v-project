<%--
  User: Valery E. Malakhov
  Date: 20.05.2017
  Time: 20:34
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
    <!-- Bootstrap-daterangepicker -->
    <link href="<c:url value="/resources/libs/bootstrap-daterangepicker/daterangepicker.css"/>" rel="stylesheet">
    <!-- Bootstrap-wysiwyg -->
    <link href="<c:url value="/resources/libs/google-code-prettify/bin/prettify.min.css "/>" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">

    <!-- switchery -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/switchery/0.8.2/switchery.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">

    <!-- iCheck -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/flat/green.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet">

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
                <br/>

                <!-- sidebar menu -->
                <jsp:include page="/WEB-INF/views/blocks/sidebar.jsp"></jsp:include>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <jsp:include page="/WEB-INF/views/blocks/sidebar_footer.jsp"></jsp:include>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- header content -->
        <jsp:include page="/WEB-INF/views/blocks/header.jsp"></jsp:include>
        <!-- /header content -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Добавление документа</h3>
                    </div>

                </div>
                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Выберите файл
                                    <small>и нажмите "далее"</small>
                                </h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content">
                                <spring:url value="/new/fileUpload" var="formUrl"/>
                                <br/>
                                <form:form action="./fileUpload?${_csrf.parameterName}=${_csrf.token}"
                                           method="POST" modelAttribute="fileBucket"
                                           enctype="multipart/form-data" class="form-horizontal form-label-left">

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file">Выберите
                                            файл <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <form:input type="file" path="file" id="file"
                                                        class="form-control input-sm"/>
                                            <div class="has-error">
                                                <form:errors path="file" class="help-inline"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="docTitle">Название
                                            документа: <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" path="docTitle" id="docTitle" name="docTitle"
                                                   required="required"
                                                   class="form-control col-md-7 col-xs-12"
                                                   placeholder="Основное название/суть документа">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="docAuthorsString">Автор/авторы
                                            документа: <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" path="docAuthorsString" id="docAuthorsString"
                                                   name="docAuthorsString" required="required"
                                                   class="form-control col-md-7 col-xs-12"
                                                   placeholder="Фамилия И.О.[; Фамилия И.О.]">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="docType">Тип
                                            документа: <span class="required">*</span> </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <select class="select2_group form-control" path="docType" id="docType"
                                                    name="docType">

                                                <c:forEach items="${entityDocumentTypeList}" var="typeList">

                                                    <c:choose>
                                                        <c:when test="${typeList.type_id == 1}">
                                                            <optgroup label="Процесс не определен">
                                                                <option value="${typeList.type_id}" selected="selected">${typeList.type_name}</option>
                                                            </optgroup>
                                                        </c:when>
                                                        <c:when test="${typeList.type_id == 2}">
                                                            <optgroup label="Учебный процесс">
                                                            <option value="${typeList.type_id}">${typeList.type_name}</option>
                                                        </c:when>
                                                        <c:when test="${typeList.type_id == 13}">
                                                            </optgroup>
                                                            <optgroup label="Научный процесс">
                                                            <option value="${typeList.type_id}">${typeList.type_name}</option>
                                                        </c:when>
                                                        <c:when test="${typeList.type_id == 26}">
                                                            </optgroup>
                                                            <optgroup label="Организационный процесс">
                                                            <option value="${typeList.type_id}">${typeList.type_name}</option>
                                                        </c:when>
                                                        <c:when test="${typeList.type_id == 37}">
                                                            <option value="${typeList.type_id}">${typeList.type_name}</option>
                                                            </optgroup>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${typeList.type_id}">${typeList.type_name}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>


                                                <%--<optgroup label="Процесс не определен">
                                                    <option value="1">Не определено</option>
                                                </optgroup>
                                                <optgroup label="Учебный процесс">
                                                    <option value="2">Диплом</option>
                                                    <option value="3">Курсовая работа</option>
                                                    <option value="4">Учебный план специальности</option>
                                                    <option value="5">Рабочий учебный план</option>
                                                    <option value="6">Рабочая программа дисциплины</option>
                                                    <option value="7">Конспект лекций</option>
                                                    <option value="8">Методические указания к лабораторным работам
                                                    </option>
                                                    <option value="9">Методические указания к курсовому проекту
                                                    </option>
                                                    <option value="10">Методические указания для студентов заочного
                                                        обучения
                                                    </option>
                                                    <option value="11">Список фамилий студентов</option>
                                                    <option value="12">Журнал текущей успеваемости</option>
                                                </optgroup>
                                                <optgroup label="Научный процесс">
                                                    <option value="13">Тезисы конференций</option>
                                                    <option value="14">Статья</option>
                                                    <option value="15">Монография</option>
                                                    <option value="16">Учебное пособие</option>
                                                    <option value="17">Учебное издание</option>
                                                    <option value="18">Патент</option>
                                                    <option value="19">Проводимая конференция</option>
                                                    <option value="20">НИР кафедры</option>
                                                    <option value="21">Заявка на НИР</option>
                                                    <option value="22">Отчёт о научной работе кафедры</option>
                                                    <option value="23">Протокол научного семинара кафедры</option>
                                                    <option value="24">Программа конференции</option>
                                                    <option value="25">Сборник тезисов</option>
                                                </optgroup>
                                                <optgroup label="Организационный процесс">
                                                    <option value="26">Протокол заседания кафедры</option>
                                                    <option value="27">Выписка из протокола</option>
                                                    <option value="28">Должностная инструкция</option>
                                                    <option value="29">Анкета преподавателеля</option>
                                                    <option value="30">Штатное расписание</option>
                                                    <option value="31">План работы кафедры</option>
                                                    <option value="32">Отчёт работы кафедры</option>
                                                    <option value="33">Расписание</option>
                                                    <option value="34">Журнал о выполнении аудиторной нагрузки
                                                    </option>
                                                    <option value="35">Индивидуальный план преподавателя</option>
                                                    <option value="36">Стажировка</option>
                                                    <option value="37">Объявление</option>
                                                </optgroup>--%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="docDate">Год связанный с документом: </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="number" path="docDate" id="docDate" name="docDate"
                                                   min="1900" max="2150" class="form-control"
                                                   data-bind="value:replyNumber"
                                                   placeholder="2017">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="docDescription">Заметка
                                            к документу:</label>
                                        <textarea class="form-control col-md-7 col-xs-12" path="docDescription"
                                                  id="docDescription"
                                                  name="docDescription"></textarea>
                                    </div>

                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">

                                            <button class="btn btn-primary" type="reset">Сброс</button>
                                            <button type="submit" class="btn btn-success">Далее</button>
                                        </div>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- /page content -->

    <!-- footer content -->
    <jsp:include page="/WEB-INF/views/blocks/footer.jsp"></jsp:include>
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


<!-- bootstrap-progressbar -->
<script src="<c:url value="/resources/libs/bootstrap-progressbar/bootstrap-progressbar.min.js"/>"></script>
<!-- bootstrap-daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<script src="<c:url value="/resources/libs/bootstrap-daterangepicker/daterangepicker.js"/>"></script>
<!-- bootstrap-wysiwyg -->
<script src="<c:url value="/resources/libs/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"/>"></script>
<script src="<c:url value="/resources/libs/jquery.hotkeys/jquery.hotkeys.js"/>"></script>
<script src="<c:url value="/resources/libs/google-code-prettify/src/prettify.js"/>"></script>
<!-- jQuery Tags Input -->
<script src="<c:url value="/resources/libs/jquery.tagsinput/src/jquery.tagsinput.js"/>"></script>
<!-- Switchery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/switchery/0.8.2/switchery.min.js"></script>
<!-- Select2 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.full.min.js"></script>
<!-- Parsley -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.7.2/parsley.min.js"></script>
<!-- Autosize -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/autosize.js/3.0.21/autosize.min.js"></script>

<!-- Custom Theme Scripts -->
<script src="<c:url value="/resources/js/custom.min.js"/>"></script>
</body>
</html>

