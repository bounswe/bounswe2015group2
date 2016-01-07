<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container" id="content-bar">
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/content-bar-style.css"/>
  <div class="row">
    <div class="col-md-8 text-left">
      <ul class="nav nav-pills">
        <c:if test="${full_name == ''}">
          <li role="presentation" id="content_bar_recipes"><a href="/aciktim/recipes">Recipes</a></li>
          <li role="presentation" id="content_bar_restaurants"><a href="/aciktim/restaurants">Restaurants</a></li>
        </c:if>
        <c:if test="${full_name != ''}">
          <li role="presentation" id="content_bar_profile" ><a href="/aciktim/user/view">Profile</a></li>
          <li role="presentation" id="content_bar_recipes"><a href="${contextPath}/recipes">Recipes</a></li>
          <li role="presentation" id="content_bar_restaurants"><a href="/aciktim/restaurants">Restaurants</a></li>
          <li role="presentation" id="content_bar_create_recipe"><a href="/aciktim/recipe/form?action_type=add">Create Recipe</a></li>
          <li role="presentation" id="content_bar_create_menu"><a href="/aciktim/menu/form?action_type=add">Create Menu</a></li>
          <li role="presentation" id="content_bar_menus"><a href="/aciktim/menus">My Menus</a></li>
          <li role="presentation" id="content_bar_dailyconsumption"><a href="/aciktim/user/dailyconsumption"> Consumption</a></li>
          <%--<li role="presentation" id="content_bar_recommendations"><a href="/aciktim/user/recommendations">Recommendations</a></li>--%>
        </c:if>

      </ul>
    </div>


    <div class="col-md-4 text-right">
      <form action="${contextPath}/restaurants" method="get" class="navbar-form">
        <div class="form-group">
          <input type="text" name="search_keyword" class="form-control search_group" id="search-field" placeholder="Search" id="content_bar_input">
          <button type="submit" class="btn btn-warning search_group" id="content_bar_search">Search</button>
        </div>
      </form>
    </div>


  </div>

</div>


<script>
  $(document).ready(function(){
    var selection = "${content_bar_selection}";
    $("li").removeAttr("class");
    $("#content_bar_"+selection).attr("class","active");

    if(selection == "profile"){
      $(".search_group").css("display","none");
    }else if(selection == "recipes"){
      $(".search_group").css("display","");
    }
  });



</script>
