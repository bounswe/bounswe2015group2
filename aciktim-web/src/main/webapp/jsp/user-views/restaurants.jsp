<%@include file="../sub-element/jsp_imports.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <%@include file="../sub-element/imports.jsp"%>
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">

  <c:if test="${full_name == ''}">
    <%@include  file="../sub-element/header_signed_out.jsp" %>
  </c:if>
  <c:if test="${full_name != ''}">
    <%@include  file="../sub-element/header_signed_in.jsp" %>
  </c:if>

    <%@include file="../sub-element/content_bar_restaurant.jsp"%>


  <%@include  file="../user-views/restaurant_grid.jsp" %>

  <%@include  file="../sub-element/footer.jsp" %>

</div>

</body>
</html>