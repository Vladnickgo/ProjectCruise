<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="LogIn"/>

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
        <div class="col-3"></div>
        <div class="col-6">
            <div id="userForm">
                <h1 class="mb-5" id="headColor">
                    <f:message key="logForUsers" bundle="${bunCont}"/>
                </h1>
                <div style="display: flex; flex-direction: row; justify-content: center;">
                    <form  action="user" method="post">
                        <input name="command" value="login" hidden>
                        <table>
                            <tr>
                                <td>email</td>
                                <td><input class="form-control" type="text" name="email"></td>
                            </tr>
                            <tr>
                                <td>
                                    <f:message key="password" bundle="${bunCont}"/>
                                </td>
                                <td><input class="form-control" type="password" name="password"></td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td><input type="submit" value=<f:message key="submit" bundle="${bunCont}"/>></td>
                            </tr>
                        </table>
                        <div class="alert alert-warning"
                        ${errorMessage=='PASSWORD IS NOT VALID'?'':'hidden'}>
                            <p><f:message key="authFailed" bundle="${bunCont}"/></p>
                        </div>
                        <div class="alert alert-warning"
                        ${errorMessage=='EMAIL IS NOT VALID'?'':'hidden'}>
                            <p><f:message key="authFailed" bundle="${bunCont}"/></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-3">
            andrey@mail.com<br>
            sergey@mail.com<br>
            gena@mail.com<br>
            chuiko@mail.com<br>
            ivan@mail.com<br>
            artem@mail.com<br>
            holubkova@test.com<br>
            inna@gmail.com<br>
            algol@gmail.com<br>
            admin@mail.com<br>
        </div>
    </div>
</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>
