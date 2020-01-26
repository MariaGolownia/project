<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./img/site_img/favicon.ico">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="./css/signin.css" rel="stylesheet">
    <fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
    <fmt:setBundle basename="config.content" var="cnt"/>
    <!-- Подключение библиотеки с пользовательскими тегами-->
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <script src="./js/ie-emulation-modes-warning.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>
<t:nav/>


<script>
    var i;
    var cookiesArray = getCookieKeys("Bic");

    for (i = 0; i < cookiesArray.length; i++) {
        setCookie(cookiesArray[i], "", 0);
    }

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(cname) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function getCookieKeys(cname) {
        var paramArray=new Array(0);
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i = 0; i < ca.length; i++) {
            var c = ca[i];

            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }

            if (c.indexOf(cname) == 0) {
                var cb=c.split('=');
                paramArray.push(cb[0]);
            }
        }
        return paramArray;
    }
</script>

<div class="container">
    <form class="form-signin" action="Controller?command=location_page" method="post">
        <button class="btn btn-lg btn-primary btn-block" type="submit" >
            <fmt:message key="main_page.choose_location" bundle="${cnt}"/>
        </button>
        <input type="status" id="selectedLocation" class="form-control" value="${locationName}" readonly autofocus>
    </form>
    </br>
    <form class="form-signin" action="Controller?command=user_page" method="post">
        <button class="btn btn-lg btn-primary btn-block" type="submit" >
            <fmt:message key="main_page.choose_user" bundle="${cnt}"/>
        </button>
        <input type="status" id="selectedUser" class="form-control" value="${userName}" readonly autofocus>
    </form>
    </br>
    <form id="submitOrder" class="form-signin">
        <button class="btn btn-lg btn-primary btn-block" id="submit-order">
            <fmt:message key="main_page.choose_order" bundle="${cnt}"/>
        </button>
    </form>
    </br>
    <form class="form-signin" action="Controller?command=order_page_status" method="post">
        <button class="btn btn-lg btn-primary btn-block" type="submit" >
            <fmt:message key="nav_tag.menu.order.page.status" bundle="${cnt}"/>
        </button>
    </form>
    <div id="messageCheckFillingLocAndUser" hidden>
        <label style="color: red">
            <fmt:message key="message.error.form.main.page.fill.loc.user" bundle="${cnt}"/>
        </label>
    </div>
</div> <!-- /container -->
<t:footer/>

<script>
    document.getElementById("submit-order").addEventListener("click", function(event){
        var loc = document.getElementById('selectedLocation');
        var user = document.getElementById('selectedUser');
        var frm = document.getElementById('submitOrder') || null;

        if(frm && loc.value!="" && user.value!="") {
            frm.action = "Controller?command=order_page";
            frm.method = "post";
            frm.submit();
        }
        else {
            document.getElementById('messageCheckFillingLocAndUser').hidden = false;
            event.preventDefault()
        }
    });
</script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>

</html>