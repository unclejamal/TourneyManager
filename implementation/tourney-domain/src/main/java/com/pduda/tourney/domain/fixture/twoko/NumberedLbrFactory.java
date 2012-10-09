package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.TourneyEvent;
import java.io.Serializable;

public class NumberedLbrFactory implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "L";
    private int freeBracketId = 0;
    private final TourneyEvent tourneyEvent;

    public NumberedLbrFactory(TourneyEvent tourneyEvent) {
        this.tourneyEvent = tourneyEvent;
    }

    public Bracket createNextBracket() {
        Bracket bracket = new Bracket(tourneyEvent, PREFIX, round(freeBracketId), gameNumberWithinRound(freeBracketId));
        bracket.setPlace(place(freeBracketId++));
        return bracket;
    }

    private int round(int id) {
        if (id == 0) {
            return 3;
        } else if (id == 1) {
            return 4;
        } else if ((id >= 2) && (id <= 3)) {
            return 6;
        } else if ((id >= 4) && (id <= 5)) {
            return 8;
        } else if ((id >= 6) && (id <= 9)) {
            return 12;
        } else if ((id >= 10) && (id <= 13)) {
            return 16;
        } else if ((id >= 14) && (id <= 21)) {
            return 24;
        } else if ((id >= 22) && (id <= 29)) {
            return 32;
        } else if ((id >= 30) && (id <= 45)) {
            return 48;
        } else if ((id >= 46) && (id <= 61)) {
            return 64;
        }

        throw new RuntimeException("strange id: " + id);
    }

    private int gameNumberWithinRound(int id) {
        if (id < 2) {
            return 1;
        } else if ((id >= 2) && (id <= 3)) {
            return id - 1;
        } else if ((id >= 4) && (id <= 5)) {
            return id - 3;
        } else if ((id >= 6) && (id <= 9)) {
            return id - 5;
        } else if ((id >= 10) && (id <= 13)) {
            return id - 9;
        } else if ((id >= 14) && (id <= 21)) {
            return id - 13;
        } else if ((id >= 22) && (id <= 29)) {
            return id - 21;
        } else if ((id >= 30) && (id <= 45)) {
            return id - 29;
        } else if ((id >= 46) && (id <= 61)) {
            return id - 45;
        }

        throw new RuntimeException("strange id: " + id);
    }

    private String place(int id) {
        if (id == 0) {
            return "3";
        } else if (id == 1) {
            return "4";
        } else if ((id >= 2) && (id <= 3)) {
            return "5/6";
        } else if ((id >= 4) && (id <= 5)) {
            return "7/8";
        } else if ((id >= 6) && (id <= 9)) {
            return "9/12";
        } else if ((id >= 10) && (id <= 13)) {
            return "13/16";
        } else if ((id >= 14) && (id <= 21)) {
            return "17/24";
        } else if ((id >= 22) && (id <= 29)) {
            return "25/32";
        } else if ((id >= 30) && (id <= 45)) {
            return "33/48";
        } else if ((id >= 46) && (id <= 61)) {
            return "49/64";
        }

        throw new RuntimeException("strange id: " + id);
    }
}
