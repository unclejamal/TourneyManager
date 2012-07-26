package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.TournamentCategory;
import java.util.Date;


public interface FeeHandler {

    long getDefaultFee(Player player, TournamentCategory tourneyCategory);

    Date getMembershipPaymentsLastUpdate();

    void updateMembershipPayments();

}
