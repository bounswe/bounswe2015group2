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



    <%@include file="../sub-element/content_bar.jsp"%>



    <c:if test="${full_name != ''}">
        <%@include  file="../sub-element/profile.jsp" %>
    </c:if>
    <c:if test="${full_name == ''}">
        <%@include  file="../sub-element/login_to_see.jsp" %>
    </c:if>



    <%@include  file="../sub-element/footer.jsp" %>

</div>

<script type="text/javascript">
    $(document).ready(function() {
        var bad_attempt = ${bad_attempt};
        console.log("Ballshot");
        if(bad_attempt){
            bootbox.alert("Invalid Credentials", function(){
                window.location.replace("${contextPath}/index");
            });
        }
    })
</script>


</body>
</html>