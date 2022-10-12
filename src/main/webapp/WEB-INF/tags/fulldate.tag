<%@ tag import="java.time.LocalDate" %>
<%@ tag import="java.util.Objects" %>
<%@ tag import="java.time.format.DateTimeFormatter" %>
<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ attribute name="date" rtexprvalue="true" type="java.lang.String" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
//    String myDate=date;
    String language=(String) request.getSession().getAttribute("language");
    LocalDate localDate = LocalDate.parse(date);
    String pattern = Objects.equals(language, "ua") || Objects.equals(language, "") ? "dd.MM.yyyy" : "yyyy-MM-dd";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    String format = localDate.format(formatter);
    out.print(format);
%>