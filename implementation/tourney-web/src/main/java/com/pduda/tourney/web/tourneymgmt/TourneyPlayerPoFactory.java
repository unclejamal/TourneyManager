package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.EventPlayer;

public class TourneyPlayerPoFactory {

    public PlayerPo createPo(EventPlayer eventPlayer) {
        PlayerPo po = new PlayerPo();

        po.setFullName(eventPlayer.getFullName());
        po.setClub(eventPlayer.getClub());
        po.setPoints(eventPlayer.getPoints());
        po.setRankClass(eventPlayer.getRankClass());
        po.setFee(eventPlayer.getFee());

        return po;
    }
}
