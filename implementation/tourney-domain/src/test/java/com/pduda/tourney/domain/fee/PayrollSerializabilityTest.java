package com.pduda.tourney.domain.fee;

import java.io.Serializable;
import junitx.extensions.SerializabilityTestCase;

public class PayrollSerializabilityTest extends SerializabilityTestCase {

    public PayrollSerializabilityTest(String name) {
        super(name);
    }

    @Override
    protected void checkThawedObject(Serializable expected, Serializable actual) throws Exception {
        Payroll expectedPayroll = (Payroll) expected;
        Payroll actualPayroll = (Payroll) actual;
        assertEquals(expectedPayroll.getId(), actualPayroll.getId());
        assertEquals(MembershipType.MEMBER_WITH_PAYMENT, actualPayroll.getMembershipType("Edek Paid"));
        assertEquals(MembershipType.MEMBER_WITHOUT_PAYMENT, actualPayroll.getMembershipType("Edek Unpaid"));
        assertEquals(MembershipType.NOT_MEMBER, actualPayroll.getMembershipType("Edek Not"));
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected Serializable createInstance() throws Exception {
        Payroll payroll = new Payroll();
        payroll.add("Edek Paid", MembershipType.MEMBER_WITH_PAYMENT);
        payroll.add("Edek Unpaid", MembershipType.MEMBER_WITHOUT_PAYMENT);
        payroll.add("Edek Not", MembershipType.NOT_MEMBER);

        return payroll;
    }
}
