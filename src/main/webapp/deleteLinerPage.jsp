<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Delete ship page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Delete ship page</title>
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
                <input name="sorting" value="${sorting}" hidden>
                <input name="numberOfPage" value="${numberOfPage}" hidden>
                <input name="ordering" value="${ordering}" hidden>
                <input name="statusAvailable" value="${statusAvailable}" hidden>
                <input name="statusInProgress" value="${statusInProgress}" hidden>
                <input name="statusFinished" value="${statusFinished}" hidden>
                <input name="statusNotAvailable" value="${statusNotAvailable}" hidden>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <h1><f:message key="linerRemoving" bundle="${bunCont}"/></h1>
        <hr>
    </div>
    <div class="col-3"></div>
</div>
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">

        <table class="table">
            <tr>
                <td rowspan="3"><img width="100%" src="${shipImage}"/></td>
                <th><f:message key="linerName" bundle="${bunCont}"/></th>
                <td>${shipName}</td>
            </tr>
            <tr>
                <th><f:message key="passengerCapacity" bundle="${bunCont}"/></th>
                <td>${passengersCapacity}</td>
            </tr>
            <tr>
                <th><f:message key="numberOfStaff" bundle="${bunCont}"/></th>
                <td>${numberOfStaff}</td>
            </tr>
        </table>
        <h3><f:message key="confirmRemoving" bundle="${bunCont}"/></h3>
        <div style="display: flex">
            <form action="home" method="get">
                <button class="btn btn-outline-primary"
                        style="width: 150px"
                        name="command" value="editLinersCommand">
                    <f:message key="back" bundle="${bunCont}"/>
                </button>
            </form>
            <form action="home" method="post">
                <button class="btn btn-outline-primary"
                        style="width: 150px"
                        name="command" value="deleteLinerCommand">
                    <f:message key="confirm" bundle="${bunCont}"/>
                </button>
                <input name="id" value="${id}" hidden>
            </form>
        </div>

    </div>
    <div class="col-3"></div>
</div>

</body>
</html>
