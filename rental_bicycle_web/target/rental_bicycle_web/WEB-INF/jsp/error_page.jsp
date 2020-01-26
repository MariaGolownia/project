<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
<fmt:setBundle basename="config.content" var="cnt"/>

<html>
<head>
	<title><fmt:message key="page.error.title.500" bundle="${cnt}"/></title>
</head>
<body>
<my:nav/>
<div>
	<div>
		<br>
		<div class="row justify-content-center py-0">
			<div class="container h-50 w-50">
				<img src="<c:url value = "/img/errors_img/err_500.png"/>" class="img-fluid img-thumbnail border-dark" alt="" style="height:400px;width:auto;max-width:100%;display: block;margin-left: auto;margin-right: auto;">
			</div>
		</div>
		<div class="row justify-content-center pt-2">
			<div class="container h-100 w-100">
				<h1 class="text-center"><fmt:message key="page.error.title.500" bundle="${cnt}"/></h1>
			</div>
		</div>
		<div class="row justify-content-center py-0" align="center">
			<a class="h6 font-weight-lighter" href="<c:url value = "."/>">
				<fmt:message key="page.error.action.go_home" bundle="${cnt}"/>
			</a>
		</div>
	</div>
</div>
</div>
<t:footer/>
</body>
</html>
