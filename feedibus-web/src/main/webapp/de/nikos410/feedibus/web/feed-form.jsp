<%@ page import="de.nikos410.feedibus.web.model.bean.FeedReaderBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Feedibus</title>
</head>
<body>
<%
    if (request.getParameter("error") != null &&
            !request.getParameter("error").isBlank()) {
        out.println("<p>" + request.getParameter("error") + "</p>")   ;
    }
%>

<form method="get" action="find-feeds">
    <label>
        URLs (Komma-getrennt angeben)
        <input type="url" multiple required name="urls" />
    </label>
    <input type="submit" value="Los">
</form>
</body>
</html>
