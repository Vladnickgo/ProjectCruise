<%--
  Created by IntelliJ IDEA.
  User: Computer
  Date: 08.10.2022
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--123--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="AboutUs"/>

<c:import url="../views/head.jsp"/>

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
            <div><img width="100%"
                      src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTC9fHZqkJqHaX6Shg1pPm6QandUH-X7eD6g&usqp=CAU"
                      alt=""></div>
            <p class="h1">DECIDE WHAT TYPE OF CABIN YOU WOULD LIKE</p>
            <div class="ip">
            </div>

            <p class="h3">industry:</p>

            <div class="ip">

                Inside cabins:
                These cabins are usually the lowest price. Typically they will have twin beds that convert to a queen size, a private bath with a shower, a closet for hanging clothes, a dresser and a television. These cabins do not have a window to let natural light in. To help give the occupants an impression of having a view, some inside cabins have a virtual view. That means there is a large screen that displays outside views. Expect to pay $100 to $200 a night for an inside cabin.

                Exception: Carnival has several cabins which are classified as inside cabins but actually have a french glass door which allows light into the room (no balcony, but the door can be opened). Carnival also has some cabins that have a window, but because the window has an obstructed view (that means there is a railing or object in the way) it is listed as an inside cabin. Royal Caribbean has some cabins that have a window but look out over an inside promenade area. These are called "promenade staterooms".

                Oceanview cabins:
                These cabins are like inside cabins but usually have a port hole or window. A window is much more desirable than a porthole because the porthole can be difficult to look through. Windows will vary in size and some cabins will even have full floor to ceiling windows. Most window sizes for oceanview cabins are about 4 feet by 3 feet.  Be sure to read the cabin description carefully to determine if there is a different type of window. Windows can not be opened.  Expect to pay $150 to $250 a night for an oceanview.

                Balcony cabins:
                These cabins are better, and more expensive, than oceanview cabins because they typically have full glass sliding doors that lead to a balcony. The balcony allows fresh air and light into the cabin and also may have additional chairs or loungers. Balconies tend to be separated by dividers which give some sense of privacy, but be aware that most balconies are not fully private and can be viewed from above or by the cabin next to you.  Balconies on new Norwegian Cruise Line ships and Princess Cruise Line ships tend to be smaller and more narrow than balconies on other ships.  We will list balcony size in the cabin description.  Carnival offers what is called a "Cove Balcony" on some of their ships.  These balconies are very popular because they sit lower on the ship (closer to the water line).  Expect to pay $200 to $400 a night for an balcony cabin.

                Suite:
                A suite is a premium cabin on a cruise ship. It is usually much larger than a balcony and includes more perks. Suites typically have a full bath tub, large shower, larger closets, larger balcony, larger TV, and butler services. Expect to pay $400 to over $1,000 a night for a suite.


                <p>
                    <img src="https://setupmyhotel.com/images/Room-Type-Single-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                         alt=""></p>
            </div>


        </div>
        <div class="col-2"></div>
    </div>
</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>
