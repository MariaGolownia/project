<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./img/site_img/favicon.ico">
    <fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
    <fmt:setBundle basename="config.content" var="cnt"/>
    <!-- Подключение библиотеки с пользовательскими тегами-->
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <title>Registration</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/owner.css" rel="stylesheet">
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="./css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="./js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<t:nav/>
<div class="container">

    <form class="form-signin" action="Controller?command=registration_page" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <!---------------------------------------Role-------------------------------------------------------------------------->
        <label for="inputRole" class="sr-show">Your role by default</label>
        <input type="role" id="inputRole" name="userRole" class="form-control" value="user" readonly autofocus>
        <!----------------------------------------------------------------------------------------------------------------->
        <label for="inputLogin" class="sr-show">Login</label>
        <input type="login" id="inputLogin" name="userLogin" class="form-control" placeholder="Enter your login" value="${userLogin}" required autofocus>
        <c:if test="${messageCheckUserLogin != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.login" bundle="${cnt}"/>
            </label>
        </c:if>
        <!---------------------------------------Password----------------------------------------------------------------->
        <label for="inputPassword" class="sr-show">Password</label>
        <input type="password" id="inputPassword" name="userPassword" class="form-control" placeholder="Enter your password" required>
        <c:if test="${messageCheckUserPassword != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.password" bundle="${cnt}"/>
            </label>
        </c:if>
        <!---------------------------------------Repeating of Password----------------------------------------------------------------->
        <label for="inputPasswordRep" class="sr-show">Repeat password</label>
        <input type="password" id="inputPasswordRep" name="userPasswordRep" class="form-control" placeholder="Repeat your password" required>
        <c:if test="${messageCheckUserPasswordRep != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.password.rep" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------Surname--------------------------------------------------------------->
        <label for="inputSurname" class="sr-show">Surname</label>
        <input type="surname" id="inputSurname" name="userSurname" class="form-control" placeholder="" value="${userSurname}" required autofocus>
        <c:if test="${messageCheckUserSurname != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.surname" bundle="${cnt}"/>
            </label>
        </c:if>

        <!-----------------------------------------name--------------------------------------------------------------->
        <label for="inputName" class="sr-show">Name</label>
        <input type="name" id="inputName" name="userName" class="form-control" placeholder="" value="${userName}" required autofocus>
        <c:if test="${messageCheckUserName != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.name" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------secondName--------------------------------------------------------------->
        <label for="inputSecondName" class="sr-show">Second name</label>
        <input type="secondName" id="inputSecondName" name="userSecondName" class="form-control" placeholder="" value="${userSecondName}"  autofocus>
        <c:if test="${messageCheckSecName != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.secname" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------birthDate--------------------------------------------------------------->
        <label for="inputBirthDate" class="sr-show">Birth date</label>
        <input type="birthDate" id="inputBirthDate" name="userBirthDate" class="form-control" placeholder="" value="${userBirthDate}" required autofocus>
        <c:if test="${messageCheckBirthDate != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.birth.day" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------country--------------------------------------------------------------->
        <label for="inputCountry" class="sr-show">Country</label>
        <input type="country" id="inputCountry" name="userCountry" class="form-control" placeholder="" value="${userCountry}" autofocus>
        <c:if test="${messageCheckCountry != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.country" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------passportIssueDate--------------------------------------------------------------->
        <label for="inputPassportIssueDate" class="sr-show">Passport issue date</label>
        <input type="passportIssueDate" id="inputPassportIssueDate" name="userPassportIssueDate" class="form-control"  placeholder="" value="${userPassportIssueDate}" required autofocus>
        <c:if test="${messageCheckPassportDate != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.passport.date" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------passportIssuingAuthority--------------------------------------------------------------->
        <label for="inputPassportIssuingAuthority" class="sr-show">Passport issuing authority</label>
        <input type="passportIssuingAuthority" id="inputPassportIssuingAuthority" name="userPassportIssuingAuthority" class="form-control" placeholder="" value="${userPassportIssuingAuthority}" autofocus>
        <!-----------------------------------------passportIdentificationNumber--------------------------------------------------------------->
        <label for="inputPassportIdentificationNumber" class="sr-show">Passport identification number</label>
        <input type="passportIdentificationNumber" id="inputPassportIdentificationNumber" name="userPassportIdentificationNumber" class="form-control" placeholder="" value="${userPassportIdentificationNumber}" required autofocus>
        <c:if test="${messageCheckIdPassport != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.duplicate.id.passport" bundle="${cnt}"/>
            </label>
        </c:if>

        <!-----------------------------------------passportSerialNumber--------------------------------------------------------------->
        <label for="inputPassportSerialNumber" class="sr-show">Passport serial number</label>
        <input type="passportSerialNumber" id="inputPassportSerialNumber" name="userPassportSerialNumber" class="form-control" placeholder="" value="${userPassportSerialNumber}" autofocus>
        <!-----------------------------------------passportAddressRegistration--------------------------------------------------------------->
        <label for="inputPassportAddressRegistration" class="sr-show">Passport address registration</label>
        <input type="passportAddressRegistration" id="inputPassportAddressRegistration" name="userPassportAddressRegistration" class="form-control" placeholder="" value="${userPassportAddressRegistration}" autofocus>
        <!-----------------------------------------passportAddressResidence--------------------------------------------------------------->
        <label for="inputPassportAddressResidence" class="sr-show">Passport address residence</label>
        <input type="passportAddressResidence" id="inputPassportAddressResidence" name="userPassportAddressResidence" class="form-control" placeholder="" value="${userPassportAddressResidence}" autofocus>
        <!-----------------------------------------phoneNumber--------------------------------------------------------------->
        <label for="inputPhoneNumber" class="sr-show">Phone number</label>
        <input type="phoneNumber" id="inputPhoneNumber" name="userPhoneNumber" class="form-control" placeholder="Enter your phone" value="${userPhoneNumber}" required autofocus>
        <c:if test="${messageCheckUserPhone != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.phone" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------secondPhoneNumber--------------------------------------------------------------->
        <label for="inputSecondPhoneNumber" class="sr-show">Second phone number</label>
        <input type="secondPhoneNumber" id="inputSecondPhoneNumber" name="userSecondPhoneNumber" class="form-control" placeholder="" value="${userSecondPhoneNumber}" autofocus>
        <c:if test="${messageCheckUserSecPhone != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.phone" bundle="${cnt}"/>
            </label>
        </c:if>
        <!-----------------------------------------email--------------------------------------------------------------->
        <label for="inputEmail" class="sr-show">Email</label>
        <input type="email" id="inputEmail" name="userEmail" class="form-control" placeholder="" value="${userEmail}" autofocus>
        <c:if test="${messageCheckEmail != null}">
            <label style="color: red">
                <fmt:message key="message.error.form.user.phone" bundle="${cnt}"/>
            </label>
        </c:if>
        <!----------------------------------------------------------------------------------------------------------------->

        <button class="btn btn-lg btn-primary btn-block" type="register" >Register</button>
    </form>
</div> <!-- /container -->
<t:footer/>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>