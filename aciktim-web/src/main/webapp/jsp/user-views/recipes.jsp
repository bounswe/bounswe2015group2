<%@include  file="../sub-element/jsp_imports.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <%@include  file="../sub-element/imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">


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

    <%--<div class="container text-center">--%>
        <%--<div class="row">--%>
            <%--<div class="col-sm-12">--%>
                <%--<h4>Hello, there will be public recipes here</h4>--%>
            <%--</div>--%>
        <%--</div>--%>

    <%--</div>--%>

    <%--<div class="row">--%>
        <%--<div class="col-sm-12">--%>
            <%--<table class="table table-bordered" style="margin-top:10px;">--%>
                <%--<thead>--%>
                <%--<th>Name</th>--%>
                <%--<th>Picture</th>--%>
                <%--<th>Description</th>--%>
                <%--&lt;%&ndash;<th>Red</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>Delete</th>&ndash;%&gt;--%>
                <%--</thead>--%>
                <%--<tbody>--%>
                <%--<c:forEach var="recipe" items="${recipes}" varStatus="roop">--%>
                    <%--<tr>--%>
                        <%--<td>${recipe.name}</td>--%>
                        <%--<td>--%>
                            <%--<img src = "${recipe.pictureAddress}" class = "img-rounded center-block"  width="240">--%>
                        <%--</td>--%>
                        <%--<td>${recipe.description}</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%@include  file="../recipe-views/recipe_grid_2.jsp" %>

    <%@include  file="../sub-element/footer.jsp" %>

</div>

<script type="text/javascript">
    $(document).ready(function() {
        var bad_attempt = ${bad_attempt};
        if(bad_attempt){
            bootbox.alert("Invalid Credentials", function(){
                window.location.replace("${contextPath}/index");
            });
        }

    })
</script>


</body>
</html>