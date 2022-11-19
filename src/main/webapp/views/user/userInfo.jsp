<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>
<html>
<head>
    <title>userInfo</title>
</head>
<body>
<table class="table table-sm">
<%--    <tr ${user.firstName==null?'hidden':''}>--%>
<%--        <th><f:message key="firstName" bundle="${bunCont}"/></th>--%>
<%--        <td>${user.firstName}: ${user.id}</td>--%>
<%--    </tr>--%>
<%--    <tr ${user.lastName==null?'hidden':''}>--%>
<%--        <th><f:message key="lastName" bundle="${bunCont}"/></th>--%>
<%--        <td>${user.lastName}</td>--%>
<%--    </tr>--%>
<%--    <tr ${user.email==null?'hidden':''}>--%>
<%--        <th><f:message key="email" bundle="${bunCont}"/></th>--%>
<%--        <td>${user.email}</td>--%>
    </tr>
    </tr>
</table>
</body>
</html>
