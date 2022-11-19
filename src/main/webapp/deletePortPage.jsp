<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Delete port page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Delete port page</title>
</head>
<body>
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
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <h1>Видалення порту</h1>
        <hr>

        <div class="p-3">
            <div>
                <h3 style="text-transform:capitalize; background-color: darkslateblue; color: white;height: 60px; padding-left: 20px">
                    <f:message key="portName" bundle="${bunCont}"/>
                </h3>
            </div>
            <div class="pl-3"><h6>UA: </h6>${portNameUa}</div>
            <div class="pl-3"><h6>EN: </h6>${portNameEn}</div>
            <div>
                <h3 style="text-transform:capitalize; background-color: darkslateblue; color: white;height: 60px; padding-left: 20px">
                    <f:message key="country" bundle="${bunCont}"/>
                </h3>
            </div>
            <div class="pl-3"><h6>UA: </h6>${countryUa}</div>
            <div class="pl-3"><h6>EN: </h6>${countryEn}</div>
            <div style="display: flex; margin-top: 20px">
                <form action="home" method="get">
                    <button class="btn btn-outline-primary">
                        <f:message key="back" bundle="${bunCont}"/>
                    </button>
                    <input name="command" value="editPortsCommand" hidden>
                </form>
                <form action="home" method="post">
                    <button class="btn btn-outline-primary">
                        <f:message key="delete" bundle="${bunCont}"/>
                    </button>
                    <input name="portId" value="${portId}" hidden>
                    <input name="portNameUa" value="${portNameUa}" hidden>
                    <input name="portNameEn" value="${portNameEn}" hidden>
                    <input name="countryUa" value="${countryUa}" hidden>
                    <input name="countryEn" value="${countryEn}" hidden>
                    <input name="command" value="deletePortCommand" hidden>
                </form>
            </div>
        </div>
    </div>
    <div class="col-3"></div>
</div>

</body>
</html>
