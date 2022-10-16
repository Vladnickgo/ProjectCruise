<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Show Cruises"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Show Cruises</title>
    <link rel="stylesheet" type="text/css" href="/views/style/style.css">
</head>
<body>
<div class="">
    <div class="row text-center" style="background-color:#F8F9FA">
        <div class="col-11">
            <c:import url="views/header.jsp"/>
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
<div>
    <div class="row container-fluid">
        <div class="col-2" style="">
        </div>
        <div class="col-8">
            <h1 class="text-center"><f:message key="ourCruises" bundle="${bunCont}"/></h1>
            <hr>

            <form action="home" method="get" onchange="submit()">
                <div class="row">
                    <div class="col-5">
                        <input name="command" value="showCruises" hidden>
                        <%--                <input name="numberOfPage" value="${numberOfPage==null?1:numberOfPage}" hidden>--%>
                        <select name="recordsOnPage">
                            <option value="4" ${recordsOnPage==4?'selected':''}>4 <f:message key="perPage"
                                                                                             bundle="${bunCont}"/></option>
                            <option value="8" ${recordsOnPage==8?'selected':''}>8 <f:message key="perPage"
                                                                                             bundle="${bunCont}"/></option>
                            <option value="12" ${recordsOnPage==12?'selected':''}>12 <f:message key="perPage"
                                                                                                bundle="${bunCont}"/></option>
                        </select>
                    </div>
                    <div class="col-7">
                        <a class="btn btn-light"
                           href="home?command=showCruises&numberOfPage=${numberOfPage-1<1?1:numberOfPage-1}&recordsOnPage=${recordsOnPage}" ${numberOfPage==1?'hidden':''}><</a>
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>
                            <a class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                               href="home?command=showCruises&numberOfPage=${i}&recordsOnPage=${recordsOnPage}" ${Math.abs(numberOfPage-i)<3||i==totalPages?'':'hidden'}>${i}</a>
                            <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                               style="text-decoration: none;">...</a>
                        </c:forEach>
                        <a class="btn btn-light"
                           href="home?command=showCruises&numberOfPage=${numberOfPage+1>totalPages?totalPages:numberOfPage+1}&recordsOnPage=${recordsOnPage}"
                        ${numberOfPage==totalPages?'hidden':''}>></a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row text-center">
        <div class="col-3"></div>
        <div class="col-6">
            <c:set var="salary" value="0"/>
            <table>
                <c:forEach items="${listOfCruises}" var="item">
                    <c:set var="row" value="${row=(row+1)%2}"/>
                    <c:if test="${row==1}">
                        <tr style="margin: 50px;padding: 30px;">
                        <td>
                        </td>
                        <td style="font-size: 14pt; width: 350px;">
                            <form action="home" method="get" style="margin: auto;">
                                <button class="btn btn-outline-secondary" name="command" value="viewCruiseCommand"
                                        type="submit"
                                        style="width: 500px;height: max-content">
                                    <div class="column">
                                        <div><h4 class="primary"><c:out value="${item.cruiseName}"/></h4></div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-5">
                                                <img width="200x" src="${item.shipImageSource}"/>
                                            </div>
                                            <div class="col-7">
                                                <table>
                                                    <tr>
                                                        <th><f:message key="ship"
                                                                                              bundle="${bunCont}"/></th>
                                                        <td class="cruiseInfoCell">${item.shipName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th><f:message key="route" bundle="${bunCont}"/></th>
                                                        <td class="cruiseInfoCell">${item.routeName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            <f:message key="dateStart" bundle="${bunCont}"/>
                                                        </th>
                                                        <td class="cruiseInfoCell">
                                                            <tags:fulldate date="${item.dateStart}"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            <f:message key="dateEnd" bundle="${bunCont}"/>
                                                        </th>
                                                        <td class="cruiseInfoCell">
                                                                <tags:fulldate date="${item.dateEnd}"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </button>
                                <input name="cruiseId" value="${item.id}" hidden>
                                <input name="cruiseName" value="${item.cruiseName}" hidden>
                            </form>
                        </td>
                        <td></td>
                    </c:if>
                    <c:if test="${row==0}">
                        <td><i class='fas fa-hotel' style='font-size:48px;color:lightskyblue'></i></td>
                        <td style="font-size: 14pt; width: 350px;">
                            <form action="home" method="get" style="margin: auto;">
                                <button class="btn btn-outline-secondary" name="command" value="viewCruiseCommand"
                                        type="submit"
                                        style="width: 500px;height: max-content">
                                    <div class="column">
                                        <div><h4><c:out value="${item.cruiseName}"/></h4></div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-5">
                                                <img width="200px" src="${item.shipImageSource}"/>
                                            </div>
                                            <div class="col-7">
                                                <table>
                                                    <tr>
                                                        <th><f:message key="ship" bundle="${bunCont}"/></th>
                                                        <td class="cruiseInfoCell">${item.shipName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th><f:message key="route" bundle="${bunCont}"/></th>
                                                        <td class="cruiseInfoCell">${item.routeName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            <f:message key="dateStart" bundle="${bunCont}"/>
                                                        </th>
                                                        <td class="cruiseInfoCell">
                                                                <tags:fulldate date="${item.dateStart}"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            <f:message key="dateEnd" bundle="${bunCont}"/>
                                                        </th>
                                                        <td class="cruiseInfoCell">
                                                                <tags:fulldate date="${item.dateEnd}"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </button>
                                <input name="cruiseId" value="${item.id}" hidden>
                                <input name="cruiseName" value="${item.cruiseName}" hidden>
                            </form>
                        </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <div class="col-3"></div>
    </div>
</div>
</body>
</html>
