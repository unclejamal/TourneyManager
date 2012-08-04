package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.TournamentCategory;
import com.pduda.tourney.domain.fee.MembershipType;
import java.util.Date;
import java.util.Map;


public interface FeeHandler {

    long getDefaultFee(Player player, TournamentCategory tourneyCategory);

    Date getMembershipPaymentsLastUpdate();

    void updateMembershipPayments();

    Map<String, MembershipType> getPayroll();

}
