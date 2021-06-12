<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GOTO</title>
</head>
<body>
<%
    if (request.getParameter("target") == null ||
            request.getParameter("target").isBlank()) {
        response.sendRedirect("goto.jsp?error=Ziel%20muss%20angegeben%20sein.");
    } else {
        response.sendRedirect(request.getParameter("target"));
    }
%>

</body>
</html>
