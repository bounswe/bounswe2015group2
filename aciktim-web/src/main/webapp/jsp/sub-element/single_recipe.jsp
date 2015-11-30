<%@include file="jsp_imports.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">


    <c:if test="${full_name != ''}">


        <div class="row">
            <div class="col-sm-12">
                <table class="table table-bordered" style="margin-top:10px;">
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