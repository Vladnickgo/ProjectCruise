<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Edit ports page"/>

<c:import url="views/head.jsp"/>
<html lang="${sessionScope.language}">
<head>
    <meta charset="UTF-8">
    <title>Edit ports page</title>
</head>
<body>
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
            <input name="recordsOnPage" value="${recordsOnPage}" hidden>
            <input name="sorting" value="${sorting}" hidden>
            <input name="numberOfPage" value="${numberOfPage}" hidden>
            <input name="ordering" value="${ordering}" hidden>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-1"></div>
    <div class="col-8">
        <h1><f:message key="adminProfile" bundle="${bunCont}"/></h1>
        <hr>
    </div>
</div>
<div class="row mb-3">
    <div class="col-1"></div>
    <div class="col-8">
        <form action="home" method="get" onchange="submit()">
            <div class="row">
                <label for="itemsOnPage"
                       style="width: 85px;font-size: 8pt;margin-left: 30px; margin-right: 5px;font-weight: bold">
                    <f:message key="itemsPerPage" bundle="${bunCont}"/>
                </label>
                <select class="select-size form-control" id="itemsOnPage" name="itemsOnPage"
                        style="width: 60px;height: 40px">
                    <option value="5" ${itemsOnPage == '5'? 'selected' : ''}>5</option>
                    <option value="10" ${itemsOnPage == '10' ? 'selected' : ''}>10</option>
                    <option value="15" ${itemsOnPage == '15' ? 'selected' : ''}>15</option>
                </select>
                <label for="sorting"
                       style="width: 85px;font-size: 8pt; margin-left: 15px; margin-right: 5px;font-weight: bold">
                    <f:message key="orderItemsBy" bundle="${bunCont}"/>
                </label>
                <select class="select-size form-control" name="sorting" id="sorting"
                        style="width: 150px;height: 40px">
                    <option value="port_name_ua" ${sorting == 'port_name_ua' ? 'selected' : ''}>
                        <f:message key="portName" bundle="${bunCont}"/> UA
                    </option>
                    <option value="port_name_en" ${sorting == 'port_name_en' ? 'selected' : ''}>
                        <f:message key="portName" bundle="${bunCont}"/> EN
                    </option>
                    <option value="country_ua" ${sorting == 'country_ua' ? 'selected' : ''}>
                        <f:message key="countryName" bundle="${bunCont}"/> UA
                    </option>
                    <option value="country_en" ${sorting == 'country_en'? 'selected' : ''}>
                        <f:message key="countryName" bundle="${bunCont}"/> EN
                    </option>
                </select>
                <select class="select-size form-control" name="ordering"
                        style="width: 70px;margin-left: 3px;height: 40px">
                    <option value="ASC" ${ordering == 'ASC' ? 'selected' : ''}>A->Z</option>
                    <option value="DESC" ${ordering == 'DESC' ? 'selected' : ''}>Z->A</option>
                </select>
            </div>
            <input name="command" value="editPortsCommand" hidden>
        </form>
    </div>
    <div class="col-1">
    </div>
</div>

<div class="row">
    <div class="col-1"></div>
    <div class="col-8">
        <div style="text-transform:capitalize; background-color: darkslateblue; color: white;height: 60px; padding-left: 20px">
            <h2>
                <f:message key="ports" bundle="${bunCont}"/>
            </h2>
        </div>
        <hr>
        <table class="table striped-table">
            <tr>
                <th rowspan="2"><f:message key="indexNumber" bundle="${bunCont}"/></th>
                <th colspan="2"><f:message key="portName" bundle="${bunCont}"/></th>
                <th colspan="2"><f:message key="country" bundle="${bunCont}"/></th>
                <th rowspan="2" style="text-align: center"><f:message key="action" bundle="${bunCont}"/></th>
            </tr>
            <tr>
                <th>ua</th>
                <th>en</th>
                <th>ua</th>
                <th>en</th>
            </tr>
            <c:forEach var="port" items="${portList}">
                <tr>
                    <td></td>
                    <td>${port.portNameUa}</td>
                    <td>${port.portNameEn}</td>
                    <td>${port.countryUa}</td>
                    <td>${port.countryEn}</td>
                    <td>
                        <form action="home" method="get">
                            <button class="btn btn-outline-primary">
                                <f:message key="delete" bundle="${bunCont}"/>
                            </button>
                            <input name="portId" value="${port.id}" hidden>
                            <input name="portNameUa" value="${port.portNameUa}" hidden>
                            <input name="portNameEn" value="${port.portNameEn}" hidden>
                            <input name="countryUa" value="${port.countryUa}" hidden>
                            <input name="countryEn" value="${port.countryEn}" hidden>
                            <input name="command" value="deletePortPageCommand" hidden>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="padding-bottom: 100px; display: flex">
            <form action="home" method="get">
                <button type="submit" class="btn btn-light" ${numberOfPage==1?'hidden':''}><</button>
                <input name="command" value="editPortsCommand" hidden>
                <input name="numberOfPage" value="${numberOfPage-1<1?1:numberOfPage-1}" hidden>
                <input name="itemsOnPage" value="${itemsOnPage}" hidden>
                <input name="sorting" value="${sorting}" hidden>
                <input name="ordering" value="${ordering}" hidden>
            </form>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>

                <form action="home" method="get">
                    <button type="submit" class="btn btn-light" ${Math.abs(numberOfPage-i)<3&&totalPages>1?'':'hidden'}>
                            ${i}
                    </button>
                    <input name="command" value="editPortsCommand" hidden>
                    <input name="numberOfPage" value="${i}" hidden>
                    <input name="itemsOnPage" value="${itemsOnPage}" hidden>
                    <input name="sorting" value="${sorting}" hidden>
                    <input name="ordering" value="${ordering}" hidden>
                </form>
                <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                   style="text-decoration: none;">...</a>
            </c:forEach>
            <form action="home" method="get">
                <button type="submit" class="btn btn-light" ${numberOfPage>=totalPages?'hidden':''}>
                    >
                </button>
                <input name="command" value="editPortsCommand" hidden>
                <input name="numberOfPage" value="${numberOfPage+1<=totalPages?numberOfPage+1:numberOfPage}" hidden>
                <input name="itemsOnPage" value="${itemsOnPage}" hidden>
                <input name="sorting" value="${sorting}" hidden>
                <input name="ordering" value="${ordering}" hidden>
            </form>
        </div>
    </div>
    <div class="col-3">
        <div class="container">
            <div id="userForm" class="p-4">
                <h2><f:message key="addPort" bundle="${bunCont}"/></h2>
                <form action="home" method="post">
                    <div class="mb-3">
                        <h5><f:message key="portName" bundle="${bunCont}"/></h5>
                        <label for="portNameUa">UA</label>
                        <input class="form-control" id="portNameUa"
                               placeholder="${portDto.portNameUa==''?'input value':''}" type="text"
                               name="portNameUa"
                               value="${portDto.portNameUa}">
                        <label for="portNameEn">EN</label>
                        <input id="portNameEn" placeholder="${portDto.portNameEn==''?'input value':''}"
                               class="form-control" type="text" name="portNameEn" value="${portDto.portNameEn}">
                    </div>
                    <div class="mb-3">
                        <h5><f:message key="country" bundle="${bunCont}"/></h5>
                        <label for="countryUa">UA</label>
                        <input id="countryUa" placeholder="${portDto.countryUa==''?'input value':''}"
                               class="form-control" type="text" name="countryNameUa" value="${portDto.countryUa}">
                        <label for="countryEn">EN</label>
                        <input id="countryEn" placeholder="${portDto.countryEn==''?'input value':''}"
                               class="form-control" type="text" name="countryNameEn" value="${portDto.countryEn}">
                    </div>
                    <button style="width: 150px" type="submit"
                            class="btn btn-outline-primary"
                    >Ok
                    </button>
                    <input name="portList" value="${portList}" hidden>
                    <input name="command" value="addPortCommand" hidden>
                </form>
            </div>
            <div class="m-4 p-3" ${message!=null?'':'hidden'}>
                <h4>${message}</h4>
            </div>
        </div>
    </div>
</div>
</body>
</html>