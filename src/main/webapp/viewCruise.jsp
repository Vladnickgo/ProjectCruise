<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="View Cruise"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>View Cruise</title>
    <link rel="stylesheet" type="text/css" href="/views/style/style.css">
</head>
<body>
<div>
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
                <input name="cruiseId" value="${cruise.id}" hidden>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="column">
                <div class="row">
                    <h1 style="color: darkslateblue; text-align: center; font-size: 100pt">${cruise.cruiseName}</h1>
                </div>
            </div>
        </div>
        <div class="col-3"></div>
    </div>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="column">
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10"><img width="100%" src="${cruise.shipImageSource}" alt=""></div>
                    <div class="col-1"></div>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="container">
                <div><h2><f:message key="cruiseInfo" bundle="${bunCont}"/></h2></div>
                <table class="table table-striped table-hover pr-5">
                    <tr>
                        <th class="linerInfoCellTh"><f:message key="ship"
                                                               bundle="${bunCont}"/></th>
                        <td class="linerInfoCellTd">${cruise.shipName}</td>
                    </tr>
                    <tr>
                        <th class="linerInfoCellTh"><f:message key="route" bundle="${bunCont}"/></th>
                        <td class="linerInfoCellTd">${cruise.routeName}</td>
                    </tr>
                    <tr>
                        <th class="linerInfoCellTh">
                            <f:message key="dateStart" bundle="${bunCont}"/>
                        </th>
                        <td class="linerInfoCellTd">
                            <tags:fulldate date="${cruise.dateStart}"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="linerInfoCellTh">
                            <f:message key="dateEnd" bundle="${bunCont}"/>
                        </th>
                        <td class="linerInfoCellTd">
                            <tags:fulldate date="${cruise.dateEnd}"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="linerInfoCellTh">
                            <f:message key="nights" bundle="${bunCont}"/>
                        </th>
                        <td class="linerInfoCellTd">
                            ${cruise.nights}
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="row">
                <h1 style="color: darkslateblue; text-align: center;margin-top: 30px">
                    <f:message key="bookTravel" bundle="${bunCont}"/>
                </h1>
                <c:forEach items="${cabinTypes}" var="item">
                    <form action="user" method="get">
                        <div class="row m-3 shadow-sm p-3 mb-5 bg-body rounded">
                            <div class="col-6">
                                <img width="250px" src="views/img/cabins/cabin.jpg"/>
                            </div>
                            <div class="col-6">
                                <table>
                                    <tr>
                                        <th style="text-align: center">
                                            <h2>
                                                <f:message key="${item.cabinTypeName}" bundle="${bunCont}"/>
                                            </h2>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th style="font-size: 42pt; color: darkslateblue">${item.price*cruise.nights}
                                            <f:message key="uah"
                                                       bundle="${bunCont}"/></th>
                                    </tr>
                                </table>
                                <div style="text-align: right; margin-top: 20px">
                                    <button class="btn btn-outline-primary"
                                            name="command" value="createCabinOrder"
                                            type="submit"
                                            style="width: 150px"
                                        ${user.role == 'USER'?'':'hidden'}>
                                        <f:message key="order" bundle="${bunCont}"/>
                                    </button>
                                    <input name="cabinTypeId" value="${item.id}" hidden>
                                    <input name="cruiseId" value="${cruise.id}" hidden>
                                    <input name="nights" value="${cruise.nights}" hidden>
                                    <input name="routeName" value="${cruise.routeName}" hidden>
                                    <input name="cruise" value="${cruise.cruiseName}" hidden>
                                    <input name="dateStart" value="${cruise.dateStart}" hidden>
                                    <input name="dateEnd" value="${cruise.dateEnd}" hidden>
                                    <input name="amount" value="${item.price*cruise.nights}" hidden>
                                    <input name="shipName" value="${cruise.shipName}" hidden>
                                    <input name="cabinTypeName" value="${item.cabinTypeName}" hidden>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </div>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>
</body>
</html>