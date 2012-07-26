package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.fee.WkpsMembership.MembershipType;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/com/pduda/tourney/domain-context.xml")
public class WkpsMembershipTest {
    @Inject
    private WkpsMembership cut;
    
    @Before
    public void setUp() {
    }

    @Test
    public void test() {
        assertEquals(MembershipType.MEMBER_PAID, cut.getMembershipType("Dawid Liptak"));
        assertEquals(MembershipType.MEMBER_UNPAID, cut.getMembershipType("Maciej Drabik"));
        assertEquals(MembershipType.NOT_MEMBER, cut.getMembershipType("Edek Klamka"));
    }
}


