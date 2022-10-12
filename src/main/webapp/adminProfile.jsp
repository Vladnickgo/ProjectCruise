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
                <input name="statusNotDone" value="${statusNotDone}" hidden>
                <input name="statusCompleted" value="${statusCompleted}" hidden>
                <input name="sorting" value="${sorting}" hidden>
                <input name="ordering" value="${ordering}" hidden>
                <input name="itemsOnPage" value="${itemsOnPage}" hidden>
            </form>
        </div>
    </div>
</div>
<div>
    <div class="row container-fluid">
        <div class="col-2" style="">

        </div>
        <div class="col-8">
            <h1 class="text-center"><f:message key="adminProfile" bundle="${bunCont}"/></h1>
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
                        <input type="checkbox" id="notDone" name="statusNotDone"
                               value="notDone" ${statusNotDone=='notDone'?'checked':''}>
                        <label><f:message key="notDone" bundle="${bunCont}"/></label><br>
                        <input type="checkbox" id="completed" name="statusCompleted"
                               value="completed" ${statusCompleted=='completed'?'checked':''}>
                        <label><f:message key="completed" bundle="${bunCont}"/></label><br>
                    </div>
                    <label for="sorting"
                           style="width: 70px;font-size: 8pt; margin-left: 30px; margin-right: 5px;font-weight: bold">
                        <f:message key="orderItemsBy" bundle="${bunCont}"/>
                    </label>
                    <select class="select-size form-control" name="sorting" id="sorting"
                            style="width: 140px;height: 40px">
                        <option value="order_date" ${sorting == 'order_date' ? 'selected' : ''}><f:message
                                key="orderDate"
                                bundle="${bunCont}"/></option>
                        <option value="hotel_name" ${sorting == 'hotel_name' ? 'selected' : ''}>
                            <f:message key="hotel" bundle="${bunCont}"/>
                        </option>
                        <option value="type_name" ${sorting == 'type_name'? 'selected' : ''}><f:message key="roomType"
                                                                                                        bundle="${bunCont}"/></option>
                        </option>
                        <option value="number_of_persons" ${sorting == 'number_of_persons'? 'selected' : ''}><f:message
                                key="numberOfPersons"
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
                    <th><f:message key="hotel" bundle="${bunCont}"/></th>
                    <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <th><f:message key="roomType" bundle="${bunCont}"/></th>
                    <th><f:message key="numberOfPersons" bundle="${bunCont}"/></th>
                    <th><f:message key="client" bundle="${bunCont}"/></th>
                    <th></th>
                </tr>
                <c:forEach var="usersOrder" items="${listOfOrders}">
                    <tr>
                        <td><tags:fulldate date="${usersOrder.orderDate}"/></td>
                        <td>${usersOrder.hotelDto.name}</td>
                        <td><tags:fulldate date="${usersOrder.dateStart}"/></td>
                        <td><tags:fulldate date="${usersOrder.dateEnd}"/></td>
                        <td>${usersOrder.roomDtoResponse.roomType.typeName}</td>
                        <td>${usersOrder.numberOfPersons}</td>
                        <td>${usersOrder.userDto.email}</td>
                        <td>
                            <form action="home" method="get" style="margin: auto;">
                                <button class="btn btn-outline-primary" ${usersOrder.orderStatus!='PROCESSED'?'hidden':''}
                                        name="command" value="orderHandlerPage"
                                        type="submit"
                                        style="">
                                    <f:message key="view" bundle="${bunCont}"/>
                                </button>
                                <input name="userId" value="${usersOrder.userDto.id}" hidden>
                                <input name="firstName" value="${usersOrder.userDto.firstName}" hidden>
                                <input name="lastName" value="${usersOrder.userDto.lastName}" hidden>
                                <input name="email" value="${usersOrder.userDto.email}" hidden>
                                <input name="dateStart" value="${usersOrder.dateStart}" hidden>
                                <input name="dateEnd" value="${usersOrder.dateEnd}" hidden>
                                <input name="roomType" value="${usersOrder.roomDtoResponse.roomType.typeName}" hidden>
                                <input name="hotelId" value="${usersOrder.hotelDto.id}" hidden>
                                <input name="hotelName" value="${usersOrder.hotelDto.name}" hidden>
                                <input name="numberOfPersons" value="${usersOrder.numberOfPersons}" hidden>
                                <input name="userOrderId" value="${usersOrder.id}" hidden>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div style="padding-bottom: 100px">
                <a class="btn btn-light"
                   href="user?command=showAdminProfile&numberOfPage=${numberOfPage-1<1?1:numberOfPage-1}&itemsOnPage=${itemsOnPage}&statusNotDone=${statusNotDone}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                ${numberOfPage==1?'hidden':''}><</a>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>
                    <a class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                       href="user?command=showAdminProfile&numberOfPage=${i}&itemsOnPage=${itemsOnPage}&statusNotDone=${statusNotDone}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                        ${Math.abs(numberOfPage-i)<3&&totalPages>1?'':'hidden'}>${i}</a>
                    <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                       style="text-decoration: none;">...</a>
                </c:forEach>
                <a class="btn btn-light"
                   href="user?command=showAdminProfile&numberOfPage=${numberOfPage+1>totalPages?totalPages:numberOfPage+1}&itemsOnPage=${itemsOnPage}&statusNotDone=${statusNotDone}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                ${numberOfPage>=totalPages?'hidden':''}>></a>
            </div>

        </div>
        <div class="col-2">
            <form action="home">
                <button class="btn btn-outline-primary" name="command" value="adminStatisticCommand" type="submit"
                        style="width: 100%; height: 40px">
                    <f:message key="statistics" bundle="${bunCont}"/>
                </button>
            </form>
        </div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
