<%@include file="jsp_imports.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">





    <div class="row">
        <div class="col-sm-12">
            <table class="table table-striped" style="margin-top:10px;">
                <tbody>

                <tr>
                    <td>
                        Name:
                    </td>
                    <td>
                        ${recipe.name}
                    </td>
                </tr>
                <tr>
                    <td>
                        Picture:
                    </td>
                    <td>
                        <img src="${recipe.pictureAddress}" class="img-rounded center-block" width="240">
                    </td>
                </tr>
                <tr>
                    <td>
                        Owner:
                    </td>
                    <td>
                        ${owner_name}
                    </td>
                </tr>
                <tr>
                    <td>
                        Likes:
                    </td>
                    <td>
                        ${recipe.likes}
                    </td>
                </tr>
                <tr>
                    <td>
                        Protein Value:
                    </td>
                    <td>
                        ${recipe.totalProtein}
                    </td>
                </tr>
                <tr>
                    <td>
                        Fat Value:
                    </td>
                    <td>
                        ${recipe.totalFat}
                    </td>
                </tr>
                <tr>
                    <td>
                        Carb Value:
                    </td>
                    <td>
                        ${recipe.totalCarb}
                    </td>
                </tr>
                <tr>
                    <td>
                        Total Calories:
                    </td>
                    <td>
                        ${recipe.totalCal}
                    </td>
                </tr>
                <tr>
                    <td>
                        Ingredients:
                    </td>
                    <td>
                        <table class="table table-striped">
                            <c:forEach var="ingredient" items="${recipe.ingredientAmountMap}" varStatus="roop">
                                <tr>
                                    <td>${ingredient.key.name}</td>
                                    <td>${ingredient.value}</td>
                                    <td>${ingredient.key.unitName}</td>
                                </tr>

                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        Tags:
                    </td>
                    <td>
                        <table class="table table-striped">
                            <c:forEach var="tag" items="${recipe.tagList}" varStatus="roop">
                                <tr>
                                    <td>${tag.name}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <tr id="consume_section" style="display: none;">
                    <td>
                        Date:
                    </td>
                    <td>


                        <form class="form-horizontal row-border" action="${contextPath}/recipe/consume"
                              method="post">
                            <input type="input" name="day" placeholder="dd"/>
                            <input type="input" name="month" placeholder="mm"/>
                            <input type="input" name="year" placeholder="yyyy"/>
                            <input type="hidden" name="recipe_id" value="${recipe.id}"/>
                            <button type="submit" class="btn btn-primary btn-sm"
                                    style="text-transform: capitalize">Consume
                            </button>
                        </form>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>



    <%--<c:if test="${full_name == ''}">--%>
    <%--<%@include  file="../sub-element/login_to_see.jsp" %>--%>
    <%--</c:if>--%>

</div>

</body>
</html>

<script>
    $(document).ready(function(){
        var full_name = "${full_name}";
        if(full_name != ""){
            $("#consume_section").css("display","");
        }

    });


</script>