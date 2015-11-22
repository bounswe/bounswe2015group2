<%@include  file="../sub-element/jsp_imports.jsp" %>

<html>
<head>
    <%@include  file="../sub-element/imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>


</head>
<body>
<%@include  file="../sub-element/header_signed_out.jsp" %>
<%@ include  file="../sub-element/content_bar_signed_in.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <div class="input-group">
                <span class="input-group-addon" >enter the name</span>
                <input type="text" name="recipeName" class="form-control" placeholder="Name for recipe">
            </div>
        </div>
        <div class="col-lg-6">
            <div class="input-group">
                <span class="input-group-addon" >enter the category</span>
                <input type="text"  class="form-control" placeholder="Category for the recipe">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <div class="input-group">
                <span class="input-group-addon" >Photo</span>
                <input type="text" class="form-control" placeholder="photo.png" >
      <span class="input-group-btn">
        <button class="btn btn-warning btn-block" type="button">Browse</button>
      </span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            Please enter the ingredients and their amounts
            <div class="col-lg-6">
            </div>
            <div class="col-lg-3">
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Ingredients
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" >
                        <li><a href="#">testCarbonhydrate</a></li>
                        <li><a href="#">testProtein</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Amount
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" >
                        <li><a href="#">testAmount</a></li>
                        <li><a href="#">testAmount2</a></li>
                    </ul>
                </div>

            </div>
        </div>

    </div>
    <div class="col-md-3">
        <button type="button" class="btn btn-warning btn-block">Add</button>
    </div>
    <div class="col-md-3">
        <button type="button" class="btn btn-warning btn-block">Calculate the nutritional data</button>
    </div>

</div>

<div class="col-md-6">
</div>
<div class="col-md-6">
    <div class = "row">
        Tags
    </div>
    <div class = "row">
        <div class="col-md-6">
            <form role = "form">
                <div class = "form-group">
                    <input type = "text" class = "form-control" placeholder = "Enter a tag">
                </div>
            </form>
        </div>
        <div class="col-md-3">
            <button type="button" class="btn btn-warning btn-block">Add</button>
        </div>

    </div>
    <div class = "row">
        <div class="col-md-6">
            <form role = "form">
                <div class = "form-group">
                    <textarea class = "form-control" rows = "3" placeholder = "Some tags"></textarea>
                </div>

            </form>
        </div>
    </div>
</div>

<%@include  file="../sub-element/footer.jsp" %>
</body>
</html>
