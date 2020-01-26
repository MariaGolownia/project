<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
<fmt:setBundle basename="config.content" var="cnt"/>

<script src="./js/jquery.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min.js" type="text/javascript"></script>

<link href="./css/navbar-static-top.css" rel="stylesheet">
<link href="./css/bootstrap.min.css" rel="stylesheet">


<!-- Static navbar -->

<nav class="navbar navbar-default navbar-static-top"  >
    <div class="container">
        <div class="navbar-header">

            <a class="navbar-brand" href="#">
                <fmt:message key="nav_tag.name_app" bundle="${cnt}"/>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <!------------------------------------------------------------------------------------------------>
            <ul class="nav navbar-nav">
                <li><a href="/web/html/about.jsp">
                    <fmt:message key="nav_tag.about_company" bundle="${cnt}"/>
                </a></li>
            </ul>

            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="nav_tag.menu" bundle="${cnt}"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <form class="form-locale" value="en_US" name="set_locale_li" method="post" action="Controller?command=main_page">
                            <button type="submit" name="locale" value="en_US" class="btn btn-link nav-link">
                                <fmt:message key="nav_tag.menu.main.page" bundle="${cnt}"/>
                            </button>
                        </form>

                        <form class="form-locale" value="ru_RU" name="set_locale_li" method="post" action="Controller?command=location_page&isAdminTag=1">
                            <button type="submit" name="locale" value="ru_RU" class="btn btn-link nav-link">
                                <fmt:message key="nav_tag.menu.location.page" bundle="${cnt}"/>
                            </button>
                        </form>

                        <form class="form-locale" value="ru_RU" name="set_locale_li" method="post" action="Controller?command=user_page">
                            <button type="submit" name="locale" value="ru_RU" class="btn btn-link nav-link">
                                <fmt:message key="nav_tag.menu.users.page" bundle="${cnt}"/>
                            </button>
                        </form>

                        <form class="form-locale" value="ru_RU" name="set_locale_li" method="post" action="Controller?command=order_page_status">
                            <button type="submit" name="locale" value="ru_RU" class="btn btn-link nav-link">
                                <fmt:message key="nav_tag.menu.order.page.status" bundle="${cnt}"/>
                            </button>
                        </form>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="nav_tag.language" bundle="${cnt}"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">

                        <form class="form-locale" value="en_US" name="set_locale_li" method="post" action="Controller?command=set_locale">
                            <button type="submit" name="locale" value="en_US" class="btn btn-link nav-link">
                                <fmt:message key="nav_tag.language.english" bundle="${cnt}"/>
                            </button>
                        </form>

                        <form class="form-locale" value="ru_RU" name="set_locale_li" method="post" action="Controller?command=set_locale">
                            <button type="submit" name="locale" value="ru_RU" class="btn btn-link nav-link">
                                <fmt:message key="nav_tag.language.russian" bundle="${cnt}"/>
                            </button>
                        </form>
                    </ul>
                </li>
                <li><a href="./Controller?command=authorization_page">
                    <fmt:message key="nav_tag.exit" bundle="${cnt}"/>
                </a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>

</nav>