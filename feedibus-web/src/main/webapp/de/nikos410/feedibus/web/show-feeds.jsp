<%@ page import="de.nikos410.feedibus.web.model.feed.RssFeed" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="de.nikos410.feedibus.web.model.feed.RssChannel" %>
<%@ page import="de.nikos410.feedibus.web.model.feed.RssItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GOTO</title>
</head>
<body>

<h1>Feeds</h1>
<%
    final Map<String, List<RssFeed>> rssFeeds = (Map<String, List<RssFeed>>) request.getSession().getAttribute("feeds");

    for (Map.Entry<String, List<RssFeed>> entry : rssFeeds.entrySet()) {
        out.println("<h2>Results for URL " + entry.getKey() + "</h2>");
        out.println("<p>Found " + entry.getValue().size() + " feed(s).</p>");

        for (RssFeed feed : entry.getValue()) {
            out.println("<hr/>");
            for (RssChannel channel : feed.getChannels()) {
                out.println("<h3>Channel " + channel.getTitle() + "</h3>");
                out.println("<p>" + channel.getDescription() + "</p>");
                out.println("<ul>");
                for (RssItem item : channel.getItems()) {
                    out.println("<li>" + item.getTitle() + "</li>");
                }
                out.println("</ul>");
            }
        }
    }
%>
</body>
</html>
