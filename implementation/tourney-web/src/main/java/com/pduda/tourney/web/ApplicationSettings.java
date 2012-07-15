package com.pduda.tourney.web;

import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named("app")
@Scope("singleton")
public class ApplicationSettings {

    private String timePattern = "HH:mm";
    private String datePattern = "yyyy-MM-dd HH:mm";

    public String getTimePattern() {
        return timePattern;
    }

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
