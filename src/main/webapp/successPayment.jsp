<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Success payment"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Success payment</title>
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
        <div>
            <h3><f:message key="paymentReceipt" bundle="${bunCont}"/></h3>
            <table class="table table-striped">
                <tr>
                    <th><f:message key="orderDate" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.paymentDate}</td>
                </tr>
                <tr style="background-color: lightsteelblue">
                    <th colspan="2"><f:message key="customerInfo" bundle="${bunCont}"/></th>
                </tr>
                <tr>
                    <th><f:message key="name" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.firstName} ${sessionScope.payment.lastName} </td>
                </tr>
                <tr>
                    <th><f:message key="email" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.email} </td>
                </tr>
                <tr style="background-color: lightsteelblue">
                    <th><f:message key="receipt" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.paymentNumber} </td>
                </tr>
                <tr>
                    <th><f:message key="cruise" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.cruiseName}</td>
                </tr>
                <tr>
                    <th><f:message key="dateStart" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.dateStart}</td>
                </tr>
                <tr>
                    <th><f:message key="dateEnd" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.dateEnd}</td>
                </tr>
                <tr>
                    <th><f:message key="cabinType" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.cabinType}</td>
                </tr>
                <tr>
                    <th><f:message key="cabin" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.cabinNumber}</td>
                </tr>
                <tr>
                    <th><f:message key="price" bundle="${bunCont}"/></th>
                    <td>${sessionScope.payment.amount} <f:message key="uah" bundle="${bunCont}"/></td>
                </tr>
            </table>
            <form action="user" method="get">
                <button class="btn btn-outline-primary"
                        name="command" value="showUserProfile"
                        type="submit"
                        style="width: 120px; margin-top: 20px">
                    Ok
                </button>
            </form>
        </div>
    </div>
    <div class="col-3"></div>
</div>
</div>
</body>
</html>
