<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="View Cruise"/>

<c:import url="views/head.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Cruise</title>
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
                <input name="cruiseId" value="${cruise.id}" hidden>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <div class="column">
            <div class="row">
                <h1 style="color: darkslateblue; text-align: center">${cruise.cruiseName}</h1>
            </div>
            <div class="row">
                <div class="col-8"><img width="100%" src="${cruise.shipImageSource}" alt=""></div>
                <div class="col-4">
                    <table>
                        <tr>
                            <th class="cruiseInfoCell"><f:message key="ship"
                                                                  bundle="${bunCont}"/></th>
                            <td class="cruiseInfoCell">${cruise.shipName}</td>
                        </tr>
                        <tr>
                            <th><f:message key="route" bundle="${bunCont}"/></th>
                            <td>${cruise.routeName}</td>
                        </tr>
                        <tr>
                            <th>
                                <f:message key="dateStart" bundle="${bunCont}"/>
                            </th>
                            <td>
                                <tags:fulldate date="${cruise.dateStart}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <f:message key="dateEnd" bundle="${bunCont}"/>
                            </th>
                            <td>
                                <tags:fulldate date="${cruise.dateEnd}"/>
                            </td>
                        </tr>
                    </table>


                </div>
            </div>
        </div>
    </div>
    <div class="col-3"></div>
</div>
</body>
</html>
