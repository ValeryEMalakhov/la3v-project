<%--
  User: Valery E. Malakhov
  Date: 05.05.2017
  Time: 13:50
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

    <title> Login </title>

    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
    <%--<!-- Bootlint -->--%>
    <link href="https://maxcdn.bootstrapcdn.com/bootlint/0.14.2/bootlint.min.js" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="<c:url value="/resources/css/custom.min.css"/>" rel="stylesheet">
</head>
<body class="login">
<div>
    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form method="POST" action="${contextPath}/login" class="form-signin">
                    <h2 class="form-heading">Log in</h2>

                    <div class="form-group ${error != null ? 'has-error' : ''}">
                        <span>${message}</span>
                        <input name="username" type="text" class="form-control" placeholder="Username"
                               autofocus="true"/>
                        <input name="password" type="password" class="form-control" placeholder="Password"/>
                        <span>${error}</span>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                        <br />
                        <%--<h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>--%>


                        <div class="separator">
                            <div class="clearfix"></div>
                            <br/>
                            <div>
                                <h1><img src="<c:url value="/resources/images/logo-blue.png"/>" width="50"
                                         class="img-circle"> LA3V </h1>
                                <p>©2017 All Rights Reserved.</p>
                                <p>Privacy and Terms</p>
                            </div>
                        </div>

                </form>
            </section>
        </div>
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
<!-- validator -->
<script src="<c:url value="/resources/libs/validator/validator.js"/>"></script>

<!-- Custom Theme Scripts -->
<script src="<c:url value="/resources/js/custom.min.js"/>"></script>

</body>
</html>


