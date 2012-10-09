package com.pduda.tourney.web.jsf;

public interface WebUtils {

    void redirect(String uri, RequestParam... params);

    String getRequestAttribute(Object request, String attribute);

    String getRequestParameter(String parameter);
}
