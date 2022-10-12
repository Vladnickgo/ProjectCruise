<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Contacts"/>

<c:import url="../views/head.jsp"/>

<body>

<div class="">
    <div class="row text-center" style="background-color:#F8F9FA">
        <div class="col-11">
            <c:import url="../views/header.jsp"/>
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

<div class="container mt-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <h1 class="mb-5">
                <f:message key="contacts" bundle="${bunCont}"> </f:message>
            </h1>
        </div>
        <div class="col-4"></div>
    </div>
</div>

<div class="container mt-1">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <h3 class="mt-3">
                <f:message key="address" bundle="${bunCont}"> </f:message>
            </h3>
            Our address
            <h3 class="mt-5">
                <f:message key="telephones" bundle="${bunCont}"> </f:message>
            </h3>
            +(380)99-876-56-32<br>
            +(380)99-876-56-33<br>
            +(380)99-876-56-34<br>
            <h3 class="mt-5">Email</h3>
            test@test.ua<br>
            test@test.ua<br>
            test@test.ua<br>
        </div>
        <div class="col-4"></div>
    </div>
</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>
