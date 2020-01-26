<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=KOI8-R" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <!--<link rel="stylesheet" type="text/css" href="../css/about_form.css" media="all"/>-->
    <fmt:setLocale value="${empty cookie.lang.value ? 'en_US' : cookie.lang.value}"/>
    <fmt:setBundle basename="config.content" var="cnt"/>
    <meta charset="KOI8-R">
    <title>Website Layout with Flexbox</title>

</head>

<body>
<div class="my_flex_container1">
    <div class="my_flex_block">
         <fmt:message key="about_page.invocation1" bundle="${cnt}"/>
       </div>
    <div class="my_flex_block">
         <fmt:message key="about_page.invocation2" bundle="${cnt}"/>
    </div>
    <div class="my_flex_block">
         <fmt:message key="about_page.invocation3" bundle="${cnt}"/>
    </div>
    <div class="my_flex_block">
        <fmt:message key="about_page.invocation4" bundle="${cnt}"/>
    </div>
    <div class="my_flex_block">
        <fmt:message key="about_page.invocation5" bundle="${cnt}"/>
    </div>
    <div class="my_flex_block">
        <fmt:message key="about_page.invocation6" bundle="${cnt}"/>
    </div>
</div>

<main class="main columns">

    <section class="column main-column">

        <a class="article first-article" href="#" >
            <figure class="article-image is-4by3">
                <img src="company.jpg" alt="">
            </figure>
            <div class="article-body">
                <h2 class="article-title">
                    <fmt:message key="about_page.label.about_us" bundle="${cnt}"/>
                </h2>
                <p class="article-content">
                   <fmt:message key="about_page.text.about_us" bundle="${cnt}"/>

                    <!--We are a company offering rental bicycles of well-known manufacturers.
                    <br>
                    Do you ride a bicycle? I fancy it so much! To go for a ride alone or with my friends in the evening is a big joy for me. First of all, this is a kind of sport. Sitting at the computer desk all day long is not actually that healthy.
                    <br>
                    We don’t do much physical work, and for this reason, we feel the need for exercise at the end of the day.
                    <br>It is well-known that sedentary life may cause a lot of diseases. But in most cases we just don’t have any choice because this is our job. I think that One of the solutions of the problem is using a bicycle instead of public transport or a car. Bicycling is widely spread in the developed European countries. It is difficult to say, for example, whether the number of cyclists exceeds that of car drivers in the Netherlands or vice versa.
                    <br>When you look at the empty roads of Rotterdam you have a feeling that the cyclists definitely have some advantage. Bicycling means not only a healthy way of life but also the improvement of the city ecology and saving of your own money.-->
                </p>
                <footer class="article-info">
                    <span>
                         <fmt:message key="about_page.after.about_us" bundle="${cnt}"/>
                    </span>
                </footer>
            </div>
        </a>

        <a class="article" href="#">
            <figure class="article-image is-16by9">
                <img src="bicycle.jpg" alt="">
            </figure>
            <div class="article-body">
                <h2 class="article-title">
                    <fmt:message key="about_page.label.about_bic" bundle="${cnt}"/>
                </h2>
                <p class="article-content">
                    <fmt:message key="about_page.text.about_bic" bundle="${cnt}"/>

                   <!-- The bicycle has a very interesting inner history of invention which started long time ago. They say that Leonardo da Vinchy was the first to invent a bicycle. There are no facts in proof of this claim but taking into account all his developments it is easy to believe that it could have been his invention. But one thing is to suppose, the other thing is to have some facts. People have been using the bicycle for 200 years!
                    <br>A real history started with the invention of German Baron Karl von Drais. He presented his wooden “Draisienne” in about 1818. His vehicle was quite similar to the modern bicycle but there with a slight difference; namely, it didn’t have any pedals.
                    <br>The first bicycle was run with the help of the legs. Only in 20 years later the “Draisienne” vehicle was improved by Kirkpatrick MacMillan, a Scottish blacksmith, who added this missing element. During the XIX century different people from Europe and America worked at the modification of the bicycle but the most important modification was made in the second half of the XIX century when James K. Star, a Scottish veterinarian, added an irrigation hose to the wheels. Thus, the tire became an integral part of the bicycle. It can be said that after the tires had been installed the period of modern bicycle started. And now about 100 million bicycles are sold worldwide each year.-->
                </p>
                <footer class="article-info">
                    <span>
                          <fmt:message key="about_page.after.about_bic" bundle="${cnt}"/>
                    </span>
                </footer>
            </div>
        </a>

        <a class="article" href="#">
            <figure class="article-image is-16by9">
                <img src="contact.jpg" alt="">
            </figure>
            <div class="article-body">
                <h2 class="article-title">
                    <fmt:message key="about_page.label.contact" bundle="${cnt}"/>
                </h2>
                <p class="article-content">
                    <fmt:message key="about_page.text.contact" bundle="${cnt}"/>

                    <!--Our telephone numbers:
                    <br>Belarus: 8 029 777 77 77, 8 029 888 88 88
                    <br>Poland: 8 048 777 77 77, 8 048 888 88 88
                    <br>Lithuania:  8 370 777 77 77, 8 370 888 88 88
                    <br>The first person who circled the globe by bicycle in 1935 was Fred A. Birchmore. He crossed Europe, Asia, the United States having covered the distance of 40 000 miles. It is difficult to imagine the number of tires he used! I believe that it was the best year of his life.
                    <br>And what about the most famous bicycle race “Tour de France”? It was the year of 1903 when it started for the first time. From the time the bicycle was invented and up to the present moment it has become not only the vehicle but the style of life.
                    <br>
                    It is possible to find lots of examples of cooperation between a man and a bicycle when acting together they have become a team. But the main question whether there is a place for bicycle in your life.
                    <br>
                    I was 5 years old when one morning my parents met me with inscrutable smiles. A new blue bicycle was waiting for me behind the door. During the next few years I and my friends spent hours riding the bicycles almost every day.
                    <br>
                    I will remember forever the moment when I saw my first bicycle.-->
                </p>
                <footer class="article-info">
                    <span>
                         <fmt:message key="about_page.after.contact" bundle="${cnt}"/>
                    </span>
                </footer>
            </div>
        </a>
    </section>

</main>




<!-- <Элемент <div> является блочным элементом и предназначен для выделения фрагмента документа с целью изменения вида содержимого. > - Тег для создания ссылки -->
<!-- <a> - Тег для создания ссылки -->
<!-- <figure> - Тег для группирования элементов -->
<!-- <p> - Тег <p> является блочным элементом, всегда начинается с новой строки, абзацы текста идущие друг за другом разделяются между собой отбивкой. -->
<!-- <main> - Элемент <main> предназначен для основного содержимого документа. Содержимое должно быть уникальным и не включать типовые блоки вроде шапки сайта, подвала, навигации, боковой панели, формы поиска и т. п. -->
</div>

</body>

<style>
    html {
        background: #e3f3ff;
        font-family: sans-serif;
        font-size: 14px;
    }

    a {
        text-decoration: none;     <!-- Отменяет все эффекты, в том числе и подчеркивания у ссылок, которое задано по умолчанию. -->
    }

    div, h2, p, figure {
        margin: 0;
        padding: 0;
    }

    .main {
        margin: 0 auto;            <!-- Устанавливает величину отступа от каждого края элемента. Отступом является пространство от границы текущего элемента до внутренней границы его родительского элемента -->
        max-width: 1600px;         <!-- Ширина main (основного содержимого) -->
        padding: 20px;             <!-- Устанавливает значение полей вокруг содержимого элемента. Полем называется расстояние от внутреннего края рамки элемента до воображаемого прямоугольника, ограничивающего его содержимое  -->
    }

    .columns {
        display: flex;
    }

    .column {
        display: flex;
        flex-direction: row;

    }

    .main-column {

    }

    .article {
        background: white;
        color: #666;
        display: flex;
        flex: 0.33333;            <!-- Выравниваем ширину блоков одинаково (в первом ряду 3 блока по 033333X) -->
        flex: 1;
        flex-direction: column;   <!-- Устанавливает отображение Рисунок + текст столбиком -->
        flex-basis: auto;
        margin: 10px;
    }


    .article-image {
        background: #eee;
        display: block;
        padding-top: 75%;
        position: relative;
        width: 100%;
    }

    .article-image img {
        display: block;
        height: 100%;
        left: 0;
        position: absolute;
        top: 0;
        width: 100%;
    }


    input[type=text]{
        width:520px;
    }

    input[type=button]{
        width:500px;

    }

    .article-image.is-16by9 {
        padding-top: 75%;         <!-- Проставляем расстояние до нижней границы (от картинки до нижней границы блока)-->
    }


    .article-body {
        display: flex;
        flex: 1;
        flex-direction: column;
        padding: 20px;
    }

    .article-title {
        color: #333;
        flex-shrink: 0;
        font-size: 1.4em;
        font-weight: bold;
        font-weight: 700;
        line-height: 1.2;
    }

    .article-content {
        flex: 1;
        margin-top: 1px;
    }

    .article-info {
        display: flex;
        font-size: 0.85em;
        justify-content: space-between;
        margin-top: 10px;
    }

    .my_flex_container1{
        display: flex;
        flex-direcrion:row;             <!-- Направление слева направо, работаем со строкой -->
        justify-content:space-between;  <!-- Распределили текстовые контейнеры равномерно по линии -->
        align-items: flex-start;        <!-- Блоки прижаты к началу поперечной оси -->

    }


    .my_flex_block {
        width: 300px;
        height: 20px;
        margin: auto;
        background-color: #E5EBFE;
        text-align: center;
        font-style:italic;
        font-weight:bold;
        font-family: ‘Monotype Corsiva’;
        box-shadow: inset 2px 2px 5px rgba(154, 147, 140, 0.5), 1px 1px 5px rgba(255, 255, 255, 1);}



    .caption{
        background-color: #E5EBFE;
        text-align: center;
        font-style:italic;
        font-family: ‘Monotype Corsiva’;
        box-shadow: inset 2px 2px 5px rgba(154, 147, 140, 0.5), 1px 1px 5px rgba(255, 255, 255, 1);}
</style>

