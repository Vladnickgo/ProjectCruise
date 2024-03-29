<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Error page*"/>

<c:import url="../views/head.jsp"/>

<body>

<c:import url="../views/header.jsp"/>
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
<div class="row container-fluid mt-4">
    <div class="col-3">
    </div>
    <div class="col-6">
        <div class="row bg-danger align-middle">
            <div class="col-3">
                <svg xmlns="http://www.w3.org/2000/svg" width="150" height="150" fill="white"
                     class="bi bi-exclamation-triangle ml-2"
                     viewBox="0 0 16 16">
                    <path d="M7.938 2.016A.13.13 0 0 1 8.002 2a.13.13 0 0 1 .063.016.146.146 0 0 1 .054.057l6.857 11.667c.036.06.035.124.002.183a.163.163 0 0 1-.054.06.116.116 0 0 1-.066.017H1.146a.115.115 0 0 1-.066-.017.163.163 0 0 1-.054-.06.176.176 0 0 1 .002-.183L7.884 2.073a.147.147 0 0 1 .054-.057zm1.044-.45a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566z"/>
                    <path d="M7.002 12a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 5.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995z"/>
                </svg>
            </div>
            <div class="col-9 align-middle">
                <h1 class="pt-5 text-white">
                    <f:message key="error" bundle="${bunCont}" ></f:message>
                </h1>
            </div>
        </div>
    </div>
    <div class="col-3">
    </div>
</div>
<div class="row container-fluid">
    <div class="col-3">
    </div>
    <div class="col-6">
        <div class="display-3">
            <f:message key="notRegistered" bundle="${bunCont}"></f:message>
        </div>
    </div>
    <div class="col-3">
    </div>

</div>
<c:import url="../views/footer.jsp"/>
</body>
</html>
