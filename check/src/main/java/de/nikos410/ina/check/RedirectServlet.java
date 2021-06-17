package de.nikos410.ina.check;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RedirectServlet extends HttpServlet {

    private static final String TARGET_PARAMETER_NAME = "target";
    private static final String ERROR_URL = "check?error=Ziel%20muss%20angegeben%20sein.";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String redirectUrl = getRedirectUrl(request);
        response.sendRedirect(redirectUrl);
    }

    private String getRedirectUrl(HttpServletRequest request) {

        return getTargetFromRequest(request)
                .orElse(ERROR_URL);
    }

    private Optional<String> getTargetFromRequest(HttpServletRequest request) {

        return Optional.ofNullable(request.getParameter(TARGET_PARAMETER_NAME));
    }
}