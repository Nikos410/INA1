package de.nikos410.feedibus.web.controller;

import de.nikos410.feedibus.web.controller.helper.FeedReaderControllerHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

public class FeedReaderController extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        getOrCreateControllerHelper(request)
                .doGet(request, response);
    }

    private FeedReaderControllerHelper getOrCreateControllerHelper(HttpServletRequest request) {

        final FeedReaderControllerHelper controllerHelper = (FeedReaderControllerHelper) request.getSession()
                .getAttribute(FeedReaderControllerHelper.class.getCanonicalName());

        return Objects.requireNonNullElseGet(controllerHelper, FeedReaderControllerHelper::new);
    }
}