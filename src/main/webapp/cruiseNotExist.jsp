<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Cruise not exist"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Cruise not exist</title>
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
                <input name="cabinTypeId" value="${cabinTypeId}" hidden>
                <input name="cabinStatusId" value="${cabinStatusId}" hidden>
                <input name="cruiseId" value="${cruiseId}" hidden>
                <input name="nights" value="${nights}" hidden>
                <input name="routeName" value="${routeName}" hidden>
                <input name="cruise" value="${cruise}" hidden>
                <input name="dateStart" value="${dateStart}" hidden>
                <input name="dateEnd" value="${dateEnd}" hidden>
                <input name="amount" value="${amount}" hidden>
                <input name="shipName" value="${shipName}" hidden>
                <input name="cabinTypeName" value="${cabinTypeName}" hidden>
                <input name="command" value="${command}" hidden>
            </form>
        </div>
    </div>
</div>
<div class="row mb-6">
    <div class="col-3"></div>
    <div class="col-6">
        <h1>
            Sorry we don't have any active cruises yet
        </h1>
    </div>
    <div class="col-3"></div>
</div>

</body>
</html>
