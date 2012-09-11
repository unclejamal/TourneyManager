package com.pduda.tourney.domain.repository;

import com.pduda.tourney.domain.fee.Payroll;

public interface PayrollRepo extends BaseRepo<Payroll, Long> {

    Payroll getNewestPayroll();
}
