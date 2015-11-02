<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="requestURI" value="${pageContext.request.requestURI}" scope="application"/>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <script type="text/javascript" src="${contextPath}/assets/jquery/jquery-1.11.2.min.js"></script>
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/bootstrap/css/bootstrap.min.css"/>
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-out-style.css"/>
  <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

  <%@include  file="header-signed-out.jsp" %>
  <%@include  file="content-bar.jsp" %>

  <div class="container">

    <div class="row">
      <div class="col-md-3"><h3>My Profile</h3>
          <div class = "row">
              <!-- change the dummy link -->
              <img src = "http://ris.fashion.telegraph.co.uk/RichImageService.svc/imagecontent/1/TMG10811028/m/Miranda_Kerr_2902539a.jpg" class = "img-rounded"  width="240" height="320">
          </div>
          <div class = "row">
              <!-- change the dummy name and surname -->
              <h4>Miranda Kerr</h4>
          </div>



      </div>

      <div class="col-md-9"><h3>Recent Activities</h3>
          <div class = "row">
              <!-- change the dummy list items -->
              <ul class = "list-group">
                  <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
                  <li class = "list-group-item">Posted a new recipe: Tagliatelle Pollo</li>
                  <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
                  <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
                  <li class = "list-group-item">Posted a new recipe: Tagliatelle Pollo</li>
                  <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
              </ul>
          </div>
      </div>


    </div>


  </div> <!-- /container -->

  <%@include  file="footer.jsp" %>

</div>

</body>
</html>
