package de.nikos410.ina.check;

import de.nikos410.ina.check.model.Data;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RedirectServlet extends HttpServlet {

    private static final String ERROR_URL = "check?error=Ziel%20muss%20angegeben%20sein.";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final Data bean = getBeanFromRequest(request);
        setBeanForSession(request, bean);
        response.sendRedirect(getRedirectUrl(bean));
    }

    private Data getBeanFromRequest(HttpServletRequest httpServletRequest) {

        final var bean = new Data();
        bean.setName(httpServletRequest.getParameter("name"));
        bean.setRedirectUrl(httpServletRequest.getParameter("target"));
        bean.setDescription(httpServletRequest.getParameter("description"));

        return bean;
    }

    private void setBeanForSession(HttpServletRequest request, Data bean) {

        request.getSession().setAttribute("data", bean);
    }

    private String getRedirectUrl(Data bean) {

        return Optional.ofNullable(bean.getRedirectUrl())
                .orElse(ERROR_URL);
    }
}