<%--
  User: Vladyslav V. Drabynka
  Date: 03.04.2017
  Time: 23:21
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
                <jsp:include page="../blocks/sidebar.jsp"></jsp:include>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <jsp:include page="../blocks/sidebar_footer.jsp"></jsp:include>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- header content -->
        <jsp:include page="../blocks/header.jsp"></jsp:include>
        <!-- /header content -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Архивированные документы</h3>
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
                                <%--<h2>Окно 1</h2>--%>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                           aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Внешний вид 1</a>
                                            </li>
                                            <li><a href="#">Внешний вид 2</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <table id="datatable-responsive"
                                       class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
                                       width="100%">
                                    <thead>
                                    <tr>
                                        <%--<th>ID</th>--%>
                                        <th>Название</th>
                                        <th>Автор</th>
                                        <th>Версия</th>
                                        <th>Путь</th>
                                        <th>Дата создания</th>
                                        <th>Дата архивации</th>
                                        <th>Атрибуты</th>
                                        <th>Комментарии</th>
                                        <th>Путь в архиве</th>
                                        <th>Срок хранения</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${entityArchivedDocumentList}" var="doc">
                                        <tr>
                                                <%--<td><c:out value="${doc.id}"/></td>--%>
                                            <td><c:out value="${doc.name}"/></td>
                                            <td><c:out value="${doc.author}"/></td>
                                            <td><c:out value="${doc.version}"/></td>
                                            <td><c:out value="${doc.path}"/></td>
                                            <td><c:out value="${doc.dateOfCreation}"/></td>
                                            <td><c:out value="${doc.dateOfArchiving}"/></td>
                                            <td><a href="all/documentattributes/${doc.id}"/>
                                                    <%--<c:out value="${doc.attributes}"/>--%>
                                                <button type="reset">Атрибуты</button>
                                            </td>
                                            <td><c:out value="${doc.comments}"/></td>
                                            <td><c:out value="${doc.archivePath}"/></td>
                                            <td><c:out value="${doc.archivingTerm}"/></td>
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
        <jsp:include page="../blocks/footer.jsp"></jsp:include>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables-fixedheader/2.1.1/dataTables.fixedHeader.min.js"></script>

<%--<script src="../../resources/libs/datatables.net/js/jquery.dataTables.min.js"></script>--%>
<script src="../../../resources/libs/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<%--<script src="../../resources/libs/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>--%>
<script src="../../../resources/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="../../../resources/libs/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>

<!-- Custom Theme Scripts -->
<script src="<c:url value="/resources/js/custom.min.js"/>"></script>
</body>
</html>