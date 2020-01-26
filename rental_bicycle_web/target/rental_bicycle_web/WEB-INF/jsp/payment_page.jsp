<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
    <fmt:setBundle basename="config.content" var="cnt"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./img/site_img/favicon.ico">

    <title>Payment page</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/owner.css" rel="stylesheet">
    <link href="./css/payment.css" rel="stylesheet">
    <link href="./css/flex_new_card.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">
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
        //-----------------------------------------------------------------------------------------------------
        //Получение баланса и валюты выбранной карточки
        //-----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Вызов апплета происходит по событию change
            $('#userCards').on('change', function() {
                $.ajax({
                    url : 'GetUserCardBalance',
                    data : {
                        // Структура данных, которую передаем
                        // value's content is id of card
                        userCardId: $('#userCards').val()
                    },
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        // Сооружаем конструкцию типа <option value="xxx">xxx
                        var curCard=JSON.parse(responseJson);
                        $('#cardCurrency').attr('value', curCard.currency);
                        $('#cardBalance').attr('value', curCard.balance);

                        if ($('#orderCurrency').val() != $('#cardCurrency').val()) {
                            document.getElementById("messageDifCurr").hidden = false;
                            document.getElementById("newRateCurrDiv").hidden = false;
                            document.getElementById("doPay").hidden = true;
                        }
                        else{
                            document.getElementById("messageDifCurr").hidden = true;
                            document.getElementById("newRateCurrDiv").hidden = true;
                            document.getElementById("doPay").hidden = false;
                        }

                        document.getElementById("balanceEq").value="";
                        document.getElementById("rateCurr").value="";
                    }
                });
            });
        });
    </script>
    <script>
        function isNum(strNumber)
        {
            var res=true;

            if(isNaN(strNumber.replace(",", "."))){
                res=false;
            }
            return res;
        }
        $(document).ready(function() {
            $('#calcBalance').on('click', function() {
                // work by id
                if (isNum($('#rateCurr').val())) {
                    document.getElementById("balanceEq").value = (document.getElementById("orderAmount").value /
                                                            document.getElementById("rateCurr").value).toFixed(2);
                    document.getElementById("messageCheckCalcBalance").hidden = true;
                    document.getElementById("doPay").hidden = false;
                }
                else {
                    document.getElementById("messageCheckCalcBalance").hidden = false;
                    document.getElementById("balanceEq").value="";
                }
            });
        });
    </script>
    <script>
        function doMainPay(){
            var resAmount=0;
            document.getElementById("doResSuccess").hidden = true;
            document.getElementById("doResError").hidden = true;
            if ($('#orderCurrency').val() != $('#cardCurrency').val()) {
                resAmount=$('#balanceEq').val();
            }
            else{
                resAmount=$('#orderAmount').val();
            }
            if (resAmount>$('#cardBalance').val()){
                document.getElementById("doResError").hidden = false;
            }
            else {
                $.ajax({
                    url: 'Controller?command=do_main_pay',
                    data: {
                        // Структура данных, которую передаем
                        // value's content is id of card
                        userCardId: $('#userCards').val(),
                        ammountForPay: resAmount
                    },
                    // Структура данных, которую принимаем
                    success: function (response) {
                        if (response == "true") {
                            document.getElementById("doPay").hidden = true;
                            document.getElementById("doBack").hidden = false;
                            document.getElementById("doResSuccess").hidden = false;
                        } else {
                            document.getElementById("doResError").hidden = false;
                        }
                        // Сооружаем конструкцию типа <option value="xxx">xxx
                        var curCard = JSON.parse(responseJson);
                        $('#cardCurrency').attr('value', curCard.currency);
                        $('#cardBalance').attr('value', curCard.balance);

                        if ($('#orderCurrency') != $('#cardCurrency')) {
                            document.getElementById("messageDifCurr").hidden = false;
                            document.getElementById("newRateCurrDiv").hidden = false;
                            document.getElementById("doPay").hidden = true;
                            document.getElementById("userCards").readonly = true;
                        } else {
                            document.getElementById("messageDifCurr").hidden = true;
                            document.getElementById("newRateCurrDiv").hidden = true;
                            document.getElementById("doPay").hidden = false;
                        }

                        document.getElementById("balanceEq").value = "";
                        document.getElementById("rateCurr").value = "";
                    }
                });
            }
        }
    </script>

</head>

<body>
<t:nav/>
<div class="flex-rent">
    <div class="item-rent">
        <h2 align="center">Payment</h2>
    </div>
    <div class="item-rent">
        <div class="item-rent">
            <label for="idPayment" class="sr-show">IdPayment</label>
            <input id="idPayment" class="form-control" placeholder="" value="${idPayment}" readonly autofocus>
            <label for="idPassportUser" class="sr-show">IdPassportUser</label>
            <input id="idPassportUser" class="form-control" placeholder="" value="${IdPassportUser}" readonly autofocus>
            <label for="userCards" class="sr-show">Card</label>
            <select id="userCards" class="form-control">
                <option value=""></option>
                <c:forEach items="${cards}" var="card">
                    <option value="${card.id}">Id:${card.id}, ${card.name}</option>
                </c:forEach>
            </select>
            <label for="cardBalance" class="sr-show">Balance</label>
            <input id="cardBalance" class="form-control" placeholder="" value="" readonly autofocus> <!--${cardBalance}-->
            <label for="cardCurrency" class="sr-show">Currency</label>
            <input id="cardCurrency" class="form-control" placeholder="" value="" readonly autofocus> <!--${cardCurrency}-->
            <label for="selectedBicycles" class="sr-show">Bicycles</label>
            <select id="selectedBicycles" class="form-control">
                <c:forEach items="${bicycles}" var="bicycle">
                    <option value="${bicycle.id}">Id:${bicycle.id}, ${bicycle.model}, year: ${bicycle.productionYear}, type: ${bicycle.bicycleType}</option>
                </c:forEach>
            </select>
            <label for="orderDuration" class="sr-show">Duration</label>
            <input id="orderDuration" class="form-control" placeholder="" value="${orderDuration}" readonly autofocus>
            <label for="orderAmount" class="sr-show">Amount</label>
            <input id="orderAmount" class="form-control" placeholder="" value="${orderAmount}" readonly autofocus>
            <label for="orderCurrency" class="sr-show">Currency</label>
            <input id="orderCurrency" class="form-control" placeholder="" value="${orderCurrency}" readonly autofocus>
            <div id="messageDifCurr" hidden>
                <label style="color: red">
                    <fmt:message key="message.error.form.payment.page.difcurr" bundle="${cnt}"/>
                </label>
            </div>
            </br>
            <div id="newRateCurrDiv" class="flexNewCard" hidden>
                <div class="itemNewCard">
                    <label for="rateCurr" class="sr-show">Rate of conversion: 1 CURR = ___ BYN </label>
                    <input type="balance" id="rateCurr" name="rateCurr" class="form-control" placeholder="" autofocus>
                    <label for="balanceEq" class="sr-show">Equivalent</label>
                    <input type="balance" id="balanceEq" name="balanceEq" class="form-control" placeholder="" readonly>
                    <div id="messageCheckCalcBalance" hidden>
                        <label style="color: red">
                            <fmt:message key="message.error.form.payment.page.rate.curr" bundle="${cnt}"/>
                        </label>
                    </div>
                </div>
                <div class="flexNewCard">
                    <div class="itemNewCard">
                        <button class="btn btn-lg btn-primary btn-block" type="topUp" id="calcBalance" > Calculate </button>
                    </div>
                </div>
            </div>
            </br>
            <div class="flex-rent" id="doPay" hidden>
                <button class="btn btn-lg btn-primary btn-block" onclick="doMainPay()">Do Pay</button>
            </div>
            <div class="flex-rent" id="doBack" hidden>
                <form action="Controller?command=main_page" method="post">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Back to Main page</button>
                </form>
            </div>
            <div class="flex-rent" id="doResSuccess" hidden>
                <label id="orderResultSuccess" class="sr-show" style="color: red">
                    <fmt:message key="message.inform.payment.result.success" bundle="${cnt}"/>
                </label>
            </div>
            <div class="flex-rent" id="doResError" hidden>
                <label id="orderResultError" class="sr-show" style="color: red">
                    <fmt:message key="message.error.payment.result.failure" bundle="${cnt}"/>
                </label>
            </div>
         </div>
    </div>
</div>
<t:footer/>
</body>
</html>