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

    <title>Bicycle's rent</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/owner.css" rel="stylesheet">
    <link href="./css/selected_user.css" rel="stylesheet">
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="./css/signin.css" rel="stylesheet">
    <link href="./css/order_page1.css" rel="stylesheet">
    <link href="./css/flex_new_card.css" rel="stylesheet">
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
        //Выбор локации в рамках города
        //-----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Работа с объектом по id = countryName
// Вызов апплета происходит по событию change
            $('#findUser').on('click', function() {
                $.ajax({
                    url : 'GetUser',
                    data : {
                        // Структура данных, которую передаем
                        userDocId: $('#inputPassportIdentificationNumber').val()
                    },
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        // Сооружаем конструкцию типа <option value="City">City
                        if (responseJson != "" && responseJson != "{}") {
                            var curUser = JSON.parse(responseJson);
                           // $('#userName').attr('value', curUser.name);
                           // $('#userSurname').attr('value', curUser.surname);
                           // $('#userCountry').attr('value', curUser.country);
                            document.getElementById("userSurname").value = curUser.surname;
                            document.getElementById("userName").value = curUser.name;
                            document.getElementById("userCountry").value = curUser.country;
                            document.getElementById("selectedUserIsNotExist").hidden = true;
                            document.getElementById("addCardDiv").hidden = false;
                            document.getElementById("selUser").hidden = false;
                        }
                        else {
                            document.getElementById("selectedUserIsNotExist").hidden = false;
                            document.getElementById("addCardDiv").hidden = true;
                            document.getElementById("userSurname").value = "";
                            document.getElementById("userName").value = "";
                            document.getElementById("userCountry").value = "";
                        }
                        var controlId = document.getElementById("selectedUserIsNotExist2");
                        if (controlId!=null)
                            controlId.hidden=true;
                    }
                });

                $.ajax({
                    url : 'GetUserCards',
                    data : {
                        // Структура данных, которую передаем
                        userDocId: $('#inputPassportIdentificationNumber').val()
                    },
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        var $select = $('#cardName');
                        // Очищаем предыдущие значения в combobox
                        $select.find('option').remove();
                        // Добавляем пустую строчку
                        $select.append($('<option>').text("").attr('value', ""));

                        if (responseJson!="" && responseJson != "{}") {
                            $.each(JSON.parse(responseJson), function (i, cards) {
                                $select.append($('<option>').text(cards.name).attr('value', cards.id));
                            });
                        }
                    }
                });

                $.ajax({
                    url : 'Controller?command=get_currency_admin',
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        var $select = $('#currencyNewCard');
                        // Очищаем предыдущие значения в combobox
                        $select.find('option').remove();
                        // Добавляем пустую строчку
                        $select.append($('<option>').text("").attr('value', ""));

                        if (responseJson!="" && responseJson != "{}") {
                            $.each(JSON.parse(responseJson), function (i, ccy) {
                                $select.append($('<option>').text(ccy).attr('value', ccy));
                            });
                        }
                    }
                });
            });
        });
    </script>
    <script>
        //-----------------------------------------------------------------------------------------------------
        //Получение баланса и валюты выбранной карточки
        //-----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
// Работа с объектом по id = countryName
// Вызов апплета происходит по событию change
            $('#cardName').on('change', function() {
                $.ajax({
                    url : 'GetUserCardBalance',
                    data : {
                        // Структура данных, которую передаем
                        // value's content is id of card
                        userCardId: $('#cardName').val()
                    },
                    // Структура данных, которую принимаем
                    success : function(responseJson) {
                        // Сооружаем конструкцию типа <option value="City">City
                        var curCard=JSON.parse(responseJson);
                        $('#currencyCard').attr('value', curCard.currency);
                        $('#balanceCard').attr('value', curCard.balance);
                    }
                });
            });
        });
    </script>
    <script>
        //-----------------------------------------------------------------------------------------------------
        // Пополнение баланса
        //-----------------------------------------------------------------------------------------------------
        $(document).ready(function() {
            $('#buttonTopUpBalance').on('click', function() {

                if (isNum($('#rechargeableBalanceCard').val())) {
                    $.ajax({
                        url: 'Controller?command=user_pageup_balance',
                        data: {
                            // Структура данных, которую передаем
                            userCardId: $('#cardName').val(),
                            userCardAmmount: $('#rechargeableBalanceCard').val().replace(",", ".")
                        },
                        // Структура данных, которую принимаем
                        success: function (balanceCard) {
                            // Сооружаем конструкцию типа <option value="City">City
                            $('#balanceCard').attr('value', balanceCard);
                            document.getElementById("messageCheckCardTopUpBalance").hidden = true;
                        }
                    });
                }
                else {
                    document.getElementById("messageCheckCardTopUpBalance").hidden = false;
                }
            });
        });

    </script>
    <script>
        function showForm() {
            document.getElementById("newCardDiv").hidden = false;
        }
    </script>
    <script>
        function hideForm() {
            document.getElementById("newCardDiv").hidden = true;
            document.getElementById("nameNewCard").value = "";
            document.getElementById("currencyNewCard").value = "";
            document.getElementById("balanceNewCard").value = "";
            document.getElementById("messageCheckCardBalance").hidden = true;
        }
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
            $('#okNewCard').on('click', function() {
                // work by id
                if (isNum($('#balanceNewCard').val())) {
                    $.ajax({
                        url: 'Controller?command=add_new_card_admin',
                        data: {
                            // Структура данных, которую передаем
                            userDocId: $('#inputPassportIdentificationNumber').val(),
                            userCardName: $('#nameNewCard').val(),
                            userCardCcy: $('#currencyNewCard').val(),
                            userCardAmmount: $('#balanceNewCard').val()
                        },
                        // Структура данных, которую принимаем
                        success: function (responseId) {
                            var $select = $('#cardName');
                            if (responseId != null && responseId != "") {
                                // new element of combobox
                                $select.append($('<option>').text($('#nameNewCard').val()).attr('value', responseId));
                            }
                            hideForm();
                        }
                    });
                }
                else
                {
                    document.getElementById("messageCheckCardBalance").hidden = false;
                }
            });
        });
    </script>
</head>

<body>
<t:nav/>

    <!--<h2 class="form-signin-heading">Select a user by key parameters: </h2>-->
<div class="flex2">
    <div class="item2">
        <div class="item2">
            <!--------------------------------PassportIdentificationNumber--------------------------------------------------------------->
            <form class="form-signin" action="Controller?command=edit_user_page_admin" method="post">
                <label style="color: blue">${msgString}</label>
                <div id="selectedUserIsNotExist" class="item2" hidden>
                    <label style="color: red">
                        <fmt:message key="selected.user.page.message_user_not_exist" bundle="${cnt}"/>
                    </label>
                </div>
                <c:if test="${messageUserIsNotExist != null}">
                    <label id="selectedUserIsNotExist2" style="color: red">
                        <fmt:message key="selected.user.page.message_user_not_exist" bundle="${cnt}"/>
                    </label>
                </c:if>

                <label for="inputPassportIdentificationNumber" class="sr-show">Passport identification number</label>
                <input type="text" id="inputPassportIdentificationNumber" name="userPassportIdentificationNumber" class="form-control" placeholder="" value="${idValue}" required autofocus>
                <button class="btn btn-lg btn-primary btn-block" type="register" >Edit</button>
            </form>
            </br>
            <div class="form-signin">
                <button class="btn btn-lg btn-primary btn-block" type="topUp" id="findUser" > Find user </button>
            </div>
            </br>
            </br>
            </br>
            <!-----------------------------------------Surname--------------------------------------------------------------->
            <label for="userSurname" class="sr-show">Surname</label>
            <input type="surname" id="userSurname" name="userSurname" class="form-control" placeholder="" required autofocus>
            <!-----------------------------------------Name--------------------------------------------------------------->
            <label for="userName" class="sr-show">Name</label>
            <input type="name" id="userName" name="userName" class="form-control" placeholder="" required autofocus>
            <!-----------------------------------------country--------------------------------------------------------------->
            <label for="userCountry" class="sr-show">Country</label>
            <input type="country" id="userCountry" name="userCountry" class="form-control" placeholder=""  autofocus>
            <!--------------------------------Virtual card--------------------------------------------------------------->
            <label class="sr-show">Virtual card</label>
            <!--combobox-->
            <select id="cardName" class="form-control">
                <option value=""></option>
            </select>

            <div id="addCardDiv" class="item2" hidden>
                <button class="btn btn-lg btn-primary btn-block" type="topUp" id="addCard" onclick="showForm()"> Add new card </button>
            </div>
            <div id="newCardDiv" class="flexNewCard" hidden>
                <div class="itemNewCard">
                    <label for="nameNewCard" class="sr-show">Card name</label>
                    <input type="currency" id="nameNewCard" name="nameNewCard" class="form-control" placeholder="" autofocus>
                    <label for="currencyNewCard" class="sr-show">Currency</label>
                    <select id="currencyNewCard" class="form-control">
                        <option value=""></option>
                    </select>
                    <label for="balanceNewCard" class="sr-show">Card balance</label>
                    <input type="balance" id="balanceNewCard" name="balanceNewCard" class="form-control" placeholder="" autofocus>
                    <div id="messageCheckCardBalance" class="item2" hidden>
                        <label style="color: red">
                            <fmt:message key="message.error.form.selected.user.page.cardbalance" bundle="${cnt}"/>
                        </label>
                    </div>
                </div>
                <div class="flexNewCard">
                    <div class="itemNewCard">
                        <button class="btn btn-lg btn-primary btn-block" type="topUp" id="okNewCard" > Ok </button>
                    </div>
                    <div class="itemNewCard">
                        <button class="btn btn-lg btn-primary btn-block" type="topUp" id="cancelNewCard" onclick="hideForm()"> Cancel </button>
                    </div>
                </div>
            </div>
            </br>
            </br>
            </br>
            <!--------------------------------Balance--------------------------------------------------------------->
            <label for="balanceCard" class="sr-show">Virtual card's balance</label>
            <input type="balance" id="balanceCard" name="userBalanceCard" class="form-control" placeholder="" autofocus>
            <!--------------------------------Currency--------------------------------------------------------------->
            <label for="currencyCard" class="sr-show">Currency</label>
            <input type="currency" id="currencyCard" name="userCurrencyCard" class="form-control" placeholder="" autofocus>
        </div>

        </br>
        </br>
        </br>

        <div class="item2">
            <!--------------------------------Top up--------------------------------------------------------------->
            <label for="rechargeableBalanceCard" class="sr-show">Rechargeable balance</label>
            <input type="rechargeableBalance" id="rechargeableBalanceCard" name="userRechargeableBalanceCard" class="form-control" placeholder="" autofocus>
            <div id="messageCheckCardTopUpBalance" class="item2" hidden>
                <label style="color: red">
                    <fmt:message key="message.error.form.selected.user.page.cardbalance.up" bundle="${cnt}"/>
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="topUp" id="buttonTopUpBalance">Top up balance</button>
        </div>
    </div>

</br>

    <div class="item2">
        <div class="item2" id="selUser" hidden>
            <form action="Controller?command=main_page" method="post">
                <button class="btn btn-lg btn-primary btn-block" type="topUp" id="selectUser"> Select this user </button>
            </form>
        </div>

        </br>

        <form action="Controller?command=register_command&mode=admin" method="post">
            <div class="item2">
                <button class="btn btn-lg btn-primary btn-block" type="topUp" id="addUser"> Add user </button>
            </div>
        </form>
        <!--

                </br>
                </br>
                <form action="Controller?command=edit_user_page_admin" method="post">
                    <div class="item2">
                        <button class="btn btn-lg btn-primary btn-block" type="topUp" id="editUser"> Edit user </button>
                    </div>
                </form>
        -->
        </br>

        <div class="item2">
            <button class="btn btn-lg btn-primary btn-block" type="topUp" onclick="history.back()"> Back </button>
        </div>
    </div>
</div>
<t:footer/>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>