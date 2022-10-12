<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Homepage"/>

<%--<jsp:useBean id="now" class="java.util.Date" scope="page"/>--%>

<c:import url="../views/head.jsp"/>
<html>
<head>
    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"--%>
    <%--            integrity="sha512-ElRFoEQdI5Ht6kZvyzXhYG9NqjtkmlkfYk0wr6wHxU9JEHakS7UJZNeml5ALk+8IKlU6jDgMabC3vkumRokgJA=="--%>
    <%--            crossorigin="anonymous" referrerpolicy="no-referrer"></script>--%>

</head>
<body>
<div>
    <div class="row text-center" style="background-color:#F8F9FA">
        <div class="col-11">
            <c:import url="/views/header.jsp"/>
        </div>
        <div class="col-1 pt-2">
            <form class="d-flex" method="get" onchange="submit()">
                <select class=" form-control select-size" id="language" name="language"
                        style="width: 120px;">
                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                </select>
                <%--                <input name="command" value="${command}" hidden>--%>
            </form>
        </div>
    </div>
</div>

<div class="container mt-5 mb-5 pb-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <div style="margin-top: 150px"><img src="/views/img/cruise_logo.png"/></div>
            <h1 style="color: darkslateblue">Cruise Line Company</h1>
        </div>
        <div class="col-4">
        </div>
    </div>
</div>

<div class="container mt-1 pt-1">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-4 text-center">
            <h1 class="display-1"><f:message key="welcome" bundle="${bunCont}"></f:message></h1>
            <h2 class="mt-5">☆ ☆ ☆ ☆ ☆</h2>
        </div>
        <div class="col-4">
        </div>
    </div>

</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>
