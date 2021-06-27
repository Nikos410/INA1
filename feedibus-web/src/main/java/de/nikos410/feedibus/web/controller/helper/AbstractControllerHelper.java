package de.nikos410.feedibus.web.controller.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public abstract class AbstractControllerHelper<T> {

    private volatile T bean;

    public T getBean() {
        return bean;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.bean = getBeanFromRequest(request);
        setSessionAttributes(request);

        processRequest(request, response);
    }

    private void setSessionAttributes(HttpServletRequest request) {

        final HttpSession session = request.getSession();
        session.setAttribute(this.getClass().getCanonicalName(), this);
        session.setAttribute(getBeanAttributeName(), this.bean);
    }

    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    protected abstract T getBeanFromRequest(HttpServletRequest httpServletRequest);

    protected abstract String getBeanAttributeName();
}
