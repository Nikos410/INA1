<%@ page import="de.nikos410.ina.check.model.Data" %>
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

    final Data data = (Data) request.getSession().getAttribute("data");
%>

<form method="get" action="redirect-servlet">
    <label>
        Name
        <input type="text" name="name" placeholder="<%
            if (data != null) {
                out.println("Letzter Wert: " + data.getName());
            }
        %>">
    </label>
    <label>
        Ziel-Adresse
        <input type="url" name="target" placeholder="<%
            if (data != null) {
                out.println("Letzter Wert: " + data.getRedirectUrl());
            }
        %>" required>
    </label>
    <label>
        Beschreibung
        <input type="text" name="description" placeholder="<%
            if (data != null) {
                out.println("Letzter Wert: " + data.getDescription());
            }
        %>" >
    </label>
    <input type="submit" value="Los">
</form>
</body>
</html>
