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

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/owner.css" rel="stylesheet">
    <link href="./css/order_page.css" rel="stylesheet">
    <link href="./css/order_page1.css" rel="stylesheet">
    <link href="./css/order_page2.css" rel="stylesheet">
    <link href="./css/order_page3.css" rel="stylesheet">
    <link href="./css/order_page4.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/signin.css" rel="stylesheet">
    <fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
    <fmt:setBundle basename="config.content" var="cnt"/>
    <!-- Подключение библиотеки с пользовательскими тегами-->
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="./js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

    <script>
        function setCookie(cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires="+d.toUTCString();
            document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
        }

        function getCookie(cname) {
            var name = cname + "=";
            var indEq = 0;
            var keyCookie="";
            // take all cookies
            var ca = document.cookie.split(';');
            for(var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') {
                    c = c.substring(1);
                }
                indEq=c.indexOf("=");
                keyCookie=c.substring(0, indEq);
                if (keyCookie==cname) {
                    // select all after '='
                    return c.substring(cname.length+1, c.length);
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

        function checkCookie(cname) {
            var user = getCookie(cname);
            if (user != "") {
                return true;
            } else {
                return false;
            }
        }

        function deleteCookie(cname) {
            document.cookie = cname +"=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
        }

    </script>

    <script>

        // -----------------------------------------------------------------------------------------------------
        // Выбор велосипеда в рамках локации
        // -----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Работа с объектом по id = countryName
// Вызов апплета происходит по событию change
            $('#freeBicycles').on('change', function () {
                $.ajax({
                    url : 'GetBicycleImg?bicycleId='+$('#freeBicycles').val(),
                    // Структура данных, которую принимаем
                    success : function(imageString) {
                        var img = document.getElementById('pictSelBycycle');
                        img.src = "data:image/jpg;base64," + imageString;
                        document.getElementById('pictSelBycycle').style.visibility='visible';
                    }
                });
            });
        });
    </script>
    <script type="text/javascript">
        function getBicycle() {
            // name of check box
            var freeBic=document.getElementsByName('comb_name');
            // object 'bicycle' of combobox
            // selBic = combobox
            var selBic=document.getElementById('selectedBicycles');

            var i;
            for (i = 0; i < freeBic.length; i++) {
                //freeBic[i].type == "checkbox" - if use similar name in another element
                //checkCookie("Bic" + freeBic[i].value) == false - check if bicycle hasn't been already choose\
                //freeBic[i].value - id in it
                if (freeBic[i].type == "checkbox" && freeBic[i].checked == true && checkCookie("Bic" + freeBic[i].value) == false) {
                    var selImg=document.getElementById('pictBycycle' + freeBic[i].value);
                        selBic.options[selBic.options.length] = new Option(selImg.getAttribute("alt"), freeBic[i].value);

                    setCookie("Bic" + freeBic[i].value, selImg.getAttribute("alt"), 1);
                }
            }
            //Скрываем картинку
            document.getElementById('pictSelBycycle').style.visibility='hidden';
        }
    </script>
    <script type="text/javascript">
        function removeBicycle() {
            var selBic=document.getElementById('selectedBicycles');

            deleteCookie("Bic" + selBic.value);
            selBic.options[selBic.selectedIndex].remove();
        }
    </script>

    <script>
        //-----------------------------------------------------------------------------------------------------
        //
        //-----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
            $('#buttonStart').on('click', function() {
                var idVal='';
                var idPassport= document.getElementById("choosedIDPassport");
                var selBic= document.getElementById("selectedBicycles");
                var idLocation= document.getElementById("choosedLocationId");
                for (var i = 0; i < selBic.options.length; i++) {
                    idVal += selBic.options[i].value + ',';
                }

                $.ajax({
                    //url: 'startOrder',
                    url:'Controller?command=start_order',
                    data:{
                        idOfPassport: idPassport.value,
                        idVal: idVal,
                        idLocation: idLocation.value},
                    // Структура данных, которую принимаем
                    success : function(order) {
                        $('#orderID').attr('value', JSON.parse(order).id);
                        $('#startTime').attr('value', JSON.parse(order).startTimeStr);
                        $('#buttonStart').attr('readonly', 'true');
                    }
                });
            });
        });
    </script>
    <script>
        //-----------------------------------------------------------------------------------------------------
        //
        //-----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
            $('#buttonFinish').on('click', function() {
                var idOrder= document.getElementById("orderID");
                $.ajax({
                    url: 'finishOrder',
                    data:{
                        idOrder:idOrder.value
                    },
                    // Структура данных, которую принимаем
                    success : function(order) {
                        document.getElementById("finishTime").value = order;

                        var $select = $('#countryName');
                        $select.find('option').remove();
                        $select.append($('<option>').text("").attr('value', ""));
                        $select.append($('<option>').text("Belarus").attr('value', "Belarus"));
                        $select.append($('<option>').text("Poland").attr('value', "Poland"));
                        $select.append($('<option>').text("Lithuania").attr('value', "Lithuania"));
                        document.getElementById("buttonFinish").readonly = true;
                    }
                });
            });
        });
    </script>

    <script>
        // -----------------------------------------------------------------------------------------------------
        // Выбор города в рамках страны
        // -----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Работа с объектом по id = countryName
// Вызов апплета происходит по событию change
            $('#countryName').on('change', function() {
                document.getElementById("doPay").hidden = true;
                $.ajax({
                    url : 'GetCity',
                    data : {
                        // Структура данных, которую передаем
                        countryName : $('#countryName').val()
                    },
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        var $select = $('#cityName');
                        // Очищаем предыдущие значения в combobox
                        $select.find('option').remove();
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
        $(document).ready(function() {
            $('#locationName').on('change', function() {
                document.getElementById("doPay").hidden = false;
            });
        });
    </script>
    <script type="text/javascript">
        function getOrderID() {
            var x = document.getElementById('orderID');
            var y = document.getElementById('locationName');
            var str="Controller?command=payment_page&orderid=" + x.value + "&locationid=" + y.value;

            var frm = document.getElementById('submit-pay') || null;
            if(frm) {
                frm.action = str;
            }
        }
    </script>
    <script>
        function doFindOrder() {
            $.ajax({
                url : 'Controller?command=find_order',
                data : {
                    orderId : $('#orderID').val(),
                    mode: "1"
                },
                success : function(responseJson) {
                    var strJsonLoc = JSON.parse(responseJson);
                    var locName=strJsonLoc.country + ", " + strJsonLoc.city + ", " + strJsonLoc.street + ", house: " + strJsonLoc.house + ", office: " + strJsonLoc.office;

                    document.getElementById("choosedLocationId").value = strJsonLoc.id;
                    document.getElementById("choosedAddress").value = locName;
                }
            });

            $.ajax({
                url : 'Controller?command=find_order',
                data : {
                    orderId : $('#orderID').val(),
                    mode: "2"
                },
                success : function(responseJson) {
                    var strJsonUser = JSON.parse(responseJson);
                    var userName = strJsonUser.surname + " " + strJsonUser.name + " " + strJsonUser.secondName;

                    document.getElementById("choosedIDPassport").value = strJsonUser.passportIdentificationNumber;
                    document.getElementById("choosedUser").value = userName;
                }
            });

            $.ajax({
                url : 'Controller?command=find_order',
                data : {
                    orderId : $('#orderID').val(),
                    mode: "3"
                },
                success : function(responseJson) {
                    var strJsonUser = JSON.parse(responseJson);
                    var $select = $('#selectedBicycles');
                    $select.find('option').remove();
                    $select.append($('<option>').text("").attr('value', ""));
                    $.each(JSON.parse(responseJson), function(i, bic) {
                        var bicName="Id:" + bic.id + " " + bic.model + ", year: " + bic.productionYear + ", type: " + bic.bicycleType;
                        $select.append($('<option>').text(bicName).attr('value', bic.id));
                    });
                }
            });

            $.ajax({
                url : 'Controller?command=find_order',
                data : {
                    orderId : $('#orderID').val(),
                    mode: "4"
                },
                success : function(responseJson) {
                    var listTime = JSON.parse(responseJson);

                    document.getElementById("startTime").value = listTime[0];
                    document.getElementById("finishTime").value = listTime[1];
                    if (document.getElementById("finishTime").value == "")
                        document.getElementById("btnFinishDiv").hidden = false;
                    else
                        document.getElementById("btnFinishDiv").hidden = true;

                    if (document.getElementById("startTime").value != "" &&
                                document.getElementById("finishTime").value != "")
                        document.getElementById("finishLock").hidden = true;
                    else
                        document.getElementById("finishLock").hidden = false;
                }
            });
        }
    </script>
</head>

<body>
<t:nav/>
<div class="container2">
<div class="flex">
    <!--<h2 class="form-signin-heading">Select a user by key parameters: </h2>-->
    <label for="orderID" class="sr-show">ID of order</label>
    <input id="orderID" class="form-control" placeholder="" value="${numberOrder}" required autofocus>
    <div class="flex-rent" id="doFind">
        <button class="btn btn-lg btn-primary btn-block" onclick="doFindOrder()">
            <fmt:message key="order.page.btn.findorder" bundle="${cnt}"/>
        </button>
    </div>
    </br>
</div>

<div class="flex2">
    <div class="item2">
        <div class="item2">
            <h2 align="center">Choosed location</h2>
            <!--------------------------------PassportIdentificationNumber--------------------------------------------------------------->
            <label for="choosedLocationId" class="sr-show">Location ID</label>
            <input id="choosedLocationId" class="form-control" placeholder="" value="${selectLocation}" readonly autofocus>
            <!-----------------------------------------Name--------------------------------------------------------------->
            <label for="choosedAddress" class="sr-show">Address</label>
            <input id="choosedAddress" class="form-control" placeholder="" value="${selectAddress}" readonly autofocus>
        </div>
    </div>

</br>

    <div class="item2">
        <div class="item2">
            <h2>Choosed user</h2>
            <!--------------------------------PassportIdentificationNumber--------------------------------------------------------------->
            <label for="choosedIDPassport" class="sr-show">ID passport</label>
            <input id="choosedIDPassport" class="form-control" placeholder="" value="${selectIdPassport}" readonly autofocus>
            <!-----------------------------------------Surname--------------------------------------------------------------->
            <label for="choosedUser" class="sr-show">User</label>
            <input id="choosedUser" class="form-control" placeholder="" value="${selectUser}" readonly autofocus>
        </div>
    </div>
</div>

<h2 align="center">Order</h2>
<div class="flex4">
    <div class="item4">
        <select name="selectedBicycles" id="selectedBicycles" class="form-control">
            <option value=""></option>
            <c:forEach items="${bicycles}" var="bicycle">
                <option value="${bicycle.id}">Id:${bicycle.id}, ${bicycle.model}, year: ${bicycle.productionYear}, type: ${bicycle.bicycleType}</option>
            </c:forEach>
        </select>
    </div>
    <div>
    </div>
</div>

<div class="flex5">
    <div class="item5">
        <div class="item5">
            <h2 align="center">Order in the filling stage</h2>
            <!--------------------------------PassportIdentificationNumber--------------------------------------------------------------->
            <label for="startTime" class="sr-show">Start time</label>
            <input id="startTime" class="form-control" placeholder="" value="" readonly autofocus>
            <!-----------------------------------------Surname--------------------------------------------------------------->
            <label for="finishTime" class="sr-show">Finish time</label>
            <input id="finishTime" class="form-control" placeholder="" value="" readonly autofocus>
            <div class="item5" id="finishLock">
                <!-----------------------------------------Name--------------------------------------------------------------->
                <label class="sr-show">Country</label>
                <select id="countryName" class="form-control">
                </select>
                <!----------------------------------------Cities------------------------------------------------------------->
                <label class="sr-show">City</label>
                <select id="cityName" class="form-control">
                </select>
                <!----------------------------------------Location----------------------------------------------------------->
                <label class="sr-show">Location</label>
                <select id="locationName" class="form-control">
                </select>
            </div>
        </div>
    </div>

    </br>

    <div class="item5">
        <div class="item5" id="btnFinishDiv" hidden>
            <button class="btn btn-lg btn-primary btn-block" id="buttonFinish" >Finish</button>
        </div>
        </br>
        </br>
        </br>
        <div class="item5" id="doPay" hidden>
            <form name="submit-pay" id="submit-pay" class="form-signin" method="post" action="/">
                <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="getOrderID()">Pay</button>
            </form>
        </div>
    </div>
</div>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- <script src="./js/ie10-viewport-bug-workaround.js"></script> -->
</div>
<tag:footer/>
</body>
</html>