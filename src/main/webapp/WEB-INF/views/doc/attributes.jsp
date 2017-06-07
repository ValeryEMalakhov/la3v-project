<%--
  User: Vladyslav V. Drabynka
  Date: 04.05.2017
  Time: 19:00
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
                        <h3>Атрибуты</h3>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2><c:out value="${entityDocument.docType}"/></h2>

                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <spring:url value="/document/view/${entityDocument.docId}" var="formUrl"/>
                                <form:form method="POST" modelAttribute="entityDocument"
                                           action="/document/view/${entityDocument.docId}?${_csrf.parameterName}=${_csrf.token}"
                                           enctype="multipart/form-data" class="form-horizontal form-label-left">

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="topic">Название
                                            документа:</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input name="topic" type="text" class="form-control" id="topic"
                                                   readonly value="${entityDocument.docTitle}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="author">Автор:</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input name="author" type="text" class="form-control" id="author"
                                                   readonly value="${entityDocument.authorName}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="process">Процесс:</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input name="process" type="text" class="form-control" id="process"
                                                   readonly value="${entityDocument.docProcess}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="docType">Тип
                                            документа:</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input name="docType" type="text" class="form-control" id="docType"
                                                   readonly value="${entityDocument.docType}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="docDate">Год связанный с документом: </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input name="docDate" type="text" class="form-control" id="docDate"
                                                   readonly value="${entityDocument.docDate.toString()}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="filePath">Путь
                                            к файлу:</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input name="filePath" type="text" class="form-control" id="filePath"
                                                   readonly value="${entityDocument.filePath}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="docDescription">Заметка к документу:</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <blockquote id="docDescription"
                                                        name="docDescription">
                                                <p>${entityDocument.docDescription}</p>
                                            </blockquote>
                                        </div>
                                    </div>

                                    <c:choose>
                                        <c:when test="${entityDocumentAttribute != null}">
                                            <div class="ln_solid"></div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">
                                                    Атрибуты документа:</label>
                                                <br/>
                                                <br/>
                                                <c:forEach items="${entityDocumentAttribute}" var="attr">
                                                    ${attr}<br/>
                                                </c:forEach>
                                            </div>
                                        </c:when>
                                        <%--<c:otherwise>--%>
                                        <%----%>
                                        <%--</c:otherwise>--%>
                                    </c:choose>

                                    <br/>

                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                            <button class="btn btn-default" action="action" onclick="history.go(-1);">
                                                Назад
                                            </button>
                                            <button type="submit" class="btn btn-warning">Редактировать</button>
                                        </div>
                                    </div>

                                    <br/>

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
<script src="<c:url value="/resources/js/custom.js"/>"></script>
</body>
</html>