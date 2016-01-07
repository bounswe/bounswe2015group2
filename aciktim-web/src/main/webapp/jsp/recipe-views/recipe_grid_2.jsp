<%@ page import="java.util.List" %>
<%@include file="../sub-element/jsp_imports.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../sub-element/imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">


    <c:if test="${full_name != ''}">


        <div class="row">
            <div class="col-sm-12">
                <table class="table table-bordered" style="margin-top:10px;">
                    <thead>
                    <th>Picture</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Tags</th>
                    </thead>
                    <tbody>
                    <%--search result recipes--%>
                    <c:forEach var="recipe" items="${recipeResults}" varStatus="roop">
                        <tr>
                            <td width="20%">
                                <img src="${recipe.pictureAddress}" class="img-rounded center-block" width="240">
                            </td>
                            <td width="20%"><a href="${contextPath}/recipe/single?recipe_id=${recipe.id}">${recipe.name}</a></td>
                            <td width="40%">${recipe.description}</td>
                            <td>
                                <c:forEach var="tag" items="${recipe.tagList}" varStatus="roop">
                                    <code><a class="nostyle" href="recipes?tags=${tag.name}">${tag.name}</a></code>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>


    </c:if>

    <%--<c:if test="${full_name == ''}">--%>
    <%--<%@include  file="../sub-element/login_to_see.jsp" %>--%>
    <%--</c:if>--%>

</div>

</body>
</html>
<style>
    a.nostyle:link {
        text-decoration: inherit;
        color: inherit;
        cursor: auto;
    }

    a.nostyle:visited {
        text-decoration: inherit;
        color: inherit;
        cursor: auto;
    }
    .disableClick{
        pointer-events: none;
    }
</style>


<script>
    $(document).ready(function(){
        var full_name = "${full_name}";
        if(full_name == ""){
            $("a.nostyle").addClass("disableClick");
        }

    });


</script>

<%--<form class="form-horizontal row-border" action="${contextPath}/recipe/single">--%>
<%--<input type="hidden" name="recipe_id" value="${recipe.id}"/>--%>
<%--&lt;%&ndash;<c:forEach var="recipe" items="${recipeResults}" varStatus="roop">&ndash;%&gt;--%>
<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--<button type="submit" class="btn btn-default"--%>
<%--style="text-transform: capitalize">View</button>--%>
<%--</form>--%>