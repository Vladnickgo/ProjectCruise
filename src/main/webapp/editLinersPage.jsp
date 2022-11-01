<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Edit liners page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Edit liners Page</title>
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
            </form>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <h1><f:message key="liners" bundle="${bunCont}"/></h1>
        <hr>
        <form action="home" method="get" onchange="submit()">
            <div class="row">
                <div class="col-6" style="display: flex">
                    <input name="command" value="editLinersCommand" hidden>
                    <%--                <input name="numberOfPage" value="${numberOfPage==null?1:numberOfPage}" hidden>--%>
                    <select name="recordsOnPage" class="form-select" style="width: 150px;">
                        <option value="4" ${recordsOnPage==4?'selected':''}>4 <f:message key="perPage"
                                                                                         bundle="${bunCont}"/></option>
                        <option value="8" ${recordsOnPage==8?'selected':''}>8 <f:message key="perPage"
                                                                                         bundle="${bunCont}"/></option>
                        <option value="12" ${recordsOnPage==12?'selected':''}>12 <f:message key="perPage"
                                                                                            bundle="${bunCont}"/></option>
                    </select>
                    <label for="sorting"
                           style="width: 200px;font-size: 8pt; margin-left: 30px; margin-right: 5px;font-weight: bold">
                        <f:message key="orderItemsBy" bundle="${bunCont}"/>
                    </label>
                    <select class="select-size form-control" name="sorting" id="sorting"
                            style="width: 200px;height: 40px">
                        <option value="ship_name" ${sorting == 'ship_name' ? 'selected' : ''}>
                            <f:message key="linerName" bundle="${bunCont}"/>
                        </option>
                        <option value="passengers" ${sorting == 'passengers' ? 'selected' : ''}>
                            <f:message key="passengerCapacity" bundle="${bunCont}"/>
                        </option>
                        <option value="number_of_staff" ${sorting == 'number_of_staff' ? 'selected' : ''}>
                            <f:message key="numberOfStaff" bundle="${bunCont}"/>
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
                <th><f:message key="linerName" bundle="${bunCont}"/></th>
                <th><f:message key="passengerCapacity" bundle="${bunCont}"/></th>
                <th><f:message key="numberOfStaff" bundle="${bunCont}"/></th>
                <th style="text-align: center"><f:message key="action" bundle="${bunCont}"/></th>
            </tr>
            <c:forEach items="${shipList}" var="ship">
                <form action="home" method="get">
                    <tr>
                        <td>${ship.shipName}</td>
                        <td>${ship.passengersCapacity}</td>
                        <td>${ship.numberOfStaff}</td>
                        <td style="text-align: center">
                            <button class="btn btn-outline-primary"
                                    name="command" value="editShipDataCommand"
                                    type="submit"
                                    style="width: 120px">
                                <f:message key="edit" bundle="${bunCont}"/>
                            </button>
                            <button class="btn btn-outline-primary"
                                    name="command" value="deleteShipPageCommand"
                                    type="submit"
                                    style="width: 120px">
                                <f:message key="delete" bundle="${bunCont}"/>
                            </button>
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
                <button class="btn btn-light" command="editLinersCommand" ${numberOfPage==1?'hidden':''}><</button>
                <input name="command" value="editLinersCommand" hidden>
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
                    <input name="command" value="editLinersCommand" hidden>
                    <input name="numberOfPage" value="${i}" hidden>
                    <input name="recordsOnPage" value="${recordsOnPage}" hidden>
                </form>

                <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                   style="text-decoration: none;">...</a>
            </c:forEach>
            <form action="home" method="get">
                <button class="btn btn-light" ${numberOfPage==totalPages?'hidden':''}>></button>
                <input name="command" value="editLinersCommand" hidden>
                <input name="numberOfPage" value="${numberOfPage+1>totalPages?totalPages:numberOfPage+1}" hidden>
                <input name="recordsOnPage" value="${recordsOnPage}" hidden>
            </form>
        </div>
    </div>
    <div class="col-3">
        <div class="container">
            <div id="userForm">
                <h3 style="text-align: center"><f:message key="addLiner" bundle="${bunCont}"/></h3>
                <form class="form-floating" action="home" method="post">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="linerName" placeholder="linerName" name="shipName">
                        <label for="linerName"><f:message key="linerName" bundle="${bunCont}"/> </label>
                    </div>
                    <div class="form-floating">
                        <input type="number" class="form-control" id="numberOfStaff" placeholder=" "
                               name="numberOfStaff">
                        <label for="numberOfStaff"><f:message key="numberOfStaff" bundle="${bunCont}"/></label>
                    </div>
                    <div class="form-floating input-group mb-3">
                        <label class="input-group-text" for="inputGroupFile">Upload</label>
                        <input type="file" name="shipImage" class="form-control" id="inputGroupFile">
                    </div>
                    <table class="table">
                        <tr>
                            <th><f:message key="cabinType" bundle="${bunCont}"/></th>
                            <th><f:message key="numberOfCabins" bundle="${bunCont}"/></th>
                        </tr>

                        <c:forEach var="cabinType" items="${allCabinTypes}">
                            <tr>
                                <td>${cabinType.cabinTypeName}</td>
                                <td><input class="form-control" type="number" style="width: 150px"
                                           name="numberOfCabin" min="0" value="0">
                                    <input name="persons" value="${cabinType.numberOfBeds}" hidden>
                                    <input name="cabinTypeName" value="${cabinType.cabinTypeName}" hidden>
                                    <input name="cabinTypeIds" value="${cabinType.id}" hidden>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button class="btn btn-outline-primary"
                            name="command" value="addShipCommand"
                            type="submit"
                            style="width: 120px">
                        ok
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
