<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Edit route data page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Edit route date page</title>
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
                <input name="recordsOnPage" value="${recordsOnPage}" hidden>
                <input name="numberOfPage" value="${numberOfPage}" hidden>
                <input name="routeId" value="${routeId}" hidden>
            </form>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-1"></div>
    <div class="col-8">
        <h1><f:message key="route" bundle="${bunCont}"/></h1>
        <hr>
        <div style="text-transform:capitalize; background-color: darkslateblue; color: white;height: 60px; padding-left: 20px">
            <h2>
                ${routePointDtoList.get(0).routeName}
            </h2>
        </div>
        <hr>
        <table class="table striped-table">
            <tr>
                <th rowspan="2"><f:message key="dayNumber" bundle="${bunCont}"/></th>
                <th colspan="2"><f:message key="portName" bundle="${bunCont}"/></th>
                <th colspan="2"><f:message key="country" bundle="${bunCont}"/></th>
            </tr>
            <tr>

                <th>ua</th>
                <th>en</th>
                <th>ua</th>
                <th>en</th>
            </tr>
            <c:forEach var="routePoint" items="${routePointDtoList}">
                <tr>
                    <td>${routePoint.dayNumber}</td>
                    <td>${routePoint.portNameUa}</td>
                    <td>${routePoint.portNameEn}</td>
                    <td>${routePoint.countryUa}</td>
                    <td>${routePoint.countryEn}</td>
                </tr>
            </c:forEach>
        </table>
        <form action="home" method="post">
            <button class="btn btn-outline-primary" ${routePointDtoList.size()>1?'':'hidden'}>
                <f:message key="deleteLastRecord" bundle="${bunCont}"/>
            </button>
            <input name="command" value="deleteRoutePointCommand" hidden>
            <input name="routeId" value="${routeId}" hidden>
            <input name="routePointId" value="${routePointDtoList.get(routePointDtoList.size()-1).id}" hidden>
        </form>
    </div>
    <div class="col-3">
        <div class="container">
            <div id="userForm">
                <h2 class="mb-4"><f:message key="addRoutePoint" bundle="${bunCont}"/></h2>
                <form action="home" method="post">
                    <table class="table">
                        <tr>
                            <th><f:message key="dayNumber" bundle="${bunCont}"/></th>
                            <td>${routePointDtoList.size()+1}</td>
                        </tr>
                    </table>
                    <select class="form-select mb-3" id="pointOfRoute" name="portId" required>
                        <option selected><f:message key="chooseRoutePoint" bundle="${bunCont}"/></option>
                        <c:forEach var="port" items="${portList}">
                            <option value="${port.id}">${port.portNameEn} / ${port.portNameUa}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-outline-primary">
                        <f:message key="add" bundle="${bunCont}"/>
                    </button>
                    <input name="portId" value="${portId}" hidden>
                    <input name="routeId" value="${routeId}" hidden>
                    <input name="dayNumber" value="${routePointDtoList.size()+1}" hidden>
                    <input name="command" value="addRoutePointCommand" hidden>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
