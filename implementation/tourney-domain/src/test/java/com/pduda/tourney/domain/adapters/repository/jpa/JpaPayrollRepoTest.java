package com.pduda.tourney.domain.adapters.repository.jpa;

import com.pduda.tourney.domain.adapters.repository.jpa.JpaPayrollRepo;
import com.pduda.tourney.domain.fee.MembershipType;
import com.pduda.tourney.domain.fee.Payroll;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-test-context.xml"})
@Transactional
public class JpaPayrollRepoTest {

    @Inject
    private JpaPayrollRepo cut;

    @Test
    public void testCreateTournament() {
        Payroll payroll = new Payroll();
        payroll.add("Edek Paid", MembershipType.MEMBER_WITH_PAYMENT);
        payroll.add("Edek Unpaid", MembershipType.MEMBER_WITHOUT_PAYMENT);
        payroll.add("Edek Not", MembershipType.NOT_MEMBER);

        cut.persistAndReturn(payroll);
        Payroll persisted = cut.getNewestPayroll();

        assertEquals(MembershipType.MEMBER_WITH_PAYMENT, persisted.getMembershipType("Edek Paid"));
        assertEquals(MembershipType.MEMBER_WITHOUT_PAYMENT, persisted.getMembershipType("Edek Unpaid"));
        assertEquals(MembershipType.NOT_MEMBER, persisted.getMembershipType("Edek Not"));
    }
}
