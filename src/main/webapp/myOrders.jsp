<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="My orders page"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>My orders page</title>
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

<div class="row mt-4">
    <div class="col-1"></div>
    <div class="col-6">
        <table class="table table-striped">
            <tr style="text-transform: uppercase; background-color: rgba(0,135,253,0.75);color: #F8F9FA">
                <td><h3><f:message key="paymentNumber" bundle="${bunCont}"/> ${paymentNumber}</h3></td>
                <td><f:message key="paymentDate" bundle="${bunCont}"/> <tags:fulldate
                        date="${paymentDate}"/></td>
            </tr>
            <tr>
                <td><f:message key="customerInfo" bundle="${bunCont}"/></td>
                <td></td>
            </tr>
            <tr>
                <td><f:message key="name" bundle="${bunCont}"/></td>
                <td>${firstName} ${lastName}</td>
            </tr>
            <tr>
                <td><f:message key="email" bundle="${bunCont}"/></td>
                <td>${email}</td>
            </tr>
            <tr style="text-transform: uppercase; background-color: rgba(0,135,253,0.75);color: #F8F9FA">
                <td><h3><f:message key="orderInfo" bundle="${bunCont}"/></h3></td>
                <td></td>
            </tr>
            <tr>
                <td><f:message key="cruise" bundle="${bunCont}"/></td>
                <td>${cruiseName}</td>
            </tr>
            <tr>
                <td><f:message key="route" bundle="${bunCont}"/></td>
                <td>${routeName}</td>
            </tr>
            <tr>
                <td><f:message key="dateStart" bundle="${bunCont}"/></td>
                <td><tags:fulldate date="${dateStart}"/></td>
            </tr>
            <tr>
                <td><f:message key="dateEnd" bundle="${bunCont}"/></td>
                <td><tags:fulldate date="${dateEnd}"/></td>
            </tr>
            <tr>
                <td><f:message key="price" bundle="${bunCont}"/></td>
                <td>${amount} <f:message key="uah" bundle="${bunCont}"/></td>
            </tr>
            <tr>
                <td><f:message key="status" bundle="${bunCont}"/></td>
                <td>${orderStatusName}</td>
            </tr>
        </table>
    </div>
    <div class="col-1"></div>
    <div class="col-3">
        <img src="${userDocument}" width="300 px"/>
    </div>
    <div class="col-1"></div>

</div>
</body>
</html>
