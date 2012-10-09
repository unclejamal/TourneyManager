package com.pduda.tourney.web.jsf;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
public class JsfWebUtils implements WebUtils {

    @Override
    public void redirect(String uri, RequestParam... params) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(addParamsToUri(uri, params));

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getRequestAttribute(Object request, String attribute) {
        ActionEvent event = (ActionEvent) request;
        return event.getComponent().getAttributes().get(attribute).toString();
    }

    @Override
    public String getRequestParameter(String parameter) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter);

    }

    private String addParamsToUri(String uri, RequestParam[] requestParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(uri);

        if (requestParams.length > 0) {
            sb.append("?");
            for (RequestParam requestParam : requestParams) {
                sb.append(requestParam.name);
                sb.append("=");
                sb.append(requestParam.value);
                sb.append("&");
            }
        }

        return sb.toString();
    }
}
