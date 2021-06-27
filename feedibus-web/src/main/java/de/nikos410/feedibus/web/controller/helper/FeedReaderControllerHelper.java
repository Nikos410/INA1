package de.nikos410.feedibus.web.controller.helper;

import de.nikos410.feedibus.web.FeedReader;
import de.nikos410.feedibus.web.model.bean.FeedReaderBean;
import de.nikos410.feedibus.web.model.feed.RssFeed;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class FeedReaderControllerHelper extends AbstractControllerHelper<FeedReaderBean> {

    private static final String ERROR_URL = "check?error=Es%20m%C3%BCssen%203%20URLs%20angegeben%20weden.";

    private final FeedReader feedReader = new FeedReader();

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final FeedReaderBean bean = getBean();
        if (isNull(bean.urlOne()) || isNull(bean.urlTwo()) || isNull(bean.urlThree())) {
            response.sendRedirect(ERROR_URL);
        } else {
            final Map<String, List<RssFeed>> feeds = new HashMap<>();
            feeds.put(bean.urlOne(), feedReader.findFeeds(bean.urlOne()));
            feeds.put(bean.urlTwo(), feedReader.findFeeds(bean.urlTwo()));
            feeds.put(bean.urlThree(), feedReader.findFeeds(bean.urlThree()));
            request.getSession().setAttribute("feeds", feeds);

            request.getRequestDispatcher("show-feeds").forward(request, response);
        }
    }

    @Override
    protected FeedReaderBean getBeanFromRequest(HttpServletRequest httpServletRequest) {

        final String urlOne = httpServletRequest.getParameter("urlOne");
        final String urlTwo = httpServletRequest.getParameter("urlTwo");
        final String urlThree = httpServletRequest.getParameter("urlThree");
        return new FeedReaderBean(urlOne, urlTwo, urlThree);
    }

    @Override
    protected String getBeanAttributeName() {
        return "feed-reader";
    }

}
