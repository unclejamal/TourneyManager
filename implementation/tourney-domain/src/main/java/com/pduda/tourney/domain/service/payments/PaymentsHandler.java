package com.pduda.tourney.domain.service.payments;

import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.fee.Payroll;
import java.util.Date;

public interface PaymentsHandler {

    long getDefaultFee(RankingPlayer player, EventCategory tourneyCategory);

    Date getPayrollLastUpdate();

    Payroll getPayroll();

    void updatePayroll();
}
