package de.nikos410.feedibus.web.controller.helper;

import de.nikos410.feedibus.web.model.FeedReaderBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.Objects.isNull;

public class FeedReaderControllerHelper extends AbstractControllerHelper<FeedReaderBean> {

    private static final String ERROR_URL = "check?error=Es%20m%C3%BCssen%203%20URLs%20angegeben%20weden.";

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final FeedReaderBean bean = getBean();
        if (isNull(bean.urlOne()) || isNull(bean.urlTwo()) || isNull(bean.urlThree())) {
            response.sendRedirect(ERROR_URL);
        } else {
            // TODO: read feeds
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
