<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Edit data ship page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Edit data ship page</title>
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
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <h1><f:message key="editLiner" bundle="${bunCont}"/></h1>
        <hr>
        <form action="home" method="post">
            <table class="table">
                <tr>
                    <td><f:message key="linerName" bundle="${bunCont}"/></td>
                    <td>
                        <input type="text" class="form-control" value="${shipName}" name="shipName">
                    </td>
                    <td rowspan="3">
                        <img width="350px" src="${shipImage}"/>
                    </td>
                </tr>
                <tr>
                    <td><f:message key="numberOfStaff" bundle="${bunCont}"/></td>
                    <td>
                        <input type="number" class="form-control"
                               value="${numberOfStaff}"
                               name="newNumberOfStaff">
                    </td>
                </tr>
                <tr>
                    <td><f:message key="image" bundle="${bunCont}"/></td>
                    <td>
                        <input type="file" name="shipImage" class="form-control" id="inputGroupFile" required>
                    </td>
                </tr>
            </table>
            <div style="display: flex;justify-content: flex-end">
                <button class="btn btn-outline-primary"
                        name="command" value="editLinersCommand"
                        type="submit"
                        style="width: 120px">
                    <f:message key="back" bundle="${bunCont}"/>
                </button>
                <button class="btn btn-outline-primary"
                        name="command" value="confirmChangeShipDataCommand"
                        type="submit"
                        style="width: 120px">
                    <f:message key="update" bundle="${bunCont}"/>
                </button>
                <input name="id" value="${id}" hidden>
                <input name="shipName" value="${shipName}" hidden>
                <input name="passengersCapacity" value="${passengersCapacity}" hidden>
                <input name="newNumberOfStaff" value="${newNumberOfStaff}" hidden>
                <input name="shipImage" value="${shipImage}" hidden>
            </div>
        </form>
    </div>
</div>
<div class="col-3"></div>
</div>
</body>
</html>
