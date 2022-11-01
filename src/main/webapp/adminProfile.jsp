<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Admin profile"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Admin profile</title>
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

                <input name="sorting" value="${sorting}" hidden>
                <input name="ordering" value="${ordering}" hidden>
                <input name="itemsOnPage" value="${itemsOnPage}" hidden>
                <input name="numberOfPage" value="${numberOfPage}" hidden>

                <input name="statusInProgress" value="${statusInProgress}" hidden>
                <input name="statusConfirmed" value="${statusConfirmed}" hidden>
                <input name="statusCanceled" value="${statusCanceled}" hidden>
                <input name="statusCompleted" value="${statusCompleted}" hidden>
            </form>
        </div>
    </div>
</div>


<%--<div>--%>
<%--    <div class="row container-fluid">--%>
<%--        <div class="col-2" style="">--%>

<%--        </div>--%>
<%--        <div class="col-8">--%>
<%--            <h1 class="text-center"><f:message key="adminProfile" bundle="${bunCont}"/></h1>--%>
<%--            <hr>--%>
<%--            <h3 style="text-align: left; margin-bottom: 20px; color: darkslateblue">--%>
<%--                <f:message key="usersOrders" bundle="${bunCont}"/>--%>
<%--            </h3>--%>
<%--            <div style="margin-top: 10px;" >--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<div>
    <div class="row">
        <div class="col-2" style="">
        </div>
        <div class="col-8">
            <h1 class="text-center">
                <f:message key="adminProfile" bundle="${bunCont}"/>
            </h1>
            <hr>
            <h3 style="text-align: left; margin-bottom: 20px; color: darkslateblue">
                <f:message key="usersOrders" bundle="${bunCont}"/>
            </h3>
            <div style="margin-top: 10px;" id="fieldsForSorting">
                <form style="display: flex;flex-direction: row;" class="" action="user" method="get"
                      onchange="submit()">
                    <label style="margin-left: 50px; width: 60px;font-size: 8pt; margin-right: 5px;font-weight: bold"><f:message
                            key="bookingStatus" bundle="${bunCont}"/>:</label>
                    <div style="font-size: 9pt;margin-left: 10px;">
                        <input type="checkbox" id="inProgress" name="statusInProgress"
                               value="inProgress" ${statusInProgress=='inProgress'?'checked':''}>
                        <label for="inProgress"><f:message key="inProgress" bundle="${bunCont}"/></label><br>
                        <input type="checkbox" id="confirmed" name="statusConfirmed"
                               value="confirmed" ${statusConfirmed=='confirmed'?'checked':''}>
                        <label for="confirmed"><f:message key="confirmed" bundle="${bunCont}"/></label><br>
                        <input type="checkbox" id="canceled" name="statusCanceled"
                               value="canceled" ${statusCanceled=='canceled'?'checked':''}>
                        <label for="canceled"><f:message key="canceled" bundle="${bunCont}"/></label><br>
                        <input type="checkbox" id="completed" name="statusCompleted"
                               value="completed" ${statusCompleted=='completed'?'checked':''}>
                        <label for="completed"><f:message key="completed" bundle="${bunCont}"/></label><br>
                    </div>
                    <label for="sorting"
                           style="width: 70px;font-size: 8pt; margin-left: 30px; margin-right: 5px;font-weight: bold">
                        <f:message key="orderItemsBy" bundle="${bunCont}"/>
                    </label>
                    <select class="select-size form-control" name="sorting" id="sorting"
                            style="width: 140px;height: 40px">
                        <option value="order_date" ${sorting == 'order_date' ? 'selected' : ''}>
                            <f:message key="orderDate" bundle="${bunCont}"/>
                        </option>
                        <option value="cruise_name" ${sorting == 'cruise_name' ? 'selected' : ''}>
                            <f:message key="cruise" bundle="${bunCont}"/>
                        </option>
                        <option value="amount" ${sorting == 'amount' ? 'selected' : ''}><f:message key="price"
                                                                                                   bundle="${bunCont}"/></option>
                        <option value="date_start" ${sorting == 'date_start'? 'selected' : ''}><f:message
                                key="dateStart"
                                bundle="${bunCont}"/></option>
                        </option>
                    </select>
                    <select class="select-size form-control" name="ordering"
                            style="width: 70px;margin-left: 3px;height: 40px">
                        <option value="ASC" ${ordering == 'ASC' ? 'selected' : ''}>A->Z</option>
                        <option value="DESC" ${ordering == 'DESC' ? 'selected' : ''}>Z->A</option>
                    </select>

                    <label for="itemsOnPage"
                           style="width: 65px;font-size: 8pt;margin-left: 30px; margin-right: 5px;font-weight: bold">
                        <f:message key="itemsPerPage" bundle="${bunCont}"/>
                    </label>
                    <select class="select-size form-control" id="itemsOnPage" name="itemsOnPage"
                            style="width: 60px;margin-right: 15%;height: 40px">
                        <option value="5" ${itemsOnPage == '5'? 'selected' : ''}>5</option>
                        <option value="10" ${itemsOnPage == '10' ? 'selected' : ''}>10</option>
                        <option value="15" ${itemsOnPage == '15' ? 'selected' : ''}>15</option>
                    </select>
                    <input name="command" value="showAdminProfile" hidden>
                </form>
            </div>
        </div>
        <div class="col-2" style="">
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">
                </div>
                <div class="col-2"></div>
            </div>
        </div>
    </div>
</div>


<div>
    <div class="row">
        <div class="col-2">
        </div>
        <div class="col-8 text-center">
            <table class="table table-striped" style="width: 100%;padding-bottom: 30px">
                <tr class="table-primary">
                    <th><f:message key="orderDate" bundle="${bunCont}"/></th>
                    <th><f:message key="cruise" bundle="${bunCont}"/></th>
                    <th><f:message key="dateStart" bundle="${bunCont}"/></th>
                    <th><f:message key="dateEnd" bundle="${bunCont}"/></th>
                    <th><f:message key="cabinType" bundle="${bunCont}"/></th>
                    <th><f:message key="cabin" bundle="${bunCont}"/></th>
                    <th><f:message key="price" bundle="${bunCont}"/>, <f:message key="uah" bundle="${bunCont}"/></th>
                    <th><f:message key="client" bundle="${bunCont}"/></th>
                    <th> orderStatus</th>
                    <th><f:message key="action" bundle="${bunCont}"/></th>
                </tr>
                <c:forEach var="payment" items="${paymentsList}">
                    <tr>
                        <td>${payment.paymentDate}</td>
                        <td>${payment.cruiseName}</td>
                        <td>${payment.dateStart}</td>
                        <td>${payment.dateEnd}</td>
                        <td>${payment.cabinType}</td>
                        <td>${payment.cabinNumber}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.firstName} ${payment.lastName}</td>
                        <td>${payment.orderStatusName}</td>
                        <td>
                            <form action="user" method="get" style="margin: auto;">
                                <button class="btn btn-outline-primary"
                                        name="command" value="orderHandlerPage"
                                        type="submit"
                                        style="">
                                    <f:message key="view" bundle="${bunCont}"/>
                                </button>
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
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="padding-bottom: 100px">
                <a class="btn btn-light"
                   href="user?command=showAdminProfile&numberOfPage=${numberOfPage-1<1?1:numberOfPage-1}&itemsOnPage=${itemsOnPage}&statusInProgress=${statusInProgress}&statusConfirmed=${statusConfirmed}&statusCanceled=${statusCanceled}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                ${numberOfPage==1?'hidden':''}><</a>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>
                    <a class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                       href="user?command=showAdminProfile&numberOfPage=${i}&numberOfPage=${numberOfPage-1<1?1:numberOfPage-1}&itemsOnPage=${itemsOnPage}&statusInProgress=${statusInProgress}&statusConfirmed=${statusConfirmed}&statusCanceled=${statusCanceled}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                        ${Math.abs(numberOfPage-i)<3&&totalPages>1?'':'hidden'}>${i}</a>
                    <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                       style="text-decoration: none;">...</a>
                </c:forEach>
                <a class="btn btn-light"
                   href="user?command=showAdminProfile&numberOfPage=${numberOfPage+1<=totalPages?numberOfPage+1:numberOfPage}&itemsOnPage=${itemsOnPage}&statusInProgress=${statusInProgress}&statusConfirmed=${statusConfirmed}&statusCanceled=${statusCanceled}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                ${numberOfPage>=totalPages?'hidden':''}>></a>
            </div>

        </div>
        <div class="col-2">
            <div class="container">
                <form action="home" method="get">
                    <button class="btn btn-outline-primary" name="command" value="editLinersCommand" type="submit"
                            style="width: 100%; height: 40px">
                        <f:message key="liners" bundle="${bunCont}"/>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="editRoutesCommand" type="submit"
                            style="width: 100%; height: 40px">
                        <f:message key="routes" bundle="${bunCont}"/>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="editCruisesCommand" type="submit"
                            style="width: 100%; height: 40px">
                        <f:message key="cruises" bundle="${bunCont}"/>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="adminStatisticCommand" type="submit"
                            style="width: 100%; height: 40px">
                        <f:message key="statistics" bundle="${bunCont}"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
