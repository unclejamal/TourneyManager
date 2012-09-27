package com.pduda.tourney.domain;

public enum EventCategory {

    AS(false, false),
    AD(true, false),
    OS(false, false),
    OD(true, false),
    WS(false, true),
    WD(true, true),
    DYP(true, false);
    private boolean doub;
    private boolean women;

    EventCategory(boolean doub, boolean women) {
        this.doub = doub;
        this.women = women;
    }

    public boolean isDouble() {
        return doub;
    }

    public boolean isWomen() {
        return women;
    }
}