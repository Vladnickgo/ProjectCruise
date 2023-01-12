<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="AboutUs"/>

<c:import url="../views/head.jsp"/>
<head>
    <link rel="stylesheet" type="text/css" href="/views/style/styleAbout.css">
</head>

<body>

<div>
    <div class="row text-center" style="background-color:#F8F9FA">
        <div class="col-11">
            <c:import url="../views/header.jsp"/>
        </div>
        <div class="col-1 pt-2">
            <form class="d-flex" method="get" onchange="submit()">
                <select class=" form-control select-size" id="language" name="language"
                        style="width: 120px;">
                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                </select>
                <input name="command" value="${command}" hidden>
            </form>
        </div>
    </div>
</div>


<div class="container mt-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <h1 class="mb-5">
                <f:message key="about" bundle="${bunCont}"></f:message>
            </h1>
        </div>
        <div class="col-4"></div>
    </div>
</div>

<div class="mt-1">
    <div class="row text-center">
        <%--        <div class="col-2"></div>--%>
        <div ${sessionScope.language=='en'?'':'hidden'}>
            <div class="heading1">
                DISCOVER THE&nbsp;BEST CRUISE SHIPS
            </div>
            <div class="above">
                <img width="100%" src="views\img\shipImage\above.jpg" alt="">
            </div>
            <div>
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">

                        <div class="conteiner2">
                            <div class="row">
                                <div class="col-4"><img src="views\img\shipImage\collage.jpg" alt=""></div>
                                <div class="col-8" id="">
                                    <div class="Reasons">
                                        <b>Reasons to choose our company</b>
                                    </div>

                                    <div class="cont">
                                        <div class="row">
                                            <div class="col-1"></div>
                                            <div class="col-10">
                                                <div class="reasons2">
                                                    <br><img src="views\img\shipImage\corabl.png" alt=""
                                                             class="reasons21">choosing the most interesting route;</br>
                                                    <br><img src="views\img\shipImage\corabl.png" alt=""
                                                             class="reasons21">information about attractions in ports of
                                                    call;</br>
                                                    <br><img src="views\img\shipImage\corabl.png" alt=""
                                                             class="reasons21">visa requirements;</br>
                                                    <br><img src="views\img\shipImage\corabl.png" alt=""
                                                             class="reasons21">service packages on board;</br>
                                                    <br><img src="views\img\shipImage\corabl.png" alt=""
                                                             class="reasons21">additional discounts and offers for
                                                    frequent cruisers.</br>
                                                </div>


                                                <div class="meet">
                                                    <b>Meet the biggest and boldest ships in the world</b>
                                                </div>
                                                <div class="txt2">These engineering marvels win awards every year for
                                                    cutting-edge design, first-of-its-kind attractions, world-class
                                                    restaurants and accommodations, and unforgettable experiences. From
                                                    the highest slide in the sea to culinary concepts, the world's
                                                    largest cruise ships are full of adventures guaranteed to surprise
                                                    any explorer.
                                                </div>
                                            </div>
                                            <div class="col-1"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-2">
                </div>
                <div class="row">
                    <div class="col-3"></div>
                    <div class="col-6">
                        <div class="ogl"><img src="views\img\shipImage\ракушка1.png" alt="" class="r1"><b>Cabin types on
                            our ships</b><img src="views\img\shipImage\ракушка1.png" alt="" class="r2"></div>

                        <div class="bgcol">
                            <div class="roomst">Cabins on a ship are usually divided into types or categories, some
                                cruise lines can have as many as 20 or more. But do not rush to faint: despite such a
                                stunning variety, in fact, there are only 5 main cabin classes:
                            </div>

                            <div class="roomtype">
                                <div class="container">
                                    <p><b>Single Cabin:</b> The smallest cabins (12 m<sup>2</sup>) designed for one
                                        person and with all the essentials.</p>
                                    <img width="100%" src="views\img\shipImage\single1.jpg" alt="">
                                    <p><b>Inner cabin:</b> small double cabins (18 m<sup>2</sup>) that do not have a
                                        window with a sea view (located in the inner rows of cabins);</p>
                                    <img width="100%" src="views\img\shipImage\inner.jpg" alt="">
                                    <p><b>Outside cabins:</b> a room with a window or porthole overlooking the sea
                                        (windows never open, mind you), the same size as the inside or slightly larger
                                        (18-20 m<sup>2</sup>);</p>
                                    <img width="100%" src="views\img\shipImage\outside.jpg" alt="">

                                    <p><b>Cabin with balcony:</b> as the name suggests, it is distinguished by the
                                        presence of a balcony (room from 22 m<sup>2</sup> + balcony 6 m<sup>2</sup>);
                                        because ordinary windows in the cabins do not open, the balcony gives a
                                        significant advantage in the form of the opportunity to get out into the fresh
                                        air without visiting the common deck;</p>
                                    <img width="100%" src="views\img\shipImage\wbalc.jpg" alt="">

                                    <p><b>Suite:</b> Large cabin (24 m<sup>2</sup> or more), with separate sleeping and
                                        living areas, and a significant selection of additional features and amenities
                                        (such as your own butler).</p>
                                    <img width="100%" src="views\img\shipImage\suite.jpg" alt="">
                                </div>
                            </div>
                        </div>

                        <div class="concl" style="background-color: #ECECEC; padding: 5px; margin-top: 10px;"><b>If
                            you're ready to discover more incredible adventures with Cruise Line Company, check out our
                            2022-2023 cruise bookings, all available to search right now. Dive into our deck plans and
                            become a fleet expert. Or find out why our ships Royal Diamond and Royal Emerald were chosen
                            among the best cruise ships.</b>
                        </div>
                    </div>
                    <div class="col-3"></div>

                </div>
            </div>
        </div>

        <div ${sessionScope.language=='ua'?'':'hidden'}>
            <div class="heading1">
                ВІДКРИЙТЕ ДЛЯ СЕБЕ НАЙКРАЩІ КРУЇЗНІ КОРАБЛІ
            </div>
            <div class="above">
                <img width="100%" src="views\img\shipImage\above.jpg" alt="">
            </div>
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">

                    <div class="conteiner2">
                        <div class="row">
                            <div class="col-4"><img src="views\img\shipImage\collage.jpg" alt=""></div>
                            <div class="col-8" id="col-81">
                                <div class="Reasons">
                                    <b>Причини обрати нашу компанію</b>
                                </div>

                                <div class="cont">
                                    <div class="row">
                                        <div class="col-1"></div>
                                        <div class="col-10">
                                            <div class="reasons2">
                                                <br><img src="views\img\shipImage\corabl.png" alt=""
                                                         class="reasons21">вибір найцікавішого маршруту;</br>
                                                <br><img src="views\img\shipImage\corabl.png" alt=""
                                                         class="reasons21">інформація про пам'ятки в портах
                                                заходу;</br>
                                                <br><img src="views\img\shipImage\corabl.png" alt=""
                                                         class="reasons21">візові вимоги;</br>
                                                <br><img src="views\img\shipImage\corabl.png" alt=""
                                                         class="reasons21">пакети послуг на борту;</br>
                                                <br><img src="views\img\shipImage\corabl.png" alt=""
                                                         class="reasons21">додаткові знижки та пропозиції для
                                                постійних круїзерів.</br>
                                            </div>


                                            <div class="meet">
                                                <b>Зустрічайте найбільші та найсміливіші кораблі у світі</b>
                                            </div>
                                            <div class="txt2">Ці інженерні дива щороку отримують нагороди за
                                                передовий дизайн, перші у своєму роді пам’ятки, ресторани та
                                                помешкання світового рівня та незабутні враження. Від найвищої
                                                морської гірки до кулінарних концепцій, найбільші у світі круїзні
                                                лайнери сповнені пригод, які гарантовано здивують будь-якого
                                                дослідника.
                                            </div>
                                        </div>
                                        <div class="col-1"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-2">
            </div>
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                    <div class="ogl"><img src="views\img\shipImage\ракушка1.png" alt="" class="r1"><b>Типи кают на
                        наших кораблях</b><img src="views\img\shipImage\ракушка1.png" alt="" class="r2"></div>

                    <div class="bgcol">
                        <div class="roomst">Каюти на кораблі зазвичай поділяються на типи або категорії, деякі
                            круїзні компанії можуть мати до 20 і більше. Але не поспішайте падати в непритомність:
                            незважаючи на таке приголомшливе розмаїття, насправді існує всього 5 основних класів
                            салонів:
                        </div>

                        <div class="roomtype">
                            <div class="container">
                                <p><b>Одномісна каюта:</b> Найменші каюти (12 м<sup>2</sup>) розраховані на одну
                                    людину та з усім необхідним.</p>
                                <img width="100%" src="views\img\shipImage\single1.jpg" alt="">
                                <p><b>Внутрішня каюта:</b> малі двомісні каюти (18 м<sup>2</sup>), які не мають
                                    вікна з видом на море (розташовані у внутрішніх рядах кают);</p>
                                <img width="100%" src="views\img\shipImage\inner.jpg" alt="">
                                <p><b>Зовнішні каюти:</b> кімната з вікном або ілюмінатором на море (вікна ніколи не
                                    відчиняються, зауважте), такого ж розміру, як всередині, або трохи більше (18-20
                                    м<sup>2</sup>);</p>
                                <img width="100%" src="views\img\shipImage\outside.jpg" alt="">

                                <p><b>Каюта з балконом:</b> як зрозуміло з назви, виділяється наявністю балкона
                                    (кімната від 22 м<sup>2</sup> + балкон 6 м<sup>2</sup>); оскільки звичайні вікна
                                    в каютах не відкриваються, балкон дає істотну перевагу у вигляді можливості
                                    вийти на свіже повітря, не відвідуючи загальну палубу;</p>
                                <img width="100%" src="views\img\shipImage\wbalc.jpg" alt="">

                                <p><b>Люкс:</b> велика каюта (24 м<sup>2</sup> або більше), з окремими зонами для
                                    сну та вітальні, а також значним вибором додаткових функцій та зручностей
                                    (наприклад, власний дворецький).</p>
                                <img width="100%" src="views\img\shipImage\suite.jpg" alt="">
                            </div>
                        </div>
                    </div>

                    <div class="conclusion"><b>Якщо ви
                        готові відкривати для себе ще неймовірні пригоди з Cruise Line Cimpany, ознайомтеся з нашими
                        замовленнями круїзів на 2022–2023 роки. Вони доступні для пошуку прямо зараз. <br>Зануртеся в
                        наші плани і станьте експертом у флоті. <br>Дізнайтеся, чому наші кораблі Royal
                        Diamond і Royal Emerald були обрані найкращими серед круїзних лайнерів.</b>
                        <br>***<br>
                    </div>
                </div>
                <div class="col-3"></div>

            </div>
        </div>
    </div>
</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>
