<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container" id="content-bar">
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/content-bar-style.css"/>
  <div class="row">
    <div class="col-md-6 text-left">
      <ul class="nav nav-pills">
        <li role="presentation" id="content_bar_recipes"><a href="/aciktim/recipes">Recipes</a></li>
        <li role="presentation" id="content_bar_restaurants"><a href="/aciktim/restaurants">Restaurants</a></li>
      </ul>
    </div>


    <div class="col-md-4 text-right">
      <form action="${contextPath}/recipes" method="post" class="navbar-form">
        <div class="form-group">
          <input type="text" name="search_keyword" class="form-control search_group" id="search-field" placeholder="Search" id="content_bar_input">
          <button type="submit" class="btn btn-warning search_group" id="content_bar_search">Search</button>
        </div>
        <%--<button type="button" class="btn btn-warning search_group" id="content_bar_advanced">Advanced Search</button>--%>
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
