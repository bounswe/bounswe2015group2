<%@include file="../sub-element/jsp_imports.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../sub-element/imports.jsp"%>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/sign-up-style.css"/>
</head>
<body>
<%--<div class="container text-center" id="main-container">--%>

<%@include  file="../sub-element/header_signed_out.jsp" %>

<%@include  file="../sub-element/content_bar_signed_out.jsp" %>

<div class="container text-center" id="sign-up">
    <%--<div class="container">--%>

    <c:if test="${type == 'ERROR'}">
        <div class="alert alert-danger">
            <strong>ERROR:</strong> Check your data.
        </div>
    </c:if>
    <!-- ${type} -->


    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <form action="${contextPath}/user/add" method="post" class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">First Name : </span>
                    <input type="text" name="first_name" class="form-control" placeholder="First Name" aria-describedby="basic-addon1">
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
                    <span class="input-group-addon">Institutional User? </span>
                    <input type="checkbox" name="is_institution" aria-describedby="basic-addon1">
                </div>

                <div class="input-group">
                    <button type="submit" class="btn btn-success">Register</button>
                </div>
            </form>
        </div>
    </div>


</div>
<%--</div>--%>

<%--</div>--%>



<%@include  file="../sub-element/footer.jsp" %>

</div>



<script>
    jQuery(document).ready(function ($) {
        $('.nav-tabs').tab();
    });
</script>

</body>
</html>