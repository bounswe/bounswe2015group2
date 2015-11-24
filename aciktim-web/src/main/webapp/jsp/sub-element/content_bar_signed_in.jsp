<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container" id="content-bar">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/content-bar-style.css"/>
    <div class="row">
        <div class="col-md-8 text-left">
            <ul class="nav nav-pills">

                <li role="presentation" id="content_bar_profile" ><a href="/aciktim/user/view">Profile</a></li>
                <li role="presentation" id="content_bar_recipes"><a href="/aciktim/recipes">Recipes</a></li>
                <li role="presentation" id="content_bar_restaurants"><a href="/aciktim/restaurants">Restaurants</a></li>
                <li role="presentation" id="content_bar_create_recipe"><a href="/aciktim/recipe/form?action_type=add">Create Recipe</a></li>
                <li role="presentation" id="content_bar_create_menu"><a href="/aciktim/menu/form?action_type=add">Create Menu</a></li>
            </ul>
        </div>

        <div class="col-md-4 text-right">
            <input type="name" class="form-control search_group" id="search-field" placeholder="Search" id="content_bar_input">
            <button type="button" class="btn btn-warning search_group" id="content_bar_search">Search</button>
            <button type="button" class="btn btn-warning search_group" id="content_bar_advanced">Advanced Search</button>
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
        }else if(selection == "create_recipe"){
            $(".search_group").css("display","");
        }else{
            $(".search_group").css("display","");
            $("#content_bar_advanced").css("display","none");
        }
        $("#content_bar_advanced").click(function(){
            $("#searchForm").show();
        });
        $("#searchAlert").click(function(){
            alert("You enter a search");
        })


    });



</script>
<div class="row" style="background-color:#fff1c6 "  >
    <form role="form" id="searchForm" hidden="hidden">
        <div class="col-xs-6 col-md-2"></div>
        <div class="col-xs-6 col-md-3">
            <div class="form-group">
                <label >Ingredients</label>
                <input type="text" class="form-control" id="ingredients">
            </div>
            <div class="form-group">
                <label >Distinct</label>
                <input type="text" class="form-control" id="distinct">
            </div>
            <div>
                <label>type</label><br>
                <input type="radio" name="madeWhere" value="male">Homemade<br>
                <input type="radio" name="madeWhere" value="female">Made from restaurant
            </div>
        </div>
        <div class="col-xs-6 col-md-2">
            <div class="form-group">
                <label  >Carbonhydrate</label>
                <div class="input-group">
                    <input type="text" class="form-control"   id="carbon">
                    <span class="input-group-addon">gr</span>
                </div>
            </div>
            <div class="form-group">
                <label >Fat</label>
                <div class="input-group">
                    <input type="text" class="form-control"   id="fat">
                    <span class="input-group-addon">gr</span>
                </div>
            </div>
            <div class="form-group">
                <label >Protein</label>
                <div class="input-group">
                    <input type="text" class="form-control"   id="protein">
                    <span class="input-group-addon">gr</span>
                </div>
            </div>
            <div class="form-group">
                <label >Calories</label>
                <div class="input-group">
                    <input type="text" class="form-control"   id="calories">
                    <span class="input-group-addon">gr</span>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-2">
            <label>Some popular tags<span class="glyphicon glyphicon-tags"></span></label><br>
            <div id="c_b">

                <input type="checkbox" name="madeWhere" value="Cheap">Cheap<br>
                <input type="checkbox" name="madeWhere" value="Fat-free">Fat-free<br>
                <input type="checkbox" name="madeWhere" value="Quick">Quick<br>
                <input type="checkbox" name="madeWhere" value="Forguest">For guest<br>
            </div>
            <textarea id="t"></textarea>
            <script>
            function updateTextArea() {
            var allVals = [];
            $('#c_b :checked').each(function() {
            allVals.push($(this).val());
            });
            $("#t").val(allVals);
            }
            $(function() {
            $("#c_b input").click(updateTextArea);
            updateTextArea();
            });
            </script>
        </div>


        <button type="submit" id="searchAlert" class="btn btn-warning search_group"><span class="glyphicon glyphicon-search"></span>Search</button>
    </form>
</div>



