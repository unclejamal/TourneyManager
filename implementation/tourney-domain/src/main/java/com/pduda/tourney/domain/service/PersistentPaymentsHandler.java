package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.*;
import com.pduda.tourney.domain.fee.MembershipType;
import com.pduda.tourney.domain.fee.Payroll;
import com.pduda.tourney.domain.fee.PointsThresholdFeeCalculatorKey.Licensed;
import com.pduda.tourney.domain.fee.WkpsCompositeFeeCalculator;
import com.pduda.tourney.domain.fee.WkpsPayrollReader;
import com.pduda.tourney.domain.repository.PayrollRepo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

@Named
@Transactional
public class PersistentPaymentsHandler implements PaymentsHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(PersistentPaymentsHandler.class.getName());
    @Inject
    private WkpsCompositeFeeCalculator wkpsCalc;
    @Inject
    private WkpsPayrollReader payrollReader;
    @Inject
    private PayrollRepo payrollRepo;

    @Override
    public long getDefaultFee(Player player, TourneyCategory tourneyCategory) {
        log.log(Level.INFO, "System is checking the fee for the player {0}", new Object[]{player});
        Payroll payroll = getPayrollFromRepo();

        MembershipType membershipType = payroll.getMembershipType(player.getFullName());
        return wkpsCalc.getFee(tourneyCategory, licensed(membershipType), player.getPoints(), player.getRankClass());
    }

    private Licensed licensed(MembershipType membershipType) {
        if (MembershipType.MEMBER_WITH_PAYMENT == membershipType) {
            return Licensed.LICENSED_AT_THIS_CLUB;
        }

        return Licensed.NOT_LICENCED;
    }

    @Override
    public Date getPayrollLastUpdate() {
        Payroll payroll = getPayrollFromRepo();
        return payroll.getLastUpdate();
    }

    @Override
    public void updatePayroll() {
        System.out.println("pduda updatePayroll");
        Payroll newPayroll = payrollReader.loadPayroll();
        Payroll persisted = payrollRepo.persistAndReturn(newPayroll);
    }

    @Override
    public Payroll getPayroll() {
        Payroll payroll = getPayrollFromRepo();
        return payroll;
    }

    private Payroll getPayrollFromRepo() {
//        initPayroll();
        System.out.println("pduda getpayrollfromrepo count " + payrollRepo.getCount());

        List<Payroll> findEntities = payrollRepo.findEntities();

        if (!findEntities.isEmpty()) {
            Payroll payroll = findEntities.get(0);
            return payroll;
        }

        Payroll nullPayroll = new Payroll(null);
        return nullPayroll;
    }

    private void initPayroll() {
        if (0 == payrollRepo.getCount()) {
            System.out.println("pduda kant");
            Payroll nullPayroll = new Payroll();
            payrollRepo.persistAndReturn(nullPayroll);
            System.out.println("pduda created nullpayroll");

            System.out.println("pduda kant2 " + payrollRepo.getCount());
        }
    }
}
