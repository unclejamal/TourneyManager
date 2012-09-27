package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.Team;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class TeamConverter implements Converter {

    private static final TeamStringifier teamStringifier = new TeamStringifier();

    public Object getAsObject(FacesContext facesContext, UIComponent component, String s) {
        return teamStringifier.stringToObject(s);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object o) {
        if (o == null) {
            return null;
        }

        return teamStringifier.objectToString((Team) o);
    }
}