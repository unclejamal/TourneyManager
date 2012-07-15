package com.pduda.tourney.domain;

import java.io.Serializable;

public class Table implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String name;

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
