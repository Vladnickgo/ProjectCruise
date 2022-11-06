<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Admin statistic page"/>

<c:import url="views/head.jsp"/>

<html>
<head>
    <title>adminStatisticPage</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"
            integrity="sha512-ElRFoEQdI5Ht6kZvyzXhYG9NqjtkmlkfYk0wr6wHxU9JEHakS7UJZNeml5ALk+8IKlU6jDgMabC3vkumRokgJA=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
<div class="row">
    <div class="col-1"></div>
    <div class="col-8">
        <h1>
            <f:message key="adminProfile" bundle="${bunCont}"/>
        </h1>
        <hr>
    </div>
    <div class="col-3"></div>
</div>
<div class="row">
    <div class="col-1"></div>
    <div class="col-8">
        <div style="text-transform:capitalize; background-color: darkslateblue; color: white;height: 60px; padding-left: 20px">
            <h1>
                <f:message key="statistics" bundle="${bunCont}"/>
            </h1>
        </div>
        <hr>
    </div>
    <div class="col-3"></div>
</div>
<div class="row mt-3">
    <div class="col-1"></div>
    <div class="col-8">
        <div>
            <form action="home" method="get" onchange="submit();">
                <input name="command" value="adminStatisticCommand" hidden>
                <div id="inputData" class="row">

                    <label for="start"
                           style="margin-left: 0px; width: 80px;font-size: 8pt; margin-right: 5px;font-weight: bold">
                        <f:message key="dateStart" bundle="${bunCont}"/>:</label>

                    <input style="width: 150px" type="date" class="form-control" id="start" name="dateStart"
                           value="${dateStart}"
                           min="${minDateStart}" max="${maxDateStart}">

                    <label for="start"
                           style="margin-left: 20px; width: 80px;font-size: 8pt; margin-right: 5px;font-weight: bold"><f:message
                            key="dateEnd" bundle="${bunCont}"/>:</label>

                    <input style="width: 150px" type="date" class="form-control" id="finish" name="dateEnd"
                           value="${dateEnd}"
                           min="${minDateEnd}" max="${maxDateEnd}">
                </div>
            </form>
        </div>
        <div class="row">
            <hr style="margin-top: 25px;">
            <div class="col-9">
                <h4>
                    <f:message key="numberOfCruisesPerPeriod" bundle="${bunCont}"/>
                </h4>
                <div style="width: 100%;height: 100%;">
                    <canvas id="numberOfCruisesPerPeriod" width="600" height="250"></canvas>
                </div>
            </div>
            <div class="col-3">
                <table class="table table-striped">
                    <tr>
                        <th><f:message key="linerName" bundle="${bunCont}"/></th>
                        <th style="text-align: center"><f:message key="numberOfCruises" bundle="${bunCont}"/></th>
                    </tr>
                    <c:forEach var="cruisesMap" items="${numberOfCruisesForShips}">
                        <tr style="font-size: 10pt">
                            <td>${cruisesMap.key}</td>
                            <td style="text-align: center">${cruisesMap.value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <hr style="margin-top: 30px;">
            <div class="col-9">
                <h4>
                    <f:message key="orderCabinsInfo" bundle="${bunCont}"/>
                </h4>
                <form action="home" method="get" onchange="submit()">
                    <select name="cruiseId" class="form-select" style="width: 200px">
                        <option selected disabled value="">Choose...</option>
                        <c:forEach items="${cruiseDtoList}" var="cruise">
                            <option value="${cruise.id}" ${cruise.id==cruiseId?'selected':''}>${cruise.cruiseName}</option>
                        </c:forEach>
                    </select>
                    <input name="command" value="adminStatisticCommand" hidden>
                    <input name="dateStart" value="${dateStart}" hidden>
                    <input name="dateEnd" value="${dateEnd}" hidden>
                </form>
                <div style="width: 100%;height: 100%;">
                    <canvas id="cruisesProfitabilityChart" width="600" height="220"></canvas>
                </div>
            </div>
            <div class="col-3">

                <table class="table table-striped">
                    <tr>
                        <th><f:message key="cruise" bundle="${bunCont}"/></th>
                        <th style="text-align: center"><f:message key="numberOfBusyCabins" bundle="${bunCont}"/></th>
                        <th style="text-align: center"><f:message key="numberOfCabins" bundle="${bunCont}"/></th>
                    </tr>
                    <c:forEach var="cabinType" items="${cabinTypeResponseDtoList}">
                        <tr style="font-size: 10pt">
                            <td>${cabinType.cabinTypeName}</td>
                            <td style="text-align: center">${cabinType.numberOfBusyCabins}</td>
                            <td style="text-align: center">${cabinType.numberOfCabins}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="col-2"></div>

</div>

<script>
    const xCruiseStatisticsValues = [];
    const yCruiseStatisticsValues = [];
    const barCruiseStatisticsColors = [];
    <c:forEach var="shipStatistic" items="${numberOfCruisesForShips}">
    xCruiseStatisticsValues.push("${shipStatistic.key}");
    yCruiseStatisticsValues.push("${shipStatistic.value}");
    barCruiseStatisticsColors.push('rgba(50, 205, 50, 0.4)');
    </c:forEach>

    new Chart("numberOfCruisesPerPeriod", {
        type: "bar",
        data: {
            labels: xCruiseStatisticsValues,
            datasets: [{
                backgroundColor: barCruiseStatisticsColors,
                data: yCruiseStatisticsValues,
                label: '<f:message key="numberOfOrders" bundle="${bunCont}"/>'

            }]
        },
        options: {
            legend: {display: true},
            labels: ['<f:message key="numberOfOrders" bundle="${bunCont}"/>'],
            title: {
                display: true,
                text: "Info about bookings"
            }
        }
    });

    const xCabinStatisticsValues = [];
    const yCabinStatisticsValues = [];
    const yBusyCabinStatisticsValues = [];
    const barCabinStatisticsColors = [];
    const barBusyCabinStatisticsColors = [];
    <c:forEach var="cabinType" items="${cabinTypeResponseDtoList}">
    xCabinStatisticsValues.push("${cabinType.cabinTypeName}");
    yCabinStatisticsValues.push("${cabinType.numberOfCabins}");
    yBusyCabinStatisticsValues.push("${cabinType.numberOfBusyCabins}");
    barCabinStatisticsColors.push('rgba(97,97,163, 0.4)');
    barBusyCabinStatisticsColors.push('rgba(163,21,180, 0.4)');
    </c:forEach>

    const newBar = {
        data: {
            labels: xCabinStatisticsValues,
            datasets: [
                {
                    type: "bar",
                    backgroundColor: barBusyCabinStatisticsColors,
                    data: yBusyCabinStatisticsValues,
                    label: '<f:message key="numberOfOrders" bundle="${bunCont}"/>'

                },
                {
                type: "bar",
                backgroundColor: barCabinStatisticsColors,
                data: yCabinStatisticsValues,
                label: '<f:message key="numberOfCabins" bundle="${bunCont}"/>'

            }
            ]
        },
        options: {
            legend: {display: true},
            labels: ['<f:message key="numberOfOrders" bundle="${bunCont}"/>'],
            title: {
                display: true,
                text: "Info about bookings"
            }
        }
    }
    new Chart("cruisesProfitabilityChart", newBar);
</script>
</body>
</html>
