package com.pduda.tourney.web.jsf;

public class RequestParam {

    public final String name;
    public final String value;

    public RequestParam(String name, Object value) {
        this.name = name;
        this.value = value.toString();
    }
}
