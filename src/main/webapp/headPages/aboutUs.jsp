<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="AboutUs"/>

<c:import url="../views/head.jsp"/>
<head>
    <link rel="stylesheet" type="text/css" href="/views/style/styleAbout.css.css">
</head>

<body>

<div class="">
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
<div class="container mt-1">
    <div class="row text-center">
        <div class="col-2"></div>
        <div ${sessionScope.language=='en'?'':'hidden'} class="col-8">
            <div class="heading1">
                DISCOVER THE&nbsp;BEST CRUISE SHIPS
            </div>
            <div class="above">
                <img src="view\img\shipImage\above.jpg" alt="">
            </div>
            <div class="conteiner">
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">

                        <div class="conteiner2">
                            <div class="row">
                                <div class="col-4"><img src="view\img\shipImage\collage.jpg" alt=""></div>
                                <div class="col-8" id="col-81">
                                    <div class="Reasons">
                                        <b>Reasons to choose our company</b>
                                    </div>

                                    <div class="cont">
                                        <div class="row">
                                            <div class="col-1"></div>
                                            <div class="col-10">
                                                <div class="reasons2">
                                                    <br><img src="view\img\shipImage\corabl.png" alt="" class="reasons21">choosing the most interesting route;</br>
                                                    <br><img src="view\img\shipImage\corabl.png" alt="" class="reasons21">information about attractions in ports of call;</br>
                                                    <br><img src="view\img\shipImage\corabl.png" alt="" class="reasons21">visa requirements;</br>
                                                    <br><img src="view\img\shipImage\corabl.png" alt="" class="reasons21">service packages on board;</br>
                                                    <br><img src="view\img\shipImage\corabl.png" alt="" class="reasons21">additional discounts and offers for frequent cruisers.</br>
                                                </div>


                                                <div class="meet">
                                                    <b>Meet the biggest and boldest ships in the world</b>
                                                </div>
                                                <div class="txt2">These engineering marvels win awards every year for cutting-edge design, first-of-its-kind attractions, world-class restaurants and accommodations, and unforgettable experiences. From the highest slide in the sea to culinary concepts, the world's largest cruise ships are full of adventures guaranteed to surprise any explorer.</div>
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
                        <div class="ogl"><img src="view\img\shipImage\ракушка1.png" alt="" class="r1"><b>Сabin types on our ships</b><img src="view\img\shipImage\ракушка1.png" alt="" class="r2"></div>

                        <div class="bgcol">
                            <div class="roomst">Cabins on a ship are usually divided into types or categories, some cruise lines can have as many as 20 or more. But do not rush to faint: despite such a stunning variety, in fact, there are only 5 main cabin classes:
                            </div>

                            <div class="roomtype">
                                <div class="container">
                                    <p><b>Single Cabin:</b> The smallest cabins (12 m^2) designed for one person and with all the essentials.</p>
                                    <img src="view\img\shipImage\single1.jpg" alt="" class="ntkw">
                                    <p><b>Inner cabin:</b> small double cabins (18 m^2) that do not have a window with a sea view (located in the inner rows of cabins);</p>
                                    <img src="view\img\shipImage\inner.jpg" alt="" class="ones">
                                    <p><b>Outside cabins:</b> a room with a window or porthole overlooking the sea (windows never open, mind you), the same size as the inside or slightly 		larger (18-20 m^2);</p>
                                    <img src="view\img\shipImage\outside.jpg" alt="" class="ones">

                                    <p><b>Cabin with balcony:</b> as the name suggests, it is distinguished by the presence of a balcony (room from 22m^2 + balcony 6 m^2); because ordinary 		windows in the cabins do not open, the balcony gives a significant advantage in the form of the opportunity to get out into the fresh air without 		visiting the common deck;</p>
                                    <img src="view\img\shipImage\wbalc.jpg" alt="" class="ones">

                                    <p><b>Suite:</b> Large cabin (24m^2 or more), with separate sleeping and living areas, and a significant selection of additional features and amenities (		such as your own butler).</p>
                                    <img src="view\img\shipImage\suite.jpg" alt="" class="ones">
                                </div>
                            </div>
                        </div>

                        <div class="concl" style="background-color: #ECECEC; padding: 5px; margin-top: 10px;"><b>If you're ready to discover more incredible adventures with Cruise Line Cimpany, check out our 2022-2023 cruise bookings, all available to search right now. Dive into our deck plans and become a fleet expert. Or find out why our ships Royal Diamond and Royal Emerald were chosen among the best cruise ships.</b>
                        </div>
                    </div>
                    <div class="col-3"></div>

                </div>
            </div>



        </div>
        <div ${sessionScope.language=='ua'?'':'hidden'} class="col-8">

        </div>
        <div class="col-2"></div>
    </div>
</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>
