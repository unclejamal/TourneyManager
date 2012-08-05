package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.*;
import com.pduda.tourney.domain.fee.FeeCalculatorKey.Licensed;
import com.pduda.tourney.domain.fee.MembershipType;
import com.pduda.tourney.domain.fee.WkpsFeeCalculator;
import com.pduda.tourney.domain.fee.WkpsMembership;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DefaultFeeHandler implements FeeHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(DefaultFeeHandler.class.getName());
    @Inject
    private WkpsFeeCalculator wkpsCalc;
    @Inject
    private WkpsMembership membership;

    @Override
    public long getDefaultFee(Player player, TourneyCategory tourneyCategory) {
        log.log(Level.INFO, "System is checking the fee for the player {0}", new Object[]{player});
        return wkpsCalc.getFee(tourneyCategory, membership(membership.getMembershipType(player.getFullName())), player.getPoints(), player.getRankClass());
    }

    private Licensed membership(MembershipType membershipType) {
        if (MembershipType.MEMBER_PAID == membershipType) {
            return Licensed.LICENSED_AT_THIS_CLUB;
        }

        return Licensed.NOT_LICENCED;
    }

    @Override
    public Date getMembershipPaymentsLastUpdate() {
        return membership.getLastUpdate();
    }

    @Override
    public void updateMembershipPayments() {
        membership.loadMembershipData();
    }

    @Override
    public Map<String, MembershipType> getPayroll() {
        return membership.getPayroll();
    }
}
