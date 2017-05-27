<%--
  User: Valery E. Malakhov
  Date: 27.05.2017
  Time: 16:52
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<body>

<c:if test="${not empty errCode}">
    <h1>${errCode} : System Errors</h1>
</c:if>

<c:if test="${empty errCode}">
    <h1>System Errors</h1>
</c:if>

<c:if test="${not empty errMsg}">
    <h2>${errMsg}</h2>
</c:if>

</body>
</html>
