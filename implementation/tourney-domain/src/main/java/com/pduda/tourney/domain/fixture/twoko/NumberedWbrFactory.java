package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.TourneyEvent;
import java.io.Serializable;

public class NumberedWbrFactory implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "W";
    private int freeBracketId = 0;
    private final TourneyEvent tourneyEvent;

    public NumberedWbrFactory(TourneyEvent tourneyEvent) {
        this.tourneyEvent = tourneyEvent;
    }

    public Bracket createNextBracket() {
        return new Bracket(tourneyEvent, PREFIX, round(freeBracketId), gameNumberWithinRound(freeBracketId++));
    }

    private int round(int id) {
        if (id == 0) {
            return 1;
        } else if ((id >= 1) && (id <= 2)) {
            return 2;
        } else if ((id >= 3) && (id <= 6)) {
            return 4;
        } else if ((id >= 7) && (id <= 14)) {
            return 8;
        } else if ((id >= 15) && (id <= 30)) {
            return 16;
        } else if ((id >= 31) && (id <= 62)) {
            return 32;
        } else if ((id >= 63) && (id <= 126)) {
            return 64;
        } else if ((id >= 127) && (id <= 254)) {
            return 128;
        }

        throw new RuntimeException("strange id");
    }

    private int gameNumberWithinRound(int id) {
        if (id == 0) {
            return 1;
        } else if ((id >= 1) && (id <= 2)) {
            return id;
        } else if ((id >= 3) && (id <= 6)) {
            return id - 2;
        } else if ((id >= 7) && (id <= 14)) {
            return id - 6;
        } else if ((id >= 15) && (id <= 30)) {
            return id - 14;
        } else if ((id >= 31) && (id <= 62)) {
            return id - 30;
        } else if ((id >= 63) && (id <= 126)) {
            return id - 62;
        } else if ((id >= 127) && (id <= 254)) {
            return id - 126;
        }

        throw new RuntimeException("strange id");
    }
}
