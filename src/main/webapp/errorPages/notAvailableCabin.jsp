<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Not available cabin"/>

<c:import url="/views/head.jsp"/>
<html>
<head>
    <title>Not available cabin</title>
</head>
<body>
Sorry, but this cabin type not available. Try other...
</body>
</html>
