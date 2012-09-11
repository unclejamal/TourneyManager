package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.TourneyCategory;
import com.pduda.tourney.domain.fee.Payroll;
import java.util.Date;

public interface PaymentsHandler {

    long getDefaultFee(Player player, TourneyCategory tourneyCategory);

    Date getPayrollLastUpdate();

    Payroll getPayroll();

    void updatePayroll();
}
