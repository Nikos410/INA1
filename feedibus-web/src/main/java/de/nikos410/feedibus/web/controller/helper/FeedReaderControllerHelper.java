package de.nikos410.feedibus.web.controller.helper;

import de.nikos410.feedibus.web.FeedReader;
import de.nikos410.feedibus.web.model.feed.RssFeed;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class FeedReaderControllerHelper extends AbstractControllerHelper<List<String>> {

    private static final String ERROR_URL = "check?error=Es%20muss%20mindestens%20eine%20URL%20angegeben%20sein.";

    private final FeedReader feedReader = new FeedReader();

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final List<String> urls = getBean();
        if (urls.isEmpty()) {
            response.sendRedirect(ERROR_URL);
        } else {

            final Map<String, List<RssFeed>> feeds = urls.stream()
                    .parallel()
                    .collect(Collectors.toMap(Function.identity(),
                            feedReader::findFeeds));
            request.getSession().setAttribute("feeds", feeds);

            request.getRequestDispatcher("show-feeds").forward(request, response);
        }
    }

    @Override
    protected List<String> getBeanFromRequest(HttpServletRequest httpServletRequest) {

        final String urls = httpServletRequest.getParameter("urls");
        return Arrays.asList(urls.split("[,;]"));
    }

    @Override
    protected String getBeanAttributeName() {
        return "feed-reader";
    }

}
