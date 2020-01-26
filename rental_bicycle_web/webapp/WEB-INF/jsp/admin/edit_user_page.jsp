<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
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

    <form class="form-signin" action="Controller?command=edit_user_apply_command_admin" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label style="color: red" class="error_message"> ${errorMessage}  </label>
        <!---------------------------------------Role-------------------------------------------------------------------------->
        <label for="inputRole" class="sr-show">Role </label>
        <input type="role" id="inputRole" name="userRole" class="form-control" value="${role_user}" required autofocus>
        <!----------------------------------------------------------------------------------------------------------------->
        <label for="inputLogin" class="sr-show">Login</label>
        <input type="login" id="inputLogin" name="userLogin" class="form-control" value="${login_user}" readonly autofocus>
        <!-----------------------------------------Surname--------------------------------------------------------------->
        <label for="inputSurname" class="sr-show">Surname</label>
        <input type="surname" id="inputSurname" name="userSurname" class="form-control" value="${surname_user}" placeholder="" required autofocus>
        <!-----------------------------------------name--------------------------------------------------------------->
        <label for="inputName" class="sr-show">Name</label>
        <input type="name" id="inputName" name="userName" class="form-control" value="${name_user}" placeholder="" required autofocus>
        <!-----------------------------------------secondName--------------------------------------------------------------->
        <label for="inputSecondName" class="sr-show">Second name</label>
        <input type="secondName" id="inputSecondName" name="userSecondName" class="form-control" value="${second_name_user}" placeholder=""  autofocus>
        <!-----------------------------------------birthDate--------------------------------------------------------------->
        <label for="inputBirthDate" class="sr-show">Birth date</label>
        <input type="birthDate" id="inputBirthDate" name="userBirthDate" class="form-control" value="${birth_date_user}" placeholder=""  autofocus>
        <!-----------------------------------------country--------------------------------------------------------------->
        <label for="inputCountry" class="sr-show">Country</label>
        <input type="country" id="inputCountry" name="userCountry" class="form-control" value="${country_user}" placeholder=""  autofocus>
        <!-----------------------------------------passportIssueDate--------------------------------------------------------------->
        <label for="inputPassportIssueDate" class="sr-show">Passport issue date</label>
        <input type="passportIssueDate" id="inputPassportIssueDate" name="userPassportIssueDate" class="form-control"  value="${pass_issue_date_user}" placeholder="" autofocus>
        <!-----------------------------------------passportIssuingAuthority--------------------------------------------------------------->
        <label for="inputPassportIssuingAuthority" class="sr-show">Passport issuing authority</label>
        <input type="passportIssuingAuthority" id="inputPassportIssuingAuthority" name="userPassportIssuingAuthority" class="form-control" value="${pass_iss_authotity_user}" placeholder="" autofocus>
        <!-----------------------------------------passportIdentificationNumber--------------------------------------------------------------->
        <label for="inputPassportIdentificationNumber" class="sr-show">Passport identification number</label>
        <input type="passportIdentificationNumber" id="inputPassportIdentificationNumber" name="userPassportIdentificationNumber" value="${pass_id_number_user}" class="form-control" placeholder="" autofocus>
        <!-----------------------------------------passportSerialNumber--------------------------------------------------------------->
        <label for="inputPassportSerialNumber" class="sr-show">Passport serial number</label>
        <input type="passportSerialNumber" id="inputPassportSerialNumber" name="userPassportSerialNumber" class="form-control" value="${pass_s_number_user}" placeholder="" autofocus>
        <!-----------------------------------------passportAddressRegistration--------------------------------------------------------------->
        <label for="inputPassportAddressRegistration" class="sr-show">Passport address registration</label>
        <input type="passportAddressRegistration" id="inputPassportAddressRegistration" name="userPassportAddressRegistration" class="form-control" value="${address_reg_user}" placeholder="" autofocus>
        <!-----------------------------------------passportAddressResidence--------------------------------------------------------------->
        <label for="inputPassportAddressResidence" class="sr-show">Passport address residence</label>
        <input type="passportAddressResidence" id="inputPassportAddressResidence" name="userPassportAddressResidence" class="form-control" value="${address_res_user}" placeholder="" autofocus>
        <!-----------------------------------------phoneNumber--------------------------------------------------------------->
        <label for="inputPhoneNumber" class="sr-show">Phone number</label>
        <input type="phoneNumber" id="inputPhoneNumber" name="userPhoneNumber" class="form-control" placeholder="Enter your phone" value="${phone_number_user}" required autofocus>
        <!-----------------------------------------secondPhoneNumber--------------------------------------------------------------->
        <label for="inputSecondPhoneNumber" class="sr-show">Second phone number</label>
        <input type="secondPhoneNumber" id="inputSecondPhoneNumber" name="userSecondPhoneNumber" class="form-control" value="${phone_number2_user}" placeholder="" autofocus>
        <!-----------------------------------------email--------------------------------------------------------------->
        <label for="inputEmail" class="sr-show">Email</label>
        <input type="email" id="inputEmail" name="userEmail" class="form-control" value="${e_mail_user}" placeholder="" autofocus>
        <!----------------------------------------------------------------------------------------------------------------->

        <button class="btn btn-lg btn-primary btn-block" type="register" >Update</button>
    </form>
    </br>
    <div class="form-signin">
        <button class="btn btn-lg btn-primary btn-block" type="topUp" onclick="history.back()"> Back </button>
    </div>
</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>