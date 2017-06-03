<%--
  User: Valery E. Malakhov
  Date: 01.04.2017
  Time: 20:52
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                            width="50"
                            class="img-circle"> <span>LA3V</span></a>
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
                        <h3>Документы</h3>
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
                            <div class="x_title">
                                <h2> Вывод таблицы документов
                                    <%--<small>Users</small>--%>
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
                                <%--<p class="text-muted font-13 m-b-30">--%>
                                <%--Вывод существующих документов.--%>
                                <%--</p>--%>
                                <table id="datatable-responsive"
                                       class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
                                       width="100%">
                                    <thead>
                                    <tr>
                                        <%--<th>ID</th>--%>
                                        <%--<th>uid</th>--%>
                                        <%--<th>storage</th>--%>
                                        <th>Название</th>
                                        <th>Авторы/Соавторы</th>
                                        <th>Тип документа</th>
                                        <th>Тип процесса</th>
                                        <th>Дата</th>
                                        <%--<th>Атрибуты</th>--%>
                                        <th>Описание</th>
                                        <%--<th>Путь к файлу</th>--%>
                                        <%--<th>Редактирование</th>--%>
                                        <th>Архивация</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${entityDocumentList}" var="doc">
                                        <tr>
                                                <%--<td><c:out value="${doc.docid}"/></td>--%>
                                            <td><a href=<spring:url value="view/${doc.docId}"/>/><c:out value="${doc.docTitle}"/></td>
                                            <td><c:out value="${doc.authorName}"/></td>
                                            <td><c:out value="${doc.docType}"/></td>
                                            <td><c:out value="${doc.docProcess}"/></td>
                                            <td><c:out value="${doc.docDate}"/></td>
                                            <%--<td><c:out value="${doc.attributes}"/></td>--%>
                                            <td><c:out value="${doc.docDescription}"/></td>

                                            <%-- кнопки --%>
                                            <%--<td>--%>
                                                <%--<a href=<spring:url value="/doc/edit/${doc.docId}"/>/>--%>
                                                <%--<button type="reset">Редактировать</button>--%>
                                            <%--</td>--%>
                                            <td>
                                                <a href=<spring:url value="/archive/archivation/${doc.docId}"/>/>
                                                <button type="reset">Архивировать</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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
