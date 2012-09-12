package com.pduda.tourney.domain.service;

import java.util.Date;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-test-context.xml"})
public class PersistentPaymentsHandlerTest {

    @Inject
    private PersistentPaymentsHandler cut;

    @Test
    public void test() {
        Date date = cut.getPayrollLastUpdate();

        assertNull(date);
    }
}
