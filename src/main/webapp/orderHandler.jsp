<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Order handler page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Order Handler Page</title>
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
                <input name="paymentNumber" value="${payment.paymentNumber}" hidden>
                <input name="paymentDate" value="${payment.paymentDate}" hidden>
                <input name="firstName" value="${payment.firstName}" hidden>
                <input name="lastName" value="${payment.lastName}" hidden>
                <input name="email" value="${payment.email}" hidden>
                <input name="cruiseName" value="${payment.cruiseName}" hidden>
                <input name="routeName" value="${payment.routeName}" hidden>
                <input name="cabinNumber" value="${payment.cabinNumber}" hidden>
                <input name="cabinType" value="${payment.cabinType}" hidden>
                <input name="dateStart" value="${payment.dateStart}" hidden>
                <input name="dateEnd" value="${payment.dateEnd}" hidden>
                <input name="orderStatusName" value="${payment.orderStatusName}" hidden>
                <input name="amount" value="${payment.amount}" hidden>
                <input name="userDocument" value="${payment.userDocument}" hidden>
            </form>
        </div>
    </div>
</div>
<div>
    <div class=" row">
        <div class="col-3"></div>
        <div class="col-6">
            <h1>
                <f:message key="adminProfile" bundle="${bunCont}"/>
            </h1>
        </div>
        <div class="col-3"></div>
    </div>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <table class="table table-striped">
                <tr style="text-transform: uppercase; background-color: rgba(0,135,253,0.75);color: #F8F9FA">
                    <td><h3><f:message key="paymentNumber" bundle="${bunCont}"/> ${payment.paymentNumber}</h3></td>
                    <td><f:message key="paymentDate" bundle="${bunCont}"/> <tags:fulldate
                            date="${payment.paymentDate}"/></td>
                </tr>
                <tr>
                    <td><f:message key="customerInfo" bundle="${bunCont}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><f:message key="name" bundle="${bunCont}"/></td>
                    <td>${payment.firstName} ${payment.lastName}</td>
                </tr>
                <tr>
                    <td><f:message key="email" bundle="${bunCont}"/></td>
                    <td>${payment.email}</td>
                </tr>
                <tr style="text-transform: uppercase; background-color: rgba(0,135,253,0.75);color: #F8F9FA">
                    <td><h3><f:message key="orderInfo" bundle="${bunCont}"/></h3></td>
                    <td></td>
                </tr>
                <tr>
                    <td><f:message key="cruise" bundle="${bunCont}"/></td>
                    <td>${payment.cruiseName}</td>
                </tr>
                <tr>
                    <td><f:message key="route" bundle="${bunCont}"/></td>
                    <td>${payment.routeName}</td>
                </tr>
                <tr>
                    <td><f:message key="dateStart" bundle="${bunCont}"/></td>
                    <td><tags:fulldate date="${payment.dateStart}"/></td>
                </tr>
                <tr>
                    <td><f:message key="dateEnd" bundle="${bunCont}"/></td>
                    <td><tags:fulldate date="${payment.dateEnd}"/></td>
                </tr>
                <tr>
                    <td><f:message key="price" bundle="${bunCont}"/></td>
                    <td>${payment.amount} <f:message key="uah" bundle="${bunCont}"/></td>
                </tr>
            </table>
            <div style="display: flex; flex-direction: row; justify-content:flex-end ">
                <form action="user" method="get">
                    <button class="btn btn-outline-primary"
                            name="command" value="showAdminProfile"
                            type="submit"
                            style="width: 120px; margin-top: 20px">
                        <f:message key="back" bundle="${bunCont}"/>
                    </button>
                </form>
                <form action="user" method="post">
                    <button class="btn btn-outline-primary"
                            name="command" value="cancelPayment"
                            type="submit"
                            style="width: 120px; margin-top: 20px"
                    ${payment.orderStatusName=='canceled'?'hidden':''}>
                        <f:message key="cancel" bundle="${bunCont}"/>
                    </button>
                    <button class="btn btn-outline-primary"
                            name="command" value="confirmPayment"
                            type="submit"
                            style="width: 120px; margin-top: 20px;"
                    ${payment.orderStatusName=='confirmed'||payment.orderStatusName=='canceled'?'hidden':''}>
                        <f:message key="confirm" bundle="${bunCont}"/>
                    </button>

                    <input name="paymentId" value="${payment.paymentNumber}" hidden>
                </form>
            </div>
        </div>

        <div class="col-3">
            <div class="container">
                <h3 style="text-transform: uppercase; background-color: rgba(0,135,253,0.75);color: #F8F9FA;text-align:center">
                    <f:message key="userDocument" bundle="${bunCont}"/>
                </h3>
                <div><img width="100%" src="${payment.userDocument}" alt="NO IMG"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
