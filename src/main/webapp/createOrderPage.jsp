<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Create Order"/>

<c:import url="views/head.jsp"/>

<html>
<head>
    <title>Create Order</title>
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
<div class="row text-center" style="">
    <div class="col-3"></div>
    <div class="col-6" style="margin-top:25px ;padding: 20px;
                                box-shadow: inset 0 0 1em rgba(0,0,0,0.1),
                                0 0  0 2px rgb(255,255,255),
                                0.3em 0.3em 1em rgba(0,0,0,0.3);">
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

        <div style="text-align: right" ${message!='PDF created'?'hidden':''}>
            <h5>
                <f:message key="documentCreated" bundle="${bunCont}"/>
            </h5>
        </div>
    </div>
    <div class="col-3">
        <div class="m-4">
            <h3><f:message key="ordering" bundle="${bunCont}"/></h3>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
            <form action="user" method="get">
                <table>
                    <tr style="height: 30px">
                        <th colspan="">
                            <f:message key="uploadScan" bundle="${bunCont}"/>
                        </th>
                    </tr>
                    <tr style="height: 35px">
                        <td>
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputGroupFile01">Upload</label>
                                <input type="file" name="idCard" class="form-control" id="inputGroupFile01">
                            </div>
                        </td>
                    </tr>
                    <tr style="height: 35px">
                        <th><f:message key="cardNumber" bundle="${bunCont}"/></th>
                    </tr>
                    <tr style="height: 35px">
                        <td><input type="text" name="cardNumber" class="form-control"/></td>
                    </tr>
                    <tr style="height: 35px">
                        <th>CVV</th>
                    </tr>
                    <tr style="height: 35px">
                        <td><input type="text" name="cvvCode" class="form-control"/></td>
                    </tr>
                    <tr>
                        <input name="cabinTypeId" value="${cabinTypeId}" hidden>
                        <input name="cruiseId" value="${cruiseId}" hidden>
                        <input name="nights" value="${nights}" hidden>
                        <input name="routeName" value="${routeName}" hidden>
                        <input name="cruise" value="${cruise}" hidden>
                        <input name="dateStart" value="${dateStart}" hidden>
                        <input name="dateEnd" value="${dateEnd}" hidden>
                        <input name="amount" value="${amount}" hidden>
                        <input name="shipName" value="${shipName}" hidden>
                        <input name="cabinTypeName" value="${cabinTypeName}" hidden>
                        <input name="cabinStatusId" value="${cabinStatusId}" hidden>
                        ${cabinStatusId}
                    </tr>
                </table>
                <button class="btn btn-outline-primary"
                        name="command" value="paymentConfirmationPageCommand"
                        type="submit"
                        style="width: 120px; margin-top: 20px">
                    <f:message key="toPay" bundle="${bunCont}"/>
                </button>
            </form>
        </div>
        <div ${cardErrorMessage=='idCard file name is not valid'?'':'hidden'}>
            <h2><f:message key="error.fileNameIsNotValid" bundle="${bunCont}"/></h2>
        </div>
        <div ${cardErrorMessage=='Card number is not valid'?'':'hidden'}>
            <h2><f:message key="error.cardNumberIsNotValid" bundle="${bunCont}"/></h2>
        </div>
        <div ${cardErrorMessage=='CVV number is not valid'?'':'hidden'}>
            <h2><f:message key="error.cvvIsNotValid" bundle="${bunCont}"/></h2>
        </div>
    </div>
</div>
</body>
</html>
