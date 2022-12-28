<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Edit cruises page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="views/style/style.css"/>
    <title>Edit cruises page</title>
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
    <div class="col-1"></div>
    <div class="col-8">
        <h1><f:message key="cruises" bundle="${bunCont}"/></h1>
        <hr>
        <form action="home" method="get" onchange="submit()">
            <div class="row">
                <div class="col-12" style="display: flex">
                    <div>
                        <input name="command" value="editCruisesCommand" hidden>
                        <select name="recordsOnPage" class="form-select" style="width: 170px;">
                            <option value="5" ${recordsOnPage==5?'selected':''}>5 <f:message key="perPage"
                                                                                             bundle="${bunCont}"/></option>
                            <option value="10" ${recordsOnPage==10?'selected':''}>10 <f:message key="perPage"
                                                                                                bundle="${bunCont}"/></option>
                            <option value="15" ${recordsOnPage==15?'selected':''}>15 <f:message key="perPage"
                                                                                                bundle="${bunCont}"/></option>
                        </select>
                    </div>

                    <div style="display: flex;width: 250px">
                        <label style="margin-left: 50px; width: 60px;font-size: 8pt; margin-right: 5px;font-weight: bold"><f:message
                                key="status" bundle="${bunCont}"/>:</label>
                        <div style="font-size: 9pt;margin-left: 10px;">
                            <div style="display: flex">
                                <input type="checkbox" id="available" name="statusAvailable"
                                       value="available" ${statusAvailable=='available'?'checked':''}>
                                <label style="margin-left: 5px" for="available"><f:message key="available"
                                                                                           bundle="${bunCont}"/></label><br>
                            </div>
                            <div style="display: flex">
                                <input type="checkbox" id="inProgress" name="statusInProgress"
                                       value="in progress" ${statusInProgress=='in progress'?'checked':''}>
                                <label style="margin-left: 5px" for="inProgress"><f:message key="inProgress"
                                                                                            bundle="${bunCont}"/></label><br>
                            </div>
                            <div style="display: flex">
                                <input type="checkbox" id="finished" name="statusFinished"
                                       value="finished" ${statusFinished=='finished'?'checked':''}>
                                <label style="margin-left: 5px" for="finished"><f:message key="finished"
                                                                                          bundle="${bunCont}"/></label><br>
                            </div>
                            <div style="display: flex">
                                <input type="checkbox" id="notAvailable" name="statusNotAvailable"
                                       value="not available" ${statusNotAvailable=='not available'?'checked':''}>
                                <label style="margin-left: 5px" for="notAvailable"><f:message key="notAvailable"
                                                                                              bundle="${bunCont}"/></label><br>
                            </div>

                        </div>
                    </div>

                    <label for="sorting"
                           style="width: 200px;font-size: 8pt; margin-left: 30px; margin-right: 5px;font-weight: bold">
                        <f:message key="orderItemsBy" bundle="${bunCont}"/>
                    </label>
                    <select class="select-size form-control" name="sorting" id="sorting"
                            style="width: 200px;height: 40px">
                        <option value="cruise_name" ${sorting == 'cruise_name' ? 'selected' : ''}>
                            <f:message key="cruiseName" bundle="${bunCont}"/>
                        </option>
                        <option value="date_start" ${sorting == 'date_start' ? 'selected' : ''}>
                            <f:message key="dateStart" bundle="${bunCont}"/>
                        </option>
                        <option value="date_end" ${sorting == 'date_end' ? 'selected' : ''}>
                            <f:message key="dateEnd" bundle="${bunCont}"/>
                        </option>
                    </select>

                    <select class="select-size form-control" name="ordering"
                            style="width: 70px;margin-left: 3px;height: 40px">
                        <option value="ASC" ${ordering == 'ASC' ? 'selected' : ''}>A->Z</option>
                        <option value="DESC" ${ordering == 'DESC' ? 'selected' : ''}>Z->A</option>
                    </select>

                </div>
            </div>
        </form>
        <table class="table table-striped">
            <tr class="table-primary">
                <th><f:message key="cruiseName" bundle="${bunCont}"/></th>
                <th><f:message key="route" bundle="${bunCont}"/></th>
                <th><f:message key="dateStart" bundle="${bunCont}"/></th>
                <th><f:message key="dateEnd" bundle="${bunCont}"/></th>
                <th><f:message key="nights" bundle="${bunCont}"/></th>
                <th><f:message key="status" bundle="${bunCont}"/></th>
                <th><f:message key="ship" bundle="${bunCont}"/></th>
                <th style="text-align: center"><f:message key="action" bundle="${bunCont}"/></th>
            </tr>
            <c:forEach items="${cruiseList}" var="cruise">
                <form action="home" method="post">
                    <tr>
                        <td>${cruise.cruiseName}</td>
                        <td>${cruise.routeName}</td>
                        <td><tags:fulldate date="${cruise.dateStart}"/></td>
                        <td><tags:fulldate date="${cruise.dateEnd}"/></td>
                        <td>${cruise.nights}</td>
                        <td>${cruise.cruiseStatusName}</td>
                        <td>${cruise.shipName}</td>
                        <td style="text-align: center">
                            <button class="btn btn-outline-primary" style="width: 120px"
                                    name="command" value="blockCruiseCommand"
                                    type="submit"
                                ${cruise.cruiseStatusName=='available'?'':'hidden'}
                            >
                                <f:message key="block" bundle="${bunCont}"/>
                            </button>
                            <button class="btn btn-outline-primary" style="width: 120px"
                                    name="command" value="unblockCruiseCommand"
                                    type="submit"
                                ${cruise.cruiseStatusName=='not available'?'':'hidden'}
                            >
                                <f:message key="unLock" bundle="${bunCont}"/>
                            </button>
                            <input name="cruiseId" value="${cruise.id}" hidden>
                            <input name="id" value="${ship.id}" hidden>
                            <input name="shipName" value="${ship.shipName}" hidden>
                            <input name="passengersCapacity" value="${ship.passengersCapacity}" hidden>
                            <input name="numberOfStaff" value="${ship.numberOfStaff}" hidden>
                            <input name="shipImage" value="${ship.shipImage}" hidden>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
        <div class="col-7" style="display: flex">
            <form action="home" method="get">
                <button class="btn btn-light" ${numberOfPage==1?'hidden':''}><</button>
                <input name="command" value="editCruisesCommand" hidden>
                <input name="numberOfPage" value="${numberOfPage-1<1?1:numberOfPage-1}" hidden>
                <input name="recordsOnPage" value="${recordsOnPage}" hidden>
            </form>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>

                <form action="home" method="get">
                    <button class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                        ${Math.abs(numberOfPage-i)<3||i==totalPages?'':'hidden'} command="editLinersCommand">
                            ${i}
                    </button>
                    <input name="command" value="editCruisesCommand" hidden>
                    <input name="numberOfPage" value="${i}" hidden>
                    <input name="recordsOnPage" value="${recordsOnPage}" hidden>
                </form>

                <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                   style="text-decoration: none;">...</a>
            </c:forEach>
            <form action="home" method="get">
                <button class="btn btn-light" ${numberOfPage==totalPages?'hidden':''}>></button>
                <input name="command" value="editCruisesCommand" hidden>
                <input name="numberOfPage" value="${numberOfPage+1>totalPages?totalPages:numberOfPage+1}" hidden>
                <input name="recordsOnPage" value="${recordsOnPage}" hidden>
            </form>
        </div>
    </div>

    <div class="col-3">
        <div class="container mt-5">
            <div id="userForm" class="p-3">
                <h3 style="text-align: center"><f:message key="addCruise" bundle="${bunCont}"/></h3>
                <form class="form-floating" action="home" method="get" onchange="submit()">
                    <div class="form-floating mb-3">
                        <input class="form-control" type="text" id="cruiseName" name="cruiseName" value="${cruiseName}"
                               required>
                        <label for="start"><f:message key="cruiseName" bundle="${bunCont}"/> </label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" type="date" id="start" name="dateStart" value="${dateStart}"
                               min="${minDateStart}" max="${maxDateStart}">
                        <label for="start"><f:message key="dateStart" bundle="${bunCont}"/> </label>
                    </div>

                    <div class="form-floating mb-3">
                        <select class="form-control" id="route" name="routeId" required>
                            <option selected disabled value="">Choose...</option>
                            <c:forEach var="route" items="${routeList}">
                                <option name="routeId" value="${route.id}"
                                    ${route.id==routeId?'selected':''}
                                >${route.routeName}</option>
                            </c:forEach>
                        </select>
                        <label for="route">
                            <f:message key="route" bundle="${bunCont}"/>
                        </label>
                    </div>
                    <div ${routePointList.size()>1?'':'hidden'} class="col-md-12 mb-3">
                        <f:message key="numberOfDays" bundle="${bunCont}"/>:
                        ${routePointList.size()}
                    </div>
                    <div ${routePointList.size()>1?'':'hidden'} class="col-md-12 mb-3">
                        <f:message key="dateEnd" bundle="${bunCont}"/>:
                        ${dateEnd}
                    </div>
                    <div ${routePointList.size()>1?'':'hidden'} class="form-floating">

                        <select class="form-control" id="ships" name="shipId" required>
                            <option selected disabled value="">Choose...</option>
                            <c:forEach var="ship" items="${shipList}">
                                <option value="${ship.id}"
                                    ${ship.id==shipId?'selected':''}
                                >${ship.shipName}</option>
                            </c:forEach>
                        </select>
                        <label for="ships">
                            <f:message key="linerName" bundle="${bunCont}"/>
                        </label>
                    </div>
                    <input name="command" value="editCruisesCommand" hidden>
<%--                    <input name="routeId" value="${routeId}" hidden>--%>
                </form>
                <form>
                    <button class="btn btn-outline-primary"
                            type="submit"
                            style="width: 120px">
                        ok
                    </button>
                    <input name="command" value="addCruiseCommand" hidden>
                    <input name="shipId" value="${shipId}" hidden>
                    <input name="dateStart" value="${dateStart}" hidden>
                    <input name="dateEnd" value="${dateEnd}" hidden>
                    <input name="routeId" value="${routeId}" hidden>
                    <input name="nights" value="${routePointList.size()-1}" hidden>
                    <input name="cruiseName" value="${cruiseName}" hidden>
                </form>
                ${message}
            </div>
        </div>

    </div>
</div>
</body>
</html>
