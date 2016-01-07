<%@include file="../sub-element/jsp_imports.jsp"%>
<html>
<head>
    <%@include file="../sub-element/imports.jsp"%>
    <%--<link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-out-style.css"/>--%>
</head>

<body>
<div class="container">
    <%@include file="../sub-element/header.jsp"%>

    <c:if test="${full_name != ''}">
        <%@include  file="../sub-element/recipe_form.jsp" %>
    </c:if>



    <%@include  file="../sub-element/footer.jsp" %>
</div>
</body>
</html>
