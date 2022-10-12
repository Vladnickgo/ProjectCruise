<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${param.language}">
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="resources"/>
<nav class="navbar navbar-expand-sm navbar-light bg-light pb-2">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 ">
                <a class="navbar-brand" href="#">
                    <img width="80px" src="/views/img/cruise_logo.png"/>
                </a>
                <form class="d-flex" action="home" method="get">
                    <button class="btn btn-outline-primary" name="command" value="homePage" type="submit">
                        <f:message key="home"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="showCruises"
                            type="submit" ${user.role == 'ADMIN'?'hidden':''}>
                        <f:message key="cruises"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="cruiseManage"
                            type="submit" ${user.role == 'ADMIN'?'':'hidden'}>
                        <f:message key="cruises"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="aboutPage" type="submit">
                        <f:message key="about"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="contactsPage" type="submit">
                        <f:message key="contacts"></f:message>
                    </button>
                </form>
                <form class="d-flex" action="user" method="get">
                    <c:if test="${user == null}">
                        <button class="btn btn-outline-primary" name="command" value="loginPage" type="submit">
                            <f:message key="login"></f:message>
                        </button>
                        <button class="btn btn-outline-primary" name="command" value="registerPage" type="submit">
                            <f:message key="signin"></f:message>
                        </button>
                    </c:if>
                    <button class="btn btn-outline-primary" name="command" value="usersOrderPage"
                            type="submit" ${user.role != 'USER'?'hidden':''}>
                        <f:message key="order"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="showUserProfile"
                            type="submit" ${user.role == 'USER'?'':'hidden'}>
                        <f:message key="cabinet"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="showAdminProfile"
                            type="submit" ${user.role == 'ADMIN'?'':'hidden'}>
                        <f:message key="cabinet"></f:message>
                    </button>
                </form>
                <li class="nav-item ">
                    <form class="d-flex" action="user" method="get">
                        <button class="btn btn-primary" name="command" value="logout"
                                type="submit" ${user == null?'hidden':''}>
                            <f:message key="logout"></f:message>
                        </button>
                    </form>
                </li>
            </ul>
            <ul style="list-style: none; margin-right:25px; font-size: 14px">
                <%@ taglib uri="/WEB-INF/mytags.tld" prefix="m" %>
                <m:userinfo/>
            </ul>
            <%--            <form class="d-flex" method="post" onchange="submit()">--%>
            <%--                <select class=" form-control select-size" id="language" name="language"--%>
            <%--                        style="width: 120px;">--%>
            <%--                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>--%>
            <%--                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>--%>
            <%--                </select>--%>
            <%--                <input name="command" value="${command==null?'homePage':command}" hidden>--%>
            <%--                <input name="parameters" value="${parameters}" hidden>--%>
            <%--            </form>--%>
        </div>
    </div>
</nav>




