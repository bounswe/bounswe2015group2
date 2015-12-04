<%@include file="../sub-element/jsp_imports.jsp"%>
<html>
<head>
  <%@include file="../sub-element/imports.jsp"%>
  <%--<link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-out-style.css"/>--%>
</head>

<body>
<div class="container">
  <c:if test="${full_name == ''}">
    <%@include  file="../sub-element/header_signed_out.jsp" %>
  </c:if>
  <c:if test="${full_name != ''}">
    <%@include file="../sub-element/header_signed_in.jsp"%>
  </c:if>

  <c:if test="${full_name == ''}">
    <%@include file="../sub-element/content_bar_signed_out.jsp"%>
  </c:if>
  <c:if test="${full_name != ''}">
    <%@include file="../sub-element/content_bar_signed_in.jsp"%>
  </c:if>




  <%--<c:if test="${full_name != ''}">--%>
  <%@include  file="../sub-element/single_recipe.jsp" %>
  <%--</c:if>--%>



  <%@include  file="../sub-element/footer.jsp" %>
</div>
</body>
</html>
