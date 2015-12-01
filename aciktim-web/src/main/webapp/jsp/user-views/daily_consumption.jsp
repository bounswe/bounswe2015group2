<%@ page import="edu.boun.cmpe451.group2.client.Recipe" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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

    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <%--<div class="btn btn-primary pull-right">Prev</div>--%>
                        <div class="row">
                            <div class="col-sm-2">
                                <span class="btn btn-primary pull-left" id="prev_btn">Prev</span>
                            </div>
                            <div class="col-sm-8">
                                <h3 class="panel-title text-center" >${year}/${month}/${day} Consumption Summary</h3>
                            </div>
                            <div class="col-sm-2">
                                <span class="btn btn-primary pull-right" id="next_btn">Next</span>
                            </div>
                        </div>



                    </div>
                    <div class="panel-body">
                        <c:if test="${full_name != ''}">
                            <%--<%! int aggregate_cal = 0; %>--%>
                            <%--<%! int aggregate_carb = 0; %>--%>
                            <%--<%! int aggregate_prot = 0; %>--%>
                            <%--<%! int aggregate_fat = 0; %>--%>

                            <div class="row">
                                <div class="col-sm-12">
                                    <table class="table table-bordered" style="margin-top:10px;">
                                        <thead>
                                        <th>Name</th>
                                        <th>Picture</th>
                                        <th>Ingredients</th>
                                        <th>Nutritional Values</th>
                                        </thead>
                                        <tbody>


                                        <c:forEach var="recipe" items="${consumed_recipes}" varStatus="roop">
                                            <tr>
                                                <td width="20%">${recipe.name}</td>
                                                <td>
                                                    <img src="${recipe.pictureAddress}" class="img-rounded center-block" width="240">
                                                </td>
                                                <td width="60%">
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
                                                <td>
                                                    <table class="table table-striped">
                                                        <tr>
                                                            <td>Energy (kcal)</td>
                                                            <td>${recipe.totalCal}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Carbohydrate (grams)</td>
                                                            <td>${recipe.totalCarb}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Protein (grams)</td>
                                                            <td>${recipe.totalProtein}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Fat (grams)</td>
                                                            <td>${recipe.totalFat}</td>
                                                        </tr>
                                                    </table>
                                            </tr>
                                            </td>
                                            </tr>

                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <%
                                        List<Recipe> recipes = (ArrayList<Recipe>)request.getAttribute("consumed_recipes");
                                        int totalCal=0;
                                        int totalCarb=0;
                                        int totalProt=0;
                                        int totalFat=0;

                                        for (Recipe r : recipes){
                                            totalCal += r.totalCal;
                                            totalCarb += r.totalCarb;
                                            totalProt += r.totalProtein;
                                            totalFat += r.totalFat;
                                        }
                                    %>
                                    <table class="table table-striped">
                                        <tr>
                                            <td>Total Calories Taken</td>
                                            <td><%=totalCal%> kcal</td>
                                            <td>Daily Needed Calories</td>
                                            <td>2078 kcal</td>
                                        </tr>
                                        <tr>
                                            <td>Total Carbohydrate Taken</td>
                                            <td><%=totalCarb%> grams</td>
                                            <td>Daily Needed Carbohydrate</td>
                                            <td>310 grams</td>
                                        </tr>
                                        <tr>
                                            <td>Total Protein Taken</td>
                                            <td><%=totalProt%> grams</td>
                                            <td>Daily Needed Protein</td>
                                            <td>50 grams</td>
                                        </tr>
                                        <tr>
                                            <td>Total Fat Taken</td>
                                            <td><%=totalFat%> grams</td>
                                            <td>Daily Needed Fat</td>
                                            <td>70 grams</td>
                                        </tr>
                                    </table>
                                </div>

                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include  file="../sub-element/footer.jsp" %>

</div>


</body>

<script>
    $(document).ready(function (){
        $("#prev_btn").on("click", function(){
            var year = ${year};
            var month = ${month};
            var day = ${day};
            var d = new Date(year,month,day,0,0,0,0);
            d.setDate(d.getDate() - 1);
            window.location.replace("dailyconsumption?date="+d.getFullYear()+"/"+ (d.getMonth()+1)+"/"+ d.getDate());
        });

        $("#next_btn").on("click", function(){
            var year = ${year};
            var month = ${month};
            var day = ${day};
            var d = new Date(year,month,day,0,0,0,0);
            d.setDate(d.getDate() + 1);
            window.location.replace("dailyconsumption?date="+d.getFullYear()+"/"+ (d.getMonth()+1)+"/"+ d.getDate());
        });





    });




</script>

</html>
