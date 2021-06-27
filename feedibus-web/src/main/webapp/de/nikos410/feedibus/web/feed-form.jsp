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

    final FeedReaderBean data = (FeedReaderBean) request.getSession().getAttribute("feed-reader");
%>

<form method="get" action="find-feeds">
    <label>
        URL 1
        <input type="url" required name="urlOne" placeholder="<%
            if (data != null) {
                out.println("Letzter Wert: " + data.urlOne());
            }
        %>">
    </label>
    <label>
        URL 2
        <input type="url" required name="urlTwo" placeholder="<%
            if (data != null) {
                out.println("Letzter Wert: " + data.urlTwo());
            }
        %>">
    </label>
    <label>
        URL 3
        <input type="url" required name="urlThree" placeholder="<%
            if (data != null) {
                out.println("Letzter Wert: " + data.urlThree());
            }
        %>">
    </label>
    <input type="submit" value="Los">
</form>
</body>
</html>
