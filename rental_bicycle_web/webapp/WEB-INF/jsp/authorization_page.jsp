<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./img/site_img/favicon.ico">
    <title>Bicycle's rent</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/signin.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]script src="./js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


    <fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
    <fmt:setBundle basename="config.content" var="cnt"/>
    <!-- Подключение библиотеки с пользовательскими тегами-->
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <!--c:set var="contextPath" value="{pageContext.request.contextPath}"/>-->
    <!--<link rel = "stylesheet" href = "{contextPath}/css/signin.css">-->
</head>

<body>
<!-- Пользовательский тэг для внедрения информационного нафигационного элемента-->
<t:nav_user/>

<div class="container">
    <!-- Обработка кнопки Submit, принадлежащей данной форме submit-form -->
    <form class="form-signin" name="submit-form" action="Controller?command=authorization_page_user_submit" method="post">
        <h2 class="form-signin-heading">
            <fmt:message key="authorization_page.signing" bundle="${cnt}"/>
        </h2>
        <label for="inputLogin" class="sr-only">
            Login
        </label>
        <!-- Вывод на форму переданного значения в loginUser в AuthorizationPageUserSubmitCommand -->
        <input type="login" id="inputLogin" name="login" class="form-control" placeholder="<fmt:message key="authorization_page.label.login" bundle="${cnt}"/>" value="${loginUser}" required autofocus>
        <label for="inputPassword" class="sr-only">
            Password
        </label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="<fmt:message key="authorization_page.label.password" bundle="${cnt}"/>" required>
        <div class="checkbox">
            <li><a href="./Controller?command=register_command">
                <fmt:message key="authorization_page.input.registr" bundle="${cnt}"/>
            </a></li>
        </div>
        <!-- Вывод на форму переданного значения в loginErr в AuthorizationPageUserSubmitCommand -->
        <c:if test="${loginErr != null}">
        <label style="color: red">
            <fmt:message key="authorization_page.error.message" bundle="${cnt}"/>
        </label>
    </c:if>
        <c:if test="${messageSuccessRegistration != null}">
            <label style="color: green">
                <fmt:message key="message.inform.registration.success" bundle="${cnt}"/>
            </label>
        </c:if>

        </br>
        <button class="btn btn-lg btn-primary btn-block" type="submit" >
            <fmt:message key="authorization_page.button.submit" bundle="${cnt}"/>
        </button>
    </form>
</div> <!-- /container -->
<t:footer/>

<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>