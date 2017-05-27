<%--
  User: Valery E. Malakhov
  Date: 20.05.2017
  Time: 20:34
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
                <jsp:include page="../../views/blocks/sidebar.jsp"></jsp:include>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <jsp:include page="../../views/blocks/sidebar_footer.jsp"></jsp:include>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- header content -->
        <jsp:include page="../../views/blocks/header.jsp"></jsp:include>
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
                                <h2>Введите общие атрибуты документа
                                    <small>нажмите "далее" для продолжения</small>
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
                                <br/>
                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="doc_name">Выберите файл <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" id="doc_name" name="doc_name" required="required"
                                                   class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="doc_title">Название
                                            документа: <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" id="doc_title" name="doc_title" required="required"
                                                   class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="author_name">Автор
                                            документа: <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" id="author_name" name="author_name" required="required"
                                                   class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="doc_date_creation">Дата создания докумета: </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" id="doc_date_creation" name="doc_date_creation"
                                                   class="form-control" data-inputmask="'mask': '99/99/9999'">
                                            <span class="fa fa-user form-control-feedback right"
                                                  aria-hidden="true"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="doc_type_id">Тип
                                            документа: <span class="required">*</span> </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <select id="doc_type_id" name="doc_type_id"
                                                    class="select2_group form-control">
                                                <option value="U0">Не определенно</option>
                                                <optgroup label="Учебный процесс">
                                                    <option value="E1">Диплом</option>
                                                    <option value="E2">Курсовая работа</option>
                                                    <option value="E3">Учебный план специальности</option>
                                                    <option value="E4">Рабочий учебный план</option>
                                                    <option value="E5">Список фамилий студентов</option>
                                                    <option value="E6">Журнал текущей успеваемости</option>
                                                </optgroup>
                                                <optgroup label="Научный процесс">
                                                    <option value="S1">Тезисы конференций</option>
                                                    <option value="S2">Статья</option>
                                                    <option value="S3">Монография</option>
                                                    <option value="S4">Учебное пособие</option>
                                                    <option value="S5">Учебное издание</option>
                                                    <option value="S6">Патенты</option>
                                                    <option value="S7">Проводимые конференции</option>
                                                    <option value="S8">НИР кафедры</option>
                                                    <option value="S9">Заявка на НИР</option>
                                                    <option value="S10">Отчёт о научной работе кафедры</option>
                                                    <option value="S11">Протоколы научных семинаров кафедры</option>
                                                </optgroup>
                                                <optgroup label="Организационный процесс">
                                                    <option value="O1">Протокол заседания кафедры</option>
                                                    <option value="O2">Выписка из протокола</option>
                                                    <option value="O3">Должностная инструкция</option>
                                                    <option value="O4">Анкеты преподавателей</option>
                                                    <option value="O5">Штатное расписание</option>
                                                    <option value="O6">План работы кафедры</option>
                                                    <option value="O7">Отчёт работы кафедры</option>
                                                    <option value="O8">Расписание</option>
                                                    <option value="O9">Журнал о выполнении аудиторной нагрузки</option>
                                                    <option value="O10">Индивидуальный план преподавателя</option>
                                                    <option value="O11">Стажировка</option>
                                                    <option value="O12">Объявления Dakota</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="doc_description">Заметка к документу:</label>
                                        <textarea id="doc_description" class="form-control col-md-7 col-xs-12"
                                                  name="doc_description"></textarea>
                                    </div>

                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">

                                            <button class="btn btn-primary" type="reset">Сброс</button>
                                            <button type="submit" class="btn btn-success">Далее</button>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- /page content -->

    <!-- footer content -->
    <jsp:include page="../../views/blocks/footer.jsp"></jsp:include>
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

