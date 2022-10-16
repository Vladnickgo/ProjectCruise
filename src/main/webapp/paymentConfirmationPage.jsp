<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Payment confirmation page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Payment confirmation page</title>
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
                <input name="cruiseId" value="${cruiseId}" hidden>
                <input name="cabinStatusId" value="${cabinStatusId}" hidden>
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
<div class="row text-center" style="">
    <div class="col-3"></div>
    <div class="col-6" style="margin-top:25px ;padding: 20px;
                                box-shadow: inset 0 0 1em rgba(0,0,0,0.1),
                                0 0  0 2px rgb(255,255,255),
                                0.3em 0.3em 1em rgba(0,0,0,0.3);">
        <h1><f:message key="paymentConfirmation" bundle="${bunCont}"/></h1>
        <hr>
        <table class="table">
            <thead>
            <tr style="color: darkslateblue">
                <th scope="col"><img width="150" src="views/img/cruise_logo.png"/></th>
                <th scope="col" style="text-align: center">
                    <h1>
                        ${cruise}
                    </h1>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row" colspan="2" style="text-transform: uppercase; background-color: rgba(0,135,253,0.75)">
                    <h4><f:message key="user" bundle="${bunCont}"/></h4>

                </th>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="name" bundle="${bunCont}"/></th>
                <td>${user.firstName} ${user.lastName}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="email" bundle="${bunCont}"/></th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th scope="row" colspan="2" style="text-transform: uppercase; background-color: rgba(0,135,253,0.75)">
                    <h4><f:message key="cruiseInfo" bundle="${bunCont}"/></h4>

                </th>
            </tr>

            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="cabinType" bundle="${bunCont}"/></th>
                <td><f:message key="${cabinTypeName}" bundle="${bunCont}"/></td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="checkIn" bundle="${bunCont}"/></th>
                <td><tags:fulldate date="${dateStart}"/></td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="checkOut" bundle="${bunCont}"/></th>
                <td><tags:fulldate date="${dateEnd}"/></td>
            </tr>

            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="nights" bundle="${bunCont}"/></th>
                <td>${nights}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="price" bundle="${bunCont}"/></th>
                <td>${amount} <f:message key="uah" bundle="${bunCont}"/></td>
            </tr>
            </tbody>
        </table>
        <div class="container" style="display: flex; flex-direction: row; justify-content:flex-end; ">
            <form action="home" method="get">
                <button class="btn btn-outline-primary"
                        name="command" value="showCruises"
                        type="submit"
                        style="width: 120px; margin-top: 20px">
                    <f:message key="cancel" bundle="${bunCont}"/>
                </button>
            </form>
            <form action="user" method="post">
                <input name="cabinStatusId" value="${cabinStatusId}" hidden>
                <input name="cruiseId" value="${cruiseId}" hidden>
                <input name="amount" value="${amount}" hidden>
                <button class="btn btn-outline-primary"
                        name="command" value="paymentCommand"
                        type="submit"
                        style="width: 120px; margin-top: 20px">
                    <f:message key="confirm" bundle="${bunCont}"/>
                </button>
            </form>
        </div>

    </div>
    <div class="col-3">
    </div>
</div>
</body>
</html>
