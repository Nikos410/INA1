package de.nikos410.ina.check.helper;

import de.nikos410.ina.check.model.Data;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class RedirectControllerHelper extends AbstractControllerHelper<Data> {

    private static final String ERROR_URL = "check?error=Ziel%20muss%20angegeben%20sein.";

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect(getRedirectUrl());
    }

    @Override
    protected Data getBeanFromRequest(HttpServletRequest httpServletRequest) {

        final var bean = new Data();
        bean.setName(httpServletRequest.getParameter("name"));
        bean.setRedirectUrl(httpServletRequest.getParameter("target"));
        bean.setDescription(httpServletRequest.getParameter("description"));

        return bean;
    }

    @Override
    protected String getBeanAttributeName() {
        return "data";
    }

    private String getRedirectUrl() {

        return Optional.ofNullable(getBean().getRedirectUrl())
                .orElse(ERROR_URL);
    }
}
