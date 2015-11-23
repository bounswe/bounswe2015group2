<%@include file="sub-element/jsp_imports.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="sub-element/imports.jsp"%>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/sign-up-style.css"/>
</head>
<body>
<div class="container" id="main-container">

    <%@include  file="sub-element/header_signed_out.jsp" %>

    <%@include  file="sub-element/content_bar.jsp" %>

    <div class="container" id="sign-up">
        <div class="container">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#">Regular User</a></li>
                <li role="presentation"><a href="#">Institutional User</a></li>
            </ul>
        </div>

        <div class="container">
            <form action="${contextPath}/adduser" method="post">
                <form class="form-group">

                    <div class="input-group">
                        <span class="input-group-addon">First Name : </span>
                        <input type="text" name="first_name" class="form-control" placeholder="${type}" aria-describedby="basic-addon1">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Last Name : </span>
                        <input type="text" name="last_name" class="form-control" placeholder="Last Name" aria-describedby="basic-addon1">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">E-mail : </span>
                        <input type="email" name="email" class="form-control" placeholder="Email" aria-describedby="basic-addon1">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Confirm E-mail: </span>
                        <input type="email" name="confirm_email" class="form-control" placeholder="Email" aria-describedby="basic-addon1">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Password: </span>
                        <input type="password" name="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Confirm Password: </span>
                        <input type="password" name="confirm_password" class="form-control" placeholder="Password" aria-describedby="basic-addon1">
                    </div>

                    <div class="input-group">
                        <button type="submit" class="btn btn-success">Update Profile</button>
                    </div>

                    <div class="input-group">
                        <button type="submit" class="btn btn-default">Cancel</button>
                    </div>

                </form>
            </form>
        </div>
    </div>


</div>

<%@include  file="sub-element/footer.jsp" %>

</body>
</html>
