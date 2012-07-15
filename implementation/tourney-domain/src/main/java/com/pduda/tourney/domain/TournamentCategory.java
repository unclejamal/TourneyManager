package com.pduda.tourney.domain;

public enum TournamentCategory {

    OS(false, false),
    OD(true, false),
    WS(false, true),
    WD(true, true);
    private boolean doub;
    private boolean women;

    TournamentCategory(boolean doub, boolean women) {
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