package de.nikos410.ina.check;

import de.nikos410.ina.check.helper.RedirectControllerHelper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

public class RedirectServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        getOrCreateControllerHelper(request)
                .doGet(request, response);
    }

    private RedirectControllerHelper getOrCreateControllerHelper(HttpServletRequest request) {

        final RedirectControllerHelper controllerHelper = (RedirectControllerHelper) request.getSession()
                .getAttribute(RedirectControllerHelper.class.getCanonicalName());

        return Objects.requireNonNullElseGet(controllerHelper, RedirectControllerHelper::new);
    }
}