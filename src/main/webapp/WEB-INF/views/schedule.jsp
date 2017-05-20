<%--
  User: Valery E. Malakhov
  Date: 11.03.2017
  Time: 20:33
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

    <!-- FullCalendar -->
    <link href="<c:url value="/resources/libs/fullcalendar/fullcalendar.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/libs/fullcalendar/fullcalendar.print.css"/>" rel="stylesheet" media="print">

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
                <jsp:include page="../views/blocks/sidebar.jsp"></jsp:include>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <jsp:include page="../views/blocks/sidebar_footer.jsp"></jsp:include>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- header content -->
        <jsp:include page="../views/blocks/header.jsp"></jsp:include>
        <!-- /header content -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Calendar
                            <small>Click to add/edit events</small>
                        </h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search for...">
                                <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Calendar Events
                                    <small>Sessions</small>
                                </h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                           aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Settings 1</a>
                                            </li>
                                            <li><a href="#">Settings 2</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <div id='calendar'></div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <jsp:include page="../views/blocks/footer.jsp"></jsp:include>
        <!-- /footer content -->

        <!-- calendar modal -->
        <div id="CalenderModalNew" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">New Calendar Entry</h4>
                    </div>
                    <div class="modal-body">
                        <div id="testmodal" style="padding: 5px 20px;">
                            <form id="antoform" class="form-horizontal calender" role="form">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Title</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="title" name="title">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Description</label>
                                    <div class="col-sm-9">
                                        <textarea class="form-control" style="height:55px;" id="descr"
                                                  name="descr"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary antosubmit">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="CalenderModalEdit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel2">Edit Calendar Entry</h4>
                    </div>
                    <div class="modal-body">

                        <div id="testmodal2" style="padding: 5px 20px;">
                            <form id="antoform2" class="form-horizontal calender" role="form">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Title</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="title2" name="title2">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Description</label>
                                    <div class="col-sm-9">
                                        <textarea class="form-control" style="height:55px;" id="descr2"
                                                  name="descr"></textarea>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary antosubmit2">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="fc_create" data-toggle="modal" data-target="#CalenderModalNew"></div>
        <div id="fc_edit" data-toggle="modal" data-target="#CalenderModalEdit"></div>
        <!-- /calendar modal -->

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

<!-- FullCalendar -->
<script src="<c:url value="/resources/libs/fullcalendar/lib/moment.min.js"/>"></script>
<script src="<c:url value="/resources/libs/fullcalendar/fullcalendar.js"/>"></script>

<!-- Custom Theme Scripts -->
<script src="<c:url value="/resources/js/custom.js"/>"></script>
</body>
</html>

