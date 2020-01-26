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
    <link href="./css/location.css" rel="stylesheet">
    <link href="./css/location2.css" rel="stylesheet">
    <link href="./css/owner.css" rel="stylesheet">
    <fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
    <fmt:setBundle basename="config.content" var="cnt"/>
    <!-- Подключение библиотеки с пользовательскими тегами-->
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <script src="./js/ie-emulation-modes-warning.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

    <script>

        // -----------------------------------------------------------------------------------------------------
        // Выбор города в рамках страны
        // -----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Работа с объектом по id = countryName
// Вызов апплета происходит по событию change
            $('#countryName').on('change', function() {
                $.ajax({
                    url : 'GetCity',
                    data : {
                        // Структура данных, которую передаем
                        countryName : $('#countryName').val()
                    },
                    // Структура данных, которую принимаем (массив в виде строки)
                    success : function(responseJson) {
                        //Создаем переменную, указывающую на элемент формы, в которую размещаем соответсвующие значения
                        var $select = $('#cityName');
                        // Очищаем предыдущие значения в combobox
                        $select.find('option').remove();
                        $('#locationName').find('option').remove();
                        $('#bicyclesName').find('option').remove();
                        //Скрываем картинки
                        document.getElementById('loc1').style.visibility='hidden';
                        document.getElementById('loc2').style.visibility='hidden';
                        // Добавляем пустую строчку
                        $select.append($('<option>').text("").attr('value', ""));
                        // Сооружаем конструкцию типа <option value="City">City
                        $.each(JSON.parse(responseJson), function(i, city) {
                            $select.append($('<option>').text(city).attr('value', city));
                        });
                    }
                });
            });
        });
    </script>
    <script>
 //-----------------------------------------------------------------------------------------------------
 //Выбор локации в рамках города
//-----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Работа с объектом по id = countryName
// Вызов апплета происходит по событию change
            //GetLocation?cityName=...&countryName=...
            $('#cityName').on('change', function() {
                $.ajax({
                    url : 'GetLocation',
                    data : {
                        // Структура данных, которую передаем
                        cityName : $('#cityName').val(),
                        countryName : $('#countryName').val()
                    },
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        var $select = $('#locationName');
                        // Очищаем предыдущие значения в combobox
                        $select.find('option').remove();
                        document.getElementById('loc1').style.visibility='hidden';
                        document.getElementById('loc2').style.visibility='hidden';
                        $('#bicyclesName').find('option').remove();
                        // Добавляем пустую строчку
                        $select.append($('<option>').text("").attr('value', ""));
                        // Сооружаем конструкцию типа <option value="City">City
                        $.each(JSON.parse(responseJson), function(i, city) {
                            var valueForLocationStr = city.street + ", house: " + city.house + ", office: " + city.office;
                            $select.append($('<option>').text(valueForLocationStr).attr('value', city.id));
                        });
                    }
                });
            });
        });
    </script>
    <script>

        // -----------------------------------------------------------------------------------------------------
        // Выбор велосипеда в рамках локации
        // -----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Работа с объектом по id = countryName
// Вызов апплета происходит по событию change
            $('#locationName').on('change', function() {
                $.ajax({
                    url : 'GetFreeBicycles',
                    data : {
                        // Структура данных, которую передаем
                        locationId : $('#locationName').val()
                    },
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        var $select = $('#bicyclesName');
                        // Очищаем предыдущие значения в combobox
                        $select.find('option').remove();
                        // Добавляем пустую строчку
                        $select.append($('<option>').text("").attr('value', ""));
                        document.getElementById('loc2').style.visibility='hidden';
                        // Сооружаем конструкцию типа <option value="Bicycle">Bicycle
                        $.each(JSON.parse(responseJson), function(i, bicycle) {
                            var valueForBicycleStr = "Model: " + bicycle.model + ", type: " + bicycle.bicycleType.toLowerCase() +
                                ", producer: " + bicycle.producer + ", production year: " + bicycle.productionYear;
                            $select.append($('<option>').text(valueForBicycleStr).attr('value', bicycle.id));
                        });
                    }
                });

                // -----------------------------------------------------------------------------------------------------
                // Выбор фото точки локации
                // -----------------------------------------------------------------------------------------------------
                $.ajax({
                    url : 'GetLocationImg?locationId='+$('#locationName').val(),
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        $('#loc1').attr('src', "." + responseJson.toString().replace('"', '').replace('"', ''));
                        document.getElementById('loc1').style.visibility='visible';
                    }
                });
            });
        });
    </script>
    <script>
        // -----------------------------------------------------------------------------------------------------
        // Выбор фото велосипеда
        // -----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Вызов апплета происходит по событию change
            $('#bicyclesName').on('change', function() {
                $.ajax({
                    url : 'GetBicycleImg?bicycleId='+$('#bicyclesName').val(),
                    // Структура данных, которую принимаем
                    success : function(imageString) {
                        //указатель на картику
                        var img = document.getElementById('loc2');
                        img.src = "data:image/jpg;base64," + imageString;
                        document.getElementById('loc2').style.visibility='visible';
                    }
                });
            });
        });
    </script>
</head>

<body>
<c:if test="${isAdminTag == null}">
    <t:nav_user/>
</c:if>
<c:if test="${isAdminTag != null}">
    <t:nav/>
</c:if>

<div class="flex">
    <div class="item">

        <form class="form-location" action="Controller?command=main_page" method="post">
            <h2 class="form-signin-heading">Select locations:</h2>
            <label class="sr-only">Data</label>
            <!---------------------------------------Country----------------------------------------------------------------->
            <label class="sr-show">Country</label>
            <form class="form-location">
                <!--value объекта countryName (обращение по id) забираем request.getParameter("countryName")  -->
                <!--combobox -->
                <select id="countryName" class="form-control">
                    <option value="">
                    <c:if test="${not empty selectedCountry}">
                        <option value="${selectedCountry}" selected>${selectedCountry}</option>
                    </c:if>
                    <c:if test="${selectedCountry != 'Belarus'}">
                        <option value="Belarus">Belarus
                    </c:if>
                    <c:if test="${selectedCountry != 'Poland'}">
                        <option value="Poland">Poland
                    </c:if>
                    <c:if test="${selectedCountry != 'Lithuania'}">
                        <option value="Lithuania">Lithuania
                    </c:if>
                </select>
                <!----------------------------------------Cities------------------------------------------------------------->
                <label class="sr-show">City</label>
                <select id="cityName" class="form-control">
                    <option value="">
                    <c:forEach var="city" items="${citys}">s
                        <c:if test="${!selectedCity.equals(city)}">
                            <option value="${city}">${city}</option>
                        </c:if>
                        <c:if test="${selectedCity.equals(city)}">
                            <option value="${selectedCity}" selected>${selectedCity}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <!----------------------------------------Location----------------------------------------------------------->
                <label class="sr-show">Location</label>
                <select id="locationName" class="form-control">
                    <option value="">
                    <c:forEach items="${locations}" var="location">
                        <c:if test="${!selectedLocationId.equals(location.id)}">
                            <option value="${location.id}">${location.street} , house: ${location.house} , office: ${location.office}</option>
                        </c:if>
                        <c:if test="${selectedLocationId.equals(location.id)}">
                            <option value="${selectedLocationId}" selected>${selectedLocation}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label class="sr-show">Free bicycles</label>
                <select id="bicyclesName" class="form-control">
                </select>

                <button class="btn btn-lg btn-primary btn-block" type="submit" >Choose location</button>
            </form>
            <!-- btn btn-lg btn-primary btn-block-->
        </form>
    </div>

    <div class="item">
        <div class="item">
            <img id="loc1" src="${selectedImg}" width="200px" height="133px">
        </div>
        <div class="item">
            <img id="loc2" width="200px" height="133px">
        </div>
    </div>

</div> <!-- /container -->
<t:footer/>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>