<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container" id="header-bar">
    
    <c:if test="${full_name == ''}">
        <%@include file="../sub-element/header_signed_out.jsp" %>
    </c:if>
    <c:if test="${full_name != ''}">
        <%@include file="../sub-element/header_signed_in.jsp" %>
    </c:if>


    <c:if test="${full_name == ''}">
        <%@include file="../sub-element/content_bar_signed_out.jsp" %>
    </c:if>
    <c:if test="${full_name != ''}">
        <%@include file="../sub-element/content_bar_signed_in.jsp" %>
    </c:if>
</div>
