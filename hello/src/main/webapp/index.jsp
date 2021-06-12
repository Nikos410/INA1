<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello JSP!</title>
</head>
<body>
Hey <%
        if (request.getParameter("name") == null) {
            out.println("there!");
        } else {
            out.println("<b>" + request.getParameter("name") + "</b>!");
        }
    %>
</body>
</html>
