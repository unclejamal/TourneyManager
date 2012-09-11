package com.pduda.tourney.domain.adapters.repository.jpa;

import com.pduda.tourney.domain.fee.Payroll;
import com.pduda.tourney.domain.repository.PayrollRepo;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class JpaPayrollRepo extends JpaBaseRepo<Payroll, Long> implements PayrollRepo {

    @PersistenceContext(name = "tourney-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Payroll getNewestPayroll() {
        // FIXME pduda
        List<Payroll> findEntities = findEntities(1, 0);
        return findEntities.get(0);
    }
}
