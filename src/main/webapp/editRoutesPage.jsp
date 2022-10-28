<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Edit routes page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Edit routes page</title>
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
            </form>
        </div>
    </div>
</div>
<div>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <h1><f:message key="routes" bundle="${bunCont}"/></h1>
            <hr>
            <form action="home" method="get" onchange="submit()">
                <div class="row">
                    <div class="col-6" style="display: flex">
                        <input name="command" value="editRoutesCommand" hidden>
                        <select name="recordsOnPage" class="form-select" style="width: 170px;">
                            <option value="4" ${recordsOnPage==4?'selected':''}>4 <f:message key="perPage"
                                                                                             bundle="${bunCont}"/></option>
                            <option value="8" ${recordsOnPage==8?'selected':''}>8 <f:message key="perPage"
                                                                                             bundle="${bunCont}"/></option>
                            <option value="12" ${recordsOnPage==12?'selected':''}>12 <f:message key="perPage"
                                                                                                bundle="${bunCont}"/></option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-3"></div>
    </div>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <table class="table">
                <tr>
                    <th><f:message key="route" bundle="${bunCont}"/></th>
                    <th style="text-align: center"><f:message key="action" bundle="${bunCont}"/></th>
                </tr>
                <c:forEach items="${routeList}" var="route">
                    <tr>
                        <td>${route.routeName}</td>
                        <td style="text-align: center">
                            <form action="home" method="get">
                                <button class="btn btn-outline-primary" style="width: 120px"
                                        name="command" value="editRouteDataCommand"
                                >
                                    <f:message key="edit" bundle="${bunCont}"/>
                                </button>

                                <button class="btn btn-outline-primary" style="width: 120px">
                                    <f:message key="delete" bundle="${bunCont}"/>
                                </button>
                                <input name="routeId" value="${route.id}" hidden>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="col-7" style="display: flex">
                <form action="home" method="get">
                    <button class="btn btn-light" ${numberOfPage==1?'hidden':''}><</button>
                    <input name="command" value="editRoutesCommand" hidden>
                    <input name="numberOfPage" value="${numberOfPage-1<1?1:numberOfPage-1}" hidden>
                    <input name="recordsOnPage" value="${recordsOnPage}" hidden>
                </form>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>

                    <form action="home" method="get">
                        <button class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                            ${Math.abs(numberOfPage-i)<3||i==totalPages?'':'hidden'}>
                                ${i}
                        </button>
                        <input name="command" value="editRoutesCommand" hidden>
                        <input name="numberOfPage" value="${i}" hidden>
                        <input name="recordsOnPage" value="${recordsOnPage}" hidden>
                    </form>

                    <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                       style="text-decoration: none;">...</a>
                </c:forEach>
                <form action="home" method="get">
                    <button class="btn btn-light" ${numberOfPage==totalPages?'hidden':''}>></button>
                    <input name="command" value="editRoutesCommand" hidden>
                    <input name="numberOfPage" value="${numberOfPage+1>totalPages?totalPages:numberOfPage+1}" hidden>
                    <input name="recordsOnPage" value="${recordsOnPage}" hidden>
                </form>
            </div>
        </div>
        <div class="col-3">
            <div class="container">
                <div id="userForm">
                    <h3 style="text-align: center"><f:message key="addRoute" bundle="${bunCont}"/></h3>
                    <form action="home" method="post">
                        <div class="mb-3">
                            <label for="routeName">
                                <f:message key="routeName" bundle="${bunCont}"/>
                            </label>
                            <input class="form-control" type="text" id="routeName" name="routeName"
                                   value="${routeName}" required>
                        </div>
                        <label for="firstPortOfRoute">
                            <f:message key="firstPointOfRoute" bundle="${bunCont}"/>
                        </label>
                        <select class="form-select mb-3" id="firstPortOfRoute" name="firstPortOfRoute" required>
                            <option selected><f:message key="chooseFirstPointOfRoute" bundle="${bunCont}"/></option>
                            <c:forEach var="port" items="${portList}">
                                <option value="${port.id}">${port.portNameEn} / ${port.portNameUa}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-outline-primary"
                                type="submit"
                                style="width: 120px">
                            ok
                        </button>
                        <input name="command" value="addRouteCommand" hidden>
                    </form>
                    <div ${message=='Route created'?'':'hidden'}>
                        <h3>
                            <f:message key="routeCreated" bundle="${bunCont}"/>
                        </h3>
                    </div>
                    <div ${message=='Route name is empty'?'':'hidden'}>
                        <h3>
                            <f:message key="error.emptyRouteName" bundle="${bunCont}"/>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
