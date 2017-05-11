<%--
  User: Valery E. Malakhov
  Date: 04.05.2017
  Time: 17:13
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>LA3Vs</title>

    <!-- Bootstrap -->
    <%--<link href="<c:url value="/resources/libs/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">--%>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Bootlint -->
    <link href="https://maxcdn.bootstrapcdn.com/bootlint/0.14.2/bootlint.min.js" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <%--<link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.css" rel="stylesheet">--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">

    <!-- iCheck -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/fixedheader/3.1.2/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/scroller/1.4.2/css/scroller.bootstrap.min.css" rel="stylesheet">

    <!-- Not usually used -->

    <!-- Datatables -->
    <%--<link href="https://cdn.datatables.net/autofill/2.2.0/css/autoFill.bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="https://cdn.datatables.net/colreorder/1.3.3/css/colReorder.bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="https://cdn.datatables.net/keytable/2.2.1/css/keyTable.bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="https://cdn.datatables.net/rowgroup/1.0.0/css/rowGroup.bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="https://cdn.datatables.net/rowreorder/1.2.0/css/rowReorder.bootstrap.min.css" rel="stylesheet">--%>

    <%--<link href="" rel="stylesheet">--%>
    <%--<link href="" rel="stylesheet">--%>
    <%--<link href="" rel="stylesheet">--%>
    <%--<link href="" rel="stylesheet">--%>
    <%--<link href="" rel="stylesheet">--%>




    <!-- Custom Theme Style -->
    <link href="<c:url value="/resources/css/custom.min.css"/>" rel="stylesheet">

</head>

<body class="nav-md">

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

    <!-- Not usually used -->

    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-Knob/1.2.13/jquery.knob.min.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/easy-pie-chart/2.1.6/easypiechart.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/easy-pie-chart/2.1.6/jquery.easypiechart.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/easy-pie-chart/2.1.6/angular.easypiechart.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-mousewheel/3.1.13/jquery.mousewheel.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.js"></script>--%>
    <%--<link src="https://cdnjs.cloudflare.com/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.css">--%>

    <%--<script src="https://cdn.datatables.net/autofill/2.2.0/js/dataTables.autoFill.min.js"></script>--%>
    <%--<script src="https://cdn.datatables.net/autofill/2.2.0/js/autoFill.bootstrap.min.js"></script>--%>
    <%--<script src="https://cdn.datatables.net/colreorder/1.3.3/js/dataTables.colReorder.min.js"></script>--%>
    <%--<script src="https://cdn.datatables.net/fixedcolumns/3.2.2/js/dataTables.fixedColumns.min.js"></script>--%>
    <%--<script src="https://cdn.datatables.net/rowgroup/1.0.0/js/dataTables.rowGroup.min.js"></script>--%>
    <%--<script src="https://cdn.datatables.net/rowreorder/1.2.0/js/dataTables.rowReorder.min.js"></script>--%>
    <%--<script src="https://cdn.datatables.net/select/1.2.2/js/dataTables.select.min.js"></script>--%>

    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-progressbar/0.9.0/bootstrap-progressbar.js"></script>--%>

    <%--<script src=""></script>--%>
    <%--<script src=""></script>--%>
    <%--<script src=""></script>--%>
    <%--<script src=""></script>--%>
    <%--<script src=""></script>--%>

</body>
