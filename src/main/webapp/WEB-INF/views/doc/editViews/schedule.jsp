<%--
  User: Vladyslav V. Drabynka
  Date: 17.05.2017
  Time: 14:12
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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>LA3Vs</title>

    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
    <%--<!-- Bootlint -->--%>
    <link href="https://maxcdn.bootstrapcdn.com/bootlint/0.14.2/bootlint.min.js" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">

    <!-- iCheck -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/flat/green.css" rel="stylesheet">

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
                        <h3>Редактирование</h3>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>${entityDocumentBase.docTitle}</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <spring:url value="/document/edit/${addressPart}/attributes/schedule"
                                            var="formUrl"/>
                                <form:form method="post" modelAttribute="attributes"
                                           action="/document/edit/{${addressPart}}/attributes/schedule"
                                           class="form-horizontal">

                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="semester">Семестр: <span class="required">*</span></label>
                                        <div class="col-xs-9">
                                            <input name="semester" type="number" min="1" max="8" step="1" value="1" pattern="[1-8]" class="form-control"
                                                   id="semester" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="lecturer">Преподаватель:</label>
                                        <div class="col-xs-9">
                                            <input name="lecturer" type="text" class="form-control" id="lecturer"
                                                   readonly value="${entityDocumentBase.docAuthor}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="dayOfWeek">День недели: <span class="required">*</span></label>
                                        <div class="col-xs-9">
                                            <select class="select2_group form-control" id="dayOfWeek"
                                                    name="dayOfWeek" required>
                                                <option value="Понедельник">Понедельник</option>
                                                <option value="Вторник">Вторник</option>
                                                <option value="Среда">Среда</option>
                                                <option value="Четверг">Четверг</option>
                                                <option value="Пятница">Пятница</option>
                                                <option value="Суббота">Суббота</option>
                                                <option value="Воскресенье">Воскресенье</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="week">Неделя: <span class="required">*</span></label>
                                        <div class="col-xs-9">
                                            <input name="week" type="number" min="1" max="99" step="1" value="1" pattern="[0-9]{1,2}" class="form-control"
                                                   id="week" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="classes">Пара: <span class="required">*</span></label>
                                        <div class="col-xs-9">
                                            <input name="classes" type="number" min="1" max="7" step="1" value="1" pattern="[1-7]" class="form-control"
                                                   id="classes" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-offset-3 col-xs-9">
                                            <button class="btn btn-primary" type="reset">Сброс</button>
                                            <input type="submit" class="btn btn-primary" value="Сохранить"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <br/>
                                    Поля, отмеченные *, должны быть заполнены
                                </form:form>
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

<!-- Custom Theme Scripts -->
<script src="<c:url value="/resources/js/custom.min.js"/>"></script>
</body>
</html>


