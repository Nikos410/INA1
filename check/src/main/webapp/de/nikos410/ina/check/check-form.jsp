<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GOTO</title>
</head>
<body>
<%
    if (request.getParameter("error") != null &&
            !request.getParameter("error").isBlank()) {
        out.println("<p>" + request.getParameter("error") + "</p>")   ;
    }
%>

<form method="get" action="redirect-servlet">
    <input type="url" name="target" required>
    <input type="submit" value="Submit">
</form>
</body>
</html>
