
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Homepage"/>

<c:import url="../views/head.jsp"/>
<html>
<head>
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
            </form>
        </div>
    </div>
</div>

<div class="mb-4 pb-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <div style="margin-top: 120px"><img src="/views/img/cruise_logo.png"/></div>
            <h1 style="color: darkslateblue">Cruise Line Company</h1>
        </div>
        <div class="col-4">
        </div>
    </div>
</div>

<div class="row">
    <div class="col-4"></div>
    <div class="col-4 text-center">
        <h1 class="display-1"><f:message key="welcome" bundle="${bunCont}"></f:message></h1>
        <h2 class="mt-4">☆ ☆ ☆ ☆ ☆</h2>
    </div>
    <div class="col-4">
    </div>
</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>