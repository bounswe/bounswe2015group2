<%@include file="../sub-element/jsp_imports.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <%@include file="../sub-element/imports.jsp"%>
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">



  <c:if test="${full_name != ''}">



    <div class="container">
      <%--<div class="row">--%>
        <%--<div class="col-sm-12">--%>

          <%--<form action="${contextPath}/recipeform" method="post" class="form-group">--%>
            <%--<input type="hidden" name="action_type" value="add">--%>
            <%--<button type="submit" class="btn btn-primary">Add a new recipe</button>--%>
          <%--</form>--%>
        <%--</div>--%>
      <%--</div>--%>


      <div class="row">
        <div class="col-sm-12">
          <table class="table table-bordered" style="margin-top:10px;">
            <thead>
            <th>Name</th>
            <th>Picture</th>
            <th>Description</th>
            </thead>
            <tbody>

              <%--my recipes by default--%>
            <c:forEach var="recipe" items="${recipes}" varStatus="roop">
              <tr>
                <td width="20%">${recipe.name}</td>
                <td>
                  <img src = "${recipe.pictureAddress}" class = "img-rounded center-block"  width="240">
                </td>
                <td width="60%">${recipe.description}</td>
              </tr>
            </c:forEach>
                <%--search result recipes--%>
            <c:forEach var="recipe" items="${recipeResults}" varStatus="roop">
              <tr>
                <td width="20%">${recipe.name}</td>
                <td>
                  <img src = "${recipe.pictureAddress}" class = "img-rounded center-block"  width="240">
                </td>
                <td width="60%">${recipe.description}</td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
      </div>


    </div>


  </c:if>

  <%--<c:if test="${full_name == ''}">--%>
    <%--<%@include  file="../sub-element/login_to_see.jsp" %>--%>
  <%--</c:if>--%>

</div>

</body>
</html>